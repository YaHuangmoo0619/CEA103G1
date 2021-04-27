package com.article_collection.model;

import java.io.Serializable;

public class Article_CollectionVO implements Serializable {
	private Integer mbr_no;
	private Integer art_no;
	
	
	public Article_CollectionVO() {
		super();
	}

	public Article_CollectionVO(Integer mbr_no,Integer art_no) {
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
