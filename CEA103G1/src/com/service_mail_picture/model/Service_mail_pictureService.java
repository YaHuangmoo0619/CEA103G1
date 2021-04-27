package com.service_mail_picture.model;

import java.util.List;

public class Service_mail_pictureService {
	
	private Service_mail_pictureDAO_interface dao;
	
	public Service_mail_pictureService() {
		dao = new Service_mail_pictureDAO();
	}
	
	public Service_mail_pictureVO addService_mail_picture(Integer mail_no) {
		
		Service_mail_pictureVO service_mail_pictureVO = new Service_mail_pictureVO();
		
		service_mail_pictureVO.setMail_no(mail_no);

		dao.insert(service_mail_pictureVO);//package attributes to VO, and then use insert(...VO) from DAO to insert data into table
		
		return service_mail_pictureVO;// no return is OK, but has return may be can do more things
	}
	
	// prepare for Struts2
	public void addService_mail_picture(Service_mail_pictureVO service_mail_pictureVO) {
		dao.insert(service_mail_pictureVO);
	}
	
	public Service_mail_pictureVO updateService_mail_picture(Integer svc_mail_pic_no, Integer mail_no) {
		
		Service_mail_pictureVO service_mail_pictureVO = new Service_mail_pictureVO();
		
		service_mail_pictureVO.setSvc_mail_pic_no(svc_mail_pic_no);
		service_mail_pictureVO.setMail_no(mail_no);
		dao.update(service_mail_pictureVO);
		
		return dao.findByPrimaryKey(svc_mail_pic_no);
	}
	
	// prepare for Struts2
	public void updateService_mail_picture(Service_mail_pictureVO service_mail_pictureVO) {
		dao.update(service_mail_pictureVO);
	}
	
	public void deleteService_mail_picture(Integer svc_mail_pic_no) {
		dao.delete(svc_mail_pic_no);
	}

	public Service_mail_pictureVO getOneService_mail_picture(Integer svc_mail_pic_no) {
		return dao.findByPrimaryKey(svc_mail_pic_no);
	}

	public List<Service_mail_pictureVO> getAll() {
		return dao.getAll();
	}

}
