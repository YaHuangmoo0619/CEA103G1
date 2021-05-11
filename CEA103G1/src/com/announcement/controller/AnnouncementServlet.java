package com.announcement.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.announcement.model.AnnouncementService;
import com.announcement.model.AnnouncementVO;
import com.member_mail.model.Member_mailService;
import com.member_mail.model.Member_mailVO;


@WebServlet("/announcement/announcement.do")
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
public class AnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getDate_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
   
			try {
				if (req.getParameter("an_skd_date").equals("0")) {
					errorMsgs.add("�п�ܧ�s���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateU = dsf.parse(req.getParameter("an_skd_date"));
				java.sql.Date an_skd_date = new java.sql.Date(dateU.getTime());
							
				AnnouncementService announcementSvc = new AnnouncementService();
				List<AnnouncementVO> announcementVO_date = announcementSvc.getDateEmp_no(an_skd_date);
				if (announcementVO_date.size() == 0) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				req.setAttribute("announcementVO_date", announcementVO_date);
				String url = "/back-end/announcement/listDateAnnouncement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listDateAnnouncement.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/announcement/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("an_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���i�s��");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				if(str.equals("0")) {
					errorMsgs.add("�п�ܤ��i�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer an_no = null;
				try {
					an_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���i�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				AnnouncementVO announcementVO = announcementSvc.getOneAnnouncement(an_no);
				if (announcementVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("announcementVO", announcementVO); // ��Ʈw���X��announcementVO����,�s�Jreq
				String url = "/back-end/announcement/listOneAnnouncement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneAnnouncement.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/announcement/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllAnnouncement.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer an_no = new Integer(req.getParameter("an_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				AnnouncementVO announcementVO = announcementSvc.getOneAnnouncement(an_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("announcementVO", announcementVO);         // ��Ʈw���X��announcementVO����,�s�Jreq
				String url = "/back-end/announcement/update_announcement_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_announcement_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/announcement/listAllAnnouncement.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_announcement_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer an_no = new Integer(req.getParameter("an_no"));
				Integer emp_no = new Integer(req.getParameter("emp_no"));
				if(emp_no == 0) {
					errorMsgs.add("�п�ܵo���");
				}							
				String an_cont = req.getParameter("an_cont");
				if(an_cont == null || an_cont.trim().isEmpty()) {
					errorMsgs.add("�м��g����");
				}
				String dateS = req.getParameter("an_skd_date");
				java.sql.Date an_skd_date = null;
				if(dateS == null || dateS.trim().isEmpty()) {
					errorMsgs.add("�п�ܤ��i���");
				}else {
					an_skd_date = java.sql.Date.valueOf(dateS);
				}
				Part pic = req.getPart("an_pic");
				InputStream in = pic.getInputStream();
				byte[] an_pic = new byte[in.available()];
				in.read(an_pic);
				
				AnnouncementVO announcementVO = new AnnouncementVO();
				announcementVO.setAn_no(an_no);
				announcementVO.setEmp_no(emp_no);
				announcementVO.setAn_cont(an_cont);
				announcementVO.setAn_skd_date(an_skd_date);
				byte[] an_pic_database = new byte[0];
				if(an_pic.length == 0) {
					AnnouncementService announcementSvc = new AnnouncementService();
					AnnouncementVO announcementVOPic = announcementSvc.getOneAnnouncement(an_no);
					an_pic_database = announcementVOPic.getAn_pic();
					announcementVO.setAn_pic(an_pic_database);
				}else {
					announcementVO.setAn_pic(an_pic);
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("announcementVO", announcementVO); // �t����J�榡���~��announcementVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/update_announcement_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				if(an_pic.length == 0) {
					announcementVO = announcementSvc.updateAnnouncement(an_no, emp_no, an_cont, an_skd_date, an_pic_database);
				}else {
					announcementVO = announcementSvc.updateAnnouncement(an_no, emp_no, an_cont, an_skd_date, an_pic);
				}
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("announcementVO", announcementVO); // ��Ʈwupdate���\��,���T����announcementVO����,�s�Jreq
				String url = "/back-end/announcement/listAllAnnouncement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneAnnouncement.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/announcement/update_announcement_input.jsp");
				failureView.forward(req, res);
			}
		}

		
        if ("insert".equals(action)) { // �Ӧ�addAnnouncement.jsp���ШD  
       	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				Integer emp_no = new Integer(req.getParameter("emp_no"));
				if(emp_no == 0) {
					errorMsgs.add("�п�ܵo���");
				}							
				String an_cont = req.getParameter("an_cont");
				if(an_cont == null || an_cont.trim().isEmpty()) {
					errorMsgs.add("�м��g����");
				}
				String dateS = req.getParameter("an_skd_date");
				java.sql.Date an_skd_date = null;
				if(dateS == null || dateS.trim().isEmpty()) {
					errorMsgs.add("�п�ܤ��i���");
				}else {
					an_skd_date = java.sql.Date.valueOf(dateS);
				}
				
				Part pic = req.getPart("an_pic");
				InputStream in = pic.getInputStream();
				byte[] an_pic = new byte[in.available()];
				in.read(an_pic);
				if(an_pic.length == 0) {
					errorMsgs.add("�ФW�ǷӤ�");
				}
				
				AnnouncementVO announcementVO = new AnnouncementVO();
				announcementVO.setEmp_no(emp_no);
				announcementVO.setAn_cont(an_cont);
				announcementVO.setAn_skd_date(an_skd_date);
				announcementVO.setAn_pic(an_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) { // �t����J�榡���~��announcementVO����,�]�s�Jreq
					req.setAttribute("announcementVO", announcementVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/addAnnouncement.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				announcementVO = announcementSvc.addAnnouncement(emp_no, an_cont, an_skd_date, an_pic);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				List<AnnouncementVO> announcementVONew = announcementSvc.getAll();
				Integer an_no = announcementVONew.get(0).getAn_no();
				req.setAttribute("an_no", an_no);
				String url = "/back-end/announcement/listAllAnnouncement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllAnnouncement.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/announcement/addAnnouncement.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllAnnouncement.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer an_no = new Integer(req.getParameter("an_no"));
				
				/***************************2.�}�l�R�����***************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				announcementSvc.deleteAnnouncement(an_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/announcement/listAllAnnouncement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/announcement/listAllAnnouncement.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("read".equals(action) || "readBack".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				AnnouncementService announcementSvc = new AnnouncementService();
				AnnouncementVO announcementVO = announcementSvc.getOneAnnouncement(Integer.valueOf(req.getParameter("an_no")));
				
				if("read".equals(action)){
					req.setAttribute("announcementVO", announcementVO);
					RequestDispatcher successView = req.getRequestDispatcher("/front-end/announcement/listOneAnnouncement.jsp");
					successView.forward(req, res);
				}else {
					req.setAttribute("announcementVO", announcementVO);
					RequestDispatcher successView = req.getRequestDispatcher("/back-end/announcement/listOneAnnouncement.jsp");
					successView.forward(req, res);
				}
				
			}catch(Exception e) {
				if("read".equals(action)){
					errorMsgs.add("�d�ݸ�ƥ���:"+e.getMessage());;
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/announcement/listAllAnnouncement.jsp");
					failureView.forward(req, res);
				}else {
					errorMsgs.add("�d�ݸ�ƥ���:"+e.getMessage());;
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/announcement/listAllAnnouncement.jsp");
					failureView.forward(req, res);
				}
				
			}
		}
	}
}