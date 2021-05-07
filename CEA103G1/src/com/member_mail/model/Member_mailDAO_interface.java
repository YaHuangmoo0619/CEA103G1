package com.member_mail.model;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.service_mail.model.Service_mailVO;
import com.service_mail_picture.model.Service_mail_pictureVO;

public interface Member_mailDAO_interface {

	public void insert(Member_mailVO member_mailVO);
    public void update(Member_mailVO member_mailVO);
    public void delete(Integer mail_no);
    public Member_mailVO findByPrimaryKey(Integer mail_no);
    public List<Member_mailVO> getAll();
    public Set<Member_mailVO> getWhereCondition(Map<String,String[]> map);
    public void insertWithEmp (Member_mailVO member_mailVO , Connection con);
}
