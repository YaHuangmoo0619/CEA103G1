package com.product.model;

import java.util.List;
import java.util.Map;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductDAO();
	}

	public ProductVO addProduct(Integer prod_cat_no, Integer prod_stat, String prod_name, Integer prod_pc,
			Integer prod_stg, String prod_info, String prod_bnd, String prod_clr, String prod_size, Integer ship_meth) {

		ProductVO productVO = new ProductVO();

		productVO.setProd_cat_no(prod_cat_no);
		productVO.setProd_stat(prod_stat);
		productVO.setProd_name(prod_name);
		productVO.setProd_pc(prod_pc);
		productVO.setProd_stg(prod_stg);
		productVO.setProd_info(prod_info);
		productVO.setProd_bnd(prod_bnd);
		productVO.setProd_clr(prod_clr);
		productVO.setProd_size(prod_size);
		productVO.setShip_meth(ship_meth);
		
		dao.insert(productVO);

		return productVO;
	}

	public ProductVO updateProduct(Integer prod_no, Integer prod_cat_no, Integer prod_stat, String prod_name, Integer prod_pc,
			Integer prod_stg, String prod_info, String prod_bnd, String prod_clr, String prod_size, Integer ship_meth) {

		ProductVO productVO = new ProductVO();

		productVO.setProd_cat_no(prod_cat_no);
		productVO.setProd_stat(prod_stat);
		productVO.setProd_name(prod_name);
		productVO.setProd_pc(prod_pc);
		productVO.setProd_stg(prod_stg);
		productVO.setProd_info(prod_info);
		productVO.setProd_bnd(prod_bnd);
		productVO.setProd_clr(prod_clr);
		productVO.setProd_size(prod_size);
		productVO.setShip_meth(ship_meth);
		dao.update(productVO);

		return productVO;
	}

	public void deleteProduct(Integer prod_no) {
		dao.delete(prod_no);
	}

	public ProductVO getOneProduct(Integer prod_no) {
		return dao.findByPrimaryKey(prod_no);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
		
	public List<ProductVO> getShop() {
		return dao.getShop();
	}
	
	public List<ProductVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	//雅凰加的
	public void updateUpOrDown(Integer prod_stat, Integer prod_no) {
		dao.updateUpOrDown(prod_stat, prod_no);
	}
	
	public List<ProductVO> getAllStat() {
		return dao.getAllStat();
	}
	//雅凰加的
}

