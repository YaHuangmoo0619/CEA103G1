package com.place_order.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/place_order/place_order.do")
public class Place_OrderServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("listPresent".equals(action) || "listHistory".equals(action)) {
			try {
				String url = "/back-end/place_order/listAllPlace_order.jsp";
				if ("listPresent".equals(action))
					req.setAttribute("list", "PresentPlace_order.jsp");
				else if ("listHistory".equals(action))
					req.setAttribute("list", "HistoryPlace_order.jsp");

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
