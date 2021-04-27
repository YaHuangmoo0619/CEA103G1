package com.follow.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.follow.model.FollowService;
import com.follow.model.FollowVO;


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
		
	
		if ("getbyflwed_no".equals(action)) { // �Ӧ�select_page.jsp���ШD�A�C�X�o�ӤH�����ǯ���

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("flwed_mbr_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				

				Integer flwed_mbr_no = null;
				try {
					flwed_mbr_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�|���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				FollowService followSvc = new FollowService();
				List<FollowVO> followVO = followSvc.findbyflwed(flwed_mbr_no);
				if (followVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("followVO", followVO); // ��Ʈw���X��followVO����,�s�Jreq
				String url = "/back-end/follow/listOneByFlwed.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneByFlwed.jsp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/follow/select_page.jsp");
				failureView.forward(req, res);
			}
		}		
	
	
		
		
		if ("getbyflw_no".equals(action)) { // �Ӧ�select_page.jsp���ШD�A�C�X�o�ӤH�O���ǤH������

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("flw_mbr_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				

				Integer flw_mbr_no = null;
				try {
					flw_mbr_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�|���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				System.out.println(flw_mbr_no);			
				/***************************2.�}�l�d�߸��*****************************************/
				FollowService followSvc = new FollowService();
				List<FollowVO> followVO = followSvc.findbyflw(flw_mbr_no);
				if (followVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/follow/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("followVO", followVO); // ��Ʈw���X��followVO����,�s�Jreq
				String url = "/back-end/follow/listOneByFlw.jsp";
				System.out.println("hello");
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneByFlw.jsp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/follow/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	
	}
	

	
}
