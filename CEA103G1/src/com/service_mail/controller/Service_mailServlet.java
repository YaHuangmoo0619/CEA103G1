package com.service_mail.controller;

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

import com.service_mail.model.Service_mailService;
import com.service_mail.model.Service_mailVO;

@WebServlet("/service_mail/service_mail.do")
public class Service_mailServlet extends HttpServlet {
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/service_mail/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				Service_mailService service_mailSvc = new Service_mailService();
				Set<Service_mailVO> service_mailVOSet = service_mailSvc.getWhereCondition(map);
				req.setAttribute("service_mailVOSet", service_mailVOSet);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/service_mail/listWhereService_mail.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/service_mail/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}