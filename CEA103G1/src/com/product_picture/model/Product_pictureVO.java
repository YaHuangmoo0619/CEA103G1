package com.product_picture.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

public class Product_pictureVO implements java.io.Serializable{
	private Integer prod_pic_no;
	private Integer prod_no;
	private String prod_pic;
	private Timestamp prod_pic_time;
	
	public Product_pictureVO() {
	
	}

	public Product_pictureVO(Integer prod_pic_no, Integer prod_no, String prod_pic, Timestamp prod_pic_time) {
		super();
		this.prod_pic_no = prod_pic_no;
		this.prod_no = prod_no;
		this.prod_pic = prod_pic;
		this.prod_pic_time = prod_pic_time;
	}

	public Integer getProd_pic_no() {
		return prod_pic_no;
	}

	public void setProd_pic_no(Integer prod_pic_no) {
		this.prod_pic_no = prod_pic_no;
	}

	public Integer getProd_no() {
		return prod_no;
	}

	public void setProd_no(Integer prod_no) {
		this.prod_no = prod_no;
	}

	public String getProd_pic() {
		return prod_pic;
	}

	public void setProd_pic(String prod_pic) {
		this.prod_pic = prod_pic;
	}

	public Timestamp getProd_pic_time() {
		return prod_pic_time;
	}

	public void setProd_pic_time(Timestamp prod_pic_time) {
		this.prod_pic_time = prod_pic_time;
	}
	
	
	
}