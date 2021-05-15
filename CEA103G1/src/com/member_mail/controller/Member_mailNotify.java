package com.member_mail.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
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

import com.member_mail.model.Member_mailService;
import com.member_mail.model.Member_mailVO;

@ServerEndpoint ("/Member_mailNotify/{userName}")
public class Member_mailNotify {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) {
		final Session mySession = userSession;
		sessionsMap.put(userName, userSession);
		Member_mailService member_mailSvc = new Member_mailService();
		Map<String,String[]> map = new LinkedHashMap<String,String[]>();
		map.put("mail_read_stat",new String[] {"0"});
		Set<Member_mailVO> set = member_mailSvc.getWhereCondition(map);
		int countNoRead = 0;
		for(Member_mailVO vo : set) {
//			System.out.println(vo.getRcpt_no());
			if(userName.equals(vo.getRcpt_no().toString()) && "0".equals(vo.getMail_stat().toString())) {
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
				int countNoRead = 0;
				for(Member_mailVO vo : set) {
//					System.out.println(vo.getRcpt_no());
					if(rcpt_no.equals(vo.getRcpt_no().toString()) && "0".equals(vo.getMail_stat().toString())) {
						countNoRead++;
					}
				}
				try {
					sessionsMap.get(key).getBasicRemote().sendText(Integer.valueOf(countNoRead).toString());
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
