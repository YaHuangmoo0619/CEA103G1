package com.product_report.model;

import java.sql.Timestamp;

public class Product_reportVO implements java.io.Serializable{
	private Integer prod_rpt_no;
	private Integer mbr_no;
	private Integer prod_no;
	private String rpt_cont;
	private Timestamp rpt_time;
	private Integer proc_stat;
	
	public Product_reportVO() {
		
	}
	
	public Product_reportVO (Integer prod_rpt_no, Integer mbr_no, Integer prod_no, String rpt_cont, Timestamp rpt_time,
			Integer proc_stat) {
		super();
		this.prod_rpt_no = prod_rpt_no;
		this.mbr_no = mbr_no;
		this.prod_no = prod_no;
		this.rpt_cont = rpt_cont;
		this.rpt_time = rpt_time;
		this.proc_stat = proc_stat;
	}
	
	public Integer getProd_rpt_no() {
		return prod_rpt_no;
	}
	public void setProd_rpt_no(Integer prod_rpt_no) {
		this.prod_rpt_no = prod_rpt_no;
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
