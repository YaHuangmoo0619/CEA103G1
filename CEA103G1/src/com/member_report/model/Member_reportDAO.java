package com.member_report.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Member_reportDAO implements Member_reportDAO_interface {

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
		"INSERT INTO campion.member_report (rpted_mbr_no,rpt_mbr_no,rpt_cont,rpt_time,proc_stat) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT mbr_rpt_no,rpted_mbr_no,rpt_mbr_no,rpt_cont,rpt_time,proc_stat FROM campion.member_report order by mbr_rpt_no";
	private static final String GET_ONE_STMT = 
		"SELECT mbr_rpt_no,rpted_mbr_no,rpt_mbr_no,rpt_cont,rpt_time,proc_stat FROM campion.member_report where mbr_rpt_no = ?";
	private static final String DELETE = 
		"DELETE FROM campion.member_report where mbr_rpt_no = ?";
	private static final String UPDATE = 
		"UPDATE campion.member_report set rpted_mbr_no=?, rpt_mbr_no=?, rpt_cont=?, rpt_time=?, proc_stat=? where mbr_rpt_no=?";

	@Override
	public void insert(Member_reportVO member_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, member_reportVO.getRpted_mbr_no());
			pstmt.setInt(2, member_reportVO.getRpt_mbr_no());
			pstmt.setString(3, member_reportVO.getRpt_cont());
			pstmt.setTimestamp(4, member_reportVO.getRpt_time());
			pstmt.setInt(5, member_reportVO.getProc_stat());

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
	public void update(Member_reportVO member_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, member_reportVO.getRpted_mbr_no());
			pstmt.setInt(2, member_reportVO.getRpt_mbr_no());
			pstmt.setString(3, member_reportVO.getRpt_cont());
			pstmt.setTimestamp(4, member_reportVO.getRpt_time());
			pstmt.setInt(5, member_reportVO.getProc_stat());
			pstmt.setInt(6, member_reportVO.getMbr_rpt_no());

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
	public void delete(Integer mbr_rpt_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mbr_rpt_no);

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
	public Member_reportVO findByPrimaryKey(Integer mbr_rpt_no) {

		Member_reportVO member_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mbr_rpt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// member_reportVO 也稱為 Domain objects
				member_reportVO = new Member_reportVO();
				member_reportVO.setMbr_rpt_no(rs.getInt("mbr_rpt_no"));
				member_reportVO.setRpted_mbr_no(rs.getInt("rpted_mbr_no"));
				member_reportVO.setRpt_mbr_no(rs.getInt("rpt_mbr_no"));
				member_reportVO.setRpt_cont(rs.getString("rpt_cont"));
				member_reportVO.setRpt_time(rs.getTimestamp("rpt_time"));
				member_reportVO.setProc_stat(rs.getInt("proc_stat"));
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
		return member_reportVO;
	}

	@Override
	public List<Member_reportVO> getAll() {
		List<Member_reportVO> list = new ArrayList<Member_reportVO>();
		Member_reportVO member_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// member_reportVO 也稱為 Domain objects
				member_reportVO = new Member_reportVO();
				member_reportVO.setMbr_rpt_no(rs.getInt("mbr_rpt_no"));
				member_reportVO.setRpted_mbr_no(rs.getInt("rpted_mbr_no"));
				member_reportVO.setRpt_mbr_no(rs.getInt("rpt_mbr_no"));
				member_reportVO.setRpt_cont(rs.getString("rpt_cont"));
				member_reportVO.setRpt_time(rs.getTimestamp("rpt_time"));
				member_reportVO.setProc_stat(rs.getInt("proc_stat"));
				list.add(member_reportVO); // Store the row in the list
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