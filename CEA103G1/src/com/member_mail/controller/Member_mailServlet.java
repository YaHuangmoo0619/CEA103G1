package com.member_mail.controller;

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

import com.member_mail.model.Member_mailService;
import com.member_mail.model.Member_mailVO;
import com.member_mail.model.Member_mailService;
import com.member_mail.model.Member_mailVO;

@WebServlet("/member_mail/member_mail.do")
public class Member_mailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("compositeSearch".equals(action) || "compositeSearchTop".equals(action)) {
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
				if("compositeSearchTop".equals(action) && map.get("mail_cont")[0].isEmpty()) {
					errorMsgs.put("notFound", new String[] {"請選擇或輸入查詢關鍵字"});
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_mail/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				if(checkCount == 8) {
					errorMsgs.put("notFound", new String[] {"請選擇或輸入查詢關鍵字"});
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/service_mail/listAllService_mail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Member_mailService member_mailSvc = new Member_mailService();
				Set<Member_mailVO> member_mailVOSet = member_mailSvc.getWhereCondition(map);
				req.setAttribute("member_mailVOSet", member_mailVOSet);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/member_mail/listWhereMember_mail.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_mail/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)){
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				String send_noTest = req.getParameter("send_no");
				if(send_noTest.equals("99")) {
					errorMsgs.put("send_no", new String[] {"請選擇寄件人編號"});
					System.out.print('a');
				}
				Integer send_no = Integer.valueOf(send_noTest);
				
				String rcpt_noTest = req.getParameter("rcpt_no");
				if(rcpt_noTest.equals("99")) {
					errorMsgs.put("rcpt_no", new String[] {"請選擇收件人編號"});
					System.out.print('b');
				}
				Integer rcpt_no = Integer.valueOf(rcpt_noTest);
				
				String mail_cont = req.getParameter("mail_cont");
				if(mail_cont.trim().isEmpty()) {
					errorMsgs.put("mail_cont", new String[] {"請輸入信件內容"});
					System.out.print('c');
				}

				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_mail/addMember_mail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				Integer mail_stat =  Integer.valueOf(req.getParameter("mail_stat"));
				Integer mail_read_stat =  Integer.valueOf(req.getParameter("mail_read_stat"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String mail_time = sdf.format(new java.util.Date());
				
				Member_mailService member_mailSvc = new Member_mailService();
				Member_mailVO member_mailVO = member_mailSvc.addMember_mail(send_no,rcpt_no,mail_read_stat,mail_stat,mail_cont,mail_time);
				req.setAttribute("member_mailVO", member_mailVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/member_mail/listAllMember_mail.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_mail/addMember_mail.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Update".equals(action)) {
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Member_mailVO member_mailVO = new Member_mailService().getOneMember_mail(Integer.valueOf(req.getParameter("mail_no")));
				req.setAttribute("member_mailVO", member_mailVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/member_mail/update_member_mail_input.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_mail/listAllMember_mail.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String send_noTest = req.getParameter("send_no");
				if(send_noTest.equals("99")) {
					errorMsgs.put("send_no", new String[] {"請選擇員工編號"});
				}
				Integer send_no = Integer.valueOf(send_noTest);
				
				String rcpt_noTest = req.getParameter("rcpt_no");
				if(rcpt_noTest.equals("99")) {
					errorMsgs.put("rcpt_no", new String[] {"請選擇會員編號"});
				}
				Integer rcpt_no = Integer.valueOf(rcpt_noTest);
				
				String mail_cont = req.getParameter("mail_cont");
				if(mail_cont.trim().isEmpty()) {
					errorMsgs.put("mail_cont", new String[] {"請輸入信件內容"});
				}

				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_mail/update_member_mail_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				Integer mail_stat =  Integer.valueOf(req.getParameter("mail_stat"));
				Integer mail_read_stat =  Integer.valueOf(req.getParameter("mail_read_stat"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String mail_time = sdf.format(new java.util.Date());
				Integer mail_no =  Integer.valueOf(req.getParameter("mail_no"));
				
				Member_mailService member_mailSvc = new Member_mailService();
				Member_mailVO member_mailVO = member_mailSvc.updateMember_mail(mail_no,send_no,rcpt_no,mail_read_stat,mail_stat,mail_cont,mail_time);
				req.setAttribute("member_mailVO", member_mailVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/member_mail/listAllMember_mail.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_mail/update_member_mail_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("read".equals(action)) {
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Member_mailService member_mailSvc = new Member_mailService();
				Member_mailVO member_mailVO = member_mailSvc.getOneMember_mail(Integer.valueOf(req.getParameter("mail_no")));
				member_mailVO.setMail_read_stat(1);
				
				Integer send_no =  member_mailVO.getSend_no();
				Integer rcpt_no =  member_mailVO.getRcpt_no();
				String mail_cont =  member_mailVO.getMail_cont();
				Integer mail_stat =  member_mailVO.getMail_stat();
				Integer mail_read_stat =  member_mailVO.getMail_read_stat();
				String mail_time = member_mailVO.getMail_time();
				Integer mail_no =  member_mailVO.getMail_no();
				Member_mailVO member_mailVO2 = member_mailSvc.updateMember_mail(mail_no,send_no,rcpt_no,mail_read_stat,mail_stat,mail_cont,mail_time);
				
				req.setAttribute("member_mailVO", member_mailVO2);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/member_mail/listOneMember_mail.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_mail/listAllMember_mail.jsp");
				failureView.forward(req, res);
			}
		}
	}
}