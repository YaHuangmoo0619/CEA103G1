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
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "不處分"); //審核紀錄
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				case 2:{ //僅刪文與寄信警告
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "僅刪文與寄信警告");//審核紀錄
					articleSvc.hide(art_no); //刪文(隱藏文章)
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				case 3:{ //刪文並禁發文章七天與寄信警告
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "刪文並禁發文章七天與寄信警告");//審核紀錄
					jedis.set("article_report:"+articleVO.getMbr_no()+":banned", "604800");
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 604800);
					articleSvc.hide(art_no); //刪文(隱藏文章)
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				case 4:{ //刪文並禁發文章十五天與寄信警告
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "刪文並禁發文章十五天與寄信警告");//審核紀錄
					jedis.set("article_report:"+articleVO.getMbr_no()+":banned", "1296000");
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 1296000);
					articleSvc.hide(art_no); //刪文(隱藏文章)
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				case 5:{ //刪文並禁發文章三十天與寄信警告
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "刪文並禁發文章三十天與寄信警告");//審核紀錄
					jedis.set("article_report:"+articleVO.getMbr_no()+":banned", "2592000"); 
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 2592000);
					articleSvc.hide(art_no); //刪文(隱藏文章)
					article_reportSvc.updateArticle_Report(article_reportVO.getArt_rpt_no(), article_reportVO.getArt_no(),article_reportVO.getMbr_no(), article_reportVO.getRpt_cont(), article_reportVO.getRpt_time(), 1);
					break;
				}
				}
				

				/***************************2.開始新增資料***************************************/

				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				jedis.close();
				
				String url = "/back-end/article_report/listAllArticle_Report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllArticle.jsp
				successView.forward(req, res);				
				
				
			
		}
		
        
        
        if ("judge_change".equals(action)) { 
			
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
				
				Integer judgement = new Integer(req.getParameter("judge_changeRadios").trim());
				
				Jedis jedis = new Jedis("localhost", 6379); // 建立redis實體
				jedis.auth("123456"); // 密碼
				jedis.select(5); // db05 檢舉專用
				
				
				switch(judgement) {
				case 1:{ //改為不處分 執行的動作:   
					jedis.del("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record"); //1. 更改審核紀錄(刪掉重創最快)
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "不處分");
					if(jedis.exists("article_report:"+articleVO.getMbr_no()+":banned")) {// 2.如果之前是被ban，要註銷
						jedis.del("article_report:"+articleVO.getMbr_no()+":banned");
						} 
					if(articleVO.getArt_stat()==1) { //3.如果該篇文章之前被隱藏了  要改回顯示
						articleSvc.updateArticle(articleVO.getArt_no(), articleVO.getBd_cl_no(), articleVO.getMbr_no(), articleVO.getArt_rel_time(), articleVO.getArt_title(), articleVO.getArt_cont(), articleVO.getLikes(), 0, articleVO.getReplies(), articleVO.getArt_first_img());
					}
					break;
				}
				case 2:{ //僅刪文與寄信警告             1. 更改審核紀錄(刪掉重創最快)  2.如果之前是被ban，要註銷   3.如果該篇文章之前被隱藏了  要改回顯示
					jedis.del("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record"); //1. 更改審核紀錄(刪掉重創最快)
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "僅刪文與寄信警告");
					if(jedis.exists("article_report:"+articleVO.getMbr_no()+":banned")) {// 2.如果之前是被ban，要註銷
						jedis.del("article_report:"+articleVO.getMbr_no()+":banned");
						} 
					if(articleVO.getArt_stat()==0) { //3.如果該篇文章之前是不處分的了  要隱藏文章
						articleSvc.updateArticle(articleVO.getArt_no(), articleVO.getBd_cl_no(), articleVO.getMbr_no(), articleVO.getArt_rel_time(), articleVO.getArt_title(), articleVO.getArt_cont(), articleVO.getLikes(), 1, articleVO.getReplies(), articleVO.getArt_first_img());
					}
					break;
				}
				case 3:{ //刪文並禁發文章七天與寄信警告
					jedis.del("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record"); //1. 更改審核紀錄(刪掉重創最快)
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "刪文並禁發文章七天與寄信警告");
					if(jedis.exists("article_report:"+articleVO.getMbr_no()+":banned")) {// 2.如果之前有被ban，先註銷
						jedis.del("article_report:"+articleVO.getMbr_no()+":banned");
						}
					jedis.set("article_report:"+articleVO.getMbr_no()+":banned", "604800"); //再設定新的ban
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 604800);
					if(articleVO.getArt_stat()==0) { //3.如果該篇文章之前是不處分的了  要隱藏文章
						articleSvc.updateArticle(articleVO.getArt_no(), articleVO.getBd_cl_no(), articleVO.getMbr_no(), articleVO.getArt_rel_time(), articleVO.getArt_title(), articleVO.getArt_cont(), articleVO.getLikes(), 1, articleVO.getReplies(), articleVO.getArt_first_img());
					}
					
					break;
				}
				case 4:{ //刪文並禁發文章十五天與寄信警告
					jedis.del("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record"); //1. 更改審核紀錄(刪掉重創最快)
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "刪文並禁發文章十五天與寄信警告");//審核紀錄
					if(jedis.exists("article_report:"+articleVO.getMbr_no()+":banned")) {// 2.如果之前有被ban，先註銷
						jedis.del("article_report:"+articleVO.getMbr_no()+":banned");
						}
					jedis.set("article_report:"+articleVO.getMbr_no()+":banned", "1296000");
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 1296000);
					if(articleVO.getArt_stat()==0) { //3.如果該篇文章之前是不處分的了  要隱藏文章
						articleSvc.updateArticle(articleVO.getArt_no(), articleVO.getBd_cl_no(), articleVO.getMbr_no(), articleVO.getArt_rel_time(), articleVO.getArt_title(), articleVO.getArt_cont(), articleVO.getLikes(), 1, articleVO.getReplies(), articleVO.getArt_first_img());
					}
					break;
				}
				case 5:{ //刪文並禁發文章三十天與寄信警告
					jedis.del("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record"); //1. 更改審核紀錄(刪掉重創最快)
					jedis.set("article_report:"+article_reportVO.getArt_rpt_no()+":judge_record", "刪文並禁發文章三十天與寄信警告");//審核紀錄
					if(jedis.exists("article_report:"+articleVO.getMbr_no()+":banned")) {// 2.如果之前有被ban，先註銷
						jedis.del("article_report:"+articleVO.getMbr_no()+":banned");
						}
					jedis.set("article_report:"+articleVO.getMbr_no()+":banned", "2592000"); 
					jedis.expire("article_report:"+articleVO.getMbr_no()+":banned", 2592000);
					if(articleVO.getArt_stat()==0) { //3.如果該篇文章之前是不處分的了  要隱藏文章
						articleSvc.updateArticle(articleVO.getArt_no(), articleVO.getBd_cl_no(), articleVO.getMbr_no(), articleVO.getArt_rel_time(), articleVO.getArt_title(), articleVO.getArt_cont(), articleVO.getLikes(), 1, articleVO.getReplies(), articleVO.getArt_first_img());
					}
					break;
				}
				}
				

				/***************************2.開始新增資料***************************************/

				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				jedis.close();
				
				String url = "/back-end/article_report/listAllArticle_Report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllArticle.jsp
				successView.forward(req, res);				
				
				
			
		}
		
		
		
	}
}
