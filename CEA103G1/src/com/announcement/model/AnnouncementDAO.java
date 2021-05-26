package com.announcement.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AnnouncementDAO implements AnnouncementDAO_interface {

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
			"INSERT INTO campion.announcement (emp_no,an_cont,an_skd_date,an_pic) VALUES (?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT an_no,emp_no,an_cont,an_skd_date,an_pic FROM campion.announcement order by an_no desc";
		private static final String GET_ALL_TO_SHOW_STMT = 
				"SELECT an_no,emp_no,an_cont,an_skd_date,an_pic FROM campion.announcement  where DATEDIFF(an_skd_date , sysdate()) <= 0 order by an_no desc";
		private static final String GET_ALL_DATE_EMP_NO_STMT = 
				"SELECT an_no,emp_no,an_cont,an_skd_date,an_pic FROM campion.announcement where an_skd_date = ?";
		private static final String GET_ONE_STMT = 
			"SELECT an_no,emp_no,an_cont,an_skd_date,an_pic FROM campion.announcement where an_no = ?";
		private static final String DELETE = 
			"DELETE FROM campion.announcement where an_no = ?";
		private static final String UPDATE = 
			"UPDATE campion.announcement set emp_no=?, an_cont=?, an_skd_date=?, an_pic=? where an_no = ?";
	
	@Override
	public void insert(AnnouncementVO announcementVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, announcementVO.getEmp_no());
			pstmt.setString(2, announcementVO.getAn_cont());
			pstmt.setDate(3, announcementVO.getAn_skd_date());
			pstmt.setBytes(4, announcementVO.getAn_pic());

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
	public void update(AnnouncementVO announcementVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(5, announcementVO.getAn_no());
			pstmt.setInt(1, announcementVO.getEmp_no());
			pstmt.setString(2, announcementVO.getAn_cont());
			pstmt.setDate(3, announcementVO.getAn_skd_date());
			pstmt.setBytes(4, announcementVO.getAn_pic());

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
	public void delete(Integer an_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, an_no);

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
	public AnnouncementVO findByPrimaryKey(Integer an_no) {

		AnnouncementVO announcementVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, an_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				announcementVO = new AnnouncementVO();
				announcementVO.setAn_no(rs.getInt("an_no"));
				announcementVO.setEmp_no(rs.getInt("emp_no"));
				announcementVO.setAn_cont(rs.getString("an_cont"));
				announcementVO.setAn_skd_date(rs.getDate("an_skd_date"));
				announcementVO.setAn_pic(rs.getBytes("an_pic"));
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
		return announcementVO;
	}

	@Override
	public List<AnnouncementVO> getAll() {
		List<AnnouncementVO> list = new ArrayList<AnnouncementVO>();
		AnnouncementVO announcementVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				announcementVO = new AnnouncementVO();
				announcementVO.setAn_no(rs.getInt("an_no"));
				announcementVO.setEmp_no(rs.getInt("emp_no"));
				announcementVO.setAn_cont(rs.getString("an_cont"));
				announcementVO.setAn_skd_date(rs.getDate("an_skd_date"));
				list.add(announcementVO);
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
	public List<AnnouncementVO> getDateEmp_no(Date an_skd_date) {
		List<AnnouncementVO> list = new ArrayList<AnnouncementVO>();
		AnnouncementVO announcementVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_DATE_EMP_NO_STMT);
			pstmt.setDate(1, an_skd_date);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				announcementVO = new AnnouncementVO();
				announcementVO.setAn_no(rs.getInt("an_no"));
				announcementVO.setEmp_no(rs.getInt("emp_no"));
				announcementVO.setAn_cont(rs.getString("an_cont"));
				announcementVO.setAn_skd_date(rs.getDate("an_skd_date"));
				list.add(announcementVO);
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
	public List<AnnouncementVO> getAlltoShow() {
		List<AnnouncementVO> list = new ArrayList<AnnouncementVO>();
		AnnouncementVO announcementVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_TO_SHOW_STMT);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				announcementVO = new AnnouncementVO();
				announcementVO.setAn_no(rs.getInt("an_no"));
				announcementVO.setEmp_no(rs.getInt("emp_no"));
				announcementVO.setAn_cont(rs.getString("an_cont"));
				announcementVO.setAn_skd_date(rs.getDate("an_skd_date"));
				list.add(announcementVO);
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
