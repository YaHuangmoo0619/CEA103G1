package com.campsite_report.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Campsite_reportDAO implements Campsite_reportDAO_interface {

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
		"INSERT INTO campion.campsite_report (mbr_no,camp_no,rpt_cont,rpt_time,proc_stat) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT camp_rpt_no,mbr_no,camp_no,rpt_cont,rpt_time,proc_stat FROM campion.campsite_report order by camp_rpt_no";
	private static final String GET_ONE_STMT = 
		"SELECT camp_rpt_no,mbr_no,camp_no,rpt_cont,rpt_time,proc_stat FROM campion.campsite_report where camp_rpt_no = ?";
	private static final String DELETE = 
		"DELETE FROM campion.campsite_report where camp_rpt_no = ?";
	private static final String UPDATE = 
		"UPDATE campion.campsite_report set mbr_no=?, camp_no=?, rpt_cont=?, rpt_time=?, proc_stat=? where camp_rpt_no=?";

	@Override
	public void insert(Campsite_reportVO campsite_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, campsite_reportVO.getMbr_no());
			pstmt.setInt(2, campsite_reportVO.getCamp_no());
			pstmt.setString(3, campsite_reportVO.getRpt_cont());
			pstmt.setTimestamp(4, campsite_reportVO.getRpt_time());
			pstmt.setInt(5, campsite_reportVO.getProc_stat());

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
	public void update(Campsite_reportVO campsite_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, campsite_reportVO.getMbr_no());
			pstmt.setInt(2, campsite_reportVO.getCamp_no());
			pstmt.setString(3, campsite_reportVO.getRpt_cont());
			pstmt.setTimestamp(4, campsite_reportVO.getRpt_time());
			pstmt.setInt(5, campsite_reportVO.getProc_stat());
			pstmt.setInt(6, campsite_reportVO.getCamp_rpt_no());

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
	public void delete(Integer camp_rpt_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, camp_rpt_no);

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
	public Campsite_reportVO findByPrimaryKey(Integer camp_rpt_no) {

		Campsite_reportVO campsite_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, camp_rpt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// campsite_reportVO 也稱為 Domain objects
				campsite_reportVO = new Campsite_reportVO();
				campsite_reportVO.setCamp_rpt_no(rs.getInt("camp_rpt_no"));
				campsite_reportVO.setMbr_no(rs.getInt("mbr_no"));
				campsite_reportVO.setCamp_no(rs.getInt("camp_no"));
				campsite_reportVO.setRpt_cont(rs.getString("rpt_cont"));
				campsite_reportVO.setRpt_time(rs.getTimestamp("rpt_time"));
				campsite_reportVO.setProc_stat(rs.getInt("proc_stat"));
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
		return campsite_reportVO;
	}

	@Override
	public List<Campsite_reportVO> getAll() {
		List<Campsite_reportVO> list = new ArrayList<Campsite_reportVO>();
		Campsite_reportVO campsite_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// campsite_reportVO 也稱為 Domain objects
				campsite_reportVO = new Campsite_reportVO();
				campsite_reportVO.setCamp_rpt_no(rs.getInt("camp_rpt_no"));
				campsite_reportVO.setMbr_no(rs.getInt("mbr_no"));
				campsite_reportVO.setCamp_no(rs.getInt("camp_no"));
				campsite_reportVO.setRpt_cont(rs.getString("rpt_cont"));
				campsite_reportVO.setRpt_time(rs.getTimestamp("rpt_time"));
				campsite_reportVO.setProc_stat(rs.getInt("proc_stat"));
				list.add(campsite_reportVO); // Store the row in the list
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