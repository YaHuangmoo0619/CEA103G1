package com.article_likes.model;
import java.util.List;



public class Article_LikesService {
	private Article_LikesDAO_Interface dao;
	
	public Article_LikesService() {
		dao = new Article_LikesDAO(); 
	}

	public Article_LikesVO addArticle_Likes(Integer mbr_no,Integer art_no) {

		Article_LikesVO article_LikesVO = new Article_LikesVO();
		article_LikesVO.setMbr_no(mbr_no);
		article_LikesVO.setArt_no(art_no);
		dao.insert(article_LikesVO);

		return article_LikesVO;
	}


	
	public List<Article_LikesVO> findbymbr_no(Integer mbr_no) {
		return dao.findByMbr_no(mbr_no);
	}
	
	public List<Article_LikesVO> findbyart_no(Integer art_no) {
		return dao.findByArt_no(art_no);
	}
	
	public void deleteLike(Integer mbr_no,Integer art_no) {
		dao.delete(mbr_no,art_no);
	}
}
