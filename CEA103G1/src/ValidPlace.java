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
		List<Place_Order_DetailsVO> place_order_detailslist = place_order_detailsSvc.getAll();// ��Ҧ��q����Ө��X��
		TreeSet<Integer> plc_no_set = new TreeSet();// new�@�ө�w�Q�U�q�����s����set

		for (Place_Order_DetailsVO place_order_detailsVO : place_order_detailslist) {
			plc_no_set.add(place_order_detailsVO.getPlc_no());// ��Ҧ��q����Ӫ����s����iset�ñƧǥB������
		}

		PlaceService placeSvc = new PlaceService();
		List<PlaceVO> placelist = placeSvc.getAll();// ��Ҧ������X��

		for (PlaceVO placeVO : placelist) {
			for (Integer plc_no : plc_no_set) {
				if (placeVO.getPlc_no() == plc_no) {// ��w�U�q����첾��
					placelist.remove(placeVO);
				}
			}
		}

		Place_OrderService place_orderSvc = new Place_OrderService();
		List<Place_OrderVO> place_orderlist = new ArrayList();
		Set<Integer> plc_ord_no_set = new TreeSet();

		for (Integer plc_no : plc_no_set) {

			Boolean flag = true;// �߭ӺX�l

			plc_ord_no_set = place_order_detailsSvc.getOnePlace_Order_Details2(plc_no);// ��@����쪺�Ҧ��q��s����iset
			for (Integer plc_ord_no : plc_ord_no_set) {
				place_orderlist.add(place_orderSvc.getOnePlace_Order(plc_ord_no));
			}
			for (Place_OrderVO place_orderVO : place_orderlist) {// ���@���q��A�J���bcheckout�餧��ΰh�Ф�bcheckin�餧�e
				if (startdate.after(place_orderVO.getCkout_date()) || enddate.before(place_orderVO.getCkin_date())) {
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

		for (int i = 0; i < placelist.size(); i++) {
			int count = 0;// �έp��ϳѾl���i�e�ǤH�ƥ�
			Integer camp_no = placelist.get(i).getCamp_no();
			for (PlaceVO placeVO : placelist) {
				if (placeVO.getCamp_no() == camp_no) {// �����Ͻs���A�@�P�h�έp�H��
					count += placeVO.getPpl();
				}
			}
			if (count < people) {// �p�G�έp�H�Ƥp���J�H�ƫh�N����ϩҦ���첾��
				for (PlaceVO placeVO : placelist) {
					if (placeVO.getCamp_no() == camp_no) {
						placelist.remove(placeVO);
					}
				}
				i--;// ��i��]�|��۳Q�����A�ҥH�n��i������ӼƦr
			}
		}

		if (!county.equals("����")) { // �p�G����ܿ���

			CampService campSvc = new CampService();
			List<CampVO> camplist = campSvc.getAll();
			DistrictService distSvc = new DistrictService();
			List<DistrictVO> distlist = distSvc.getAll();

			List<Integer> dist_no_list = new ArrayList();
			List<Integer> camp_no_list = new ArrayList();

			for (DistrictVO districtVO : distlist) { // ��ӿ����Ҧ���F�Ͻs����i��F�Ͻs��list
				if (districtVO.getCty().equals("county")) {
					dist_no_list.add(districtVO.getDist_no());
				}
			}

			for (CampVO campVO : camplist) {// ���ݩ�ӿ�������Ϫ��s����i��Ͻs��list
				for (Integer dist_no : dist_no_list) {
					if (campVO.getCamp_no() == dist_no) {
						camp_no_list.add(campVO.getCamp_no());
					}
				}
			}

			for (PlaceVO placeVO : placelist) {

				Boolean flag = false;// �߭ӺX�l

				for (Integer camp_no : camp_no_list) {
					if (placeVO.getCamp_no() == camp_no) {
						flag = true;// �ŦX��Ͻs�������A�X�l½����
					}
				}
				if (!flag) {// �j��]���X�l�����ϭ��h���������
					placelist.remove(placeVO);
				}
			}

		}
		for (PlaceVO placeVO : placelist) {// ���ե�
			System.out.println(placeVO.getPlc_name() + " " + placeVO.getCamp_no());
//			 JSONObject jsonObject = JSONObject.fromObject(placeVO);
		}
		
//		JSONArray jsonArray = JSONArray.fromObject(placelist);
//		out.println();
		req.setAttribute("placelist", placelist);
	}
}
