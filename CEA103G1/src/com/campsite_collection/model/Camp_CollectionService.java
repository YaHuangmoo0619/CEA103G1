package com.campsite_collection.model;

import java.sql.Date;
import java.util.List;

public class Camp_CollectionService {
	private Camp_CollectionDAO_interface dao;

	public Camp_CollectionService() {
		dao = new Camp_CollectionDAO();
	}

	public Camp_CollectionVO addCamp_Collection(Integer camp_no, Integer mbr_no) {

		Camp_CollectionVO camp_collectionVO = new Camp_CollectionVO();
		camp_collectionVO.setMbr_no(mbr_no);
		camp_collectionVO.setCamp_no(camp_no);
		dao.insert(camp_collectionVO);

		return camp_collectionVO;
	}

	public Camp_CollectionVO updateCamp_Collection(Integer camp_no, Integer mbr_no) {

		Camp_CollectionVO camp_collectionVO = new Camp_CollectionVO();
		camp_collectionVO.setMbr_no(mbr_no);
		camp_collectionVO.setCamp_no(camp_no);
		dao.update(camp_collectionVO);

		return camp_collectionVO;
	}

	public void deleteCamp_Collection(Integer camp_no, Integer mbr_no) {
		dao.delete(camp_no, mbr_no);
	}

	public Camp_CollectionVO getOneCamp_Collection(Integer camp_no, Integer mbr_no) {
		return dao.findByPrimaryKey(camp_no, mbr_no);
	}

	public List<Camp_CollectionVO> getAll() {
		return dao.getAll();
	}
}
