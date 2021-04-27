package com.authority.model;

import java.util.List;

public interface AuthorityDAO_interface {

	public void insert(AuthorityVO authorityVO);
//    public void update(AuthorityVO authorityVO);
    public void delete(Integer emp_no, Integer fx_no);
    public AuthorityVO findByPrimaryKey(Integer emp_no, Integer fx_no);
    public List<AuthorityVO> getAll();
    public List<AuthorityVO> findByEmp_no(Integer emp_no);
    public List<AuthorityVO> findByFx_no(Integer fx_no);
}

