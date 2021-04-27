package com.place.model;

import java.io.Serializable;

public class PlaceVO implements Serializable{
    private Integer plc_no;
    private Integer camp_no;
    private String plc_name;
    private Integer ppl;
    private Integer max_ppl;
    private Integer pc_wkdy;
    private Integer pc_wknd;
    private Integer open_stat;
    private Integer plc_stat;
    private String camp_name;
	public PlaceVO() {
		super();
	}
	public PlaceVO(Integer plc_no, Integer camp_no, String plc_name, Integer ppl, Integer max_ppl, Integer pc_wkdy,
			Integer pc_wknd, Integer open_stat, Integer plc_stat) {
		super();
		this.plc_no = plc_no;
		this.camp_no = camp_no;
		this.plc_name = plc_name;
		this.ppl = ppl;
		this.max_ppl = max_ppl;
		this.pc_wkdy = pc_wkdy;
		this.pc_wknd = pc_wknd;
		this.open_stat = open_stat;
		this.plc_stat = plc_stat;
	}
	public Integer getPlc_no() {
		return plc_no;
	}
	public void setPlc_no(Integer plc_no) {
		this.plc_no = plc_no;
	}
	public Integer getCamp_no() {
		return camp_no;
	}
	public void setCamp_no(Integer camp_no) {
		this.camp_no = camp_no;
	}
	public String getPlc_name() {
		return plc_name;
	}
	public void setPlc_name(String plc_name) {
		this.plc_name = plc_name;
	}
	public Integer getPpl() {
		return ppl;
	}
	public void setPpl(Integer ppl) {
		this.ppl = ppl;
	}
	public Integer getMax_ppl() {
		return max_ppl;
	}
	public void setMax_ppl(Integer max_ppl) {
		this.max_ppl = max_ppl;
	}
	public Integer getPc_wkdy() {
		return pc_wkdy;
	}
	public void setPc_wkdy(Integer pc_wkdy) {
		this.pc_wkdy = pc_wkdy;
	}
	public Integer getPc_wknd() {
		return pc_wknd;
	}
	public void setPc_wknd(Integer pc_wknd) {
		this.pc_wknd = pc_wknd;
	}
	public Integer getOpen_stat() {
		return open_stat;
	}
	public void setOpen_stat(Integer open_stat) {
		this.open_stat = open_stat;
	}
	public Integer getPlc_stat() {
		return plc_stat;
	}
	public void setPlc_stat(Integer plc_stat) {
		this.plc_stat = plc_stat;
	}
	public String getCamp_name() {
		return camp_name;
	}
	public void setCamp_name(String camp_name) {
		this.camp_name = camp_name;
	}
}
