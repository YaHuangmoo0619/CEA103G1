package com.place_order_details.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.place_order_details.model.Place_Order_DetailsService;
import com.place_order_details.model.Place_Order_DetailsVO;

public class Place_Order_DetailsServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getplace_order_details".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer plc_ord_no = new Integer(req.getParameter("place_order_details"));
				/*************************** 2.開始查詢資料 *****************************************/
				Place_Order_DetailsService place_order_detailsSvc = new Place_Order_DetailsService();
				List<Place_Order_DetailsVO> list = place_order_detailsSvc.getOnePlace_Order_Details(plc_ord_no);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/feature_list/listAllPlace_order.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("place_order_detailsVO", list); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/place_order_details/listOnePlace_order_details.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/place_order/listAllPlace_order.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
