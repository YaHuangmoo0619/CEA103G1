package com.article_collection.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article_collection.model.Article_CollectionService;
import com.article_collection.model.Article_CollectionVO;




@WebServlet("/article_collection/article_collection.do")
public class Article_CollectionServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	
	
	
		if ("getbyart_no".equals(action)) { // 來自select_page.jsp的請求，取得收藏某文章的所有人

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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_collection/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_collection/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Article_CollectionService article_collectionSvc = new Article_CollectionService();
				List<Article_CollectionVO> article_collectionVO = article_collectionSvc.findbyart_no(art_no);
				if (article_collectionVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_collection/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("article_collectionVO", article_collectionVO); // 資料庫取出的article_likesVO物件,存入req
				String url = "/back-end/article_collection/listOneArticle_Collection.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneArticle_Likes.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_collection/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	
	
	
		
		if ("getbymbr_no".equals(action)) { // 來自select_page.jsp的請求，列出某會員收藏了哪些文章

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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_collection/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_collection/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Article_CollectionService article_collectionSvc = new Article_CollectionService();
				List<Article_CollectionVO> article_collectionVO = article_collectionSvc.findbyart_no(mbr_no);

				if (article_collectionVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_collection/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("article_collectionVO", article_collectionVO); // 資料庫取出的article_collectionVO物件,存入req
				String url = "/back-end/article_collection/listOneMember_Collections.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember_Collections.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_collection/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("plus_collection".equals(action)) { // 為某篇文章新增一筆收藏資料

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer art_no = null;
			art_no = new Integer(req.getParameter("art_no").trim());

			Integer mbr_no = null;
			mbr_no = new Integer(req.getParameter("mbr_no").trim());

			Article_CollectionVO article_collectionVO = new Article_CollectionVO();
			article_collectionVO.setMbr_no(mbr_no);
			article_collectionVO.setArt_no(art_no);

			/*************************** 2.開始新增資料 ***************************************/
			Article_CollectionService article_collectionSvc = new Article_CollectionService();
			article_collectionVO = article_collectionSvc.addArticle_Collection(mbr_no, art_no);

		} //end of plus_collection
		
		
		if ("minus_collection".equals(action)) { // 為某篇文章刪除一筆收藏資料

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer art_no = null;
			art_no = new Integer(req.getParameter("art_no").trim());

			Integer mbr_no = null;
			mbr_no = new Integer(req.getParameter("mbr_no").trim());

			Article_CollectionVO article_collectionVO = new Article_CollectionVO();
			article_collectionVO.setMbr_no(mbr_no);
			article_collectionVO.setArt_no(art_no);

			/*************************** 2.開始新增資料 ***************************************/
			Article_CollectionService article_collectionSvc = new Article_CollectionService();
			article_collectionSvc.deleteArticle_Collection(mbr_no, art_no);


		} //end of minus_collection
		
		
		
		
	}
}
