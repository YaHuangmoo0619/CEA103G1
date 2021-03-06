package com.announcement.model;

import java.io.Serializable;
import java.sql.Date;

public class AnnouncementVO implements Serializable {

	private Integer an_no;
	private Integer emp_no;
	private String an_cont;
	private Date an_skd_date;
	private byte[] an_pic;

	public AnnouncementVO() {
		
	}
	
	public AnnouncementVO(Integer emp_no, String an_cont, java.sql.Date an_skd_date, byte[] an_pic) {
		this.emp_no = emp_no;
		this.an_cont = an_cont;
		this.an_skd_date = an_skd_date;
		this.an_pic = an_pic;
	}
	
	public Integer getAn_no() {
		return an_no;
	}

	public void setAn_no(Integer an_no) {
		this.an_no = an_no;
	}

	public Integer getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}

	public String getAn_cont() {
		return an_cont;
	}

	public void setAn_cont(String an_cont) {
		this.an_cont = an_cont;
	}

	public Date getAn_skd_date() {
		return an_skd_date;
	}

	public void setAn_skd_date(Date an_skd_date) {
		this.an_skd_date = an_skd_date;
	}

	public byte[] getAn_pic() {
		return an_pic;
	}

	public void setAn_pic(byte[] an_pic) {
		this.an_pic = an_pic;
	}
	
}
