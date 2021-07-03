package com.member_mail_picture.model;

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

import com.member_mail.model.Member_mailVO;

@Entity
@Table(name="member_mail_picture")
public class Member_mail_pictureHBVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mail_pic_no")
	private Integer mail_pic_no;
	
//	@Column(name="mail_no")
//	private Integer mail_no;
	
	@Column(name="mail_pic")
	private String mail_pic;
	
//	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.DETACH,
//						 CascadeType.MERGE, CascadeType.REFRESH})
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="mail_no")
	private Member_mailVO member_mailVO;
	
	public Member_mail_pictureHBVO() {
	}
	
//	public Member_mail_pictureVO(Integer mail_pic_no, Integer mail_no, String mail_pic) {
	public Member_mail_pictureHBVO(Integer mail_pic_no, String mail_pic) {
		super();
		this.mail_pic_no = mail_pic_no;
//		this.mail_no = mail_no;
		this.mail_pic = mail_pic;
	}
	
	public Integer getMail_pic_no() {
		return mail_pic_no;
	}
	public void setMail_pic_no(Integer mail_pic_no) {
		this.mail_pic_no = mail_pic_no;
	}
//	public Integer getMail_no() {
//		return mail_no;
//	}
//	public void setMail_no(Integer mail_no) {
//		this.mail_no = mail_no;
//	}
	public String getMail_pic() {
		return mail_pic;
	}
	public void setMail_pic(String mail_pic) {
		this.mail_pic = mail_pic;
	}

	public Member_mailVO getMember_mailVO() {
		return member_mailVO;
	}

	public void setMember_mailVO(Member_mailVO member_mailVO) {
		this.member_mailVO = member_mailVO;
	}
	
	
}
