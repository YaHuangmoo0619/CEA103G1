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
		
		
		if ("getOneReply_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD�A�C�X�S�w����@�d��

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("art_rep_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�d���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article_reply/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer art_rep_no = null;
				try {
					art_rep_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�d���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article_reply/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Article_ReplyService article_replySvc = new Article_ReplyService();
				Article_ReplyVO article_replyVO = article_replySvc.getOneArticle_Reply(art_rep_no);
				if (article_replyVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article_reply/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("article_replyVO", article_replyVO); // ��Ʈw���X��article_replyVO����,�s�Jreq
				String url = "/back-end/article_reply/listOneArticle_Reply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneArticle_reply.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/article_reply/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("getOneReply_For_Display_front".equals(action)) { // �Ӧ�select_page.jsp���ШD�A�C�X�S�w����@�d��
				
				System.out.println("�ڨӰ�");

				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("art_rep_no");
				System.out.println(str);
				Integer art_rep_no = new Integer(str);

				/***************************2.�}�l�d�߸��*****************************************/
				Article_ReplyService article_replySvc = new Article_ReplyService();
				Article_ReplyVO article_replyVO = article_replySvc.getOneArticle_Reply(art_rep_no);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("article_replyVO", article_replyVO); // ��Ʈw���X��article_replyVO����,�s�Jreq
				String url = "/back-end/article_reply/listOneArticle_Reply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneArticle_reply.jsp
				successView.forward(req, res);
		}
		
		
		
		
		
		if ("getOneArticle_Replies_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD�A�C�X��@�峹���Ҧ��d��

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("art_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�峹�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article_reply/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				

				Integer art_no = null;
				try {
					art_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�峹�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article_reply/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Article_ReplyService article_replySvc = new Article_ReplyService();
				List<Article_ReplyVO> article_replyVO = article_replySvc.getOneArticle_Replies(art_no);
				if (article_replyVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article_reply/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("article_replyVO", article_replyVO); // ��Ʈw���X��article_replyVO����,�s�Jreq
				String url = "/back-end/article_reply/listOneArticle_Replies.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneArticle_Replies.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/article_reply/select_page.jsp");
				failureView.forward(req, res);
			}
		}		
		
		
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllArticle_Reply.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // ����listOneAriticle
			System.out.println(requestURL);
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer art_rep_no = new Integer(req.getParameter("art_rep_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				Article_ReplyService article_replySvc = new Article_ReplyService();
				Article_ReplyVO article_replyVO = article_replySvc.getOneArticle_Reply(art_rep_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("article_replyVO", article_replyVO);         // ��Ʈw���X��article_replyVO����,�s�Jreq
				String url = "/front-end/article_reply/update_article_reply_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_article_reply_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_article_reply_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		    
			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|:
			System.out.println(requestURL);
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				//��s�d���ɡA�d���s���B�峹�s���B�|���s���B�o���ɶ��B�g�� ���O���i��ʪ��A�]���L�����~���ҡA�����ѼƧY�i
				Integer art_rep_no = new Integer(req.getParameter("art_rep_no").trim());
				Integer art_no = new Integer(req.getParameter("art_no").trim());
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				Timestamp rep_time = new Timestamp(System.currentTimeMillis());
				rep_time = Timestamp.valueOf(req.getParameter("rep_time"));

	

				
				String rep_cont = req.getParameter("rep_cont");
				String rep_contReg = "^.{10,10000}$";

				if (rep_cont == null || rep_cont.trim().length() == 0) {
					errorMsgs.add("�d�����e: �ФŪť�");
				} 
				else if(!rep_cont.trim().matches(rep_contReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�d�����e: �����b10��10000�Ӧr����");
	            }
			
				Integer likes = new Integer(req.getParameter("likes").trim());
				
				Integer rep_stat = null;
				try {
					rep_stat = new Integer(req.getParameter("rep_stat").trim());
				} catch (NumberFormatException e) {
					rep_stat = 0;
					errorMsgs.add("�d�����A�ж�Ʀr0 or 1.");
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
					req.setAttribute("article_replyVO",article_replyVO); // �t����J�榡���~��article_replyVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article_reply/update_article_reply_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				
				Article_ReplyService article_replySvc = new Article_ReplyService();
				article_replyVO = article_replySvc.updateArticle_Reply(art_rep_no,art_no,mbr_no,rep_cont,rep_time,rep_stat,likes);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("article_replyVO", article_replyVO); // ��Ʈwupdate���\��,���T����article_replyVO����,�s�Jreq
				String url = requestURL;
				System.out.println(requestURL);								//front-end/article/listOneArticle.jsp?art_no=5&action=getOne_From
				RequestDispatcher successView = req.getRequestDispatcher("/article/article.do?art_no=5&action=getOne_From"); // �ק令�\��,���listOneArticle_Reply.jsp
				successView.forward(req, res);
				
				
				

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article_reply/update_article_reply_input.jsp");
				failureView.forward(req, res);
			}
		}

		
        if ("insert".equals(action)) { // �Ӧ�addArticle_Reply.jsp���ШD  
			System.out.println("�ڦ���");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				Integer art_no = new Integer(req.getParameter("art_no").trim());
				System.out.println("art_no"+art_no);
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				System.out.println("mbr_no"+mbr_no);
				Timestamp rep_time = new Timestamp(System.currentTimeMillis());

				String rep_cont = req.getParameter("rep_cont");
				System.out.println("rep_cont"+rep_cont);

				

//				�е��Ӽh��@�}�l
//				Step1:�d�ݥثe�o�g�峹�@���X�g�d�� (�q��Ʈw���d����+1�A�]���n��W�Y�N�[�J���o�@��)
				ArticleVO articleVO_for_search = new ArticleVO();
				ArticleService articleSvc = new ArticleService();
				articleVO_for_search = articleSvc.getOneArticle(art_no);
				int replies_num = articleVO_for_search.getReplies()+1; //���o�d����

//				Step2: �M��for�j��hreplaceAll�峹���e�̲ŦX�u#B1 �v�B�u#B2 �v......�u#Bn �v���r              ���u<a src="�d��B1�d�������|">#B1</a>�v				
//				�е��Ӽh��@����
				int i;
				for(i=1;i<=replies_num;i++) {
					//�p�G�쥻��rep_cont�t���S�w���r��
					if(rep_cont.contains("#B"+i+" ")) {
						//�N�i��d�ߡA�Qtag���o�ӯd���A�u�ꪺ�d���s������A�ǳƶi�����

						rep_cont=rep_cont.replace("#B"+i+" ","<div class=oneReply>#B"+i+" </div>" );
//						rep_cont=rep_cont.replace("#B"+i+" ","<a href=\"https://www.google.com/\">#B"+i+" </a>" );	
					}
					
				}
//				String rep_contReg = "^B{1}[1-9]{1}[0-9]{0,} {1}$";
				
				if (rep_cont == null || rep_cont.trim().length() == 0) {
					errorMsgs.add("�d�����e: �ФŪť�");
				} 
//				else if(!rep_cont.trim().matches(rep_contReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("�d�����e: �����b10��10000�Ӧr����");
//	            }

				Integer likes = 0; //�g�ƹw�]�N�O0

				Integer rep_stat = 0;//�d���w�]�]�O0 ���

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
					req.setAttribute("article_replyVO", article_replyVO); // �t����J�榡���~��article_replyVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article_reply/addArticle_reply.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.�}�l�s�W���***************************************/
				System.out.println("�ڭn�s�W�o");
				//�s�W�@���d�����Article_reply
				Article_ReplyService article_replySvc = new Article_ReplyService();
				article_replyVO = article_replySvc.addArticle_Reply(art_no, mbr_no,rep_cont,rep_time,rep_stat,likes);
				//�s�W�@���d���ƶq��Article
//				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.plus_reply(art_no);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllArticle.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllArticle_Reply.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer art_rep_no = new Integer(req.getParameter("art_rep_no"));
				
				/***************************2.�}�l�R�����***************************************/
				Article_ReplyService article_replySvc = new Article_ReplyService();
				article_replySvc.deleteArticle_Reply(art_rep_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/article_reply/listAllArticle_Reply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/article_reply/listAllArticle_Reply.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("hide".equals(action)) { // �Ӧ۫e�x�A�ä��O�u���R���A�ӬO�N�d�����A�]�������
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer art_rep_no = new Integer(req.getParameter("art_rep_no").trim());
				Article_ReplyVO article_replyVO = new Article_ReplyVO();
				article_replyVO.setArt_rep_no(art_rep_no);
				System.out.println(art_rep_no);
				/***************************2.�}�l���ø��***************************************/
				Article_ReplyService article_replySvc = new Article_ReplyService();

				article_replySvc.hide(art_rep_no);

				/***************************3.���ç���,�ǳ����(Send the Success view)***********/			
				//�n�e�^��Ӫ�����
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
		
		
		
	}
}
