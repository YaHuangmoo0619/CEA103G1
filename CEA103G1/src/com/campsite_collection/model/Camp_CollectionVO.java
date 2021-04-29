package com.campsite_collection.model;

public class Camp_CollectionVO {
	private Integer camp_no;
	private Integer mbr_no;
	public Camp_CollectionVO() {
		super();
	}
	public Camp_CollectionVO(Integer camp_no, Integer mbr_no) {
		super();
		this.camp_no = camp_no;
		this.mbr_no = mbr_no;
	}
	public Integer getCamp_no() {
		return camp_no;
	}
	public void setCamp_no(Integer camp_no) {
		this.camp_no = camp_no;
	}
	public Integer getMbr_no() {
		return mbr_no;
	}
	public void setMbr_no(Integer mbr_no) {
		this.mbr_no = mbr_no;
	}
}
