package com.product_category.model;

import java.io.Serializable;

public class Product_categoryVO implements java.io.Serializable{
	private Integer prod_cat_no;
	private String prod_cat_name;
	
	public Product_categoryVO() {
		
	}

	public Product_categoryVO(Integer prod_cat_no, String prod_cat_name) {
		this.prod_cat_no = prod_cat_no;
		this.prod_cat_name = prod_cat_name;
	}

	public Integer getProd_cat_no() {
		return prod_cat_no;
	}

	public void setProd_cat_no(Integer prod_cat_no) {
		this.prod_cat_no = prod_cat_no;
	}

	public String getProd_cat_name() {
		return prod_cat_name;
	}

	public void setProd_cat_name(String prod_cat_name) {
		this.prod_cat_name = prod_cat_name;
	}
	
	
}
