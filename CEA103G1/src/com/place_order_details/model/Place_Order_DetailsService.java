package com.place_order_details.model;

import java.util.List;
import java.util.Set;

public class Place_Order_DetailsService {
	private Place_Order_DetailsDAO_interface dao;

	public Place_Order_DetailsService() {
		dao = new Place_Order_DetailsDAO();
	}

	public Place_Order_DetailsVO addPlace_Order_Details(Integer plc_no) {

		Place_Order_DetailsVO place_order_detailsVO = new Place_Order_DetailsVO();

		place_order_detailsVO.setPlc_no(plc_no);
		dao.insert(place_order_detailsVO);

		return place_order_detailsVO;
	}

	public Place_Order_DetailsVO updatePlace_Order_Details(Integer plc_ord_no, Integer plc_no) {

		Place_Order_DetailsVO place_order_detailsVO = new Place_Order_DetailsVO();

		place_order_detailsVO.setPlc_ord_no(plc_ord_no);
		place_order_detailsVO.setPlc_no(plc_no);
		dao.update(place_order_detailsVO);

		return place_order_detailsVO;
	}

	public void deletePlace_Order_Details(Integer plc_ord_no) {
		dao.deletePlace_Order(plc_ord_no);
	}
	
	public void deletePlace_Order_Place(Integer plc_no) {
		dao.deletePlace(plc_no);
	}

	public List<Place_Order_DetailsVO> getOnePlace_Order_Details(Integer plc_ord_no) {
		return dao.findByPlaceOrder(plc_ord_no);
	}
	public Set<Integer> getOnePlace_Order_Details2(Integer plc_no) {
		return dao.findByPlace(plc_no);
	}

	public List<Place_Order_DetailsVO> getAll() {
		return dao.getAll();
	}
}
