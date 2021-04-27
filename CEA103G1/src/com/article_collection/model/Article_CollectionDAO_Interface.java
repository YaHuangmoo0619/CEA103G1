package com.article_collection.model;
import java.util.List;
import java.util.Map;

public interface Article_CollectionDAO_Interface {
	public void insert(Article_CollectionVO article_CollectionVO); //�[�J����

	public void delete(Integer mbr_no,Integer art_no); //��������

	//public List<Article_CollectionVO> getAll();//�����`��A�Pı�ä��ݭn

	public List<Article_CollectionVO> findByMbr_no(Integer mbrno); //���o�Y�ӤH���ê��峹

	public List<Article_CollectionVO> findByArt_no(Integer Art_no); //���o���ìY�峹���Ҧ��H
}
