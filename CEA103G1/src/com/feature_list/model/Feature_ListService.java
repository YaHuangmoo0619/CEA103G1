package com.feature_list.model;

import java.util.List;

public class Feature_ListService {

	private Feature_ListDAO_interface dao;

	public Feature_ListService() {
		dao = new Feature_ListDAO();
	}

	public Feature_ListVO addFeature_List(String camp_fl_name) {

		Feature_ListVO feature_listVO = new Feature_ListVO();

		feature_listVO.setCamp_fl_name(camp_fl_name);
		dao.insert(feature_listVO);

		return feature_listVO;
	}

	public Feature_ListVO updateFeature_List(Integer camp_fl_no, String camp_fl_name) {

		Feature_ListVO feature_listVO = new Feature_ListVO();

		feature_listVO.setCamp_fl_no(camp_fl_no);
		feature_listVO.setCamp_fl_name(camp_fl_name);
		dao.update(feature_listVO);

		return feature_listVO;
	}

	public void deleteFeature_List(Integer camp_fl_no) {
		dao.delete(camp_fl_no);
	}

	public List<Feature_ListVO> getOneFeature_List(String camp_fl_name) {
		return dao.findByName(camp_fl_name);
	}
	public Feature_ListVO getOneFeature_List(Integer camp_fl_no) {
		return dao.findByPrimaryKey(camp_fl_no);
	}

	public List<Feature_ListVO> getAll() {
		return dao.getAll();
	}
}
