package com.message_record.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Message_recordVO implements Serializable {

	private Integer msg_no;
	private Integer emp_no;
	private Integer mbr_no;
	private String msg_cont;
	private Timestamp msg_time;
		
	public Message_recordVO() {

	}
	
	public Message_recordVO(Integer msg_no, Integer emp_no, Integer mbr_no, String msg_cont, Timestamp msg_time) {
		super();
		this.msg_no = msg_no;
		this.emp_no = emp_no;
		this.mbr_no = mbr_no;
		this.msg_cont = msg_cont;
		this.msg_time = msg_time;
	}

	public Integer getMsg_no() {
		return msg_no;
	}
	public void setMsg_no(Integer msg_no) {
		this.msg_no = msg_no;
	}
	public Integer getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}
	public Integer getMbr_no() {
		return mbr_no;
	}
	public void setMbr_no(Integer mbr_no) {
		this.mbr_no = mbr_no;
	}
	public String getMsg_cont() {
		return msg_cont;
	}
	public void setMsg_cont(String msg_cont) {
		this.msg_cont = msg_cont;
	}
	public Timestamp getMsg_time() {
		return msg_time;
	}
	public void setMsg_time(Timestamp msg_time) {
		this.msg_time = msg_time;
	}
	
	
}
