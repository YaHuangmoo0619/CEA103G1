package com.article_likes.model;
import java.util.List;

public interface Article_LikesDAO_Interface {
	public void insert(Article_LikesVO Article_LikesVO); //���g

	//public void update(Article_LikesVO Article_LikesVO);//�Pı���ݭn ������@
 
	public void delete(Integer mbr_no,Integer art_no); //���^�g

	//public Article_LikesVO findByPrimaryKey(Integer mbr_no,Integer art_no); //�Pı���ݭn ������@

	//public List<Article_LikesVO> getAll(); //���g�`�� //�Pı���ݭn ������@

	public List<Article_LikesVO> findByMbr_no(Integer mbr_no); //�o�ӤH���F���Ǥ峹���g

	public List<Article_LikesVO> findByArt_no(Integer art_no); //�o�g�峹�Q���ǤH���g
}
