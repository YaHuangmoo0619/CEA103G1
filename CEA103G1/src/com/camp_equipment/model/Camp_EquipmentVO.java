package com.camp_equipment.model;

import java.io.Serializable;

public class Camp_EquipmentVO implements Serializable{
	private Integer eq_no;
	private Integer camp_no;

	public Camp_EquipmentVO() {
		super();
	}

	public Camp_EquipmentVO(Integer eq_no, Integer camp_no) {
		super();
		this.eq_no = eq_no;
		this.camp_no = camp_no;
	}

	public Integer getEq_no() {
		return eq_no;
	}

	public void setEq_no(Integer eq_no) {
		this.eq_no = eq_no;
	}

	public Integer getCamp_no() {
		return camp_no;
	}

	public void setCamp_no(Integer camp_no) {
		this.camp_no = camp_no;
	}

}
