package com.shopping_cart.model;

import java.util.List;

public class Shopping_cartService {

	private Shopping_cartDAO_interface dao;

	public Shopping_cartService() {
		dao = new Shopping_cartDAO();
	}

	public Shopping_cartVO addShopping_cart(Integer mbr_no, Integer prod_no, Integer prod_amt) {

		Shopping_cartVO shopping_cartVO = new Shopping_cartVO();

		shopping_cartVO.setMbr_no(mbr_no);
		shopping_cartVO.setProd_no(prod_no);
		shopping_cartVO.setProd_amt(prod_amt);
		dao.insert(shopping_cartVO);

		return shopping_cartVO;
	}
	
	public Shopping_cartVO updateShopping_cart(Integer mbr_no, Integer prod_no, Integer prod_amt) {

		Shopping_cartVO shopping_cartVO = new Shopping_cartVO();

		shopping_cartVO.setMbr_no(mbr_no);
		shopping_cartVO.setProd_no(prod_no);
		shopping_cartVO.setProd_amt(prod_amt);
		dao.update(shopping_cartVO);

		return shopping_cartVO;
	}

	public void deleteShopping_cart(Integer mbr_no, Integer prod_no) {
		dao.delete(mbr_no, prod_no);
	}

	public Shopping_cartVO getOneShopping_cart(Integer mbr_no, Integer prod_no) {
		return dao.findByPrimaryKey(mbr_no, prod_no);
	}
	
	public List<Shopping_cartVO> getByMbr_no(Integer mbr_no) {
		return dao.findByMbr_no(mbr_no);
	}
	
	public List<Shopping_cartVO> getAll() {
		return dao.getAll();
	}
	
}
