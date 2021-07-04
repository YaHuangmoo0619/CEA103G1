package com.function.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.employee.model.EmployeeVO;

@Entity
@Table(name="`function`")
public class FunctionVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="fx_no")
	private Integer fx_no;
	
	@Column(name="fx_name")
	private String fx_name;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.DETACH, CascadeType.MERGE,
					CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name="authority",
			joinColumns=@JoinColumn(name="fx_no"),
			inverseJoinColumns=@JoinColumn(name="emp_no"))
	private List<EmployeeVO> employeeVO;
	
	public FunctionVO() {
	}
	
	public FunctionVO(Integer fx_no, String fx_name) {
		this.fx_no = fx_no;
		this.fx_name = fx_name;
	}

	public Integer getFx_no() {
		return fx_no;
	}

	public void setFx_no(Integer fx_no) {
		this.fx_no = fx_no;
	}

	public String getFx_name() {
		return fx_name;
	}

	public void setFx_name(String fx_name) {
		this.fx_name = fx_name;
	}

	public List<EmployeeVO> getEmployeeVO() {
		return employeeVO;
	}

	public void setEmployeeVO(List<EmployeeVO> employeeVO) {
		this.employeeVO = employeeVO;
	}
	
	
}
