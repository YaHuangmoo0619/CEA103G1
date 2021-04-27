package com.article_reply.model;
import java.util.List;
import java.util.Map;

import com.article.model.ArticleVO;
public interface Article_ReplyDAO_Interface {
	public void insert(Article_ReplyVO article_ReplyVO);

	public void update(Article_ReplyVO article_ReplyVO);

	public void delete(Integer art_rep_no);
	
	public void hide(Article_ReplyVO article_ReplyVO);

	public Article_ReplyVO findByPrimaryKey(Integer art_rep_no);
	
	public List<Article_ReplyVO> findByArt_no(Integer art_no);
	
	public List<Article_ReplyVO> getAll();
}
