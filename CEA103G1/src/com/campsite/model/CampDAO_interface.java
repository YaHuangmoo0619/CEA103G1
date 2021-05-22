package com.campsite.model;
import java.util.List;
import java.util.Map;

import com.campsite_feature.model.Camp_FeatureVO;
import com.campsite_picture.model.Camp_PictureVO;
import com.place.model.PlaceVO;


public interface CampDAO_interface {
	public void insert(CampVO campVO, List<Camp_FeatureVO> camp_featurelist, List<PlaceVO> placelist, List<Camp_PictureVO> camp_picturelist);

	public void update(CampVO campVO);

	public void delete(Integer camp_no);

	public CampVO findByPrimaryKey(Integer camp_no);

	public List<CampVO> getAll();

	public List<Integer> findByDist(Integer dist_no);

	public void update2(CampVO campVO);

	public void update3(CampVO campVO);
}
