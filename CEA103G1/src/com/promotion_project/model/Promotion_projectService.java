package com.promotion_project.model;

import java.sql.Date;
import java.util.List;

public class Promotion_projectService {

	private Promotion_projectDAO_interface dao;

	public Promotion_projectService() {
		dao = new Promotion_projectDAO();
	}

	public Promotion_projectVO addPromotion_project(String promo_prj_name, String promo_copy, String promo_cnt,
			Date promo_st_date, Date promo_end_date) {

		Promotion_projectVO promotion_projectVO = new Promotion_projectVO();

		promotion_projectVO.setPromo_prj_name(promo_prj_name);
		promotion_projectVO.setPromo_copy(promo_copy);
		promotion_projectVO.setPromo_cnt(promo_cnt);
		promotion_projectVO.setPromo_st_date(promo_st_date);
		promotion_projectVO.setPromo_end_date(promo_end_date);
		dao.insert(promotion_projectVO);

		return promotion_projectVO;
	}

	public Promotion_projectVO updatePromotion_project(Integer promo_prj_no, String promo_prj_name, String promo_copy, String promo_cnt,
			Date promo_st_date, Date promo_end_date) {

		Promotion_projectVO promotion_projectVO = new Promotion_projectVO();

		promotion_projectVO.setPromo_prj_no(promo_prj_no);
		promotion_projectVO.setPromo_prj_name(promo_prj_name);
		promotion_projectVO.setPromo_copy(promo_copy);
		promotion_projectVO.setPromo_cnt(promo_cnt);
		promotion_projectVO.setPromo_st_date(promo_st_date);
		promotion_projectVO.setPromo_end_date(promo_end_date);
		dao.update(promotion_projectVO);

		return promotion_projectVO;
	}

	public void deletePromotion_project(Integer promo_prj_no) {
		dao.delete(promo_prj_no);
	}

	public Promotion_projectVO getOnePromotion_project(Integer promo_prj_no) {
		return dao.findByPrimaryKey(promo_prj_no);
	}

	public List<Promotion_projectVO> getAll() {
		return dao.getAll();
	}
}