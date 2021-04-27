package com.place_order.model;

import java.util.List;
import java.util.Map;


public interface Place_OrderDAO_interface {
	public void insert(Place_OrderVO place_orderVO);

	public void update(Place_OrderVO place_orderVO);

	public void delete(Integer plc_ord_no);

	public Place_OrderVO findByPrimaryKey(Integer plc_ord_no);

	public List<Place_OrderVO> getAll();

	public List<Place_OrderVO> findByCamp(Integer camp_no);
}
