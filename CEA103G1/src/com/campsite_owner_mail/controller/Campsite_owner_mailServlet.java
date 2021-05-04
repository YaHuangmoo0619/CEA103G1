package com.campsite_owner_mail.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campsite_owner_mail.model.Campsite_owner_mailService;
import com.campsite_owner_mail.model.Campsite_owner_mailVO;

@WebServlet("/campsite_owner_mail/campsite_owner_mail.do")
public class Campsite_owner_mailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("compositeSearch".equals(action)) {
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {	
				Map<String,String[]> map = req.getParameterMap();
				Set<String> keys = map.keySet();
				int checkCount = 0;
				for(String key: keys) {
					switch (map.get(key)[0]) {
					case "no":
					case "":
					case "compositeSearch":
						checkCount++;
					}
				}
				if(checkCount == 8) {
					errorMsgs.put("notFound", new String[] {"請選擇或輸入查詢關鍵字"});
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite_owner_mail/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
			
				Campsite_owner_mailService campsite_owner_mailSvc = new Campsite_owner_mailService();
				Set<Campsite_owner_mailVO> campsite_owner_mailVOSet = campsite_owner_mailSvc.getWhereCondition(map);
				req.setAttribute("campsite_owner_mailVOSet", campsite_owner_mailVOSet);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/campsite_owner_mail/listWhereCampsite_owner_mail.jsp");
				successView.forward(req, res);
		
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite_owner_mail/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}