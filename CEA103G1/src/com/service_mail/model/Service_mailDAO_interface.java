package com.service_mail.model;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.campsite_owner_mail_picture.model.Campsite_owner_mail_pictureVO;
import com.member_mail.model.Member_mailVO;
import com.member_mail_picture.model.Member_mail_pictureVO;
import com.service_mail_picture.model.Service_mail_pictureVO;

public interface Service_mailDAO_interface {

	public void insert(Service_mailVO service_mailVO);
	public void insertWithPic(Service_mailVO service_mailVO, Set<Service_mail_pictureVO> set);
	public void insertWithMbr (Service_mailVO service_mailVO, Set<Member_mail_pictureVO> set, Connection con);
    public void insertWithMbr (Service_mailVO service_mailVO, Connection con);
    public void insertWithCOwner (Service_mailVO service_mailVO, Set<Campsite_owner_mail_pictureVO> set, Connection con);
    public void insertWithCOwner (Service_mailVO service_mailVO, Connection con);
	public void update(Service_mailVO service_mailVO);
    public void delete(Integer mail_no);
    public Service_mailVO findByPrimaryKey(Integer mail_no);
    public List<Service_mailVO> getAll();
    public Set<Service_mailVO> getWhereCondition(Map<String,String[]> map);
}
