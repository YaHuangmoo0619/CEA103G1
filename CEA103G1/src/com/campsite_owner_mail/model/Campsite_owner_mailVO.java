package com.campsite_owner_mail.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Campsite_owner_mailVO implements Serializable {


	private Integer mail_no;
	private Integer send_no;
	private Integer rcpt_no;
	private Integer mail_read_stat;
	private Integer mail_stat;
	private String mail_cont;
	private Timestamp mail_time;
	
	public Campsite_owner_mailVO() {
	}

	public Campsite_owner_mailVO(Integer mail_no, Integer send_no, Integer rcpt_no, Integer mail_read_stat, Integer mail_stat,
			String mail_cont, Timestamp mail_time) {
		this.mail_no = mail_no;
		this.send_no = send_no;
		this.rcpt_no = rcpt_no;
		this.mail_read_stat = mail_read_stat;
		this.mail_stat = mail_stat;
		this.mail_cont = mail_cont;
		this.mail_time = mail_time;
	}

	public Integer getMail_no() {
		return mail_no;
	}

	public void setMail_no(Integer mail_no) {
		this.mail_no = mail_no;
	}

	public Integer getSend_no() {
		return send_no;
	}

	public void setSend_no(Integer send_no) {
		this.send_no = send_no;
	}

	public Integer getRcpt_no() {
		return rcpt_no;
	}

	public void setRcpt_no(Integer rcpt_no) {
		this.rcpt_no = rcpt_no;
	}

	public Integer getMail_read_stat() {
		return mail_read_stat;
	}

	public void setMail_read_stat(Integer mail_read_stat) {
		this.mail_read_stat = mail_read_stat;
	}

	public Integer getMail_stat() {
		return mail_stat;
	}

	public void setMail_stat(Integer mail_stat) {
		this.mail_stat = mail_stat;
	}

	public String getMail_cont() {
		return mail_cont;
	}

	public void setMail_cont(String mail_cont) {
		this.mail_cont = mail_cont;
	}

	public Timestamp getMail_time() {
		return mail_time;
	}

	public void setMail_time(Timestamp mail_time) {
		this.mail_time = mail_time;
	}
	
}
