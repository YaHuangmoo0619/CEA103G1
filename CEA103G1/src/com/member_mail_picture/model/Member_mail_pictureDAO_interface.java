package com.member_mail_picture.model;

import java.util.List;

public interface Member_mail_pictureDAO_interface {

	public void insert(Member_mail_pictureVO member_mail_pictureVO);
    public void update(Member_mail_pictureVO member_mail_pictureVO);
    public void delete(Integer mail_pic_no);
    public Member_mail_pictureVO findByPrimaryKey(Integer mail_pic_no);
    public List<Member_mail_pictureVO> getAll();
}
