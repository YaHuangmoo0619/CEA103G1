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
		
	
	
	
		if ("getbyart_no".equals(action)) { // �Ӧ�select_page.jsp���ШD�A���o���ìY�峹���Ҧ��H

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("art_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�峹�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_collection/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer art_no = null;
				try {
					art_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�峹�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_collection/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				Article_CollectionService article_collectionSvc = new Article_CollectionService();
				List<Article_CollectionVO> article_collectionVO = article_collectionSvc.findbyart_no(art_no);
				if (article_collectionVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_collection/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("article_collectionVO", article_collectionVO); // ��Ʈw���X��article_likesVO����,�s�Jreq
				String url = "/back-end/article_collection/listOneArticle_Collection.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneArticle_Likes.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_collection/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	
	
	
		
		if ("getbymbr_no".equals(action)) { // �Ӧ�select_page.jsp���ШD�A�C�X�Y�|�����äF���Ǥ峹

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("mbr_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_collection/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer mbr_no = null;
				try {
					mbr_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�|���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_collection/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				Article_CollectionService article_collectionSvc = new Article_CollectionService();
				List<Article_CollectionVO> article_collectionVO = article_collectionSvc.findbyart_no(mbr_no);

				if (article_collectionVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_collection/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("article_collectionVO", article_collectionVO); // ��Ʈw���X��article_collectionVO����,�s�Jreq
				String url = "/back-end/article_collection/listOneMember_Collections.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneMember_Collections.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_collection/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("plus_collection".equals(action)) { // ���Y�g�峹�s�W�@�����ø��

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			Integer art_no = null;
			art_no = new Integer(req.getParameter("art_no").trim());

			Integer mbr_no = null;
			mbr_no = new Integer(req.getParameter("mbr_no").trim());

			Article_CollectionVO article_collectionVO = new Article_CollectionVO();
			article_collectionVO.setMbr_no(mbr_no);
			article_collectionVO.setArt_no(art_no);

			/*************************** 2.�}�l�s�W��� ***************************************/
			Article_CollectionService article_collectionSvc = new Article_CollectionService();
			article_collectionVO = article_collectionSvc.addArticle_Collection(mbr_no, art_no);

		} //end of plus_collection
		
		
		if ("minus_collection".equals(action)) { // ���Y�g�峹�R���@�����ø��

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			Integer art_no = null;
			art_no = new Integer(req.getParameter("art_no").trim());

			Integer mbr_no = null;
			mbr_no = new Integer(req.getParameter("mbr_no").trim());

			Article_CollectionVO article_collectionVO = new Article_CollectionVO();
			article_collectionVO.setMbr_no(mbr_no);
			article_collectionVO.setArt_no(art_no);

			/*************************** 2.�}�l�s�W��� ***************************************/
			Article_CollectionService article_collectionSvc = new Article_CollectionService();
			article_collectionSvc.deleteArticle_Collection(mbr_no, art_no);


		} //end of minus_collection
		
		
		
		
	}
}
