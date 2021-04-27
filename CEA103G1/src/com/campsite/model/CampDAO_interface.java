package com.campsite.model;
import java.util.List;
import java.util.Map;


public interface CampDAO_interface {
	public void insert(CampVO campVO);

	public void update(CampVO campVO);

	public void delete(Integer camp_no);

	public CampVO findByPrimaryKey(Integer camp_no);

	public List<CampVO> getAll();

	public List<Integer> findByDist(Integer dist_no);
}
