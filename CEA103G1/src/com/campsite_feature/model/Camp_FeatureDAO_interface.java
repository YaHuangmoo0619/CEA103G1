package com.campsite_feature.model;

import java.sql.Connection;
import java.util.List;

public interface Camp_FeatureDAO_interface {
	public void insert(Camp_FeatureVO camp_featureVO);

	public void update(Camp_FeatureVO camp_featureVO);

	public void delete(Integer camp_fl_no, Integer camp_no);

	public Camp_FeatureVO findByPrimaryKey(Integer camp_fl_no, Integer camp_no);

	public List<Camp_FeatureVO> getAll();

	void insert2(Camp_FeatureVO aCamp_Feature, Connection con);
}
