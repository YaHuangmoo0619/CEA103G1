package com.service_mail_picture.model;

import java.io.Serializable;

public class Service_mail_pictureVO implements Serializable {

	private Integer svc_mail_pic_no;
	private Integer mail_no;
	private byte[] mail_pic;

	public Service_mail_pictureVO() {

	}

	public Service_mail_pictureVO(Integer svc_mail_pic_no, Integer mail_no, byte[] mail_pic) {
		this.svc_mail_pic_no = svc_mail_pic_no;
		this.mail_no = mail_no;
		this.mail_pic = mail_pic;
	}

	public Integer getSvc_mail_pic_no() {
		return svc_mail_pic_no;
	}

	public void setSvc_mail_pic_no(Integer svc_mail_pic_no) {
		this.svc_mail_pic_no = svc_mail_pic_no;
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
