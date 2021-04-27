package com.product_picture.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

public class Product_pictureVO implements java.io.Serializable{
	private Integer prod_cat_no;
	private String prod_cat_name;
	private Blob prod_pic;
	private Timestamp camp_pic_time;
	
	public Product_pictureVO() {
		
	}

	public Product_pictureVO(Integer prod_cat_no, String prod_cat_name, Blob prod_pic, Timestamp camp_pic_time) {
		super();
		this.prod_cat_no = prod_cat_no;
		this.prod_cat_name = prod_cat_name;
		this.prod_pic = prod_pic;
		this.camp_pic_time = camp_pic_time;
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

	public Blob getProd_pic() {
		return prod_pic;
	}

	public void setProd_pic(Blob prod_pic) {
		this.prod_pic = prod_pic;
	}

	public Timestamp getCamp_pic_time() {
		return camp_pic_time;
	}

	public void setCamp_pic_time(Timestamp camp_pic_time) {
		this.camp_pic_time = camp_pic_time;
	}
	
}
