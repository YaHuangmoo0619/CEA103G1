package com.personal_system_notify.model;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
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

import com.member_mail.model.Member_mailVO;


public class Personal_System_NotifyDAO implements Personal_System_NotifyDAO_Interface {
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
			"INSERT INTO Personal_System_Notify (NTFY_NO,MBR_NO,NTFY_STAT,NTFY_CONT,NTFY_TIME) VALUES (?, ?, ?, ? , ? )";
		private static final String GET_ALL_STMT = 
			"SELECT NTFY_NO,MBR_NO,NTFY_STAT,NTFY_CONT,NTFY_TIME FROM Personal_System_Notify order by NTFY_TIME DESC";
		private static final String GET_ONE_STMT = 
			"SELECT NTFY_NO,MBR_NO,NTFY_STAT,NTFY_CONT,NTFY_TIME FROM Personal_System_Notify where NTFY_NO = ?";
		private static final String DELETE = 
			"DELETE FROM Personal_System_Notify where NTFY_NO = ?";
		private static final String UPDATE = 
			"UPDATE Personal_System_Notify set MBR_NO=?,NTFY_STAT=?,NTFY_CONT=?,NTFY_TIME=? where NTFY_NO = ?";



		public void insert(Personal_System_NotifyVO Personal_System_NotifyVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, Personal_System_NotifyVO.getNtfy_no());
				pstmt.setInt(2, Personal_System_NotifyVO.getMbr_no());
				pstmt.setInt(3,Personal_System_NotifyVO.getNtfy_stat());
				pstmt.setString(4,Personal_System_NotifyVO.getNtfy_cont());
				pstmt.setString(5,Personal_System_NotifyVO.getNtfy_time());
				pstmt.executeUpdate();

				// Handle any SQL errors
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

		public void update(Personal_System_NotifyVO Personal_System_NotifyVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, Personal_System_NotifyVO.getNtfy_no());
				pstmt.setInt(2, Personal_System_NotifyVO.getMbr_no());
				pstmt.setInt(3,Personal_System_NotifyVO.getNtfy_stat());
				pstmt.setString(4,Personal_System_NotifyVO.getNtfy_cont());
				pstmt.setString(5,Personal_System_NotifyVO.getNtfy_time());
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
		
		
		
		public void delete(Integer ntfy_no) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, ntfy_no);

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
		
		
		
		public Personal_System_NotifyVO findByPrimaryKey(Integer ntfy_no) {

			Personal_System_NotifyVO Personal_System_NotifyVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setInt(1, ntfy_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					Personal_System_NotifyVO = new Personal_System_NotifyVO();
					Personal_System_NotifyVO.setMbr_no(rs.getInt("ntfy_no"));
					Personal_System_NotifyVO.setNtfy_no(rs.getInt("mbr_no"));
					Personal_System_NotifyVO.setNtfy_stat(rs.getInt("ntfy_stat"));
					Personal_System_NotifyVO.setNtfy_cont(rs.getString("ntfy_cont"));
					Personal_System_NotifyVO.setNtfy_time(rs.getString("ntfy_time"));
					
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
			return Personal_System_NotifyVO;
		}
		
		
		public List<Personal_System_NotifyVO> getAll() {
			List<Personal_System_NotifyVO> list = new ArrayList<Personal_System_NotifyVO>();
			Personal_System_NotifyVO Personal_System_NotifyVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Personal_System_NotifyVO = new Personal_System_NotifyVO();
					Personal_System_NotifyVO.setNtfy_no(rs.getInt("ntfy_no"));
					Personal_System_NotifyVO.setMbr_no(rs.getInt("mbr_no"));
					Personal_System_NotifyVO.setNtfy_stat(rs.getInt("ntfy_stat"));
					Personal_System_NotifyVO.setNtfy_cont(rs.getString("ntfy_cont"));
					Personal_System_NotifyVO.setNtfy_time(rs.getString("ntfy_time"));
					list.add(Personal_System_NotifyVO); // Store the row in the list
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

		@Override
		public Set<Personal_System_NotifyVO> getWhereCondition(Map<String, String[]> map) {
				Set<Personal_System_NotifyVO> set = new LinkedHashSet<Personal_System_NotifyVO>();
				StringBuffer partOfsqlWhere = new StringBuffer();
				Personal_System_NotifyVO personal_system_notifyVO = null;
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
					rs = stmt.executeQuery("select * from campion.personal_system_notify");
					ResultSetMetaData rm = rs.getMetaData();
					
					//建立sql指令
					List<String> checkfirst = new ArrayList<String>();
					Map<Integer,String> forPstmt = new LinkedHashMap<Integer,String>();//之後連到資料庫查找用
					//獲得所有欄位名稱
					for(int i = 1 ; i <= rm.getColumnCount(); i++) {
						nextColumn:
						//屬於特定sql型別做特定動作
						for(String key : keys) {
//							System.out.println(key);
//							System.out.println(rm.getColumnTypeName(i));
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
								}else if(rm.getColumnTypeName(i) == "TINYINT" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
									partOfsqlWhere.append("select * from "+rm.getTableName(i)+" where "+ rm.getColumnName(i) +" = ?");
//									System.out.println("inTINYINT");
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
								}else if(rm.getColumnTypeName(i) == "BIT" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
									partOfsqlWhere.append(" and "+ rm.getColumnName(i) +" = ?");
									checkfirst.add(key);
									forPstmt.put(i, key);
									break nextColumn;
								}else if(rm.getColumnTypeName(i) == "TINYINT" && rm.getColumnName(i).toLowerCase().equals(key) && !checkfirst.contains(key) && !map.get(key)[0].equals("no") && !map.get(key)[0].isEmpty()) {
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
//					System.out.println(partOfsqlWhere.toString());
					pstmt = con.prepareStatement(partOfsqlWhere.toString());
					Set<Integer> keysPstmt = forPstmt.keySet();//上一個步驟存的資料
					
					int index = 1;
					for(Integer keyPstmt : keysPstmt) {
						if(rm.getColumnTypeName(keyPstmt) == "INT") {
//							System.out.println(Integer.valueOf(map.get(forPstmt.get(keyPstmt))[0]));
							pstmt.setInt(index, Integer.valueOf(map.get(forPstmt.get(keyPstmt))[0]));
							index++;
						}else if(rm.getColumnTypeName(keyPstmt) == "BIT") {
							pstmt.setInt(index, Integer.valueOf(map.get(forPstmt.get(keyPstmt))[0]));
							index++;
						}else if(rm.getColumnTypeName(keyPstmt) == "TINYINT") {
							pstmt.setInt(index, Integer.valueOf(map.get(forPstmt.get(keyPstmt))[0]));
							index++;
						}else if(rm.getColumnTypeName(keyPstmt) == "VARCHAR") {
//							System.out.println("%"+map.get(forPstmt.get(keyPstmt))[0]+"%");
							pstmt.setString(index, "%"+map.get(forPstmt.get(keyPstmt))[0]+"%");
							index++;
						}else if(rm.getColumnTypeName(keyPstmt) == "DATETIME") {
							pstmt.setString(index, map.get(forPstmt.get(keyPstmt))[0]+"%");
							index++;
						}
					}
					
					rs_p = pstmt.executeQuery();
					
					while (rs_p.next()) {
						personal_system_notifyVO = new Personal_System_NotifyVO();
						personal_system_notifyVO.setNtfy_no(rs_p.getInt("ntfy_no"));
						personal_system_notifyVO.setMbr_no(rs_p.getInt("mbr_no"));
						personal_system_notifyVO.setNtfy_stat(rs_p.getInt("ntfy_stat"));
						personal_system_notifyVO.setNtfy_cont(rs_p.getString("ntfy_cont"));
						personal_system_notifyVO.setNtfy_time(rs_p.getString("ntfy_time"));
						set.add(personal_system_notifyVO); // Store the row in the list
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
		
		
}
