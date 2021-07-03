package com.service_mail.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.campsite_owner_mail_picture.model.Campsite_owner_mail_pictureHBVO;
import com.member_mail_picture.model.Member_mail_pictureHBVO;
import com.service_mail_picture.model.Service_mail_pictureHBVO;

public interface Service_mailHBDAO_interface {

	public void insert(Service_mailVO service_mailVO);
	public void insertWithPic(Service_mailVO service_mailVO, Set<Service_mail_pictureHBVO> set);
	public void insertWithMbr (Service_mailVO service_mailVO, Set<Member_mail_pictureHBVO> set);
    public void insertWithMbr (Service_mailVO service_mailVO);
    public void insertWithCOwner (Service_mailVO service_mailVO, Set<Campsite_owner_mail_pictureHBVO> set);
    public void insertWithCOwner (Service_mailVO service_mailVO);
	public void update(Service_mailVO service_mailVO);
    public void delete(Integer mail_no);
    public Service_mailVO findByPrimaryKey(Integer mail_no);
    public List<Service_mailVO> getAll();
    public Set<Service_mailVO> getWhereCondition(Map<String,String[]> map);
}
