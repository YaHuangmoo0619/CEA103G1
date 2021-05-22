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
		
		
	
		
		
		
		
		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllArticle_Reply.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			String requestURL = req.getParameter("requestURL"); // 接到listOneAriticle
//			System.out.println(requestURL);
//			try {
//				/***************************1.接收請求參數****************************************/
//				Integer art_rep_no = new Integer(req.getParameter("art_rep_no"));
//				
//				/***************************2.開始查詢資料****************************************/
//				Article_ReplyService article_replySvc = new Article_ReplyService();
//				Article_ReplyVO article_replyVO = article_replySvc.getOneArticle_Reply(art_rep_no);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("article_replyVO", article_replyVO);         // 資料庫取出的article_replyVO物件,存入req
//				String url = "/front-end/article_reply/update_article_reply_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_article_reply_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(requestURL);
//				failureView.forward(req, res);
//			}
//		}
//		
//		


		
        if ("insert".equals(action)) { 
				System.out.println("我有來");
				
				

				/***********************1.接收請求參數 *************************/
				//檢舉會員編號
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				
				//被檢舉文章編號
				Integer art_no = new Integer(req.getParameter("art_no").trim());
				
				//被檢舉原因
				String rpt_cont = req.getParameter("replyRadios");
				
				//檢舉時間
				Timestamp rpt_time = new Timestamp(System.currentTimeMillis());
				
				//處理狀態預設為0 : 未審核
				Integer proc_stat = 0;
				
				Article_ReportVO article_reportVO = new Article_ReportVO();
				article_reportVO.setArt_no(art_no);
				article_reportVO.setMbr_no(mbr_no);
				article_reportVO.setProc_stat(proc_stat);
				article_reportVO.setRpt_cont(rpt_cont);
				article_reportVO.setRpt_time(rpt_time);
				
				/***************************2.開始新增資料***************************************/
				Article_ReportService article_reportSvc = new Article_ReportService();
				article_reportVO = article_reportSvc.addArticle_Report(art_no, mbr_no, rpt_cont, rpt_time, proc_stat);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllArticle.jsp
				successView.forward(req, res);				
				
				
			
		}
		
		
        if ("judge".equals(action)) { 
				
				/***********************1.接收請求參數 *************************/

				
				//被檢舉文章編號
				Integer art_no = new Integer(req.getParameter("art_no").trim());
				System.out.println("art_no:"+art_no);
				//檢舉編號
				Integer art_rpt_no = new Integer(req.getParameter("art_rpt_no").trim());
				System.out.println("art_rpt_no:"+art_rpt_no);
				//查詢該文章作者
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = new ArticleVO();
				articleVO = articleSvc.getOneArticle(art_no);
				int author = articleVO.getMbr_no();
				//查詢該檢舉
				Article_ReportService article_reportSvc = new Article_ReportService();
				Article_ReportVO article_reportVO = new Article_ReportVO();
				article_reportVO = article_reportSvc.getOneArticle_Report(art_rpt_no);
				
				Integer judgement = new Integer(req.getParameter("judgeRadios").trim());
				
				Jedis jedis = new Jedis("localhost", 6379); // 建立redis實體
				jedis.auth("123456"); // 密碼
				jedis.select(5); // db05 檢舉專用
				
				
				switch(judgement) {
				case 1:{ //不處分 執行的動作就只有把審核狀態改為已審核
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				case 2:{ //僅刪文與寄信警告
					articleSvc.hide(art_no); //刪文(隱藏文章)
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				case 3:{ //刪文並禁發文章七天與寄信警告
					jedis.sadd("article_report:"+articleVO.getMbr_no()+":banned", "604800");
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 604800);
					articleSvc.hide(art_no); //刪文(隱藏文章)
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				case 4:{ //刪文並禁發文章十五天與寄信警告
					jedis.sadd("article_report:"+articleVO.getMbr_no()+":banned", "1296000");
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 1296000);
					articleSvc.hide(art_no); //刪文(隱藏文章)
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				case 5:{ //刪文並禁發文章三十天與寄信警告
					jedis.sadd("article_report:"+articleVO.getMbr_no()+":banned", "2592000"); 
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 2592000);
					articleSvc.hide(art_no); //刪文(隱藏文章)
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				}
				

				/***************************2.開始新增資料***************************************/

				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/front-end/article/listAllArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllArticle.jsp
//				successView.forward(req, res);				
				
				jedis.close();
			
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
