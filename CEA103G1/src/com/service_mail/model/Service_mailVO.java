package com.service_mail.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Service_mailVO implements Serializable {

	private Integer mail_no;
	private Integer emp_no;
	private Integer mbr_no;
	private String mail_cont;
	private Integer mail_stat;
	private Integer mail_read_stat;
	private Timestamp mail_time;
	
	public Service_mailVO() {
	}

	public Service_mailVO(Integer mail_no, Integer emp_no, Integer mbr_no, String mail_cont, Integer mail_stat,
			Integer mail_read_stat, Timestamp mail_time) {
		super();
		this.mail_no = mail_no;
		this.emp_no = emp_no;
		this.mbr_no = mbr_no;
		this.mail_cont = mail_cont;
		this.mail_stat = mail_stat;
		this.mail_read_stat = mail_read_stat;
		this.mail_time = mail_time;
	}

	public Integer getMail_no() {
		return mail_no;
	}

	public void setMail_no(Integer mail_no) {
		this.mail_no = mail_no;
	}

	public Integer getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}

	public Integer getMbr_no() {
		return mbr_no;
	}

	public void setMbr_no(Integer mbr_no) {
		this.mbr_no = mbr_no;
	}

	public String getMail_cont() {
		return mail_cont;
	}

	public void setMail_cont(String mail_cont) {
		this.mail_cont = mail_cont;
	}

	public Integer getMail_stat() {
		return mail_stat;
	}

	public void setMail_stat(Integer mail_stat) {
		this.mail_stat = mail_stat;
	}

	public Integer getMail_read_stat() {
		return mail_read_stat;
	}

	public void setMail_read_stat(Integer mail_read_stat) {
		this.mail_read_stat = mail_read_stat;
	}

	public Timestamp getMail_time() {
		return mail_time;
	}

	public void setMail_time(Timestamp mail_time) {
		this.mail_time = mail_time;
	}
	
	
	
}
