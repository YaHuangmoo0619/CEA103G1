package com.article_collection.model;
import java.util.List;
import java.util.Map;

public interface Article_CollectionDAO_Interface {
	public void insert(Article_CollectionVO article_CollectionVO); //加入收藏

	public void delete(Integer mbr_no,Integer art_no); //取消收藏

	//public List<Article_CollectionVO> getAll();//收藏總表，感覺並不需要

	public List<Article_CollectionVO> findByMbr_no(Integer mbrno); //取得某個人收藏的文章

	public List<Article_CollectionVO> findByArt_no(Integer Art_no); //取得收藏某文章的所有人
}
