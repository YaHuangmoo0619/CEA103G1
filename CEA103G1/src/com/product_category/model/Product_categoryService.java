package com.product_category.model;

import java.util.List;

public class Product_categoryService {

	private Product_categoryDAO_interface dao;

	public Product_categoryService() {
		dao = new Product_categoryDAO();
	}

	public Product_categoryVO addProduct_category(String prod_cat_name) {

		Product_categoryVO product_categoryVO = new Product_categoryVO();

		product_categoryVO.setProd_cat_name(prod_cat_name);
		dao.insert(product_categoryVO);

		return product_categoryVO;
	}

	public Product_categoryVO updateProduct_category(Integer prod_cat_no, String prod_cat_name) {

		Product_categoryVO product_categoryVO = new Product_categoryVO();

		product_categoryVO.setProd_cat_no(prod_cat_no);
		product_categoryVO.setProd_cat_name(prod_cat_name);
		dao.update(product_categoryVO);

		return product_categoryVO;
	}

	public void deleteProduct_category(Integer prod_cat_no) {
		dao.delete(prod_cat_no);
	}

	public Product_categoryVO getOneProduct_category(Integer prod_cat_no) {
		return dao.findByPrimaryKey(prod_cat_no);
	}

	public List<Product_categoryVO> getAll() {
		return dao.getAll();
	}
}
