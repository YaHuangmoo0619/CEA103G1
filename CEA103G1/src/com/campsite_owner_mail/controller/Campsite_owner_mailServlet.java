package com.campsite_owner_mail.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.campsite_owner_mail.model.Campsite_owner_mailService;
import com.campsite_owner_mail.model.Campsite_owner_mailVO;
import com.campsite_owner_mail_picture.model.Campsite_owner_mail_pictureVO;
import com.member_mail.model.Member_mailService;
import com.member_mail.model.Member_mailVO;
import com.member_mail_picture.model.Member_mail_pictureVO;

@WebServlet("/campsite_owner_mail/campsite_owner_mail.do")
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
public class Campsite_owner_mailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String saveDirectory = "/images/member_mail_picture";
	int count = 1;

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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite_owner_mail/listAllCampsite_owner_mail.jsp");
					failureView.forward(req, res);
					return;
				}
				if(checkCount == 8) {
					errorMsgs.put("notFound", new String[] {"請選擇或輸入查詢關鍵字"});
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite_owner_mail/listAllCampsite_owner_mail.jsp");
					failureView.forward(req, res);
					return;
				}
			
				Campsite_owner_mailService campsite_owner_mailSvc = new Campsite_owner_mailService();
				Set<Campsite_owner_mailVO> campsite_owner_mailVOSet = campsite_owner_mailSvc.getWhereCondition(map);
				req.setAttribute("campsite_owner_mailVOSet", campsite_owner_mailVOSet);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/campsite_owner_mail/listWhereCampsite_owner_mail.jsp");
				successView.forward(req, res);
		
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite_owner_mail/listAllCampsite_owner_mail.jsp");
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
//					System.out.print('a');
				}
				Integer send_no = Integer.valueOf(send_noTest);
				
				String rcpt_noTest = req.getParameter("rcpt_no");
				if(rcpt_noTest.equals("99")) {
					errorMsgs.put("rcpt_no", new String[] {"請選擇收件人編號"});
//					System.out.print('b');
				}
				Integer rcpt_no = Integer.valueOf(rcpt_noTest);
				
				String mail_cont = req.getParameter("mail_cont");
				if(mail_cont.trim().isEmpty()) {
					errorMsgs.put("mail_cont", new String[] {"請輸入信件內容"});
//					System.out.print('c');
				}

				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite_owner_mail/addCampsite_owner_mail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Set<Campsite_owner_mail_pictureVO> set = new LinkedHashSet<Campsite_owner_mail_pictureVO>();
				String realPath = getServletContext().getRealPath(saveDirectory);
				File fsaveDirectory = new File(realPath);
				if(!fsaveDirectory.exists()) {
					fsaveDirectory.mkdirs();
				}
				
				Collection<Part> parts = req.getParts();
//				System.out.println(parts.size());
				if(parts.size() >= 7) {
					for(Part part : parts) {
//						System.out.println(part.getHeader("content-disposition"));
//						System.out.println(part.getSubmittedFileName());
						if(part.getSubmittedFileName()!=null && !part.getSubmittedFileName().isEmpty()) {
							String fileType = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
							
							File f = new File(fsaveDirectory, "campsite_owner_mail_picture"+count+fileType);
							part.write(f.toString());
							
							String mail_pic = req.getContextPath()+"/images/campsite_owner_mail_picture/campsite_owner_mail_picture"+count+fileType;
							Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO = new Campsite_owner_mail_pictureVO();
							campsite_owner_mail_pictureVO.setMail_pic(mail_pic);
							set.add(campsite_owner_mail_pictureVO);
							count++;
						}
					}
				}
				
				Integer mail_stat = 1;
//				Integer mail_stat =  Integer.valueOf(req.getParameter("mail_stat"));
				Integer mail_read_stat =  Integer.valueOf(req.getParameter("mail_read_stat"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String mail_time = sdf.format(new java.util.Date());
				
				Campsite_owner_mailService campsite_owner_mailSvc = new Campsite_owner_mailService();
				if(set.size() == 0) {
				Campsite_owner_mailVO campsite_owner_mailVO = campsite_owner_mailSvc.addCampsite_owner_mail(send_no,rcpt_no,mail_read_stat,mail_stat,mail_cont,mail_time);
				req.setAttribute("campsite_owner_mailVO", campsite_owner_mailVO);
				}else {
					campsite_owner_mailSvc.insertWithPic(send_no,rcpt_no,mail_read_stat,mail_stat,mail_cont,mail_time, set);
				}
				
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/campsite_owner_mail/listAllCampsite_owner_mail.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite_owner_mail/addCampsite_owner_mail.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/bafront-endampsite_owner_mail/listAllCampsite_owner_mail_mail.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite_owner_mail/update_campsite_owner_mail_input.jsp");
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
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/campsite_owner_mail/listAllCampsite_owner_mail.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite_owner_mail/update_campsite_owner_mail_input.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("read".equals(action)) {
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Campsite_owner_mailService campsite_owner_mailSvc = new Campsite_owner_mailService();
				Campsite_owner_mailVO campsite_owner_mailVO = campsite_owner_mailSvc.getOneCampsite_owner_mail(Integer.valueOf(req.getParameter("mail_no")));
				campsite_owner_mailVO.setMail_read_stat(1);
				
				Integer send_no =  campsite_owner_mailVO.getSend_no();
				Integer rcpt_no =  campsite_owner_mailVO.getRcpt_no();
				String mail_cont =  campsite_owner_mailVO.getMail_cont();
				Integer mail_stat =  campsite_owner_mailVO.getMail_stat();
				Integer mail_read_stat =  campsite_owner_mailVO.getMail_read_stat();
				String mail_time = campsite_owner_mailVO.getMail_time();
				Integer mail_no =  campsite_owner_mailVO.getMail_no();
				Campsite_owner_mailVO campsite_owner_mailVO2 = campsite_owner_mailSvc.updateCampsite_owner_mail(mail_no,send_no,rcpt_no,mail_read_stat,mail_stat,mail_cont,mail_time);
				
				req.setAttribute("campsite_owner_mailVO", campsite_owner_mailVO2);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/campsite_owner_mail/listOneCampsite_owner_mail.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite_owner_mail/listAllCampsite_owner_mail.jsp");
				failureView.forward(req, res);
			}
		}
	}
}