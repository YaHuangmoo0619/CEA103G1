package com.function.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.employee.model.EmployeeVO;

public class FunctionHBDAO implements FunctionDAO_interface {

	
	private static SessionFactory factory = null;
	static {
		factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(FunctionVO.class)
				.addAnnotatedClass(EmployeeVO.class)
				.buildSessionFactory();
	}

	@Override
	public void insert(FunctionVO functionVO) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.save(functionVO);
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}	
	}

	@Override
	public void update(FunctionVO functionVO) {
		
		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			session.update(functionVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {

			e.printStackTrace();
			
			session.getTransaction().rollback();
		}
	}

	@Override
	public void delete(Integer fx_no) {
		
		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			FunctionVO functionVO = session.get(FunctionVO.class, fx_no);
			
			session.delete(functionVO);

			session.getTransaction().rollback();
			
		} catch (Exception e) {

			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}

	@Override
	public FunctionVO findByPrimaryKey(Integer fx_no) {
		
		FunctionVO functionVO = null;

		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();

			functionVO = session.get(FunctionVO.class, fx_no);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}

		return functionVO;
	}

	@Override
	public List<FunctionVO> getAll() {
		List<FunctionVO> list = new ArrayList<FunctionVO>();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			list = session.createQuery("FROM FunctionVO", FunctionVO.class).getResultList();
			
			session.getTransaction().commit();
			
		} catch (Exception e) {

			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}

		return list;
	}	
}
