package com.employee.model;

import java.util.List;

public class EmployeeService {
	
	private EmployeeDAO_interface dao;
	
	public EmployeeService() {
		dao = new EmployeeDAO();
	}
	
	public EmployeeVO addEmployee(String acc, String pwd, String name, String email) {
		
		EmployeeVO employeeVO = new EmployeeVO();
		
		employeeVO.setAcc(acc);
		employeeVO.setPwd(pwd);
		employeeVO.setName(name);
		employeeVO.setEmail(email);
		dao.insert(employeeVO);//package attributes to VO, and then use insert(...VO) from DAO to insert data into table
		
		return employeeVO;// no return is OK, but has return may be can do more things
	}
	
	// prepare for Struts2
	public void addEmployee(EmployeeVO employeeVO) {
		dao.insert(employeeVO);
	}
	
	public EmployeeVO updateEmployee(Integer emp_no, String acc, String pwd, String name, String email, Integer emp_state) {
		
		EmployeeVO employeeVO = new EmployeeVO();
		
		
		employeeVO.setEmp_no(emp_no);
		employeeVO.setAcc(acc);
		employeeVO.setPwd(pwd);
		employeeVO.setName(name);
		employeeVO.setEmail(email);
		employeeVO.setEmp_stat(emp_state);
		dao.update(employeeVO);
		
		return dao.findByPrimaryKey(emp_no);
	}
	
	// prepare for Struts2
	public void updateEmployee(EmployeeVO employeeVO) {
		dao.update(employeeVO);
	}
	
	public void deleteEmployee(Integer emp_no) {
		dao.delete(emp_no);
	}

	public EmployeeVO getOneEmployee(Integer emp_no) {
		return dao.findByPrimaryKey(emp_no);
	}

	public EmployeeVO findForLogin(String acc, String pwd) {
		return dao.findForLogin(acc, pwd);
	}
	
	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}
	
	public List<EmployeeVO> getNameEmp_no(String name) {
		return dao.getNameEmp_no(name);
	}
	
	public List<EmployeeVO> getFunctionEmp_no(Integer fx_no) {
		return dao.getFunctionEmp_no(fx_no);
	}

}
