package com.campsite_owner_mail_picture.model;

import java.util.List;

public class Campsite_owner_mail_pictureService {
	
	private Campsite_owner_mail_pictureDAO_interface dao;
	
	public Campsite_owner_mail_pictureService() {
		dao = new Campsite_owner_mail_pictureDAO();
	}
	
	public Campsite_owner_mail_pictureVO addCampsite_owner_mail_picture(Integer mail_no) {
		
		Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO = new Campsite_owner_mail_pictureVO();
		
		campsite_owner_mail_pictureVO.setMail_no(mail_no);

		dao.insert(campsite_owner_mail_pictureVO);//package attributes to VO, and then use insert(...VO) from DAO to insert data into table
		
		return campsite_owner_mail_pictureVO;// no return is OK, but has return may be can do more things
	}
	
	// prepare for Struts2
	public void addCampsite_owner_mail_picture(Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO) {
		dao.insert(campsite_owner_mail_pictureVO);
	}
	
	public Campsite_owner_mail_pictureVO updateCampsite_owner_mail_picture(Integer mail_pic_no, Integer mail_no) {
		
		Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO = new Campsite_owner_mail_pictureVO();
		
		campsite_owner_mail_pictureVO.setMail_pic_no(mail_pic_no);
		campsite_owner_mail_pictureVO.setMail_no(mail_no);
		dao.update(campsite_owner_mail_pictureVO);
		
		return dao.findByPrimaryKey(mail_pic_no);
	}
	
	// prepare for Struts2
	public void updateCampsite_owner_mail_picture(Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO) {
		dao.update(campsite_owner_mail_pictureVO);
	}
	
	public void deleteCampsite_owner_mail_picture(Integer mail_pic_no) {
		dao.delete(mail_pic_no);
	}

	public Campsite_owner_mail_pictureVO getOneCampsite_owner_mail_picture(Integer mail_pic_no) {
		return dao.findByPrimaryKey(mail_pic_no);
	}

	public List<Campsite_owner_mail_pictureVO> getAll() {
		return dao.getAll();
	}

}
