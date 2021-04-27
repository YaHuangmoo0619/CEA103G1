package com.article_report.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Article_ReportVO implements Serializable{
	private Integer art_rpt_no;
	private Integer art_no;
	private Integer mbr_no;
	private String rpt_cont;
	private Timestamp rpt_time;
	private Integer proc_stat;
	
	public Article_ReportVO() {
		super();
	}
	
	
	public Article_ReportVO(Integer art_rpt_no, Integer art_no, Integer mbr_no, String rpt_cont, Timestamp rpt_time,
			Integer proc_stat) {
		super();
		this.art_rpt_no = art_rpt_no;
		this.art_no = art_no;
		this.mbr_no = mbr_no;
		this.rpt_cont = rpt_cont;
		this.rpt_time = rpt_time;
		this.proc_stat = proc_stat;
	}


	public Integer getArt_rpt_no() {
		return art_rpt_no;
	}
	public void setArt_rpt_no(Integer art_rpt_no) {
		this.art_rpt_no = art_rpt_no;
	}
	public Integer getArt_no() {
		return art_no;
	}
	public void setArt_no(Integer art_no) {
		this.art_no = art_no;
	}
	public Integer getMbr_no() {
		return mbr_no;
	}
	public void setMbr_no(Integer mbr_no) {
		this.mbr_no = mbr_no;
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
