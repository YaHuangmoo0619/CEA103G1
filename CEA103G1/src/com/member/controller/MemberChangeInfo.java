package com.member.controller;

import java.io.IOException;
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
		System.out.println(req.getParameter("name"));
		if ("update_info".equals(action)) { // �Ӧ�update_member_rank_input.jsp���ШD
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
				
				Integer rank_no = new Integer(req.getParameter("rank_no"));
				
				String acc = req.getParameter("acc");
				String accReg = "^[(a-zA-Z0-9_)]{2,20}$";
				if (acc == null || acc.trim().length() == 0) {
					errorMsgs.put("acc","�|���b��: �ФŪť�");
				} else if(!acc.trim().matches(accReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("acc","�|���b��: �u��O�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��20����");
	            }
				
				String pwd = new String(req.getParameter("pwd").trim());
				String pwdReg = "^[(a-zA-Z0-9_)]{2,20}$";
				if (pwd == null || pwd.trim().length() == 0) {
					errorMsgs.put("pwd","�K�X: �ФŪť�");
				} else if(!acc.trim().matches(pwdReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("pwd","�K�X: �u��O�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��20����");
	            }
				
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,60}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.put("name","�|���m�W: �ФŪť�");
				} else if(!name.trim().matches(nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("name","�|���m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��60����");
	            }
					
				SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateU = dsf.parse(req.getParameter("bday"));
				java.sql.Date bday = new java.sql.Date(dateU.getTime());
				
				Integer sex = new Integer(req.getParameter("sex"));
				
				String id = req.getParameter("id");
				String idReg = "^[(\u4e00-\u9fa5)(A-Z0-9)]{10}$";
				if (id == null || id.trim().length() == 0) {
					errorMsgs.put("id","�����Ҧr��: �ФŪť�");
				} else if(!id.trim().matches(idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("id","�����Ҧr��: �u��O�j�g�^��r���B�Ʀr , �B���ץ��ݬO10");
	            }
				
				String mobile = req.getParameter("mobile");
				String mobileReg = "^[(0-9)]{10}$";
				if (mobile == null || mobile.trim().length() == 0) {
					errorMsgs.put("mobile","������X: �ФŪť�");
				} else if(!mobile.trim().matches(mobileReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("mobile","������X: �u��O�Ʀr , �B���ץ��ݬO10");
	            }
				
				String mail = req.getParameter("mail");
//				String mailReg = "^[(a-zA-Z0-9_)]{2,100}$";
//				if (mail == null || mail.trim().length() == 0) {
//					errorMsgs.put("�|���H�c: �ФŪť�");
//				} else if(!mail.trim().matches(mailReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.put("�|���H�c: �u��O�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��100����");
//				}
				
				String city = new String(req.getParameter("city").trim());
				
				String dist = new String(req.getParameter("dist").trim());
				
				String add = req.getParameter("add").trim();
				if (add == null || add.trim().length() == 0) {
					errorMsgs.put("add","�a�}�ФŪť�");
				}
				
				SimpleDateFormat dsf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date dateU1 = dsf1.parse(req.getParameter("join_time"));
				java.sql.Timestamp join_time = new java.sql.Timestamp(dateU1.getTime());
					
				String card = req.getParameter("card");
				String cardReg = "^[(A-Z0-9)]{2,100}$";
				if (card == null || card.trim().length() == 0) {
					errorMsgs.put("card","�H�Υd��: �ФŪť�");
				} else if(!card.trim().matches(cardReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("card","�H�Υd��: �u��O�^��r���B�Ʀr, �B���ץ��ݦb2��100����");
	            }
				
				Integer pt = new Integer(req.getParameter("pt").trim());
				
				Integer acc_stat = new Integer(req.getParameter("acc_stat").trim());
				
				Integer exp = new Integer(req.getParameter("exp").trim());
				
				String rmk = req.getParameter("rmk");
//				if(rmk == null || rmk.trim().isEmpty()) {
//					errorMsgs.put("�м��g����");
//				}
				

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
				System.out.println("check1");
				System.out.println(mbr_no+","+ rank_no+","+ acc+","+ pwd+","+ id+","+ name+","+ bday+","+ sex+","+ mobile+","+ mail+","+ city+","+ dist+","+ add+","+ join_time+","+ card+","+ pt+","+ acc_stat+","+ exp+","+ rmk);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("check2");
					req.setAttribute("memberVO", memberVO); // �t����J�榡���~��member_rankVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				System.out.println("check");
				/***************************2.�}�l�ק���*****************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.update_info(mbr_no, rank_no, acc, pwd, id, name, bday, sex, mobile, mail, city, dist, add, join_time, card, pt, acc_stat, exp, rmk);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("memberVOUpdate", memberVO); // ��Ʈwupdate���\��,���T����member_rankVO����,�s�Jreq
				String url = "/front-end/member/viewMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneMember_rank.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.put("error","�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/update_member_input.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
