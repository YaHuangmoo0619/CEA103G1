package com.campsite.model;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import com.campsite_feature.model.Camp_FeatureVO;
import com.place.model.PlaceVO;

public class CampService {
	private CampDAO_interface dao;

	public CampService() {
		dao = new CampDAO();
	}

	public CampVO addCamp(Integer cso_no, Integer dist_no, String camp_name, String campInfo, String note,
			byte[] config, String height, String wireless, Integer pet, String facility, Integer operate_Date,
			String park, String address, Double latitude, Double longitude, List<Camp_FeatureVO> camp_featurelist, List<PlaceVO> placelist) {

		CampVO campVO = new CampVO();
		campVO.setCso_no(cso_no);
		campVO.setDist_no(dist_no);
		campVO.setCamp_name(camp_name);
		campVO.setCampInfo(campInfo);
		campVO.setNote(note);
		campVO.setConfig(config);
		campVO.setHeight(height);
		campVO.setWireless(wireless);
		campVO.setPet(pet);
		campVO.setFacility(facility);
		campVO.setOperate_Date(operate_Date);
		campVO.setPark(park);
		campVO.setAddress(address);
		campVO.setLongitude(longitude);
		campVO.setLatitude(latitude);
System.out.println("²Ä¤@¯¸");
		dao.insert(campVO, camp_featurelist, placelist);
		return campVO;
	}

	public CampVO updateCamp(Integer dist_no, String camp_name, String campInfo, String note,
			byte[] config, String height, String wireless, Integer pet, String facility, Integer operate_Date,
			String park, String address, Double latitude, Double longitude, Integer camp_no) {

		CampVO campVO = new CampVO();
		
		campVO.setDist_no(dist_no);
        campVO.setCamp_no(camp_no);
		campVO.setCamp_name(camp_name);
		campVO.setCampInfo(campInfo);
		campVO.setNote(note);
		campVO.setConfig(config);
		campVO.setHeight(height);
		campVO.setWireless(wireless);
		campVO.setPet(pet);
		campVO.setFacility(facility);
		campVO.setOperate_Date(operate_Date);
		campVO.setPark(park);
		campVO.setAddress(address);
		campVO.setLongitude(longitude);
		campVO.setLatitude(latitude);
		dao.update(campVO);
		return campVO;
	}

	public void deleteCamp(Integer camp_no) {
		dao.delete(camp_no);
	}

	public CampVO getOneCamp(Integer camp_no) {
		return dao.findByPrimaryKey(camp_no);
	}
    public List<Integer> getByDist(Integer dist_no) {
    	return dao.findByDist(dist_no);
    }
	public List<CampVO> getAll() {
		return dao.getAll();
	}
}
