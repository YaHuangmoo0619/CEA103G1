package com.product_order.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Product_orderVO implements java.io.Serializable{
	private Integer prod_ord_no;
	private Integer mbr_no;
	private Timestamp prod_ord_time;
	private Integer prod_ord_stat;
	private Integer prod_ord_sum;
	private Integer used_pt;
	private Integer ship_meth;
	private Integer pay_meth;
	private String ship_cty;
	private String ship_dist;
	private String ship_add;
	private Integer receipt;
	private String rmk;
	
	public Product_orderVO() {
		
	}

	public Product_orderVO(Integer prod_ord_no, Integer mbr_no, Timestamp prod_ord_time, Integer prod_ord_stat,
			Integer prod_ord_sum, Integer used_pt, Integer ship_meth, Integer pay_meth, String ship_cty,
			String ship_dist, String ship_add, Integer receipt, String rmk) {
		super();
		this.prod_ord_no = prod_ord_no;
		this.mbr_no = mbr_no;
		this.prod_ord_time = prod_ord_time;
		this.prod_ord_stat = prod_ord_stat;
		this.prod_ord_sum = prod_ord_sum;
		this.used_pt = used_pt;
		this.ship_meth = ship_meth;
		this.pay_meth = pay_meth;
		this.ship_cty = ship_cty;
		this.ship_dist = ship_dist;
		this.ship_add = ship_add;
		this.receipt = receipt;
		this.rmk = rmk;
	}

	public Integer getProd_ord_no() {
		return prod_ord_no;
	}

	public void setProd_ord_no(Integer prod_ord_no) {
		this.prod_ord_no = prod_ord_no;
	}

	public Integer getMbr_no() {
		return mbr_no;
	}

	public void setMbr_no(Integer mbr_no) {
		this.mbr_no = mbr_no;
	}

	public Timestamp getProd_ord_time() {
		return prod_ord_time;
	}

	public void setProd_ord_time(Timestamp prod_ord_time) {
		this.prod_ord_time = prod_ord_time;
	}

	public Integer getProd_ord_stat() {
		return prod_ord_stat;
	}

	public void setProd_ord_stat(Integer prod_ord_stat) {
		this.prod_ord_stat = prod_ord_stat;
	}

	public Integer getProd_ord_sum() {
		return prod_ord_sum;
	}

	public void setProd_ord_sum(Integer prod_ord_sum) {
		this.prod_ord_sum = prod_ord_sum;
	}

	public Integer getUsed_pt() {
		return used_pt;
	}

	public void setUsed_pt(Integer used_pt) {
		this.used_pt = used_pt;
	}

	public Integer getShip_meth() {
		return ship_meth;
	}

	public void setShip_meth(Integer ship_meth) {
		this.ship_meth = ship_meth;
	}

	public Integer getPay_meth() {
		return pay_meth;
	}

	public void setPay_meth(Integer pay_meth) {
		this.pay_meth = pay_meth;
	}

	public String getShip_cty() {
		return ship_cty;
	}

	public void setShip_cty(String ship_cty) {
		this.ship_cty = ship_cty;
	}

	public String getShip_dist() {
		return ship_dist;
	}

	public void setShip_dist(String ship_dist) {
		this.ship_dist = ship_dist;
	}

	public String getShip_add() {
		return ship_add;
	}

	public void setShip_add(String ship_add) {
		this.ship_add = ship_add;
	}

	public Integer getReceipt() {
		return receipt;
	}

	public void setReceipt(Integer receipt) {
		this.receipt = receipt;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
		
}

