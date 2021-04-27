package com.member_rank.model;

public class Member_rankVO implements java.io.Serializable{
	
	private Integer rank_no;
	private String rank_name;
	private Integer pt_rwd_rto;
	
	public Integer getRank_no() {
		return rank_no;
	}
	
	public void setRank_no(Integer rank_no) {
		this.rank_no = rank_no;
	}
	
	public String getRank_name() {
		return rank_name;
	}
	
	public void setRank_name(String rank_name) {
		this.rank_name = rank_name;
	}
	
	public Integer getPt_rwd_rto() {
		return pt_rwd_rto;
	}
	
	public void setPt_rwd_rto(Integer pt_rwd_rto) {
		this.pt_rwd_rto = pt_rwd_rto;
	}
	
}