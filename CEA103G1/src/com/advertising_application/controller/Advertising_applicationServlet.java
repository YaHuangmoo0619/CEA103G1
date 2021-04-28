//package com.advertising_application.controller;
//
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.advertising_application.model.Advertising_applicationService;
//import com.advertising_application.model.Advertising_applicationVO;
//
//
//@WebServlet("/advertising_application/advertising_application.do")
//@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
//public class Advertising_applicationServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	public void doGet(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//		doPost(req, res);
//	}
//
//	public void doPost(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//		
//		
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("ad_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入廣告編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/advertising_application/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer ad_no = null;
//				try {
//					ad_no = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("廣告編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/advertising_application/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				Advertising_applicationService advertising_applicationSvc = new Advertising_applicationService();
//				Advertising_applicationVO advertising_applicationVO = advertising_applicationSvc.getOneAdvertising_application(ad_no);
//				if (advertising_applicationVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/advertising_application/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("Advertising_applicationVO", advertising_applicationVO); // 資料庫取出的member_rankVO物件,存入req
//				String url = "/back-end/advertising_application/listOneAdvertising_application.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAdvertising_application.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/advertising_application/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("getOne_For_Update".equals(action)) { // 來自listAlladvertising_application.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				Integer ad_no = new Integer(req.getParameter("ad_no"));
//				
//				/***************************2.開始查詢資料****************************************/
//				Advertising_applicationService advertising_applicationSvc = new Advertising_applicationService();
//				Advertising_applicationVO advertising_applicationVO = advertising_applicationSvc.getOneAdvertising_application(ad_no);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("advertising_applicationVO", advertising_applicationVO);         // 資料庫取出的advertising_applicationVO物件,存入req
//				String url = "/back-end/advertising_application/update_advertising_application_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_advertising_application_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/advertising_application/listAllAdvertising_application.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("update".equals(action)) { // 來自update_member_rank_input.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer ad_no = new Integer(req.getParameter("ad_no").trim());
//				
//				Integer cso_no = new Integer(req.getParameter("cso_no"));
//				if(cso_no == 0) {
//					errorMsgs.add("請選擇營主編號");
//
//				String ad_head = req.getParameter("ad_head");
//				String ad_headReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
//				if (ad_head == null || ad_head.trim().length() == 0) {
//					errorMsgs.add("廣告標題: 請勿空白");
//				} else if(!ad_head.trim().matches(ad_headReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("廣告標題: 只能是中、英文字母、數字和_ , 且長度必需在2到100之間");
//	            }
//				
//				String an_cont = req.getParameter("an_cont");
//				if(an_cont.isEmpty()) {
//					errorMsgs.add("請撰寫內文");
//				}
//				
//				Integer pt_rwd_rto = null;
//				try {
//					pt_rwd_rto = new Integer(req.getParameter("pt_rwd_rto").trim());
//				} catch (NumberFormatException e) {
//					pt_rwd_rto = 0;
//					errorMsgs.add("點數回饋比例請填數字.");
//				}
//
//
//				Advertising_applicationVO Advertising_applicationVO = new Advertising_applicationVO();
//				Advertising_applicationVO.setRank_no(ad_no);
//				Advertising_applicationVO.setRank_name(rank_name);
//				Advertising_applicationVO.setPt_rwd_rto(pt_rwd_rto);
//
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("Advertising_applicationVO", Advertising_applicationVO); // 含有輸入格式錯誤的member_rankVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/advertising_application/update_advertising_application_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				Advertising_applicationService Advertising_applicationSvc = new Advertising_applicationService();
//				Advertising_applicationVO = Advertising_applicationSvc.updateAdvertising_application(ad_no, rank_name, pt_rwd_rto);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("Advertising_applicationVO", Advertising_applicationVO); // 資料庫update成功後,正確的的member_rankVO物件,存入req
//				String url = "/back-end/advertising_application/listOneMember_rank.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember_rank.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/advertising_application/update_advertising_application_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//        if ("insert".equals(action)) { // 來自addMember_rank.jsp的請求  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String rank_name = req.getParameter("rank_name");
//				String rank_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (rank_name == null || rank_name.trim().length() == 0) {
//					errorMsgs.add("會員等級名稱: 請勿空白");
//				} else if(!rank_name.trim().matches(rank_nameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("會員等級名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
//				Integer pt_rwd_rto = null;
//				try {
//					pt_rwd_rto = new Integer(req.getParameter("pt_rwd_rto").trim());
//				} catch (NumberFormatException e) {
//					pt_rwd_rto = 0;
//					errorMsgs.add("點數回饋比例請填數字.");
//				}
//
//				Advertising_applicationVO Advertising_applicationVO = new Advertising_applicationVO();
//				Advertising_applicationVO.setRank_name(rank_name);
//				Advertising_applicationVO.setPt_rwd_rto(pt_rwd_rto);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("Advertising_applicationVO", Advertising_applicationVO); // 含有輸入格式錯誤的member_rankVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/advertising_application/addMember_rank.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				Advertising_applicationService Advertising_applicationSvc = new Advertising_applicationService();
//				Advertising_applicationVO = Advertising_applicationSvc.addMember_rank(rank_name, pt_rwd_rto);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/back-end/advertising_application/listAllAdvertising_application.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember_rank.jsp
//				successView.forward(req, res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/advertising_application/addMember_rank.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("delete".equals(action)) { // 來自listAllMember_rank.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				Integer ad_no = new Integer(req.getParameter("ad_no"));
//				
//				/***************************2.開始刪除資料***************************************/
//				Advertising_applicationService Advertising_applicationSvc = new Advertising_applicationService();
//				Advertising_applicationSvc.deleteAdvertising_application(ad_no);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/back-end/advertising_application/listAllAdvertising_application.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/advertising_application/listAllAdvertising_application.jsp");
//				failureView.forward(req, res);
//			}
//		}
//	}
//}
