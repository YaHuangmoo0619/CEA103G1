package com.service_mail_picture.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.service_mail.model.Service_mailVO;

@Entity
@Table(name="service_mail_picture")
public class Service_mail_pictureHBVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="svc_mail_pic_no")
	private Integer svc_mail_pic_no;
	
	@Column(name="mail_pic")
	private String mail_pic;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="mail_no")
	private Service_mailVO service_mailVO;

	public Service_mail_pictureHBVO() {

	}

	public Service_mail_pictureHBVO(String mail_pic) {
		this.mail_pic = mail_pic;
	}

	public Integer getSvc_mail_pic_no() {
		return svc_mail_pic_no;
	}

	public void setSvc_mail_pic_no(Integer svc_mail_pic_no) {
		this.svc_mail_pic_no = svc_mail_pic_no;
	}

	public String getMail_pic() {
		return mail_pic;
	}

	public void setMail_pic(String mail_pic) {
		this.mail_pic = mail_pic;
	}

	public Service_mailVO getService_mailVO() {
		return service_mailVO;
	}

	public void setService_mailVO(Service_mailVO service_mailVO) {
		this.service_mailVO = service_mailVO;
	}
	
	
}
