package com.service_mail.model;

import java.util.List;

public interface Service_mailDAO_interface {

	public void insert(Service_mailVO service_mailVO);
    public void update(Service_mailVO service_mailVO);
    public void delete(Integer mail_no);
    public Service_mailVO findByPrimaryKey(Integer mail_no);
    public List<Service_mailVO> getAll();
}
