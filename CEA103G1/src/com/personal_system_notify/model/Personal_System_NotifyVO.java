package com.personal_system_notify.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Personal_System_NotifyVO implements Serializable{

	private Integer	ntfy_no;
	private Integer mbr_no;
	private Integer ntfy_stat;
	private	String ntfy_cont;
	private String ntfy_time;
	

	public Personal_System_NotifyVO(Integer ntfy_no, Integer mbr_no, Integer ntfy_stat, String ntfy_cont , String ntfy_time) {
		super();
		this.ntfy_no = ntfy_no;
		this.mbr_no = mbr_no;
		this.ntfy_stat = ntfy_stat;
		this.ntfy_cont = ntfy_cont;
		this.ntfy_time = ntfy_time;
	}
	
	public Personal_System_NotifyVO(Integer mbr_no, Integer ntfy_stat, String ntfy_cont , String ntfy_time) {
		super();
		this.mbr_no = mbr_no;
		this.ntfy_stat = ntfy_stat;
		this.ntfy_cont = ntfy_cont;
		this.ntfy_time = ntfy_time;
	}

	public Personal_System_NotifyVO() {

	}
	
	public Integer getNtfy_no() {
		return ntfy_no;
	}

	public void setNtfy_no(Integer ntfy_no) {
		this.ntfy_no = ntfy_no;
	}

	public Integer getMbr_no() {
		return mbr_no;
	}

	public void setMbr_no(Integer mbr_no) {
		this.mbr_no = mbr_no;
	}

	public Integer getNtfy_stat() {
		return ntfy_stat;
	}

	public void setNtfy_stat(Integer ntfy_stat) {
		this.ntfy_stat = ntfy_stat;
	}

	public String getNtfy_cont() {
		return ntfy_cont;
	}

	public void setNtfy_cont(String ntfy_cont) {
		this.ntfy_cont = ntfy_cont;
	}

	public String getNtfy_time() {
		return ntfy_time;
	}

	public void setNtfy_time(String ntfy_time) {
		this.ntfy_time = ntfy_time;
	}
	
	
}
