package com.place_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campsite.model.CampService;
import com.google.gson.Gson;
import com.member.model.MemberVO;
import com.place_order.model.Place_OrderService;
import com.place_order.model.Place_OrderVO;

@WebServlet("/place_order/place_orderformamber.do")
public class PlaceOrderForMember extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		PrintWriter out = res.getWriter();
		String jsonObject;
		String action = req.getParameter("action");
		MemberVO member = (MemberVO) req.getSession().getAttribute("member");
		CampService campSvc = new CampService();
		Place_OrderService place_orderSvc = new Place_OrderService();
		if ("listPresent".equals(action) || "listHistory".equals(action)) {
			List<Place_OrderVO> place_orderlist = place_orderSvc.getByMember(member.getMbr_no());
			List<Place_OrderVO> presentlist = new ArrayList();
			List<Place_OrderVO> historylist = new ArrayList();

			for (Place_OrderVO place_orderVO : place_orderlist) {
				if (place_orderVO.getCkin_stat() == 0) {
					presentlist.add(place_orderVO);
				} else {
					historylist.add(place_orderVO);
				}
			}
			for(Place_OrderVO place_orderVO : presentlist) {
				place_orderVO.setCamp_name(campSvc.getOneCamp(place_orderVO.getCamp_no()).getCamp_name());
			}
			for(Place_OrderVO place_orderVO : historylist) {
				place_orderVO.setCamp_name(campSvc.getOneCamp(place_orderVO.getCamp_no()).getCamp_name());
			}
			if ("listPresent".equals(action)) {
				jsonObject = gson.toJson(presentlist);
				out.println(jsonObject);
			}else {
				jsonObject = gson.toJson(historylist);
				out.println(jsonObject);
			}
		}

	}
}
