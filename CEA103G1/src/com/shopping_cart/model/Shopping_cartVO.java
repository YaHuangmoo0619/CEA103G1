package com.shopping_cart.model;

import java.io.Serializable;

public class Shopping_cartVO implements java.io.Serializable{
	private Integer mbr_no;
	private Integer prod_no;
	private Integer prod_amt;
	
	public Shopping_cartVO() {
		
	}
	
	public Shopping_cartVO(Integer mbr_no, Integer prod_no, Integer prod_amt) {
		super();
		this.mbr_no = mbr_no;
		this.prod_no = prod_no;
		this.prod_amt = prod_amt;
	}
	
	public Integer getMbr_no() {
		return mbr_no;
	}
	public void setMbr_no(Integer mbr_no) {
		this.mbr_no = mbr_no;
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
	
}
