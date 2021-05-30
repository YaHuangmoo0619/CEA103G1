package com.follow.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.follow.model.FollowService;
import com.follow.model.FollowVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

import redis.clients.jedis.Jedis;


@WebServlet("/follow/follow.do")

public class FollowServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	
		if ("getbyflwed_no".equals(action)) { // 來自select_page.jsp的請求，列出這個人有哪些粉絲

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("flwed_mbr_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				

				Integer flwed_mbr_no = null;
				try {
					flwed_mbr_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FollowService followSvc = new FollowService();
				List<FollowVO> followVO = followSvc.findbyflwed(flwed_mbr_no);

				if (followVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("followVO", followVO); // 資料庫取出的followVO物件,存入req
				String url = "/back-end/follow/listOneByFlwed.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneByFlwed.jsp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/follow/select_page.jsp");
				failureView.forward(req, res);
			}
		}		
	
	
		
		
		if ("getbyflw_no".equals(action)) { // 來自select_page.jsp的請求，列出這個人是哪些人的粉絲

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("flw_mbr_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				

				Integer flw_mbr_no = null;
				try {
					flw_mbr_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				System.out.println(flw_mbr_no);			
				/***************************2.開始查詢資料*****************************************/
				FollowService followSvc = new FollowService();
				List<FollowVO> followVO = followSvc.findbyflw(flw_mbr_no);
				if (followVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("followVO", followVO); // 資料庫取出的followVO物件,存入req
				String url = "/back-end/follow/listOneByFlw.jsp";
				System.out.println("hello");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneByFlw.jsp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/follow/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	
		
		
		
		
		
		if ("getProfile".equals(action)) { 
			// 來自listOneArticle的請求，列出這個人的以下資訊
			//1. 有多少粉絲，那些粉絲是誰  2. 追蹤多少人，追蹤的人是哪些人  3.貼文數 4.該會員與該發文者的粉絲、追蹤者的關係


			//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String str = req.getParameter("mbr_no");
				Integer mbr_no = new Integer(str);
				System.out.println("mbr_no:"+mbr_no);
				
				/*
				 從某篇文章點發文者
				傳遞會員的Mbr_no進入Servlet中
				從該會員的Mbr_no取得該會員有追蹤的人
				並且跟發文者的粉絲、追蹤者進行關係比對，存成兩個數字的list
				0代表沒追蹤對方 1:代表有追蹤對方
				在listOneProfile的for迴圈中
				如果是0  就顯示「追蹤」
				如果是1  就顯示「追蹤中」*/
				
//				HttpSession session = req.getSession();  //for Session
//				String str2 = (String)session.getAttribute("mbr_no");
//				Integer mbr_no_self = new Integer(str2);
				String mbr_no_mine = req.getParameter("mbr_no_mine");
				Integer mbr_no_self = new Integer(mbr_no_mine);
				/***************************2.開始查詢資料*****************************************/
				
				ArticleService articleSvc = new ArticleService();
				List<ArticleVO> articleVO = articleSvc.getByMbr_no(mbr_no); //找到某個人發的所有文章
				int article_num = articleVO.size();//某人發的文章數量
				
				FollowService followSvc = new FollowService();
				List<FollowVO> followVO_fans = followSvc.findbyflwed(mbr_no); //找到某個人有哪些粉絲
				int fans_num = followVO_fans.size(); //粉絲數量有多少人
				System.out.println("fans_num:"+fans_num);
				List<FollowVO> followVO_follows = followSvc.findbyflw(mbr_no); //找到某個人追蹤哪些人
				int follows_num = followVO_follows.size(); //共追蹤多少人
				System.out.println("follows_num:"+follows_num);
				
				
				List<FollowVO> followVO_mine = followSvc.findbyflw(mbr_no_self); //取得我追蹤那些人的VO
				List<Integer>  followVO_mine1 = new ArrayList<Integer>(); //VO轉成純數字的arrayList儲存  放入req
			    for(FollowVO element : followVO_mine) {
			        followVO_mine1.add(element.getFlwed_mbr_no());
			    }
			    
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/follow/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO);      //某人發的文章
				req.setAttribute("article_num",article_num);   //發的文章數量
				
				req.setAttribute("followVO_fans", followVO_fans); //某人的粉絲
				req.setAttribute("fans_num",fans_num);            //粉絲數量
				
				req.setAttribute("followVO_follows", followVO_follows); //某人的追蹤者
				req.setAttribute("follows_num",follows_num); //追蹤數量
				
				req.setAttribute("followVO_mine", followVO_mine1); //我追蹤的人
				
				req.setAttribute("mbr_no",mbr_no);
				String url = "/front-end/follow/listOneProfile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProfile.jsp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/follow/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("getFollowers".equals(action)) { 
			// 來自listOneProfile的請求，列出這個人的以下資訊 1. 有多少粉絲，那些粉絲是誰
			

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mbr_no");
				Integer mbr_no = new Integer(str);
				System.out.println("mbr_no:"+mbr_no);
				
				/***************************2.開始查詢資料*****************************************/
         		FollowService followSvc = new FollowService();
				List<FollowVO> followVO_fans = followSvc.findbyflwed(mbr_no); //找到某個人有哪些粉絲
				int fans_num = followVO_fans.size(); //粉絲數量有多少人
				System.out.println("fans_num:"+fans_num);
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/			
				req.setAttribute("followVO_fans", followVO_fans); //某人的粉絲
				req.setAttribute("fans_num",fans_num);            //粉絲數量

				req.setAttribute("mbr_no",mbr_no);
				String url = "/front-end/follow/listOneFollowers.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProfile.jsp.jsp
				successView.forward(req, res);
		}
		
		
		
		
		
		if ("getFollowing".equals(action)) { 
			// 來自listOneProfile的請求，列出這個人的以下資訊 1.追蹤多少人，追蹤的人是哪些人

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mbr_no");
				Integer mbr_no = new Integer(str);
				System.out.println("mbr_no:"+mbr_no);
				
				/***************************2.開始查詢資料*****************************************/
				FollowService followSvc = new FollowService();

				List<FollowVO> followVO_follows = followSvc.findbyflw(mbr_no); //找到某個人追蹤哪些人
				int follows_num = followVO_follows.size(); //共追蹤多少人

				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				req.setAttribute("followVO_follows", followVO_follows); //某人的追蹤者
				req.setAttribute("follows_num",follows_num); //追蹤數量
				
				req.setAttribute("mbr_no",mbr_no);
				String url = "/front-end/follow/listOneFollowing.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProfile.jsp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/

		}
	
		
		
		if ("add_follow".equals(action)) { // 新增一筆訂閱關係
				
				String str = new String(req.getParameter("flwed_mbr_no"));
				Integer flwed_mbr_no = new Integer(str);
				System.out.println("flwed_mbr_no:"+flwed_mbr_no);
				String str2 = new String(req.getParameter("flw_mbr_no"));
				Integer flw_mbr_no = new Integer(str2);
				System.out.println("flw_mbr_no1:"+flw_mbr_no);
				FollowVO followVO = new FollowVO();
				followVO.setFlwed_mbr_no(flwed_mbr_no);
				followVO.setFlw_mbr_no(flw_mbr_no);
				/***************************2.開始新增資料*****************************************/
				FollowService followSvc = new FollowService();
				followVO = followSvc.addFollow(flwed_mbr_no, flw_mbr_no);

		}
		
		
		if ("add_follow1".equals(action)) { // 新增一筆訂閱關係
			
			String str = new String(req.getParameter("flwed_mbr_no")); //目前取得是帳號: lavida
			MemberService memberSvc = new MemberService();
			List<MemberVO> memberVO_all = memberSvc.getAll(); //全部的會員
			int flwed_mbr_no=0 ; //被追蹤的人的號碼
			
			for(MemberVO vo_count : memberVO_all) {
			if(vo_count.getAcc()==str) {
				flwed_mbr_no=vo_count.getMbr_no();
				}	
			}
			
			MemberVO memberVO = new MemberVO();
			memberVO = memberSvc.getOneMember(flwed_mbr_no);
			
			
			System.out.println("flwed_mbr_no:"+flwed_mbr_no);
			
			
			String str2 = new String(req.getParameter("flw_mbr_no"));
			Integer flw_mbr_no = new Integer(str2);
			System.out.println("flw_mbr_no:"+flw_mbr_no);
			FollowVO followVO = new FollowVO();
			followVO.setFlwed_mbr_no(flwed_mbr_no);
			followVO.setFlw_mbr_no(flw_mbr_no);
			/***************************2.開始新增資料*****************************************/
			FollowService followSvc = new FollowService();
			followVO = followSvc.addFollow(flwed_mbr_no, flw_mbr_no);

	}
		
		
		if ("cancel_follow".equals(action)) { // 新增一筆訂閱關係
			
			String str = new String(req.getParameter("flwed_mbr_no"));
			Integer flwed_mbr_no = new Integer(str);
			System.out.println("flwed_mbr_no:"+flwed_mbr_no);
			String str2 = new String(req.getParameter("flw_mbr_no"));
			Integer flw_mbr_no = new Integer(str2);
			System.out.println("flw_mbr_no:"+flw_mbr_no);
			/***************************2.開始新增資料*****************************************/
			FollowService followSvc = new FollowService();
			followSvc.deleteFollow(flwed_mbr_no, flw_mbr_no);

	}
		
		
		
		if ("modify_profile".equals(action)) { 
				

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mbr_no = req.getParameter("mbr_no");
				System.out.println("mbr_no:"+mbr_no);
				String new_profile = req.getParameter("new_profile");
				
				System.out.println("new_profile:"+new_profile);
				
				/***************************2.開始新增資料*****************************************/
				Jedis jedis = new Jedis("localhost", 6379);
				jedis.auth("123456");
				jedis.select(6);
				jedis.set("user:"+mbr_no+":profile", new_profile);
				jedis.close();// Redis新增結束				
				

		}
		
		
	}	
	
}
