package com.campsite_collection.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campsite.model.*;
import com.campsite_collection.model.*;
import com.campsite_picture.model.Camp_PictureService;
import com.google.gson.Gson;
import com.member.model.MemberVO;

@WebServlet("/campsite_colection/collection.do")
public class Camp_CollectionServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		
		int mbr_no = ((MemberVO) req.getSession().getAttribute("memberVO")).getMbr_no();
		String action = req.getParameter("action");
		
		Camp_CollectionService collectionSvc = new Camp_CollectionService();
		Camp_PictureService camp_pictureSvc = new Camp_PictureService();
		if("add".equals(action)) {
			int camp_no = new Integer(req.getParameter("camp_no"));
			collectionSvc.addCamp_Collection(camp_no, mbr_no);
			String jsonObject = gson.toJson("已加入收藏");
			out.println(jsonObject);
		}
		if("delete".equals(action)) {
			int camp_no = new Integer(req.getParameter("camp_no"));
			collectionSvc.deleteCamp_Collection(camp_no, mbr_no);
			String jsonObject = gson.toJson("已移除收藏");
			out.println(jsonObject);
		}
		if("getAll".equals(action)) {
			CampService campSvc = new CampService();
			List<CampVO> camplist = new ArrayList();
			List<Camp_CollectionVO> collectionlist = collectionSvc.getAll();
			for(Camp_CollectionVO collectionVO : collectionlist) {
				if(collectionVO.getMbr_no() == mbr_no) {
					camplist.add(campSvc.getOneCamp(collectionVO.getCamp_no()));
				}
			}
			for(CampVO campVO : camplist) {
				campVO = seeIfCollect(req, campVO);
			}
			for (CampVO campVO : camplist) {
				List<String> firstPic = camp_pictureSvc.getCamp_Picture(campVO.getCamp_no());
				if (firstPic.size() == 0) {// 暫時
					firstPic.add("/CEA103G1/front-images/campionLogoShort.png");
				}
				campVO.setFirst_pic(firstPic.get(0));
			}
			
			String jsonObject = gson.toJson(camplist);
			out.println(jsonObject);
		}
	}
	public CampVO seeIfCollect(HttpServletRequest req, CampVO campVO) {
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		if (member == null) {
			campVO.setCollected(1);
			return campVO;
		}
		System.out.println("測試2");
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
