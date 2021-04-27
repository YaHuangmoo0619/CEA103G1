package com.campsite_comment_report.model;

import java.sql.Timestamp;
import java.util.List;

public class Campsite_comment_reportService {

	private Campsite_comment_reportDAO_interface dao;

	public Campsite_comment_reportService() {
		dao = new Campsite_comment_reportDAO();
	}

	public Campsite_comment_reportVO addCampsite_comment_report(Integer camp_cmnt_no, Integer mbr_no, String rpt_cont, Timestamp rpt_time, Integer proc_stat) {

		Campsite_comment_reportVO campsite_comment_reportVO = new Campsite_comment_reportVO();

		campsite_comment_reportVO.setCamp_cmnt_no(camp_cmnt_no);
		campsite_comment_reportVO.setMbr_no(mbr_no);
		campsite_comment_reportVO.setRpt_cont(rpt_cont);
		campsite_comment_reportVO.setRpt_time(rpt_time);
		campsite_comment_reportVO.setProc_stat(proc_stat);
		dao.insert(campsite_comment_reportVO);
		
		return campsite_comment_reportVO;
	}
	
	public Campsite_comment_reportVO updateCampsite_comment_report(Integer camp_cmnt_rpt_no, Integer camp_cmnt_no, Integer mbr_no, String rpt_cont, Timestamp rpt_time, Integer proc_stat) {

		Campsite_comment_reportVO campsite_comment_reportVO = new Campsite_comment_reportVO();
		
		campsite_comment_reportVO.setCamp_cmnt_rpt_no(camp_cmnt_rpt_no);
		campsite_comment_reportVO.setCamp_cmnt_no(camp_cmnt_no);
		campsite_comment_reportVO.setMbr_no(mbr_no);
		campsite_comment_reportVO.setRpt_cont(rpt_cont);
		campsite_comment_reportVO.setRpt_time(rpt_time);
		campsite_comment_reportVO.setProc_stat(proc_stat);
		dao.update(campsite_comment_reportVO);
		
		return campsite_comment_reportVO;
	}

	public void deleteCampsite_comment_report(Integer camp_cmnt_rpt_no) {
		dao.delete(camp_cmnt_rpt_no);
	}

	public Campsite_comment_reportVO getOneCampsite_comment_report(Integer camp_cmnt_rpt_no) {
		return dao.findByPrimaryKey(camp_cmnt_rpt_no);
	}

	public List<Campsite_comment_reportVO> getAll() {
		return dao.getAll();
	}
}
