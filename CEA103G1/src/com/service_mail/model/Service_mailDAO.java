package com.service_mail.model;

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


public class Service_mailDAO implements Service_mailDAO_interface {

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
			"INSERT INTO campion.service_mail (mail_no,emp_no,mbr_no,mail_cont,mail_stat,mail_read_stat,mail_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT mail_no,emp_no,mbr_no,mail_cont,mail_stat,mail_read_stat,mail_time FROM campion.service_mail order by mail_no";
		private static final String GET_ONE_STMT = 
			"SELECT mail_no,emp_no,mbr_no,mail_cont,mail_stat,mail_read_stat,mail_time FROM campion.service_mail where mail_no = ?";
		private static final String DELETE = 
			"DELETE FROM campion.service_mail where mail_no = ?";
		private static final String UPDATE = 
			"UPDATE campion.service_mail set emp_no=?, mbr_no=?, mail_cont=?, mail_stat=?, mail_read_stat=?, mail_time=? where mail_no = ?";	
	
	@Override
	public void insert(Service_mailVO service_mailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, service_mailVO.getMail_no());
			pstmt.setInt(2, service_mailVO.getEmp_no());
			pstmt.setInt(3, service_mailVO.getMbr_no());
			pstmt.setString(4, service_mailVO.getMail_cont());
			pstmt.setInt(5, service_mailVO.getMail_stat());
			pstmt.setInt(6, service_mailVO.getMail_read_stat());
			pstmt.setTimestamp(7, service_mailVO.getMail_time());

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

	@Override
	public void update(Service_mailVO service_mailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, service_mailVO.getMail_no());
			pstmt.setInt(2, service_mailVO.getEmp_no());
			pstmt.setInt(3, service_mailVO.getMbr_no());
			pstmt.setString(4, service_mailVO.getMail_cont());
			pstmt.setInt(5, service_mailVO.getMail_stat());
			pstmt.setInt(6, service_mailVO.getMail_read_stat());
			pstmt.setTimestamp(7, service_mailVO.getMail_time());

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
	public Service_mailVO findByPrimaryKey(Integer mail_no) {

		Service_mailVO service_mailVO = null;
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
				service_mailVO = new Service_mailVO();
				service_mailVO.setMail_no(rs.getInt("mail_no"));
				service_mailVO.setEmp_no(rs.getInt("emp_no"));
				service_mailVO.setMbr_no(rs.getInt("mbr_no"));
				service_mailVO.setMail_cont(rs.getString("mail_cont"));
				service_mailVO.setMail_stat(rs.getInt("mail_stat"));
				service_mailVO.setMail_read_stat(rs.getInt("mail_read_stat"));
				service_mailVO.setMail_time(rs.getTimestamp("mail_time"));
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
		return service_mailVO;
	}

	@Override
	public List<Service_mailVO> getAll() {
		List<Service_mailVO> list = new ArrayList<Service_mailVO>();
		Service_mailVO service_mailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				service_mailVO = new Service_mailVO();
				service_mailVO.setMail_no(rs.getInt("mail_no"));
				service_mailVO.setEmp_no(rs.getInt("emp_no"));
				service_mailVO.setMbr_no(rs.getInt("mbr_no"));
				service_mailVO.setMail_cont(rs.getString("mail_cont"));
				service_mailVO.setMail_stat(rs.getInt("mail_stat"));
				service_mailVO.setMail_read_stat(rs.getInt("mail_read_stat"));
				service_mailVO.setMail_time(rs.getTimestamp("mail_time"));
				list.add(service_mailVO); // Store the row in the list
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
