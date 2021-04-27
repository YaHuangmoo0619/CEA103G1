package com.camp_feature.model;

import java.util.List;
import java.util.Map;


public interface Camp_FeatureDAO_interface {
	public void insert(Camp_FeatureVO camp_featureVO);

	public void update(Camp_FeatureVO camp_featureVO);

	public void delete(Integer camp_fl_no, Integer camp_no);

	public Camp_FeatureVO findByPrimaryKey(Integer camp_fl_no, Integer camp_no);

	public List<Camp_FeatureVO> getAll();
}
