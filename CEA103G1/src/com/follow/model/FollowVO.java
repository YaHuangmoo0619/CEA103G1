package com.follow.model;

import java.io.Serializable;

public class FollowVO implements Serializable{
	private Integer flwed_mbr_no;
	private Integer flw_mbr_no;
	
	public FollowVO() {
		super();
	}

	public FollowVO(Integer flwed_mbr_no, Integer flw_mbr_no) {
		super();
		this.flwed_mbr_no = flwed_mbr_no;
		this.flw_mbr_no = flw_mbr_no;
	}

	public Integer getFlwed_mbr_no() {
		return flwed_mbr_no;
	}

	public void setFlwed_mbr_no(Integer flwed_mbr_no) {
		this.flwed_mbr_no = flwed_mbr_no;
	}

	public Integer getFlw_mbr_no() {
		return flw_mbr_no;
	}

	public void setFlw_mbr_no(Integer flw_mbr_no) {
		this.flw_mbr_no = flw_mbr_no;
	}
	
	
	
	
}
