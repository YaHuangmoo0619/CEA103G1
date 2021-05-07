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

		if ("getbyart_no".equals(action)) { // �Ӧ�select_page.jsp���ШD�A�C�X��@�峹 ���ǤH���g

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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_likes/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_likes/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				Article_LikesService article_likesSvc = new Article_LikesService();
				List<Article_LikesVO> article_likesVO = article_likesSvc.findbyart_no(art_no);
				if (article_likesVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_likes/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("article_likesVO", article_likesVO); // ��Ʈw���X��article_likesVO����,�s�Jreq
				String url = "/back-end/article_likes/listOneArticle_Likes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneArticle_Likes.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_likes/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getbymbr_no".equals(action)) { // �Ӧ�select_page.jsp���ШD�A�C�X�Y�|�����F���Ǥ峹�g

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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_likes/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_likes/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				Article_LikesService article_likesSvc = new Article_LikesService();
				List<Article_LikesVO> article_likesVO = article_likesSvc.findbyart_no(mbr_no);
				if (article_likesVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_likes/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("article_likesVO", article_likesVO); // ��Ʈw���X��article_likesVO����,�s�Jreq
				String url = "/back-end/article_likes/listOneMember_Likes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneMember_Likes.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article_likes/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("like".equals(action)) { // �Ӧ�listOneArticle.jsp���ШD

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				// ���ݭn���~���ҡA�]����ɭԬO�z�Lbutton�ӫ��g
				Integer mbr_no = null;
				Integer art_no = null;

				mbr_no = new Integer(req.getParameter("mbr_no").trim());
				art_no = new Integer(req.getParameter("art_no").trim());

				Article_LikesVO article_likesVO = new Article_LikesVO();
				article_likesVO.setMbr_no(mbr_no);
				article_likesVO.setArt_no(art_no);

				/*************************** 2.�}�l�s�W��� ***************************************/
				Article_LikesService article_likesSvc = new Article_LikesService();
				article_likesVO = article_likesSvc.addArticle_Likes(mbr_no, art_no);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/front-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����^listOneArticle.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/listOneArticle.jsp");
				failureView.forward(req, res);
			}
		}

//		if ("unlike".equals(action)) { // �Ӧ�listOneArticle.jsp �����Y�H��峹�����g
//
//			try {
//				/*************************** 1.�����ШD�Ѽ� ***************************************/
//				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
//				Integer art_no = new Integer(req.getParameter("art_no"));
//
//				/*************************** 2.�}�l�R����� ***************************************/
//				Article_LikesService article_likesSvc = new Article_LikesService();
//				article_likesSvc.deleteLike(mbr_no, art_no);
//
//				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
//				String url = "/back-end/article/listOneArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//				successView.forward(req, res);
//
//				/*************************** ��L�i�઺���~�B�z **********************************/
//			} catch (Exception e) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/listOneArticle.jsp");
//				failureView.forward(req, res);
//			}
//		}

		if ("plus_like".equals(action)) { // �Ӧ�listOneArticle.jsp���ШD ���Y�g�峹�s�W�@�����g���

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			Integer art_no = null;
			art_no = new Integer(req.getParameter("art_no").trim());

			Integer mbr_no = null;
			mbr_no = new Integer(req.getParameter("mbr_no").trim());

			Article_LikesVO article_likesVO = new Article_LikesVO();
			article_likesVO.setMbr_no(mbr_no);
			article_likesVO.setArt_no(art_no);

			/*************************** 2.�}�l�s�W��� ***************************************/
			Article_LikesService article_likesSvc = new Article_LikesService();
			article_likesVO = article_likesSvc.addArticle_Likes(mbr_no, art_no);

		}
		
		
		
		
		if ("unlike".equals(action)) { // �Ӧ�listOneArticle.jsp

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
				Integer art_no = new Integer(req.getParameter("art_no"));

				/*************************** 2.�}�l�R����� ***************************************/
				Article_LikesService article_likesSvc = new Article_LikesService();
				article_likesSvc.deleteLike(mbr_no, art_no);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/back-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/listOneArticle.jsp");
				failureView.forward(req, res);
			}
		}

		
		
		if ("minus_like".equals(action)) { // �Ӧ�listOneArticle.jsp���ШD ���Y�g�峹�R���@�����g���

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			Integer art_no = null;
			art_no = new Integer(req.getParameter("art_no").trim());

			Integer mbr_no = null;
			mbr_no = new Integer(req.getParameter("mbr_no").trim());

			Article_LikesVO article_likesVO = new Article_LikesVO();
			article_likesVO.setMbr_no(mbr_no);
			article_likesVO.setArt_no(art_no);

			/*************************** 2.�}�l�s�W��� ***************************************/
			Article_LikesService article_likesSvc = new Article_LikesService();
			article_likesSvc.deleteLike(mbr_no, art_no);


		} //end of minus_like
		
		
		
		
	}
}
