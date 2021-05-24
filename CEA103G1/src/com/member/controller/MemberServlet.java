package com.member.controller;

import java.io.*;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.member.model.JedisHandler;
import com.member.model.MemberDAO;
import com.member.model.MemberService;
import com.member.model.MemberVO;


@WebServlet("/member/member.do")
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mbr_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer mbr_no = null;
				try {
					mbr_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mbr_no);
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
		
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("MemberVO", memberVO); // 資料庫取出的member_rankVO物件,存入req
				String url = "/back-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllmember.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
				
				/***************************2.開始查詢資料****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mbr_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memberVO", memberVO);         // 資料庫取出的memberVO物件,存入req
				String url = "/back-end/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_member_rank_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				
				Integer rank_no = new Integer(req.getParameter("rank_no").trim());
				
				Part pic = req.getPart("sticker");
				InputStream in = pic.getInputStream();
				byte[] sticker = new byte[in.available()];
				in.read(sticker);
				
				String acc = req.getParameter("acc");
				String accReg = "^[(a-zA-Z0-9_)]{2,20}$";
				if (acc == null || acc.trim().length() == 0) {
					errorMsgs.add("會員帳號: 請勿空白");
				} else if(!acc.trim().matches(accReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員帳號: 只能是英文字母、數字和_ , 且長度必需在2到20之間");
	            }
				
				String pwd = new String(req.getParameter("pwd").trim());
				
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,60}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到60之間");
	            }
					
				SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateU = dsf.parse(req.getParameter("bday"));
				java.sql.Date bday = new java.sql.Date(dateU.getTime());
				
				Integer sex = new Integer(req.getParameter("sex"));
				if(sex == 0) {
					errorMsgs.add("請選擇性別");
				}
				
				String id = req.getParameter("id");
				String idReg = "^[(\u4e00-\u9fa5)(A-Z0-9)]{10}$";
				if (id == null || id.trim().length() == 0) {
					errorMsgs.add("身分證字號: 請勿空白");
				} else if(!id.trim().matches(idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("身分證字號: 只能是大寫英文字母、數字 , 且長度必需是10");
	            }
				
				String mobile = req.getParameter("mobile");
				String mobileReg = "^[(0-9)]{10}$";
				if (mobile == null || mobile.trim().length() == 0) {
					errorMsgs.add("手機號碼: 請勿空白");
				} else if(!mobile.trim().matches(mobileReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("手機號碼: 只能是數字 , 且長度必需是10");
	            }
				
				String mail = req.getParameter("mail");
				String mailReg = "^[(a-zA-Z0-9_)]{2,100}$";
				if (mail == null || mail.trim().length() == 0) {
					errorMsgs.add("會員信箱: 請勿空白");
				} else if(!mail.trim().matches(mailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員信箱: 只能是英文字母、數字和_ , 且長度必需在2到100之間");
				}
				
				String city = new String(req.getParameter("city").trim());
				
				String dist = new String(req.getParameter("dist").trim());
				
				String add = req.getParameter("add").trim();
				if (add == null || add.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				
				SimpleDateFormat dsf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date dateU1 = dsf1.parse(req.getParameter("join_time"));
				java.sql.Timestamp join_time = new java.sql.Timestamp(dateU1.getTime());
					
				String card = req.getParameter("card");
				String cardReg = "^[(A-Z0-9)]{2,100}$";
				if (card == null || card.trim().length() == 0) {
					errorMsgs.add("信用卡號: 請勿空白");
				} else if(!card.trim().matches(cardReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("信用卡號: 只能是英文字母、數字, 且長度必需在2到100之間");
	            }
				
				Integer pt = new Integer(req.getParameter("pt").trim());
				
				Integer acc_stat = new Integer(req.getParameter("acc_stat").trim());
				
				Integer exp = new Integer(req.getParameter("exp").trim());
				
				String rmk = req.getParameter("rmk");
				if(rmk == null || rmk.trim().isEmpty()) {
					errorMsgs.add("請撰寫內文");
				}
				

				MemberVO memberVO = new MemberVO();
				memberVO.setMbr_no(mbr_no);
				memberVO.setRank_no(rank_no);
				memberVO.setAcc(acc);
				memberVO.setPwd(pwd);
				memberVO.setName(name);
				memberVO.setBday(bday);
				memberVO.setSex(sex);
				memberVO.setId(id);
				memberVO.setMobile(mobile);
				memberVO.setMail(mail);
				memberVO.setCity(city);
				memberVO.setDist(dist);
				memberVO.setAdd(add);
				memberVO.setJoin_time(join_time);
				memberVO.setCard(card);
				memberVO.setPt(pt);
				memberVO.setPt(acc_stat);
				memberVO.setExp(exp);
				memberVO.setSticker(sticker);
				memberVO.setRmk(rmk);
				
				byte[] sticker_database = new byte[0];
				if(sticker.length == 0) {
					System.out.println("in");
					MemberService memberSvc = new MemberService();
					MemberVO memberVOPic = memberSvc.getOneMember(mbr_no);
					sticker_database = memberVOPic.getSticker();
					memberVO.setSticker(sticker_database);
				}else {
					memberVO.setSticker(sticker);
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的member_rankVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMember(mbr_no, rank_no, acc, pwd, id, name, bday, sex, mobile, mail, city, dist, add, join_time, card, pt, acc_stat, exp, sticker, rmk);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("MemberVO", memberVO); // 資料庫update成功後,正確的的member_rankVO物件,存入req
				String url = "/back-end/member/listOneMember_rank.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember_rank.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/update_member_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addMember_rank.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				
				Integer rank_no = new Integer(req.getParameter("rank_no").trim());
				
				Part pic = req.getPart("sticker");
				InputStream in = pic.getInputStream();
				byte[] sticker = new byte[in.available()];
				in.read(sticker);
				
				String acc = req.getParameter("acc");
				String accReg = "^[(a-zA-Z0-9_)]{2,20}$";
				if (acc == null || acc.trim().length() == 0) {
					errorMsgs.add("會員帳號: 請勿空白");
				} else if(!acc.trim().matches(accReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員帳號: 只能是英文字母、數字和_ , 且長度必需在2到20之間");
	            }
				
				String pwd = new String(req.getParameter("pwd").trim());
				
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,60}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到60之間");
	            }
					
				SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateU = dsf.parse(req.getParameter("bday"));
				java.sql.Date bday = new java.sql.Date(dateU.getTime());
				
				Integer sex = new Integer(req.getParameter("sex"));
				if(sex == 0) {
					errorMsgs.add("請選擇性別");
				}
				
				String id = req.getParameter("id");
				String idReg = "^[(\u4e00-\u9fa5)(A-Z0-9)]{10}$";
				if (id == null || id.trim().length() == 0) {
					errorMsgs.add("身分證字號: 請勿空白");
				} else if(!id.trim().matches(idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("身分證字號: 只能是大寫英文字母、數字 , 且長度必需是10");
	            }
				
				String mobile = req.getParameter("mobile");
				String mobileReg = "^[(0-9)]{10}$";
				if (mobile == null || mobile.trim().length() == 0) {
					errorMsgs.add("手機號碼: 請勿空白");
				} else if(!mobile.trim().matches(mobileReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("手機號碼: 只能是數字 , 且長度必需是10");
	            }
				
				String mail = req.getParameter("mail");
				String mailReg = "^[(a-zA-Z0-9_)]{2,100}$";
				if (mail == null || mail.trim().length() == 0) {
					errorMsgs.add("會員信箱: 請勿空白");
				} else if(!mail.trim().matches(mailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員信箱: 只能是英文字母、數字和_ , 且長度必需在2到100之間");
				}
				
				String city = new String(req.getParameter("city").trim());
				
				String dist = new String(req.getParameter("dist").trim());
				
				String add = req.getParameter("add").trim();
				if (add == null || add.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				
				SimpleDateFormat dsf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date dateU1 = dsf1.parse(req.getParameter("join_time"));
				java.sql.Timestamp join_time = new java.sql.Timestamp(dateU1.getTime());
					
				String card = req.getParameter("card");
				String cardReg = "^[(A-Z0-9)]{2,100}$";
				if (card == null || card.trim().length() == 0) {
					errorMsgs.add("信用卡號: 請勿空白");
				} else if(!card.trim().matches(cardReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("信用卡號: 只能是英文字母、數字, 且長度必需在2到100之間");
	            }
				
				Integer pt = new Integer(req.getParameter("pt").trim());
				
				Integer acc_stat = new Integer(req.getParameter("acc_stat").trim());
				
				Integer exp = new Integer(req.getParameter("exp").trim());
				
				String rmk = req.getParameter("rmk");
				if(rmk == null || rmk.trim().isEmpty()) {
					errorMsgs.add("請撰寫內文");
				}
				

				MemberVO memberVO = new MemberVO();
				memberVO.setMbr_no(mbr_no);
				memberVO.setRank_no(rank_no);
				memberVO.setAcc(acc);
				memberVO.setPwd(pwd);
				memberVO.setName(name);
				memberVO.setBday(bday);
				memberVO.setSex(sex);
				memberVO.setId(id);
				memberVO.setMobile(mobile);
				memberVO.setMail(mail);
				memberVO.setCity(city);
				memberVO.setDist(dist);
				memberVO.setAdd(add);
				memberVO.setJoin_time(join_time);
				memberVO.setCard(card);
				memberVO.setPt(pt);
				memberVO.setPt(acc_stat);
				memberVO.setExp(exp);
				memberVO.setSticker(sticker);
				memberVO.setRmk(rmk);
				
				byte[] sticker_database = new byte[0];
				if(sticker.length == 0) {
					System.out.println("in");
					MemberService memberSvc = new MemberService();
					MemberVO memberVOPic = memberSvc.getOneMember(mbr_no);
					sticker_database = memberVOPic.getSticker();
					memberVO.setSticker(sticker_database);
				}else {
					memberVO.setSticker(sticker);
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的member_rankVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始新增資料***************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(mbr_no, acc, pwd, id, name, bday, sex, mobile, mail, city, dist, add, join_time, card, pt, acc_stat, exp, sticker, rmk);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember_rank.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/addMember_rank.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllMember_rank.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer ad_no = new Integer(req.getParameter("ad_no"));
				
				/***************************2.開始刪除資料***************************************/
				MemberService MemberSvc = new MemberService();
				MemberSvc.deleteMember(ad_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("login_Member".equals(action)) { // 來自login.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("acc");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String acc = null;
				try {
					acc = new String(str);
				} catch (Exception e) {
					errorMsgs.add("帳號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String str1 = req.getParameter("pwd");
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String pwd = null;
				try {
					pwd = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("密碼格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始(登入)查詢資料*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.loginMember(acc, pwd);
				if (memberVO == null) {
					errorMsgs.add("請確認輸入的帳號密碼是否正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
		
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				//雅凰改的
				HttpSession session = req.getSession();
				session.setAttribute("memberVO",memberVO);
				String location = (String)session.getAttribute("location");
				if(location != null) {
					session.removeAttribute("location");
					res.sendRedirect(location);
					return;
				}
				String url = "/campion_front.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 campion_front.jsp
				successView.forward(req, res);
				//雅凰改的
				
//				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的member_rankVO物件,存入req
//				String url = "/front-end/member/success.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 campion_front.jsp
//				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/login.jsp");
				failureView.forward(req, res);
			}
		}
		
		//雅凰改的
		if ("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.removeAttribute("memberVO");
			System.out.println("remove");
			res.sendRedirect(req.getContextPath()+"/campion_front.jsp");
		}
		//雅凰改的
		
		if ("register_Member".equals(action)) { // 來自register.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String acc = req.getParameter("acc");
				String accReg = "^[(a-zA-Z0-9_)]{2,20}$";
				if (acc == null || acc.trim().length() == 0) {
					errorMsgs.add("會員帳號: 請勿空白");
				} else if(!acc.trim().matches(accReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員帳號: 只能是英文字母、數字和_ , 且長度必需在2到20之間");
	            }
				
				String pwd = new String(req.getParameter("pwd").trim());
				
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,60}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到60之間");
	            }
					
				SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateU = dsf.parse(req.getParameter("bday"));
				java.sql.Date bday = new java.sql.Date(dateU.getTime());
				
				Integer sex = new Integer(req.getParameter("sex"));
				if(sex == 0) {
					errorMsgs.add("請選擇性別");
				}
				
				String id = req.getParameter("id");
				String idReg = "^[(\u4e00-\u9fa5)(A-Z0-9)]{10}$";
				if (id == null || id.trim().length() == 0) {
					errorMsgs.add("身分證字號: 請勿空白");
				} else if(!id.trim().matches(idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("身分證字號: 只能是大寫英文字母、數字 , 且長度必需是10");
	            }
				
				String mobile = req.getParameter("mobile");
				String mobileReg = "^[(0-9)]{10}$";
				if (mobile == null || mobile.trim().length() == 0) {
					errorMsgs.add("手機號碼: 請勿空白");
				} else if(!mobile.trim().matches(mobileReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("手機號碼: 只能是數字 , 且長度必需是10");
	            }
				
				String mail = req.getParameter("mail");
				if(mail == null ||mail.trim().isEmpty()) {
					errorMsgs.add("EMAIL: 請輸入EMAIL");
				}else if(!mail.trim().contains("@")) {
					errorMsgs.add("EMAIL: 請在電子郵件中包含@, " + mail +"並未包含@");
				}else if(mail.trim().contains("@") && mail.trim().length() == 1) {
					errorMsgs.add("EMAIL: 請輸入@前面和後面的部分, " + mail +"不是完整值");
				}else if(mail.trim().indexOf("@") == 0) {
					errorMsgs.add("EMAIL: 請輸入@前面的部分, " + mail +"不是完整值");
				}else if(mail.trim().lastIndexOf("@") == mail.trim().length()-1) {
					errorMsgs.add("EMAIL: 請輸入@後面的部分, " + mail +"不是完整值");
				}
				
				String city = new String(req.getParameter("city").trim());
				
				String dist = new String(req.getParameter("dist").trim());
				
				String add = req.getParameter("add").trim();
				if (add == null || add.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				
				
				MemberVO memberVO = new MemberVO();
				memberVO.setAcc(acc);
				memberVO.setPwd(pwd);
				memberVO.setName(name);
				memberVO.setBday(bday);
				memberVO.setSex(sex);
				memberVO.setId(id);
				memberVO.setMobile(mobile);
				memberVO.setMail(mail);
				memberVO.setCity(city);
				memberVO.setDist(dist);
				memberVO.setAdd(add);

				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的member_rankVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/register.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始新增(註冊)資料***************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.registerMember(acc, pwd, id, name, bday, sex, mobile, mail, city, dist, add);
				
				MemberMailService mms = new MemberMailService();
				
				JedisHandler jh = new JedisHandler();				
				String authCode = jh.setAuthCode(acc);
				
				String to = mail;
				String subject = "Campion營家會員帳號驗證通知信";
				String messageText = acc + "您好 :\n\n" 
						+ "感謝您使用本平臺，為了確認您的資料正確性，平臺需要驗證後才能啟用您的帳號。\n\n" 
						+ "請點擊以下連結進行驗證 :\n" 
						+ req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() 
						+ req.getContextPath() + req.getServletPath() + "?action=verifiction&acc="
						+ acc + "&authCode=" + authCode + "\n\n"
						+ "Campion營家團隊 敬上。";
				
				mms.sendMail(to, subject, messageText);
				
				/***************************3.新增(註冊)完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/member/success.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember_rank.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/register.jsp");
				failureView.forward(req, res);
			}
		}
		
		//忘記密碼
		if ("forgetPwd".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String mail = req.getParameter("mail").trim();
			/*************************** 2.開始查詢資料 *****************************************/
			MemberService memberSvc = new MemberService();
			MemberMailService mms = new MemberMailService();
			
			MemberVO memberVO = memberSvc.findByEmail(mail);
			
			if (memberVO != null) {
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				
				String to = mail;
				String subject = "Campion營家-臨時密碼";
				
				String temPwd = JedisHandler.randAuthCode();
				String messageText = memberVO.getAcc() + "您好:\n\n"
						+ "以下是您的臨時密碼，請用此密碼登入後，至會員專區重設您的密碼:\n\n"
						+ temPwd + "\n\n" + "Campion營家團隊 敬上。";

				mms.sendMail(to, subject, messageText);

				// 密碼變更為臨時密碼temPwd
				memberSvc.updatePwd(mail, temPwd);
				
				RequestDispatcher SuccessView = req.getRequestDispatcher("/front-end/member/forgetPassword_Email.jsp");
				SuccessView.forward(req, res);
				return;
			
			} else {
				errorMsgs.add("信箱不存在");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/forgetPassword.jsp");
				failureView.forward(req, res);
				return;
			}
		}
						
		//會員驗證信箱	
		if ("sendAuthMail".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String acc = req.getParameter("acc").trim();
				
				/*************************** 2.開始新增資料 ***************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.loginCheck(acc);
				
				MemberMailService mms = new MemberMailService();
				
				// 產生隨機認證碼存入redis
				JedisHandler jh = new JedisHandler();				
				String authCode = jh.setAuthCode(acc);
				
				String to = memberVO.getMail();
				String subject = "Campion營家帳號驗證通知信";
				String messageText = acc + "您好 :\n\n" 
						+ "感謝您使用本平臺，為了確認您的資料正確性，平臺需要驗證後才能啟用您的帳號。\n\n" 
						+ "請點擊以下連結進行驗證 :\n" 
						+ req.getScheme() + "://" + req.getServerName() + ":"  + req.getServerPort()
						+ req.getContextPath() + req.getServletPath() + "?action=verification&acc="
						+ acc + "&authCode=" + authCode + "\n\n"
						+ "Campion營家團隊 敬上。";
				
				mms.sendMail(to, subject, messageText);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("acc", acc);
				String url = "/front-end/member/unverifiction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("Exception" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/campion_front.jsp");
				failureView.forward(req, res);
			}
		}
		
		//會員驗證
		if("verifiction".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String acc = req.getParameter("acc");
			
			String authCode = req.getParameter("authCode");
			
			String savedCode = JedisHandler.getAuthCode(acc);
			if (savedCode == null) {
				errorMsgs.add("驗證信已過期，請重新申請");
			} else if (savedCode.equals(authCode)){
				System.out.println("驗證成功!");
				MemberService memberSvc = new MemberService();
				memberSvc.updateMemberStatus(acc, (int) 1);
			} else {
				errorMsgs.add("驗證有誤，請重新申請");
			}
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front-end/member/verificationMail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
	}
}
