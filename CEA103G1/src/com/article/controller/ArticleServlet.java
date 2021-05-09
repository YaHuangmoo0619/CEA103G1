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

import redis.clients.jedis.Jedis;


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
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				
				
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str2 = req.getParameter("art_no");
				if (str2 == null || (str2.trim()).length() == 0) {
					errorMsgs.add("�п�J�峹�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer art_no = null;
				try {
					art_no = new Integer(str2);
				} catch (Exception e) {
					errorMsgs.add("�峹�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d��SQL���*****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(art_no);
				if (articleVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				


				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO); // ��Ʈw���X��articleVO����,�s�Jreq
				String url = "/back-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneArticle.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		if ("getOneArticle_ByBoard_Clss_For_Display".equals(action)) { // �Ӧ�front-end listAllArticle.jsp���ШD  

				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("bd_cl_no"); //���ο��~���� �]���O�������� ���O�ѨϥΪ̿�J
				Integer bd_cl_no = new Integer(str);
				System.out.println(bd_cl_no);
				
				/***************************2.�}�l�d�߸��*****************************************/
				ArticleService articleSvc = new ArticleService();
				List<ArticleVO> articleVO = articleSvc.getByBoard_Class_Front(bd_cl_no);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO); // ��Ʈw���X��articleVO����,�s�Jreq
				String url = "/front-end/article/listOneBoard_ClassArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneBoard_ClassArticle.jsp
				successView.forward(req, res);

		}
		
		
		
		if ("getOneArticle_ByBoard_Clss_For_Display".equals(action)) { // �Ӧ�back-end listAllArticle.jsp���ШD  

			/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			String str = req.getParameter("bd_cl_no"); //���ο��~���� �]���O�������� ���O�ѨϥΪ̿�J
			Integer bd_cl_no = new Integer(str);
			System.out.println(bd_cl_no);
			
			/***************************2.�}�l�d�߸��*****************************************/
			ArticleService articleSvc = new ArticleService();
			List<ArticleVO> articleVO = articleSvc.getByBoard_Class_Back(bd_cl_no);
			
			/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
			req.setAttribute("articleVO", articleVO); // ��Ʈw���X��articleVO����,�s�Jreq
			String url = "/back-end/article/listOneBoard_ClassArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneBoard_ClassArticle.jsp
			successView.forward(req, res);

	}
		
		
		if ("getArticlesByTagFor_Display".equals(action)) { // �Ӧ�front-end listOneTagArticles.jsp���ШD  
			System.out.println(req.getParameter("tag"));

			/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			Jedis jedis = new Jedis("localhost", 6379); //��l��redis
			jedis.auth("123456"); 
			jedis.select(6); //���6��  �]����x�s�bdb06
			
			
			/***************************2.Redis�}�l�d�߸��*****************************************/
			Set<String> tagged_article_list = jedis.smembers("tag:"+req.getParameter("tag")+":posts"); //�N�Ҧ��Q�е���tag���峹�s���s�J�@��set
			jedis.close(); //����
			System.out.println("hello");
			/***************************3.MySQL�}�l�d�߸��*****************************************/
			List<ArticleVO> articleVO = new ArrayList<>(); //�ؤ@�Ӥ峹list
			ArticleService articleSvc = new ArticleService(); //�ؤ@��service
			System.out.println("hello2");
			for (String str : tagged_article_list) { //�N�Ҧ��Qtag�쪺�峹vo  ��J�峹list��
				int i = Integer.parseInt(str);
				articleVO.add(articleSvc.getOneArticle(i));
			}
			
			System.out.println("hello3");
			/***************************4.�d�ߧ���,�ǳ����(Send the Success view)*************/
			req.setAttribute("tag", req.getParameter("tag"));
			req.setAttribute("articleVO", articleVO); // ��Ʈw���X��List<ArticleVO> articleVO����,�s�Jreq
			String url = "/front-end/article/listOneTagArticles.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneTagArticles.jsp
			successView.forward(req, res);

	}
		
		
		
		
		
		
		
		
//		if ("getOneArticle_ByBoard_Clss_For_Display".equals(action)) { // �Ӧ�back-end listAllArticle.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				String str = req.getParameter("bd_cl_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("�п�J�ݪO�s��");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/article/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//
//				Integer bd_cl_no = null;
//				try {
//					bd_cl_no = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("�ݪO�s���榡�����T");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/article/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************2.�}�l�d�߸��*****************************************/
//				ArticleService articleSvc = new ArticleService();
//				List<ArticleVO> articleVO = articleSvc.getByBoard_Class(bd_cl_no);
//				if (articleVO == null) {
//					errorMsgs.add("�d�L���");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/article/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
//				req.setAttribute("articleVO", articleVO); // ��Ʈw���X��article_replyVO����,�s�Jreq
//				String url = "/back-end/article/listOneBoard_ClassArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneBoard_ClassArticle.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/article/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}	
		
		
		
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllArticle.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer art_no = new Integer(req.getParameter("art_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(art_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("articleVO", articleVO);         // ��Ʈw���X��articleVO����,�s�Jreq
				String url = "/front-end/article/update_article_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_article_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_article_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			
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
					errorMsgs.add("�峹���D: �ФŪť�");
				} 
				else if(!art_title.trim().matches(art_titleReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�峹���D: ���ץ����b2��30����");
	            }
				System.out.println(art_title);
				
				String art_cont = req.getParameter("art_cont");
				
				if (art_cont == null || art_cont.trim().length() == 0) {
					errorMsgs.add("�峹���e: �ФŪť�");
				} 

				System.out.println(art_cont);
				
				Integer likes = null;
				try {
					likes = new Integer(req.getParameter("likes").trim());
				} catch (NumberFormatException e) {
					likes = 0;
					errorMsgs.add("�g�ƽж�Ʀr.");
				} 
				System.out.println(likes);
				
				Integer art_stat = null;
				try {
					art_stat = new Integer(req.getParameter("art_stat").trim());
				} catch (NumberFormatException e) {
					art_stat = 0;
					errorMsgs.add("�峹���A�ж�Ʀr0 or 1.");
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
					req.setAttribute("articleVO",articleVO); // �t����J�榡���~��articleVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/update_article_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.updateArticle(art_no,bd_cl_no,mbr_no,art_rel_time,art_title,art_cont,likes,art_stat,replies);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO); // ��Ʈwupdate���\��,���T����articleVO����,�s�Jreq
				String url = "/front-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneArticle.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/update_article_input.jsp");
				failureView.forward(req, res);
			}
		}

		
		
		if ("plus_like".equals(action)) { // �Ӧ�liesOneArticle.jsp���ШD ���o�g�峹�g��+1

				/***************************1.�����ШD�Ѽ�**********************/		
				Integer art_no = new Integer(req.getParameter("art_no").trim());					
				ArticleVO articleVO = new ArticleVO();
				articleVO.setArt_no(art_no);	
				/***************************2.�}�l�ק���*****************************************/
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.plus_like(art_no);

		}
		
		
		
		if ("minus_like".equals(action)) { // �Ӧ�liesOneArticle.jsp���ШD ���o�g�峹�g��+1		
			    
			    /***************************1.�����ШD�Ѽ�**********************/
				Integer art_no = new Integer(req.getParameter("art_no").trim());						
				ArticleVO articleVO = new ArticleVO();
				articleVO.setArt_no(art_no);
	            /***************************2.�}�l�ק���*****************************************/
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.plus_like(art_no);

		}
		
		
		
		
		
        if ("insert".equals(action)) { // �Ӧ۫e��addArticle.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/

				String[] values = req.getParameterValues("tags");//���o�Ҧ��Ŀ諸����
				
				
				Integer bd_cl_no = null;
				try {
					bd_cl_no = new Integer(req.getParameter("bd_cl_no").trim());
				} catch (NumberFormatException e) {
					bd_cl_no = 0;
					errorMsgs.add("�ݪO�s���ж�Ʀr.");
				}
				
				Integer mbr_no = null;
				try {
					mbr_no = new Integer(req.getParameter("mbr_no").trim());
				} catch (NumberFormatException e) {
					mbr_no = 0;
					errorMsgs.add("�|���s���ж�Ʀr.");
				}
				
				String art_title = req.getParameter("art_title");
				String art_titleReg = "^.{2,30}$";
				if (art_title == null || art_title.trim().length() == 0) {
					errorMsgs.add("�峹���D: �ФŪť�");
				} 
				else if(!art_title.trim().matches(art_titleReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�峹���D: ���ץ����b2��30����");
	            }
				
				
	
				Timestamp art_rel_time = new Timestamp(System.currentTimeMillis());
				
				String art_cont = req.getParameter("art_cont");
//				String art_contReg = "^.{10,1000000}$";
//				System.out.println(art_cont);
				if (art_cont == null || art_cont.trim().length() == 0) {
					errorMsgs.add("�峹���e: �ФŪť�");
				}
				System.out.println(art_cont);
//				else if(!art_cont.trim().matches(art_contReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("�峹���e: �����b10��10000�Ӧr����");
//	            }
				
				Integer likes = 0;
				Integer art_stat = 0;		
				Integer replies = 0;
				

				ArticleVO articleVO = new ArticleVO(); //�n�s�W���峹
				articleVO.setBd_cl_no(bd_cl_no);
				articleVO.setMbr_no(mbr_no);
				articleVO.setArt_rel_time(art_rel_time);
				articleVO.setArt_title(art_title);
				articleVO.setArt_cont(art_cont);
				articleVO.setLikes(likes);
				articleVO.setArt_stat(art_stat);
				articleVO.setReplies(replies);
				
				ArticleVO articleVO2 = new ArticleVO(); //�d�̫߳�@���峹�����X

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // �t����J�榡���~��articleVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/

				ArticleService articleSvc = new ArticleService();
				articleVO2 = articleSvc.findLast();
				Integer no = articleVO2.getArt_no()+1; //���o�̫�@���峹+1  �Y�ثe�ҭn�s�W�峹���峹���X
				System.out.println("no:"+no); //�L�X����
				
				
				Jedis jedis = new Jedis("localhost", 6379);
				jedis.auth("123456");
				jedis.select(6);
				for(String str : values) {//Redis�s�W�}�l
				System.out.println(str); //�L�X����
				
				jedis.sadd("post:"+no+":tags", str);
			    }
				jedis.close();//Redis�s�W����
				
				articleVO = articleSvc.addArticle(bd_cl_no, mbr_no,art_rel_time,art_title,art_cont, likes,art_stat,replies);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllArticle.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/addArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllArticle.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer art_no = new Integer(req.getParameter("art_no"));
				
				/***************************2.�}�l�R�����***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.deleteArticle(art_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		

		
		if ("hide".equals(action)) { // �Ӧ۫e�x�A�ä��O�u���R���A�ӬO�N�峹���A�]�������
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer art_no = new Integer(req.getParameter("art_no").trim());
				ArticleVO articleVO = new ArticleVO();
				articleVO.setArt_no(art_no);
				System.out.println("1�ڤw�Ө즹�Bartno��"+art_no);
				/***************************2.�}�l���ø��***************************************/
				ArticleService articleSvc = new ArticleService();
				System.out.println("2�ڤw�Ө즹�Bartno��"+art_no);
				articleSvc.hide(art_no);
				System.out.println("3�ڤw�Ө즹�Bartno��"+art_no);
				/***************************3.���ç���,�ǳ����(Send the Success view)***********/			
				System.out.println("4�ڤw�Ө즹�Bartno��"+art_no);
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("���ø�ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getOne_From".equals(action)) { //�C�X�Y�峹
			System.out.println("I'm getOne_From");
			try {
				// Retrieve form parameters.
				Integer art_no = new Integer(req.getParameter("art_no"));
				com.article.model.ArticleDAO dao = new com.article.model.ArticleDAO();
				ArticleVO articleVO = dao.findByPrimaryKey(art_no);

				req.setAttribute("articleVO", articleVO); // ��Ʈw���X��articleVO����,�s�Jreq

				// ���X��articleVO�e��listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/article/listOneArticle.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		if ("getOne_From2".equals(action)) {  //�^��׾­������O�c

			try {
				
				String requestURL = req.getHeader("Referer");  
				System.out.println(requestURL);
				req.setAttribute("requestURL", requestURL);
				Jedis jedis = new Jedis("localhost", 6379);
				jedis.auth("123456");
				jedis.select(6);
				
//				System.out.println(req.getParameter("art_no"));
//				for (String str : jedis.smembers("post:"+req.getParameter("art_no")+":tags")) {
//					System.out.println(str);
//				}
				Set<String> tag_list = jedis.smembers("post:"+req.getParameter("art_no")+":tags");
				
				
				jedis.close();
				
//
//				    for(String element : tag_list) {
//				        System.out.println(element);
//				    }
//
				req.setAttribute("tag_list", tag_list); // Redis��Ʈw���X��tag_list����,�s�Jreq
				
				
				// Retrieve form parameters.
				Integer art_no = new Integer(req.getParameter("art_no"));

				ArticleDAO dao = new ArticleDAO();
				ArticleVO articleVO = dao.findByPrimaryKey(art_no);

				req.setAttribute("articleVO", articleVO); // ��Ʈw���X��articleVO����,�s�Jreq
				
				//Bootstrap_modal
				boolean openModal=true;
				System.out.println(openModal);
				req.setAttribute("openModal",openModal );
				

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		
		if ("getOne_From3".equals(action)) {  //�^��ݪO�������O�c

			try {
				// Retrieve form parameters.
				Integer art_no = new Integer(req.getParameter("art_no"));
				Integer bd_cl_no = new Integer(req.getParameter("bd_cl_no"));
				
				ArticleDAO dao = new ArticleDAO();
				ArticleVO articleVO = dao.findByPrimaryKey(art_no);

				req.setAttribute("articleVO", articleVO); // ��Ʈw���X��articleVO����,�s�Jreq
				req.setAttribute("bd_cl_no", articleVO.getBd_cl_no());
				//Bootstrap_modal
				boolean openModal=true;
				System.out.println(openModal);
				req.setAttribute("openModal",openModal );
				
				// ���X��articleVO�e��listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/article/listOneBoard_ClassArticle.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		
		if ("search_tag".equals(action)) { 

			try {
				System.out.println("hello");
				// Retrieve parameters.
				Integer art_no = new Integer(req.getParameter("art_no"));
				Integer bd_cl_no = new Integer(req.getParameter("bd_cl_no"));
				
				ArticleDAO dao = new ArticleDAO();
				ArticleVO articleVO = dao.findByPrimaryKey(art_no);

				req.setAttribute("articleVO", articleVO); // ��Ʈw���X��articleVO����,�s�Jreq
				req.setAttribute("bd_cl_no", articleVO.getBd_cl_no());
				

				
				// ���X��articleVO�e��listOneEmp.jsp
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
