package com.member_mail_picture.model;

import java.io.Serializable;

public class Member_mail_pictureVO implements Serializable {

	private Integer mail_pic_no;
	private Integer mail_no;
	private byte[] mail_pic;
	
	public Member_mail_pictureVO() {
	}
	
	public Member_mail_pictureVO(Integer mail_pic_no, Integer mail_no, byte[] mail_pic) {
		super();
		this.mail_pic_no = mail_pic_no;
		this.mail_no = mail_no;
		this.mail_pic = mail_pic;
	}
	
	public Integer getMail_pic_no() {
		return mail_pic_no;
	}
	public void setMail_pic_no(Integer mail_pic_no) {
		this.mail_pic_no = mail_pic_no;
	}
	public Integer getMail_no() {
		return mail_no;
	}
	public void setMail_no(Integer mail_no) {
		this.mail_no = mail_no;
	}
	public byte[] getMail_pic() {
		return mail_pic;
	}
	public void setMail_pic(byte[] mail_pic) {
		this.mail_pic = mail_pic;
	}
	
	
}
