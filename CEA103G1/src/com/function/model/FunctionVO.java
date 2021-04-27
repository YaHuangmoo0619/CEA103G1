package com.function.model;

public class FunctionVO implements java.io.Serializable{
	
	private Integer fx_no;
	private String fx_name;
	
	public FunctionVO() {
	}
	
	public FunctionVO(Integer fx_no, String fx_name) {
		this.fx_no = fx_no;
		this.fx_name = fx_name;
	}

	public Integer getFx_no() {
		return fx_no;
	}

	public void setFx_no(Integer fx_no) {
		this.fx_no = fx_no;
	}

	public String getFx_name() {
		return fx_name;
	}

	public void setFx_name(String fx_name) {
		this.fx_name = fx_name;
	}
	
}
