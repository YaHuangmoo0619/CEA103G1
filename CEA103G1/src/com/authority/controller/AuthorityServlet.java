package com.authority.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.authority.model.AuthorityService;
import com.authority.model.AuthorityVO;

@WebServlet("/authority/authority.do")
public class AuthorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getName_For_Display".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String name = req.getParameter("name");
				if(name.equals("0")) {
					errorMsgs.put("name", "請選擇網站管理員姓名");
				}
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("errorMsgs", errorMsgs);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/authority/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				AuthorityService authoritySvc = new AuthorityService();
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/authority/listNameAuthority.jsp");
				successView.forward(req, res);
			}catch(Exception e){
				errorMsgs.put("Exception" , e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/authority/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
	}
}