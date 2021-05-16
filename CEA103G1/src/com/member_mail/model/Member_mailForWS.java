package com.member_mail.model;

public class Member_mailForWS {
	private Integer mail_no;
	private Integer send_no;
	private Integer rcpt_no;
	private Integer mail_read_stat;
	private Integer mail_stat;
	private String mail_cont;
	private String mail_time;
	private Integer countNoReadMail;
	//-----
	private Integer	ntfy_no;
	private Integer mbr_no;
	private Integer ntfy_stat;
	private	String ntfy_cont;
	private String ntfy_time;
	private Integer countNoReadNotify;
	
	public Member_mailForWS() {
	}

	public Member_mailForWS(Integer mail_no, Integer send_no, Integer rcpt_no, Integer mail_read_stat,
			Integer mail_stat, String mail_cont, String mail_time, Integer countNoReadMail,Integer ntfy_no, Integer mbr_no, Integer ntfy_stat, String ntfy_cont , String ntfy_time, Integer countNoReadNotify) {
		this.mail_no = mail_no;
		this.send_no = send_no;
		this.rcpt_no = rcpt_no;
		this.mail_read_stat = mail_read_stat;
		this.mail_stat = mail_stat;
		this.mail_cont = mail_cont;
		this.mail_time = mail_time;
		this.countNoReadMail = countNoReadMail;
		//-----
		this.ntfy_no = ntfy_no;
		this.mbr_no = mbr_no;
		this.ntfy_stat = ntfy_stat;
		this.ntfy_cont = ntfy_cont;
		this.ntfy_time = ntfy_time;
		this.countNoReadNotify = countNoReadNotify;
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
	public String getMail_time() {
		return mail_time;
	}
	public void setMail_time(String mail_time) {
		this.mail_time = mail_time;
	}
	public Integer getCountNoReadMail() {
		return countNoReadMail;
	}
	public void setCountNoReadMail(Integer countNoReadMail) {
		this.countNoReadMail = countNoReadMail;
	}
	
	//-----

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

	public Integer getCountNoReadNotify() {
		return countNoReadNotify;
	}

	public void setCountNoReadNotify(Integer countNoReadNotify) {
		this.countNoReadNotify = countNoReadNotify;
	}
}
