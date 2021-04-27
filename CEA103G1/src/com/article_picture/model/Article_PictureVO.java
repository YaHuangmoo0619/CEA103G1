package com.article_picture.model;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

public class Article_PictureVO implements Serializable{

	private Integer art_pic_no;
	private Integer art_no;
	private byte[] art_pic;
	
	public Article_PictureVO() {
		super();
	}
	
	
	public Article_PictureVO(Integer art_pic_no,Integer art_no,byte[] art_pic) {
		super();
		this.art_pic_no=art_pic_no;
		this.art_no=art_no;
		this.art_pic=art_pic;
	}
	public Integer getArt_pic_no() {
		return art_pic_no;
	}
	public void setArt_pic_no(Integer art_pic_no) {
		this.art_pic_no = art_pic_no;
	}
	public Integer getArt_no() {
		return art_no;
	}
	public void setArt_no(Integer art_no) {
		this.art_no = art_no;
	}
	public byte[] getArt_pic() {
		return art_pic;
	}
	public void setArt_pic(byte[] art_pic) {
		this.art_pic = art_pic;
	}
}
