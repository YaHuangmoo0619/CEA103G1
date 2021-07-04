package com.function.model;

import java.util.List;

public class FunctionService {
	
	private FunctionDAO_interface dao;
	
	public FunctionService() {
		dao = new FunctionHBDAO();
	}
	
	public FunctionVO addFunction(String fx_name) {
		
		FunctionVO functionVO = new FunctionVO();
		
		functionVO.setFx_name(fx_name);
		dao.insert(functionVO);//package attributes to VO, and then use insert(...VO) from DAO to insert data into table
		
		return functionVO;// no return is OK, but has return may be can do more things
	}
	
	// prepare for Struts2
	public void addFunction(FunctionVO functionVO) {
		dao.insert(functionVO);
	}
	
	public FunctionVO updateFunction(Integer fx_no, String fx_name) {
		
		FunctionVO functionVO = new FunctionVO();
		
		functionVO.setFx_no(fx_no);
		functionVO.setFx_name(fx_name);
		dao.update(functionVO);
		
		return dao.findByPrimaryKey(fx_no);
	}
	
	// prepare for Struts2
	public void updateFunction(FunctionVO functionVO) {
		dao.update(functionVO);
	}
	
	public void deleteFunction(Integer fx_no) {
		dao.delete(fx_no);
	}

	public FunctionVO getOneFunction(Integer fx_no) {
		return dao.findByPrimaryKey(fx_no);
	}

	public List<FunctionVO> getAll() {
		return dao.getAll();
	}

}
