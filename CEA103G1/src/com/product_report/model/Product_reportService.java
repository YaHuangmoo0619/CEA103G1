package com.product_report.model;

import java.sql.Timestamp;
import java.util.List;

public class Product_reportService {

	private Product_reportDAO_interface dao;

	public Product_reportService() {
		dao = new Product_reportDAO();
	}

	public Product_reportVO addProduct_report (Integer mbr_no, Integer prod_no, String rpt_cont, Timestamp rpt_time,
			Integer proc_stat) {

		Product_reportVO product_reportVO = new Product_reportVO();

		product_reportVO.setMbr_no(mbr_no);
		product_reportVO.setProd_no(prod_no);
		product_reportVO.setRpt_cont(rpt_cont);
		product_reportVO.setRpt_time(rpt_time);
		product_reportVO.setProc_stat(proc_stat);
		dao.insert(product_reportVO);

		return product_reportVO;
	}

	public Product_reportVO updateProduct_report (Integer prod_rpt_no, Integer mbr_no, Integer prod_no, String rpt_cont, Timestamp rpt_time,
			Integer proc_stat) {

		Product_reportVO product_reportVO = new Product_reportVO();

		product_reportVO.setProd_rpt_no(prod_rpt_no);
		product_reportVO.setMbr_no(mbr_no);
		product_reportVO.setProd_no(prod_no);
		product_reportVO.setRpt_cont(rpt_cont);
		product_reportVO.setRpt_time(rpt_time);
		product_reportVO.setProc_stat(proc_stat);
		dao.update(product_reportVO);

		return product_reportVO;
	}

	public void deleteProduct_report(Integer prod_rpt_no) {
		dao.delete(prod_rpt_no);
	}

	public Product_reportVO getOneProduct_report(Integer prod_rpt_no) {
		return dao.findByPrimaryKey(prod_rpt_no);
	}

	public List<Product_reportVO> getAll() {
		return dao.getAll();
	}
}