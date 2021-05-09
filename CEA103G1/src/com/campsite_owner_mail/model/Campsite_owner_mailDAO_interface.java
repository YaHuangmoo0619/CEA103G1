package com.campsite_owner_mail.model;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.campsite_owner_mail_picture.model.Campsite_owner_mail_pictureVO;
import com.member_mail.model.Member_mailVO;
import com.member_mail_picture.model.Member_mail_pictureVO;
import com.service_mail_picture.model.Service_mail_pictureVO;

public interface Campsite_owner_mailDAO_interface {

	public void insert(Campsite_owner_mailVO campsite_owner_mailVO);
	public void insertWithPic(Campsite_owner_mailVO campsite_owner_mailVO, Set<Campsite_owner_mail_pictureVO> set);
	public void insertWithSvc (Campsite_owner_mailVO campsite_owner_mailVO, Set<Service_mail_pictureVO> set, Connection con);
    public void insertWithSvc (Campsite_owner_mailVO campsite_owner_mailVO, Connection con);
    public void insertWithMbr (Campsite_owner_mailVO campsite_owner_mailVO, Set<Member_mail_pictureVO> set, Connection con);
    public void insertWithMbr (Campsite_owner_mailVO campsite_owner_mailVO, Connection con);
    public void update(Campsite_owner_mailVO campsite_owner_mailVO);
    public void delete(Integer mail_no);
    public Campsite_owner_mailVO findByPrimaryKey(Integer mail_no);
    public List<Campsite_owner_mailVO> getAll();
    public Set<Campsite_owner_mailVO> getWhereCondition(Map<String,String[]> map);
}
