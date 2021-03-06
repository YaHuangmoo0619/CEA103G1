package com.service_mail.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.service_mail_picture.model.Service_mail_pictureVO;

public class Service_mailService {
	
	private Service_mailDAO_interface dao;
	
	public Service_mailService() {
		dao = new Service_mailDAO();
	}
	
	public Service_mailVO addService_mail(Integer emp_no, Integer mbr_no, String mail_cont, Integer mail_stat, Integer mail_read_stat, String mail_time) {
		
		Service_mailVO service_mailVO = new Service_mailVO();
		
		service_mailVO.setEmp_no(emp_no);
		service_mailVO.setMbr_no(mbr_no);
		service_mailVO.setMail_cont(mail_cont);
		service_mailVO.setMail_stat(mail_stat);
		service_mailVO.setMail_read_stat(mail_read_stat);
		service_mailVO.setMail_time(mail_time);
		dao.insert(service_mailVO);//package attributes to VO, and then use insert(...VO) from DAO to insert data into table
		
		return service_mailVO;// no return is OK, but has return may be can do more things
	}
	
	// prepare for Struts2
	public void addService_mail(Service_mailVO service_mailVO) {
		dao.insert(service_mailVO);
	}
	
	public Service_mailVO updateService_mail(Integer mail_no, Integer emp_no, Integer mbr_no, String mail_cont, Integer mail_stat, Integer mail_read_stat, String mail_time) {
		
		Service_mailVO service_mailVO = new Service_mailVO();
		
		service_mailVO.setMail_no(mail_no);
		service_mailVO.setEmp_no(emp_no);
		service_mailVO.setMbr_no(mbr_no);
		service_mailVO.setMail_cont(mail_cont);
		service_mailVO.setMail_stat(mail_stat);
		service_mailVO.setMail_read_stat(mail_read_stat);
		service_mailVO.setMail_time(mail_time);
		dao.update(service_mailVO);
		
		return dao.findByPrimaryKey(mail_no);
	}
	
	// prepare for Struts2
	public void updateService_mail(Service_mailVO service_mailVO) {
		dao.update(service_mailVO);
	}
	
	public void deleteService_mail(Integer mail_no) {
		dao.delete(mail_no);
	}

	public Service_mailVO getOneService_mail(Integer mail_no) {
		return dao.findByPrimaryKey(mail_no);
	}

	public List<Service_mailVO> getAll() {
		return dao.getAll();
	}
	
	public Set<Service_mailVO> getWhereCondition(Map<String,String[]> map){
		return dao.getWhereCondition(map);
	}
	
	public void insertWithPic(Integer emp_no, Integer mbr_no, String mail_cont, Integer mail_stat, Integer mail_read_stat, String mail_time,Set<Service_mail_pictureVO> set) {
		Service_mailVO service_mailVO = new Service_mailVO();
		
		service_mailVO.setEmp_no(emp_no);
		service_mailVO.setMbr_no(mbr_no);
		service_mailVO.setMail_cont(mail_cont);
		service_mailVO.setMail_stat(mail_stat);
		service_mailVO.setMail_read_stat(mail_read_stat);
		service_mailVO.setMail_time(mail_time);
		dao.insertWithPic(service_mailVO, set);
	}
}
