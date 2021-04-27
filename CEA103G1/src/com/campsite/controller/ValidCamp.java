package com.campsite.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campsite.model.CampService;
import com.campsite.model.CampVO;
import com.district.model.DistrictService;
import com.district.model.DistrictVO;
import com.google.gson.Gson;
import com.place.model.PlaceService;
import com.place_order_details.model.Place_Order_DetailsService;

@WebServlet("/campsite/validcamp.do")
public class ValidCamp extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();

		String action = req.getParameter("action");
		Integer people;
		try {
			people = new Integer(req.getParameter("people"));
		} catch (NumberFormatException e) {
			people = 1;
		}

		CampService campSvc = new CampService();
		PlaceService placeSvc = new PlaceService();
		Place_Order_DetailsService detailsSvc = new Place_Order_DetailsService();
		
		if ("search".equals(action)) {
			String county = req.getParameter("county").trim();
			if (!county.equals("不拘")) { // 如果有選擇縣市
				DistrictService distSvc = new DistrictService();
				
				List<CampVO> camplist = campSvc.getAll();
				List<DistrictVO> distlist = distSvc.getAll();

				List<Integer> dist_no_list = new ArrayList();
				List<Integer> camp_no_list = new ArrayList();

				for (DistrictVO districtVO : distlist) { // 把該縣市所有行政區編號放進行政區編號list
					if (districtVO.getCty().equals(county)) {
						dist_no_list.add(districtVO.getDist_no());
					}
				}
				for (CampVO campVO : camplist) {// 把屬於該縣市的營區的編號放進營區編號list
					for (Integer dist_no : dist_no_list) {
						if (campVO.getCamp_no() == dist_no) {
							camp_no_list.add(campVO.getCamp_no());
						}
					}
				}
				for(Integer campno : camp_no_list) {
					
				}
			}
		}
		if ("prefer".equals(action)) {
			Integer camp_no = new Integer(req.getParameter("camp_no"));
		}
		if ("browse".equals(action)) {
			Integer camp_no = new Integer(req.getParameter("camp_no"));
			
		}
	}

	public void compareDate(HttpServletRequest req) throws IOException {
		java.sql.Date startdate = java.sql.Date.valueOf(req.getParameter("startdate"));
		java.sql.Date enddate = java.sql.Date.valueOf(req.getParameter("enddate"));

	}
}
