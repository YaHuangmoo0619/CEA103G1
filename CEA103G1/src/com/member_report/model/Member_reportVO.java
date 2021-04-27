package com.member_report.model;

import java.sql.Timestamp;

public class Member_reportVO implements java.io.Serializable{
	
	private Integer mbr_rpt_no;   
	private Integer rpted_mbr_no;
	private Integer rpt_mbr_no;
	private String rpt_cont;
	private Timestamp rpt_time;
	private Integer proc_stat;
	
	public Integer getMbr_rpt_no() {
		return mbr_rpt_no;
	}
	
	public void setMbr_rpt_no(Integer mbr_rpt_no) {
		this.mbr_rpt_no = mbr_rpt_no;
	}
	
	public Integer getRpted_mbr_no() {
		return rpted_mbr_no;
	}
	
	public void setRpted_mbr_no(Integer rpted_mbr_no) {
		this.rpted_mbr_no = rpted_mbr_no;
	}
	
	public Integer getRpt_mbr_no() {
		return rpt_mbr_no;
	}
	
	public void setRpt_mbr_no(Integer rpt_mbr_no) {
		this.rpt_mbr_no = rpt_mbr_no;
	}
	
	public String getRpt_cont() {
		return rpt_cont;
	}
	
	public void setRpt_cont(String rpt_cont) {
		this.rpt_cont = rpt_cont;
	}
	
	public Timestamp getRpt_time() {
		return rpt_time;
	}
	
	public void setRpt_time(Timestamp rpt_time) {
		this.rpt_time = rpt_time;
	}
	
	public Integer getProc_stat() {
		return proc_stat;
	}
	
	public void setProc_stat(Integer proc_stat) {
		this.proc_stat = proc_stat;
	}
	
}