package com.campsite.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campsite.model.*;
import com.campsite_collection.model.Camp_CollectionService;
import com.campsite_collection.model.Camp_CollectionVO;
import com.campsite_feature.model.Camp_FeatureService;
import com.campsite_feature.model.Camp_FeatureVO;
import com.campsite_picture.model.Camp_PictureService;
import com.campsite_picture.model.Camp_PictureVO;
import com.feature_list.model.Feature_ListService;
import com.feature_list.model.Feature_ListVO;
import com.google.gson.Gson;
import com.member.model.MemberVO;
import com.place.model.*;
import com.place_order.model.Place_OrderService;
import com.place_order_details.model.*;

@WebServlet("/campsite/CampInfo.do")
public class CampInfo extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		String jsonObject;
		Gson gson = new Gson();

		CampService campSvc = new CampService();
		Camp_PictureService camp_pictureSvc = new Camp_PictureService();
		List<Object> list = new LinkedList<Object>();

		if (!req.getQueryString().contains("action") || !(req.getParameter("startdate") == null)) {
			Integer camp_no = new Integer(req.getParameter("camp_no"));

			PlaceService placeSvc = new PlaceService();
			Place_OrderService place_orderSvc = new Place_OrderService();
			Place_Order_DetailsService place_order_detailsSvc = new Place_Order_DetailsService();

			CampVO campVO = campSvc.getOneCamp(camp_no);
			List<PlaceVO> placelist = placeSvc.getByCamp(camp_no);

			Set<Integer> plc_ord_set = new LinkedHashSet<Integer>();
			
			try {
				java.sql.Date startdate = java.sql.Date.valueOf(req.getParameter("startdate"));
				java.sql.Date enddate = java.sql.Date.valueOf(req.getParameter("enddate"));
				for (int i = 0; i < placelist.size(); i++) {
					Boolean flag = true;// �߭ӺX�l
					plc_ord_set = place_order_detailsSvc.getOnePlace_Order_Details2(placelist.get(i).getPlc_no());// �@���s��@����쪺�Ҧ��q��
					for (Integer plc_ord_no : plc_ord_set) {
						int ckin_stat = place_orderSvc.getOnePlace_Order(plc_ord_no).getCkin_stat();
						if(ckin_stat == 1 || ckin_stat == 3 || ckin_stat == 4) { //���v�q�檽�����L
							continue;
						}
						Date ckin = place_orderSvc.getOnePlace_Order(plc_ord_no).getCkin_date();
						Date ckout = place_orderSvc.getOnePlace_Order(plc_ord_no).getCkout_date();
						if (startdate.after(ckout) || startdate.equals(ckout) || enddate.before(ckin)
								|| enddate.equals(ckin)) {
							flag = true;// ����ŦX�A�X�l��������
						} else {
							flag = false;// ������ŦX�A�X�l½�ϭ�
							break;
						}
					}
					if (!flag) {
						placelist.remove(i);// �j�鵲���Y�X�l���ϭ��h���������
						i--;
					}
					plc_ord_set = null;
				}
			} catch (Exception e) {
			}
			Collections.sort(placelist, new Comparator<Object>() {
				public int compare(Object a, Object b) {
					int one = ((PlaceVO) a).getPlc_no();
					int two = ((PlaceVO) b).getPlc_no();
					return one - two;
				}
			});
			campVO = seeIfCollect(req, campVO);
			
			list.add(campVO);
			list.add(placelist);
			list.add(findFeature(campVO));
			list.add(camp_pictureSvc.getCamp_Picture(camp_no));
			
		} else {
			String action = req.getParameter("action");
			if ("getall".equals(action)) {
				PlaceService placeSvc = new PlaceService();
				List<CampVO> camplist = campSvc.getAll();

				for (CampVO campVO : camplist) {
					campVO = seeIfCollect(req, campVO);
				}

				for (CampVO campVO : camplist) {
					list.add(campVO);
				}

				for (Object campVO : list) {
					List<String> firstPic = camp_pictureSvc.getCamp_Picture(((CampVO) campVO).getCamp_no());
					if (firstPic.size() == 0) {
						firstPic.add("/CEA103G1/front-images/campionLogoShort.png");
					}
					((CampVO) campVO).setFirst_pic(firstPic.get(0));
				}

				for (Object campVO : list) {
					List<Integer> low_pc = new ArrayList();
					List<PlaceVO> plclist = placeSvc.getByCamp(((CampVO) campVO).getCamp_no());
					if (plclist.size() == 0) {// �Ȯ�
						break;
					}
					for (PlaceVO placeVO : plclist) {
						low_pc.add(placeVO.getPc_wkdy());
					}
					Collections.sort(low_pc);
					System.out.println(low_pc);
					((CampVO) campVO).setLow_pc(low_pc.get(0));
				}

			} else if ("getone".equals(action)) {
				Integer camp_no = new Integer(req.getParameter("camp_no"));
				CampVO campVO = campSvc.getOneCamp(camp_no);
				campVO = seeIfCollect(req, campVO);

				list.add(campVO);
				list.add(findFeature(campVO));
				list.add(camp_pictureSvc.getCamp_Picture(camp_no));
			}
		}
		jsonObject = gson.toJson(list);
		out.println(jsonObject);
	}

	public List<String> getPhoto(int camp_no) {
		Camp_PictureService camp_pictureSvc = new Camp_PictureService();
		List<String> picturelist = camp_pictureSvc.getCamp_Picture(camp_no);
		return picturelist;
	}

	public List<Feature_ListVO> findFeature(CampVO campVO) {
		Feature_ListService feature_listSvc = new Feature_ListService();
		Camp_FeatureService camp_featureSvc = new Camp_FeatureService();
		List<Camp_FeatureVO> camp_featurelist = camp_featureSvc.getAll();
		List<Feature_ListVO> featurelist = new ArrayList();
		for (Camp_FeatureVO camp_featureVO : camp_featurelist) {
			if ((int) campVO.getCamp_no() == (int) camp_featureVO.getCamp_no()) {
				Feature_ListVO feature_listVO = feature_listSvc.getOneFeature_List(camp_featureVO.getCamp_fl_no());
				featurelist.add(feature_listVO);
			}
		}
		for (Feature_ListVO feature_listVO : featurelist) {
			System.out.println(feature_listVO.getCamp_fl_name());
		}
		return featurelist;
	}

	public CampVO seeIfCollect(HttpServletRequest req, CampVO campVO) {
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
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
