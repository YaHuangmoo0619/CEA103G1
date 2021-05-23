package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;

@WebServlet("/MemberGetDistrict")
public class MemberGetDistrict extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Jedis jedis = null;
	
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=Big5");
		PrintWriter out = res.getWriter();
		
		if("city".equals(action)) {
			StringBuilder sbr = new StringBuilder();
			int count = 1;
			Set<String> set = jedis.smembers("┐дел");
			for(String city :set) {
				if(sbr.length() == 0) {
					sbr.append("{" + count + ":\"" + city + "\"");
				}else {
					sbr.append("," + count + ":\"" + city + "\"");
				}
				count++;
			}
			sbr.append("}");
			System.out.println(sbr);
			JSONObject json = null;
			try {
				json = new JSONObject(sbr.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			System.out.println(json);
			out.println(json);
		}
		
	}

	public void init() throws ServletException{
		jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");
		jedis.select(7);
	}
	
	public void destroy() {
		jedis.close();
	}
}
