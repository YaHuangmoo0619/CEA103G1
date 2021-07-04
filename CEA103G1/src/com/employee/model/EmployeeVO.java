package com.employee.model;

import java.io.Serializable;
import java.util.ArrayList;
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

import com.function.model.FunctionVO;

@Entity
@Table(name="employee")
public class EmployeeVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emp_no")
	private Integer emp_no;
	
	@Column(name="acc")
	private String acc;
	
	@Column(name="pwd")
	private String pwd;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="emp_stat")
	private Integer emp_stat;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.DETACH, CascadeType.MERGE, 
					CascadeType.PERSIST, CascadeType.REFRESH,})
	@JoinTable(
			name="authority",
			joinColumns=@JoinColumn(name="emp_no"),
			inverseJoinColumns=@JoinColumn(name="fx_no"))
	private List<FunctionVO> functionVO;

	public EmployeeVO() {
		
	}

	public EmployeeVO(Integer emp_no, String acc, String pwd, String name, String email, Integer emp_stat) {
		this.emp_no = emp_no;
		this.acc = acc;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.emp_stat = emp_stat;
	}
	
	public EmployeeVO(String acc, String pwd, String name) {
		this.acc = acc;
		this.pwd = pwd;
		this.name = name;
	}

	public Integer getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEmp_stat() {
		return emp_stat;
	}

	public void setEmp_stat(Integer emp_stat) {
		this.emp_stat = emp_stat;
	}

	public List<FunctionVO> getFunctionVO() {
		return functionVO;
	}

	public void setFunctionVO(List<FunctionVO> functionVO) {
		this.functionVO = functionVO;
	}
	
	public void add(FunctionVO functionVO2) {
		
		if(functionVO == null) {
			functionVO = new ArrayList<>();
		}
		
		functionVO.add(functionVO2);
		
	}
}
