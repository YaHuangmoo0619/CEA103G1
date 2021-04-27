package com.article_replay_report.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Article_Rep_ReportVO implements Serializable{
	private Integer art_rep_rpt_no;
	private Integer mbr_no;
	private Integer art_rep_no;
	private String rpt_cont;
	private Timestamp rpt_time;
	private Integer proc_stat;
	
	public Article_Rep_ReportVO() {
		super();
	}

	public Article_Rep_ReportVO(Integer art_rep_rpt_no, Integer mbr_no, Integer art_rep_no, String rpt_cont,
			Timestamp rpt_time, Integer proc_stat) {
		super();
		this.art_rep_rpt_no = art_rep_rpt_no;
		this.mbr_no = mbr_no;
		this.art_rep_no = art_rep_no;
		this.rpt_cont = rpt_cont;
		this.rpt_time = rpt_time;
		this.proc_stat = proc_stat;
	}

	public Integer getArt_rep_rpt_no() {
		return art_rep_rpt_no;
	}

	public void setArt_rep_rpt_no(Integer art_rep_rpt_no) {
		this.art_rep_rpt_no = art_rep_rpt_no;
	}

	public Integer getMbr_no() {
		return mbr_no;
	}

	public void setMbr_no(Integer mbr_no) {
		this.mbr_no = mbr_no;
	}

	public Integer getArt_rep_no() {
		return art_rep_no;
	}

	public void setArt_rep_no(Integer art_rep_no) {
		this.art_rep_no = art_rep_no;
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
