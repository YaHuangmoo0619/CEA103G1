package com.campsite_owner_mail.model;

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
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, campsite_owner_mailVO.getSend_no());
			pstmt.setInt(2, campsite_owner_mailVO.getRcpt_no());
			pstmt.setInt(3, campsite_owner_mailVO.getMail_read_stat());
			pstmt.setInt(4, campsite_owner_mailVO.getMail_stat());
			pstmt.setString(5, campsite_owner_mailVO.getMail_cont());
			pstmt.setString(6, campsite_owner_mailVO.getMail_time());

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


}
