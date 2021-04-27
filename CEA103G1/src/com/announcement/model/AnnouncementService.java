package com.announcement.model;

import java.sql.Date;
import java.util.List;

public class AnnouncementService {
	
	private AnnouncementDAO_interface dao;
	
	public AnnouncementService() {
		dao = new AnnouncementDAO();
	}
	
	public AnnouncementVO addAnnouncement(Integer emp_no, String an_cont, java.sql.Date an_skd_date, byte[] an_pic) {
		
		AnnouncementVO announcementVO = new AnnouncementVO();
		
		announcementVO.setEmp_no(emp_no);
		announcementVO.setAn_cont(an_cont);
		announcementVO.setAn_skd_date(an_skd_date);
		announcementVO.setAn_pic(an_pic);
		dao.insert(announcementVO);//package attributes to VO, and then use insert(...VO) from DAO to insert data into table
		
		return announcementVO;// no return is OK, but has return may be can do more things
	}
	
	// prepare for Struts2
	public void addAnnouncement(AnnouncementVO announcementVO) {
		dao.insert(announcementVO);
	}
	
	public AnnouncementVO updateAnnouncement(Integer an_no, Integer emp_no, String an_cont, java.sql.Date an_skd_date, byte[] an_pic) {
		
		AnnouncementVO announcementVO = new AnnouncementVO();
		
		announcementVO.setAn_no(an_no);
		announcementVO.setEmp_no(emp_no);
		announcementVO.setAn_cont(an_cont);
		announcementVO.setAn_skd_date(an_skd_date);
		announcementVO.setAn_pic(an_pic);
		dao.update(announcementVO);
		
		return dao.findByPrimaryKey(an_no);
	}
	
	// prepare for Struts2
	public void updateAnnouncement(AnnouncementVO announcementVO) {
		dao.update(announcementVO);
	}
	
	public void deleteAnnouncement(Integer an_no) {
		dao.delete(an_no);
	}

	public AnnouncementVO getOneAnnouncement(Integer an_no) {
		return dao.findByPrimaryKey(an_no);
	}

	public List<AnnouncementVO> getAll() {
		return dao.getAll();
	}
	
	public List<AnnouncementVO> getDateEmp_no(Date an_skd_date) {
		return dao.getDateEmp_no(an_skd_date);
	}

}
