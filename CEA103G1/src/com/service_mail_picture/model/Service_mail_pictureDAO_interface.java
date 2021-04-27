package com.service_mail_picture.model;

import java.util.List;

public interface Service_mail_pictureDAO_interface {

	public void insert(Service_mail_pictureVO service_mail_pictureVO);
    public void update(Service_mail_pictureVO service_mail_pictureVO);
    public void delete(Integer svc_mail_pic_no);
    public Service_mail_pictureVO findByPrimaryKey(Integer svc_mail_pic_no);
    public List<Service_mail_pictureVO> getAll();
}
