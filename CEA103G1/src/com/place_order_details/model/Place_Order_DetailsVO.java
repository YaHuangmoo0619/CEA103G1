package com.place_order_details.model;

import java.io.Serializable;

public class Place_Order_DetailsVO implements Serializable{
	private Integer plc_ord_no;
	private Integer plc_no;
	private String plc_name;

	public Place_Order_DetailsVO() {
		super();
	}

	public Place_Order_DetailsVO(Integer plc_ord_no, Integer plc_no) {
		super();
		this.plc_ord_no = plc_ord_no;
		this.plc_no = plc_no;
	}

	public Integer getPlc_ord_no() {
		return plc_ord_no;
	}

	public void setPlc_ord_no(Integer plc_ord_no) {
		this.plc_ord_no = plc_ord_no;
	}

	public Integer getPlc_no() {
		return plc_no;
	}

	public void setPlc_no(Integer plc_no) {
		this.plc_no = plc_no;
	}

	public String getPlc_name() {
		return plc_name;
	}

	public void setPlc_name(String plc_name) {
		this.plc_name = plc_name;
	}
}
