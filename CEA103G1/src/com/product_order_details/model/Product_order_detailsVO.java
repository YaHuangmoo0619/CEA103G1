package com.product_order_details.model;

import java.io.Serializable;

public class Product_order_detailsVO implements java.io.Serializable{
	private Integer prod_ord_no;
	private Integer prod_no;
	private Integer prod_amt;
	private Integer prod_unit_pc;
	
	public Product_order_detailsVO() {
		
	}

	public Product_order_detailsVO(Integer prod_ord_no, Integer prod_no, Integer prod_amt, Integer prod_unit_pc) {
		super();
		this.prod_ord_no = prod_ord_no;
		this.prod_no = prod_no;
		this.prod_amt = prod_amt;
		this.prod_unit_pc = prod_unit_pc;
	}

	public Integer getProd_ord_no() {
		return prod_ord_no;
	}

	public void setProd_ord_no(Integer prod_ord_no) {
		this.prod_ord_no = prod_ord_no;
	}

	public Integer getProd_no() {
		return prod_no;
	}

	public void setProd_no(Integer prod_no) {
		this.prod_no = prod_no;
	}

	public Integer getProd_amt() {
		return prod_amt;
	}

	public void setProd_amt(Integer prod_amt) {
		this.prod_amt = prod_amt;
	}

	public Integer getProd_unit_pc() {
		return prod_unit_pc;
	}

	public void setProd_unit_pc(Integer prod_unit_pc) {
		this.prod_unit_pc = prod_unit_pc;
	}
	
	
}
