package com.equipment.model;

import java.io.Serializable;

public class EquipmentVO implements Serializable{
	private Integer eq_no;
	private String eq_name;

	public EquipmentVO() {
		super();
	}

	public EquipmentVO(Integer eq_no, String eq_name) {
		super();
		this.eq_no = eq_no;
		this.eq_name = eq_name;
	}

	public Integer getEq_no() {
		return eq_no;
	}

	public void setEq_no(Integer eq_no) {
		this.eq_no = eq_no;
	}

	public String getEq_name() {
		return eq_name;
	}

	public void setEq_name(String eq_name) {
		this.eq_name = eq_name;
	}

}
