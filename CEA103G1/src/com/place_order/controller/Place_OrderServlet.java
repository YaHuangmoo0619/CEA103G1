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
		
		if ("getOnePlaceOrder".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("plc_ord_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/place_ordersite/PresentPlace_order.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer plc_ord_no = null;
				try {
					plc_ord_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/place_order/PresentPlace_order.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Place_OrderService place_orderSvc = new Place_OrderService();
				Place_OrderVO place_orderVO = place_orderSvc.getOnePlace_Order(plc_ord_no);
				if (place_orderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/place_order/PresentPlace_order.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("place_orderVO", place_orderVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/place_order/listOnePlace_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/place_order/PresentPlace_order.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOnePlaceOrderFromBack".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("plc_ord_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/place_ordersite/PresentPlace_order.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				Integer plc_ord_no = null;
				try {
					plc_ord_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/place_order/PresentPlace_order.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/*************************** 2.開始查詢資料 *****************************************/
				Place_OrderService place_orderSvc = new Place_OrderService();
				Place_OrderVO place_orderVO = place_orderSvc.getOnePlace_Order(plc_ord_no);
				if (place_orderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/place_order/PresentPlace_order.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("place_orderVO", place_orderVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/place_order/listOnePlace_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/place_order/PresentPlace_order.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
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

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("place_orderVO", place_orderVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/place_order/listOnePlace_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/place_order/listOnePlace_order.jsp");
				failureView.forward(req, res);
			}
		}
		if ("updateFromBack".equals(action)) { // 來自update_emp_input.jsp的請求
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer plc_ord_no = new Integer(req.getParameter("plc_ord_no").trim());
				Integer pay_stat = new Integer(req.getParameter("pay_stat").trim());
				
				Place_OrderService place_orderSvc = new Place_OrderService();
				Place_OrderVO place_orderVO = place_orderSvc.getOnePlace_Order(plc_ord_no);
				int ckin_stat = place_orderVO.getCkin_stat();
				
				place_orderSvc.updatePlace_Order(plc_ord_no, pay_stat, ckin_stat);
				place_orderVO = place_orderSvc.getOnePlace_Order(plc_ord_no);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("place_orderVO", place_orderVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/place_order/listOnePlace_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/place_order/listOnePlace_order.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
