package com.campsite_feature.model;

import java.io.Serializable;

public class Camp_FeatureVO implements Serializable{
    private Integer camp_fl_no;
    private Integer camp_no;
	public Camp_FeatureVO() {
		super();
	}
	public Camp_FeatureVO(Integer camp_fl_no, Integer camp_no) {
		super();
		this.camp_fl_no = camp_fl_no;
		this.camp_no = camp_no;
	}
	public Integer getCamp_fl_no() {
		return camp_fl_no;
	}
	public void setCamp_fl_no(Integer camp_fl_no) {
		this.camp_fl_no = camp_fl_no;
	}
	public Integer getCamp_no() {
		return camp_no;
	}
	public void setCamp_no(Integer camp_no) {
		this.camp_no = camp_no;
	}
    
    
}
