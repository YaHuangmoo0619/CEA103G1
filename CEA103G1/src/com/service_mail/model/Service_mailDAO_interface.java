package com.service_mail.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Service_mailDAO_interface {

	public void insert(Service_mailVO service_mailVO);
    public void update(Service_mailVO service_mailVO);
    public void delete(Integer mail_no);
    public Service_mailVO findByPrimaryKey(Integer mail_no);
    public List<Service_mailVO> getAll();
    public Set<Service_mailVO> getWhereCondition(Map<String,String[]> map);
}
