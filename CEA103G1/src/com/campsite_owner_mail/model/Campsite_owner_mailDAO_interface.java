package com.campsite_owner_mail.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.member_mail.model.Member_mailVO;

public interface Campsite_owner_mailDAO_interface {

	public void insert(Campsite_owner_mailVO campsite_owner_mailVO);
    public void update(Campsite_owner_mailVO campsite_owner_mailVO);
    public void delete(Integer mail_no);
    public Campsite_owner_mailVO findByPrimaryKey(Integer mail_no);
    public List<Campsite_owner_mailVO> getAll();
    public Set<Campsite_owner_mailVO> getWhereCondition(Map<String,String[]> map);
}
