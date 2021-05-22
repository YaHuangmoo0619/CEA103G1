package com.article_report.controller;

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
import com.article_report.model.Article_ReportService;
import com.article_report.model.Article_ReportVO;

import redis.clients.jedis.Jedis;

@WebServlet("/article_report/article_report.do")
public class Article_reportServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
	
		
		
		
		
		
//		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllArticle_Reply.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			String requestURL = req.getParameter("requestURL"); // ����listOneAriticle
//			System.out.println(requestURL);
//			try {
//				/***************************1.�����ШD�Ѽ�****************************************/
//				Integer art_rep_no = new Integer(req.getParameter("art_rep_no"));
//				
//				/***************************2.�}�l�d�߸��****************************************/
//				Article_ReplyService article_replySvc = new Article_ReplyService();
//				Article_ReplyVO article_replyVO = article_replySvc.getOneArticle_Reply(art_rep_no);
//								
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//				req.setAttribute("article_replyVO", article_replyVO);         // ��Ʈw���X��article_replyVO����,�s�Jreq
//				String url = "/front-end/article_reply/update_article_reply_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_article_reply_input.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(requestURL);
//				failureView.forward(req, res);
//			}
//		}
//		
//		


		
        if ("insert".equals(action)) { 
				System.out.println("�ڦ���");
				
				

				/***********************1.�����ШD�Ѽ� *************************/
				//���|�|���s��
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				
				//�Q���|�峹�s��
				Integer art_no = new Integer(req.getParameter("art_no").trim());
				
				//�Q���|��]
				String rpt_cont = req.getParameter("replyRadios");
				
				//���|�ɶ�
				Timestamp rpt_time = new Timestamp(System.currentTimeMillis());
				
				//�B�z���A�w�]��0 : ���f��
				Integer proc_stat = 0;
				
				Article_ReportVO article_reportVO = new Article_ReportVO();
				article_reportVO.setArt_no(art_no);
				article_reportVO.setMbr_no(mbr_no);
				article_reportVO.setProc_stat(proc_stat);
				article_reportVO.setRpt_cont(rpt_cont);
				article_reportVO.setRpt_time(rpt_time);
				
				/***************************2.�}�l�s�W���***************************************/
				Article_ReportService article_reportSvc = new Article_ReportService();
				article_reportVO = article_reportSvc.addArticle_Report(art_no, mbr_no, rpt_cont, rpt_time, proc_stat);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllArticle.jsp
				successView.forward(req, res);				
				
				
			
		}
		
		
        if ("judge".equals(action)) { 
				
				/***********************1.�����ШD�Ѽ� *************************/

				
				//�Q���|�峹�s��
				Integer art_no = new Integer(req.getParameter("art_no").trim());
				System.out.println("art_no:"+art_no);
				//���|�s��
				Integer art_rpt_no = new Integer(req.getParameter("art_rpt_no").trim());
				System.out.println("art_rpt_no:"+art_rpt_no);
				//�d�߸Ӥ峹�@��
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = new ArticleVO();
				articleVO = articleSvc.getOneArticle(art_no);
				int author = articleVO.getMbr_no();
				//�d�߸����|
				Article_ReportService article_reportSvc = new Article_ReportService();
				Article_ReportVO article_reportVO = new Article_ReportVO();
				article_reportVO = article_reportSvc.getOneArticle_Report(art_rpt_no);
				
				Integer judgement = new Integer(req.getParameter("judgeRadios").trim());
				
				Jedis jedis = new Jedis("localhost", 6379); // �إ�redis����
				jedis.auth("123456"); // �K�X
				jedis.select(5); // db05 ���|�M��
				
				
				switch(judgement) {
				case 1:{ //���B�� ���檺�ʧ@�N�u����f�֪��A�אּ�w�f��
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				case 2:{ //�ȧR��P�H�Hĵ�i
					articleSvc.hide(art_no); //�R��(���ä峹)
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				case 3:{ //�R��øT�o�峹�C�ѻP�H�Hĵ�i
					jedis.sadd("article_report:"+articleVO.getMbr_no()+":banned", "604800");
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 604800);
					articleSvc.hide(art_no); //�R��(���ä峹)
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				case 4:{ //�R��øT�o�峹�Q���ѻP�H�Hĵ�i
					jedis.sadd("article_report:"+articleVO.getMbr_no()+":banned", "1296000");
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 1296000);
					articleSvc.hide(art_no); //�R��(���ä峹)
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				case 5:{ //�R��øT�o�峹�T�Q�ѻP�H�Hĵ�i
					jedis.sadd("article_report:"+articleVO.getMbr_no()+":banned", "2592000"); 
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 2592000);
					articleSvc.hide(art_no); //�R��(���ä峹)
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				}
				

				/***************************2.�}�l�s�W���***************************************/

				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				String url = "/front-end/article/listAllArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllArticle.jsp
//				successView.forward(req, res);				
				
				jedis.close();
			
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
