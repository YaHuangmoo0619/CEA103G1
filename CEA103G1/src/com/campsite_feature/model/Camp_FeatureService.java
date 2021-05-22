package com.campsite_feature.model;

import java.sql.Connection;
import java.util.List;

public class Camp_FeatureService {
	private Camp_FeatureDAO_interface dao;

	public Camp_FeatureService() {
		dao = new Camp_FeatureDAO();
	}

	public Camp_FeatureVO addCamp_Feature(Integer camp_fl_no, Integer camp_no, Connection con) {

		Camp_FeatureVO camp_featureVO = new Camp_FeatureVO();
		camp_featureVO.setCamp_no(camp_no);
		camp_featureVO.setCamp_fl_no(camp_fl_no);
		dao.insert(camp_featureVO, con);

		return camp_featureVO;
	}

	public List<Camp_FeatureVO> updateCamp_Feature(List<Camp_FeatureVO> camp_featurelist) {
		dao.update(camp_featurelist);
		return camp_featurelist;
	}

	public void deleteCamp_Feature(Integer camp_no, Connection con) {
		dao.delete(camp_no, con);
	}

	public Camp_FeatureVO getOneCamp_Feature(Integer camp_fl_no, Integer camp_no) {
		return dao.findByPrimaryKey(camp_fl_no, camp_no);
	}

	public List<Camp_FeatureVO> getAll() {
		return dao.getAll();
	}
}
