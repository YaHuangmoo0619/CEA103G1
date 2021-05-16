package com.member_mail.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import com.member_mail.model.Member_mailForWS;
import com.member_mail.model.Member_mailService;
import com.member_mail.model.Member_mailVO;
import com.personal_system_notify.model.Personal_System_NotifyService;
import com.personal_system_notify.model.Personal_System_NotifyVO;

@ServerEndpoint ("/Member_mailNotify/{userName}")
public class Member_mailNotify {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) {
		//記錄有誰登入
		final Session mySession = userSession;
		sessionsMap.put(userName, userSession);
		
		//準備用service找VO
		Member_mailService member_mailSvc = new Member_mailService();
		Personal_System_NotifyService personal_system_notifySvc = new Personal_System_NotifyService();
		
		//找出未讀的
		Map<String,String[]> mapMail = new LinkedHashMap<String,String[]>();
		mapMail.put("mail_read_stat",new String[] {"0"});
		Set<Member_mailVO> setMail = member_mailSvc.getWhereCondition(mapMail);
		//---
		Map<String,String[]> mapNotify = new LinkedHashMap<String,String[]>();
		mapNotify.put("ntfy_stat",new String[] {"0"});
		Set<Personal_System_NotifyVO> setNotify = personal_system_notifySvc.getWhereCondition(mapNotify);
		
		//給予累計數字的初值為零
		int countNoReadMail = 0;
		int countNoReadNotify = 0;
		
		//開始計數
		for(Member_mailVO vo : setMail) {
//			System.out.println(vo.getRcpt_no());
			if(userName.equals(vo.getRcpt_no().toString()) && "0".equals(vo.getMail_stat().toString())) {
				countNoReadMail++;
			}
		}
		for(Personal_System_NotifyVO vo : setNotify) {
//			System.out.println(vo.getMbr_no());
			if(userName.equals(vo.getMbr_no().toString()) && "0".equals(vo.getNtfy_stat().toString())) {
				countNoReadNotify++;
			}
		}
		try {
			//將結果轉為JSONObject
			Member_mailForWS member_mailForWS = new Member_mailForWS();
			member_mailForWS.setCountNoReadMail(countNoReadMail);
			member_mailForWS.setCountNoReadNotify(countNoReadNotify);
			String jsonStr = new JSONObject(member_mailForWS).toString();
			
			//傳送JSON格式字串給前端連線的WebSocket
			mySession.getBasicRemote().sendText(jsonStr);
//			System.out.println(Integer.valueOf(countNoReadMail).toString());
//			System.out.println(userName);
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
					member_mailForWS.setCountNoReadMail(countNoRead);
					
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
