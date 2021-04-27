package com.article_reply.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Article_ReplyVO implements Serializable{
	private Integer art_rep_no;
	private Integer art_no;
	private Integer mbr_no;
	private String 	rep_cont;
	private Timestamp  rep_time;
	private Integer rep_stat;
	private Integer likes;
	
	public Article_ReplyVO() {
		super();
	}

	public Article_ReplyVO(Integer art_rep_no, Integer art_no, Integer mbr_no, String rep_cont, Timestamp rep_time,
			Integer rep_stat,Integer likes) {
		this.art_rep_no = art_rep_no;
		this.art_no = art_no;
		this.mbr_no = mbr_no;
		this.rep_cont = rep_cont;
		this.rep_time = rep_time;
		this.rep_stat = rep_stat;
		this.likes = likes;
	}

	public Integer getArt_rep_no() {
		return art_rep_no;
	}

	public void setArt_rep_no(Integer art_rep_no) {
		this.art_rep_no = art_rep_no;
	}

	public Integer getArt_no() {
		return art_no;
	}

	public void setArt_no(Integer art_no) {
		this.art_no = art_no;
	}

	public Integer getMbr_no() {
		return mbr_no;
	}

	public void setMbr_no(Integer mbr_no) {
		this.mbr_no = mbr_no;
	}

	public String getRep_cont() {
		return rep_cont;
	}

	public void setRep_cont(String rep_cont) {
		this.rep_cont = rep_cont;
	}

	public Timestamp getRep_time() {
		return rep_time;
	}

	public void setRep_time(Timestamp rep_time) {
		this.rep_time = rep_time;
	}

	public Integer getRep_stat() {
		return rep_stat;
	}

	public void setRep_stat(Integer rep_stat) {
		this.rep_stat = rep_stat;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	
	
	
}
