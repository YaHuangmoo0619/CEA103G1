package com.article.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.json.JSONObject;

import com.article.model.ArticleDAO;
import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

@WebServlet("/article/article.do")
public class ArticleServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		
		//for session
//		HttpSession session =  req.getSession(); //建立session
//		session.getAttribute("memberVO"); //拿到該會員的會員資料

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str2 = req.getParameter("art_no");
				if (str2 == null || (str2.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer art_no = null;
				try {
					art_no = new Integer(str2);
				} catch (Exception e) {
					errorMsgs.add("文章編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 2.開始查詢SQL資料
				 *****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(art_no);
				if (articleVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req
				String url = "/back-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneArticle.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOneArticle_ByBoard_Clss_For_Display".equals(action)) { // 來自front-end listAllArticle.jsp的請求

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("bd_cl_no"); // 不用錯誤驗證 因為是直接跳轉 不是由使用者輸入
			Integer bd_cl_no = new Integer(str);
			System.out.println(bd_cl_no);

			/*************************** 2.開始查詢資料 *****************************************/
			ArticleService articleSvc = new ArticleService();
			List<ArticleVO> articleVO = articleSvc.getByBoard_Class_Front(bd_cl_no);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req
			String url = "/front-end/article/listOneBoard_ClassArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBoard_ClassArticle.jsp
			successView.forward(req, res);

		}

		if ("getOneArticle_ByBoard_Clss_For_Display".equals(action)) { // 來自back-end listAllArticle.jsp的請求

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("bd_cl_no"); // 不用錯誤驗證 因為是直接跳轉 不是由使用者輸入
			Integer bd_cl_no = new Integer(str);
			System.out.println(bd_cl_no);

			/*************************** 2.開始查詢資料 *****************************************/
			ArticleService articleSvc = new ArticleService();
			List<ArticleVO> articleVO = articleSvc.getByBoard_Class_Back(bd_cl_no);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req
			String url = "/back-end/article/listOneBoard_ClassArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBoard_ClassArticle.jsp
			successView.forward(req, res);

		}

		if ("getArticlesByTagFor_Display".equals(action)) { // 來自front-end listOneTagArticles.jsp的請求
			System.out.println(req.getParameter("tag"));

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Jedis jedis = new Jedis("localhost", 6379); // 初始化redis
			jedis.auth("123456");
			jedis.select(6); // 選擇6號 因資料儲存在db06

			/***************************
			 * 2.Redis開始查詢資料
			 *****************************************/
			Set<String> tagged_article_list = jedis.smembers("tag:" + req.getParameter("tag") + ":posts"); // 將所有被標註此tag的文章編號存入一個set
			jedis.close(); // 關閉
			System.out.println("hello");
			/***************************
			 * 3.MySQL開始查詢資料
			 *****************************************/
			List<ArticleVO> articleVO = new ArrayList<>(); // 建一個文章list
			ArticleService articleSvc = new ArticleService(); // 建一個service
			System.out.println("hello2");
			for (String str : tagged_article_list) { // 將所有被tag到的文章vo 放入文章list中
				int i = Integer.parseInt(str);
				articleVO.add(articleSvc.getOneArticle(i));
			}

			System.out.println("hello3");
			/*************************** 4.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("tag", req.getParameter("tag"));
			req.setAttribute("articleVO", articleVO); // 資料庫取出的List<ArticleVO> articleVO物件,存入req
			String url = "/front-end/article/listOneTagArticles.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneTagArticles.jsp
			successView.forward(req, res);

		}

	

		if ("getOne_For_Update".equals(action)) { // 來自listAllArticle.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer art_no = new Integer(req.getParameter("art_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(art_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req
				String url = "/front-end/article/update_article_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_article_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}


		
		
		
		if ("update".equals(action)) { // 來自前端addArticle.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

//				String[] values = req.getParameterValues("tags");// 取得所有勾選的標籤
				
				Integer art_no  = new Integer(req.getParameter("art_no").trim());
				Integer bd_cl_no  = new Integer(req.getParameter("bd_cl_no").trim());
	

				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());


				String art_title = req.getParameter("art_title");
				if (art_title == null || art_title.trim().length() == 0) {
					errorMsgs.add("文章標題: 請勿空白");
				} 

				Timestamp art_rel_time = new Timestamp(System.currentTimeMillis());
				art_rel_time = Timestamp.valueOf(req.getParameter("art_rel_time"));

				String art_cont = req.getParameter("art_cont");

				if (art_cont == null || art_cont.trim().length() == 0) {
					errorMsgs.add("文章內容: 請勿空白");
				}
				
				
				String reg_split_p = "(?<=[<][/][p][>])"; //先將每段p分開
				String split_array[] = art_cont.split(reg_split_p);
				int i = 0;
				String save_to_redis = "";
				for(i=0;i<split_array.length;i++) {
					if(split_array[i].contains("img src")==false){ //如果不含圖
						split_array[i]=split_array[i].replaceAll("[<][p][>]",""); //去掉網頁元素符號
						split_array[i]=split_array[i].replaceAll("[<][/][p][>]","");
						System.out.println("測試中"+split_array[i]); //印出測試
						save_to_redis = save_to_redis+split_array[i]; //就串接就加入其中
					}
				}
				if(save_to_redis.length()>=30) { //如果不到30個字，不動作，超過30個字就截斷
					save_to_redis=save_to_redis.substring(0, 30);	
				}
				


				Integer likes =  new Integer(req.getParameter("likes").trim());
				Integer art_stat =  new Integer(req.getParameter("art_stat").trim());
				Integer replies = new Integer(req.getParameter("replies").trim());
				
				
				ArticleVO articleVO = new ArticleVO(); // 要新增的文章
				articleVO.setBd_cl_no(bd_cl_no);
				articleVO.setMbr_no(mbr_no);
				articleVO.setArt_rel_time(art_rel_time);
				articleVO.setArt_title(art_title);
				articleVO.setArt_cont(art_cont);
				articleVO.setLikes(likes);
				articleVO.setArt_stat(art_stat);
				articleVO.setReplies(replies);
				
				int begin;
				int end;
				String art_first_img;
				String reg = "[w][i][d][t][h][:] {1}[1-9]{1}[0-9]{1,}[p][x]";
				if(art_cont.indexOf("<p><img")>=0) {
					begin = art_cont.indexOf("<p><img");
					end   = art_cont.indexOf("\">",begin)+2;
					art_first_img = art_cont.substring(begin, end);
					art_first_img = art_first_img.replaceAll(reg, "width: 200px");
					String end_p = "</p>";
					art_first_img = art_first_img + end_p;
					articleVO.setArt_first_img(art_first_img);
					System.out.println("圖片的base64"+art_first_img);
				}else {
					art_first_img = "";
					articleVO.setArt_first_img(art_first_img);
//					System.out.println("我沒圖");
				}
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的articleVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/

				ArticleService articleSvc = new ArticleService();

//				Jedis jedis = new Jedis("localhost", 6379);
//				jedis.auth("123456");
//				jedis.select(6);
				
//				for (String str : values) {// Redis新增開始
//					System.out.println(str); // 印出測試
//
//					jedis.sadd("post:" + no + ":tags", str);
//				}
//				jedis.close();// Redis新增結束
				
				Jedis jedis = new Jedis("localhost", 6379); 
				jedis.auth("123456");
				jedis.select(6);
				jedis.del("post:"+art_no+":art_simple_cont"); //移除舊的開頭內容
				jedis.set("post:"+art_no+":art_simple_cont", save_to_redis); //設定新的
				jedis.close();
				System.out.println("最後結果:"+save_to_redis);//最後結果，準備加入redis資料庫
				
							
				articleVO = articleSvc.updateArticle(art_no, bd_cl_no, mbr_no, art_rel_time, art_title, art_cont, likes, art_stat, replies, art_first_img);
		
				

				
				/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllArticle.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/update_article_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		

		if ("plus_like".equals(action)) { // 來自liesOneArticle.jsp的請求 幫這篇文章讚數+1

			/*************************** 1.接收請求參數 **********************/
			Integer art_no = new Integer(req.getParameter("art_no").trim());
			ArticleVO articleVO = new ArticleVO();
			articleVO.setArt_no(art_no);
			/*************************** 2.開始修改資料 *****************************************/
			ArticleService articleSvc = new ArticleService();
			articleVO = articleSvc.plus_like(art_no);

		}

		if ("minus_like".equals(action)) { // 來自liesOneArticle.jsp的請求 幫這篇文章讚數+1

			/*************************** 1.接收請求參數 **********************/
			Integer art_no = new Integer(req.getParameter("art_no").trim());
			ArticleVO articleVO = new ArticleVO();
			articleVO.setArt_no(art_no);
			/*************************** 2.開始修改資料 *****************************************/
			ArticleService articleSvc = new ArticleService();
			articleVO = articleSvc.minus_like(art_no);

		}

		if ("insert".equals(action)) { // 來自前端addArticle.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

//				String[] values = req.getParameterValues("tags");// 取得所有勾選的標籤

				Integer bd_cl_no = null;
				try {
					bd_cl_no = new Integer(req.getParameter("bd_cl_no").trim());
				} catch (NumberFormatException e) {
					bd_cl_no = 0;
					errorMsgs.add("看板編號請填數字.");
				}

				Integer mbr_no = null;
				try {
					mbr_no = new Integer(req.getParameter("mbr_no").trim());
				} catch (NumberFormatException e) {
					mbr_no = 0;
					errorMsgs.add("會員編號請填數字.");
				}

				String art_title = req.getParameter("art_title");
				String art_titleReg = "^.{2,30}$";
				if (art_title == null || art_title.trim().length() == 0) {
					errorMsgs.add("文章標題: 請勿空白");
				} else if (!art_title.trim().matches(art_titleReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("文章標題: 長度必須在2到30之間");
				}

				Timestamp art_rel_time = new Timestamp(System.currentTimeMillis());

				String art_cont = req.getParameter("art_cont");
//				String art_contReg = "^.{10,1000000}$";
//				System.out.println(art_cont);
				if (art_cont == null || art_cont.trim().length() == 0) {
					errorMsgs.add("文章內容: 請勿空白");
				}
				
				
				String reg_split_p = "(?<=[<][/][p][>])"; //先將每段p分開
				String split_array[] = art_cont.split(reg_split_p);
				int i = 0;
				String save_to_redis = "";
				for(i=0;i<split_array.length;i++) {
					if(split_array[i].contains("img src")==false){ //如果不含圖
						split_array[i]=split_array[i].replaceAll("[<][p][>]",""); //去掉網頁元素符號
						split_array[i]=split_array[i].replaceAll("[<][/][p][>]","");
						System.out.println("測試中"+split_array[i]); //印出測試
						save_to_redis = save_to_redis+split_array[i]; //就串接就加入其中
					}
				}
				if(save_to_redis.length()>=30) { //如果不到30個字，不動作，超過30個字就截斷
					save_to_redis=save_to_redis.substring(0, 30);	
				}
				


		
				Integer likes = 0;
				Integer art_stat = 0;
				Integer replies = 0;
				ArticleVO articleVO = new ArticleVO(); // 要新增的文章
				articleVO.setBd_cl_no(bd_cl_no);
				articleVO.setMbr_no(mbr_no);
				articleVO.setArt_rel_time(art_rel_time);
				articleVO.setArt_title(art_title);
				articleVO.setArt_cont(art_cont);
				articleVO.setLikes(likes);
				articleVO.setArt_stat(art_stat);
				articleVO.setReplies(replies);
				
				int begin;
				int end;
				String art_first_img;
				String reg = "[w][i][d][t][h][:] {1}[1-9]{1}[0-9]{1,}[p][x]";
				if(art_cont.indexOf("<p><img")>=0) {
					begin = art_cont.indexOf("<p><img");
					end   = art_cont.indexOf("\">",begin)+2;
					art_first_img = art_cont.substring(begin, end);
					String end_p = "</p>";
					art_first_img = art_first_img + end_p;
					art_first_img = art_first_img.replaceAll(reg, "width: 200px");
					articleVO.setArt_first_img(art_first_img);
					System.out.println("圖片的base64"+art_first_img);
				}else {
					art_first_img = null;
					articleVO.setArt_first_img(art_first_img);
//					System.out.println("我沒圖");
				}
				
				ArticleVO articleVO2 = new ArticleVO(); // 查詢最後一筆文章的號碼

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的articleVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				/*************************** 2.開始新增資料 ***************************************/

				ArticleService articleSvc = new ArticleService();
				articleVO2 = articleSvc.findLast();
				Integer no = articleVO2.getArt_no() + 1; // 取得最後一筆文章+1 即目前所要新增文章的文章號碼
				System.out.println("no:" + no); // 印出測試
				
//				Jedis jedis = new Jedis("localhost", 6379);
//				jedis.auth("123456");
//				jedis.select(6);
				
//				for (String str : values) {// Redis新增開始
//					System.out.println(str); // 印出測試
//
//					jedis.sadd("post:" + no + ":tags", str);
//				}
//				jedis.close();// Redis新增結束
				
				Jedis jedis = new Jedis("localhost", 6379); 
				jedis.auth("123456");
				jedis.select(6);
				jedis.set("post:"+no+":art_simple_cont", save_to_redis);
				jedis.close();
				System.out.println("最後結果:"+save_to_redis);//最後結果，準備加入redis資料庫
				
							
				articleVO = articleSvc.addArticle(bd_cl_no, mbr_no, art_rel_time, art_title, art_cont, likes, art_stat, replies, art_first_img);
				
				
				//新增完文章如果發文看板符合，就開始增加經驗值
				if(bd_cl_no==1 || bd_cl_no==3 || bd_cl_no ==9) { //露營知識分享、露營日記、部落客分享
					MemberService memberSvc = new MemberService();
					MemberVO memberVO = memberSvc.getOneMember(mbr_no);
					int exp_now = memberVO.getExp();
					int exp_plus = exp_now+10;
					int rank_now = memberVO.getRank_no();
					switch(exp_now) {
					case 90:
						memberVO.setExp(exp_plus); //經驗加10
						memberVO.setRank_no(rank_now+1); //等級加1
						memberSvc.updateMember(memberVO); //更新資料
						break;
					case 190:
						memberVO.setExp(exp_plus); //經驗加10
						memberVO.setRank_no(rank_now+1); //等級加1
						memberSvc.updateMember(memberVO); //更新資料
						break;
					case 290:
						memberVO.setExp(exp_plus); //經驗加10
						memberVO.setRank_no(rank_now+1); //等級加1
						memberSvc.updateMember(memberVO); //更新資料
						break;
					case 390:
						memberVO.setExp(exp_plus); //經驗加10
						memberVO.setRank_no(rank_now+1); //等級加1
						memberSvc.updateMember(memberVO); //更新資料
						break;
					default:
						memberVO.setExp(exp_plus); //經驗加10
						memberSvc.updateMember(memberVO); //更新資料
					}
					
				}
				

				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				//雅凰加的，為了推播通知
				req.setAttribute("articleVO", articleVO);
				//雅凰加的，為了推播通知
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllArticle.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllArticle.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer art_no = new Integer(req.getParameter("art_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.deleteArticle(art_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}

		if ("hide".equals(action)) { // 來自前台，並不是真的刪除，而是將文章狀態設為不顯示

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer art_no = new Integer(req.getParameter("art_no").trim());
				ArticleVO articleVO = new ArticleVO();
				articleVO.setArt_no(art_no);
				System.out.println("1我已來到此處artno為" + art_no);
				/*************************** 2.開始隱藏資料 ***************************************/
				ArticleService articleSvc = new ArticleService();
				System.out.println("2我已來到此處artno為" + art_no);
				articleSvc.hide(art_no);
				System.out.println("3我已來到此處artno為" + art_no);
				/*************************** 3.隱藏完成,準備轉交(Send the Success view) ***********/
				System.out.println("4我已來到此處artno為" + art_no);
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("隱藏資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_From".equals(action)) { // 列出某文章
			System.out.println("I'm getOne_From");
			try {
				// Retrieve form parameters.
				Integer art_no = new Integer(req.getParameter("art_no"));
				com.article.model.ArticleDAO dao = new com.article.model.ArticleDAO();
				ArticleVO articleVO = dao.findByPrimaryKey(art_no);

				req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req

				// 取出的articleVO送給listOneEmp.jsp
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/article/listOneArticle.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("getOne_From2".equals(action)) { // 回到論壇首頁的燈箱

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
				Set<String> tag_list = jedis.smembers("post:" + req.getParameter("art_no") + ":tags");

				jedis.close();

//
//				    for(String element : tag_list) {
//				        System.out.println(element);
//				    }
//
				req.setAttribute("tag_list", tag_list); // Redis資料庫取出的tag_list物件,存入req

				// Retrieve form parameters.
				Integer art_no = new Integer(req.getParameter("art_no"));

				ArticleDAO dao = new ArticleDAO();
				ArticleVO articleVO = dao.findByPrimaryKey(art_no);

				req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req

				// Bootstrap_modal
				boolean openModal = true;
				System.out.println(openModal);
				req.setAttribute("openModal", openModal);

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		
		if ("getOne_From3".equals(action)) { // 回到看板首頁的燈箱

			try {
				// Retrieve form parameters.
				Integer art_no = new Integer(req.getParameter("art_no"));
				Integer bd_cl_no = new Integer(req.getParameter("bd_cl_no"));

				ArticleDAO dao = new ArticleDAO();
				ArticleVO articleVO = dao.findByPrimaryKey(art_no);

				req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req
				req.setAttribute("bd_cl_no", articleVO.getBd_cl_no());
				// Bootstrap_modal
				boolean openModal = true;
				System.out.println(openModal);
				req.setAttribute("openModal", openModal);

				// 取出的articleVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/article/listOneBoard_ClassArticle.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		
		
		
		if ("getOne_From4".equals(action)) { // 回到論壇首頁的燈箱

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
				Set<String> tag_list = jedis.smembers("post:" + req.getParameter("art_no") + ":tags");

				jedis.close();

//
//				    for(String element : tag_list) {
//				        System.out.println(element);
//				    }
//
				req.setAttribute("tag_list", tag_list); // Redis資料庫取出的tag_list物件,存入req

				// Retrieve form parameters.
				Integer art_no = new Integer(req.getParameter("art_no"));

				ArticleDAO dao = new ArticleDAO();
				ArticleVO articleVO = dao.findByPrimaryKey(art_no);

				req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req

				// Bootstrap_modal
				boolean openModal = true;
				System.out.println(openModal);
				req.setAttribute("openModal", openModal);

				RequestDispatcher successView = req.getRequestDispatcher("/campion_front.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		
		if ("getOne_From5".equals(action)) { // 回到後台檢舉管理的燈箱

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
				Set<String> tag_list = jedis.smembers("post:" + req.getParameter("art_no") + ":tags");

				jedis.close();

//
//				    for(String element : tag_list) {
//				        System.out.println(element);
//				    }
//
				req.setAttribute("tag_list", tag_list); // Redis資料庫取出的tag_list物件,存入req

				// Retrieve form parameters.
				Integer art_no = new Integer(req.getParameter("art_no"));

				ArticleDAO dao = new ArticleDAO();
				ArticleVO articleVO = dao.findByPrimaryKey(art_no);

				req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req

				// Bootstrap_modal
				boolean openModal = true;
				System.out.println(openModal);
				req.setAttribute("openModal", openModal);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/article_report/listAllArticle_Report.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		if ("search_tag".equals(action)) {

			try {
				res.setContentType("text/html;charset=UTF-8");
				System.out.println("hello");
				// 取得要搜尋的tag
				String target_tag = new String(req.getParameter("tag"));
				System.out.println(target_tag);

				// 用redis查詢有沒有跟target_tag一樣或相似的tags
				Jedis jedis = new Jedis("localhost", 6379); // 建立redis實體
				jedis.auth("123456"); // 密碼
				jedis.select(6); // db06

//			                     模糊查詢
				Map<String, Integer> tag_map = new HashMap<String, Integer>(); // 這個Map的key裝標籤 value裝該標籤有多少文章
				String cursor = ScanParams.SCAN_POINTER_START;
				String key = "tag:*"+target_tag+"*";
				String mapentry = new String(); // 用來裝某標籤 例如 tag:心情:posts
				String tag_real_name = new String();
				Long tag_real_name_art_num;
				ScanParams scanParams = new ScanParams();
				scanParams.match(key);// 尋找以tag:心* 為開頭 key
				scanParams.count(1000);
				while (true) {
					// 使用scan獲取數據，使用cursor游標記錄位置，下次循环使用
					ScanResult<String> scanResult = jedis.scan(cursor, scanParams);
					cursor = scanResult.getStringCursor();// 返回0 說明遍歷完成
					List<String> list = scanResult.getResult();
					long t1 = System.currentTimeMillis();
					for (int m = 0; m < list.size(); m++) {
						mapentry = list.get(m);
						System.out.println(mapentry);
						String[] mapentry_split = mapentry.split(":"); // 把tag:心情:post 切成 「tag」、「心情」、「post」
						tag_real_name = mapentry_split[1]; // 取中間的那個，即標籤名稱
						System.out.println("這個標籤有" + jedis.scard(mapentry) + "篇文章"); // 取得這個標籤有多少篇文章
						tag_real_name_art_num = jedis.scard(mapentry); // 這數字是long型別
						tag_map.put(tag_real_name, tag_real_name_art_num.intValue()); // 要從long轉int 用intValue();
					}

					if ("0".equals(cursor)) {
						break;
					}

					jedis.close();
				}

				for (Entry<String, Integer> e : tag_map.entrySet()) { // 印出測試
					System.out.println(e.getKey() + ":" + e.getValue());
				}
				
				
//		        HashMap returnData = new HashMap();
		        
//		        returnData.put("tag_map", tag_map);
				
		        JSONObject responseJSONObject = new JSONObject(tag_map);
		        
		        PrintWriter out = res.getWriter();
		        
		        out.println(responseJSONObject);

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
