package com.equipment_order_details.model;

import java.io.Serializable;

public class Equipment_Order_DetailsVO implements Serializable{
	private Integer plc_ord_no;
	private Integer eq_no;
	private Integer eq_amt;

	public Equipment_Order_DetailsVO() {
		super();
	}

	public Equipment_Order_DetailsVO(Integer plc_ord_no, Integer eq_no, Integer eq_amt) {
		super();
		this.plc_ord_no = plc_ord_no;
		this.eq_no = eq_no;
		this.eq_amt = eq_amt;
	}

	public Integer getPlc_ord_no() {
		return plc_ord_no;
	}

	public void setPlc_ord_no(Integer plc_ord_no) {
		this.plc_ord_no = plc_ord_no;
	}

	public Integer getEq_no() {
		return eq_no;
	}

	public void setEq_no(Integer eq_no) {
		this.eq_no = eq_no;
	}

	public Integer getEq_amt() {
		return eq_amt;
	}

	public void setEq_amt(Integer eq_amt) {
		this.eq_amt = eq_amt;
	}

}
