package com.service_mail.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.service_mail_picture.model.Service_mail_pictureVO;

public interface Service_mailDAO_interface {

	public void insert(Service_mailVO service_mailVO);
	public void insertWithPic(Service_mailVO service_mailVO, Set<Service_mail_pictureVO> set);
    public void update(Service_mailVO service_mailVO);
    public void delete(Integer mail_no);
    public Service_mailVO findByPrimaryKey(Integer mail_no);
    public List<Service_mailVO> getAll();
    public Set<Service_mailVO> getWhereCondition(Map<String,String[]> map);
}
