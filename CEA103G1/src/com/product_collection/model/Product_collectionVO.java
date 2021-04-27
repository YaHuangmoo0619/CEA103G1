package com.product_collection.model;

import java.io.Serializable;

public class Product_collectionVO implements java.io.Serializable{
	private Integer mbr_no;
	private Integer prod_no;
	
	public Product_collectionVO() {
		
	}

	public Product_collectionVO(Integer mbr_no, Integer prod_no) {
		super();
		this.mbr_no = mbr_no;
		this.prod_no = prod_no;
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

	
}
