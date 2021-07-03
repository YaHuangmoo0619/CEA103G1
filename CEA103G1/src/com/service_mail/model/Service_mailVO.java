package com.service_mail.model;

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

import com.service_mail_picture.model.Service_mail_pictureHBVO;

@Entity
@Table(name="service_mail")
public class Service_mailVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mail_no")
	private Integer mail_no;
	
	@Column(name="emp_no")
	private Integer emp_no;
	
	@Column(name="mbr_no")
	private Integer mbr_no;
	
	@Column(name="mail_cont")
	private String mail_cont;
	
	@Column(name="mail_stat")
	private Integer mail_stat;
	
	@Column(name="mail_read_stat")
	private Integer mail_read_stat;
	
	@Column(name="mail_time")
	private String mail_time;
	
	@OneToMany(fetch=FetchType.LAZY,
			cascade=CascadeType.ALL,
			mappedBy="service_mailVO")
	private Set<Service_mail_pictureHBVO> service_mail_pictureHBVO;
	
	public Service_mailVO() {
	}

	public Service_mailVO(Integer emp_no, Integer mbr_no, String mail_cont, Integer mail_stat,
			Integer mail_read_stat, String mail_time) {
		super();
//		this.mail_no = mail_no;
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

	public String getMail_time() {
		return mail_time;
	}

	public void setMail_time(String mail_time) {
		this.mail_time = mail_time;
	}
	
	public void add(Service_mail_pictureHBVO service_mail_pictureHBVO2) {
		
		if(service_mail_pictureHBVO == null) {
			service_mail_pictureHBVO = new LinkedHashSet<>();
		}
		
		service_mail_pictureHBVO.add(service_mail_pictureHBVO2);
		
		service_mail_pictureHBVO2.setService_mailVO(this);
	}
	
}
