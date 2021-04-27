package com.district.model;

import java.io.Serializable;

public class DistrictVO implements Serializable{
	private Integer dist_no;
	private String dist_name;
	private String cty;
	private String area;

	public DistrictVO() {
		super();
	}

	public DistrictVO(Integer dist_no, String dist_name, String cty, String area) {
		super();
		this.dist_no = dist_no;
		this.dist_name = dist_name;
		this.cty = cty;
		this.area = area;
	}

	public Integer getDist_no() {
		return dist_no;
	}

	public void setDist_no(Integer dist_no) {
		this.dist_no = dist_no;
	}

	public String getDist_name() {
		return dist_name;
	}

	public void setDist_name(String dist_name) {
		this.dist_name = dist_name;
	}

	public String getCty() {
		return cty;
	}

	public void setCty(String cty) {
		this.cty = cty;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
