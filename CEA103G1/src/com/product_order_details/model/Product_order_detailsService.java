package com.product_order_details.model;

import java.util.List;

public class Product_order_detailsService {

	private Product_order_detailsDAO_interface dao;

	public Product_order_detailsService() {
		dao = new Product_order_detailsDAO();
	}

	public Product_order_detailsVO addProduct_order_details
		(Integer prod_ord_no, Integer prod_no, Integer prod_amt, Integer prod_unit_pc) {

		Product_order_detailsVO product_order_detailsVO = new Product_order_detailsVO();

		product_order_detailsVO.setProd_ord_no(prod_ord_no);
		product_order_detailsVO.setProd_no(prod_no);
		product_order_detailsVO.setProd_amt(prod_amt);
		product_order_detailsVO.setProd_unit_pc(prod_unit_pc);
		dao.insert(product_order_detailsVO);

		return product_order_detailsVO;
	}

	public Product_order_detailsVO updateProduct_order_details 
		(Integer prod_ord_no, Integer prod_no, Integer prod_amt, Integer prod_unit_pc) {

		Product_order_detailsVO product_order_detailsVO = new Product_order_detailsVO();

		product_order_detailsVO.setProd_ord_no(prod_ord_no);
		product_order_detailsVO.setProd_no(prod_no);
		product_order_detailsVO.setProd_amt(prod_amt);
		product_order_detailsVO.setProd_unit_pc(prod_unit_pc);
		dao.update(product_order_detailsVO);

		return product_order_detailsVO;
	}

	public void deleteProduct_order_details(Integer prod_ord_no, Integer prod_no) {
		dao.delete(prod_ord_no, prod_no);
	}

	public Product_order_detailsVO getOneProduct_order_details(Integer prod_ord_no, Integer prod_no) {
		return dao.findByPrimaryKey(prod_ord_no, prod_no);
	}

	public List<Product_order_detailsVO> getByProd_ord_no(Integer prod_ord_no) {
		return dao.findByProd_ord_no(prod_ord_no);
	}
	
//	public List<Product_order_detailsVO> getByProd_no(Integer prod_no) {
//		return dao.findByProd_no(prod_no);
//	}
	
	public List<Product_order_detailsVO> getAll() {
		return dao.getAll();
	}
		
}