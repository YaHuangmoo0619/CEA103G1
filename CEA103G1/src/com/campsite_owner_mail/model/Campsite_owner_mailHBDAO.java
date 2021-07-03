package com.campsite_owner_mail.model;

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
import com.service_mail.model.Service_mailHBDAO;
import com.service_mail.model.Service_mailVO;
import com.service_mail_picture.model.Service_mail_pictureHBVO;

public class Campsite_owner_mailHBDAO implements Campsite_owner_mailHBDAO_interface {
	
	private static SessionFactory factory = null;
	static {
		factory = new Configuration()
				.configure()
				.addAnnotatedClass(Campsite_owner_mailVO.class)
				.addAnnotatedClass(Campsite_owner_mail_pictureHBVO.class)
				.buildSessionFactory();
	}

	@Override
	public void insert(Campsite_owner_mailVO campsite_owner_mailVO) {

		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			session.save(campsite_owner_mailVO);

			if(campsite_owner_mailVO.getRcpt_no().toString().substring(0,1).equals("9")) {
				Service_mailHBDAO service_mailHBDAO = new Service_mailHBDAO();
				Integer mbr_no = campsite_owner_mailVO.getSend_no();
				Integer emp_no = campsite_owner_mailVO.getRcpt_no();
				Integer mail_read_stat = 0;
				Integer mail_stat = 0;
				String mail_cont = campsite_owner_mailVO.getMail_cont();
				String mail_time = campsite_owner_mailVO.getMail_time();
				Service_mailVO service_mailVO = new Service_mailVO(emp_no, mbr_no, mail_cont, mail_stat, mail_read_stat, mail_time); 
				service_mailHBDAO.insertWithCOwner(service_mailVO);
			}else if(campsite_owner_mailVO.getRcpt_no().toString().substring(0,1).equals("1")) {
				Member_mailHBDAO member_mailHBDAO = new Member_mailHBDAO();
				Integer send_no = campsite_owner_mailVO.getSend_no();
				Integer rcpt_no = campsite_owner_mailVO.getRcpt_no();
				Integer mail_read_stat = 0;
				Integer mail_stat = 0;
				String mail_cont = campsite_owner_mailVO.getMail_cont();
				String mail_time = campsite_owner_mailVO.getMail_time();
				Member_mailVO member_mailVO = new Member_mailVO(send_no, rcpt_no, mail_read_stat, mail_stat, mail_cont, mail_time); 
				member_mailHBDAO.insertWithCOwner(member_mailVO);
			}
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}

	}

	@Override
	public void update(Campsite_owner_mailVO campsite_owner_mailVO) {

		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			session.update(campsite_owner_mailVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().commit();
			
		}

	}

	@Override
	public void delete(Integer mail_no) {

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			Campsite_owner_mailVO campsite_owner_mailVO = session.get(Campsite_owner_mailVO.class, mail_no);
			
			session.delete(campsite_owner_mailVO);
			
			session.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();
			
			session.getTransaction().commit();
			
		}
	}

	@Override
	public Campsite_owner_mailVO findByPrimaryKey(Integer mail_no) {

		Campsite_owner_mailVO campsite_owner_mailVO = null;
		
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			campsite_owner_mailVO = session.get(Campsite_owner_mailVO.class, mail_no);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		} 
		
		return campsite_owner_mailVO;
	}


	@Override
	public List<Campsite_owner_mailVO> getAll() {
		List<Campsite_owner_mailVO> list = new ArrayList<Campsite_owner_mailVO>();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			list = session.createQuery("from Campsite_owner_mailVO", Campsite_owner_mailVO.class).getResultList();
			
			session.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
		
		return list;
	}

	public Set<Campsite_owner_mailVO> getWhereCondition(Map<String,String[]> map){
//		Set<Campsite_owner_mailVO> set = new LinkedHashSet<Campsite_owner_mailVO>();
//		StringBuffer partOfsqlWhere = new StringBuffer();
//		Campsite_owner_mailVO campsite_owner_mailVO = null;
//		Set<String> keys = map.keySet();
////		System.out.print("in");
//		Connection con = null;
//		Statement stmt = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		ResultSet rs_p = null;
//		try {
//			//建立連線
//			con = ds.getConnection();
//			stmt = con.createStatement();
//			//取得所有欄位名稱
//			rs = stmt.executeQuery("select * from campion.campsite_owner_mail");
//			ResultSetMetaData rm = rs.getMetaData();
//			
//			//建立sql指令
//			List<String> checkfirst = new ArrayList<String>();
//			Map<Integer,String> forPstmt = new LinkedHashMap<Integer,String>();//之後連到資料庫查找用
//			//獲得所有欄位名稱
//			for(int i = 1 ; i <= rm.getColumnCount(); i++) {
//				nextColumn:
//				//屬於特定sql型別做特定動作
//				for(String key : keys) {
////					System.out.println(key);
//					if(partOfsqlWhere.length() == 0) {
//						if(rm.getColumnTypeName(i) == "INT" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
//							partOfsqlWhere.append("select * from "+rm.getTableName(i)+" where "+ rm.getColumnName(i) +" = ?");
//							checkfirst.add(key);
//							forPstmt.put(i, key);
//							break nextColumn;
//						}else if(rm.getColumnTypeName(i) == "BIT" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
//							partOfsqlWhere.append("select * from "+rm.getTableName(i)+" where "+ rm.getColumnName(i) +" = ?");
//							checkfirst.add(key);
//							forPstmt.put(i, key);
//							break nextColumn;
//						}else if(rm.getColumnTypeName(i) == "VARCHAR" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
//							partOfsqlWhere.append("select * from "+rm.getTableName(i)+" where "+ rm.getColumnName(i) +" like ?");
//							checkfirst.add(key);
//							forPstmt.put(i, key);
//							break nextColumn;
//						}else if(rm.getColumnTypeName(i) == "DATETIME" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
//							partOfsqlWhere.append("select * from "+rm.getTableName(i)+" where "+ rm.getColumnName(i) +" like ?");
//							checkfirst.add(key);
//							forPstmt.put(i, key);
//							break nextColumn;
//						}
//					}else {
//						if(rm.getColumnTypeName(i) == "INT" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
//							partOfsqlWhere.append(" and "+ rm.getColumnName(i) +" = ?");
//							checkfirst.add(key);
//							forPstmt.put(i, key);
//							break nextColumn;
//						}if(rm.getColumnTypeName(i) == "BIT" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
//							partOfsqlWhere.append(" and "+ rm.getColumnName(i) +" = ?");
//							checkfirst.add(key);
//							forPstmt.put(i, key);
//							break nextColumn;
//						}else if(rm.getColumnTypeName(i) == "VARCHAR" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
//							partOfsqlWhere.append(" and "+ rm.getColumnName(i) +" like ?");
//							checkfirst.add(key);
//							forPstmt.put(i, key);
//							break nextColumn;
//						}else if(rm.getColumnTypeName(i) == "DATETIME" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
//							partOfsqlWhere.append(" and "+ rm.getColumnName(i) +" like ?");
//							checkfirst.add(key);
//							forPstmt.put(i, key);
//							break nextColumn;
//						}
//					}
//				}
//			}
//			//問號放入對應的值
//			System.out.print(partOfsqlWhere.toString());
//			pstmt = con.prepareStatement(partOfsqlWhere.toString());
//			Set<Integer> keysPstmt = forPstmt.keySet();//上一個步驟存的資料
//			
//			int index = 1;
//			for(Integer keyPstmt : keysPstmt) {
//				if(rm.getColumnTypeName(keyPstmt) == "INT") {
//					pstmt.setInt(index, Integer.valueOf(map.get(forPstmt.get(keyPstmt))[0]));
//					index++;
//				}else if(rm.getColumnTypeName(keyPstmt) == "BIT") {
//					pstmt.setInt(index, Integer.valueOf(map.get(forPstmt.get(keyPstmt))[0]));
//					index++;
//				}else if(rm.getColumnTypeName(keyPstmt) == "VARCHAR") {
////					System.out.print("%"+map.get(forPstmt.get(keyPstmt))[0]+"%");
//					pstmt.setString(index, "%"+map.get(forPstmt.get(keyPstmt))[0]+"%");
//					index++;
//				}else if(rm.getColumnTypeName(keyPstmt) == "DATETIME") {
//					pstmt.setString(index, map.get(forPstmt.get(keyPstmt))[0]+"%");
//					index++;
//				}
//			}
//			
//			rs_p = pstmt.executeQuery();
//			
//			while (rs_p.next()) {
//				campsite_owner_mailVO = new Campsite_owner_mailVO();
////				System.out.print(rs_p.getString("mail_cont"));
//				campsite_owner_mailVO.setMail_no(rs_p.getInt("mail_no"));
//				campsite_owner_mailVO.setSend_no(rs_p.getInt("send_no"));
//				campsite_owner_mailVO.setRcpt_no(rs_p.getInt("rcpt_no"));
//				campsite_owner_mailVO.setMail_cont(rs_p.getString("mail_cont"));
//				campsite_owner_mailVO.setMail_stat(rs_p.getInt("mail_stat"));
//				campsite_owner_mailVO.setMail_read_stat(rs_p.getInt("mail_read_stat"));
//				campsite_owner_mailVO.setMail_time(rs_p.getString("mail_time"));
//				set.add(campsite_owner_mailVO); // Store the row in the list
//			}
//			
//			
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs_p != null) {
//				try {
//					rs_p.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return set;
		return null;
	}
	
	@Override
	public void insertWithSvc (Campsite_owner_mailVO campsite_owner_mailVO, Set<Service_mail_pictureHBVO> set) {

		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			Set<Campsite_owner_mail_pictureHBVO> setCOwner = new LinkedHashSet<Campsite_owner_mail_pictureHBVO>();
			for(Service_mail_pictureHBVO service_mail_pictureHBVO : set) {
				Campsite_owner_mail_pictureHBVO campsite_owner_mail_pictureHBVO = new Campsite_owner_mail_pictureHBVO();
				campsite_owner_mail_pictureHBVO.setMail_pic(service_mail_pictureHBVO.getMail_pic());
				setCOwner.add(campsite_owner_mail_pictureHBVO);
			}
			
			for(Campsite_owner_mail_pictureHBVO campsite_owner_mail_pictureHBVO : setCOwner) {
				campsite_owner_mailVO.add(campsite_owner_mail_pictureHBVO);
			}
			
			session.save(campsite_owner_mailVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}
	
	@Override
	public void insertWithSvc (Campsite_owner_mailVO campsite_owner_mailVO) {

		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			session.save(campsite_owner_mailVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}
	
	@Override
	public void insertWithMbr (Campsite_owner_mailVO campsite_owner_mailVO, Set<Member_mail_pictureHBVO> set) {

		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			Set<Campsite_owner_mail_pictureHBVO> setCOwner = new LinkedHashSet<Campsite_owner_mail_pictureHBVO>();
			for(Member_mail_pictureHBVO member_mail_pictureHBVO : set) {
				Campsite_owner_mail_pictureHBVO campsite_owner_mail_pictureHBVO = new Campsite_owner_mail_pictureHBVO();
				campsite_owner_mail_pictureHBVO.setMail_pic(member_mail_pictureHBVO.getMail_pic());
				setCOwner.add(campsite_owner_mail_pictureHBVO);
			}
			
			for(Campsite_owner_mail_pictureHBVO campsite_owner_mail_pictureHBVO : setCOwner) {

				campsite_owner_mailVO.add(campsite_owner_mail_pictureHBVO);
				
			}
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}
	
	@Override
	public void insertWithMbr (Campsite_owner_mailVO campsite_owner_mailVO) {

		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			session.save(campsite_owner_mailVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}
}
