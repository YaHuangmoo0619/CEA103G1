package com.place.model;

import java.sql.Connection;
import java.util.List;
import java.util.Map;


public interface PlaceDAO_interface {
	public void insert(PlaceVO placeVO, Connection con);

	public void update(List<PlaceVO> placelist);

	public void delete(Integer camp_no, Connection con);

	public PlaceVO findByPrimaryKey(Integer plc_no);

	public List<PlaceVO> getAll();

	public List<PlaceVO> findByCampno(Integer camp_no);

	void insert2(PlaceVO placeVO, Connection con);
}
