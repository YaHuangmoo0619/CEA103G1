package com.member.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/MemberChangeInfo")
public class MemberChangeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println(req.getParameter("name"));
		if ("update_info".equals(action)) { // 來自update_member_rank_inadd.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
//				System.out.println("mbr_no="+req.getParameter("mbr_no"));
				
				Integer rank_no = new Integer(req.getParameter("rank_no"));
//				System.out.println("rank_no="+req.getParameter("rank_no"));
				
				String acc = req.getParameter("acc");
				String accReg = "^[(a-zA-Z0-9_)]{2,20}$";
				if (acc == null || acc.trim().length() == 0) {
					errorMsgs.add("會員帳號: 請勿空白");
					acc = acc.trim();
				} else if(!acc.trim().matches(accReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員帳號: 只能是英文字母、數字和_ , 且長度必需在2到20之間");
	            }
//				System.out.println("acc="+req.getParameter("acc"));
				
				String pwd = new String(req.getParameter("pwd").trim());
				String pwdReg = "^[(a-zA-Z0-9_)]{2,20}$";
				if (pwd == null || pwd.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if(!pwd.trim().matches(pwdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼: 只能是英文字母、數字和_ , 且長度必需在2到20之間");
	            }
//				System.out.println("pwd="+req.getParameter("pwd"));
				
				String pwd2 = new String(req.getParameter("pwd2").trim());
				if (pwd2 == null || pwd2.trim().length() == 0) {
					errorMsgs.add("密碼: 請輸入第二次密碼");
				}
				
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,60}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到60之間");
	            }
//				System.out.println("name="+req.getParameter("name"));
				
				java.sql.Date bday;
				String bdayStr = req.getParameter("bday");
				if (bdayStr == null || bdayStr.trim().length() == 0) {
					bday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請填寫生日");
				}else {
					SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date dateU = null;
					try {
						dateU = dsf.parse(bdayStr);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					bday = new java.sql.Date(dateU.getTime());
				}
//				System.out.println("bdayStr="+req.getParameter("bdayStr"));
				
				Integer sex = new Integer(req.getParameter("sex"));
//				System.out.println("sex="+req.getParameter("sex"));
				
				String id = req.getParameter("id");
//				System.out.println(id.trim().length());
				if (id == null || id.trim().length() == 0) {
					errorMsgs.add("身分證字號: 請勿空白");
				}else if(id.trim().length() < 10){
					errorMsgs.add("身分證字號: 身份證字號錯誤");
				}else {
					String checked = testId(id);
	            	if(checked.equals("false")) { 
	            		errorMsgs.add("身分證字號: 身份證字號錯誤");
	            	}
	            }
//				System.out.println("id="+req.getParameter("id"));
				
				String mobile = req.getParameter("mobile");
				String mobileReg = "^[(0-9)]{10}$";
				if (mobile == null || mobile.trim().length() == 0) {
					mobile = mobile.trim();
					errorMsgs.add("手機號碼: 請勿空白");
				} else if(!mobile.trim().matches(mobileReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("手機號碼: 只能是數字 , 且長度必需是10");
	            }
//				System.out.println("mobile="+req.getParameter("mobile"));
				
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
//				System.out.println("mail="+req.getParameter("mail"));
				
				String city = new String(req.getParameter("city"));
				if (city == null || city == "no") {
					errorMsgs.add("請選擇縣市");
				} 
//				System.out.println("city="+req.getParameter("city"));
				
				String dist = new String(req.getParameter("dist"));
				if (dist == null || dist.length() == 0) {
					errorMsgs.add("請選擇鄉鎮市區");
				}
//				System.out.println("dist="+req.getParameter("dist"));
				
				String add = req.getParameter("add").trim();
				if (add == null || add.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
//				System.out.println("add="+req.getParameter("add"));
				
				SimpleDateFormat dsf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date dateU1 = null;
				try {
					dateU1 = dsf1.parse(req.getParameter("join_time"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				java.sql.Timestamp join_time = new java.sql.Timestamp(dateU1.getTime());
//				System.out.println("join_time="+req.getParameter("join_time"));
					
				String card = req.getParameter("card");
				String cardReg = "^[(0-9)]{16,16}$";
				if(!card.trim().matches(cardReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("信用卡卡號為16個數字");
	            }
//				System.out.println("card="+req.getParameter("card"));
				
				Integer pt = new Integer(req.getParameter("pt"));
//				System.out.println("pt="+req.getParameter("pt"));
				
//				System.out.println("acc_stat="+req.getParameter("acc_stat"));
				Integer acc_stat = 0;
				try {
					acc_stat = new Integer(req.getParameter("acc_stat"));
				}catch(NumberFormatException ne) {
//					System.out.println("faile");
				}
				
				
				Integer exp = new Integer(req.getParameter("exp"));
//				System.out.println("exp="+req.getParameter("exp"));
				
				String rmk = req.getParameter("rmk");
//				System.out.println("rmk="+req.getParameter("rmk"));
				

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
				memberVO.setRmk(rmk);
//				System.out.println("check1");
//				System.out.println(mbr_no+","+ rank_no+","+ acc+","+ pwd+","+ id+","+ name+","+ bday+","+ sex+","+ mobile+","+ mail+","+ city+","+ dist+","+ add+","+ join_time+","+ card+","+ pt+","+ acc_stat+","+ exp+","+ rmk);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
//					System.out.println("check2");
					req.setAttribute("memberVO", memberVO); 
//					System.out.println("check3");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/update_member_input.jsp");
					failureView.forward(req, res);
//					System.out.println("check4");
					return; //程式中斷
				}
//				System.out.println("check");
				/***************************2.開始修改資料*****************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.update_info(mbr_no, rank_no, acc, pwd, id, name, bday, sex, mobile, mail, city, dist, add, join_time, card, pt, acc_stat, exp, rmk);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVOUpdate", memberVO); // 資料庫update成功後,正確的的member_rankVO物件,存入req
				String url = "/front-end/member/viewMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember_rank.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/update_member_input.jsp");
				failureView.forward(req, res);
			}
		}
	}
	public String testId(String id) {
		String[] idCode = id.split("");
//		System.out.println(idCode[0]);
		String[] local = {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "W", "Z", "I", "O"};
		int num = 10;
		for(int i = 0; i < local.length; i++){
			if(idCode[0].equals(local[i])){
				num += i;
			}
		}
		String[] nums = new Integer(num).toString().split("");
		int checkNum = new Integer(nums[0]) * 1 + new Integer(nums[1]) * 9;
		for(int i = 1; i < 9; i++){
			checkNum = checkNum + new Integer(idCode[i]) * (Math.abs(i - 9));
		}
		checkNum = 10 - (checkNum % 10);
		if(new Integer(idCode[9]) == checkNum){
			return "true";
		}else{
			return "false";
		}
	}
}
