package com.product_comment_report.model;

import java.sql.Timestamp;

public class Product_comment_reportVO implements java.io.Serializable{
	
	private Integer prod_cmnt_rpt_no;
	private Integer mbr_no;
	private Integer prod_cmnt_no;
	private String rpt_cont;
	private Timestamp rpt_time;
	private Integer proc_stat;
	
	public Integer getProd_cmnt_rpt_no() {
		return prod_cmnt_rpt_no;
	}
	
	public void setProd_cmnt_rpt_no(Integer prod_cmnt_rpt_no) {
		this.prod_cmnt_rpt_no = prod_cmnt_rpt_no;
	}
	
	public Integer getMbr_no() {
		return mbr_no;
	}
	
	public void setMbr_no(Integer mbr_no) {
		this.mbr_no = mbr_no;
	}
	
	public Integer getProd_cmnt_no() {
		return prod_cmnt_no;
	}
	
	public void setProd_cmnt_no(Integer prod_cmnt_no) {
		this.prod_cmnt_no = prod_cmnt_no;
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