package com.advertising_application.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Advertising_applicationVO implements java.io.Serializable{
	
	private Integer ad_no;
	private Integer cso_no;
	private String ad_head;
	private String ad_cont;
	private Byte ad_pic;
	private Timestamp app_time;
	private Timestamp rev_time;
	private Integer ad_stat;
	private Date ad_st_date;
	private Date ad_end_date;
	
	public Integer getAd_no() {
		return ad_no;
	}
	
	public void setAd_no(Integer ad_no) {
		this.ad_no = ad_no;
	}
	
	public Integer getCso_no() {
		return cso_no;
	}
	
	public void setCso_no(Integer cso_no) {
		this.cso_no = cso_no;
	}
	
	public String getAd_head() {
		return ad_head;
	}
	
	public void setAd_head(String ad_head) {
		this.ad_head = ad_head;
	}
	
	public String getAd_cont() {
		return ad_cont;
	}
	
	public void setAd_cont(String ad_cont) {
		this.ad_cont = ad_cont;
	}
	
	public Byte getAd_pic() {
		return ad_pic;
	}
	
	public void setAd_pic(Byte ad_pic) {
		this.ad_pic = ad_pic;
	}
	
	public Timestamp getApp_time() {
		return app_time;
	}
	
	public void setApp_time(Timestamp app_time) {
		this.app_time = app_time;
	}
	
	public Timestamp getRev_time() {
		return rev_time;
	}
	
	public void setRev_time(Timestamp rev_time) {
		this.rev_time = rev_time;
	}
	
	public Integer getAd_stat() {
		return ad_stat;
	}
	
	public void setAd_stat(Integer ad_stat) {
		this.ad_stat = ad_stat;
	}
	
	public Date getAd_st_date() {
		return ad_st_date;
	}
	
	public void setAd_st_date(Date ad_st_date) {
		this.ad_st_date = ad_st_date;
	}
	
	public Date getAd_end_date() {
		return ad_end_date;
	}
	
	public void setAd_end_date(Date ad_end_date) {
		this.ad_end_date = ad_end_date;
	}
}