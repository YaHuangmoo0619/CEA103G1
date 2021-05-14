package com.article.model;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

public class ArticleVO implements Serializable {



	private Integer art_no;
	private Integer bd_cl_no;
	private Integer mbr_no;
	private Timestamp art_rel_time;
	private String art_title;
	private String art_cont;
	private Integer likes;
	private Integer art_stat;
	private Integer replies;
	private String art_first_img;
	
	public ArticleVO() {
		super();
	}

	
	public ArticleVO(Integer art_no,Integer bd_cl_no,Integer mbr_no,Timestamp art_rel_time,String art_title,String art_cont,Integer likes,Integer art_stat,Integer replies) {
		super();
		this.art_no=art_no;
		this.bd_cl_no=bd_cl_no;
		this.mbr_no=mbr_no;
		this.art_rel_time=art_rel_time;
		this.art_title=art_title;
		this.art_cont=art_cont;
		this.likes=likes;
		this.art_stat=art_stat;
		this.replies=replies;
		}
	
	public ArticleVO(Integer bd_cl_no,Integer mbr_no,Timestamp art_rel_time,String art_title,String art_cont,Integer likes,Integer art_stat,Integer replies) {
		super();
		this.bd_cl_no=bd_cl_no;
		this.mbr_no=mbr_no;
		this.art_rel_time=art_rel_time;
		this.art_title=art_title;
		this.art_cont=art_cont;
		this.likes=likes;
		this.art_stat=art_stat;
		this.replies=replies;
		}
	
	
	public Integer getArt_no() {
		return art_no;
	}


	public void setArt_no(Integer art_no) {
		this.art_no = art_no;
	}


	public Integer getBd_cl_no() {
		return bd_cl_no;
	}


	public void setBd_cl_no(Integer bd_cl_no) {
		this.bd_cl_no = bd_cl_no;
	}


	public Integer getMbr_no() {
		return mbr_no;
	}


	public void setMbr_no(Integer mbr_no) {
		this.mbr_no = mbr_no;
	}


	public Timestamp getArt_rel_time() {
		return art_rel_time;
	}


	public void setArt_rel_time(Timestamp art_rel_time) {
		this.art_rel_time = art_rel_time;
	}


	public String getArt_title() {
		return art_title;
	}


	public void setArt_title(String art_title) {
		this.art_title = art_title;
	}


	public String getArt_cont() {
		return art_cont;
	}


	public void setArt_cont(String art_cont) {
		this.art_cont = art_cont;
	}


	public Integer getLikes() {
		return likes;
	}


	public void setLikes(Integer likes) {
		this.likes = likes;
	}


	public Integer getArt_stat() {
		return art_stat;
	}


	public void setArt_stat(Integer art_stat) {
		this.art_stat = art_stat;
	}


	public Integer getReplies() {
		return replies;
	}


	public void setReplies(Integer replies) {
		this.replies = replies;
	}


	public String getArt_first_img() {
		return art_first_img;
	}


	public void setArt_first_img(String art_first_img) {
		this.art_first_img = art_first_img;
	}
	}


