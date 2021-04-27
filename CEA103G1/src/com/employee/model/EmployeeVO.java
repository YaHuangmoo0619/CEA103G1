package com.employee.model;

import java.io.Serializable;

public class EmployeeVO implements Serializable {

	private Integer emp_no;
	private String acc;
	private String pwd;
	private String name;
	private String email;
	private Integer emp_stat;

	public EmployeeVO() {
		
	}

	public EmployeeVO(Integer emp_no, String acc, String pwd, String name, String email, Integer emp_stat) {
		this.emp_no = emp_no;
		this.acc = acc;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.emp_stat = emp_stat;
	}
	
	public EmployeeVO(String acc, String pwd, String name) {
		this.acc = acc;
		this.pwd = pwd;
		this.name = name;
	}

	public Integer getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEmp_stat() {
		return emp_stat;
	}

	public void setEmp_stat(Integer emp_stat) {
		this.emp_stat = emp_stat;
	}
	
	
}
