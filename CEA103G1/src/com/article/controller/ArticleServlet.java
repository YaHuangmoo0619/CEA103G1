package com.article.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.article.model.ArticleDAO;
import com.article.model.ArticleService;
import com.article.model.ArticleVO;


@WebServlet("/article/article.do")
public class ArticleServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("art_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer art_no = null;
				try {
					art_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("文章編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(art_no);
				if (articleVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req
				String url = "/back-end/article/listOneArticle.jsp";
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
		
		
		
		
		
		
		if ("getOneArticle_ByBoard_Clss_For_Display".equals(action)) { // 來自front-end listAllArticle.jsp的請求  

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("bd_cl_no"); //不用錯誤驗證 因為是直接跳轉 不是由使用者輸入
				Integer bd_cl_no = new Integer(str);
				System.out.println(bd_cl_no);
				
				/***************************2.開始查詢資料*****************************************/
				ArticleService articleSvc = new ArticleService();
				List<ArticleVO> articleVO = articleSvc.getByBoard_Class_Front(bd_cl_no);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req
				String url = "/front-end/article/listOneBoard_ClassArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBoard_ClassArticle.jsp
				successView.forward(req, res);

		}
		
		
		
		if ("getOneArticle_ByBoard_Clss_For_Display".equals(action)) { // 來自back-end listAllArticle.jsp的請求  

			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("bd_cl_no"); //不用錯誤驗證 因為是直接跳轉 不是由使用者輸入
			Integer bd_cl_no = new Integer(str);
			System.out.println(bd_cl_no);
			
			/***************************2.開始查詢資料*****************************************/
			ArticleService articleSvc = new ArticleService();
			List<ArticleVO> articleVO = articleSvc.getByBoard_Class_Back(bd_cl_no);
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req
			String url = "/back-end/article/listOneBoard_ClassArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBoard_ClassArticle.jsp
			successView.forward(req, res);

	}
		
		
		
		
		
		
		
//		if ("getOneArticle_ByBoard_Clss_For_Display".equals(action)) { // 來自back-end listAllArticle.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("bd_cl_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入看板編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/article/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//
//				Integer bd_cl_no = null;
//				try {
//					bd_cl_no = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("看板編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/article/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				ArticleService articleSvc = new ArticleService();
//				List<ArticleVO> articleVO = articleSvc.getByBoard_Class(bd_cl_no);
//				if (articleVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/article/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("articleVO", articleVO); // 資料庫取出的article_replyVO物件,存入req
//				String url = "/back-end/article/listOneBoard_ClassArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBoard_ClassArticle.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/article/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}	
		
		
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllArticle.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer art_no = new Integer(req.getParameter("art_no"));
				
				/***************************2.開始查詢資料****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(art_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("articleVO", articleVO);         // 資料庫取出的articleVO物件,存入req
				String url = "/front-end/article/update_article_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_article_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_article_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			
				Integer art_no = new Integer(req.getParameter("art_no").trim());
				System.out.println("art_no:"+art_no);

				
				


				Integer bd_cl_no = new Integer(req.getParameter("bd_cl_no").trim());
					System.out.println("bd_cl_no"+bd_cl_no);

				
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				System.out.println("mbr_no:"+mbr_no);
				
				
				Timestamp art_rel_time = new Timestamp(System.currentTimeMillis());
				art_rel_time = Timestamp.valueOf(req.getParameter("art_rel_time"));
				System.out.println("art_rel_time:"+art_rel_time);

				
				String art_title = req.getParameter("art_title");
				String art_titleReg = "^.{2,30}$";
				if (art_title == null || art_title.trim().length() == 0) {
					errorMsgs.add("文章標題: 請勿空白");
				} 
				else if(!art_title.trim().matches(art_titleReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("文章標題: 長度必須在2到30之間");
	            }
				System.out.println(art_title);
				
				String art_cont = req.getParameter("art_cont");
				
				if (art_cont == null || art_cont.trim().length() == 0) {
					errorMsgs.add("文章內容: 請勿空白");
				} 

				System.out.println(art_cont);
				
				Integer likes = null;
				try {
					likes = new Integer(req.getParameter("likes").trim());
				} catch (NumberFormatException e) {
					likes = 0;
					errorMsgs.add("讚數請填數字.");
				} 
				System.out.println(likes);
				
				Integer art_stat = null;
				try {
					art_stat = new Integer(req.getParameter("art_stat").trim());
				} catch (NumberFormatException e) {
					art_stat = 0;
					errorMsgs.add("文章狀態請填數字0 or 1.");
				} 
				
				System.out.println(art_stat);
				
				Integer replies = new Integer(req.getParameter("replies").trim());
				System.out.println(replies);
				
				
				ArticleVO articleVO = new ArticleVO();
				articleVO.setArt_no(art_no);
				articleVO.setBd_cl_no(bd_cl_no);
				articleVO.setMbr_no(mbr_no);
				articleVO.setArt_rel_time(art_rel_time);
				articleVO.setArt_title(art_title);
				articleVO.setArt_cont(art_cont);
				articleVO.setLikes(likes);
				articleVO.setArt_stat(art_stat);
				articleVO.setReplies(replies);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO",articleVO); // 含有輸入格式錯誤的articleVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/update_article_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.updateArticle(art_no,bd_cl_no,mbr_no,art_rel_time,art_title,art_cont,likes,art_stat,replies);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO); // 資料庫update成功後,正確的的articleVO物件,存入req
				String url = "/front-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneArticle.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/update_article_input.jsp");
				failureView.forward(req, res);
			}
		}

		
		
		if ("plus_like".equals(action)) { // 來自liesOneArticle.jsp的請求 幫這篇文章讚數+1

				/***************************1.接收請求參數**********************/		
				Integer art_no = new Integer(req.getParameter("art_no").trim());					
				ArticleVO articleVO = new ArticleVO();
				articleVO.setArt_no(art_no);	
				/***************************2.開始修改資料*****************************************/
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.plus_like(art_no);

		}
		
		
		
		if ("minus_like".equals(action)) { // 來自liesOneArticle.jsp的請求 幫這篇文章讚數+1		
			    
			    /***************************1.接收請求參數**********************/
				Integer art_no = new Integer(req.getParameter("art_no").trim());						
				ArticleVO articleVO = new ArticleVO();
				articleVO.setArt_no(art_no);
	            /***************************2.開始修改資料*****************************************/
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.plus_like(art_no);

		}
		
		
		
		
		
        if ("insert".equals(action)) { // 來自前端addArticle.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer bd_cl_no = null;
				try {
					bd_cl_no = new Integer(req.getParameter("bd_cl_no").trim());
				} catch (NumberFormatException e) {
					bd_cl_no = 0;
					errorMsgs.add("看板編號請填數字.");
				}
				
				Integer mbr_no = null;
				try {
					mbr_no = new Integer(req.getParameter("mbr_no").trim());
				} catch (NumberFormatException e) {
					mbr_no = 0;
					errorMsgs.add("會員編號請填數字.");
				}
				
				String art_title = req.getParameter("art_title");
				String art_titleReg = "^.{2,30}$";
				if (art_title == null || art_title.trim().length() == 0) {
					errorMsgs.add("文章標題: 請勿空白");
				} 
				else if(!art_title.trim().matches(art_titleReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("文章標題: 長度必須在2到30之間");
	            }
				
				
	
				Timestamp art_rel_time = new Timestamp(System.currentTimeMillis());
				
				String art_cont = req.getParameter("art_cont");
//				String art_contReg = "^.{10,1000000}$";
				System.out.println(art_cont);
				if (art_cont == null || art_cont.trim().length() == 0) {
					errorMsgs.add("文章內容: 請勿空白");
				} 
//				else if(!art_cont.trim().matches(art_contReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("文章內容: 必須在10到10000個字之間");
//	            }
				
				Integer likes = 0;
				Integer art_stat = 0;		
				Integer replies = 0;
				

				ArticleVO articleVO = new ArticleVO();
				articleVO.setBd_cl_no(bd_cl_no);
				articleVO.setMbr_no(mbr_no);
				articleVO.setArt_rel_time(art_rel_time);
				articleVO.setArt_title(art_title);
				articleVO.setArt_cont(art_cont);
				articleVO.setLikes(likes);
				articleVO.setArt_stat(art_stat);
				articleVO.setReplies(replies);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的articleVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.addArticle(bd_cl_no, mbr_no,art_rel_time,art_title,art_cont, likes,art_stat,replies);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllArticle.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/addArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllArticle.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer art_no = new Integer(req.getParameter("art_no"));
				
				/***************************2.開始刪除資料***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.deleteArticle(art_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		

		
		if ("hide".equals(action)) { // 來自前台，並不是真的刪除，而是將文章狀態設為不顯示
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer art_no = new Integer(req.getParameter("art_no").trim());
				ArticleVO articleVO = new ArticleVO();
				articleVO.setArt_no(art_no);
				System.out.println("1我已來到此處artno為"+art_no);
				/***************************2.開始隱藏資料***************************************/
				ArticleService articleSvc = new ArticleService();
				System.out.println("2我已來到此處artno為"+art_no);
				articleSvc.hide(art_no);
				System.out.println("3我已來到此處artno為"+art_no);
				/***************************3.隱藏完成,準備轉交(Send the Success view)***********/			
				System.out.println("4我已來到此處artno為"+art_no);
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("隱藏資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getOne_From".equals(action)) { //列出某文章

			try {
				// Retrieve form parameters.
				Integer art_no = new Integer(req.getParameter("art_no"));

				com.article.model.ArticleDAO dao = new com.article.model.ArticleDAO();
				ArticleVO articleVO = dao.findByPrimaryKey(art_no);

				req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req

				// 取出的articleVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/article/listOneArticle.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		if ("getOne_From2".equals(action)) {  //回到論壇首頁的燈箱

			try {
				// Retrieve form parameters.
				Integer art_no = new Integer(req.getParameter("art_no"));

				ArticleDAO dao = new ArticleDAO();
				ArticleVO articleVO = dao.findByPrimaryKey(art_no);

				req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req
				
				//Bootstrap_modal
				boolean openModal=true;
				System.out.println(openModal);
				req.setAttribute("openModal",openModal );
				
				// 取出的articleVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		
		if ("getOne_From3".equals(action)) {  //回到論壇首頁的燈箱

			try {
				// Retrieve form parameters.
				Integer art_no = new Integer(req.getParameter("art_no"));
				Integer bd_cl_no = new Integer(req.getParameter("bd_cl_no"));
				
				ArticleDAO dao = new ArticleDAO();
				ArticleVO articleVO = dao.findByPrimaryKey(art_no);

				req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req
				req.setAttribute("bd_cl_no", articleVO.getBd_cl_no());
				//Bootstrap_modal
				boolean openModal=true;
				System.out.println(openModal);
				req.setAttribute("openModal",openModal );
				
				// 取出的articleVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/article/listOneBoard_ClassArticle.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
	}
}
