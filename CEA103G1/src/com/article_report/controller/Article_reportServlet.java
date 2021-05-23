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
		
		
	


		
        if ("insert".equals(action)) { 
				
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
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "���B��"); //�f�֬���
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				case 2:{ //�ȧR��P�H�Hĵ�i
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "�ȧR��P�H�Hĵ�i");//�f�֬���
					articleSvc.hide(art_no); //�R��(���ä峹)
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				case 3:{ //�R��øT�o�峹�C�ѻP�H�Hĵ�i
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "�R��øT�o�峹�C�ѻP�H�Hĵ�i");//�f�֬���
					jedis.set("article_report:"+articleVO.getMbr_no()+":banned", "604800");
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 604800);
					articleSvc.hide(art_no); //�R��(���ä峹)
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				case 4:{ //�R��øT�o�峹�Q���ѻP�H�Hĵ�i
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "�R��øT�o�峹�Q���ѻP�H�Hĵ�i");//�f�֬���
					jedis.set("article_report:"+articleVO.getMbr_no()+":banned", "1296000");
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 1296000);
					articleSvc.hide(art_no); //�R��(���ä峹)
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				case 5:{ //�R��øT�o�峹�T�Q�ѻP�H�Hĵ�i
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "�R��øT�o�峹�T�Q�ѻP�H�Hĵ�i");//�f�֬���
					jedis.set("article_report:"+articleVO.getMbr_no()+":banned", "2592000"); 
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 2592000);
					articleSvc.hide(art_no); //�R��(���ä峹)
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				}
				

				/***************************2.�}�l�s�W���***************************************/

				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				jedis.close();
				
				String url = "/back-end/article_report/listAllArticle_Report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllArticle.jsp
				successView.forward(req, res);				
				
				
			
		}
		
        
        
        if ("judge_change".equals(action)) { 
			
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
				
				Integer judgement = new Integer(req.getParameter("judge_changeRadios").trim());
				
				Jedis jedis = new Jedis("localhost", 6379); // �إ�redis����
				jedis.auth("123456"); // �K�X
				jedis.select(5); // db05 ���|�M��
				
				
				switch(judgement) {
				case 1:{ //�אּ���B�� ���檺�ʧ@:   
					jedis.del("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record"); //1. ���f�֬���(�R�����г̧�)
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "���B��");
					if(jedis.exists("article_report:"+articleVO.getMbr_no()+":banned")) {// 2.�p�G���e�O�Qban�A�n���P
						jedis.del("article_report:"+articleVO.getMbr_no()+":banned");
						} 
					if(articleVO.getArt_stat()==1) { //3.�p�G�ӽg�峹���e�Q���äF  �n��^���
						articleSvc.updateArticle(articleVO.getArt_no(), articleVO.getBd_cl_no(), articleVO.getMbr_no(), articleVO.getArt_rel_time(), articleVO.getArt_title(), articleVO.getArt_cont(), articleVO.getLikes(), 0, articleVO.getReplies(), articleVO.getArt_first_img());
					}
					break;
				}
				case 2:{ //�ȧR��P�H�Hĵ�i             1. ���f�֬���(�R�����г̧�)  2.�p�G���e�O�Qban�A�n���P   3.�p�G�ӽg�峹���e�Q���äF  �n��^���
					jedis.del("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record"); //1. ���f�֬���(�R�����г̧�)
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "�ȧR��P�H�Hĵ�i");
					if(jedis.exists("article_report:"+articleVO.getMbr_no()+":banned")) {// 2.�p�G���e�O�Qban�A�n���P
						jedis.del("article_report:"+articleVO.getMbr_no()+":banned");
						} 
					if(articleVO.getArt_stat()==0) { //3.�p�G�ӽg�峹���e�O���B�����F  �n���ä峹
						articleSvc.updateArticle(articleVO.getArt_no(), articleVO.getBd_cl_no(), articleVO.getMbr_no(), articleVO.getArt_rel_time(), articleVO.getArt_title(), articleVO.getArt_cont(), articleVO.getLikes(), 1, articleVO.getReplies(), articleVO.getArt_first_img());
					}
					break;
				}
				case 3:{ //�R��øT�o�峹�C�ѻP�H�Hĵ�i
					jedis.del("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record"); //1. ���f�֬���(�R�����г̧�)
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "�R��øT�o�峹�C�ѻP�H�Hĵ�i");
					if(jedis.exists("article_report:"+articleVO.getMbr_no()+":banned")) {// 2.�p�G���e���Qban�A�����P
						jedis.del("article_report:"+articleVO.getMbr_no()+":banned");
						}
					jedis.set("article_report:"+articleVO.getMbr_no()+":banned", "604800"); //�A�]�w�s��ban
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 604800);
					if(articleVO.getArt_stat()==0) { //3.�p�G�ӽg�峹���e�O���B�����F  �n���ä峹
						articleSvc.updateArticle(articleVO.getArt_no(), articleVO.getBd_cl_no(), articleVO.getMbr_no(), articleVO.getArt_rel_time(), articleVO.getArt_title(), articleVO.getArt_cont(), articleVO.getLikes(), 1, articleVO.getReplies(), articleVO.getArt_first_img());
					}
					
					break;
				}
				case 4:{ //�R��øT�o�峹�Q���ѻP�H�Hĵ�i
					jedis.del("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record"); //1. ���f�֬���(�R�����г̧�)
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "�R��øT�o�峹�Q���ѻP�H�Hĵ�i");//�f�֬���
					if(jedis.exists("article_report:"+articleVO.getMbr_no()+":banned")) {// 2.�p�G���e���Qban�A�����P
						jedis.del("article_report:"+articleVO.getMbr_no()+":banned");
						}
					jedis.set("article_report:"+articleVO.getMbr_no()+":banned", "1296000");
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 1296000);
					if(articleVO.getArt_stat()==0) { //3.�p�G�ӽg�峹���e�O���B�����F  �n���ä峹
						articleSvc.updateArticle(articleVO.getArt_no(), articleVO.getBd_cl_no(), articleVO.getMbr_no(), articleVO.getArt_rel_time(), articleVO.getArt_title(), articleVO.getArt_cont(), articleVO.getLikes(), 1, articleVO.getReplies(), articleVO.getArt_first_img());
					}
					break;
				}
				case 5:{ //�R��øT�o�峹�T�Q�ѻP�H�Hĵ�i
					jedis.del("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record"); //1. ���f�֬���(�R�����г̧�)
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "�R��øT�o�峹�T�Q�ѻP�H�Hĵ�i");//�f�֬���
					if(jedis.exists("article_report:"+articleVO.getMbr_no()+":banned")) {// 2.�p�G���e���Qban�A�����P
						jedis.del("article_report:"+articleVO.getMbr_no()+":banned");
						}
					jedis.set("article_report:"+articleVO.getMbr_no()+":banned", "2592000"); 
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 2592000);
					if(articleVO.getArt_stat()==0) { //3.�p�G�ӽg�峹���e�O���B�����F  �n���ä峹
						articleSvc.updateArticle(articleVO.getArt_no(), articleVO.getBd_cl_no(), articleVO.getMbr_no(), articleVO.getArt_rel_time(), articleVO.getArt_title(), articleVO.getArt_cont(), articleVO.getLikes(), 1, articleVO.getReplies(), articleVO.getArt_first_img());
					}
					break;
				}
				}
				

				/***************************2.�}�l�s�W���***************************************/

				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				jedis.close();
				
				String url = "/back-end/article_report/listAllArticle_Report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllArticle.jsp
				successView.forward(req, res);				
				
				
			
		}
		
		
		
	}
}
