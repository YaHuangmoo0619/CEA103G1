package com.article.model;
import java.sql.Timestamp;
import java.util.List;


public class ArticleService {

	private ArticleDAO_Interface dao;
	////private ArticleDAO_Interface dao =  new ArticleDAO();
	
	public ArticleService() {
		dao = new ArticleDAO(); 
	}

	public ArticleVO addArticle(Integer bd_cl_no, Integer mbr_no,Timestamp art_rel_time, String art_title, String art_cont,Integer likes, Integer art_stat,Integer replies,String art_first_img) {

		ArticleVO articleVO = new ArticleVO();
	

		articleVO.setBd_cl_no(bd_cl_no);
		articleVO.setMbr_no(mbr_no);
		articleVO.setArt_rel_time(art_rel_time);
		articleVO.setArt_title(art_title);
		articleVO.setArt_cont(art_cont);
		articleVO.setLikes(likes);
		articleVO.setArt_stat(art_stat);
		articleVO.setReplies(replies);
		articleVO.setArt_first_img(art_first_img);
		dao.insert(articleVO);

		return articleVO;
	}

	public ArticleVO updateArticle(Integer art_no,Integer bd_cl_no, Integer mbr_no,Timestamp art_rel_time, String art_title, String art_cont,Integer likes, Integer art_stat,Integer replies,String art_first_img) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setArt_no(art_no);
		articleVO.setBd_cl_no(bd_cl_no);
		articleVO.setMbr_no(mbr_no);
		articleVO.setArt_rel_time(art_rel_time);
		articleVO.setArt_title(art_title);
		articleVO.setArt_cont(art_cont);
		articleVO.setLikes(likes);
		articleVO.setArt_stat(art_stat);
		articleVO.setReplies(replies);
		articleVO.setArt_first_img(art_first_img);
		dao.update(articleVO);

		return articleVO;
	}
	
	
	public ArticleVO plus_like(Integer art_no) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setArt_no(art_no);
		dao.plus_like(articleVO);

		return articleVO;
	}
	
	
	
	public ArticleVO minus_like(Integer art_no) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setArt_no(art_no);
		dao.minus_like(articleVO);

		return articleVO;
	}
	
	
	
	public ArticleVO plus_reply(Integer art_no) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setArt_no(art_no);
		dao.plus_reply(articleVO);

		return articleVO;
	}
	
	
	
	public ArticleVO minus_reply(Integer art_no) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setArt_no(art_no);
		dao.minus_reply(articleVO);

		return articleVO;
	}
	
	
	

	public void deleteArticle(Integer art_no) {
		dao.delete(art_no);
	}
	
	public ArticleVO hide(Integer art_no) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setArt_no(art_no);
		dao.hide(articleVO);

		return articleVO;
	}

	public ArticleVO getOneArticle(Integer art_no) {
		return dao.findByPrimaryKey(art_no);
	}
	
	public ArticleVO findLast() {
		return dao.findLast();
	}

	public List<ArticleVO> getAll_Front() {
		return dao.getAll_Front();
	}
	
	public List<ArticleVO> getAll_By_Likes() {
		return dao.getAll_Front_By_Likes();
	}
	
	public List<ArticleVO> getAll_Back() {
		return dao.getAll_Back();
	}
	
	public List<ArticleVO> getByBoard_Class_Front(Integer bd_cl_no){
		return dao.findByBd_cl_no_front(bd_cl_no);
	}
	
	public List<ArticleVO> getByBoard_Class_Back(Integer bd_cl_no){
		return dao.findByBd_cl_no_front(bd_cl_no);
	}
	
	public List<ArticleVO> getByMbr_no(Integer mbr_no){
		return dao.findByMbr_no(mbr_no);
	}
	
}


