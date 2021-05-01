package com.feature_list.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campsite.model.CampService;
import com.campsite.model.CampVO;
import com.campsite_feature.model.Camp_FeatureService;
import com.campsite_feature.model.Camp_FeatureVO;
import com.feature_list.model.Feature_ListService;
import com.feature_list.model.Feature_ListVO;
import com.google.gson.Gson;

@WebServlet("/feature_list/featurelist.do")
public class GetAllFeature_List extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String action = req.getParameter("action");

		if ("getCamp".equals(action)) {
			Integer camp_fl_no = new Integer(req.getParameter("camp_fl_no"));
			Camp_FeatureService camp_featureSvc = new Camp_FeatureService();
			List<Camp_FeatureVO> camp_featurelist = camp_featureSvc.getAll();
			CampService campSvc = new CampService();

			Set<CampVO> campset = new LinkedHashSet();

			for (Camp_FeatureVO camp_featureVO : camp_featurelist) {
				if ((int)camp_fl_no == (int)camp_featureVO.getCamp_fl_no()) {
					campset.add(campSvc.getOneCamp(camp_featureVO.getCamp_no()));
				}
			}
			String jsonObject = gson.toJson(campset);
			out.println(jsonObject);
		} else {
			Feature_ListService feature_listSvc = new Feature_ListService();
			List<Feature_ListVO> feature_list = feature_listSvc.getAll();

			String jsonObject = gson.toJson(feature_list);
			out.println(jsonObject);
		}
	}
}
