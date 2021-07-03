package com.service_mail.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public Set<Service_mailVO> getWhereCondition(Map<String,String[]> map){
/*		Set<Service_mailVO> set = new LinkedHashSet<Service_mailVO>();
		StringBuffer partOfsqlWhere = new StringBuffer();
		Service_mailVO service_mailVO = null;
		Set<String> keys = map.keySet();
		
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs_p = null;
		try {
			//建立連線
			con = ds.getConnection();
			stmt = con.createStatement();
			//取得所有欄位名稱
			rs = stmt.executeQuery("select * from campion.service_mail");
			ResultSetMetaData rm = rs.getMetaData();
			
			//建立sql指令
			List<String> checkfirst = new ArrayList<String>();
			Map<Integer,String> forPstmt = new LinkedHashMap<Integer,String>();//之後連到資料庫查找用
			//獲得所有欄位名稱
			for(int i = 1 ; i <= rm.getColumnCount(); i++) {
				nextColumn:
				//屬於特定sql型別做特定動作
				for(String key : keys) {
					if(partOfsqlWhere.length() == 0) {
						if(rm.getColumnTypeName(i) == "INT" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
							partOfsqlWhere.append("select * from "+rm.getTableName(i)+" where "+ rm.getColumnName(i) +" = ?");
							checkfirst.add(key);
							forPstmt.put(i, key);
							break nextColumn;
						}else if(rm.getColumnTypeName(i) == "BIT" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
							partOfsqlWhere.append("select * from "+rm.getTableName(i)+" where "+ rm.getColumnName(i) +" = ?");
							checkfirst.add(key);
							forPstmt.put(i, key);
							break nextColumn;
						}else if(rm.getColumnTypeName(i) == "VARCHAR" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
							partOfsqlWhere.append("select * from "+rm.getTableName(i)+" where "+ rm.getColumnName(i) +" like ?");
							checkfirst.add(key);
							forPstmt.put(i, key);
							break nextColumn;
						}else if(rm.getColumnTypeName(i) == "DATETIME" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
							partOfsqlWhere.append("select * from "+rm.getTableName(i)+" where "+ rm.getColumnName(i) +" like ?");
							checkfirst.add(key);
							forPstmt.put(i, key);
							break nextColumn;
						}
					}else {
						if(rm.getColumnTypeName(i) == "INT" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
							partOfsqlWhere.append(" and "+ rm.getColumnName(i) +" = ?");
							checkfirst.add(key);
							forPstmt.put(i, key);
							break nextColumn;
						}if(rm.getColumnTypeName(i) == "BIT" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
							partOfsqlWhere.append(" and "+ rm.getColumnName(i) +" = ?");
							checkfirst.add(key);
							forPstmt.put(i, key);
							break nextColumn;
						}else if(rm.getColumnTypeName(i) == "VARCHAR" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
							partOfsqlWhere.append(" and "+ rm.getColumnName(i) +" like ?");
							checkfirst.add(key);
							forPstmt.put(i, key);
							break nextColumn;
						}else if(rm.getColumnTypeName(i) == "DATETIME" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
							partOfsqlWhere.append(" and "+ rm.getColumnName(i) +" like ?");
							checkfirst.add(key);
							forPstmt.put(i, key);
							break nextColumn;
						}
					}
				}
			}
			//問號放入對應的值
			pstmt = con.prepareStatement(partOfsqlWhere.toString()+" order by mail_time desc");
			Set<Integer> keysPstmt = forPstmt.keySet();//上一個步驟存的資料
			
			int index = 1;
			for(Integer keyPstmt : keysPstmt) {
				if(rm.getColumnTypeName(keyPstmt) == "INT") {
					pstmt.setInt(index, Integer.valueOf(map.get(forPstmt.get(keyPstmt))[0]));
					index++;
				}else if(rm.getColumnTypeName(keyPstmt) == "BIT") {
					pstmt.setInt(index, Integer.valueOf(map.get(forPstmt.get(keyPstmt))[0]));
					index++;
				}else if(rm.getColumnTypeName(keyPstmt) == "VARCHAR") {
					pstmt.setString(index, "%"+map.get(forPstmt.get(keyPstmt))[0]+"%");
					index++;
				}else if(rm.getColumnTypeName(keyPstmt) == "DATETIME") {
					pstmt.setString(index, map.get(forPstmt.get(keyPstmt))[0]+"%");
					index++;
				}
			}
			
			rs_p = pstmt.executeQuery();
			
			while (rs_p.next()) {
				service_mailVO = new Service_mailVO();
				service_mailVO.setMail_no(rs_p.getInt("mail_no"));
				service_mailVO.setEmp_no(rs_p.getInt("emp_no"));
				service_mailVO.setMbr_no(rs_p.getInt("mbr_no"));
				service_mailVO.setMail_cont(rs_p.getString("mail_cont"));
				service_mailVO.setMail_stat(rs_p.getInt("mail_stat"));
				service_mailVO.setMail_read_stat(rs_p.getInt("mail_read_stat"));
				service_mailVO.setMail_time(rs_p.getString("mail_time"));
				set.add(service_mailVO); // Store the row in the list
			}
			
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs_p != null) {
				try {
					rs_p.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return set;
*/		return null;
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
