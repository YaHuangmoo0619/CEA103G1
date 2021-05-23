package com.campsite.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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

@ServerEndpoint("/webSocket/CampWS/{campInfo}")
public class CampWS {
	private static Map<String, Set<Session>> camps = new ConcurrentHashMap();
	private static HashMap<String, Set<String>> history = new HashMap();

	@OnOpen
	public void onOpen(@PathParam("campInfo") String campInfo, Session session) throws IOException {
		System.out.println("WS~~~~~");
		if (!camps.containsKey(campInfo)) {
			Set<Session> camp = new HashSet<Session>();
			camp.add(session);
			camps.put(campInfo, camp);
		} else {
			camps.get(campInfo).add(session);
		}
		Set<String> plc = history.get(campInfo);
		if (plc != null) {
			System.out.println(plc);
			String allplc = plc.toString().substring(1, plc.toString().length() - 1);
			if (!allplc.contains(", ")) {
				session.getAsyncRemote().sendText(allplc + "&no");
			} else {
				allplc = allplc.replaceAll(", ", "&&");
				System.out.println(allplc);
				session.getAsyncRemote().sendText(allplc);
			}
		}
	}

	@OnClose
	public void disConnect(@PathParam("campInfo") String campInfo, Session session) {
		camps.get(campInfo).remove(session);
		if (camps.get(campInfo).size() == 0) {
			history.put(campInfo, null);
		}
		System.out.println("a client has disconnected!");
	}

	@OnMessage
	public void receiveMsg(@PathParam("campInfo") String campInfo, String plc_no, Session session) throws Exception {
		System.out.println(plc_no);
		System.out.println(campInfo);
		if (plc_no.contains("release")) {
			plc_no = plc_no.split("&")[0];
			Set<String> historyPlc = history.get(campInfo);
			System.out.println(historyPlc);
			historyPlc.remove(plc_no);
			System.out.println(historyPlc);
			history.put(campInfo, historyPlc);
			for (Session receiver : camps.get(campInfo)) {
				if (receiver != session) {
					receiver.getAsyncRemote().sendText(plc_no + "&yes");
				} else {
					session.getAsyncRemote().sendText(plc_no + "&yes");
				}
			}
		} else if (plc_no.contains("&&&") || plc_no.contains("gone")) {
			for (Session receiver : camps.get(campInfo)) {
				receiver.getAsyncRemote().sendText(plc_no);
			}
		} else {
			Set<String> historyPlc = history.get(campInfo);
			System.out.println(historyPlc);
			if (historyPlc == null) {
				historyPlc = new HashSet<String>();
				historyPlc.add(plc_no);
			} else {
				historyPlc.add(plc_no);
			}
			System.out.println(historyPlc);
			history.put(campInfo, historyPlc);
			for (Session receiver : camps.get(campInfo)) {
				if (receiver != session) {
					receiver.getAsyncRemote().sendText(plc_no + "&no");
				} else {
					session.getAsyncRemote().sendText(plc_no + "&yes");
				}
			}
		}
	}

	@OnError
	public void onerror(Session session, Throwable throwable) {
		System.out.println("error¡K");
	}
}
