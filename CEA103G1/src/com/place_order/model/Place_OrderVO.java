package com.place_order.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Place_OrderVO implements Serializable{
	private Integer plc_ord_no;
	private Integer mbr_no;
	private Integer camp_no;
	private Timestamp plc_ord_time;
	private Date ckin_date;
	private Date ckout_date;
	private Integer plc_amt;
	private Integer plc_ord_sum;
	private Integer ex_ppl;
	private Integer pay_meth;
	private Integer pay_stat;
	private Integer used_pt;
	private Integer ckin_stat;
	private Integer receipt;
	private String rmk;
	private String camp_name;

	public Place_OrderVO() {
		super();
	}

	public Place_OrderVO(Integer plc_ord_no, Integer mbr_no, Integer camp_no, Timestamp plc_ord_time, Date ckin_date,
			Date ckout_date, Integer plc_amt, Integer plc_ord_sum, Integer ex_ppl, Integer pay_meth, Integer pay_stat,
			Integer used_pt, Integer ckin_stat, Integer receipt, String rmk) {
		super();
		this.plc_ord_no = plc_ord_no;
		this.mbr_no = mbr_no;
		this.camp_no = camp_no;
		this.plc_ord_time = plc_ord_time;
		this.ckin_date = ckin_date;
		this.ckout_date = ckout_date;
		this.plc_amt = plc_amt;
		this.plc_ord_sum = plc_ord_sum;
		this.ex_ppl = ex_ppl;
		this.pay_meth = pay_meth;
		this.pay_stat = pay_stat;
		this.used_pt = used_pt;
		this.ckin_stat = ckin_stat;
		this.receipt = receipt;
		this.rmk = rmk;
	}

	public Integer getPlc_ord_no() {
		return plc_ord_no;
	}

	public void setPlc_ord_no(Integer plc_ord_no) {
		this.plc_ord_no = plc_ord_no;
	}

	public Integer getMbr_no() {
		return mbr_no;
	}

	public void setMbr_no(Integer mbr_no) {
		this.mbr_no = mbr_no;
	}

	public Integer getCamp_no() {
		return camp_no;
	}

	public void setCamp_no(Integer camp_no) {
		this.camp_no = camp_no;
	}

	public Timestamp getPlc_ord_time() {
		return plc_ord_time;
	}

	public void setPlc_ord_time(Timestamp plc_ord_time) {
		this.plc_ord_time = plc_ord_time;
	}

	public Date getCkin_date() {
		return ckin_date;
	}

	public void setCkin_date(Date ckin_date) {
		this.ckin_date = ckin_date;
	}

	public Date getCkout_date() {
		return ckout_date;
	}

	public void setCkout_date(Date ckout_date) {
		this.ckout_date = ckout_date;
	}

	public Integer getPlc_amt() {
		return plc_amt;
	}

	public void setPlc_amt(Integer plc_amt) {
		this.plc_amt = plc_amt;
	}

	public Integer getPlc_ord_sum() {
		return plc_ord_sum;
	}

	public void setPlc_ord_sum(Integer plc_ord_sum) {
		this.plc_ord_sum = plc_ord_sum;
	}

	public Integer getEx_ppl() {
		return ex_ppl;
	}

	public void setEx_ppl(Integer ex_ppl) {
		this.ex_ppl = ex_ppl;
	}

	public Integer getPay_meth() {
		return pay_meth;
	}

	public void setPay_meth(Integer pay_meth) {
		this.pay_meth = pay_meth;
	}

	public Integer getPay_stat() {
		return pay_stat;
	}

	public void setPay_stat(Integer pay_stat) {
		this.pay_stat = pay_stat;
	}

	public Integer getUsed_pt() {
		return used_pt;
	}

	public void setUsed_pt(Integer used_pt) {
		this.used_pt = used_pt;
	}

	public Integer getCkin_stat() {
		return ckin_stat;
	}

	public void setCkin_stat(Integer ckin_stat) {
		this.ckin_stat = ckin_stat;
	}

	public Integer getReceipt() {
		return receipt;
	}

	public void setReceipt(Integer receipt) {
		this.receipt = receipt;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public String getCamp_name() {
		return camp_name;
	}

	public void setCamp_name(String camp_name) {
		this.camp_name = camp_name;
	}
	
}
