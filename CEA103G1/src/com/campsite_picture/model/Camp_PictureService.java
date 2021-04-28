package com.campsite_picture.model;

import java.util.List;

public class Camp_PictureService {
	private Camp_PictureDAO_interface dao;

	public Camp_PictureService() {
		dao = new Camp_PictureDAO();
	}

	public Camp_PictureVO addCamp_Picture(Integer camp_no, String camp_pic) {

		Camp_PictureVO feature_listVO = new Camp_PictureVO();
		feature_listVO.setCamp_no(camp_no);
		feature_listVO.setCamp_pic(camp_pic);
		dao.insert(feature_listVO);

		return feature_listVO;
	}
	
	public void deleteCamp_Picture(Integer camp_pic_no) {
		dao.delete(camp_pic_no);
	}

	public List<String> getCamp_Picture(Integer camp_no) {
		return dao.findByCamp(camp_no);
	}
	public Camp_PictureVO getOneCamp_Picture(Integer camp_pic_no) {
		return dao.findByPrimaryKey(camp_pic_no);
	}

	public List<Camp_PictureVO> getAll() {
		return dao.getAll();
	}
}
