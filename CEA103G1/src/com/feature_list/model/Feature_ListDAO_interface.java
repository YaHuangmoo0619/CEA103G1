package com.feature_list.model;

import java.util.List;
import java.util.Map;


public interface Feature_ListDAO_interface {
	public void insert(Feature_ListVO feature_listVO);

	public void update(Feature_ListVO feature_listVO);

	public void delete(Integer camp_fl_no);

	public List<Feature_ListVO> findByName(String camp_fl_name);

	public Feature_ListVO findByPrimaryKey(Integer camp_fl_no);

	public List<Feature_ListVO> getAll();

}
