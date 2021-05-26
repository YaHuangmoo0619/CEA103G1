import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedHashSet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.campsite.model.*;
import com.campsite_collection.model.Camp_CollectionService;
import com.campsite_collection.model.Camp_CollectionVO;
import com.campsite_picture.model.Camp_PictureService;
import com.district.model.*;
import com.feature_list.model.Feature_ListService;
import com.feature_list.model.Feature_ListVO;
import com.place.model.*;
import com.place_order.model.*;
import com.place_order_details.model.*;
import com.google.gson.Gson;
import com.member.model.MemberVO;

public class ValidPlace extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();

		String county = req.getParameter("county").trim();
		System.out.println(county);
		Integer people;
		try {
			people = new Integer(req.getParameter("people"));
		} catch (Exception e) {
			people = 1;
		}
		Camp_PictureService camp_pictureSvc = new Camp_PictureService();
		Place_Order_DetailsService place_order_detailsSvc = new Place_Order_DetailsService();
		Place_OrderService place_orderSvc = new Place_OrderService();
		List<Place_Order_DetailsVO> place_order_detailslist = place_order_detailsSvc.getAll();// ��Ҧ��q����Ө��X��

		for (int i = 0; i < place_order_detailslist.size(); i++) { // ����v�q�沾��
			int ckin_stat = place_orderSvc.getOnePlace_Order(place_order_detailslist.get(i).getPlc_ord_no())
					.getCkin_stat();
			if (ckin_stat == 1 || ckin_stat == 3 || ckin_stat == 4) {
				place_order_detailslist.remove(i);
				if (!(i == 0)) {
					i--;
				}
				if (place_order_detailslist.size() == 0) {
					break;
				}
			}
		}

		LinkedHashSet<Integer> plc_no_set = new LinkedHashSet();// new�@�ө�w�Q�U�q�����s����set

		for (Place_Order_DetailsVO place_order_detailsVO : place_order_detailslist) {
			plc_no_set.add(place_order_detailsVO.getPlc_no());// ��Ҧ��q����Ӫ����s����iset�ñƧǥB������
		}

		PlaceService placeSvc = new PlaceService();
		List<PlaceVO> placelist = placeSvc.getAll();// ��Ҧ������X��

		for (int i = 0; i < placelist.size(); i++) {
			for (Integer plc_no : plc_no_set) {
				if (placelist.get(i).getPlc_no() == plc_no) {// ��w�U�q����첾��
					placelist.remove(placelist.get(i));
					if (!(i == 0)) {
						i--;
					}
					if (placelist.size() == 0) {
						break;
					}
				}
			}
			if (placelist.size() == 0) {
				break;
			}
		}

		List<Place_OrderVO> place_orderlist;
		Set<Integer> plc_ord_no_set = new LinkedHashSet();

		try {
			java.sql.Date startdate = java.sql.Date.valueOf(req.getParameter("startdate").trim());
			java.sql.Date enddate = java.sql.Date.valueOf(req.getParameter("enddate").trim());
			for (Integer plc_no : plc_no_set) {
				place_orderlist = new ArrayList<Place_OrderVO>();
				Boolean flag = true;// �߭ӺX�l

				plc_ord_no_set = place_order_detailsSvc.getOnePlace_Order_Details2(plc_no);// ��@����쪺�Ҧ��q��s����iset
				for (Integer plc_ord_no : plc_ord_no_set) {
					place_orderlist.add(place_orderSvc.getOnePlace_Order(plc_ord_no));
				}
				for (Place_OrderVO place_orderVO : place_orderlist) {// ���@���q��A�J���b�q��checkout�餧��ΰh�Ф�b�q��checkin�餧�e
					if (startdate.after(place_orderVO.getCkout_date())
							|| startdate.equals(place_orderVO.getCkout_date())
							|| enddate.before(place_orderVO.getCkin_date())
							|| enddate.equals(place_orderVO.getCkin_date())) {
						flag = true;
					} else {
						flag = false;// ������ŦX�A�X�l½�ϭ�
						break;
					}
				}
				if (flag) {
					placelist.add(placeSvc.getOnePlace(plc_no));// ����L��i�q�쪺���[�i���list
				}
				place_orderlist = null;
			}
		} catch (Exception e) {
		}
		
		Set<PlaceVO> newplacelist = new HashSet();
		for (int i = 0; i < placelist.size(); i++) {
			int count = 0;// �έp��ϳѾl���i�e�ǤH�ƥ�
			Integer camp_no = placelist.get(i).getCamp_no();
			for (int j = 0; j < placelist.size(); j++) {
				if (placelist.get(j).getCamp_no() == camp_no) {// �����Ͻs���A�@�P�h�έp�H��
					count += placelist.get(j).getMax_ppl();
				}
			}
			for (int j = 0; j < placelist.size(); j++) {
				if (placelist.get(j).getCamp_no() == camp_no && count > people) {
					newplacelist.add(placelist.get(j));
				}else {
					continue;
				}			
			}
		}
		placelist = new ArrayList();
		placelist.addAll(newplacelist);
		
		CampService campSvc = new CampService();
		List<CampVO> camplist = campSvc.getAll();

		if (!county.equals("����")) { // �p�G����ܿ���

			DistrictService distSvc = new DistrictService();
			List<DistrictVO> distlist = distSvc.getAll();

			List<Integer> dist_no_list = new ArrayList();
			List<Integer> camp_no_list = new ArrayList();

			for (DistrictVO districtVO : distlist) { // ��ӿ����Ҧ���F�Ͻs����i��F�Ͻs��list
				if (districtVO.getCty().equals(county)) {
					dist_no_list.add(districtVO.getDist_no());
					System.out.println(dist_no_list);
				}
			}
			for (CampVO campVO : camplist) {// ���ݩ�ӿ�������Ϫ��s����i��Ͻs��list
				for (Integer dist_no : dist_no_list) {
					if (campVO.getDist_no() == dist_no) {
						camp_no_list.add(campVO.getCamp_no());
					}
				}
			}
			System.out.println(camp_no_list);

			for (int i = 0; i < placelist.size(); i++) {
				Boolean flag = false;// �߭ӺX�l
				for (Integer camp_no : camp_no_list) {
					if (placelist.get(i).getCamp_no() == camp_no) {
						flag = true;// �ŦX��Ͻs�������A�X�l½����
					}
				}
				if (!flag) {// �j��]���X�l�����ϭ��h���������
					placelist.remove(placelist.get(i));
					if (placelist.size() == 0) {
						break;
					}
					;
					i--;

				}
			}

		}

		Set<Integer> campno = new HashSet();

		for (PlaceVO placeVO : placelist) {
			campno.add(placeVO.getCamp_no());
		}

		camplist = new ArrayList<CampVO>();
		for (Integer camp_no : campno) {
			CampVO campVO = new CampVO();
			campVO.setCamp_no(camp_no);
			campVO.setReview_Status(campSvc.getOneCamp(camp_no).getReview_Status());
			campVO.setCampsite_Status(campSvc.getOneCamp(camp_no).getCampsite_Status());
			campVO.setCamp_name(campSvc.getOneCamp(camp_no).getCamp_name());
			campVO.setAddress(campSvc.getOneCamp(camp_no).getAddress());
			camplist.add(campVO);
		}
		
		List<CampVO> pass = new ArrayList();
		for (CampVO campVO : camplist) {
			if((int)campVO.getReview_Status() == 1 && (int)campVO.getCampsite_Status() == 0) {
				pass.add(campVO);
			}
		}
		camplist = pass;
		
		for (CampVO campVO : camplist) {
			List<String> firstPic = camp_pictureSvc.getCamp_Picture(campVO.getCamp_no());
			if (firstPic.size() == 0) {// �Ȯ�
				firstPic.add("/CEA103G1/front-images/campionLogoShort.png");
			}
			campVO.setFirst_pic(firstPic.get(0));
		}

		for (CampVO campVO : camplist) {
			List<Integer> low_pc = new ArrayList();
			List<PlaceVO> plclist = placeSvc.getByCamp(campVO.getCamp_no());
			for (PlaceVO placeVO : plclist) {
				low_pc.add(placeVO.getPc_wkdy());
			}
			Collections.sort(low_pc);
			campVO.setLow_pc(low_pc.get(0));
		}

		for (CampVO campVO : camplist) {
			campVO = seeIfCollect(req, campVO);
		}
		String jsonObject = gson.toJson(camplist);
		out.println(jsonObject);
	}

	public CampVO seeIfCollect(HttpServletRequest req, CampVO campVO) {
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("memberVO");
		if (member == null) {
			campVO.setCollected(1);
			return campVO;
		}
		System.out.println("����2");
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
