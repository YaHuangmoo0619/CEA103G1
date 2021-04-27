package com.campsite_comment_report.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Campsite_comment_reportDAO implements Campsite_comment_reportDAO_interface {

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
		"INSERT INTO campion.campsite_comment_report (camp_cmnt_no,mbr_no,rpt_cont,rpt_time,proc_stat) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT camp_cmnt_rpt_no,camp_cmnt_no,mbr_no,rpt_cont,rpt_time,proc_stat FROM campion.campsite_comment_report order by camp_cmnt_rpt_no";
	private static final String GET_ONE_STMT = 
		"SELECT camp_cmnt_rpt_no,camp_cmnt_no,mbr_no,rpt_cont,rpt_time,proc_stat FROM campion.campsite_comment_report where camp_cmnt_rpt_no = ?";
	private static final String DELETE = 
		"DELETE FROM campion.campsite_comment_report where camp_cmnt_rpt_no = ?";
	private static final String UPDATE = 
		"UPDATE campion.campsite_comment_report set camp_cmnt_no=? ,mbr_no=? ,rpt_cont=? ,rpt_time=? ,proc_stat=? where camp_cmnt_rpt_no=?";

	@Override
	public void insert(Campsite_comment_reportVO campsite_comment_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, campsite_comment_reportVO.getCamp_cmnt_no());
			pstmt.setInt(2, campsite_comment_reportVO.getMbr_no());
			pstmt.setString(3, campsite_comment_reportVO.getRpt_cont());
			pstmt.setTimestamp(4, campsite_comment_reportVO.getRpt_time());
			pstmt.setInt(5, campsite_comment_reportVO.getProc_stat());

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
	public void update(Campsite_comment_reportVO campsite_comment_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, campsite_comment_reportVO.getCamp_cmnt_no());
			pstmt.setInt(2, campsite_comment_reportVO.getMbr_no());
			pstmt.setString(3, campsite_comment_reportVO.getRpt_cont());
			pstmt.setTimestamp(4, campsite_comment_reportVO.getRpt_time());
			pstmt.setInt(5, campsite_comment_reportVO.getProc_stat());
			pstmt.setInt(6, campsite_comment_reportVO.getCamp_cmnt_rpt_no());

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
	public void delete(Integer camp_cmnt_rpt_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, camp_cmnt_rpt_no);

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
	public Campsite_comment_reportVO findByPrimaryKey(Integer camp_cmnt_rpt_no) {

		Campsite_comment_reportVO campsite_comment_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, camp_cmnt_rpt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// campsite_comment_reportVO 也稱為 Domain objects
				campsite_comment_reportVO = new Campsite_comment_reportVO();
				campsite_comment_reportVO.setCamp_cmnt_rpt_no(rs.getInt("camp_cmnt_rpt_no"));
				campsite_comment_reportVO.setCamp_cmnt_no(rs.getInt("camp_cmnt_no"));
				campsite_comment_reportVO.setMbr_no(rs.getInt("mbr_no"));
				campsite_comment_reportVO.setRpt_cont(rs.getString("rpt_cont"));
				campsite_comment_reportVO.setRpt_time(rs.getTimestamp("rpt_time"));
				campsite_comment_reportVO.setProc_stat(rs.getInt("proc_stat"));
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
		return campsite_comment_reportVO;
	}

	@Override
	public List<Campsite_comment_reportVO> getAll() {
		List<Campsite_comment_reportVO> list = new ArrayList<Campsite_comment_reportVO>();
		Campsite_comment_reportVO campsite_comment_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// campsite_comment_reportVO 也稱為 Domain objects
				campsite_comment_reportVO = new Campsite_comment_reportVO();
				campsite_comment_reportVO = new Campsite_comment_reportVO();
				campsite_comment_reportVO.setCamp_cmnt_rpt_no(rs.getInt("camp_cmnt_rpt_no"));
				campsite_comment_reportVO.setCamp_cmnt_no(rs.getInt("camp_cmnt_no"));
				campsite_comment_reportVO.setMbr_no(rs.getInt("mbr_no"));
				campsite_comment_reportVO.setRpt_cont(rs.getString("rpt_cont"));
				campsite_comment_reportVO.setRpt_time(rs.getTimestamp("rpt_time"));
				campsite_comment_reportVO.setProc_stat(rs.getInt("proc_stat"));
				list.add(campsite_comment_reportVO); // Store the row in the list
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