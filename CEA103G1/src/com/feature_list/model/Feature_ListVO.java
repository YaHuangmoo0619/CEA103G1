package com.feature_list.model;

import java.io.Serializable;

public class Feature_ListVO implements Serializable{
    private Integer camp_fl_no;
    private String camp_fl_name;
	public Feature_ListVO() {
		super();
	}
	public Feature_ListVO(Integer camp_fl_no, String camp_fl_name) {
		super();
		this.camp_fl_no = camp_fl_no;
		this.camp_fl_name = camp_fl_name;
	}
	public Integer getCamp_fl_no() {
		return camp_fl_no;
	}
	public void setCamp_fl_no(Integer camp_fl_no) {
		this.camp_fl_no = camp_fl_no;
	}
	public String getCamp_fl_name() {
		return camp_fl_name;
	}
	public void setCamp_fl_name(String camp_fl_name) {
		this.camp_fl_name = camp_fl_name;
	}
    
    
}
