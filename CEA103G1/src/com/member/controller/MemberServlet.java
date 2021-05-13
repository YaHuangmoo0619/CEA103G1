package com.member.controller;

import java.io.*;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

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
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("mbr_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer mbr_no = null;
				try {
					mbr_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�|���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mbr_no);
				if (memberVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
		
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("MemberVO", memberVO); // ��Ʈw���X��member_rankVO����,�s�Jreq
				String url = "/back-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneMember.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllmember.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mbr_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("memberVO", memberVO);         // ��Ʈw���X��memberVO����,�s�Jreq
				String url = "/back-end/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_member_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_member_rank_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				
				Integer rank_no = new Integer(req.getParameter("rank_no").trim());
				
				Part pic = req.getPart("sticker");
				InputStream in = pic.getInputStream();
				byte[] sticker = new byte[in.available()];
				in.read(sticker);
				
				String acc = req.getParameter("acc");
				String accReg = "^[(a-zA-Z0-9_)]{2,20}$";
				if (acc == null || acc.trim().length() == 0) {
					errorMsgs.add("�|���b��: �ФŪť�");
				} else if(!acc.trim().matches(accReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���b��: �u��O�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��20����");
	            }
				
				String pwd = new String(req.getParameter("pwd").trim());
				
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,60}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("�|���m�W: �ФŪť�");
				} else if(!name.trim().matches(nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��60����");
	            }
					
				SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateU = dsf.parse(req.getParameter("bday"));
				java.sql.Date bday = new java.sql.Date(dateU.getTime());
				
				Integer sex = new Integer(req.getParameter("sex"));
				if(sex == 0) {
					errorMsgs.add("�п�ܩʧO");
				}
				
				String id = req.getParameter("id");
				String idReg = "^[(\u4e00-\u9fa5)(A-Z0-9)]{10}$";
				if (id == null || id.trim().length() == 0) {
					errorMsgs.add("�����Ҧr��: �ФŪť�");
				} else if(!id.trim().matches(idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�����Ҧr��: �u��O�j�g�^��r���B�Ʀr , �B���ץ��ݬO10");
	            }
				
				String mobile = req.getParameter("mobile");
				String mobileReg = "^[(0-9)]{10}$";
				if (mobile == null || mobile.trim().length() == 0) {
					errorMsgs.add("������X: �ФŪť�");
				} else if(!mobile.trim().matches(mobileReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("������X: �u��O�Ʀr , �B���ץ��ݬO10");
	            }
				
				String mail = req.getParameter("mail");
				String mailReg = "^[(a-zA-Z0-9_)]{2,100}$";
				if (mail == null || mail.trim().length() == 0) {
					errorMsgs.add("�|���H�c: �ФŪť�");
				} else if(!mail.trim().matches(mailReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���H�c: �u��O�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��100����");
				}
				
				String city = new String(req.getParameter("city").trim());
				
				String dist = new String(req.getParameter("dist").trim());
				
				String add = req.getParameter("add").trim();
				if (add == null || add.trim().length() == 0) {
					errorMsgs.add("�a�}�ФŪť�");
				}
				
				SimpleDateFormat dsf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date dateU1 = dsf1.parse(req.getParameter("join_time"));
				java.sql.Timestamp join_time = new java.sql.Timestamp(dateU1.getTime());
					
				String card = req.getParameter("card");
				String cardReg = "^[(A-Z0-9)]{2,100}$";
				if (card == null || card.trim().length() == 0) {
					errorMsgs.add("�H�Υd��: �ФŪť�");
				} else if(!card.trim().matches(cardReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�H�Υd��: �u��O�^��r���B�Ʀr, �B���ץ��ݦb2��100����");
	            }
				
				Integer pt = new Integer(req.getParameter("pt").trim());
				
				Integer acc_stat = new Integer(req.getParameter("acc_stat").trim());
				
				Integer exp = new Integer(req.getParameter("exp").trim());
				
				String rmk = req.getParameter("rmk");
				if(rmk == null || rmk.trim().isEmpty()) {
					errorMsgs.add("�м��g����");
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
					req.setAttribute("memberVO", memberVO); // �t����J�榡���~��member_rankVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMember(mbr_no, rank_no, acc, pwd, id, name, bday, sex, mobile, mail, city, dist, add, join_time, card, pt, acc_stat, exp, sticker, rmk);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("MemberVO", memberVO); // ��Ʈwupdate���\��,���T����member_rankVO����,�s�Jreq
				String url = "/back-end/member/listOneMember_rank.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneMember_rank.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/update_member_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addMember_rank.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				
				Integer rank_no = new Integer(req.getParameter("rank_no").trim());
				
				Part pic = req.getPart("sticker");
				InputStream in = pic.getInputStream();
				byte[] sticker = new byte[in.available()];
				in.read(sticker);
				
				String acc = req.getParameter("acc");
				String accReg = "^[(a-zA-Z0-9_)]{2,20}$";
				if (acc == null || acc.trim().length() == 0) {
					errorMsgs.add("�|���b��: �ФŪť�");
				} else if(!acc.trim().matches(accReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���b��: �u��O�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��20����");
	            }
				
				String pwd = new String(req.getParameter("pwd").trim());
				
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,60}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("�|���m�W: �ФŪť�");
				} else if(!name.trim().matches(nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��60����");
	            }
					
				SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateU = dsf.parse(req.getParameter("bday"));
				java.sql.Date bday = new java.sql.Date(dateU.getTime());
				
				Integer sex = new Integer(req.getParameter("sex"));
				if(sex == 0) {
					errorMsgs.add("�п�ܩʧO");
				}
				
				String id = req.getParameter("id");
				String idReg = "^[(\u4e00-\u9fa5)(A-Z0-9)]{10}$";
				if (id == null || id.trim().length() == 0) {
					errorMsgs.add("�����Ҧr��: �ФŪť�");
				} else if(!id.trim().matches(idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�����Ҧr��: �u��O�j�g�^��r���B�Ʀr , �B���ץ��ݬO10");
	            }
				
				String mobile = req.getParameter("mobile");
				String mobileReg = "^[(0-9)]{10}$";
				if (mobile == null || mobile.trim().length() == 0) {
					errorMsgs.add("������X: �ФŪť�");
				} else if(!mobile.trim().matches(mobileReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("������X: �u��O�Ʀr , �B���ץ��ݬO10");
	            }
				
				String mail = req.getParameter("mail");
				String mailReg = "^[(a-zA-Z0-9_)]{2,100}$";
				if (mail == null || mail.trim().length() == 0) {
					errorMsgs.add("�|���H�c: �ФŪť�");
				} else if(!mail.trim().matches(mailReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���H�c: �u��O�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��100����");
				}
				
				String city = new String(req.getParameter("city").trim());
				
				String dist = new String(req.getParameter("dist").trim());
				
				String add = req.getParameter("add").trim();
				if (add == null || add.trim().length() == 0) {
					errorMsgs.add("�a�}�ФŪť�");
				}
				
				SimpleDateFormat dsf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date dateU1 = dsf1.parse(req.getParameter("join_time"));
				java.sql.Timestamp join_time = new java.sql.Timestamp(dateU1.getTime());
					
				String card = req.getParameter("card");
				String cardReg = "^[(A-Z0-9)]{2,100}$";
				if (card == null || card.trim().length() == 0) {
					errorMsgs.add("�H�Υd��: �ФŪť�");
				} else if(!card.trim().matches(cardReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�H�Υd��: �u��O�^��r���B�Ʀr, �B���ץ��ݦb2��100����");
	            }
				
				Integer pt = new Integer(req.getParameter("pt").trim());
				
				Integer acc_stat = new Integer(req.getParameter("acc_stat").trim());
				
				Integer exp = new Integer(req.getParameter("exp").trim());
				
				String rmk = req.getParameter("rmk");
				if(rmk == null || rmk.trim().isEmpty()) {
					errorMsgs.add("�м��g����");
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
					req.setAttribute("memberVO", memberVO); // �t����J�榡���~��member_rankVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�s�W���***************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(mbr_no, acc, pwd, id, name, bday, sex, mobile, mail, city, dist, add, join_time, card, pt, acc_stat, exp, sticker, rmk);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMember_rank.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/addMember_rank.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllMember_rank.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer ad_no = new Integer(req.getParameter("ad_no"));
				
				/***************************2.�}�l�R�����***************************************/
				MemberService MemberSvc = new MemberService();
				MemberSvc.deleteMember(ad_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("login_Member".equals(action)) { // �Ӧ�login.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("acc");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�b��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/login.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String acc = null;
				try {
					acc = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�b���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/login.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String str1 = req.getParameter("pwd");
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("�п�J�K�X");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/login.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String pwd = null;
				try {
					pwd = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("�K�X�榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/login.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				/***************************2.�}�l�d�߸��*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.loginMember(acc, pwd);
				if (memberVO == null) {
					errorMsgs.add("�d�L�|�����");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/login.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
		
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				//���ħ諸
				HttpSession session = req.getSession();
				session.setAttribute("memberVO",memberVO);
				String location = (String)session.getAttribute("location");
				if(location != null) {
					session.removeAttribute("location");
					res.sendRedirect(location);
					return;
				}
				String url = "/campion_front.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� campion_front.jsp
				successView.forward(req, res);
				//���ħ諸
				
//				req.setAttribute("MemberVO", MemberVO); // ��Ʈw���X��member_rankVO����,�s�Jreq
//				String url = "/front-end/member/success.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� campion_front.jsp
//				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/login.jsp");
				failureView.forward(req, res);
			}
		}
		
		//���ħ諸
		if ("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.removeAttribute("memberVO");
			System.out.println("remove");
			res.sendRedirect(req.getContextPath()+"/campion_front.jsp");
		}
		//���ħ諸
	}
}
