package com.article.model;
import java.util.List;
import java.util.Map;
public interface ArticleDAO_Interface {
	public void insert(ArticleVO articleVO);

	public void update(ArticleVO articleVO);

	public void delete(Integer art_no);

	public ArticleVO findByPrimaryKey(Integer art_no);
	
	public List<ArticleVO> findByBd_cl_no(Integer bd_cl_no);
	
	public List<ArticleVO> getAll();
}
