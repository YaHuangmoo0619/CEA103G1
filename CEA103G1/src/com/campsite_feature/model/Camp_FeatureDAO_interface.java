package com.campsite_feature.model;

import java.sql.Connection;
import java.util.List;

public interface Camp_FeatureDAO_interface {
	public void insert(Camp_FeatureVO camp_featureVO, Connection con);

	public void update(List<Camp_FeatureVO> camp_featurelist);

	public void delete(Integer camp_no, Connection con);

	public Camp_FeatureVO findByPrimaryKey(Integer camp_fl_no, Integer camp_no);

	public List<Camp_FeatureVO> getAll();

	void insert2(Camp_FeatureVO aCamp_Feature, Connection con);

	
}
