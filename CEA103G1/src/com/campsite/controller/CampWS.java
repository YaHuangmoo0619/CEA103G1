package com.campsite.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
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

@ServerEndpoint("/webSocket/CampWS/{campInfo}")
public class CampWS {
	private static Map<String, Set<Session>> camps = new ConcurrentHashMap<String, Set<Session>>();
	private static HashMap<String, Set<String>> history = new HashMap<String, Set<String>>();
	private static HashMap<String, HashMap<Session, Set<String>>> forDisconnect = new HashMap();

	@OnOpen
	public void onOpen(@PathParam("campInfo") String campInfo, Session session) throws Exception {
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

		Set<String> all = new HashSet<String>();
		all.addAll(DateInside(camps, campInfo));
		all.addAll(DateOutside(camps, campInfo));
		System.out.println(all);

		Set<String> plc = new HashSet<String>();
		for (String eachInfo : all) {
			System.out.println("測試" + eachInfo);
			Set<String> temp = history.get(eachInfo);
			if (temp != null) {
				plc.addAll(temp);
			}
			System.out.println("嗨");
		}
//		Set<String> plc = history.get(campInfo);
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
	public void disConnect(@PathParam("campInfo") String campInfo, Session session) throws Exception {
		System.out.println("disconnect:" + campInfo + " " + session);
		Set<String> forDisPlc = forDisconnect.get(campInfo).get(session);
		System.out.println("disconnect:" + forDisPlc);
		Set<String> all = new HashSet<String>();
		all.addAll(DateInside(camps, campInfo));
		all.addAll(DateOutside(camps, campInfo));
		if (forDisPlc != null) {
			String disPlc = forDisPlc.toString().substring(1, forDisPlc.toString().length() - 1);
			if (!disPlc.contains(", ")) {
				Set<String> left = history.get(campInfo);
				left.remove(disPlc);
				history.put(campInfo, left);
				for (String eachInfo : all) {
					for (Session receiver : camps.get(eachInfo)) {
						if (receiver != session) {
							receiver.getAsyncRemote().sendText(disPlc + "&yes");
						}
					}
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
				Set<String> left = new HashSet<String>();
				for (int i = 0; i < historyPlc.length; i++) {
					if (historyPlc[i] != null) {
						left.add(historyPlc[i]);
					}
				}
				System.out.println("disconnect:" + "剩下的" + left);
				history.put(campInfo, left);
				disPlc = disPlc.replaceAll(", ", "&dis&");
				System.out.println(disPlc);
				for (String eachInfo : all) {
					for (Session receiver : camps.get(eachInfo)) {
						if (receiver != session) {
							receiver.getAsyncRemote().sendText(disPlc);
						}
					}
				}
			}
		}
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
		Set<String> all = new HashSet<String>();
		all.addAll(DateInside(camps, campInfo));
		all.addAll(DateOutside(camps, campInfo));
		System.out.println(all);
		if (plc_no.contains("release")) {
			plc_no = plc_no.split("&")[0];
			Set<String> historyPlc = history.get(campInfo);
			System.out.println("移除前" + historyPlc);
			historyPlc.remove(plc_no);
			System.out.println("移除後" + historyPlc);
			history.put(campInfo, historyPlc);

			Set<String> forDisPlc = forDisconnect.get(campInfo).get(session);
			forDisPlc.remove(plc_no);
			HashMap<Session, Set<String>> forDisSession = forDisconnect.get(campInfo);
			forDisSession.put(session, forDisPlc);
			forDisconnect.put(campInfo, forDisSession);
			System.out.println("離線用" + forDisconnect.get(campInfo).get(session));

			for (String eachInfo : all) {
				for (Session receiver : camps.get(eachInfo)) {
					receiver.getAsyncRemote().sendText(plc_no + "&yes");
				}
			}
//			for (Session receiver : camps.get(campInfo)) {
//				if (receiver != session) {
//					receiver.getAsyncRemote().sendText(plc_no + "&yes");
//				} else {
//					session.getAsyncRemote().sendText(plc_no + "&yes");
//				}
//			}
		} else if (plc_no.contains("&&&") || plc_no.contains("gone")) {
			for (String eachInfo : all) {
				for (Session receiver : camps.get(eachInfo)) {
					receiver.getAsyncRemote().sendText(plc_no);
				}
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

			for (String eachInfo : all) {
				for (Session receiver : camps.get(eachInfo)) {
					if (receiver != session) {
						receiver.getAsyncRemote().sendText(plc_no + "&no");
					} else {
						session.getAsyncRemote().sendText(plc_no + "&yes");
					}
				}
			}

//			for (Session receiver : camps.get(campInfo)) {
//				if (receiver != session) {
//					receiver.getAsyncRemote().sendText(plc_no + "&no");
//				} else {
//					session.getAsyncRemote().sendText(plc_no + "&yes");
//				}
//			}
		}
	}

	@OnError
	public void onerror(Session session, Throwable throwable) {
		System.out.println("error…");
	}

	public Set<String> DateInside(Map<String, Set<Session>> camps, String campInfo) throws ParseException {
		Set<String> pass = new HashSet<String>();
		DateFormat dateFo = new SimpleDateFormat("yyyy-MM-dd");
		String camp_no = campInfo.split("&")[0].split("=")[1];
		String start = campInfo.split("&")[1].split("=")[1];
		String end = campInfo.split("&")[2].split("=")[1];
		long startdate = dateFo.parse(start).getTime();
		long enddate = dateFo.parse(end).getTime();
		List<String> interval = new ArrayList<String>();
		while (startdate != enddate) {
			startdate = startdate + 24 * 60 * 60 * 1000;
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(startdate);
			interval.add(dateFo.format(calendar.getTime()));
		}
		interval.remove(interval.size() - 1);
		for (String eachInfo : camps.keySet()) {
			String eachCamp_no = eachInfo.split("&")[0].split("=")[1];
			String eachStart = eachInfo.split("&")[1].split("=")[1];
			String eachEnd = eachInfo.split("&")[2].split("=")[1];
			for (String str : interval) {
				if ((eachStart.equals(str) || eachEnd.equals(str)) && eachCamp_no.equals(camp_no)) {
					pass.add(eachInfo);
				}
			}
		}
		return pass;
	}

	public Set<String> DateOutside(Map<String, Set<Session>> camps, String campInfo) throws ParseException {
		Set<String> pass = new HashSet<String>();
		DateFormat dateFo = new SimpleDateFormat("yyyy-MM-dd");
		String camp_no = campInfo.split("&")[0].split("=")[1];
		String start = campInfo.split("&")[1].split("=")[1];
		String end = campInfo.split("&")[2].split("=")[1];

		for (String eachInfo : camps.keySet()) {
			String eachCamp_no = eachInfo.split("&")[0].split("=")[1];
			String eachStart = eachInfo.split("&")[1].split("=")[1];
			String eachEnd = eachInfo.split("&")[2].split("=")[1];

			List<String> temp = new ArrayList<String>();
			long startdate = dateFo.parse(eachStart).getTime();
			long enddate = dateFo.parse(eachEnd).getTime();
			temp.add(eachStart);
			temp.add(eachEnd);
			while (startdate != enddate) {
				startdate = startdate + 24 * 60 * 60 * 1000;
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(startdate);
				temp.add(dateFo.format(calendar.getTime()));
			}
			temp.remove(temp.size() - 1);

			if (!eachCamp_no.equals(camp_no) || temp.get(0).equals(end) || temp.get(1).equals(start)) {
				continue;
			} else {
				for (int i = 2; i < temp.size(); i++) {
					if ((temp.get(i).equals(start) || temp.get(i).equals(end)) && eachCamp_no.equals(camp_no)) {
						pass.add(eachInfo);
					}
				}
			}
		}
		return pass;
	}
}
