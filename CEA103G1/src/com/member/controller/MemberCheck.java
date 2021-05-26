package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.member.model.MemberService;

@WebServlet("/member/MemberCheck")
public class MemberCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		res.setContentType("BIG5");
		PrintWriter out = res.getWriter();
		
		if("checkAcc".equals(action)) {
			String acc = req.getParameter("acc");
			MemberService memberSvc = new MemberService();
			String accChecked = memberSvc.findByAcc(acc);
			if( accChecked != null  && !accChecked.isEmpty()) {
				JSONObject jsonStr = null;
				try {
					jsonStr = new JSONObject("{acc:duplicate}");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				out.println(jsonStr);
			}else {
				JSONObject jsonStr = null;
				try {
					jsonStr = new JSONObject("{acc:notFound}");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				out.println(jsonStr);
			}
		}
	}

}
