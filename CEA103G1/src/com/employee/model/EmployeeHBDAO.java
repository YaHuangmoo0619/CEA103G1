package com.employee.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.function.model.FunctionVO;

public class EmployeeHBDAO implements EmployeeDAO_interface {

	private static SessionFactory factory = null;
	static {
		factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(EmployeeVO.class)
				.addAnnotatedClass(FunctionVO.class)
				.buildSessionFactory();
	}

	@Override
	public void insert(EmployeeVO employeeVO) {

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			employeeVO.setEmp_stat(0);
			
			session.save(employeeVO);
			
			session.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}

	}

	@Override
	public void update(EmployeeVO employeeVO) {

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			session.update(employeeVO);

			session.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}

	@Override
	public void delete(Integer emp_no) {

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			EmployeeVO employeeVO = session.get(EmployeeVO.class, emp_no);

			session.delete(employeeVO);

			session.getTransaction().commit();
			
		} catch (Exception e) {

			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}

	@Override
	public EmployeeVO findByPrimaryKey(Integer emp_no) {

		EmployeeVO employeeVO = null;

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			employeeVO = session.get(EmployeeVO.class, emp_no);

			session.getTransaction().commit();
			
			if(employeeVO != null) {
				employeeVO.setPwd(showPwdformDatabase(employeeVO.getPwd()));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}

		return employeeVO;
	}

	@Override
	public List<EmployeeVO> getAll() {

		List<EmployeeVO> list = new LinkedList<EmployeeVO>();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			List<EmployeeVO> listArray = session.createQuery("FROM EmployeeVO", EmployeeVO.class).getResultList();
			
			session.getTransaction().commit();
			
			for(EmployeeVO employeeVO : listArray) {
				employeeVO.setPwd(showPwdformDatabase(employeeVO.getPwd()));
				list.add(employeeVO);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}

		return list;
	}

	@Override
	public List<EmployeeVO> getNameEmp_no(String name) {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			list = session.createQuery("FROM EmployeeVO WHERE name = " + name, EmployeeVO.class).getResultList();

			session.getTransaction().commit();
			
			for(EmployeeVO employeeVO : list) {
				employeeVO.setPwd(showPwdformDatabase(employeeVO.getPwd()));
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}

		return list;
	}

	@Override
	public List<EmployeeVO> getFunctionEmp_no(Integer fx_no) {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			FunctionVO functionVO = session.get(FunctionVO.class, fx_no);
			
			list = functionVO.getEmployeeVO();

			session.getTransaction().commit();
			
			for(EmployeeVO employeeVO : list) {
				employeeVO.setPwd(showPwdformDatabase(employeeVO.getPwd()));
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}

		return list;
	}

	@Override
	public EmployeeVO findForLogin(String acc, String pwd) {
		EmployeeVO employeeVO = null;
		
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			employeeVO = session.createQuery("FROM EmployeeVO WHERE CAST(acc as binary) = CAST('" + acc + "' as binary) AND CAST(pwd as binary) = CAST('" + pwd + "' as binary)", EmployeeVO.class).getSingleResult();

			session.getTransaction().commit();
			
			if(employeeVO != null) {
				employeeVO.setPwd(showPwdformDatabase(pwd));
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}

		return employeeVO;
	}
	
	// show the correct password
	public String showPwdformDatabase(String newPwd) {
		char[] forShow = newPwd.toCharArray();
		char[] gobackCode = new char[newPwd.length()];
		for (int i = 0; i < newPwd.length(); i++) {
			if (i < 2) {
				gobackCode[i + ((newPwd.length() - 2))] = forShow[i];
			} else {
				gobackCode[i - 2] = forShow[i];
			}
		}
		String showPwd = String.valueOf(gobackCode);
		return showPwd;
	}

}
