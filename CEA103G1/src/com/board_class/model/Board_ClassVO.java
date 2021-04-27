package com.board_class.model;

import java.io.Serializable;

public class Board_ClassVO implements Serializable{
	private Integer bd_cl_no;
	private String bd_name;
	private Integer bd_stat;
	
	public Board_ClassVO() {
		super();
	}

	public Board_ClassVO(Integer bd_cl_no, String bd_name, Integer bd_stat) {
		super();
		this.bd_cl_no = bd_cl_no;
		this.bd_name = bd_name;
		this.bd_stat = bd_stat;
	}

	public Integer getBd_cl_no() {
		return bd_cl_no;
	}

	public void setBd_cl_no(Integer bd_cl_no) {
		this.bd_cl_no = bd_cl_no;
	}

	public String getBd_name() {
		return bd_name;
	}

	public void setBd_name(String bd_name) {
		this.bd_name = bd_name;
	}

	public Integer getBd_stat() {
		return bd_stat;
	}

	public void setBd_stat(Integer bd_stat) {
		this.bd_stat = bd_stat;
	}
	
	
	
}
