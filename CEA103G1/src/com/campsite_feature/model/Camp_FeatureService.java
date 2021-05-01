package com.campsite_feature.model;

import java.util.List;

public class Camp_FeatureService {
	private Camp_FeatureDAO_interface dao;

	public Camp_FeatureService() {
		dao = new Camp_FeatureDAO();
	}

	public Camp_FeatureVO addCamp_Feature(Integer camp_fl_no, Integer camp_no) {

		Camp_FeatureVO camp_collectionVO = new Camp_FeatureVO();
		camp_collectionVO.setCamp_no(camp_no);
		camp_collectionVO.setCamp_fl_no(camp_fl_no);
		dao.insert(camp_collectionVO);

		return camp_collectionVO;
	}

	public Camp_FeatureVO updateCamp_Feature(Integer camp_fl_no, Integer camp_no) {

		Camp_FeatureVO camp_collectionVO = new Camp_FeatureVO();
		camp_collectionVO.setCamp_no(camp_no);
		camp_collectionVO.setCamp_fl_no(camp_fl_no);
		dao.update(camp_collectionVO);

		return camp_collectionVO;
	}

	public void deleteCamp_Feature(Integer camp_fl_no, Integer camp_no) {
		dao.delete(camp_fl_no, camp_no);
	}

	public Camp_FeatureVO getOneCamp_Feature(Integer camp_fl_no, Integer camp_no) {
		return dao.findByPrimaryKey(camp_fl_no, camp_no);
	}

	public List<Camp_FeatureVO> getAll() {
		return dao.getAll();
	}
}
