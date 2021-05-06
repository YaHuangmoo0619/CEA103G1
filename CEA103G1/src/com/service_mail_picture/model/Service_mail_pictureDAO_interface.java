package com.service_mail_picture.model;

import java.sql.Connection;
import java.util.List;

public interface Service_mail_pictureDAO_interface {

	public void insert(Service_mail_pictureVO service_mail_pictureVO);
    public void update(Service_mail_pictureVO service_mail_pictureVO);
    public void delete(Integer svc_mail_pic_no);
    public Service_mail_pictureVO findByPrimaryKey(Integer svc_mail_pic_no);
    public List<Service_mail_pictureVO> getAll();
    public List<Service_mail_pictureVO> findByMail_no(Integer mail_no);
    public void insertWithMail (Service_mail_pictureVO service_mail_pictureVO , Connection con);
}
