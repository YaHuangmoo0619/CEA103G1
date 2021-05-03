package com.product_comment.model;

import java.sql.Timestamp;
import java.util.List;

import com.product_comment.model.Product_commentVO;

public class Product_commentService {

	private Product_commentDAO_interface dao;

	public Product_commentService() {
		dao = new Product_commentDAO();
	}

	public Product_commentVO addProduct_comment(Integer prod_no, Integer mbr_no, String cmnt_cont, Timestamp cmnt_time, Integer cmnt_stat) {

		Product_commentVO product_commentVO = new Product_commentVO();

		product_commentVO.setProd_no(prod_no);
		product_commentVO.setMbr_no(mbr_no);
		product_commentVO.setCmnt_cont(cmnt_cont);
		product_commentVO.setCmnt_time(cmnt_time);
		product_commentVO.setCmnt_stat(cmnt_stat);
		dao.insert(product_commentVO);
		
		return product_commentVO;
	}
	
	public Product_commentVO updateProduct_comment(Integer prod_cmnt_no, Integer prod_no, Integer mbr_no, String cmnt_cont, Timestamp cmnt_time, Integer cmnt_stat) {

		Product_commentVO product_commentVO = new Product_commentVO();
		
		product_commentVO.setProd_cmnt_no(prod_cmnt_no);
		product_commentVO.setProd_no(prod_no);
		product_commentVO.setMbr_no(mbr_no);
		product_commentVO.setCmnt_cont(cmnt_cont);
		product_commentVO.setCmnt_time(cmnt_time);
		product_commentVO.setCmnt_stat(cmnt_stat);
		dao.update(product_commentVO);
		
		return product_commentVO;
	}

	public void deleteProduct_comment(Integer prod_cmnt_no) {
		dao.delete(prod_cmnt_no);
	}

	public Product_commentVO getOneProduct_comment(Integer prod_cmnt_no) {
		return dao.findByPrimaryKey(prod_cmnt_no);
	}

	public List<Product_commentVO> getAll() {
		return dao.getAll();
	}
	
	public List<Product_commentVO> getTimestampProd_no(Timestamp cmnt_time) {
		return dao.getTimestampProd_no(cmnt_time);
	}


}
