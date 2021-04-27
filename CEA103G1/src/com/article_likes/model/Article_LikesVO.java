package com.article_likes.model;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
public class Article_LikesVO implements Serializable{
	
	private Integer art_no;
	private Integer mbr_no;
	
	public Article_LikesVO() {
		super();
	}
	
	
	
	public Article_LikesVO(Integer mbr_no,Integer art_no) {
		super();
		this.art_no = art_no;
		this.mbr_no = mbr_no;
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

}
