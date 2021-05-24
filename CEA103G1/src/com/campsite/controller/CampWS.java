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
	private static HashMap<String, HashMap<Session, Set<String>>> forDisconnect = new HashMap();

	@OnOpen
	public void onOpen(@PathParam("campInfo") String campInfo, Session session) throws IOException {
		System.out.println("connect:" + campInfo + " " + session);
		if (!camps.containsKey(campInfo)) {
			Set<Session> camp = new HashSet<Session>();
			camp.add(session);
			camps.put(campInfo, camp);

			HashMap<Session, Set<String>> forDis = new HashMap<Session, Set<String>>();
			forDis.put(session, null);
			forDisconnect.put(campInfo, forDis);
		} else {
			camps.get(campInfo).add(session);

			HashMap<Session, Set<String>> forDis = forDisconnect.get(campInfo);
			forDis.put(session, null);
			forDisconnect.put(campInfo, forDis);
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
for (Session receiver : camps.get(campInfo)) {
if (receiver != session) {
System.out.println(receiver + ":" + forDisconnect.get(campInfo).get(receiver));
}}
		System.out.println("disconnect:" + campInfo + " " + session);
		Set<String> forDisPlc = forDisconnect.get(campInfo).get(session);
		System.out.println("disconnect:" + forDisPlc);
		if (forDisPlc != null) {
			String disPlc = forDisPlc.toString().substring(1, forDisPlc.toString().length() - 1);
			if (!disPlc.contains(", ")) {
				Set<String> left = history.get(campInfo);
				left.remove(disPlc);
				history.put(campInfo, left);
				for (Session receiver : camps.get(campInfo)) {
					receiver.getAsyncRemote().sendText(disPlc + "&yes");
				}
			} else {
				String[] remove = disPlc.split(", ");
				String[] historyPlc = (history.get(campInfo).toString().substring(1,
						history.get(campInfo).toString().length() - 1)).split(", ");

				for (int i = 0; i < historyPlc.length; i++) {
					for (int j = 0; j < remove.length; j++) {
						if (historyPlc[i].equals(remove[j])) {
							historyPlc[i] = null;
							break;
						}
					}
				}
				Set<String> left = new HashSet();
				for (int i = 0; i < historyPlc.length; i++) {
					if (historyPlc[i] != null) {
						left.add(historyPlc[i]);
					}
				}
				System.out.println("disconnect:" + "剩下的" + left);
				history.put(campInfo, left);
				disPlc = disPlc.replaceAll(", ", "&dis&");
				System.out.println(disPlc);
				for (Session receiver : camps.get(campInfo)) {
					if (receiver != session) {
						receiver.getAsyncRemote().sendText(disPlc);
					}
				}
			}
		}
for (Session receiver : camps.get(campInfo)) {
if (receiver != session) {
System.out.println(receiver + ":" + forDisconnect.get(campInfo).get(receiver));
}}
		camps.get(campInfo).remove(session);
		if (camps.get(campInfo).size() == 0) {
			history.put(campInfo, null);
		}
		System.out.println("a client has disconnected!");
	}

	@OnMessage
	public void receiveMsg(@PathParam("campInfo") String campInfo, String plc_no, Session session) throws Exception {
		System.out.println(plc_no);
		System.out.println(campInfo + " " + session);
		if (plc_no.contains("release")) {
			plc_no = plc_no.split("&")[0];
			Set<String> historyPlc = history.get(campInfo);
			System.out.println("移除前" + historyPlc);
			historyPlc.remove(plc_no);
			System.out.println("移除後" + historyPlc);
			history.put(campInfo, historyPlc);

			Set<String> forDisPlc = forDisconnect.get(campInfo).get(session);
			System.out.println("我有執行1");
			forDisPlc.remove(plc_no);
			System.out.println("我有執行2");
			HashMap<Session, Set<String>> forDisSession = forDisconnect.get(campInfo);
			forDisSession.put(session, forDisPlc);
			forDisconnect.put(campInfo, forDisSession);
			System.out.println("離線用" + forDisconnect.get(campInfo).get(session));

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
			System.out.println("新增前" + historyPlc);
			if (historyPlc == null) {
				historyPlc = new HashSet<String>();
				historyPlc.add(plc_no);
			} else {
				historyPlc.add(plc_no);
			}
			System.out.println("新增後" + historyPlc);
			history.put(campInfo, historyPlc);

			Set<String> forDisPlc = forDisconnect.get(campInfo).get(session);
			if (forDisPlc == null) {
				forDisPlc = new HashSet<String>();
				forDisPlc.add(plc_no);
			} else {
				forDisPlc.add(plc_no);
			}
			HashMap<Session, Set<String>> forDisSession = forDisconnect.get(campInfo);
			forDisSession.put(session, forDisPlc);
			forDisconnect.put(campInfo, forDisSession);
			System.out.println("離線用" + forDisconnect.get(campInfo).get(session));

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
		System.out.println("error…");
	}
}
