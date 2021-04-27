package com.promotion_product.model;

import java.util.List;

public class Promotion_productService {

	private Promotion_productDAO_interface dao;

	public Promotion_productService() {
		dao = new Promotion_productDAO();
	}

	public Promotion_productVO addPromotion_product(Integer promo_prj_no, Integer prod_no, Integer prod_pc) {

		Promotion_productVO promotion_productVO = new Promotion_productVO();

		promotion_productVO.setPromo_prj_no(promo_prj_no);
		promotion_productVO.setProd_no(prod_no);
		promotion_productVO.setProd_pc(prod_pc);
		dao.insert(promotion_productVO);

		return promotion_productVO;
	}

	public Promotion_productVO updatePromotion_product(Integer promo_prj_no, Integer prod_no, Integer prod_pc) {

		Promotion_productVO promotion_productVO = new Promotion_productVO();

		promotion_productVO.setPromo_prj_no(promo_prj_no);
		promotion_productVO.setProd_no(prod_no);
		promotion_productVO.setProd_pc(prod_pc);
		dao.update(promotion_productVO);

		return promotion_productVO;
	}

	public void deletePromotion_product(Integer promo_prj_no, Integer prod_no) {
		dao.delete(promo_prj_no, prod_no);
	}

	public Promotion_productVO getOnePromotion_product(Integer promo_prj_no, Integer prod_no) {
		return dao.findByPrimaryKey(promo_prj_no, prod_no);
	}

	public List<Promotion_productVO> getByProd_prj_no(Integer promo_prj_no) {
		return dao.findByProd_prj_no(promo_prj_no);
	}
	
	public List<Promotion_productVO> getByProd_no(Integer prod_no) {
		return dao.findByProd_no(prod_no);
	}
	
	public List<Promotion_productVO> getAll() {
		return dao.getAll();
	}
	
}