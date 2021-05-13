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
	public void startClock(@PathParam("userName") String userName, Session session) {
		final Session mySession = session;
		Member_mailService member_mailSvc = new Member_mailService();
		Map<String,String[]> map = new LinkedHashMap<String,String[]>();
		map.put("mail_read_stat",new String[] {"0"});
		Set<Member_mailVO> set = member_mailSvc.getWhereCondition(map);
		int countNoRead = 0;
		for(Member_mailVO vo : set) {
			System.out.println(vo.getRcpt_no());
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
	
}
