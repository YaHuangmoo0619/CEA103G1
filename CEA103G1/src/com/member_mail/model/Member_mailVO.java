package com.member_mail.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.member_mail_picture.model.Member_mail_pictureHBVO;

@Entity
@Table(name="member_mail")
public class Member_mailVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mail_no")
	private Integer mail_no;
	
	@Column(name="send_no")
	private Integer send_no;
	
	@Column(name="rcpt_no")
	private Integer rcpt_no;
	
	@Column(name="mail_read_stat")
	private Integer mail_read_stat;
	
	@Column(name="mail_stat")
	private Integer mail_stat;
	
	@Column(name="mail_cont")
	private String mail_cont;
	
	@Column(name="mail_time")
	private String mail_time;
	
//	@OneToMany(fetch=FetchType.LAZY,
//			mappedBy="member_mailVO",
//			cascade={CascadeType.DETACH, CascadeType.MERGE,
//						CascadeType.PERSIST, CascadeType.REFRESH})
	@OneToMany(fetch=FetchType.LAZY,
			mappedBy="member_mailVO",
			cascade=CascadeType.ALL)
	private Set<Member_mail_pictureHBVO> member_mail_pictureHBVO;
	
	public Member_mailVO() {
	}

	public Member_mailVO(Integer send_no, Integer rcpt_no, Integer mail_read_stat, Integer mail_stat,
			String mail_cont, String mail_time) {
		super();
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

	public String getMail_time() {
		return mail_time;
	}

	public void setMail_time(String mail_time) {
		this.mail_time = mail_time;
	}

	public Set<Member_mail_pictureHBVO> getMember_mail_pictureHBVO() {
		return member_mail_pictureHBVO;
	}

	public void setMember_mail_pictureHBVO(Set<Member_mail_pictureHBVO> member_mail_pictureHBVO) {
		this.member_mail_pictureHBVO = member_mail_pictureHBVO;
	}
	
	public void add(Member_mail_pictureHBVO member_mail_pictureHBVO2) {
		
		if (member_mail_pictureHBVO == null) {
			member_mail_pictureHBVO = new LinkedHashSet<>();
		}
		
		member_mail_pictureHBVO.add(member_mail_pictureHBVO2);
		
		member_mail_pictureHBVO2.setMember_mailVO(this);
	}
	
}
