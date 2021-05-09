package com.campsite_owner_mail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.campsite_owner_mail_picture.model.Campsite_owner_mail_pictureDAO;
import com.campsite_owner_mail_picture.model.Campsite_owner_mail_pictureVO;
import com.member_mail.model.Member_mailDAO;
import com.member_mail.model.Member_mailVO;
import com.member_mail_picture.model.Member_mail_pictureDAO;
import com.member_mail_picture.model.Member_mail_pictureVO;
import com.service_mail.model.Service_mailDAO;
import com.service_mail.model.Service_mailVO;
import com.service_mail_picture.model.Service_mail_pictureVO;

public class Campsite_owner_mailDAO implements Campsite_owner_mailDAO_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO campion.campsite_owner_mail (send_no,rcpt_no,mail_read_stat,mail_stat,mail_cont,mail_time) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT mail_no,send_no,rcpt_no,mail_read_stat,mail_stat,mail_cont,mail_time FROM campion.campsite_owner_mail order by mail_no";
	private static final String GET_ONE_STMT = 
		"SELECT mail_no,send_no,rcpt_no,mail_read_stat,mail_stat,mail_cont,mail_time FROM campion.campsite_owner_mail where mail_no = ?";
	private static final String DELETE = 
		"DELETE FROM campion.campsite_owner_mail where mail_no = ?";
	private static final String UPDATE = 
		"UPDATE campion.campsite_owner_mail set send_no=?, rcpt_no=?, mail_read_stat=?, mail_stat=? ,mail_cont=?, mail_time=? where mail_no = ?";
		
	@Override
	public void insert(Campsite_owner_mailVO campsite_owner_mailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, campsite_owner_mailVO.getSend_no());
			pstmt.setInt(2, campsite_owner_mailVO.getRcpt_no());
			pstmt.setInt(3, campsite_owner_mailVO.getMail_read_stat());
			pstmt.setInt(4, campsite_owner_mailVO.getMail_stat());
			pstmt.setString(5, campsite_owner_mailVO.getMail_cont());
			pstmt.setString(6, campsite_owner_mailVO.getMail_time());

			pstmt.executeUpdate();

			if(campsite_owner_mailVO.getRcpt_no().toString().substring(0,1).equals("9")) {
				Service_mailDAO service_mailDAO = new Service_mailDAO();
				Integer mbr_no = campsite_owner_mailVO.getSend_no();
				Integer emp_no = campsite_owner_mailVO.getRcpt_no();
				Integer mail_read_stat = 0;
				Integer mail_stat = 0;
				String mail_cont = campsite_owner_mailVO.getMail_cont();
				String mail_time = campsite_owner_mailVO.getMail_time();
				Service_mailVO service_mailVO = new Service_mailVO(emp_no, mbr_no, mail_cont, mail_stat, mail_read_stat, mail_time); 
				service_mailDAO.insertWithMbr(service_mailVO, con);
			}else if(campsite_owner_mailVO.getRcpt_no().toString().substring(0,1).equals("1")) {
				Member_mailDAO member_mailDAO = new Member_mailDAO();
				Integer send_no = campsite_owner_mailVO.getSend_no();
				Integer rcpt_no = campsite_owner_mailVO.getRcpt_no();
				Integer mail_read_stat = 0;
				Integer mail_stat = 0;
				String mail_cont = campsite_owner_mailVO.getMail_cont();
				String mail_time = campsite_owner_mailVO.getMail_time();
				Member_mailVO member_mailVO = new Member_mailVO(send_no, rcpt_no, mail_read_stat, mail_stat, mail_cont, mail_time); 
				member_mailDAO.insertWithMbr(member_mailVO,con);
			}
			con.commit();
			con.setAutoCommit(true);
			
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-campsite_owner_mail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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

	}

	@Override
	public void update(Campsite_owner_mailVO campsite_owner_mailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(7, campsite_owner_mailVO.getMail_no());
			pstmt.setInt(1, campsite_owner_mailVO.getSend_no());
			pstmt.setInt(2, campsite_owner_mailVO.getRcpt_no());
			pstmt.setInt(3, campsite_owner_mailVO.getMail_read_stat());
			pstmt.setInt(4, campsite_owner_mailVO.getMail_stat());
			pstmt.setString(5, campsite_owner_mailVO.getMail_cont());
			pstmt.setString(6, campsite_owner_mailVO.getMail_time());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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

	}

	@Override
	public void delete(Integer mail_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mail_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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

	}

	@Override
	public Campsite_owner_mailVO findByPrimaryKey(Integer mail_no) {

		Campsite_owner_mailVO campsite_owner_mailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mail_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				campsite_owner_mailVO = new Campsite_owner_mailVO();
				campsite_owner_mailVO.setMail_no(rs.getInt("mail_no"));
				campsite_owner_mailVO.setSend_no(rs.getInt("send_no"));
				campsite_owner_mailVO.setRcpt_no(rs.getInt("rcpt_no"));
				campsite_owner_mailVO.setMail_read_stat(rs.getInt("mail_read_stat"));
				campsite_owner_mailVO.setMail_stat(rs.getInt("mail_stat"));
				campsite_owner_mailVO.setMail_cont(rs.getString("mail_cont"));
				campsite_owner_mailVO.setMail_time(rs.getString("mail_time"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return campsite_owner_mailVO;
	}


	@Override
	public List<Campsite_owner_mailVO> getAll() {
		List<Campsite_owner_mailVO> list = new ArrayList<Campsite_owner_mailVO>();
		Campsite_owner_mailVO campsite_owner_mailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				campsite_owner_mailVO = new Campsite_owner_mailVO();
				campsite_owner_mailVO.setMail_no(rs.getInt("mail_no"));
				campsite_owner_mailVO.setSend_no(rs.getInt("send_no"));
				campsite_owner_mailVO.setRcpt_no(rs.getInt("rcpt_no"));
				campsite_owner_mailVO.setMail_read_stat(rs.getInt("mail_read_stat"));
				campsite_owner_mailVO.setMail_stat(rs.getInt("mail_stat"));
				campsite_owner_mailVO.setMail_cont(rs.getString("mail_cont"));
				campsite_owner_mailVO.setMail_time(rs.getString("mail_time"));
				list.add(campsite_owner_mailVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public Set<Campsite_owner_mailVO> getWhereCondition(Map<String,String[]> map){
		Set<Campsite_owner_mailVO> set = new LinkedHashSet<Campsite_owner_mailVO>();
		StringBuffer partOfsqlWhere = new StringBuffer();
		Campsite_owner_mailVO campsite_owner_mailVO = null;
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
			rs = stmt.executeQuery("select * from campion.campsite_owner_mail");
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
			pstmt = con.prepareStatement(partOfsqlWhere.toString());
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
				campsite_owner_mailVO = new Campsite_owner_mailVO();
				campsite_owner_mailVO.setMail_no(rs_p.getInt("mail_no"));
				campsite_owner_mailVO.setSend_no(rs_p.getInt("send_no"));
				campsite_owner_mailVO.setRcpt_no(rs_p.getInt("rcpt_no"));
				campsite_owner_mailVO.setMail_cont(rs_p.getString("mail_cont"));
				campsite_owner_mailVO.setMail_stat(rs_p.getInt("mail_stat"));
				campsite_owner_mailVO.setMail_read_stat(rs_p.getInt("mail_read_stat"));
				campsite_owner_mailVO.setMail_time(rs_p.getString("mail_time"));
				set.add(campsite_owner_mailVO); // Store the row in the list
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
	}
	
	@Override
	public void insertWithSvc (Campsite_owner_mailVO campsite_owner_mailVO, Set<Service_mail_pictureVO> set, Connection con) {
		PreparedStatement pstmt = null;
		try {

			String cols[] = {"mail_no"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);

			pstmt.setInt(1, campsite_owner_mailVO.getSend_no());
			pstmt.setInt(2, campsite_owner_mailVO.getRcpt_no());
			pstmt.setInt(3, campsite_owner_mailVO.getMail_read_stat());
			pstmt.setInt(4, campsite_owner_mailVO.getMail_stat());
			pstmt.setString(5, campsite_owner_mailVO.getMail_cont());
			pstmt.setString(6, campsite_owner_mailVO.getMail_time());

			pstmt.executeUpdate();
			
			String next_mail_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
//			System.out.println("Res="+ rs);
			if(rs.next()) {
				next_mail_no = rs.getString(1);
			}
			rs.close();
			
			Set<Campsite_owner_mail_pictureVO> setCOwner = new LinkedHashSet<Campsite_owner_mail_pictureVO>();
			for(Service_mail_pictureVO service_mail_pictureVO : set) {
				Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO = new Campsite_owner_mail_pictureVO();
				campsite_owner_mail_pictureVO.setMail_pic(service_mail_pictureVO.getMail_pic());
				setCOwner.add(campsite_owner_mail_pictureVO);
			}
			
			Campsite_owner_mail_pictureDAO campsite_owner_mail_pictureDAO = new Campsite_owner_mail_pictureDAO();
			for(Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO : setCOwner) {
				campsite_owner_mail_pictureVO.setMail_no(new Integer(next_mail_no));
				campsite_owner_mail_pictureDAO.insertWithMail(campsite_owner_mail_pictureVO , con);
			}
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-campsite_owner_mail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void insertWithSvc (Campsite_owner_mailVO campsite_owner_mailVO, Connection con) {
		PreparedStatement pstmt = null;
		try {

			pstmt = con.prepareStatement(INSERT_STMT);
			

			pstmt.setInt(1, campsite_owner_mailVO.getSend_no());
			pstmt.setInt(2, campsite_owner_mailVO.getRcpt_no());
			pstmt.setInt(3, campsite_owner_mailVO.getMail_read_stat());
			pstmt.setInt(4, campsite_owner_mailVO.getMail_stat());
			pstmt.setString(5, campsite_owner_mailVO.getMail_cont());
			pstmt.setString(6, campsite_owner_mailVO.getMail_time());

			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-campsite_owner_mail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void insertWithMbr (Campsite_owner_mailVO campsite_owner_mailVO, Set<Member_mail_pictureVO> set, Connection con) {
		PreparedStatement pstmt = null;
		try {

			String cols[] = {"mail_no"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);

			pstmt.setInt(1, campsite_owner_mailVO.getSend_no());
			pstmt.setInt(2, campsite_owner_mailVO.getRcpt_no());
			pstmt.setInt(3, campsite_owner_mailVO.getMail_read_stat());
			pstmt.setInt(4, campsite_owner_mailVO.getMail_stat());
			pstmt.setString(5, campsite_owner_mailVO.getMail_cont());
			pstmt.setString(6, campsite_owner_mailVO.getMail_time());

			pstmt.executeUpdate();
			
			String next_mail_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
//			System.out.println("Res="+ rs);
			if(rs.next()) {
				next_mail_no = rs.getString(1);
			}
			rs.close();
			
			Set<Campsite_owner_mail_pictureVO> setCOwner = new LinkedHashSet<Campsite_owner_mail_pictureVO>();
			for(Member_mail_pictureVO member_mail_pictureVO : set) {
				Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO = new Campsite_owner_mail_pictureVO();
				campsite_owner_mail_pictureVO.setMail_pic(member_mail_pictureVO.getMail_pic());
				setCOwner.add(campsite_owner_mail_pictureVO);
			}
			
			Campsite_owner_mail_pictureDAO campsite_owner_mail_pictureDAO = new Campsite_owner_mail_pictureDAO();
			for(Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO : setCOwner) {
				campsite_owner_mail_pictureVO.setMail_no(new Integer(next_mail_no));
				campsite_owner_mail_pictureDAO.insertWithMail(campsite_owner_mail_pictureVO , con);
			}
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-campsite_owner_mail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void insertWithMbr (Campsite_owner_mailVO campsite_owner_mailVO, Connection con) {
		PreparedStatement pstmt = null;
		try {

			pstmt = con.prepareStatement(INSERT_STMT);
			

			pstmt.setInt(1, campsite_owner_mailVO.getSend_no());
			pstmt.setInt(2, campsite_owner_mailVO.getRcpt_no());
			pstmt.setInt(3, campsite_owner_mailVO.getMail_read_stat());
			pstmt.setInt(4, campsite_owner_mailVO.getMail_stat());
			pstmt.setString(5, campsite_owner_mailVO.getMail_cont());
			pstmt.setString(6, campsite_owner_mailVO.getMail_time());

			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-campsite_owner_mail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void insertWithPic(Campsite_owner_mailVO campsite_owner_mailVO, Set<Campsite_owner_mail_pictureVO> set) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			con.setAutoCommit(false);
			
			String cols[] = {"mail_no"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);

			pstmt.setInt(1, campsite_owner_mailVO.getSend_no());
			pstmt.setInt(2, campsite_owner_mailVO.getRcpt_no());
			pstmt.setInt(3, campsite_owner_mailVO.getMail_read_stat());
			pstmt.setInt(4, campsite_owner_mailVO.getMail_stat());
			pstmt.setString(5, campsite_owner_mailVO.getMail_cont());
			pstmt.setString(6, campsite_owner_mailVO.getMail_time());
			
			pstmt.executeUpdate();
			
			String next_mail_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
//			System.out.println("Res="+ rs);
			if(rs.next()) {
				next_mail_no = rs.getString(1);
			}
			rs.close();
			
			Campsite_owner_mail_pictureDAO campsite_owner_mail_pictureDAO = new Campsite_owner_mail_pictureDAO();
			for(Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO : set) {
				campsite_owner_mail_pictureVO.setMail_no(new Integer(next_mail_no));
				campsite_owner_mail_pictureDAO.insertWithMail(campsite_owner_mail_pictureVO , con);
			}
			
			if(campsite_owner_mailVO.getRcpt_no().toString().substring(0,1).equals("9")) {
				Service_mailDAO service_mailDAO = new Service_mailDAO();
				Integer mbr_no = campsite_owner_mailVO.getSend_no();
				Integer emp_no = campsite_owner_mailVO.getRcpt_no();
				Integer mail_read_stat = 0;
				Integer mail_stat = 0;
				String mail_cont = campsite_owner_mailVO.getMail_cont();
				String mail_time = campsite_owner_mailVO.getMail_time();
				Service_mailVO service_mailVO = new Service_mailVO(emp_no, mbr_no, mail_cont, mail_stat, mail_read_stat, mail_time); 
				service_mailDAO.insertWithCOwner(service_mailVO, set, con);
			}else if(campsite_owner_mailVO.getRcpt_no().toString().substring(0,1).equals("1")) {
				Member_mailDAO member_mailDAO = new Member_mailDAO();
				Integer send_no = campsite_owner_mailVO.getSend_no();
				Integer rcpt_no = campsite_owner_mailVO.getRcpt_no();
				Integer mail_read_stat = 0;
				Integer mail_stat = 0;
				String mail_cont = campsite_owner_mailVO.getMail_cont();
				String mail_time = campsite_owner_mailVO.getMail_time();
				Member_mailVO member_mailVORcpt = new Member_mailVO(send_no, rcpt_no, mail_read_stat, mail_stat, mail_cont, mail_time); 
				member_mailDAO.insertWithCOwner(member_mailVORcpt, set,con);
			}
			
			
			con.commit();
			con.setAutoCommit(true);
			// Handle any SQL errors
		}catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-campsite_owner_mail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
	}
}
