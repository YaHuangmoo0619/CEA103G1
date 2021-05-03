package com.product_comment_report.model;

import java.sql.Timestamp;
import java.util.List;

public class Product_comment_reportService {

	private Product_comment_reportDAO_interface dao;

	public Product_comment_reportService() {
		dao = new Product_comment_reportDAO();
	}

	public Product_comment_reportVO addProduct_comment_report(Integer mbr_no, Integer prod_cmnt_no, String rpt_cont, Timestamp rpt_time, Integer proc_stat) {

		Product_comment_reportVO product_comment_reportVO = new Product_comment_reportVO();

		product_comment_reportVO.setMbr_no(mbr_no);
		product_comment_reportVO.setProd_cmnt_no(prod_cmnt_no);
		product_comment_reportVO.setRpt_cont(rpt_cont);
		product_comment_reportVO.setRpt_time(rpt_time);
		product_comment_reportVO.setProc_stat(proc_stat);
		dao.insert(product_comment_reportVO);
		
		return product_comment_reportVO;
	}
	
	public Product_comment_reportVO updateProduct_comment_report(Integer prod_cmnt_rpt_no, Integer mbr_no, Integer prod_cmnt_no, String rpt_cont, Timestamp rpt_time, Integer proc_stat) {

		Product_comment_reportVO product_comment_reportVO = new Product_comment_reportVO();
		
		product_comment_reportVO.setProd_cmnt_rpt_no(prod_cmnt_rpt_no);
		product_comment_reportVO.setMbr_no(mbr_no);
		product_comment_reportVO.setProd_cmnt_no(prod_cmnt_no);
		product_comment_reportVO.setRpt_cont(rpt_cont);
		product_comment_reportVO.setRpt_time(rpt_time);
		product_comment_reportVO.setProc_stat(proc_stat);
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

	public List<Product_comment_reportVO> getTimestampProd_cmnt_no(Timestamp rpt_time) {
		return dao.getTimestampProd_cmnt_no(rpt_time);
	}


}
