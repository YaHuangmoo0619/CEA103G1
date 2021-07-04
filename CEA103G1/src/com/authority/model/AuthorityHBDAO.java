package com.authority.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AuthorityHBDAO implements AuthorityDAO_interface{

	private static SessionFactory factory = null;
	static {
		factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(AuthorityVO.class)
				.buildSessionFactory();
	}
	
	@Override
	public void insert(AuthorityVO authorityVO) {
		
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			session.save(authorityVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
		}
	}
		
	@Override
	public void delete(Integer emp_no, Integer fx_no) {
		
		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			AuthorityVO authorityVO = session.createQuery("FROM AuthorityVO WHERE emp_no = " + emp_no + " and " + " fx_no = " + fx_no, AuthorityVO.class).getSingleResult();

			session.delete(authorityVO);
			
			session.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}
	
	@Override
	public AuthorityVO findByPrimaryKey(Integer emp_no, Integer fx_no) {

		AuthorityVO authorityVO = null;

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			authorityVO = session.createQuery("FROM AuthorityVO WHERE emp_no = " + emp_no + " AND " + " fx_no = " + fx_no, AuthorityVO.class).getSingleResult();
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
		
		return authorityVO;
	}
	@Override
	public List<AuthorityVO> getAll() {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			list = session.createQuery("FROM AuthorityVO", AuthorityVO.class).getResultList();

			session.getTransaction().commit();

		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
		
		return list;
	}
	@Override
	public List<AuthorityVO> findByEmp_no(Integer emp_no) {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			list = session.createQuery("FROM AuthorityVO WHERE emp_no = " + emp_no, AuthorityVO.class).getResultList();

			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}

		return list;
	}
	@Override
	public List<AuthorityVO> findByFx_no(Integer fx_no) {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			list = session.createQuery("FROM AuthorityVO WHERE fx_no = " + fx_no, AuthorityVO.class).getResultList();

			session.getTransaction().commit();
			
		} catch (Exception e) {

			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
		
		return list;
	}
}
