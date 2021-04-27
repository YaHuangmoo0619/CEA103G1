package com.product_comment_report.model;

import java.sql.Timestamp;
import java.util.List;

public class Product_comment_reportService {

	private Product_comment_reportDAO_interface dao;

	public Product_comment_reportService() {
		dao = new Product_comment_reportDAO();
	}

	public Product_comment_reportVO addProduct_comment_report(Integer mbr_no, Integer prod_no, String cmnt_cont, Timestamp cmnt_time, Integer cmnt_stat) {

		Product_comment_reportVO product_comment_reportVO = new Product_comment_reportVO();

		product_comment_reportVO.setMbr_no(mbr_no);
		product_comment_reportVO.setProd_no(prod_no);
		product_comment_reportVO.setCmnt_cont(cmnt_cont);
		product_comment_reportVO.setCmnt_time(cmnt_time);
		product_comment_reportVO.setCmnt_stat(cmnt_stat);
		dao.insert(product_comment_reportVO);
		
		return product_comment_reportVO;
	}
	
	public Product_comment_reportVO updateProduct_comment_report(Integer prod_cmnt_rpt_no, Integer mbr_no, Integer prod_no, String cmnt_cont, Timestamp cmnt_time, Integer cmnt_stat) {

		Product_comment_reportVO product_comment_reportVO = new Product_comment_reportVO();
		
		product_comment_reportVO.setProd_cmnt_rpt_no(prod_cmnt_rpt_no);
		product_comment_reportVO.setMbr_no(mbr_no);
		product_comment_reportVO.setProd_no(prod_no);
		product_comment_reportVO.setCmnt_cont(cmnt_cont);
		product_comment_reportVO.setCmnt_time(cmnt_time);
		product_comment_reportVO.setCmnt_stat(cmnt_stat);
		dao.update(product_comment_reportVO);
		
		return product_comment_reportVO;
	}

	public void deleteProduct_comment_report(Integer prod_cmnt_rpt_no) {
		dao.delete(prod_cmnt_rpt_no);
	}

	public Product_comment_reportVO getOneProduct_comment_report(Integer prod_cmnt_rpt_no) {
		return dao.findByPrimaryKey(prod_cmnt_rpt_no);
	}

	public List<Product_comment_reportVO> getAll() {
		return dao.getAll();
	}
}
