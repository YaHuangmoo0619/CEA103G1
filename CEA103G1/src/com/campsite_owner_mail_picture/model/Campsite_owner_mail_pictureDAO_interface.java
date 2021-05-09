package com.campsite_owner_mail_picture.model;

import java.sql.Connection;
import java.util.List;


public interface Campsite_owner_mail_pictureDAO_interface {

	public void insert(Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO);
    public void update(Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO);
    public void delete(Integer mail_pic_no);
    public Campsite_owner_mail_pictureVO findByPrimaryKey(Integer mail_pic_no);
    public List<Campsite_owner_mail_pictureVO> getAll();
    public List<Campsite_owner_mail_pictureVO> findByMail_no(Integer mail_no);
    public void insertWithMail (Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO , Connection con);
}
