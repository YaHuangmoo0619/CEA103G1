package com.campsite.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

public class CampVO implements Serializable {
	private Integer camp_no;
	private Integer cso_no;
	private Integer dist_no;
	private String camp_name;
	private Integer campsite_Status;
	private String campInfo;
	private String note;
	private byte[] config;
	private Integer review_Status;
	private String height;
	private String wireless;
	private Integer pet;
	private String facility;
	private Integer operate_Date;
	private String park;
	private String county;
	private String district;
	private String address;
	private Double latitude;
	private Double longitude;
	private Integer total_Stars;
	private Integer total_Cmnt;
	private String first_pic;
	private Integer low_pc;
	private Integer collected;

	public CampVO() {
		super();
	}

	public CampVO(Integer camp_no, Integer cso_no, Integer dist_no, String camp_name, Integer campsite_Status,
			String campInfo, String note, byte[] config, Integer review_Status, String height, String wireless,
			Integer pet, String facility, Integer operate_Date, String park, String address, Double latitude,
			Double longitude, Integer total_Stars, Integer total_Cmnt) {
		super();
		this.camp_no = camp_no;
		this.cso_no = cso_no;
		this.dist_no = dist_no;
		this.camp_name = camp_name;
		this.campsite_Status = campsite_Status;
		this.campInfo = campInfo;
		this.note = note;
		this.config = config;
		this.review_Status = review_Status;
		this.height = height;
		this.wireless = wireless;
		this.pet = pet;
		this.facility = facility;
		this.operate_Date = operate_Date;
		this.park = park;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.total_Stars = total_Stars;
		this.total_Cmnt = total_Cmnt;
	}

	public Integer getCamp_no() {
		return camp_no;
	}

	public void setCamp_no(Integer camp_no) {
		this.camp_no = camp_no;
	}

	public Integer getCso_no() {
		return cso_no;
	}

	public void setCso_no(Integer cso_no) {
		this.cso_no = cso_no;
	}

	public Integer getDist_no() {
		return dist_no;
	}

	public void setDist_no(Integer dist_no) {
		this.dist_no = dist_no;
	}

	public String getCamp_name() {
		return camp_name;
	}

	public void setCamp_name(String camp_name) {
		this.camp_name = camp_name;
	}

	public Integer getCampsite_Status() {
		return campsite_Status;
	}

	public void setCampsite_Status(Integer campsite_Status) {
		this.campsite_Status = campsite_Status;
	}

	public String getCampInfo() {
		return campInfo;
	}

	public void setCampInfo(String campInfo) {
		this.campInfo = campInfo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public byte[] getConfig() {
		return config;
	}

	public void setConfig(byte[] bs) {
		this.config = bs;
	}

	public Integer getReview_Status() {
		return review_Status;
	}

	public void setReview_Status(Integer review_Status) {
		this.review_Status = review_Status;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWireless() {
		return wireless;
	}

	public void setWireless(String wireless) {
		this.wireless = wireless;
	}

	public Integer getPet() {
		return pet;
	}

	public void setPet(Integer pet) {
		this.pet = pet;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public Integer getOperate_Date() {
		return operate_Date;
	}

	public void setOperate_Date(Integer operate_Date) {
		this.operate_Date = operate_Date;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longtitude) {
		this.longitude = longtitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getTotal_Stars() {
		return total_Stars;
	}

	public void setTotal_Stars(Integer total_Stars) {
		this.total_Stars = total_Stars;
	}

	public Integer getTotal_Cmnt() {
		return total_Cmnt;
	}

	public void setTotal_Cmnt(Integer total_Cmnt) {
		this.total_Cmnt = total_Cmnt;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getFirst_pic() {
		return first_pic;
	}

	public void setFirst_pic(String first_pic) {
		this.first_pic = first_pic;
	}

	public Integer getLow_pc() {
		return low_pc;
	}

	public void setLow_pc(Integer low_pc) {
		this.low_pc = low_pc;
	}

	public Integer getCollected() {
		return collected;
	}

	public void setCollected(Integer collected) {
		this.collected = collected;
	}
}
