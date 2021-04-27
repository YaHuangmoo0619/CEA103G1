package com.personal_system_notify.model;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


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
			"SELECT NTFY_NO,MBR_NO,NTFY_STAT,NTFY_CONT,NTFY_TIME FROM Personal_System_Notify order by NTFY_NO";
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
				pstmt.setTimestamp(5,Personal_System_NotifyVO.getNtfy_time());
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
				pstmt.setTimestamp(5,Personal_System_NotifyVO.getNtfy_time());
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
					// empVo ¤]ºÙ¬° Domain objects
					Personal_System_NotifyVO = new Personal_System_NotifyVO();
					Personal_System_NotifyVO.setMbr_no(rs.getInt("ntfy_no"));
					Personal_System_NotifyVO.setNtfy_no(rs.getInt("mbr_no"));
					Personal_System_NotifyVO.setNtfy_stat(rs.getInt("ntfy_stat"));
					Personal_System_NotifyVO.setNtfy_cont(rs.getString("ntfy_cont"));
					Personal_System_NotifyVO.setNtfy_time(rs.getTimestamp("ntfy_time"));
					
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
					Personal_System_NotifyVO.setMbr_no(rs.getInt("ntfy_no"));
					Personal_System_NotifyVO.setNtfy_no(rs.getInt("mbr_no"));
					Personal_System_NotifyVO.setNtfy_stat(rs.getInt("ntfy_stat"));
					Personal_System_NotifyVO.setNtfy_cont(rs.getString("ntfy_cont"));
					Personal_System_NotifyVO.setNtfy_time(rs.getTimestamp("ntfy_time"));
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
		
		
}
