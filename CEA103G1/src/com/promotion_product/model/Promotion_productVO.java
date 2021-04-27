package com.promotion_product.model;

import java.io.Serializable;

public class Promotion_productVO implements java.io.Serializable{
	private Integer promo_prj_no;
	private Integer prod_no;
	private Integer prod_pc;
	
	public Promotion_productVO() {
	
	}
	
	public Promotion_productVO(Integer promo_prj_no, Integer prod_no, Integer prod_pc) {
		super();
		this.promo_prj_no = promo_prj_no;
		this.prod_no = prod_no;
		this.prod_pc = prod_pc;
	}
	
	public Integer getPromo_prj_no() {
		return promo_prj_no;
	}
	public void setPromo_prj_no(Integer promo_prj_no) {
		this.promo_prj_no = promo_prj_no;
	}
	public Integer getProd_no() {
		return prod_no;
	}
	public void setProd_no(Integer prod_no) {
		this.prod_no = prod_no;
	}
	public Integer getProd_pc() {
		return prod_pc;
	}
	public void setProd_pc(Integer prod_pc) {
		this.prod_pc = prod_pc;
	}
	
}
