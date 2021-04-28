//package com.advertising_application.controller;
//
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.advertising_application.model.Advertising_applicationService;
//import com.advertising_application.model.Advertising_applicationVO;
//
//
//@WebServlet("/advertising_application/advertising_application.do")
//@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
//public class Advertising_applicationServlet extends HttpServlet {
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
//				String str = req.getParameter("ad_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("�п�J�s�i�s��");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/advertising_application/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				Integer ad_no = null;
//				try {
//					ad_no = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("�s�i�s���榡�����T");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/advertising_application/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************2.�}�l�d�߸��*****************************************/
//				Advertising_applicationService advertising_applicationSvc = new Advertising_applicationService();
//				Advertising_applicationVO advertising_applicationVO = advertising_applicationSvc.getOneAdvertising_application(ad_no);
//				if (advertising_applicationVO == null) {
//					errorMsgs.add("�d�L���");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/advertising_application/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
//				req.setAttribute("Advertising_applicationVO", advertising_applicationVO); // ��Ʈw���X��member_rankVO����,�s�Jreq
//				String url = "/back-end/advertising_application/listOneAdvertising_application.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneAdvertising_application.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/advertising_application/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAlladvertising_application.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.�����ШD�Ѽ�****************************************/
//				Integer ad_no = new Integer(req.getParameter("ad_no"));
//				
//				/***************************2.�}�l�d�߸��****************************************/
//				Advertising_applicationService advertising_applicationSvc = new Advertising_applicationService();
//				Advertising_applicationVO advertising_applicationVO = advertising_applicationSvc.getOneAdvertising_application(ad_no);
//								
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//				req.setAttribute("advertising_applicationVO", advertising_applicationVO);         // ��Ʈw���X��advertising_applicationVO����,�s�Jreq
//				String url = "/back-end/advertising_application/update_advertising_application_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_advertising_application_input.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/advertising_application/listAllAdvertising_application.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("update".equals(action)) { // �Ӧ�update_member_rank_input.jsp���ШD
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				Integer ad_no = new Integer(req.getParameter("ad_no").trim());
//				
//				Integer cso_no = new Integer(req.getParameter("cso_no"));
//				if(cso_no == 0) {
//					errorMsgs.add("�п����D�s��");
//
//				String ad_head = req.getParameter("ad_head");
//				String ad_headReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
//				if (ad_head == null || ad_head.trim().length() == 0) {
//					errorMsgs.add("�s�i���D: �ФŪť�");
//				} else if(!ad_head.trim().matches(ad_headReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("�s�i���D: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��100����");
//	            }
//				
//				String an_cont = req.getParameter("an_cont");
//				if(an_cont.isEmpty()) {
//					errorMsgs.add("�м��g����");
//				}
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
//				Advertising_applicationVO Advertising_applicationVO = new Advertising_applicationVO();
//				Advertising_applicationVO.setRank_no(ad_no);
//				Advertising_applicationVO.setRank_name(rank_name);
//				Advertising_applicationVO.setPt_rwd_rto(pt_rwd_rto);
//
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("Advertising_applicationVO", Advertising_applicationVO); // �t����J�榡���~��member_rankVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/advertising_application/update_advertising_application_input.jsp");
//					failureView.forward(req, res);
//					return; //�{�����_
//				}
//				
//				/***************************2.�}�l�ק���*****************************************/
//				Advertising_applicationService Advertising_applicationSvc = new Advertising_applicationService();
//				Advertising_applicationVO = Advertising_applicationSvc.updateAdvertising_application(ad_no, rank_name, pt_rwd_rto);
//				
//				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
//				req.setAttribute("Advertising_applicationVO", Advertising_applicationVO); // ��Ʈwupdate���\��,���T����member_rankVO����,�s�Jreq
//				String url = "/back-end/advertising_application/listOneMember_rank.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneMember_rank.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/advertising_application/update_advertising_application_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//        if ("insert".equals(action)) { // �Ӧ�addMember_rank.jsp���ШD  
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
//				Advertising_applicationVO Advertising_applicationVO = new Advertising_applicationVO();
//				Advertising_applicationVO.setRank_name(rank_name);
//				Advertising_applicationVO.setPt_rwd_rto(pt_rwd_rto);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("Advertising_applicationVO", Advertising_applicationVO); // �t����J�榡���~��member_rankVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/advertising_application/addMember_rank.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.�}�l�s�W���***************************************/
//				Advertising_applicationService Advertising_applicationSvc = new Advertising_applicationService();
//				Advertising_applicationVO = Advertising_applicationSvc.addMember_rank(rank_name, pt_rwd_rto);
//				
//				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				String url = "/back-end/advertising_application/listAllAdvertising_application.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMember_rank.jsp
//				successView.forward(req, res);				
//				
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/advertising_application/addMember_rank.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("delete".equals(action)) { // �Ӧ�listAllMember_rank.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.�����ШD�Ѽ�***************************************/
//				Integer ad_no = new Integer(req.getParameter("ad_no"));
//				
//				/***************************2.�}�l�R�����***************************************/
//				Advertising_applicationService Advertising_applicationSvc = new Advertising_applicationService();
//				Advertising_applicationSvc.deleteAdvertising_application(ad_no);
//				
//				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
//				String url = "/back-end/advertising_application/listAllAdvertising_application.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//				successView.forward(req, res);
//				
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/advertising_application/listAllAdvertising_application.jsp");
//				failureView.forward(req, res);
//			}
//		}
//	}
//}
