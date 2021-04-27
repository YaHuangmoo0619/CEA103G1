package com.campsite_report.model;

import java.sql.Timestamp;
import java.util.List;

public class Campsite_reportService {

	private Campsite_reportDAO_interface dao;

	public Campsite_reportService() {
		dao = new Campsite_reportDAO();
	}

	public Campsite_reportVO addCampsite_report(Integer mbr_no, Integer camp_no, String rpt_cont, Timestamp rpt_time, Integer proc_stat) {

		Campsite_reportVO campsite_reportVO = new Campsite_reportVO();

		campsite_reportVO.setMbr_no(mbr_no);
		campsite_reportVO.setCamp_no(camp_no);
		campsite_reportVO.setRpt_cont(rpt_cont);
		campsite_reportVO.setRpt_time(rpt_time);
		campsite_reportVO.setProc_stat(proc_stat);
		dao.insert(campsite_reportVO);
		
		return campsite_reportVO;
	}
	
	public Campsite_reportVO updateCampsite_report(Integer camp_rpt_no, Integer mbr_no, Integer camp_no, String rpt_cont, Timestamp rpt_time, Integer proc_stat) {

		Campsite_reportVO campsite_reportVO = new Campsite_reportVO();
		
		campsite_reportVO.setCamp_rpt_no(camp_rpt_no);
		campsite_reportVO.setMbr_no(mbr_no);
		campsite_reportVO.setCamp_no(camp_no);
		campsite_reportVO.setRpt_cont(rpt_cont);
		campsite_reportVO.setRpt_time(rpt_time);
		campsite_reportVO.setProc_stat(proc_stat);
		dao.update(campsite_reportVO);
		
		return campsite_reportVO;
	}

	public void deleteCampsite_report(Integer camp_rpt_no) {
		dao.delete(camp_rpt_no);
	}

	public Campsite_reportVO getOneCampsite_report(Integer camp_rpt_no) {
		return dao.findByPrimaryKey(camp_rpt_no);
	}

	public List<Campsite_reportVO> getAll() {
		return dao.getAll();
	}
}
