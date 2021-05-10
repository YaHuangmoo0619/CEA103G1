package com.district.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.district.model.DistrictService;
import com.district.model.DistrictVO;
import com.google.gson.Gson;

@WebServlet("/district/district.do")
public class DistrictServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		
		DistrictService districtSvc = new DistrictService();
		List<DistrictVO> districtlist = districtSvc.getAll();
		Set<String> city = new HashSet();
		for(DistrictVO districtVO: districtlist) {
			city.add(districtVO.getCty());
		}
		
		String jsonObject = gson.toJson(city);
		out.println(jsonObject);
	}

}
