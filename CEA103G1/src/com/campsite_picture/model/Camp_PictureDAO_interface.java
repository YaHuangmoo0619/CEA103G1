package com.campsite_picture.model;

import java.util.List;
import java.util.Map;


public interface Camp_PictureDAO_interface {
	public void insert(Camp_PictureVO camp_pictureVO);

	public void delete(Integer camp_pic_no);

	public Camp_PictureVO findByPrimaryKey(Integer camp_pic_no);

	public List<Camp_PictureVO> getAll();

	public List<String> findByCamp(Integer camp_no);
}
