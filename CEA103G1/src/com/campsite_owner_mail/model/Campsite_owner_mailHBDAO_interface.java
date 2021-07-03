package com.campsite_owner_mail.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.member_mail_picture.model.Member_mail_pictureHBVO;
import com.service_mail_picture.model.Service_mail_pictureHBVO;

public interface Campsite_owner_mailHBDAO_interface {

	public void insert(Campsite_owner_mailVO campsite_owner_mailVO);
	public void insertWithSvc (Campsite_owner_mailVO campsite_owner_mailVO, Set<Service_mail_pictureHBVO> set);
    public void insertWithSvc (Campsite_owner_mailVO campsite_owner_mailVO);
    public void insertWithMbr (Campsite_owner_mailVO campsite_owner_mailVO, Set<Member_mail_pictureHBVO> set);
    public void insertWithMbr (Campsite_owner_mailVO campsite_owner_mailVO);
    public void update(Campsite_owner_mailVO campsite_owner_mailVO);
    public void delete(Integer mail_no);
    public Campsite_owner_mailVO findByPrimaryKey(Integer mail_no);
    public List<Campsite_owner_mailVO> getAll();
    public Set<Campsite_owner_mailVO> getWhereCondition(Map<String,String[]> map);
}
