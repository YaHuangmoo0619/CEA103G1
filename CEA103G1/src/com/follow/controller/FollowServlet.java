package com.follow.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.follow.model.FollowService;
import com.follow.model.FollowVO;


@WebServlet("/follow/follow.do")

public class FollowServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	
		if ("getbyflwed_no".equals(action)) { // 來自select_page.jsp的請求，列出這個人有哪些粉絲

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("flwed_mbr_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				

				Integer flwed_mbr_no = null;
				try {
					flwed_mbr_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FollowService followSvc = new FollowService();
				List<FollowVO> followVO = followSvc.findbyflwed(flwed_mbr_no);
				if (followVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("followVO", followVO); // 資料庫取出的followVO物件,存入req
				String url = "/back-end/follow/listOneByFlwed.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneByFlwed.jsp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/follow/select_page.jsp");
				failureView.forward(req, res);
			}
		}		
	
	
		
		
		if ("getbyflw_no".equals(action)) { // 來自select_page.jsp的請求，列出這個人是哪些人的粉絲

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("flw_mbr_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				

				Integer flw_mbr_no = null;
				try {
					flw_mbr_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				System.out.println(flw_mbr_no);			
				/***************************2.開始查詢資料*****************************************/
				FollowService followSvc = new FollowService();
				List<FollowVO> followVO = followSvc.findbyflw(flw_mbr_no);
				if (followVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("followVO", followVO); // 資料庫取出的followVO物件,存入req
				String url = "/back-end/follow/listOneByFlw.jsp";
				System.out.println("hello");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneByFlw.jsp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/follow/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	
	}
	

	
}
