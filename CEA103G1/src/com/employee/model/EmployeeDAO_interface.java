package com.employee.model;

import java.sql.Date;
import java.util.List;

import com.announcement.model.AnnouncementVO;

public interface EmployeeDAO_interface {
	public void insert(EmployeeVO employeeVO);
    public void update(EmployeeVO employeeVO);
    public void delete(Integer emp_no);
    public EmployeeVO findByPrimaryKey(Integer emp_no);
    public List<EmployeeVO> getAll();
    public List<EmployeeVO> getNameEmp_no(String name);
    public List<EmployeeVO> getFunctionEmp_no(Integer fx_no);
}
