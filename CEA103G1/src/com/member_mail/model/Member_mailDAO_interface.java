package com.member_mail.model;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.campsite_owner_mail_picture.model.Campsite_owner_mail_pictureVO;
import com.member_mail_picture.model.Member_mail_pictureVO;
import com.service_mail_picture.model.Service_mail_pictureVO;

public interface Member_mailDAO_interface {

	public void insert(Member_mailVO member_mailVO);
	public void insertWithPic(Member_mailVO member_mailVO, Set<Member_mail_pictureVO> set);
	public void insertWithSvc (Member_mailVO member_mailVO, Set<Service_mail_pictureVO> set, Connection con);
    public void insertWithSvc (Member_mailVO member_mailVO, Connection con);
    public void insertWithCOwner (Member_mailVO member_mailVO, Set<Campsite_owner_mail_pictureVO> set, Connection con);
    public void insertWithCOwner (Member_mailVO member_mailVO, Connection con);
    public void insertWithMbr (Member_mailVO member_mailVO, Set<Member_mail_pictureVO> set, Connection con);
    public void insertWithMbr (Member_mailVO member_mailVO, Connection con);
    public void update(Member_mailVO member_mailVO);
    public void delete(Integer mail_no);
    public Member_mailVO findByPrimaryKey(Integer mail_no);
    public List<Member_mailVO> getAll();
    public List<Member_mailVO> getSend(Integer send_no);
    public List<Member_mailVO> getRcpt(Integer rcpt_no);
    public List<Member_mailVO> getStat(Integer mail_stat);
    public Set<Member_mailVO> getWhereCondition(Map<String,String[]> map);
}