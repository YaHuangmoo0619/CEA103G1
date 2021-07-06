package com.service_mail.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.campsite_owner_mail_picture.model.Campsite_owner_mail_pictureHBVO;
import com.member_mail.model.Member_mailHBDAO;
import com.member_mail.model.Member_mailVO;
import com.member_mail_picture.model.Member_mail_pictureHBVO;
import com.service_mail_picture.model.Service_mail_pictureHBVO;


public class Service_mailHBDAO implements Service_mailHBDAO_interface {

		private static SessionFactory factory = null;
		static {
			
				factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Service_mailVO.class)
						.addAnnotatedClass(Service_mail_pictureHBVO.class)
						.buildSessionFactory();
		}

	
	@Override
	public void insert(Service_mailVO service_mailVO) {

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			session.save(service_mailVO);

			Member_mailHBDAO member_mailHBDAO = new Member_mailHBDAO();
			Integer send_no = service_mailVO.getEmp_no();
			Integer rcpt_no = service_mailVO.getMbr_no();
			Integer mail_read_stat = 0;
			Integer mail_stat = 0;
			String mail_cont = service_mailVO.getMail_cont();
			String mail_time = service_mailVO.getMail_time();
			Member_mailVO member_mailVO = new Member_mailVO(send_no, rcpt_no, mail_read_stat, mail_stat, mail_cont, mail_time); 
			member_mailHBDAO.insertWithSvc(member_mailVO);

			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
		} 

	}

	@Override
	public void update(Service_mailVO service_mailVO) {

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			session.update(service_mailVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {

			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}

	}

	@Override
	public void delete(Integer mail_no) {

		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			Service_mailVO service_mailVO = session.get(Service_mailVO.class, mail_no);
			
			session.delete(service_mailVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {

			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}

	}

	@Override
	public Service_mailVO findByPrimaryKey(Integer mail_no) {

		Service_mailVO service_mailVO = null;
		
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			service_mailVO = session.get(Service_mailVO.class, mail_no);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
		
		}
		
		return service_mailVO;
	}

	@Override
	public List<Service_mailVO> getAll() {
		List<Service_mailVO> list = new ArrayList<Service_mailVO>();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			list = session.createQuery("from Service_mailVO order by mail_time desc", Service_mailVO.class).getResultList();
			
			session.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
		
		return list;
	}

	public Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<Service_mailVO> root, String columnName, String value) {
		
		Predicate predicate = null;
		
		if("send_no".equals(columnName) || "rcpt_no".equals(columnName) || "mail_read_stat".equals(columnName) || "mail_stat".equals(columnName))
			predicate = builder.equal(root.get(columnName), new Integer(value));
		else if("mail_cont".equals(columnName))
			predicate = builder.like(root.get(columnName), "%" + value + "%");
		else if("mail_time".equals(columnName))
			predicate = builder.equal(root.get(columnName), java.sql.Date.valueOf(value));
	
		return predicate;
	}
	
	public Set<Service_mailVO> getWhereCondition(Map<String,String[]> map){
		
		Set<Service_mailVO> set = new LinkedHashSet<Service_mailVO>();
		
		Session session = factory.getCurrentSession();
		
		List<Service_mailVO> list = null;
		
		try {
			
			session.beginTransaction();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			
			CriteriaQuery<Service_mailVO> criteriaQuery = builder.createQuery(Service_mailVO.class);
			
			Root<Service_mailVO> root = criteriaQuery.from(Service_mailVO.class);
			
			List<Predicate> predicateList = new ArrayList<Predicate>();
			
			Set<String> keys = map.keySet();
			for(String key : keys) {
				
				String value = map.get(key)[0];
				
				if(value != null && value.trim().length() != 0 && !value.equals("no")) {
					Predicate predicate = get_aPredicate_For_AnyDB(builder, root, key, value);
					if(predicate != null)
						predicateList.add(predicate);
				}
				
			}
			
			criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
			criteriaQuery.orderBy(builder.desc(root.get("mail_time")));
			
			Query query = session.createQuery(criteriaQuery);
			
			list = query.getResultList();
			
			for(Service_mailVO service_mailVO : list) {
				set.add(service_mailVO);
			}
			
			session.getTransaction().commit();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
		
		return set;
	}

	
	@Override
	public void insertWithMbr (Service_mailVO service_mailVO, Set<Member_mail_pictureHBVO> set) {

		Session session = null;
		
		try {

			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			Set<Service_mail_pictureHBVO> setSvc = new LinkedHashSet<Service_mail_pictureHBVO>();
			for(Member_mail_pictureHBVO member_mail_pictureHBVO : set) {
				Service_mail_pictureHBVO service_mail_pictureHBVO = new Service_mail_pictureHBVO();
				service_mail_pictureHBVO.setMail_pic(member_mail_pictureHBVO.getMail_pic());
				setSvc.add(service_mail_pictureHBVO);
			}
			
			for(Service_mail_pictureHBVO service_mail_pictureHBVO : setSvc) {
				service_mailVO.add(service_mail_pictureHBVO);
			}
			
			session.save(service_mailVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}		

	@Override
	public void insertWithMbr(Service_mailVO service_mailVO) {

		Session session = null;
		
		try {

			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			session.save(service_mailVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}

	@Override
	public void insertWithCOwner(Service_mailVO service_mailVO, Set<Campsite_owner_mail_pictureHBVO> set) {

		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			Set<Service_mail_pictureHBVO> setSvc = new LinkedHashSet<Service_mail_pictureHBVO>();
			for(Campsite_owner_mail_pictureHBVO campsite_owner_mail_pictureHBVO : set) {
				Service_mail_pictureHBVO service_mail_pictureHBVO = new Service_mail_pictureHBVO();
				service_mail_pictureHBVO.setMail_pic(campsite_owner_mail_pictureHBVO.getMail_pic());
				setSvc.add(service_mail_pictureHBVO);
			}
			
			for(Service_mail_pictureHBVO service_mail_pictureHBVO : setSvc) {
				service_mailVO.add(service_mail_pictureHBVO);
			}
			
			session.save(service_mailVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}

	@Override
	public void insertWithCOwner(Service_mailVO service_mailVO) {

		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			session.save(service_mailVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}

	@Override
	public void insertWithPic(Service_mailVO service_mailVO, Set<Service_mail_pictureHBVO> set) {
		
		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			for(Service_mail_pictureHBVO service_mail_pictureHBVO : set) {
				service_mailVO.add(service_mail_pictureHBVO);
			}
			
			session.save(service_mailVO);
			
			Member_mailHBDAO member_mailHBDAO = new Member_mailHBDAO();
			Integer send_no = service_mailVO.getEmp_no();
			Integer rcpt_no = service_mailVO.getMbr_no();
			Integer mail_read_stat = 0;
			Integer mail_stat = 0;
			String mail_cont = service_mailVO.getMail_cont();
			String mail_time = service_mailVO.getMail_time();
			Member_mailVO member_mailVO = new Member_mailVO(send_no, rcpt_no, mail_read_stat, mail_stat, mail_cont, mail_time); 
			member_mailHBDAO.insertWithSvc(member_mailVO, set);
			
			session.getTransaction().commit();
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}
}
