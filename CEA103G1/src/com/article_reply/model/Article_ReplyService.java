package com.article_reply.model;
import java.sql.Timestamp;
import java.util.List;

import com.article.model.ArticleVO;


public class Article_ReplyService {

	private Article_ReplyDAO_Interface dao;
	////private Article_ReplyDAO_Interface dao =  new Article_ReplyDAO();
	
	public Article_ReplyService() {
		dao = new Article_ReplyDAO(); 
	}

	public Article_ReplyVO addArticle_Reply(Integer art_no,Integer mbr_no, String rep_cont, Timestamp rep_time,Integer rep_stat,Integer likes) {

		Article_ReplyVO article_ReplyVO = new Article_ReplyVO();
	
		System.out.println("hello2");
		article_ReplyVO.setArt_no(art_no);
		article_ReplyVO.setMbr_no(mbr_no);
		article_ReplyVO.setRep_cont(rep_cont);
		article_ReplyVO.setRep_time(rep_time);
		article_ReplyVO.setRep_stat(rep_stat);
		article_ReplyVO.setLikes(likes);
		dao.insert(article_ReplyVO);
		
		return article_ReplyVO;
	}

	public Article_ReplyVO updateArticle_Reply(Integer art_rep_no,Integer art_no,Integer mbr_no, String rep_cont, Timestamp rep_time,Integer rep_stat,Integer likes) {

		Article_ReplyVO article_ReplyVO = new Article_ReplyVO();

		article_ReplyVO.setArt_rep_no(art_rep_no);
		article_ReplyVO.setArt_no(art_no);
		article_ReplyVO.setMbr_no(mbr_no);
		article_ReplyVO.setRep_cont(rep_cont);
		article_ReplyVO.setRep_time(rep_time);
		article_ReplyVO.setRep_stat(rep_stat);
		article_ReplyVO.setLikes(likes);
		dao.update(article_ReplyVO);

		return article_ReplyVO;
	}

	public void deleteArticle_Reply(Integer art_rep_no) {
		dao.delete(art_rep_no);
	}
	
	
	public Article_ReplyVO hide(Integer art_rep_no) {

		Article_ReplyVO article_replyVO = new Article_ReplyVO();

		article_replyVO.setArt_rep_no(art_rep_no);
		dao.hide(article_replyVO);

		return article_replyVO;
	}
	
	public Article_ReplyVO getOneArticle_Reply(Integer art_rep_no) {
		return dao.findByPrimaryKey(art_rep_no);
	}
	
	public List<Article_ReplyVO> getOneArticle_Replies(Integer art_no) {
		return dao.findByArt_no(art_no);
	}

	public List<Article_ReplyVO> getAll() {
		return dao.getAll();
	}
}