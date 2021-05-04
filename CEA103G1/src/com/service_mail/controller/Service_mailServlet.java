package com.service_mail.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
		System.out.println("in!");
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
		
		if("insert".equals(action)){
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("in");
			try {
				
				String emp_noTest = req.getParameter("emp_no");
				if(emp_noTest.equals("99")) {
					errorMsgs.put("emp_no", new String[] {"請選擇員工編號"});
					System.out.print('a');
				}
				Integer emp_no = Integer.valueOf(emp_noTest);
				
				String mbr_noTest = req.getParameter("mbr_no");
				if(mbr_noTest.equals("99")) {
					errorMsgs.put("mbr_no", new String[] {"請選擇會員編號"});
					System.out.print('b');
				}
				Integer mbr_no = Integer.valueOf(mbr_noTest);
				
				String mail_cont = req.getParameter("mail_cont");
				if(mail_cont.trim().isEmpty()) {
					errorMsgs.put("mail_cont", new String[] {"請輸入信件內容"});
					System.out.print('c');
				}

				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/service_mail/addService_mail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				Integer mail_stat =  Integer.valueOf(req.getParameter("mail_stat"));
				Integer mail_read_stat =  Integer.valueOf(req.getParameter("mail_read_stat"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String mail_time = sdf.format(new java.util.Date());
				
				Service_mailService service_mailSvc = new Service_mailService();
				Service_mailVO service_mailVO = service_mailSvc.addService_mail(emp_no,mbr_no,mail_cont,mail_stat,mail_read_stat,mail_time);
				req.setAttribute("service_mailVO", service_mailVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/service_mail/listAllService_mail.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/service_mail/addService_mail.jsp");
				failureView.forward(req, res);
			}
		}
	}
}