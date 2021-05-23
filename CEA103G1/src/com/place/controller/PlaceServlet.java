package com.place.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campsite.model.CampService;
import com.campsite.model.CampVO;
import com.google.gson.Gson;
import com.place.model.PlaceService;
import com.place.model.PlaceVO;

@WebServlet("/place/place.do")
public class PlaceServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();

		PlaceService placeSvc = new PlaceService();
		String action = req.getParameter("action");
System.out.println(action);
		if ("updatestat".equals(action)) {
			int plc_no = new Integer(req.getParameter("plc_no"));
			int open_stat = new Integer(req.getParameter("open_stat"));
			placeSvc.updateStat(plc_no, open_stat);
			if (open_stat == 0) {
				String jsonObject = gson.toJson("營位不開放");
				out.println(jsonObject);
			} else {
				String jsonObject = gson.toJson("營位開放");
				out.println(jsonObject);
			}
		}
		if ("insert".equals(action)) {
System.out.println("hey");
			int camp_no = new Integer(req.getParameter("camp_no"));
			List<PlaceVO> placelist = null;
			Integer plc_amt = new Integer(req.getParameter("plc_amt"));
			placelist = new ArrayList<PlaceVO>();
			for (int i = 0; i <= plc_amt; i++) {
				String[] plc = req.getParameterValues("plc" + i);
				for (int j = 1; j <= new Integer(plc[1]); j++) {
					PlaceVO placeVO = new PlaceVO();
					placeVO.setPlc_name(plc[0] + j);
					placeVO.setPpl(new Integer(plc[2]));
					placeVO.setMax_ppl(new Integer(plc[3]));
					placeVO.setPc_wkdy(new Integer(plc[4]));
					placeVO.setPc_wknd(new Integer(plc[5]));
					placelist.add(placeVO);
				}
			}
			for(PlaceVO placeVO : placelist) {
				placeSvc.addPlace(camp_no, placeVO.getPlc_name(), placeVO.getPpl(), placeVO.getMax_ppl(), placeVO.getPc_wkdy(), placeVO.getPc_wknd());
			}
			CampService campSvc = new CampService();
			CampVO campVO = campSvc.getOneCamp(camp_no);
			req.setAttribute("campVO", campVO); // 資料庫取出的empVO物件,存入req
			String url = "/front-end/campsite/listOneCamp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
	}
}
