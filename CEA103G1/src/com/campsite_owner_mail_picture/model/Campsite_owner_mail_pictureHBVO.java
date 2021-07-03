package com.campsite_owner_mail_picture.model;

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

import com.campsite_owner_mail.model.Campsite_owner_mailVO;

@Entity
@Table(name="campsite_owner_mail_picture")
public class Campsite_owner_mail_pictureHBVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mail_pic_no")
	private Integer mail_pic_no;
	
	@Column(name="mail_pic")
	private String mail_pic;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="mail_no")
	private Campsite_owner_mailVO campsite_owner_mailVO;
	
	public Campsite_owner_mail_pictureHBVO() {
	}
	
	public Campsite_owner_mail_pictureHBVO(Integer mail_pic_no, String mail_pic) {
		super();
		this.mail_pic_no = mail_pic_no;
		this.mail_pic = mail_pic;
	}
	
	public Integer getMail_pic_no() {
		return mail_pic_no;
	}
	public void setMail_pic_no(Integer mail_pic_no) {
		this.mail_pic_no = mail_pic_no;
	}
	public String getMail_pic() {
		return mail_pic;
	}
	public void setMail_pic(String mail_pic) {
		this.mail_pic = mail_pic;
	}

	public Campsite_owner_mailVO getCampsite_owner_mailVO() {
		return campsite_owner_mailVO;
	}

	public void setCampsite_owner_mailVO(Campsite_owner_mailVO campsite_owner_mailVO) {
		this.campsite_owner_mailVO = campsite_owner_mailVO;
	}
	
	
}
