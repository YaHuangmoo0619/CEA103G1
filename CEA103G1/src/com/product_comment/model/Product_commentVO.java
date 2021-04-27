package com.product_comment.model;

import java.sql.Timestamp;

public class Product_commentVO implements java.io.Serializable{
	
	private Integer prod_cmnt_no;
	private Integer prod_no;
	private Integer mbr_no;
	private String cmnt_cont;
	private Timestamp cmnt_time;
	private Integer cmnt_stat;
	
	public Integer getProd_cmnt_no() {
		return prod_cmnt_no;
	}
	
	public void setProd_cmnt_no(Integer prod_cmnt_no) {
		this.prod_cmnt_no = prod_cmnt_no;
	}
	
	public Integer getProd_no() {
		return prod_no;
	}
	
	public void setProd_no(Integer prod_no) {
		this.prod_no = prod_no;
	}
	
	public Integer getMbr_no() {
		return mbr_no;
	}
	
	public void setMbr_no(Integer mbr_no) {
		this.mbr_no = mbr_no;
	}
	
	public String getCmnt_cont() {
		return cmnt_cont;
	}
	
	public void setCmnt_cont(String cmnt_cont) {
		this.cmnt_cont = cmnt_cont;
	}
	
	public Timestamp getCmnt_time() {
		return cmnt_time;
	}
	
	public void setCmnt_time(Timestamp cmnt_time) {
		this.cmnt_time = cmnt_time;
	}
	
	public Integer getCmnt_stat() {
		return cmnt_stat;
	}
	
	public void setCmnt_stat(Integer cmnt_stat) {
		this.cmnt_stat = cmnt_stat;
	}
	
}