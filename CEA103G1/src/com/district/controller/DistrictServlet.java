package com.district.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.district.model.DistrictService;
import com.district.model.DistrictVO;
import com.google.gson.Gson;
import com.place.model.PlaceVO;

@WebServlet("/district/district.do")
public class DistrictServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String[] taiwan = {"�x�_��","�򶩥�","�s�_��","�s����","�y����","�s�˥�","�s�˿�","��饫","�]�߿�","�x����","���ƿ�","�n�뿤","�Ÿq��","�Ÿq��","���L��","�x�n��","������","���","������","�̪F��","�x�F��","�Ὤ��"};
		DistrictService districtSvc = new DistrictService();
		List<DistrictVO> districtlist = districtSvc.getAll();
		Set<String> city = new LinkedHashSet();
		for(DistrictVO districtVO: districtlist) {
			for(int i = 0; i < taiwan.length; i++) {
				if(taiwan[i].equals(districtVO.getCty())) {
					districtVO.setOrder(i);
				}
			}
		}
		try {
			Collections.sort(districtlist, new Comparator<Object>() {
				public int compare(Object a, Object b) {
					int one = ((DistrictVO) a).getOrder();
					int two = ((DistrictVO) b).getOrder();
					return one - two;
				}
			});
		} catch (Exception e) {
		}
		for(DistrictVO districtVO: districtlist) {
			city.add(districtVO.getCty());
		}
		String jsonObject = gson.toJson(city);
		out.println(jsonObject);
	}

}
