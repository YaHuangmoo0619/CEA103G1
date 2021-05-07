package com.member_mail_picture.model;

import java.util.List;

import com.service_mail_picture.model.Service_mail_pictureVO;

public class Member_mail_pictureService {
	
	private Member_mail_pictureDAO_interface dao;
	
	public Member_mail_pictureService() {
		dao = new Member_mail_pictureDAO();
	}
	
	public Member_mail_pictureVO addMember_mail_picture(Integer mail_no) {
		
		Member_mail_pictureVO member_mail_pictureVO = new Member_mail_pictureVO();
		
		member_mail_pictureVO.setMail_no(mail_no);

		dao.insert(member_mail_pictureVO);//package attributes to VO, and then use insert(...VO) from DAO to insert data into table
		
		return member_mail_pictureVO;// no return is OK, but has return may be can do more things
	}
	
	// prepare for Struts2
	public void addMember_mail_picture(Member_mail_pictureVO member_mail_pictureVO) {
		dao.insert(member_mail_pictureVO);
	}
	
	public Member_mail_pictureVO updateMember_mail_picture(Integer mail_pic_no, Integer mail_no) {
		
		Member_mail_pictureVO member_mail_pictureVO = new Member_mail_pictureVO();
		
		member_mail_pictureVO.setMail_pic_no(mail_pic_no);
		member_mail_pictureVO.setMail_no(mail_no);
		dao.update(member_mail_pictureVO);
		
		return dao.findByPrimaryKey(mail_pic_no);
	}
	
	// prepare for Struts2
	public void updateMember_mail_picture(Member_mail_pictureVO member_mail_pictureVO) {
		dao.update(member_mail_pictureVO);
	}
	
	public void deleteMember_mail_picture(Integer mail_pic_no) {
		dao.delete(mail_pic_no);
	}

	public Member_mail_pictureVO getOneMember_mail_picture(Integer mail_pic_no) {
		return dao.findByPrimaryKey(mail_pic_no);
	}

	public List<Member_mail_pictureVO> getAll() {
		return dao.getAll();
	}
	
	public List<Member_mail_pictureVO> getByMail_no(Integer mail_no) {
		return dao.findByMail_no(mail_no);
	}

}
