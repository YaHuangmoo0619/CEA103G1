package com.feature_list.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campsite.model.CampService;
import com.campsite.model.CampVO;
import com.campsite_collection.model.Camp_CollectionService;
import com.campsite_collection.model.Camp_CollectionVO;
import com.campsite_feature.model.Camp_FeatureService;
import com.campsite_feature.model.Camp_FeatureVO;
import com.campsite_picture.model.Camp_PictureService;
import com.feature_list.model.Feature_ListService;
import com.feature_list.model.Feature_ListVO;
import com.google.gson.Gson;
import com.member.model.MemberVO;
import com.place.model.PlaceService;
import com.place.model.PlaceVO;

@WebServlet("/feature_list/featurelist.do")
public class GetAllFeature_List extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String action = req.getParameter("action");
		
		Camp_PictureService camp_pictureSvc = new Camp_PictureService();
		PlaceService placeSvc = new PlaceService();
		
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
			
			Set<CampVO> pass = new LinkedHashSet();
			for (CampVO campVO : campset) {
				if((int)campVO.getReview_Status() == 1 && (int)campVO.getCampsite_Status() == 0) {
					pass.add(campVO);
				}
			}
			campset = pass;
			
			for(CampVO campVO : campset) {
				List<String> firstPic = camp_pictureSvc.getCamp_Picture(campVO.getCamp_no());
				if (firstPic.size() == 0) {
					firstPic.add("/CEA103G1/front-images/campionLogoShort.png");
				}
				campVO.setFirst_pic(firstPic.get(0));
			}
			
			for (CampVO campVO : campset) {
				campVO = seeIfCollect(req, campVO);
				List<Integer> low_pc = new ArrayList();
				List<PlaceVO> plclist = placeSvc.getByCamp(campVO.getCamp_no());
				if (plclist.size() == 0) {// ¼È®É
					break;
				}
				for (PlaceVO placeVO : plclist) {
					low_pc.add(placeVO.getPc_wkdy());
				}
				Collections.sort(low_pc);
				System.out.println(low_pc);
				campVO.setLow_pc(low_pc.get(0));
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
	public CampVO seeIfCollect(HttpServletRequest req, CampVO campVO) {
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("memberVO");
		if (member == null) {
			campVO.setCollected(1);

			return campVO;
		}

		Camp_CollectionService collectionSvc = new Camp_CollectionService();
		List<Camp_CollectionVO> collectionlist = collectionSvc.getAll();

		for (Camp_CollectionVO camp_collectionVO : collectionlist) {

			if ((int) campVO.getCamp_no() == (int) camp_collectionVO.getCamp_no()
					&& (int) member.getMbr_no() == (int) camp_collectionVO.getMbr_no()) {
				campVO.setCollected(0);
				return campVO;
			}
		}
		campVO.setCollected(1);

		return campVO;
	}
}
