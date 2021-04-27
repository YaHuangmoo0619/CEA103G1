package com.promotion_project.model;

import java.io.Serializable;
import java.sql.Date;

public class Promotion_projectVO implements java.io.Serializable{
	private Integer promo_prj_no;
	private String promo_prj_name;
	private String promo_copy;
	private String promo_cnt;
	private Date promo_st_date;
	private Date promo_end_date;
	
	public Promotion_projectVO() {
		
	}

	public Promotion_projectVO(Integer promo_prj_no, String promo_prj_name, String promo_copy, String promo_cnt,
			Date promo_st_date, Date promo_end_date) {
		super();
		this.promo_prj_no = promo_prj_no;
		this.promo_prj_name = promo_prj_name;
		this.promo_copy = promo_copy;
		this.promo_cnt = promo_cnt;
		this.promo_st_date = promo_st_date;
		this.promo_end_date = promo_end_date;
	}

	public Integer getPromo_prj_no() {
		return promo_prj_no;
	}

	public void setPromo_prj_no(Integer promo_prj_no) {
		this.promo_prj_no = promo_prj_no;
	}

	public String getPromo_prj_name() {
		return promo_prj_name;
	}

	public void setPromo_prj_name(String promo_prj_name) {
		this.promo_prj_name = promo_prj_name;
	}

	public String getPromo_copy() {
		return promo_copy;
	}

	public void setPromo_copy(String promo_copy) {
		this.promo_copy = promo_copy;
	}

	public String getPromo_cnt() {
		return promo_cnt;
	}

	public void setPromo_cnt(String promo_cnt) {
		this.promo_cnt = promo_cnt;
	}

	public Date getPromo_st_date() {
		return promo_st_date;
	}

	public void setPromo_st_date(Date promo_st_date) {
		this.promo_st_date = promo_st_date;
	}

	public Date getPromo_end_date() {
		return promo_end_date;
	}

	public void setPromo_end_date(Date promo_end_date) {
		this.promo_end_date = promo_end_date;
	}
	
}
