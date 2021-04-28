//package com.member_report.controller;
//
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.member_report.model.Member_reportService;
//import com.member_report.model.Member_reportVO;
//
//
//@WebServlet("/member_report/member_report.do")
//public class Member_reportServlet extends HttpServlet {
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
//				String str = req.getParameter("rank_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入會員等級編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member_report/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer rank_no = null;
//				try {
//					rank_no = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("會員等級編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member_report/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				Member_reportService member_reportSvc = new Member_reportService();
//				Member_reportVO member_reportVO = member_reportSvc.getOneMember_report(rank_no);
//				if (member_reportVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member_report/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("member_reportVO", member_reportVO); // 資料庫取出的member_reportVO物件,存入req
//				String url = "/back-end/member_report/listOneMember_report.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember_report.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/member_report/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllmember_report.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				Integer rank_no = new Integer(req.getParameter("rank_no"));
//				
//				/***************************2.開始查詢資料****************************************/
//				Member_reportService member_reportSvc = new Member_reportService();
//				Member_reportVO member_reportVO = member_reportSvc.getOneMember_report(rank_no);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("member_reportVO", member_reportVO);         // 資料庫取出的member_reportVO物件,存入req
//				String url = "/back-end/member_report/update_member_report_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_report_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/member_report/listAllMember_report.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("update".equals(action)) { // 來自update_member_report_input.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer rank_no = new Integer(req.getParameter("rank_no").trim());
//				
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
//
//				Member_reportVO member_reportVO = new Member_reportVO();
//				member_reportVO.setRank_no(rank_no);
//				member_reportVO.setRank_name(rank_name);
//				member_reportVO.setPt_rwd_rto(pt_rwd_rto);
//
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("member_reportVO", member_reportVO); // 含有輸入格式錯誤的member_reportVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member_report/update_member_report_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				Member_reportService member_reportSvc = new Member_reportService();
//				member_reportVO = member_reportSvc.updateMember_report(rank_no, rank_name, pt_rwd_rto);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("member_reportVO", member_reportVO); // 資料庫update成功後,正確的的member_reportVO物件,存入req
//				String url = "/back-end/member_report/listOneMember_report.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember_report.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/member_report/update_member_report_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//        if ("insert".equals(action)) { // 來自addMember_report.jsp的請求  
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
//				Member_reportVO member_reportVO = new Member_reportVO();
//				member_reportVO.setRank_name(rank_name);
//				member_reportVO.setPt_rwd_rto(pt_rwd_rto);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("member_reportVO", member_reportVO); // 含有輸入格式錯誤的member_reportVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member_report/addMember_report.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				Member_reportService member_reportSvc = new Member_reportService();
//				member_reportVO = member_reportSvc.addMember_report(rank_name, pt_rwd_rto);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/back-end/member_report/listAllMember_report.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember_report.jsp
//				successView.forward(req, res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/member_report/addMember_report.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("delete".equals(action)) { // 來自listAllMember_report.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				Integer rank_no = new Integer(req.getParameter("rank_no"));
//				
//				/***************************2.開始刪除資料***************************************/
//				Member_reportService member_reportSvc = new Member_reportService();
//				member_reportSvc.deleteMember_report(rank_no);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/back-end/member_report/listAllMember_report.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/member_report/listAllMember_report.jsp");
//				failureView.forward(req, res);
//			}
//		}
//	}
//}
