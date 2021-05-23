package com.place.model;

import java.sql.Connection;
import java.util.List;
import java.util.Map;


public interface PlaceDAO_interface {
	public void insert(PlaceVO placeVO);

	public void updateStat(Integer plc_no, Integer open_stat);

	public PlaceVO findByPrimaryKey(Integer plc_no);

	public List<PlaceVO> getAll();

	public List<PlaceVO> findByCampno(Integer camp_no);

	void insert2(PlaceVO placeVO, Connection con);
}
