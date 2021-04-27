package com.advertising_application.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Advertising_applicationDAO implements Advertising_applicationDAO_interface {

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
		"INSERT INTO campion.advertising_application (cso_no,ad_head,ad_cont,ad_pic,app_time,rev_time,ad_stat,ad_st_date,ad_end_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ad_no,cso_no,ad_head,ad_cont,ad_pic,app_time,rev_time,ad_stat,ad_st_date,ad_end_date FROM campion.advertising_application order by ad_no";
	private static final String GET_ONE_STMT = 
		"SELECT ad_no,cso_no,ad_head,ad_cont,ad_pic,app_time,rev_time,ad_stat,ad_st_date,ad_end_date FROM campion.advertising_application where ad_no = ?";
	private static final String DELETE =
		"DELETE FROM campion.advertising_application where ad_no = ?";
	private static final String UPDATE = 
		"UPDATE campion.advertising_application set cso_no=?, ad_head=? ad_cont=? ad_pic=? app_time=? rev_time=? ad_stat=? ad_st_date=? ad_end_date=? where rank_no=?";

	@Override
	public void insert(Advertising_applicationVO advertising_applicationVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, advertising_applicationVO.getCso_no());
			pstmt.setString(2, advertising_applicationVO.getAd_head());
			pstmt.setString(3, advertising_applicationVO.getAd_cont());
			pstmt.setByte(4, advertising_applicationVO.getAd_pic());
			pstmt.setTimestamp(5, advertising_applicationVO.getApp_time());
			pstmt.setTimestamp(6, advertising_applicationVO.getRev_time());
			pstmt.setInt(7, advertising_applicationVO.getAd_stat());
			pstmt.setDate(8, advertising_applicationVO.getAd_st_date());
			pstmt.setDate(9, advertising_applicationVO.getAd_end_date());

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
	public void update(Advertising_applicationVO advertising_applicationVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, advertising_applicationVO.getCso_no());
			pstmt.setString(2, advertising_applicationVO.getAd_head());
			pstmt.setString(3, advertising_applicationVO.getAd_cont());
			pstmt.setByte(4, advertising_applicationVO.getAd_pic());
			pstmt.setTimestamp(5, advertising_applicationVO.getApp_time());
			pstmt.setTimestamp(6, advertising_applicationVO.getRev_time());
			pstmt.setInt(7, advertising_applicationVO.getAd_stat());
			pstmt.setDate(8, advertising_applicationVO.getAd_st_date());
			pstmt.setDate(9, advertising_applicationVO.getAd_end_date());
			pstmt.setInt(10, advertising_applicationVO.getAd_no());

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
	public void delete(Integer ad_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ad_no);

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
	public Advertising_applicationVO findByPrimaryKey(Integer ad_no) {

		Advertising_applicationVO advertising_applicationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ad_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// advertising_applicationVO 也稱為 Domain objects
				advertising_applicationVO = new Advertising_applicationVO();
				advertising_applicationVO.setAd_no(rs.getInt("ad_no"));
				advertising_applicationVO.setCso_no(rs.getInt("cso_no"));
				advertising_applicationVO.setAd_head(rs.getString("ad_head"));
				advertising_applicationVO.setAd_cont(rs.getString("ad_cont"));
				advertising_applicationVO.setAd_pic(rs.getByte("ad_pic"));
				advertising_applicationVO.setApp_time(rs.getTimestamp("app_time"));
				advertising_applicationVO.setRev_time(rs.getTimestamp("rec_time"));
				advertising_applicationVO.setAd_stat(rs.getInt("ad_stat"));
				advertising_applicationVO.setAd_st_date(rs.getDate("ad_st_date"));
				advertising_applicationVO.setAd_end_date(rs.getDate("ad_end_date"));
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
		return advertising_applicationVO;
	}

	@Override
	public List<Advertising_applicationVO> getAll() {
		List<Advertising_applicationVO> list = new ArrayList<Advertising_applicationVO>();
		Advertising_applicationVO advertising_applicationVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// advertising_applicationVO 也稱為 Domain objects
				advertising_applicationVO = new Advertising_applicationVO();
				advertising_applicationVO.setAd_no(rs.getInt("ad_no"));
				advertising_applicationVO.setCso_no(rs.getInt("cso_no"));
				advertising_applicationVO.setAd_head(rs.getString("ad_head"));
				advertising_applicationVO.setAd_cont(rs.getString("ad_cont"));
				advertising_applicationVO.setAd_pic(rs.getByte("ad_pic"));
				advertising_applicationVO.setApp_time(rs.getTimestamp("app_time"));
				advertising_applicationVO.setRev_time(rs.getTimestamp("rec_time"));
				advertising_applicationVO.setAd_stat(rs.getInt("ad_stat"));
				advertising_applicationVO.setAd_st_date(rs.getDate("ad_st_date"));
				advertising_applicationVO.setAd_end_date(rs.getDate("ad_end_date"));
				list.add(advertising_applicationVO); // Store the row in the list
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