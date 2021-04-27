package com.advertising_application.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Advertising_applicationService {

	private Advertising_applicationDAO_interface dao;

	public Advertising_applicationService() {
		dao = new Advertising_applicationDAO();
	}

	public Advertising_applicationVO addAdvertising_application(Integer cso_no, String ad_head, String ad_cont, Byte ad_pic, Timestamp app_time, Timestamp rev_time, Integer ad_stat, Date ad_st_date, Date ad_end_date) {

		Advertising_applicationVO advertising_applicationVO = new Advertising_applicationVO();

		advertising_applicationVO.setCso_no(cso_no);
		advertising_applicationVO.setAd_head(ad_head);
		advertising_applicationVO.setAd_cont(ad_cont);
		advertising_applicationVO.setAd_pic(ad_pic);
		advertising_applicationVO.setApp_time(app_time);
		advertising_applicationVO.setRev_time(rev_time);
		advertising_applicationVO.setAd_stat(ad_stat);
		advertising_applicationVO.setAd_st_date(ad_st_date);
		advertising_applicationVO.setAd_end_date(ad_end_date);
		dao.insert(advertising_applicationVO);
		
		return advertising_applicationVO;
	}
	
	public Advertising_applicationVO updateAdvertising_application(Integer ad_no, Integer cso_no, String ad_head, String ad_cont, Byte ad_pic, Timestamp app_time, Timestamp rev_time, Integer ad_stat, Date ad_st_date, Date ad_end_date) {

		Advertising_applicationVO advertising_applicationVO = new Advertising_applicationVO();
		
		advertising_applicationVO.setCso_no(ad_no);
		advertising_applicationVO.setCso_no(cso_no);
		advertising_applicationVO.setAd_head(ad_head);
		advertising_applicationVO.setAd_cont(ad_cont);
		advertising_applicationVO.setAd_pic(ad_pic);
		advertising_applicationVO.setApp_time(app_time);
		advertising_applicationVO.setRev_time(rev_time);
		advertising_applicationVO.setAd_stat(ad_stat);
		advertising_applicationVO.setAd_st_date(ad_st_date);
		advertising_applicationVO.setAd_end_date(ad_end_date);
		dao.update(advertising_applicationVO);
		
		return advertising_applicationVO;
	}

	public void deleteAdvertising_application(Integer ad_no) {
		dao.delete(ad_no);
	}

	public Advertising_applicationVO getOneAdvertising_application(Integer ad_no) {
		return dao.findByPrimaryKey(ad_no);
	}

	public List<Advertising_applicationVO> getAll() {
		return dao.getAll();
	}
}
