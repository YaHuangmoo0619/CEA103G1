package com.campsite_comment.model;

import java.sql.Timestamp;

public class Campsite_commentVO implements java.io.Serializable{
	
	private Integer camp_cmnt_no;
	private Integer camp_no;
	private Integer mbr_no;
	private String cmnt_cont;
	private Integer star;
	private Timestamp cmnt_time;
	private Integer cmnt_stat;
	
	public Integer getCamp_cmnt_no() {
		return camp_cmnt_no;
	}
	
	public void setCamp_cmnt_no(Integer camp_cmnt_no) {
		this.camp_cmnt_no = camp_cmnt_no;
	}
	
	public Integer getCamp_no() {
		return camp_no;
	}
	
	public void setCamp_no(Integer camp_no) {
		this.camp_no = camp_no;
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
	
	public Integer getStar() {
		return star;
	}
	
	public void setStar(Integer star) {
		this.star = star;
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