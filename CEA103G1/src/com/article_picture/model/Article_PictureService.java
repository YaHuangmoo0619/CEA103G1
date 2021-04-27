package com.article_picture.model;


import java.util.List;



public class Article_PictureService {
	private Article_PictureDAO_Interface dao;
	
	public Article_PictureService() {
		dao = new Article_PictureDAO(); 
	}

	public Article_PictureVO addArticle_Picture(Integer art_no,byte[] art_pic) {

		Article_PictureVO article_PictureVO = new Article_PictureVO();
	


		article_PictureVO.setArt_no(art_no);
		article_PictureVO.setArt_pic(art_pic);

		dao.insert(article_PictureVO);

		return article_PictureVO;
	}

	public Article_PictureVO updateArticle_Picture(Integer art_pic_no,Integer art_no,byte[] art_pic) {

		Article_PictureVO article_PictureVO = new Article_PictureVO();

		article_PictureVO.setArt_pic_no(art_pic_no);
		article_PictureVO.setArt_no(art_no);
		article_PictureVO.setArt_pic(art_pic);
		dao.update(article_PictureVO);

		return article_PictureVO;
	}

	public void deleteArticle_Picture(Integer art_pic_no) {
		dao.delete(art_pic_no);
	}

	public Article_PictureVO getOneArticle_Report(Integer art_pic_no) {
		return dao.findByPrimaryKey(art_pic_no);
	}

	public List<Article_PictureVO> getAll() {
		return dao.getAll();
	}
}
