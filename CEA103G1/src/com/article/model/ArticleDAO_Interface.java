package com.article.model;
import java.util.List;
import java.util.Map;
public interface ArticleDAO_Interface {
	public void insert(ArticleVO articleVO);

	public void update(ArticleVO articleVO);
	
	public void plus_like(ArticleVO articleVO);
	
	public void minus_like(ArticleVO articleVO);
	
	public void plus_reply(ArticleVO articleVO);
	
	public void minus_reply(ArticleVO articleVO);
	
	public void delete(Integer art_no);
	
	public void hide(ArticleVO articleVO);

	public ArticleVO findByPrimaryKey(Integer art_no);
	
	public ArticleVO findLast();
	
	public List<ArticleVO> findByBd_cl_no_front(Integer bd_cl_no);
	
	public List<ArticleVO> findByBd_cl_no_back(Integer bd_cl_no);
	
	public List<ArticleVO> getAll_Front();
	
	public List<ArticleVO> findByMbr_no(Integer mbr_no);
	
	public List<ArticleVO> getAll_Back();
}
