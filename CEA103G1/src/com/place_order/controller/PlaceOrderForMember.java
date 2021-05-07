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
import com.place.model.PlaceService;
import com.place_order.model.Place_OrderService;
import com.place_order.model.Place_OrderVO;
import com.place_order_details.model.Place_Order_DetailsService;
import com.place_order_details.model.Place_Order_DetailsVO;

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
		PlaceService placeSvc = new PlaceService();
		Place_OrderService place_orderSvc = new Place_OrderService();
		Place_Order_DetailsService detailsSvc = new Place_Order_DetailsService();

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
			for (Place_OrderVO place_orderVO : presentlist) {
				place_orderVO.setCamp_name(campSvc.getOneCamp(place_orderVO.getCamp_no()).getCamp_name());
			}
			for (Place_OrderVO place_orderVO : historylist) {
				place_orderVO.setCamp_name(campSvc.getOneCamp(place_orderVO.getCamp_no()).getCamp_name());
			}
			if ("listPresent".equals(action)) {
				jsonObject = gson.toJson(presentlist);
				out.println(jsonObject);
			} else {
				jsonObject = gson.toJson(historylist);
				out.println(jsonObject);
			}
		}
		if ("listOne".equals(action)) {
			List<Object> list = new ArrayList();
			int plc_ord_no = new Integer(req.getParameter("plc_ord_no"));
			List<Place_Order_DetailsVO> detailslist = detailsSvc.getOnePlace_Order_Details(plc_ord_no);
			Place_OrderVO place_orderVO = place_orderSvc.getOnePlace_Order(plc_ord_no);
			place_orderVO.setCamp_name(campSvc.getOneCamp(place_orderVO.getCamp_no()).getCamp_name());
			for (Place_Order_DetailsVO place_order_detailsVO : detailslist) {
				place_order_detailsVO.setPlc_name(placeSvc.getOnePlace(place_order_detailsVO.getPlc_no()).getPlc_name());
			}
			list.add(place_orderVO);
			list.add(detailslist);
			jsonObject = gson.toJson(list);
			out.println(jsonObject);
		}
		if ("cancel".equals(action)) {
			List<Object> list = new ArrayList();
			int plc_ord_no = new Integer(req.getParameter("plc_ord_no"));
			Place_OrderVO place_orderVO = place_orderSvc.getOnePlace_Order(plc_ord_no);
			int pay_stat = place_orderVO.getPay_stat();
			
			List<Place_Order_DetailsVO> detailslist = detailsSvc.getOnePlace_Order_Details(plc_ord_no);
			for (Place_Order_DetailsVO place_order_detailsVO : detailslist) {
				place_order_detailsVO.setPlc_name(placeSvc.getOnePlace(place_order_detailsVO.getPlc_no()).getPlc_name());
			}
			place_orderSvc.updatePlace_Order(plc_ord_no, pay_stat, 4);
			place_orderVO = place_orderSvc.getOnePlace_Order(plc_ord_no);
			place_orderVO.setCamp_name(campSvc.getOneCamp(place_orderVO.getCamp_no()).getCamp_name());
			
			list.add(place_orderVO);
			list.add(detailslist);
			jsonObject = gson.toJson(list);
			out.println(jsonObject);
		}
	}
}
