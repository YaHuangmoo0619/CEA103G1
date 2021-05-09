package com.campsite_owner_mail.model;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.campsite_owner_mail_picture.model.Campsite_owner_mail_pictureVO;
import com.member_mail.model.Member_mailVO;
import com.member_mail_picture.model.Member_mail_pictureVO;
import com.service_mail_picture.model.Service_mail_pictureVO;

public class Campsite_owner_mailService {
	
	private Campsite_owner_mailDAO_interface dao;
	
	public Campsite_owner_mailService() {
		dao = new Campsite_owner_mailDAO();
	}
	
	public Campsite_owner_mailVO addCampsite_owner_mail(Integer send_no, Integer rcpt_no, Integer mail_read_stat, Integer mail_stat, String mail_cont, String mail_time) {
		
		Campsite_owner_mailVO campsite_owner_mailVO = new Campsite_owner_mailVO();
		
		campsite_owner_mailVO.setSend_no(send_no);
		campsite_owner_mailVO.setRcpt_no(rcpt_no);
		campsite_owner_mailVO.setMail_read_stat(mail_read_stat);
		campsite_owner_mailVO.setMail_stat(mail_stat);
		campsite_owner_mailVO.setMail_cont(mail_cont);
		campsite_owner_mailVO.setMail_time(mail_time);
		dao.insert(campsite_owner_mailVO);//package attributes to VO, and then use insert(...VO) from DAO to insert data into table
		
		return campsite_owner_mailVO;// no return is OK, but has return may be can do more things
	}
	
	// prepare for Struts2
	public void addCampsite_owner_mail(Campsite_owner_mailVO campsite_owner_mailVO) {
		dao.insert(campsite_owner_mailVO);
	}
	
	public Campsite_owner_mailVO updateCampsite_owner_mail(Integer mail_no, Integer send_no, Integer rcpt_no, Integer mail_read_stat, Integer mail_stat, String mail_cont, String mail_time) {
		
		Campsite_owner_mailVO campsite_owner_mailVO = new Campsite_owner_mailVO();
		
		campsite_owner_mailVO.setMail_no(mail_no);
		campsite_owner_mailVO.setSend_no(send_no);
		campsite_owner_mailVO.setRcpt_no(rcpt_no);
		campsite_owner_mailVO.setMail_read_stat(mail_read_stat);
		campsite_owner_mailVO.setMail_stat(mail_stat);
		campsite_owner_mailVO.setMail_cont(mail_cont);
		campsite_owner_mailVO.setMail_time(mail_time);
		dao.update(campsite_owner_mailVO);
		
		return dao.findByPrimaryKey(mail_no);
	}
	
	// prepare for Struts2
	public void updateCampsite_owner_mail(Campsite_owner_mailVO campsite_owner_mailVO) {
		dao.update(campsite_owner_mailVO);
	}
	
	public void deleteCampsite_owner_mail(Integer mail_no) {
		dao.delete(mail_no);
	}

	public Campsite_owner_mailVO getOneCampsite_owner_mail(Integer mail_no) {
		return dao.findByPrimaryKey(mail_no);
	}

	public List<Campsite_owner_mailVO> getAll() {
		return dao.getAll();
	}
	
	public Set<Campsite_owner_mailVO> getWhereCondition(Map<String,String[]> map){
		return dao.getWhereCondition(map);
	}
	
	public void insertWithPic(Integer send_no, Integer rcpt_no, Integer mail_read_stat, Integer mail_stat, String mail_cont, String mail_time, Set<Campsite_owner_mail_pictureVO> set) {
		
		Campsite_owner_mailVO campsite_owner_mailVO = new Campsite_owner_mailVO();
		
		campsite_owner_mailVO.setSend_no(send_no);
		campsite_owner_mailVO.setRcpt_no(rcpt_no);
		campsite_owner_mailVO.setMail_read_stat(mail_read_stat);
		campsite_owner_mailVO.setMail_stat(mail_stat);
		campsite_owner_mailVO.setMail_cont(mail_cont);
		campsite_owner_mailVO.setMail_time(mail_time);

		dao.insertWithPic(campsite_owner_mailVO, set);
	}
//	public void insertWithSvc (Campsite_owner_mailVO campsite_owner_mailVO, Set<Service_mail_pictureVO> set, Connection con) {
//		dao.insertWithSvc (campsite_owner_mailVO, set, con);
//	}
//    public void insertWithSvc (Campsite_owner_mailVO campsite_owner_mailVO, Connection con) {
//    	dao.insertWithSvc (campsite_owner_mailVO, con);
//    }
//    public void insertWithMbr (Campsite_owner_mailVO campsite_owner_mailVO, Set<Member_mail_pictureVO> set, Connection con) {
//    	dao.insertWithMbr (campsite_owner_mailVO, set, con);
//    }
//    public void insertWithMbr (Campsite_owner_mailVO campsite_owner_mailVO, Connection con) {
//    	dao.insertWithMbr (campsite_owner_mailVO, con);
//    }
}
