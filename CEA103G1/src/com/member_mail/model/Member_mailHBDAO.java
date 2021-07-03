package com.member_mail.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.campsite_owner_mail.model.Campsite_owner_mailHBDAO;
import com.campsite_owner_mail.model.Campsite_owner_mailVO;
import com.campsite_owner_mail_picture.model.Campsite_owner_mail_pictureHBVO;
import com.member_mail_picture.model.Member_mail_pictureHBVO;
import com.service_mail.model.Service_mailHBDAO;
import com.service_mail.model.Service_mailVO;
import com.service_mail_picture.model.Service_mail_pictureHBVO;


public class Member_mailHBDAO implements Member_mailHBDAO_interface {

	private static SessionFactory factory;
	static {
		factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Member_mailVO.class)
				.addAnnotatedClass(Member_mail_pictureHBVO.class)
				.buildSessionFactory();
	}

	
	@Override
	public void insert(Member_mailVO member_mailVO) {

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			session.save(member_mailVO);
			
			if(member_mailVO.getRcpt_no().toString().substring(0,1).equals("9")) {
				Service_mailHBDAO service_mailHBDAO = new Service_mailHBDAO();
				Integer mbr_no = member_mailVO.getSend_no();
				Integer emp_no = member_mailVO.getRcpt_no();
				Integer mail_read_stat = 0;
				Integer mail_stat = 0;
				String mail_cont = member_mailVO.getMail_cont();
				String mail_time = member_mailVO.getMail_time();
				Service_mailVO service_mailVO = new Service_mailVO(emp_no, mbr_no, mail_cont, mail_stat, mail_read_stat, mail_time); 
				service_mailHBDAO.insertWithMbr(service_mailVO);
			}else if(member_mailVO.getRcpt_no().toString().substring(0,1).equals("1")) {

				Integer send_no = member_mailVO.getSend_no();
				Integer rcpt_no = member_mailVO.getRcpt_no();
				Integer mail_read_stat = 0;
				Integer mail_stat = 0;
				String mail_cont = member_mailVO.getMail_cont();
				String mail_time = member_mailVO.getMail_time();
				Member_mailVO member_mailVORcpt = new Member_mailVO(send_no, rcpt_no, mail_read_stat, mail_stat, mail_cont, mail_time); 

				session.save(member_mailVORcpt);
			}else if(member_mailVO.getRcpt_no().toString().substring(0,1).equals("7")) {
				Campsite_owner_mailHBDAO campsite_owner_mailHBDAO = new Campsite_owner_mailHBDAO();
				Integer send_no = member_mailVO.getSend_no();
				Integer rcpt_no = member_mailVO.getRcpt_no();
				Integer mail_read_stat = 0;
				Integer mail_stat = 0;
				String mail_cont = member_mailVO.getMail_cont();
				String mail_time = member_mailVO.getMail_time();
				Campsite_owner_mailVO campsite_owner_mailVO = new Campsite_owner_mailVO(send_no, rcpt_no, mail_read_stat, mail_stat, mail_cont, mail_time); 
				campsite_owner_mailHBDAO.insertWithMbr(campsite_owner_mailVO);
			}
			
			session.getTransaction().commit();

		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}

	}


	@Override
	public void update(Member_mailVO member_mailVO) {

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			session.update(member_mailVO);
			
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
			
			Member_mailVO member_mailVO = session.get(Member_mailVO.class, mail_no);
			
			session.delete(member_mailVO);
			
			session.getTransaction();

		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction();
		
		} 

	}

	@Override
	public Member_mailVO findByPrimaryKey(Integer mail_no) {

		Member_mailVO member_mailVO = null;

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			member_mailVO = session.get(Member_mailVO.class, mail_no);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
		}
		
		return member_mailVO;
	}

	@Override
	public List<Member_mailVO> getAll() {
		List<Member_mailVO> list = new ArrayList<Member_mailVO>();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			list = session.createQuery("FROM Member_mailVO order by mail_time desc", Member_mailVO.class).getResultList();
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
		
		return list;
	}

	@Override
	public Set<Member_mailVO> getWhereCondition(Map<String,String[]> map){
/*		Set<Member_mailVO> set = new LinkedHashSet<Member_mailVO>();
		StringBuffer partOfsqlWhere = new StringBuffer();
		Member_mailVO member_mailVO = null;
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
			rs = stmt.executeQuery("select * from campion.member_mail");
			ResultSetMetaData rm = rs.getMetaData();
			
			//建立sql指令
			List<String> checkfirst = new ArrayList<String>();
			Map<Integer,String> forPstmt = new LinkedHashMap<Integer,String>();//之後連到資料庫查找用
			//獲得所有欄位名稱
			for(int i = 1 ; i <= rm.getColumnCount(); i++) {
				nextColumn:
				//屬於特定sql型別做特定動作
				for(String key : keys) {
//					System.out.println(key);
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
//			System.out.println(partOfsqlWhere.toString());
			pstmt = con.prepareStatement(partOfsqlWhere.toString());
			Set<Integer> keysPstmt = forPstmt.keySet();//上一個步驟存的資料
			
			int index = 1;
			for(Integer keyPstmt : keysPstmt) {
				if(rm.getColumnTypeName(keyPstmt) == "INT") {
//					System.out.println(Integer.valueOf(map.get(forPstmt.get(keyPstmt))[0]));
					pstmt.setInt(index, Integer.valueOf(map.get(forPstmt.get(keyPstmt))[0]));
					index++;
				}else if(rm.getColumnTypeName(keyPstmt) == "BIT") {
					pstmt.setInt(index, Integer.valueOf(map.get(forPstmt.get(keyPstmt))[0]));
					index++;
				}else if(rm.getColumnTypeName(keyPstmt) == "VARCHAR") {
//					System.out.println("%"+map.get(forPstmt.get(keyPstmt))[0]+"%");
					pstmt.setString(index, "%"+map.get(forPstmt.get(keyPstmt))[0]+"%");
					index++;
				}else if(rm.getColumnTypeName(keyPstmt) == "DATETIME") {
					pstmt.setString(index, map.get(forPstmt.get(keyPstmt))[0]+"%");
					index++;
				}
			}
			
			rs_p = pstmt.executeQuery();
			
			while (rs_p.next()) {
				member_mailVO = new Member_mailVO();
				member_mailVO.setMail_no(rs_p.getInt("mail_no"));
				member_mailVO.setSend_no(rs_p.getInt("send_no"));
				member_mailVO.setRcpt_no(rs_p.getInt("rcpt_no"));
				member_mailVO.setMail_cont(rs_p.getString("mail_cont"));
				member_mailVO.setMail_stat(rs_p.getInt("mail_stat"));
				member_mailVO.setMail_read_stat(rs_p.getInt("mail_read_stat"));
				member_mailVO.setMail_time(rs_p.getString("mail_time"));
				set.add(member_mailVO); // Store the row in the list
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
	public void insertWithSvc (Member_mailVO member_mailVO, Set<Service_mail_pictureHBVO> set) {

		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			Set<Member_mail_pictureHBVO> setMbr = new LinkedHashSet<Member_mail_pictureHBVO>();
			for(Service_mail_pictureHBVO service_mail_pictureHBVO : set) {
				Member_mail_pictureHBVO member_mail_pictureHBVO = new Member_mail_pictureHBVO();
				member_mail_pictureHBVO.setMail_pic(service_mail_pictureHBVO.getMail_pic());
				setMbr.add(member_mail_pictureHBVO);
			}
			
			for(Member_mail_pictureHBVO member_mail_pictureHBVO : setMbr) {
				member_mailVO.add(member_mail_pictureHBVO);
			}
			
			session.save(member_mailVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}
	
	@Override
	public void insertWithSvc (Member_mailVO member_mailVO) {

		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			session.save(member_mailVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}
	
	@Override
	public void insertWithCOwner (Member_mailVO member_mailVO, Set<Campsite_owner_mail_pictureHBVO> set) {

		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			Set<Member_mail_pictureHBVO> setMbr = new LinkedHashSet<Member_mail_pictureHBVO>();
			for(Campsite_owner_mail_pictureHBVO campsite_owner_mail_pictureHBVO : set) {
				Member_mail_pictureHBVO member_mail_pictureHBVO = new Member_mail_pictureHBVO();
				member_mail_pictureHBVO.setMail_pic(campsite_owner_mail_pictureHBVO.getMail_pic());
				setMbr.add(member_mail_pictureHBVO);
			}
			
			for(Member_mail_pictureHBVO member_mail_pictureHBVO : setMbr) {
				member_mailVO.add(member_mail_pictureHBVO);
			}
			
			session.save(member_mailVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().commit();
			
		}
	}
	
	@Override
	public void insertWithCOwner (Member_mailVO member_mailVO) {

		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			session.save(member_mailVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}
	
	@Override
	public void insertWithMbr (Member_mailVO member_mailVO, Set<Member_mail_pictureHBVO> set) {

		Session session = factory.getCurrentSession();
		
		try {

			member_mailVO.setMember_mail_pictureHBVO(set);
			
			session.save(member_mailVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}
	
	@Override
	public void insertWithMbr (Member_mailVO member_mailVO) {

		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			session.save(member_mailVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		} 
	}
	
	@Override
	public void insertWithPic(Member_mailVO member_mailVO, Set<Member_mail_pictureHBVO> set) {
		
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			for(Member_mail_pictureHBVO member_mail_pictureHBVO : set) {
				member_mail_pictureHBVO.setMember_mailVO(member_mailVO);
				member_mailVO.add(member_mail_pictureHBVO);
			}
			
			session.save(member_mailVO);
			
			if(member_mailVO.getRcpt_no().toString().substring(0,1).equals("9")) {
				Service_mailHBDAO service_mailHBDAO = new Service_mailHBDAO();
				Integer mbr_no = member_mailVO.getSend_no();
				Integer emp_no = member_mailVO.getRcpt_no();
				Integer mail_read_stat = 0;
				Integer mail_stat = 0;
				String mail_cont = member_mailVO.getMail_cont();
				String mail_time = member_mailVO.getMail_time();
				Service_mailVO service_mailVO = new Service_mailVO(emp_no, mbr_no, mail_cont, mail_stat, mail_read_stat, mail_time); 
				service_mailHBDAO.insertWithMbr(service_mailVO, set);
			}else if(member_mailVO.getRcpt_no().toString().substring(0,1).equals("1")) {

				Integer send_no = member_mailVO.getSend_no();
				Integer rcpt_no = member_mailVO.getRcpt_no();
				Integer mail_read_stat = 0;
				Integer mail_stat = 0;
				String mail_cont = member_mailVO.getMail_cont();
				String mail_time = member_mailVO.getMail_time();
				Member_mailVO member_mailVORcpt = new Member_mailVO(send_no, rcpt_no, mail_read_stat, mail_stat, mail_cont, mail_time); 

				for(Member_mail_pictureHBVO member_mail_pictureHBVO : set) {
					Member_mail_pictureHBVO member_mail_pictureHBVO2 = new Member_mail_pictureHBVO();
					member_mail_pictureHBVO2.setMail_pic(member_mail_pictureHBVO.getMail_pic());
					member_mail_pictureHBVO2.setMember_mailVO(member_mailVORcpt);
					member_mailVORcpt.add(member_mail_pictureHBVO2);
				}
				session.save(member_mailVORcpt);
			}else if(member_mailVO.getRcpt_no().toString().substring(0,1).equals("7")) {
				Campsite_owner_mailHBDAO campsite_owner_mailHBDAO = new Campsite_owner_mailHBDAO();
				Integer send_no = member_mailVO.getSend_no();
				Integer rcpt_no = member_mailVO.getRcpt_no();
				Integer mail_read_stat = 0;
				Integer mail_stat = 0;
				String mail_cont = member_mailVO.getMail_cont();
				String mail_time = member_mailVO.getMail_time();
				Campsite_owner_mailVO campsite_owner_mailVO = new Campsite_owner_mailVO(send_no, rcpt_no, mail_read_stat, mail_stat, mail_cont, mail_time); 
				campsite_owner_mailHBDAO.insertWithMbr(campsite_owner_mailVO, set);
			}
			
			session.getTransaction().commit();			

		}catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
	}


	@Override
	public List<Member_mailVO> getSend(Integer send_no) {
		List<Member_mailVO> list = new ArrayList<Member_mailVO>();

		Session session = factory.getCurrentSession();

		try {

			session = factory.getCurrentSession();
			
			list = session.createQuery("FROM Member_mailVO WHERE send_no = " + send_no + " order by mail_time desc", Member_mailVO.class).getResultList();
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
			
		}
		
		return list;
	}


	@Override
	public List<Member_mailVO> getRcpt(Integer rcpt_no) {
		List<Member_mailVO> list = new ArrayList<Member_mailVO>();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			list = session.createQuery("From Member_mailVO WHERE rcpt_no = " + rcpt_no + " order by mail_time desc", Member_mailVO.class).getResultList();
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
			session.getTransaction().rollback();
		
		}
		
		return list;
	}


	@Override
	public List<Member_mailVO> getStat(Integer mail_stat) {
		
		List<Member_mailVO> list = new ArrayList<Member_mailVO>();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			list = session.createQuery("FROM Member_mailVO WHERE mail_stat = " + mail_stat + " order by mail_time desc", Member_mailVO.class).getResultList();
			
			session.getTransaction().commit();

		} catch (Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
		
		}
		
		return list;
	}
}