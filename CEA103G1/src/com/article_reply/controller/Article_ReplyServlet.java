package com.article_reply.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.article_reply.model.Article_ReplyService;
import com.article_reply.model.Article_ReplyVO;

@WebServlet("/article_reply/article_reply.do")
public class Article_ReplyServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOneReply_For_Display".equals(action)) { // 來自select_page.jsp的請求，列出特定的單一留言

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("art_rep_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入留言編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article_reply/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer art_rep_no = null;
				try {
					art_rep_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("留言編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article_reply/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Article_ReplyService article_replySvc = new Article_ReplyService();
				Article_ReplyVO article_replyVO = article_replySvc.getOneArticle_Reply(art_rep_no);
				if (article_replyVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article_reply/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("article_replyVO", article_replyVO); // 資料庫取出的article_replyVO物件,存入req
				String url = "/back-end/article_reply/listOneArticle_Reply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneArticle_reply.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/article_reply/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("getOneArticle_Replies_For_Display".equals(action)) { // 來自select_page.jsp的請求，列出單一文章的所有留言

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("art_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article_reply/select_page.jsp");
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
							.getRequestDispatcher("/back-end/article_reply/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Article_ReplyService article_replySvc = new Article_ReplyService();
				List<Article_ReplyVO> article_replyVO = article_replySvc.getOneArticle_Replies(art_no);
				if (article_replyVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article_reply/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("article_replyVO", article_replyVO); // 資料庫取出的article_replyVO物件,存入req
				String url = "/back-end/article_reply/listOneArticle_Replies.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneArticle_Replies.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/article_reply/select_page.jsp");
				failureView.forward(req, res);
			}
		}		
		
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllArticle_Reply.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 接到listOneAriticle
			System.out.println(requestURL);
			try {
				/***************************1.接收請求參數****************************************/
				Integer art_rep_no = new Integer(req.getParameter("art_rep_no"));
				
				/***************************2.開始查詢資料****************************************/
				Article_ReplyService article_replySvc = new Article_ReplyService();
				Article_ReplyVO article_replyVO = article_replySvc.getOneArticle_Reply(art_rep_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("article_replyVO", article_replyVO);         // 資料庫取出的article_replyVO物件,存入req
				String url = "/front-end/article_reply/update_article_reply_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_article_reply_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_article_reply_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		    
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑:
			System.out.println(requestURL);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//更新留言時，留言編號、文章編號、會員編號、發布時間、讚數 都是不可更動的，因此無須錯誤驗證，接收參數即可
				Integer art_rep_no = new Integer(req.getParameter("art_rep_no").trim());
				Integer art_no = new Integer(req.getParameter("art_no").trim());
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				Timestamp rep_time = new Timestamp(System.currentTimeMillis());
				rep_time = Timestamp.valueOf(req.getParameter("rep_time"));

	

				
				String rep_cont = req.getParameter("rep_cont");
				String rep_contReg = "^.{10,10000}$";

				if (rep_cont == null || rep_cont.trim().length() == 0) {
					errorMsgs.add("留言內容: 請勿空白");
				} 
				else if(!rep_cont.trim().matches(rep_contReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("留言內容: 必須在10到10000個字之間");
	            }
			
				Integer likes = new Integer(req.getParameter("likes").trim());
				
				Integer rep_stat = null;
				try {
					rep_stat = new Integer(req.getParameter("rep_stat").trim());
				} catch (NumberFormatException e) {
					rep_stat = 0;
					errorMsgs.add("留言狀態請填數字0 or 1.");
				} 

								
				Article_ReplyVO article_replyVO = new Article_ReplyVO();
				article_replyVO.setArt_rep_no(art_rep_no);
				article_replyVO.setArt_no(art_no);
				article_replyVO.setMbr_no(mbr_no);
				article_replyVO.setRep_cont(rep_cont);
				article_replyVO.setRep_time(rep_time);
				article_replyVO.setRep_stat(rep_stat);
				article_replyVO.setLikes(likes);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("article_replyVO",article_replyVO); // 含有輸入格式錯誤的article_replyVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article_reply/update_article_reply_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				Article_ReplyService article_replySvc = new Article_ReplyService();
				article_replyVO = article_replySvc.updateArticle_Reply(art_rep_no,art_no,mbr_no,rep_cont,rep_time,rep_stat,likes);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("article_replyVO", article_replyVO); // 資料庫update成功後,正確的的article_replyVO物件,存入req
				String url = requestURL;
				System.out.println(requestURL);								//front-end/article/listOneArticle.jsp?art_no=5&action=getOne_From
				RequestDispatcher successView = req.getRequestDispatcher("/article/article.do?art_no=5&action=getOne_From"); // 修改成功後,轉交listOneArticle_Reply.jsp
				successView.forward(req, res);
				
				
				

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article_reply/update_article_reply_input.jsp");
				failureView.forward(req, res);
			}
		}

		
        if ("insert".equals(action)) { // 來自addArticle_Reply.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer art_no = new Integer(req.getParameter("art_no").trim());
				
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				
				Timestamp rep_time = new Timestamp(System.currentTimeMillis());

				String rep_cont = req.getParameter("rep_cont");
				
				String rep_contReg = "^.{10,10000}$";
				
				if (rep_cont == null || rep_cont.trim().length() == 0) {
					errorMsgs.add("留言內容: 請勿空白");
				} 
				else if(!rep_cont.trim().matches(rep_contReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("留言內容: 必須在10到10000個字之間");
	            }

				Integer likes = 0; //讚數預設就是0

				Integer rep_stat = 0;//留言預設也是0 顯示

				Article_ReplyVO article_replyVO = new Article_ReplyVO();
				article_replyVO.setArt_no(art_no);
				article_replyVO.setMbr_no(mbr_no);
				article_replyVO.setRep_time(rep_time);
				article_replyVO.setRep_cont(rep_cont);
				article_replyVO.setLikes(likes);
				article_replyVO.setRep_stat(rep_stat);
				
				ArticleVO articleVO = new ArticleVO();
				articleVO.setArt_no(art_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("article_replyVO", article_replyVO); // 含有輸入格式錯誤的article_replyVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article_reply/addArticle_reply.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				//新增一筆留言資料Article_reply
				Article_ReplyService article_replySvc = new Article_ReplyService();
				article_replyVO = article_replySvc.addArticle_Reply(art_no, mbr_no,rep_cont,rep_time,rep_stat,likes);
				//新增一筆留言數量到Article
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.plus_reply(art_no);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllArticle.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllArticle_Reply.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer art_rep_no = new Integer(req.getParameter("art_rep_no"));
				
				/***************************2.開始刪除資料***************************************/
				Article_ReplyService article_replySvc = new Article_ReplyService();
				article_replySvc.deleteArticle_Reply(art_rep_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/article_reply/listAllArticle_Reply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/article_reply/listAllArticle_Reply.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("hide".equals(action)) { // 來自前台，並不是真的刪除，而是將留言狀態設為不顯示
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數***************************************/
				Integer art_rep_no = new Integer(req.getParameter("art_rep_no").trim());
				Article_ReplyVO article_replyVO = new Article_ReplyVO();
				article_replyVO.setArt_rep_no(art_rep_no);
				System.out.println(art_rep_no);
				/***************************2.開始隱藏資料***************************************/
				Article_ReplyService article_replySvc = new Article_ReplyService();

				article_replySvc.hide(art_rep_no);

				/***************************3.隱藏完成,準備轉交(Send the Success view)***********/			
				//要送回原來的網頁
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
		
		
		
	}
}
