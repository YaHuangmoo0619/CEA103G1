package com.authority.model;

public class AuthorityVO implements java.io.Serializable{
	private Integer emp_no;
	private Integer fx_no;
	
	public AuthorityVO() {
		
	}
	
	public AuthorityVO(Integer emp_no, Integer fx_no) {
		this.emp_no = emp_no;
		this.fx_no = fx_no;
	}

	public Integer getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}

	public Integer getFx_no() {
		return fx_no;
	}

	public void setFx_no(Integer fx_no) {
		this.fx_no = fx_no;
	}
	
	
}
