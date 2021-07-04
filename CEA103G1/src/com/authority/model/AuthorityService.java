package com.authority.model;

import java.util.List;

public class AuthorityService {
	
	private AuthorityDAO_interface dao;
	
	public AuthorityService() {
		dao = new AuthorityHBDAO();
	}
	
	public AuthorityVO addAuthority(Integer emp_no, Integer fx_no) {
		
		AuthorityVO authorityVO = new AuthorityVO();
		
		authorityVO.setEmp_no(emp_no);
		authorityVO.setFx_no(fx_no);
		dao.insert(authorityVO);//package attributes to VO, and then use insert(...VO) from DAO to insert data into table
		
		return authorityVO;// no return is OK, but has return may be can do more things
	}
	
//	// prepare for Struts2
//	public void addAuthority(AuthorityVO authorityVO) {
//		dao.insert(authorityVO);
//	}
//	
//	public AuthorityVO updateAuthority(Integer emp_no, Integer fx_no) {
//		
//		AuthorityVO authorityVO = new AuthorityVO();
//		
//		authorityVO.setEmp_no(emp_no);
//		authorityVO.setFx_no(fx_no);
//		dao.update(authorityVO);
//		
//		return dao.findByPrimaryKey(an_no);
//	}
	
//	// prepare for Struts2
//	public void updateAuthority(AuthorityVO authorityVO) {
//		dao.update(authorityVO);
//	}
	
	public void deleteAuthority(Integer emp_no, Integer fx_no) {
		dao.delete(emp_no, fx_no);
	}

	public AuthorityVO getOneAuthority(Integer emp_no, Integer fx_no) {
		return dao.findByPrimaryKey(emp_no, fx_no);
	}

	public List<AuthorityVO> getAll() {
		return dao.getAll();
	}
	
	public List<AuthorityVO> findByFx_no(Integer fx_no) {
		return dao.findByFx_no(fx_no);
	}
	
	public List<AuthorityVO> findByEmp_no(Integer emp_no){
		return dao.findByEmp_no(emp_no);
	}

}
