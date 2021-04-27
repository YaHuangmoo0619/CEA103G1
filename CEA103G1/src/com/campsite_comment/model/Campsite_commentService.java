package com.campsite_comment.model;

import java.sql.Timestamp;
import java.util.List;

public class Campsite_commentService {

	private Campsite_commentDAO_interface dao;

	public Campsite_commentService() {
		dao = new Campsite_commentDAO();
	}

	public Campsite_commentVO addCampsite_comment(Integer camp_no, Integer mbr_no, String cmnt_cont, Integer star, Timestamp cmnt_time, Integer cmnt_stat) {

		Campsite_commentVO campsite_commentVO = new Campsite_commentVO();

		campsite_commentVO.setCamp_no(camp_no);
		campsite_commentVO.setMbr_no(mbr_no);
		campsite_commentVO.setCmnt_cont(cmnt_cont);
		campsite_commentVO.setStar(star);
		campsite_commentVO.setCmnt_time(cmnt_time);
		campsite_commentVO.setCmnt_stat(cmnt_stat);
		dao.insert(campsite_commentVO);
		
		return campsite_commentVO;
	}
	
	public Campsite_commentVO updateCampsite_comment(Integer camp_cmnt_no, Integer camp_no, Integer mbr_no, String cmnt_cont, Integer star, Timestamp cmnt_time, Integer cmnt_stat) {

		Campsite_commentVO campsite_commentVO = new Campsite_commentVO();
		
		campsite_commentVO.setCamp_cmnt_no(camp_cmnt_no);
		campsite_commentVO.setCamp_no(camp_no);
		campsite_commentVO.setMbr_no(mbr_no);
		campsite_commentVO.setCmnt_cont(cmnt_cont);
		campsite_commentVO.setStar(star);
		campsite_commentVO.setCmnt_time(cmnt_time);
		campsite_commentVO.setCmnt_stat(cmnt_stat);
		dao.update(campsite_commentVO);
		
		return campsite_commentVO;
	}

	public void deleteCampsite_comment(Integer rank_no) {
		dao.delete(rank_no);
	}

	public Campsite_commentVO getOneCampsite_comment(Integer rank_no) {
		return dao.findByPrimaryKey(rank_no);
	}

	public List<Campsite_commentVO> getAll() {
		return dao.getAll();
	}
}
