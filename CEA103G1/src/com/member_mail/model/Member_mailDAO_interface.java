package com.member_mail.model;

import java.util.List;

public interface Member_mailDAO_interface {

	public void insert(Member_mailVO member_mailVO);
    public void update(Member_mailVO member_mailVO);
    public void delete(Integer mail_no);
    public Member_mailVO findByPrimaryKey(Integer mail_no);
    public List<Member_mailVO> getAll();
}
