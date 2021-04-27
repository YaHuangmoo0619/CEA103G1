package com.article.model;
import java.sql.Timestamp;
import java.util.List;


public class ArticleService {

	private ArticleDAO_Interface dao;
	////private ArticleDAO_Interface dao =  new ArticleDAO();
	
	public ArticleService() {
		dao = new ArticleDAO(); 
	}

	public ArticleVO addArticle(Integer bd_cl_no, Integer mbr_no,Timestamp art_rel_time, String art_title, String art_cont,Integer likes, Integer art_stat) {

		ArticleVO articleVO = new ArticleVO();
	

		articleVO.setBd_cl_no(bd_cl_no);
		articleVO.setMbr_no(mbr_no);
		articleVO.setArt_rel_time(art_rel_time);
		articleVO.setArt_title(art_title);
		articleVO.setArt_cont(art_cont);
		articleVO.setLikes(likes);
		articleVO.setArt_stat(art_stat);
		dao.insert(articleVO);

		return articleVO;
	}

	public ArticleVO updateArticle(Integer art_no,Integer bd_cl_no, Integer mbr_no,Timestamp art_rel_time, String art_title, String art_cont,Integer likes, Integer art_stat) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setArt_no(art_no);
		articleVO.setBd_cl_no(bd_cl_no);
		articleVO.setMbr_no(mbr_no);
		articleVO.setArt_rel_time(art_rel_time);
		articleVO.setArt_title(art_title);
		articleVO.setArt_cont(art_cont);
		articleVO.setLikes(likes);
		articleVO.setArt_stat(art_stat);
		dao.update(articleVO);

		return articleVO;
	}

	public void deleteArticle(Integer art_no) {
		dao.delete(art_no);
	}

	public ArticleVO getOneArticle(Integer art_no) {
		return dao.findByPrimaryKey(art_no);
	}

	public List<ArticleVO> getAll() {
		return dao.getAll();
	}
	
	public List<ArticleVO> getByBoard_Class(Integer bd_cl_no){
		return dao.findByBd_cl_no(bd_cl_no);
	}
}


