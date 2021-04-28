//package com.member_report.controller;
//
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.member_report.model.Member_reportService;
//import com.member_report.model.Member_reportVO;
//
//
//@WebServlet("/member_report/member_report.do")
//public class Member_reportServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	public void doGet(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//		doPost(req, res);
//	}
//
//	public void doPost(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//		
//		
//		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				String str = req.getParameter("rank_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("�п�J�|�����Žs��");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member_report/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				Integer rank_no = null;
//				try {
//					rank_no = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("�|�����Žs���榡�����T");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member_report/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************2.�}�l�d�߸��*****************************************/
//				Member_reportService member_reportSvc = new Member_reportService();
//				Member_reportVO member_reportVO = member_reportSvc.getOneMember_report(rank_no);
//				if (member_reportVO == null) {
//					errorMsgs.add("�d�L���");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member_report/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
//				req.setAttribute("member_reportVO", member_reportVO); // ��Ʈw���X��member_reportVO����,�s�Jreq
//				String url = "/back-end/member_report/listOneMember_report.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneMember_report.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/member_report/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllmember_report.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.�����ШD�Ѽ�****************************************/
//				Integer rank_no = new Integer(req.getParameter("rank_no"));
//				
//				/***************************2.�}�l�d�߸��****************************************/
//				Member_reportService member_reportSvc = new Member_reportService();
//				Member_reportVO member_reportVO = member_reportSvc.getOneMember_report(rank_no);
//								
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//				req.setAttribute("member_reportVO", member_reportVO);         // ��Ʈw���X��member_reportVO����,�s�Jreq
//				String url = "/back-end/member_report/update_member_report_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_member_report_input.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/member_report/listAllMember_report.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("update".equals(action)) { // �Ӧ�update_member_report_input.jsp���ШD
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				Integer rank_no = new Integer(req.getParameter("rank_no").trim());
//				
//				String rank_name = req.getParameter("rank_name");
//				String rank_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (rank_name == null || rank_name.trim().length() == 0) {
//					errorMsgs.add("�|�����ŦW��: �ФŪť�");
//				} else if(!rank_name.trim().matches(rank_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("�|�����ŦW��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//	            }
//				
//				Integer pt_rwd_rto = null;
//				try {
//					pt_rwd_rto = new Integer(req.getParameter("pt_rwd_rto").trim());
//				} catch (NumberFormatException e) {
//					pt_rwd_rto = 0;
//					errorMsgs.add("�I�Ʀ^�X��ҽж�Ʀr.");
//				}
//
//
//				Member_reportVO member_reportVO = new Member_reportVO();
//				member_reportVO.setRank_no(rank_no);
//				member_reportVO.setRank_name(rank_name);
//				member_reportVO.setPt_rwd_rto(pt_rwd_rto);
//
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("member_reportVO", member_reportVO); // �t����J�榡���~��member_reportVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member_report/update_member_report_input.jsp");
//					failureView.forward(req, res);
//					return; //�{�����_
//				}
//				
//				/***************************2.�}�l�ק���*****************************************/
//				Member_reportService member_reportSvc = new Member_reportService();
//				member_reportVO = member_reportSvc.updateMember_report(rank_no, rank_name, pt_rwd_rto);
//				
//				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
//				req.setAttribute("member_reportVO", member_reportVO); // ��Ʈwupdate���\��,���T����member_reportVO����,�s�Jreq
//				String url = "/back-end/member_report/listOneMember_report.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneMember_report.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/member_report/update_member_report_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//        if ("insert".equals(action)) { // �Ӧ�addMember_report.jsp���ШD  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
//				String rank_name = req.getParameter("rank_name");
//				String rank_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (rank_name == null || rank_name.trim().length() == 0) {
//					errorMsgs.add("�|�����ŦW��: �ФŪť�");
//				} else if(!rank_name.trim().matches(rank_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("�|�����ŦW��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//	            }
//				
//				Integer pt_rwd_rto = null;
//				try {
//					pt_rwd_rto = new Integer(req.getParameter("pt_rwd_rto").trim());
//				} catch (NumberFormatException e) {
//					pt_rwd_rto = 0;
//					errorMsgs.add("�I�Ʀ^�X��ҽж�Ʀr.");
//				}
//
//				Member_reportVO member_reportVO = new Member_reportVO();
//				member_reportVO.setRank_name(rank_name);
//				member_reportVO.setPt_rwd_rto(pt_rwd_rto);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("member_reportVO", member_reportVO); // �t����J�榡���~��member_reportVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member_report/addMember_report.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.�}�l�s�W���***************************************/
//				Member_reportService member_reportSvc = new Member_reportService();
//				member_reportVO = member_reportSvc.addMember_report(rank_name, pt_rwd_rto);
//				
//				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				String url = "/back-end/member_report/listAllMember_report.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMember_report.jsp
//				successView.forward(req, res);				
//				
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/member_report/addMember_report.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("delete".equals(action)) { // �Ӧ�listAllMember_report.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.�����ШD�Ѽ�***************************************/
//				Integer rank_no = new Integer(req.getParameter("rank_no"));
//				
//				/***************************2.�}�l�R�����***************************************/
//				Member_reportService member_reportSvc = new Member_reportService();
//				member_reportSvc.deleteMember_report(rank_no);
//				
//				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
//				String url = "/back-end/member_report/listAllMember_report.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//				successView.forward(req, res);
//				
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/member_report/listAllMember_report.jsp");
//				failureView.forward(req, res);
//			}
//		}
//	}
//}
