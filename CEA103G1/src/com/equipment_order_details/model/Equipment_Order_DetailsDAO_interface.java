package com.equipment_order_details.model;

import java.util.List;
import java.util.Map;


public interface Equipment_Order_DetailsDAO_interface {
	public void insert(Equipment_Order_DetailsVO equipment_order_detailsVO);

	public void update(Equipment_Order_DetailsVO equipment_order_detailsVO);

	public void delete(Integer plc_ord_no, Integer eq_no);

	public Equipment_Order_DetailsVO findByPrimaryKey(Integer plc_ord_no, Integer eq_no);

	public List<Equipment_Order_DetailsVO> getAll();
}
