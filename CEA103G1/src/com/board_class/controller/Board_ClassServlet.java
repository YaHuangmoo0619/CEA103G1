package com.board_class.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.board_class.model.Board_ClassService;
import com.board_class.model.Board_ClassVO;

import redis.clients.jedis.Jedis;


@WebServlet("/board_class/board_class.do")
public class Board_ClassServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllBoard_Class.jsp的請求
			System.out.println(req.getParameter("bd_cl_no"));
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				System.out.println("hi0");
				Integer bd_cl_no = new Integer(req.getParameter("bd_cl_no"));
				System.out.println("hi1");
				
				/***************************2.開始查詢資料****************************************/
				Board_ClassService board_classSvc = new Board_ClassService();
				System.out.println("hi2");
				Board_ClassVO board_classVO = board_classSvc.getOneBoard_Class(bd_cl_no);
				System.out.println("hi3");
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("board_classVO", board_classVO);         // 資料庫取出的board_classVO物件,存入req
				String url = "/back-end//board_class/update_board_class_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_board_class_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end//board_class/listAllBoard_class.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_board_class_input.jsp的請求
			System.out.println(req.getParameter("bd_cl_no"));
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/		
				Integer bd_cl_no = new Integer(req.getParameter("bd_cl_no").trim());
				
				String bd_name = req.getParameter("bd_name");
				String bd_nameReg = "^.{2,15}$";
				if (bd_name == null || bd_name.trim().length() == 0) {
					errorMsgs.add("看板名稱: 請勿空白");
				} 
				else if(!bd_name.trim().matches(bd_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("看板名稱: 長度必須在2到15之間");
	            }
				
				Integer bd_stat = null;
				try {
					bd_stat = new Integer(req.getParameter("bd_stat").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("文章狀態請填數字0 or 1.");
				} 
								
				Board_ClassVO board_classVO = new Board_ClassVO();
				board_classVO.setBd_cl_no(bd_cl_no);
				board_classVO.setBd_name(bd_name);
				board_classVO.setBd_stat(bd_stat);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("board_classVO",board_classVO); // 含有輸入格式錯誤的board_classVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end//board_class/update_board_class_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Board_ClassService board_classSvc = new Board_ClassService();
				board_classVO = board_classSvc.updateBoard_Class(bd_cl_no,bd_name,bd_stat);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("board_classVO", board_classVO); // 資料庫update成功後,正確的的board_classVO物件,存入req
				String url = "/back-end/board_class/listOneBoard_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneBoard_class.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/board_class/update_board_class_input.jsp");
				failureView.forward(req, res);
			}
		}

		
//        if ("insert".equals(action)) { // 來自addArticle.jsp的請求  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				Integer bd_cl_no = null;
//				try {
//					bd_cl_no = new Integer(req.getParameter("bd_cl_no").trim());
//				} catch (NumberFormatException e) {
//					bd_cl_no = 0;
//					errorMsgs.add("看板編號請填數字.");
//				}
//				
//				Integer mbr_no = null;
//				try {
//					mbr_no = new Integer(req.getParameter("mbr_no").trim());
//				} catch (NumberFormatException e) {
//					mbr_no = 0;
//					errorMsgs.add("會員編號請填數字.");
//				}
//				
//				String art_title = req.getParameter("art_title");
//				String art_titleReg = "^.{2,30}$";
//				if (art_title == null || art_title.trim().length() == 0) {
//					errorMsgs.add("文章標題: 請勿空白");
//				} 
//				else if(!art_title.trim().matches(art_titleReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("文章標題: 長度必須在2到30之間");
//	            }
//				
//	
//				Timestamp art_rel_time = new Timestamp(System.currentTimeMillis());
//				
//				String art_cont = req.getParameter("art_cont");
//				String art_contReg = "^.{10,10000}$";
//				
//				if (art_cont == null || art_cont.trim().length() == 0) {
//					errorMsgs.add("文章內容: 請勿空白");
//				} 
//				else if(!art_cont.trim().matches(art_contReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("文章內容: 必須在10到10000個字之間");
//	            }
//				
//				Integer likes = null;
//				try {
//					likes = new Integer(req.getParameter("likes").trim());
//				} catch (NumberFormatException e) {
//					likes = 0;
//					errorMsgs.add("讚數請填數字.");
//				}
//				
//				Integer art_stat = null;
//				try {
//					art_stat = new Integer(req.getParameter("art_stat").trim());
//				} catch (NumberFormatException e) {
//					art_stat = 0;
//					errorMsgs.add("文章狀態請填數字0 or 1.");
//				}
//				
//
//				Board_ClassVO board_classVO = new Board_ClassVO();
//				board_classVO.setBd_cl_no(bd_cl_no);
//				board_classVO.setMbr_no(mbr_no);
//				board_classVO.setArt_rel_time(art_rel_time);
//				board_classVO.setArt_title(art_title);
//				board_classVO.setArt_cont(art_cont);
//				board_classVO.setLikes(likes);
//				board_classVO.setArt_stat(art_stat);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("board_classVO", board_classVO); // 含有輸入格式錯誤的board_classVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/board_class/addArticle.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				Board_ClassService board_classSvc = new Board_ClassService();
//				board_classVO = board_classSvc.addArticle(bd_cl_no, mbr_no,art_rel_time,art_title,art_cont, likes,art_stat);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/board_class/listAllArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllArticle.jsp
//				successView.forward(req, res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/board_class/addArticle.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
		if ("delete".equals(action)) { // 來自listAllBoard_Class.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer bd_cl_no = new Integer(req.getParameter("bd_cl_no"));
				
				/***************************2.開始刪除資料***************************************/
				Board_ClassService board_classSvc = new Board_ClassService();
				board_classSvc.deleteBoard_Class(bd_cl_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/board_class/listAllBoard_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/board_class/listAllBoard_class.jsp");
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
				String str = req.getParameter("bd_cl_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入看板編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/board_class/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer bd_cl_no = null;
				try {
					bd_cl_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("看板編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/board_class/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Board_ClassService board_classSvc = new Board_ClassService();
				Board_ClassVO board_classVO = board_classSvc.getOneBoard_Class(bd_cl_no);
				if (board_classVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/board_class/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("board_classVO", board_classVO); // 資料庫取出的articleVO物件,存入req
				String url = "/back-end/board_class/listOneBoard_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneArticle.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("subscribe".equals(action)) { // 來自update_board_class_input.jsp的請求

				/***************************1.接收請求參數 **********************/		
				String bd_cl_no = req.getParameter("bd_cl_no"); //要訂閱的看板
				Integer mbr_no   = new Integer(req.getParameter("mbr_no").trim());   //要訂閱的會員
								
				/***************************2.開始修改資料*****************************************/
				Jedis jedis = new Jedis("localhost", 6379);
				jedis.auth("123456");
				jedis.select(6);
				jedis.sadd("board:"+mbr_no+":subscribe", bd_cl_no);
				jedis.close();
			}
		
		
		
	}
}
