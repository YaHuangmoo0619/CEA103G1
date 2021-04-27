package com.follow.model;
import java.sql.Timestamp;
import java.util.List;


public class FollowService {

	private FollowDAO_Interface dao;
	////private FollowDAO_Interface dao =  new FollowDAO();
	
	public FollowService() {
		dao = new FollowDAO(); 
	}

	public FollowVO addFollow(Integer flwed_mbr_no, Integer flw_mbr_no) {

		FollowVO followVO = new FollowVO();
	

		followVO.setFlwed_mbr_no(flwed_mbr_no);
		followVO.setFlw_mbr_no(flw_mbr_no);

		dao.insert(followVO);

		return followVO;
	}



	public void deleteFollow(Integer flwed_mbr_no,Integer flw_mbr_no) {
		dao.delete(flwed_mbr_no,flw_mbr_no);
	}


	public List<FollowVO> getAll() {
		return dao.getAll();
	}
	
	
	public List<FollowVO> findbyflwed(Integer flwed_mbr_no) {
		return dao.findByflwed_mbr_no(flwed_mbr_no);
	}
	
	public List<FollowVO> findbyflw(Integer flw_mbr_no) {
		return dao.findByflw_mbr_no(flw_mbr_no);
	}
}