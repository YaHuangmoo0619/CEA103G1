package com.follow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class FollowDAO implements FollowDAO_Interface{

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
		"INSERT INTO Follow (flwed_mbr_no, flw_mbr_no) VALUES (?, ?)"; //新增一筆訂閱關係
	private static final String GET_ALL_STMT = 
		"SELECT flwed_mbr_no,flw_mbr_no FROM Follow order by flw_mbr_no"; //列出全部的訂閱關係
	private static final String GET_Byflwed_mbrno_STMT = 
		"SELECT flwed_mbr_no,flw_mbr_no FROM Follow WHERE flwed_mbr_no = ?"; //看一個人的粉絲有哪些
	private static final String GET_Byflw_mbr_no_STMT = 
		"SELECT flwed_mbr_no,flw_mbr_no FROM Follow WHERE flw_mbr_no = ?"; //看一個人追蹤哪些人
	private static final String DELETE = 
		"DELETE FROM Follow WHERE flwed_mbr_no = ? AND flw_mbr_no = ?"; //刪除一筆訂閱關係
	
	@Override
	public void insert(FollowVO FollowVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, FollowVO.getFlwed_mbr_no());
			pstmt.setInt(2, FollowVO.getFlw_mbr_no());

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
	public void delete(Integer flwed_mbr_no, Integer flw_mbr_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, flwed_mbr_no);
			pstmt.setInt(2, flw_mbr_no);

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
//	@Override
//	public FollowVO findByPrimaryKey(Integer flwed_mbr_no, Integer flw_mbr_no) {
//
//		FollowVO FollowVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, flwed_mbr_no);
//			pstmt.setInt(2, flw_mbr_no);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				FollowVO = new FollowVO();
//				FollowVO.setFlwed_mbr_no(rs.getInt("flwed_mbr_no"));
//				FollowVO.setFlw_mbr_no(rs.getInt("flw_mbr_no"));
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return FollowVO;
//	}
	@Override //列出所有訂閱關係
	public List<FollowVO> getAll() {
		List<FollowVO> list = new ArrayList<FollowVO>();
		FollowVO FollowVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				FollowVO = new FollowVO();
				FollowVO.setFlwed_mbr_no(rs.getInt("flwed_mbr_no"));
				FollowVO.setFlw_mbr_no(rs.getInt("flw_mbr_no"));
				list.add(FollowVO); // Store the row in the list
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
	@Override //看一個人有哪些粉絲
	public List<FollowVO> findByflwed_mbr_no(Integer flwed_mbr_no) {
		List<FollowVO> list = new ArrayList<FollowVO>();
		FollowVO FollowVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Byflwed_mbrno_STMT);
			
			pstmt.setInt(1, flwed_mbr_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				FollowVO = new FollowVO();
				FollowVO.setFlwed_mbr_no(rs.getInt("flwed_mbr_no"));
				FollowVO.setFlw_mbr_no(rs.getInt("flw_mbr_no"));
				list.add(FollowVO); // Store the row in the list
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
	
	@Override //看一個人追蹤哪些人
	public List<FollowVO> findByflw_mbr_no(Integer flw_mbr_no) {
		List<FollowVO> list = new ArrayList<FollowVO>();
		FollowVO FollowVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Byflw_mbr_no_STMT);
			
			pstmt.setInt(1, flw_mbr_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				FollowVO = new FollowVO();
				FollowVO.setFlwed_mbr_no(rs.getInt("flwed_mbr_no"));
				FollowVO.setFlw_mbr_no(rs.getInt("flw_mbr_no"));
				list.add(FollowVO); // Store the row in the list
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
