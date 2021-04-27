package com.article_collection.model;
import java.util.List;
import java.util.Map;

public interface Article_CollectionDAO_Interface {
	public void insert(Article_CollectionVO article_CollectionVO);

	public void delete(Integer mbr_no,Integer art_no);

	public List<Article_CollectionVO> getAll();

	public List<Article_CollectionVO> findByMbr_no(Integer mbrno);

	public List<Article_CollectionVO> findByArt_no(Integer Art_no);
}
