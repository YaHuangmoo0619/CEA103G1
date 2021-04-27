package com.place_order_details.model;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface Place_Order_DetailsDAO_interface {
	public void insert(Place_Order_DetailsVO place_order_detailsVO);

	public void update(Place_Order_DetailsVO place_order_detailsVO);

	public void deletePlace_Order(Integer plc_ord_no);
	
	public void deletePlace(Integer plc_no);

	public List<Place_Order_DetailsVO> findByPlaceOrder(Integer plc_ord_no);

	public List<Place_Order_DetailsVO> getAll();

	public Set<Integer> findByPlace(Integer plc_no);
}
