package com.personal_system_notify.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.member_mail.model.Member_mailForWS;
import com.member_mail.model.Member_mailService;
import com.member_mail.model.Member_mailVO;
import com.personal_system_notify.model.Personal_System_NotifyService;
import com.personal_system_notify.model.Personal_System_NotifyVO;

@ServerEndpoint ("/Personal_system_notifyNotify/{userName}")
public class Personal_system_notifyNotify {
private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) {
		final Session mySession = userSession;
		sessionsMap.put(userName, userSession);
		Personal_System_NotifyService personal_system_notifySvc = new Personal_System_NotifyService();
		Map<String,String[]> map = new LinkedHashMap<String,String[]>();
		map.put("ntfy_stat",new String[] {"0"});
		Set<Personal_System_NotifyVO> set = personal_system_notifySvc.getWhereCondition(map);
		int countNoRead = 0;
		for(Personal_System_NotifyVO vo : set) {
//			System.out.println(vo.getRcpt_no());
			if(userName.equals(vo.getMbr_no().toString()) && "0".equals(vo.getNtfy_stat().toString())) {
				countNoRead++;
			}
		}
		try {
			mySession.getBasicRemote().sendText(Integer.valueOf(countNoRead).toString());
			System.out.println(Integer.valueOf(countNoRead).toString());
			System.out.println(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@OnMessage
	public void onMessage(String rcpt_no) {
		System.out.println("onMessage="+rcpt_no);
		System.out.println("size="+sessionsMap.size());
		for(String key : sessionsMap.keySet()) {
			System.out.println(key);
			if(key.equals(rcpt_no)) {
				System.out.println(key+"in");
				Member_mailService member_mailSvc = new Member_mailService();
				Map<String,String[]> map = new LinkedHashMap<String,String[]>();
				map.put("mail_read_stat",new String[] {"0"});
				Set<Member_mailVO> set = member_mailSvc.getWhereCondition(map);
				List<Integer> mail_noList = new ArrayList<Integer>();
				int countNoRead = 0;
				for(Member_mailVO vo : set) {
//					System.out.println(vo.getRcpt_no());
					if(rcpt_no.equals(vo.getRcpt_no().toString()) && "0".equals(vo.getMail_stat().toString())) {
						mail_noList.add(vo.getMail_no());
						countNoRead++;
					}
				}
				try {
					Member_mailForWS member_mailForWS = new Member_mailForWS();
					Member_mailVO member_mailVO = member_mailSvc.getOneMember_mail(mail_noList.get(mail_noList.size() - 1));
					member_mailForWS.setMail_no(member_mailVO.getMail_no());
					member_mailForWS.setSend_no(member_mailVO.getSend_no());
					member_mailForWS.setRcpt_no(member_mailVO.getRcpt_no());
					member_mailForWS.setMail_read_stat(member_mailVO.getMail_read_stat());
					member_mailForWS.setMail_stat(member_mailVO.getMail_stat());
					member_mailForWS.setMail_cont(member_mailVO.getMail_cont());
					member_mailForWS.setMail_time(member_mailVO.getMail_time());
					member_mailForWS.setCountNoRead(countNoRead);
					
					String jsonStr = new JSONObject(member_mailForWS).toString();
					sessionsMap.get(key).getBasicRemote().sendText(jsonStr);
//					sessionsMap.get(key).getBasicRemote().sendText(Integer.valueOf(countNoRead).toString());
					System.out.println("send="+Integer.valueOf(countNoRead).toString());
					System.out.println("send="+rcpt_no);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				System.out.println(key+"out");
				continue;
				
			}
		}
	}
}
