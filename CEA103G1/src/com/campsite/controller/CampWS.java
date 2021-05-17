package com.campsite.controller;

import java.io.IOException;
import java.util.Collections;
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
	}

	@OnClose
	public void disConnect(@PathParam("campInfo") String campInfo, Session session) {
		camps.get(campInfo).remove(session);
		System.out.println("a client has disconnected!");
	}

	@OnMessage
	public void receiveMsg(@PathParam("campInfo") String campInfo, String plc_no, Session session) throws Exception {
		System.out.println(plc_no);
		System.out.println(campInfo);
		if (plc_no.contains("release")) {
			plc_no = plc_no.split("&")[0];
			for (Session receiver : camps.get(campInfo)) {
				if (receiver != session) {
					receiver.getAsyncRemote().sendText(plc_no + "&yes");
				} else {
					session.getAsyncRemote().sendText(plc_no + "&yes");
				}
			}
		} else {
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
