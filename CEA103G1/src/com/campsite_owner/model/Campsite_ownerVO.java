package com.campsite_owner.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Campsite_ownerVO implements java.io.Serializable{
	
	private Integer cso_no;
	private String acc;
	private String pwd;
	private String id;
	private String name;
	private Date bday;
	private Integer sex;
	private String mobile;
	private String mail;
	private String city;
	private String dist;
	private String add;
	private Timestamp join_time;
	private Byte id_picf;
	private Byte id_picb;
	private Byte id_pic2f;
	private Integer stat;
	private Byte sticker;
	private Byte license;
	private Integer bank_no;
	private String bank_acc;
	
	public Integer getCso_no() {
		return cso_no;
	}
	
	public void setCso_no(Integer cso_no) {
		this.cso_no = cso_no;
	}
	
	public String getAcc() {
		return acc;
	}
	
	public void setAcc(String acc) {
		this.acc = acc;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getBday() {
		return bday;
	}
	
	public void setBday(Date bday) {
		this.bday = bday;
	}
	
	public Integer getSex() {
		return sex;
	}
	
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getDist() {
		return dist;
	}
	
	public void setDist(String dist) {
		this.dist = dist;
	}
	
	public String getAdd() {
		return add;
	}
	
	public void setAdd(String add) {
		this.add = add;
	}
	
	public Timestamp getJoin_time() {
		return join_time;
	}
	
	public void setJoin_time(Timestamp join_time) {
		this.join_time = join_time;
	}
	
	public Byte getId_picf() {
		return id_picf;
	}
	
	public void setId_picf(Byte id_picf) {
		this.id_picf = id_picf;
	}
	
	public Byte getId_picb() {
		return id_picb;
	}
	
	public void setId_picb(Byte id_picb) {
		this.id_picb = id_picb;
	}
	
	public Byte getId_pic2f() {
		return id_pic2f;
	}
	
	public void setId_pic2f(Byte id_pic2f) {
		this.id_pic2f = id_pic2f;
	}
	
	public Integer getStat() {
		return stat;
	}
	
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	
	public Byte getSticker() {
		return sticker;
	}
	
	public void setSticker(Byte sticker) {
		this.sticker = sticker;
	}
	
	public Byte getLicense() {
		return license;
	}
	
	public void setLicense(Byte license) {
		this.license = license;
	}
	
	public Integer getBank_no() {
		return bank_no;
	}
	
	public void setBank_no(Integer bank_no) {
		this.bank_no = bank_no;
	}
	
	public String getBank_acc() {
		return bank_acc;
	}
	
	public void setBank_acc(String bank_acc) {
		this.bank_acc = bank_acc;
	}
	
}