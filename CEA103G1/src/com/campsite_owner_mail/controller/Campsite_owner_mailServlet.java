package com.campsite_owner_mail.controller;

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

import com.campsite_owner_mail.model.Campsite_owner_mailService;
import com.campsite_owner_mail.model.Campsite_owner_mailVO;
import com.member_mail.model.Member_mailService;
import com.member_mail.model.Member_mailVO;

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
					errorMsgs.put("notFound", new String[] {"�п�ܩο�J�d������r"});
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

		if("insert".equals(action)){
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				String send_noTest = req.getParameter("send_no");
				if(send_noTest.equals("99")) {
					errorMsgs.put("send_no", new String[] {"�п�ܱH��H�s��"});
					System.out.print('a');
				}
				Integer send_no = Integer.valueOf(send_noTest);
				
				String rcpt_noTest = req.getParameter("rcpt_no");
				if(rcpt_noTest.equals("99")) {
					errorMsgs.put("rcpt_no", new String[] {"�п�ܦ���H�s��"});
					System.out.print('b');
				}
				Integer rcpt_no = Integer.valueOf(rcpt_noTest);
				
				String mail_cont = req.getParameter("mail_cont");
				if(mail_cont.trim().isEmpty()) {
					errorMsgs.put("mail_cont", new String[] {"�п�J�H�󤺮e"});
					System.out.print('c');
				}

				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite_owner_mail/addCampsite_owner_mail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				Integer mail_stat =  Integer.valueOf(req.getParameter("mail_stat"));
				Integer mail_read_stat =  Integer.valueOf(req.getParameter("mail_read_stat"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String mail_time = sdf.format(new java.util.Date());
				
				Campsite_owner_mailService campsite_owner_mailSvc = new Campsite_owner_mailService();
				Campsite_owner_mailVO campsite_owner_mailVO = campsite_owner_mailSvc.addCampsite_owner_mail(send_no,rcpt_no,mail_read_stat,mail_stat,mail_cont,mail_time);
				req.setAttribute("campsite_owner_mailVO", campsite_owner_mailVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/campsite_owner_mail/listAllCampsite_owner_mail.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite_owner_mail/addCampsite_owner_mail.jsp");
				failureView.forward(req, res);
			}
		}

		if("getOne_For_Update".equals(action)) {
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Campsite_owner_mailVO campsite_owner_mailVO = new Campsite_owner_mailService().getOneCampsite_owner_mail(Integer.valueOf(req.getParameter("mail_no")));
				req.setAttribute("campsite_owner_mailVO", campsite_owner_mailVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/campsite_owner_mail/update_campsite_owner_mail_input.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite_owner_mail/listAllCampsite_owner_mail_mail.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String send_noTest = req.getParameter("send_no");
				if(send_noTest.equals("99")) {
					errorMsgs.put("send_no", new String[] {"�п�ܭ��u�s��"});
				}
				Integer send_no = Integer.valueOf(send_noTest);
				
				String rcpt_noTest = req.getParameter("rcpt_no");
				if(rcpt_noTest.equals("99")) {
					errorMsgs.put("rcpt_no", new String[] {"�п�ܷ|���s��"});
				}
				Integer rcpt_no = Integer.valueOf(rcpt_noTest);
				
				String mail_cont = req.getParameter("mail_cont");
				if(mail_cont.trim().isEmpty()) {
					errorMsgs.put("mail_cont", new String[] {"�п�J�H�󤺮e"});
				}

				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite_owner_mail/update_campsite_owner_mail_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				Integer mail_stat =  Integer.valueOf(req.getParameter("mail_stat"));
				Integer mail_read_stat =  Integer.valueOf(req.getParameter("mail_read_stat"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String mail_time = sdf.format(new java.util.Date());
				Integer mail_no =  Integer.valueOf(req.getParameter("mail_no"));
				
				Campsite_owner_mailService campsite_owner_mailSvc = new Campsite_owner_mailService();
				Campsite_owner_mailVO campsite_owner_mailVO = campsite_owner_mailSvc.updateCampsite_owner_mail(mail_no,send_no,rcpt_no,mail_read_stat,mail_stat,mail_cont,mail_time);
				req.setAttribute("campsite_owner_mailVO", campsite_owner_mailVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/campsite_owner_mail/listAllCampsite_owner_mail.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite_owner_mail/update_campsite_owner_mail_input.jsp");
				failureView.forward(req, res);
			}
		}
	}
}