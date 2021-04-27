import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.campsite.model.*;
import com.district.model.*;
import com.place.model.*;
import com.place_order.model.*;
import com.place_order_details.model.*;

public class ValidPlace extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
//		res.setContentType("application/json");
//		PrintWriter out = res.getWriter();

		java.sql.Date startdate = java.sql.Date.valueOf(req.getParameter("startdate").trim());
		java.sql.Date enddate = java.sql.Date.valueOf(req.getParameter("enddate").trim());
		String county = req.getParameter("county");
		Integer people = new Integer(req.getParameter("people"));
		if (people == null) {
			people = 1;
		}

		Place_Order_DetailsService place_order_detailsSvc = new Place_Order_DetailsService();
		List<Place_Order_DetailsVO> place_order_detailslist = place_order_detailsSvc.getAll();// 把所有訂單明細取出來
		TreeSet<Integer> plc_no_set = new TreeSet();// new一個放已被下訂之營位編號的set

		for (Place_Order_DetailsVO place_order_detailsVO : place_order_detailslist) {
			plc_no_set.add(place_order_detailsVO.getPlc_no());// 把所有訂單明細的營位編號放進set並排序且不重複
		}

		PlaceService placeSvc = new PlaceService();
		List<PlaceVO> placelist = placeSvc.getAll();// 把所有營位取出來

		for (PlaceVO placeVO : placelist) {
			for (Integer plc_no : plc_no_set) {
				if (placeVO.getPlc_no() == plc_no) {// 把已下訂之營位移除
					placelist.remove(placeVO);
				}
			}
		}

		Place_OrderService place_orderSvc = new Place_OrderService();
		List<Place_OrderVO> place_orderlist = new ArrayList();
		Set<Integer> plc_ord_no_set = new TreeSet();

		for (Integer plc_no : plc_no_set) {

			Boolean flag = true;// 立個旗子

			plc_ord_no_set = place_order_detailsSvc.getOnePlace_Order_Details2(plc_no);// 把一個營位的所有訂單編號放進set
			for (Integer plc_ord_no : plc_ord_no_set) {
				place_orderlist.add(place_orderSvc.getOnePlace_Order(plc_ord_no));
			}
			for (Place_OrderVO place_orderVO : place_orderlist) {// 比對一筆訂單，入住日在checkout日之後或退房日在checkin日之前
				if (startdate.after(place_orderVO.getCkout_date()) || enddate.before(place_orderVO.getCkin_date())) {
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
				for (PlaceVO placeVO : placelist) {
					if (placeVO.getCamp_no() == camp_no) {
						placelist.remove(placeVO);
					}
				}
				i--;// 第i位也會跟著被移除，所以要把i維持原來數字
			}
		}

		if (!county.equals("不拘")) { // 如果有選擇縣市

			CampService campSvc = new CampService();
			List<CampVO> camplist = campSvc.getAll();
			DistrictService distSvc = new DistrictService();
			List<DistrictVO> distlist = distSvc.getAll();

			List<Integer> dist_no_list = new ArrayList();
			List<Integer> camp_no_list = new ArrayList();

			for (DistrictVO districtVO : distlist) { // 把該縣市所有行政區編號放進行政區編號list
				if (districtVO.getCty().equals("county")) {
					dist_no_list.add(districtVO.getDist_no());
				}
			}

			for (CampVO campVO : camplist) {// 把屬於該縣市的營區的編號放進營區編號list
				for (Integer dist_no : dist_no_list) {
					if (campVO.getCamp_no() == dist_no) {
						camp_no_list.add(campVO.getCamp_no());
					}
				}
			}

			for (PlaceVO placeVO : placelist) {

				Boolean flag = false;// 立個旗子

				for (Integer camp_no : camp_no_list) {
					if (placeVO.getCamp_no() == camp_no) {
						flag = true;// 符合營區編號的營位，旗子翻正面
					}
				}
				if (!flag) {// 迴圈跑完旗子仍為反面則移除該營位
					placelist.remove(placeVO);
				}
			}

		}
		for (PlaceVO placeVO : placelist) {// 測試用
			System.out.println(placeVO.getPlc_name() + " " + placeVO.getCamp_no());
//			 JSONObject jsonObject = JSONObject.fromObject(placeVO);
		}
		
//		JSONArray jsonArray = JSONArray.fromObject(placelist);
//		out.println();
		req.setAttribute("placelist", placelist);
	}
}
