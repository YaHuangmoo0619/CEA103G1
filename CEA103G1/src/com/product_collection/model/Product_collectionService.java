package com.product_collection.model;

import java.util.List;

public class Product_collectionService {

	private Product_collectionDAO_interface dao;

	public Product_collectionService() {
		dao = new Product_collectionDAO();
	}

	public Product_collectionVO addProduct_collection(Integer mbr_no, Integer prod_no) {

		Product_collectionVO product_collectionVO = new Product_collectionVO();

		product_collectionVO.setMbr_no(mbr_no);
		product_collectionVO.setProd_no(prod_no);
		dao.insert(product_collectionVO);

		return product_collectionVO;
	}

	public void deleteProduct_collection(Integer mbr_no, Integer prod_no) {
		dao.delete(mbr_no, prod_no);
	}

	public Product_collectionVO getOneProduct_collection(Integer mbr_no, Integer prod_no) {
		return dao.findByPrimaryKey(mbr_no, prod_no);
	}
	
	public List<Product_collectionVO> getByMbr_no(Integer mbr_no) {
		return dao.findByMbr_no(mbr_no);
	}
	
	public List<Product_collectionVO> getByProd_no(Integer prod_no) {
		return dao.findByProd_no(prod_no);
	}

	public List<Product_collectionVO> getAll() {
		return dao.getAll();
	}
	
}
