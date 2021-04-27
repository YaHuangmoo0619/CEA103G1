package com.camp_picture.model;

import java.io.Serializable;
import java.sql.*;

public class Camp_PictureVO implements Serializable{
    private Integer camp_pic_no;
    private Integer camp_no;
    private Blob camp_pic;
    private Timestamp camp_pic_time;
	public Camp_PictureVO() {
		super();
	}
	public Camp_PictureVO(Integer camp_pic_no, Integer camp_no, Blob camp_pic, Timestamp camp_pic_time) {
		super();
		this.camp_pic_no = camp_pic_no;
		this.camp_no = camp_no;
		this.camp_pic = camp_pic;
		this.camp_pic_time = camp_pic_time;
	}
	public Integer getCamp_pic_no() {
		return camp_pic_no;
	}
	public void setCamp_pic_no(Integer camp_pic_no) {
		this.camp_pic_no = camp_pic_no;
	}
	public Integer getCamp_no() {
		return camp_no;
	}
	public void setCamp_no(Integer camp_no) {
		this.camp_no = camp_no;
	}
	public Blob getCamp_pic() {
		return camp_pic;
	}
	public void setCamp_pic(Blob camp_pic) {
		this.camp_pic = camp_pic;
	}
	public Timestamp getCamp_pic_time() {
		return camp_pic_time;
	}
	public void setCamp_pic_time(Timestamp camp_pic_time) {
		this.camp_pic_time = camp_pic_time;
	}
}
