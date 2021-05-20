package com.product_picture.model;

import com.campsite_picture.model.Camp_PictureDAO;
import com.campsite_picture.model.Camp_PictureDAO_interface;
import com.campsite_picture.model.Camp_PictureVO;

public class Product_pictureService {

	private Product_pictureDAO_interface dao;

	public Product_pictureService() {
		dao = new Product_pictureDAO();
	}
	
	public Product_pictureVO getOneProduct_picture(Integer prod_pic_no) {
		return dao.findByPrimaryKey(prod_pic_no);
	}
}
