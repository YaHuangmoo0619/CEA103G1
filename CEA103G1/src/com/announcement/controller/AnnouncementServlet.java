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
		
		if ("getDate_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
   
			try {
				if (req.getParameter("an_skd_date").equals("0")) {
					errorMsgs.add("請選擇更新日期");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateU = dsf.parse(req.getParameter("an_skd_date"));
				java.sql.Date an_skd_date = new java.sql.Date(dateU.getTime());
							
				AnnouncementService announcementSvc = new AnnouncementService();
				List<AnnouncementVO> announcementVO_date = announcementSvc.getDateEmp_no(an_skd_date);
				if (announcementVO_date.size() == 0) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				req.setAttribute("announcementVO_date", announcementVO_date);
				String url = "/back-end/announcement/listDateAnnouncement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listDateAnnouncement.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/announcement/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("an_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入公告編號");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				if(str.equals("0")) {
					errorMsgs.add("請選擇公告編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer an_no = null;
				try {
					an_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("公告編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				AnnouncementVO announcementVO = announcementSvc.getOneAnnouncement(an_no);
				if (announcementVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("announcementVO", announcementVO); // 資料庫取出的announcementVO物件,存入req
				String url = "/back-end/announcement/listOneAnnouncement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAnnouncement.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/announcement/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllAnnouncement.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer an_no = new Integer(req.getParameter("an_no"));
				
				/***************************2.開始查詢資料****************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				AnnouncementVO announcementVO = announcementSvc.getOneAnnouncement(an_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("announcementVO", announcementVO);         // 資料庫取出的announcementVO物件,存入req
				String url = "/back-end/announcement/update_announcement_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_announcement_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/announcement/listAllAnnouncement.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_announcement_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer an_no = new Integer(req.getParameter("an_no"));
				Integer emp_no = new Integer(req.getParameter("emp_no"));
				if(emp_no == 0) {
					errorMsgs.add("請選擇發文者");
				}							
				String an_cont = req.getParameter("an_cont");
				if(an_cont == null || an_cont.trim().isEmpty()) {
					errorMsgs.add("請撰寫內文");
				}
				String dateS = req.getParameter("an_skd_date");
				java.sql.Date an_skd_date = null;
				if(dateS == null || dateS.trim().isEmpty()) {
					errorMsgs.add("請選擇公告日期");
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
					req.setAttribute("announcementVO", announcementVO); // 含有輸入格式錯誤的announcementVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/update_announcement_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				if(an_pic.length == 0) {
					announcementVO = announcementSvc.updateAnnouncement(an_no, emp_no, an_cont, an_skd_date, an_pic_database);
				}else {
					announcementVO = announcementSvc.updateAnnouncement(an_no, emp_no, an_cont, an_skd_date, an_pic);
				}
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("announcementVO", announcementVO); // 資料庫update成功後,正確的的announcementVO物件,存入req
				String url = "/back-end/announcement/listAllAnnouncement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAnnouncement.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/announcement/update_announcement_input.jsp");
				failureView.forward(req, res);
			}
		}

		
        if ("insert".equals(action)) { // 來自addAnnouncement.jsp的請求  
       	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer emp_no = new Integer(req.getParameter("emp_no"));
				if(emp_no == 0) {
					errorMsgs.add("請選擇發文者");
				}							
				String an_cont = req.getParameter("an_cont");
				if(an_cont == null || an_cont.trim().isEmpty()) {
					errorMsgs.add("請撰寫內文");
				}
				String dateS = req.getParameter("an_skd_date");
				java.sql.Date an_skd_date = null;
				if(dateS == null || dateS.trim().isEmpty()) {
					errorMsgs.add("請選擇公告日期");
				}else {
					an_skd_date = java.sql.Date.valueOf(dateS);
				}
				
				Part pic = req.getPart("an_pic");
				InputStream in = pic.getInputStream();
				byte[] an_pic = new byte[in.available()];
				in.read(an_pic);
				if(an_pic.length == 0) {
					errorMsgs.add("請上傳照片");
				}
				
				AnnouncementVO announcementVO = new AnnouncementVO();
				announcementVO.setEmp_no(emp_no);
				announcementVO.setAn_cont(an_cont);
				announcementVO.setAn_skd_date(an_skd_date);
				announcementVO.setAn_pic(an_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) { // 含有輸入格式錯誤的announcementVO物件,也存入req
					req.setAttribute("announcementVO", announcementVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/addAnnouncement.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				announcementVO = announcementSvc.addAnnouncement(emp_no, an_cont, an_skd_date, an_pic);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				List<AnnouncementVO> announcementVONew = announcementSvc.getAll();
				Integer an_no = announcementVONew.get(0).getAn_no();
				req.setAttribute("an_no", an_no);
				String url = "/back-end/announcement/listAllAnnouncement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAnnouncement.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/announcement/addAnnouncement.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllAnnouncement.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer an_no = new Integer(req.getParameter("an_no"));
				
				/***************************2.開始刪除資料***************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				announcementSvc.deleteAnnouncement(an_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/announcement/listAllAnnouncement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
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
					errorMsgs.add("查看資料失敗:"+e.getMessage());;
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/announcement/listAllAnnouncement.jsp");
					failureView.forward(req, res);
				}else {
					errorMsgs.add("查看資料失敗:"+e.getMessage());;
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/announcement/listAllAnnouncement.jsp");
					failureView.forward(req, res);
				}
				
			}
		}
	}
}