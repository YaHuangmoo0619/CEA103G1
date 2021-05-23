package com.place.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.place.model.PlaceService;

@WebServlet("/place/place.do")
public class PlaceServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();

		String action = req.getParameter("action");
		if ("updatestat".equals(action)) {
			int plc_no = new Integer(req.getParameter("plc_no"));
System.out.println(plc_no);
			int open_stat = new Integer(req.getParameter("open_stat"));
System.out.println(open_stat);
			PlaceService placeSvc = new PlaceService();
			placeSvc.updateStat(plc_no, open_stat);
			if (open_stat == 0) {
				String jsonObject = gson.toJson("營位不開放");
				out.println(jsonObject);
			} else {
				String jsonObject = gson.toJson("營位開放");
				out.println(jsonObject);
			}
		}
	}
}
