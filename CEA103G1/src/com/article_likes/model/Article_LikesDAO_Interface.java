package com.article_likes.model;
import java.util.List;

public interface Article_LikesDAO_Interface {
	public void insert(Article_LikesVO Article_LikesVO); //按讚

	//public void update(Article_LikesVO Article_LikesVO);//感覺不需要 先不實作
 
	public void delete(Integer mbr_no,Integer art_no); //收回讚

	//public Article_LikesVO findByPrimaryKey(Integer mbr_no,Integer art_no); //感覺不需要 先不實作

	//public List<Article_LikesVO> getAll(); //按讚總表 //感覺不需要 先不實作

	public List<Article_LikesVO> findByMbr_no(Integer mbr_no); //這個人按了哪些文章的讚

	public List<Article_LikesVO> findByArt_no(Integer art_no); //這篇文章被哪些人按讚
}
