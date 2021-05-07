package com.article_likes_controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article_likes.model.Article_LikesService;
import com.article_likes.model.Article_LikesVO;

@WebServlet("/article_likes/article_likes.do")
public class Article_LikesServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getbyart_no".equals(action)) { // 來自select_page.jsp的請求，列出單一文章 哪些人按讚

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("art_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_likes/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer art_no = null;
				try {
					art_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("文章編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_likes/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Article_LikesService article_likesSvc = new Article_LikesService();
				List<Article_LikesVO> article_likesVO = article_likesSvc.findbyart_no(art_no);
				if (article_likesVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_likes/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("article_likesVO", article_likesVO); // 資料庫取出的article_likesVO物件,存入req
				String url = "/back-end/article_likes/listOneArticle_Likes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneArticle_Likes.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_likes/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getbymbr_no".equals(action)) { // 來自select_page.jsp的請求，列出某會員按了哪些文章讚

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("mbr_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_likes/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer mbr_no = null;
				try {
					mbr_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_likes/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Article_LikesService article_likesSvc = new Article_LikesService();
				List<Article_LikesVO> article_likesVO = article_likesSvc.findbyart_no(mbr_no);
				if (article_likesVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_likes/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("article_likesVO", article_likesVO); // 資料庫取出的article_likesVO物件,存入req
				String url = "/back-end/article_likes/listOneMember_Likes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember_Likes.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_likes/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("like".equals(action)) { // 來自listOneArticle.jsp的請求

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				// 不需要錯誤驗證，因為到時候是透過button來按讚
				Integer mbr_no = null;
				Integer art_no = null;

				mbr_no = new Integer(req.getParameter("mbr_no").trim());
				art_no = new Integer(req.getParameter("art_no").trim());

				Article_LikesVO article_likesVO = new Article_LikesVO();
				article_likesVO.setMbr_no(mbr_no);
				article_likesVO.setArt_no(art_no);

				/*************************** 2.開始新增資料 ***************************************/
				Article_LikesService article_likesSvc = new Article_LikesService();
				article_likesVO = article_likesSvc.addArticle_Likes(mbr_no, art_no);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交回listOneArticle.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/listOneArticle.jsp");
				failureView.forward(req, res);
			}
		}

//		if ("unlike".equals(action)) { // 來自listOneArticle.jsp 取消某人對文章的按讚
//
//			try {
//				/*************************** 1.接收請求參數 ***************************************/
//				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
//				Integer art_no = new Integer(req.getParameter("art_no"));
//
//				/*************************** 2.開始刪除資料 ***************************************/
//				Article_LikesService article_likesSvc = new Article_LikesService();
//				article_likesSvc.deleteLike(mbr_no, art_no);
//
//				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//				String url = "/back-end/article/listOneArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/listOneArticle.jsp");
//				failureView.forward(req, res);
//			}
//		}

		if ("plus_like".equals(action)) { // 來自listOneArticle.jsp的請求 為某篇文章新增一筆按讚資料

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer art_no = null;
			art_no = new Integer(req.getParameter("art_no").trim());

			Integer mbr_no = null;
			mbr_no = new Integer(req.getParameter("mbr_no").trim());

			Article_LikesVO article_likesVO = new Article_LikesVO();
			article_likesVO.setMbr_no(mbr_no);
			article_likesVO.setArt_no(art_no);

			/*************************** 2.開始新增資料 ***************************************/
			Article_LikesService article_likesSvc = new Article_LikesService();
			article_likesVO = article_likesSvc.addArticle_Likes(mbr_no, art_no);

		}
		
		
		
		
		if ("unlike".equals(action)) { // 來自listOneArticle.jsp

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
				Integer art_no = new Integer(req.getParameter("art_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				Article_LikesService article_likesSvc = new Article_LikesService();
				article_likesSvc.deleteLike(mbr_no, art_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/listOneArticle.jsp");
				failureView.forward(req, res);
			}
		}

		
		
		if ("minus_like".equals(action)) { // 來自listOneArticle.jsp的請求 為某篇文章刪除一筆按讚資料

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer art_no = null;
			art_no = new Integer(req.getParameter("art_no").trim());

			Integer mbr_no = null;
			mbr_no = new Integer(req.getParameter("mbr_no").trim());

			Article_LikesVO article_likesVO = new Article_LikesVO();
			article_likesVO.setMbr_no(mbr_no);
			article_likesVO.setArt_no(art_no);

			/*************************** 2.開始新增資料 ***************************************/
			Article_LikesService article_likesSvc = new Article_LikesService();
			article_likesSvc.deleteLike(mbr_no, art_no);


		} //end of minus_like
		
		
		
		
	}
}
