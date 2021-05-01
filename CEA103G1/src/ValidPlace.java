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

		java.sql.Date startdate = java.sql.Date.valueOf(req.getParameter("startdate").trim());
		java.sql.Date enddate = java.sql.Date.valueOf(req.getParameter("enddate").trim());
		String county = req.getParameter("county").trim();
		Integer people;
		try {
			people = new Integer(req.getParameter("people"));
		} catch (NumberFormatException e) {
			people = 1;
		}
		
		Place_Order_DetailsService place_order_detailsSvc = new Place_Order_DetailsService();
		List<Place_Order_DetailsVO> place_order_detailslist = place_order_detailsSvc.getAll();// 把所有訂單明細取出來
		LinkedHashSet<Integer> plc_no_set = new LinkedHashSet();// new一個放已被下訂之營位編號的set

		for (Place_Order_DetailsVO place_order_detailsVO : place_order_detailslist) {
			plc_no_set.add(place_order_detailsVO.getPlc_no());// 把所有訂單明細的營位編號放進set並排序且不重複
		}

		PlaceService placeSvc = new PlaceService();
		List<PlaceVO> placelist = placeSvc.getAll();// 把所有營位取出來

		for (int i = 0; i < placelist.size(); i++) {
			for (Integer plc_no : plc_no_set) {
				if (placelist.get(i).getPlc_no() == plc_no) {// 把已下訂之營位移除
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

		Place_OrderService place_orderSvc = new Place_OrderService();
		List<Place_OrderVO> place_orderlist;
		Set<Integer> plc_ord_no_set = new LinkedHashSet();
		for (Integer plc_no : plc_no_set) {
			place_orderlist = new ArrayList<Place_OrderVO>();
			Boolean flag = true;// 立個旗子

			plc_ord_no_set = place_order_detailsSvc.getOnePlace_Order_Details2(plc_no);// 把一個營位的所有訂單編號放進set
			for (Integer plc_ord_no : plc_ord_no_set) {
				place_orderlist.add(place_orderSvc.getOnePlace_Order(plc_ord_no));
			}
			for (Place_OrderVO place_orderVO : place_orderlist) {// 比對一筆訂單，入住日在訂單checkout日之後或退房日在訂單checkin日之前
				if (startdate.after(place_orderVO.getCkout_date()) || startdate.equals(place_orderVO.getCkout_date())
						|| enddate.before(place_orderVO.getCkin_date())
						|| enddate.equals(place_orderVO.getCkin_date())) {
					flag = true;
				} else {
					flag = false;// 日期不符合，旗子翻反面
					break;
				}
			}
			if (flag) {
				placelist.add(placeSvc.getOnePlace(plc_no));// 把比對過後可訂位的營位加進營位list
			}
			place_orderlist = null;
		}

		for (int i = 0; i < placelist.size(); i++) {
			int count = 0;// 統計營區剩餘營位可容納人數用
			Integer camp_no = placelist.get(i).getCamp_no();
			for (PlaceVO placeVO : placelist) {
				if (placeVO.getCamp_no() == camp_no) {// 比對營區編號，一致則統計人數
					count += placeVO.getPpl();
				}
			}
			if (count < people) {// 如果統計人數小於輸入人數則將該營區所有營位移除
				for (int j = 0; i < placelist.size(); j++) {
					if (placelist.get(j).getCamp_no() == camp_no) {
						placelist.remove(placelist.get(j));
						if (!(j == 0)) {
							j--;
						}
					}
				}
				if (!(i == 0)) {
					i--;
				} // 第i位也會跟著被移除，所以要把i維持原來數字
			}
		}

		CampService campSvc = new CampService();
		List<CampVO> camplist = campSvc.getAll();

		if (!county.equals("不拘")) { // 如果有選擇縣市

			DistrictService distSvc = new DistrictService();
			List<DistrictVO> distlist = distSvc.getAll();

			List<Integer> dist_no_list = new ArrayList();
			List<Integer> camp_no_list = new ArrayList();

			for (DistrictVO districtVO : distlist) { // 把該縣市所有行政區編號放進行政區編號list
				if (districtVO.getCty().equals(county)) {
					dist_no_list.add(districtVO.getDist_no());
					System.out.println(dist_no_list);
				}
			}
			for (CampVO campVO : camplist) {// 把屬於該縣市的營區的編號放進營區編號list
				for (Integer dist_no : dist_no_list) {
					if (campVO.getDist_no() == dist_no) {
						camp_no_list.add(campVO.getCamp_no());
					}
				}
			}
			System.out.println(camp_no_list);
			
			for (int i = 0; i < placelist.size(); i++) {
				Boolean flag = false;// 立個旗子
				for (Integer camp_no : camp_no_list) {
					if (placelist.get(i).getCamp_no() == camp_no) {
						flag = true;// 符合營區編號的營位，旗子翻正面
					}
				}
				if (!flag) {// 迴圈跑完旗子仍為反面則移除該營位
					placelist.remove(placelist.get(i));
					if (placelist.size() == 0) {
						break;
					}
					;
					i--;

				}
			}

		}

		for (PlaceVO placeVO : placelist) {
			System.out.println(placeVO.getPlc_name());
		}
		
		Set<Integer> campno = new HashSet();

		for (PlaceVO placeVO : placelist) {
			System.out.println(placeVO.getPlc_name());
			campno.add(placeVO.getCamp_no());
		}

		camplist = new ArrayList<CampVO>();
		for (Integer camp_no : campno) {
			CampVO campVO = new CampVO();
			campVO.setCamp_no(camp_no);
			campVO.setCamp_name(campSvc.getOneCamp(camp_no).getCamp_name());
			campVO.setAddress(campSvc.getOneCamp(camp_no).getAddress());
			camplist.add(campVO);
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
		MemberVO member = (MemberVO) session.getAttribute("member");
		if(member == null) {
			campVO.setCollected(1);
System.out.println("測試1");
			return campVO;
		}
System.out.println("測試2");
		Camp_CollectionService collectionSvc = new Camp_CollectionService();
		List<Camp_CollectionVO> collectionlist = collectionSvc.getAll();

		for (Camp_CollectionVO camp_collectionVO : collectionlist) {
System.out.println(camp_collectionVO.getCamp_no() + " " + camp_collectionVO.getMbr_no());
			if ((int)campVO.getCamp_no() == (int)camp_collectionVO.getCamp_no()
					&& (int)member.getMbr_no() == (int)camp_collectionVO.getMbr_no()) {
				campVO.setCollected(0);
				return campVO;
			}
		}
		campVO.setCollected(1);
		
		return campVO;
	}
}
