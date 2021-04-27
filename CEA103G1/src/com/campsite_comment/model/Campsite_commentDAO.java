package com.campsite_comment.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Campsite_commentDAO implements Campsite_commentDAO_interface {

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
		"INSERT INTO campion.campsite_comment (camp_no,mbr_no,cmnt_cont,star,cmnt_time,cmnt_stat) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT camp_cmnt_no,camp_no,mbr_no,cmnt_cont,star,cmnt_time,cmnt_stat FROM campion.campsite_comment order by camp_cmnt_no";
	private static final String GET_ONE_STMT = 
		"SELECT camp_cmnt_no,camp_no,mbr_no,cmnt_cont,star,cmnt_time,cmnt_stat FROM campion.campsite_comment where camp_cmnt_no = ?";
	private static final String DELETE = 
		"DELETE FROM campion.campsite_comment where camp_cmnt_no = ?";
	private static final String UPDATE = 
		"UPDATE campion.campsite_comment set camp_no,mbr_no,cmnt_cont,star,cmnt_time,cmnt_stat where camp_cmnt_no=?";

	@Override
	public void insert(Campsite_commentVO campsite_commentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, campsite_commentVO.getCamp_no());
			pstmt.setInt(2, campsite_commentVO.getMbr_no());
			pstmt.setString(3, campsite_commentVO.getCmnt_cont());
			pstmt.setInt(4, campsite_commentVO.getStar());
			pstmt.setTimestamp(5, campsite_commentVO.getCmnt_time());
			pstmt.setInt(6, campsite_commentVO.getCmnt_stat());

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
	public void update(Campsite_commentVO campsite_commentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, campsite_commentVO.getCamp_no());
			pstmt.setInt(2, campsite_commentVO.getMbr_no());
			pstmt.setString(3, campsite_commentVO.getCmnt_cont());
			pstmt.setInt(4, campsite_commentVO.getStar());
			pstmt.setTimestamp(5, campsite_commentVO.getCmnt_time());
			pstmt.setInt(6, campsite_commentVO.getCmnt_stat());
			pstmt.setInt(7, campsite_commentVO.getCamp_cmnt_no());

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
	public void delete(Integer camp_cmnt_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, camp_cmnt_no);

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
	public Campsite_commentVO findByPrimaryKey(Integer camp_cmnt_no) {

		Campsite_commentVO campsite_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, camp_cmnt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// campsite_commentVO 也稱為 Domain objects
				campsite_commentVO = new Campsite_commentVO();
				campsite_commentVO.setCamp_cmnt_no(rs.getInt("camp_cmnt_no"));
				campsite_commentVO.setCamp_no(rs.getInt("camp_no"));
				campsite_commentVO.setMbr_no(rs.getInt("mbr_no"));
				campsite_commentVO.setCmnt_cont(rs.getString("cmnt_cont"));
				campsite_commentVO.setStar(rs.getInt("star"));
				campsite_commentVO.setCmnt_time(rs.getTimestamp("cmnt_time"));
				campsite_commentVO.setCmnt_stat(rs.getInt("cmnt_stat"));
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
		return campsite_commentVO;
	}

	@Override
	public List<Campsite_commentVO> getAll() {
		List<Campsite_commentVO> list = new ArrayList<Campsite_commentVO>();
		Campsite_commentVO campsite_commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// campsite_commentVO 也稱為 Domain objects
				campsite_commentVO = new Campsite_commentVO();
				campsite_commentVO.setCamp_cmnt_no(rs.getInt("camp_cmnt_no"));
				campsite_commentVO.setCamp_no(rs.getInt("camp_no"));
				campsite_commentVO.setMbr_no(rs.getInt("mbr_no"));
				campsite_commentVO.setCmnt_cont(rs.getString("cmnt_cont"));
				campsite_commentVO.setStar(rs.getInt("star"));
				campsite_commentVO.setCmnt_time(rs.getTimestamp("cmnt_time"));
				campsite_commentVO.setCmnt_stat(rs.getInt("cmnt_stat"));
				list.add(campsite_commentVO); // Store the row in the list
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