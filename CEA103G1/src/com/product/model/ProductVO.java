package com.product.model;

import java.io.Serializable;

public class ProductVO implements java.io.Serializable{
	private Integer prod_no;
	private Integer prod_cat_no;
	private Integer prod_stat;
	private String prod_name;
	private Integer prod_pc;
	private Integer prod_stg;
	private String prod_info;
	private String prod_bnd;
	private String prod_clr;
	private String prod_size;
	private Integer ship_meth;
	
	public ProductVO() {
		
	}
	
	public ProductVO(Integer prod_no, Integer prod_cat_no, Integer prod_stat, String prod_name, Integer prod_pc,
			Integer prod_stg, String prod_info, String prod_bnd, String prod_clr, String prod_size, Integer ship_meth) {
		super();
		this.prod_no = prod_no;
		this.prod_cat_no = prod_cat_no;
		this.prod_stat = prod_stat;
		this.prod_name = prod_name;
		this.prod_pc = prod_pc;
		this.prod_stg = prod_stg;
		this.prod_info = prod_info;
		this.prod_bnd = prod_bnd;
		this.prod_clr = prod_clr;
		this.prod_size = prod_size;
		this.ship_meth = ship_meth;
	}
	
	public Integer getProd_no() {
		return prod_no;
	}
	public void setProd_no(Integer prod_no) {
		this.prod_no = prod_no;
	}
	public Integer getProd_cat_no() {
		return prod_cat_no;
	}
	public void setProd_cat_no(Integer prod_cat_no) {
		this.prod_cat_no = prod_cat_no;
	}
	public Integer getProd_stat() {
		return prod_stat;
	}
	public void setProd_stat(Integer prod_stat) {
		this.prod_stat = prod_stat;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public Integer getProd_pc() {
		return prod_pc;
	}
	public void setProd_pc(Integer prod_pc) {
		this.prod_pc = prod_pc;
	}
	public Integer getProd_stg() {
		return prod_stg;
	}
	public void setProd_stg(Integer prod_stg) {
		this.prod_stg = prod_stg;
	}
	public String getProd_info() {
		return prod_info;
	}
	public void setProd_info(String prod_info) {
		this.prod_info = prod_info;
	}
	public String getProd_bnd() {
		return prod_bnd;
	}
	public void setProd_bnd(String prod_bnd) {
		this.prod_bnd = prod_bnd;
	}
	public String getProd_clr() {
		return prod_clr;
	}
	public void setProd_clr(String prod_clr) {
		this.prod_clr = prod_clr;
	}
	public String getProd_size() {
		return prod_size;
	}
	public void setProd_size(String prod_size) {
		this.prod_size = prod_size;
	}
	public Integer getShip_meth() {
		return ship_meth;
	}
	public void setShip_meth(Integer ship_meth) {
		this.ship_meth = ship_meth;
	}
	
}
