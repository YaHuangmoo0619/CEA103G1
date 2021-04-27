package com.article_collection.model;

import java.util.List;

public class Article_CollectionService {
private Article_CollectionDAO_Interface dao;
	
	public Article_CollectionService() {
		dao = new Article_CollectionDAO(); 
	}

	public Article_CollectionVO addArticle_Collection(Integer mbr_no,Integer art_no) {

		Article_CollectionVO article_CollectionVO = new Article_CollectionVO();
		article_CollectionVO.setMbr_no(mbr_no);
		article_CollectionVO.setArt_no(art_no);
		dao.insert(article_CollectionVO);

		return article_CollectionVO;
	}


	public void deleteArticle_Collection(Integer mbr_no,Integer art_no) {
		dao.delete(mbr_no,art_no);
	}

//	public List<Article_CollectionVO> getAll() {
//		return dao.getAll();
//	}
	
	public List<Article_CollectionVO> findbymbr_no(Integer mbr_no) {
		return dao.findByMbr_no(mbr_no);
	}
	
	public List<Article_CollectionVO> findbyart_no(Integer art_no) {
		return dao.findByArt_no(art_no);
	}
}
