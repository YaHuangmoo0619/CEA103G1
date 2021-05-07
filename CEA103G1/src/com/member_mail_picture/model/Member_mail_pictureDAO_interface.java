package com.member_mail_picture.model;

import java.sql.Connection;
import java.util.List;

import com.service_mail_picture.model.Service_mail_pictureVO;

public interface Member_mail_pictureDAO_interface {

	public void insert(Member_mail_pictureVO member_mail_pictureVO);
    public void update(Member_mail_pictureVO member_mail_pictureVO);
    public void delete(Integer mail_pic_no);
    public Member_mail_pictureVO findByPrimaryKey(Integer mail_pic_no);
    public List<Member_mail_pictureVO> getAll();
    public List<Member_mail_pictureVO> findByMail_no(Integer mail_no);
    public void insertWithMail (Member_mail_pictureVO member_mail_pictureVO , Connection con);
}
