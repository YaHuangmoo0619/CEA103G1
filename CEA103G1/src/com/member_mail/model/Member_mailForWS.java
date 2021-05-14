package com.member_mail.model;

public class Member_mailForWS {
	private Integer mail_no;
	private Integer send_no;
	private Integer rcpt_no;
	private Integer mail_read_stat;
	private Integer mail_stat;
	private String mail_cont;
	private String mail_time;
	private Integer countNoRead;
	
	public Member_mailForWS() {
	}

	public Member_mailForWS(Integer mail_no, Integer send_no, Integer rcpt_no, Integer mail_read_stat,
			Integer mail_stat, String mail_cont, String mail_time, Integer countNoRead) {
		super();
		this.mail_no = mail_no;
		this.send_no = send_no;
		this.rcpt_no = rcpt_no;
		this.mail_read_stat = mail_read_stat;
		this.mail_stat = mail_stat;
		this.mail_cont = mail_cont;
		this.mail_time = mail_time;
		this.countNoRead = countNoRead;
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
	public Integer getCountNoRead() {
		return countNoRead;
	}
	public void setCountNoRead(Integer countNoRead) {
		this.countNoRead = countNoRead;
	}
}
