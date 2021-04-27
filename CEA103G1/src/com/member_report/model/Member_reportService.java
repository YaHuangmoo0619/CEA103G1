package com.member_report.model;

import java.sql.Timestamp;
import java.util.List;

public class Member_reportService {

	private Member_reportDAO_interface dao;

	public Member_reportService() {
		dao = new Member_reportDAO();
	}

	public Member_reportVO addMember_report(Integer rpted_mbr_no, Integer rpt_mbr_no, String rpt_cont, Timestamp rpt_time, Integer proc_stat) {

		Member_reportVO member_reportVO = new Member_reportVO();

		member_reportVO.setRpted_mbr_no(rpted_mbr_no);
		member_reportVO.setRpt_mbr_no(rpt_mbr_no);
		member_reportVO.setRpt_cont(rpt_cont);
		member_reportVO.setRpt_time(rpt_time);
		member_reportVO.setProc_stat(proc_stat);
		dao.insert(member_reportVO);
		
		return member_reportVO;
	}
	
	public Member_reportVO updateMember_report(Integer mbr_rpt_no, Integer rpted_mbr_no, Integer rpt_mbr_no, String rpt_cont, Timestamp rpt_time, Integer proc_stat) {

		Member_reportVO member_reportVO = new Member_reportVO();
		
		member_reportVO.setMbr_rpt_no(mbr_rpt_no);
		member_reportVO.setRpted_mbr_no(rpted_mbr_no);
		member_reportVO.setRpt_mbr_no(rpt_mbr_no);
		member_reportVO.setRpt_cont(rpt_cont);
		member_reportVO.setRpt_time(rpt_time);
		member_reportVO.setProc_stat(proc_stat);
		dao.update(member_reportVO);
		
		return member_reportVO;
	}

	public void deleteMember_report(Integer mbr_rpt_no) {
		dao.delete(mbr_rpt_no);
	}

	public Member_reportVO getOneMember_report(Integer mbr_rpt_no) {
		return dao.findByPrimaryKey(mbr_rpt_no);
	}

	public List<Member_reportVO> getAll() {
		return dao.getAll();
	}
}
