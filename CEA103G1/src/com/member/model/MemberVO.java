package com.member.model;

import java.sql.Date;
import java.sql.Timestamp;

public class MemberVO implements java.io.Serializable{
	
	private Integer mbr_no;
	private Integer rank_no;
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
	private String card;
	private Integer pt;
	private Integer acc_stat;
	private Integer exp;
	private Byte sticker;
	private String rmk;
	
	public Integer getMbr_no() {
		return mbr_no;
	}
	
	public void setMbr_no(Integer mbr_no) {
		this.mbr_no = mbr_no;
	}
	
	public Integer getRank_no() {
		return rank_no;
	}
	
	public void setRank_no(Integer rank_no) {
		this.rank_no = rank_no;
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
	
	public String getCard() {
		return card;
	}
	
	public void setCard(String card) {
		this.card = card;
	}
	
	public Integer getPt() {
		return pt;
	}
	
	public void setPt(Integer pt) {
		this.pt = pt;
	}
	
	public Integer getAcc_stat() {
		return acc_stat;
	}
	
	public void setAcc_stat(Integer acc_stat) {
		this.acc_stat = acc_stat;
	}
	
	public Integer getExp() {
		return exp;
	}
	
	public void setExp(Integer exp) {
		this.exp = exp;
	}
	
	public Byte getSticker() {
		return sticker;
	}
	
	public void setSticker(Byte sticker) {
		this.sticker = sticker;
	}
	
	public String getRmk() {
		return rmk;
	}
	
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
	
}