package com.service_mail.controller;

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

import com.service_mail.model.Service_mailService;
import com.service_mail.model.Service_mailVO;
import com.service_mail_picture.model.Service_mail_pictureDAO;
import com.service_mail_picture.model.Service_mail_pictureVO;

@WebServlet("/service_mail/service_mail.do")
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
public class Service_mailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String saveDirectory = "/images/service_mail_picture";
	
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
				if(checkCount == 8 || map.get("mail_cont")[0].isEmpty()) {
					errorMsgs.put("notFound", new String[] {"�п�ܩο�J�d������r"});
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/service_mail/listAllService_mail.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/service_mail/listAllService_mail.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)){
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				String emp_noTest = req.getParameter("emp_no");
				if(emp_noTest.equals("99")) {
					errorMsgs.put("emp_no", new String[] {"�п�ܭ��u�s��"});
				}
				Integer emp_no = Integer.valueOf(emp_noTest);
				
				String mbr_noTest = req.getParameter("mbr_no");
				if(mbr_noTest.equals("99")) {
					errorMsgs.put("mbr_no", new String[] {"�п�ܷ|���s��"});
				}
				Integer mbr_no = Integer.valueOf(mbr_noTest);
				
				String mail_cont = req.getParameter("mail_cont");
				if(mail_cont.trim().isEmpty()) {
					errorMsgs.put("mail_cont", new String[] {"�п�J�H�󤺮e"});
				}
				
				//�Ӥ��B�z
				String realPath = getServletContext().getRealPath(saveDirectory);
				File fsaveDirectory = new File(realPath);
				if(!fsaveDirectory.exists()) {
					fsaveDirectory.mkdirs();
				}
				int count = 1;
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/service_mail/addService_mail.jsp");
					failureView.forward(req, res);
					return;
				}
				Collection<Part> parts = req.getParts();
				if(parts.size() != 0) {
					for(Part part : parts) {
						String fileType = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
						
						File f = new File(fsaveDirectory, "service_mail_picture"+count+fileType);
						part.write(f.toString());
						count++;
						String mail_pic = req.getContextPath()+"/images/service_mail_picture/service_mail_picture"+count+fileType;
//						Service_mail_pictureDAO service_mail_pictureDAO = new Service_mail_pictureDAO();
//						Service_mail_pictureVO service_mail_pictureVO = new Service_mail_pictureVO(80004,req.getContextPath()+"/images/service_mail_picture/service_mail_picture"+count+fileType);
//						service_mail_pictureDAO.insert(service_mail_pictureVO);
					}
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
		
		if("getOne_For_Update".equals(action)) {
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Service_mailVO service_mailVO = new Service_mailService().getOneService_mail(Integer.valueOf(req.getParameter("mail_no")));
				req.setAttribute("service_mailVO", service_mailVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/service_mail/update_service_mail_input.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/service_mail/listAllService_mail.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String emp_noTest = req.getParameter("emp_no");
				if(emp_noTest.equals("99")) {
					errorMsgs.put("emp_no", new String[] {"�п�ܭ��u�s��"});
				}
				Integer emp_no = Integer.valueOf(emp_noTest);
				
				String mbr_noTest = req.getParameter("mbr_no");
				if(mbr_noTest.equals("99")) {
					errorMsgs.put("mbr_no", new String[] {"�п�ܷ|���s��"});
				}
				Integer mbr_no = Integer.valueOf(mbr_noTest);
				
				String mail_cont = req.getParameter("mail_cont");
				if(mail_cont.trim().isEmpty()) {
					errorMsgs.put("mail_cont", new String[] {"�п�J�H�󤺮e"});
				}

				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/service_mail/update_service_mail_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				Integer mail_stat =  Integer.valueOf(req.getParameter("mail_stat"));
				Integer mail_read_stat =  Integer.valueOf(req.getParameter("mail_read_stat"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String mail_time = sdf.format(new java.util.Date());
				Integer mail_no =  Integer.valueOf(req.getParameter("mail_no"));
				
				Service_mailService service_mailSvc = new Service_mailService();
				Service_mailVO service_mailVO = service_mailSvc.updateService_mail(mail_no,emp_no,mbr_no,mail_cont,mail_stat,mail_read_stat,mail_time);
				req.setAttribute("service_mailVO", service_mailVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/service_mail/listAllService_mail.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/service_mail/update_service_mail_input.jsp");
				failureView.forward(req, res);
			}
		}
		if("read".equals(action)) {
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Service_mailService service_mailSvc = new Service_mailService();
				Service_mailVO service_mailVO = service_mailSvc.getOneService_mail(Integer.valueOf(req.getParameter("mail_no")));
				service_mailVO.setMail_read_stat(1);
				
				Integer emp_no =  service_mailVO.getEmp_no();
				Integer mbr_no =  service_mailVO.getMbr_no();
				String mail_cont =  service_mailVO.getMail_cont();
				Integer mail_stat =  service_mailVO.getMail_stat();
				Integer mail_read_stat =  service_mailVO.getMail_read_stat();
				String mail_time = service_mailVO.getMail_time();
				Integer mail_no =  service_mailVO.getMail_no();
				Service_mailVO service_mailVO2 = service_mailSvc.updateService_mail(mail_no,emp_no,mbr_no,mail_cont,mail_stat,mail_read_stat,mail_time);
				
				req.setAttribute("service_mailVO", service_mailVO2);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/service_mail/listOneService_mail.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/service_mail/listAllService_mail.jsp");
				failureView.forward(req, res);
			}
		}
	}
}