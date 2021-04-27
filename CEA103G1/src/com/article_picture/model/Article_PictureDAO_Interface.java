package com.article_picture.model;
import java.util.List;
import java.util.Map;
public interface Article_PictureDAO_Interface {
	public void insert(Article_PictureVO article_PictureVO);

	public void update(Article_PictureVO article_PictureVO);

	public void delete(Integer art_pic_no);

	public Article_PictureVO findByPrimaryKey(Integer art_pic_no);
	
	public List<Article_PictureVO> findByArt_no(Integer art_no);

	public List<Article_PictureVO> getAll();
}
