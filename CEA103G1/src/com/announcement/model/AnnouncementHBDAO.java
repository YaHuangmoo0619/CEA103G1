package com.announcement.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AnnouncementHBDAO implements AnnouncementDAO_interface {

	private static SessionFactory factory = null;
	static {
		factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(AnnouncementVO.class)
				.buildSessionFactory();
	}
	
	
	@Override
	public void insert(AnnouncementVO announcementVO) {

		Session session = factory.getCurrentSession();
		
		try {
		
			session.beginTransaction();
			
			session.save(announcementVO);
			
			session.getTransaction().commit();
			
		}catch(Exception e) {
			e.getStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public void update(AnnouncementVO announcementVO) {

		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.update(announcementVO);
			
			session.getTransaction().commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public void delete(Integer an_no) {

		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			AnnouncementVO announcementVO = session.get(AnnouncementVO.class, an_no);
			
			session.delete(announcementVO);
			
			session.getTransaction().commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public AnnouncementVO findByPrimaryKey(Integer an_no) {

		AnnouncementVO announcementVO = null;
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			announcementVO = session.get(AnnouncementVO.class, an_no);
			
			session.getTransaction().commit();
		}catch(Exception e) {
			e.getStackTrace();
			session.getTransaction().rollback();
		}
		
		return announcementVO;
	}

	@Override
	public List<AnnouncementVO> getAll() {
		
		List<AnnouncementVO> list = new ArrayList<>();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			list = session.createQuery("from AnnouncementVO order by an_no desc" , AnnouncementVO.class).getResultList();
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return list;
	}

	@Override
	public List<AnnouncementVO> getAlltoShow() {

		List<AnnouncementVO> list = new ArrayList<>();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			list = session.createQuery("FROM AnnouncementVO where DATEDIFF(an_skd_date , sysdate()) <= 0 order by an_no desc" , AnnouncementVO.class).getResultList();
			
			session.getTransaction().commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return list;
	}

	@Override
	public List<AnnouncementVO> getDateEmp_no(Date an_skd_date) {

		List<AnnouncementVO> list = new ArrayList<>();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			list = session.createQuery("From AnnouncementVO WHERE an_skd_date = " + an_skd_date + " order by an_no desc", AnnouncementVO.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return null;
	}
}
