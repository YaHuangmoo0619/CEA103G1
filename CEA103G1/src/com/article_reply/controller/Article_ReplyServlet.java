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
		
		
		
		
		if ("getOneReply_For_Display_front".equals(action)) { // 來自select_page.jsp的請求，列出特定的單一留言
				
				System.out.println("我來啦");

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("art_rep_no");
				System.out.println(str);
				Integer art_rep_no = new Integer(str);

				/***************************2.開始查詢資料*****************************************/
				Article_ReplyService article_replySvc = new Article_ReplyService();
				Article_ReplyVO article_replyVO = article_replySvc.getOneArticle_Reply(art_rep_no);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("article_replyVO", article_replyVO); // 資料庫取出的article_replyVO物件,存入req
				String url = "/front-end/article_reply/listOneArticle_Reply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneArticle_reply.jsp
				successView.forward(req, res);
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
			System.out.println("我在getOne_For_Update");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 接到listOneAriticle
			System.out.println(requestURL);
			try {
				/***************************1.接收請求參數****************************************/
				Integer art_rep_no = new Integer(req.getParameter("art_rep_no"));
				System.out.println("這邊的留言編號:"+art_rep_no);
				/***************************2.開始查詢資料****************************************/
				Article_ReplyService article_replySvc = new Article_ReplyService();
				Article_ReplyVO article_replyVO = article_replySvc.getOneArticle_Reply(art_rep_no);
				System.out.println("article_replyVO:"+article_replyVO.getArt_rep_no());
								
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
		
		
		if ("update".equals(action)) { // 來自addArticle_Reply.jsp的請求  
			System.out.println("我有來");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer art_rep_no = new Integer(req.getParameter("art_rep_no").trim());
				System.out.println("art_rep_no"+art_rep_no);
				Integer art_no = new Integer(req.getParameter("art_no").trim());
				System.out.println("art_no"+art_no);
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				System.out.println("mbr_no"+mbr_no);
				Timestamp rep_time = new Timestamp(System.currentTimeMillis());

				String rep_cont = req.getParameter("rep_cont");
				System.out.println("rep_cont"+rep_cont);

				

//				標註樓層實作開始
//				Step1:查看目前這篇文章共有幾篇留言 (從資料庫取留言數+1，因為要算上即將加入的這一筆)
				ArticleVO articleVO_for_search = new ArticleVO();
				ArticleService articleSvc = new ArticleService();
				articleVO_for_search = articleSvc.getOneArticle(art_no);
				int replies_num = articleVO_for_search.getReplies(); //取得留言數

//				Step2: 然後for迴圈去replaceAll文章內容裡符合「#B1 」、「#B2 」......「#Bn 」的字              為「<a src="查看B1留言的路徑">#B1</a>」				
//				標註樓層實作結束
				int i;
				for(i=1;i<=replies_num;i++) {
					//如果原本的rep_cont含有特定的字符
					if(rep_cont.contains("#B"+i+" ")) {
						//就進行查詢，被tag的這樓留言，真實的留言編號為何，準備進行替換

						rep_cont=rep_cont.replace("#B"+i+" ","<div class=oneReply>#B"+i+" </div>" );	
					}
					
				}
				
				if (rep_cont == null || rep_cont.trim().length() == 0) {
					errorMsgs.add("留言內容: 請勿空白");
				} 

				Integer likes = new Integer(req.getParameter("likes").trim()); 

				Integer rep_stat = new Integer(req.getParameter("rep_stat").trim());

				Article_ReplyVO article_replyVO = new Article_ReplyVO();
				article_replyVO.setArt_rep_no(art_rep_no);
				article_replyVO.setArt_no(art_no);
				article_replyVO.setMbr_no(mbr_no);
				article_replyVO.setRep_time(rep_time);
				article_replyVO.setRep_cont(rep_cont);
				article_replyVO.setLikes(likes);
				article_replyVO.setRep_stat(rep_stat);
				


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("article_replyVO", article_replyVO); // 含有輸入格式錯誤的article_replyVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article_reply/addArticle_reply.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				System.out.println("我要新增囉");
				//更新一筆留言資料
				Article_ReplyService article_replySvc = new Article_ReplyService();
				article_replyVO = article_replySvc.updateArticle_Reply(art_rep_no, art_no, mbr_no, rep_cont, rep_time, rep_stat, likes);

				
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

		
        if ("insert".equals(action)) { // 來自addArticle_Reply.jsp的請求  
			System.out.println("我有來");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer art_no = new Integer(req.getParameter("art_no").trim());
				System.out.println("art_no"+art_no);
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				System.out.println("mbr_no"+mbr_no);
				Timestamp rep_time = new Timestamp(System.currentTimeMillis());

				String rep_cont = req.getParameter("rep_cont");
				System.out.println("rep_cont"+rep_cont);

				

//				標註樓層實作開始
//				Step1:查看目前這篇文章共有幾篇留言 (從資料庫取留言數+1，因為要算上即將加入的這一筆)
				ArticleVO articleVO_for_search = new ArticleVO();
				ArticleService articleSvc = new ArticleService();
				articleVO_for_search = articleSvc.getOneArticle(art_no);
				int replies_num = articleVO_for_search.getReplies()+1; //取得留言數

//				Step2: 然後for迴圈去replaceAll文章內容裡符合「#B1 」、「#B2 」......「#Bn 」的字              為「<a src="查看B1留言的路徑">#B1</a>」				
//				標註樓層實作結束
				int i;
				for(i=1;i<=replies_num;i++) {
					//如果原本的rep_cont含有特定的字符
					if(rep_cont.contains("#B"+i+" ")) {
						//就進行查詢，被tag的這樓留言，真實的留言編號為何，準備進行替換

						rep_cont=rep_cont.replace("#B"+i+" ","<div class=oneReply>#B"+i+" </div>" );
//						rep_cont=rep_cont.replace("#B"+i+" ","<a href=\"https://www.google.com/\">#B"+i+" </a>" );	
					}
					
				}
//				String rep_contReg = "^B{1}[1-9]{1}[0-9]{0,} {1}$";
				
				if (rep_cont == null || rep_cont.trim().length() == 0) {
					errorMsgs.add("留言內容: 請勿空白");
				} 
//				else if(!rep_cont.trim().matches(rep_contReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("留言內容: 必須在10到10000個字之間");
//	            }

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
				System.out.println("我要新增囉");
				//新增一筆留言資料Article_reply
				Article_ReplyService article_replySvc = new Article_ReplyService();
				article_replyVO = article_replySvc.addArticle_Reply(art_no, mbr_no,rep_cont,rep_time,rep_stat,likes);
				//新增一筆留言數量到Article
//				ArticleService articleSvc = new ArticleService();
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
