package com.place_order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.place_order.model.Place_OrderService;
import com.place_order.model.Place_OrderVO;
import com.place_order_details.model.Place_Order_DetailsVO;


@WebServlet("/place_order/place_order.do")
public class Place_OrderServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOnePlaceOrder".equals(action)) { // �Ӧ�select_page.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("plc_ord_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/place_ordersite/PresentPlace_order.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer plc_ord_no = null;
				try {
					plc_ord_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/place_order/PresentPlace_order.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				Place_OrderService place_orderSvc = new Place_OrderService();
				Place_OrderVO place_orderVO = place_orderSvc.getOnePlace_Order(plc_ord_no);
				if (place_orderVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/place_order/PresentPlace_order.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("place_orderVO", place_orderVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/place_order/listOnePlace_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/place_order/PresentPlace_order.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOnePlaceOrderFromBack".equals(action)) { // �Ӧ�select_page.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("plc_ord_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/place_ordersite/PresentPlace_order.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				
				Integer plc_ord_no = null;
				try {
					plc_ord_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/place_order/PresentPlace_order.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				
				/*************************** 2.�}�l�d�߸�� *****************************************/
				Place_OrderService place_orderSvc = new Place_OrderService();
				Place_OrderVO place_orderVO = place_orderSvc.getOnePlace_Order(plc_ord_no);
				if (place_orderVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/place_order/PresentPlace_order.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("place_orderVO", place_orderVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/place_order/listOnePlace_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
				
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/place_order/PresentPlace_order.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer plc_ord_no = new Integer(req.getParameter("plc_ord_no").trim());
				Integer ckin_stat = new Integer(req.getParameter("ckin_stat").trim());
				
				Place_OrderService place_orderSvc = new Place_OrderService();
				Place_OrderVO place_orderVO = place_orderSvc.getOnePlace_Order(plc_ord_no);
				int pay_stat = place_orderVO.getPay_stat();

				place_orderSvc.updatePlace_Order(plc_ord_no, pay_stat, ckin_stat);
				place_orderVO = place_orderSvc.getOnePlace_Order(plc_ord_no);
				if(place_orderVO.getPay_stat() == 2 && (place_orderVO.getCkin_stat() == 1 || place_orderVO.getCkin_stat() == 3)) {
					MemberVO memberVO = new MemberService().getOneMember(place_orderVO.getMbr_no());
					int pt = (int)((place_orderVO.getPlc_ord_sum() - place_orderVO.getUsed_pt()) * 0.01);
				}

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("place_orderVO", place_orderVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front-end/place_order/listOnePlace_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/place_order/listOnePlace_order.jsp");
				failureView.forward(req, res);
			}
		}
		if ("updateFromBack".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer plc_ord_no = new Integer(req.getParameter("plc_ord_no").trim());
				Integer pay_stat = new Integer(req.getParameter("pay_stat").trim());
				
				Place_OrderService place_orderSvc = new Place_OrderService();
				Place_OrderVO place_orderVO = place_orderSvc.getOnePlace_Order(plc_ord_no);
				int ckin_stat = place_orderVO.getCkin_stat();
				
				place_orderSvc.updatePlace_Order(plc_ord_no, pay_stat, ckin_stat);
				place_orderVO = place_orderSvc.getOnePlace_Order(plc_ord_no);
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("place_orderVO", place_orderVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/place_order/listOnePlace_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);
				
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/place_order/listOnePlace_order.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
