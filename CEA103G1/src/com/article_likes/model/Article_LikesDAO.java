package com.article_likes.model;

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


public class Article_LikesDAO implements Article_LikesDAO_Interface{

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
		"INSERT INTO Article_Likes (mbr_no, art_no) VALUES (?, ?)"; //按讚
//	private static final String GET_ALL_STMT = 
//		"SELECT mbr_no,art_no FROM Article_Likes order by art_no"; //按讚總表依照文章排列
//	private static final String GET_ONE_STMT = 
//		"SELECT mbr_no,art_no FROM Article_Likes WHERE mbr_no = ? AND art_no = ?";
	private static final String GET_Bymbr_no_STMT = 
		"SELECT mbr_no,art_no FROM Article_Likes WHERE mbr_no = ?";
	private static final String GET_Byart_no_STMT = 
		"SELECT mbr_no,art_no FROM Article_Likes WHERE art_no = ?";
	private static final String DELETE =  
		"DELETE FROM Article_Likes WHERE mbr_no = ? AND art_no = ?"; //收回讚
	
	@Override
	public void insert(Article_LikesVO Article_LikesVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, Article_LikesVO.getMbr_no());
			pstmt.setInt(2, Article_LikesVO.getArt_no());

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
	public void delete(Integer mbr_no, Integer art_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mbr_no);
			pstmt.setInt(2, art_no);

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
//	public Article_LikesVO findByPrimaryKey(Integer mbr_no, Integer art_no) {
//
//		Article_LikesVO Article_LikesVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, mbr_no);
//			pstmt.setInt(2, art_no);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				Article_LikesVO = new Article_LikesVO();
//				Article_LikesVO.setMbr_no(rs.getInt("mbr_no"));
//				Article_LikesVO.setArt_no(rs.getInt("art_no"));
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
//		return Article_LikesVO;
//	}
//	@Override
//	public List<Article_LikesVO> getAll() {
//		List<Article_LikesVO> list = new ArrayList<Article_LikesVO>();
//		Article_LikesVO Article_LikesVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				Article_LikesVO = new Article_LikesVO();
//				Article_LikesVO.setMbr_no(rs.getInt("mbr_no"));
//				Article_LikesVO.setArt_no(rs.getInt("art_no"));
//				list.add(Article_LikesVO); // Store the row in the list
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
//		return list;
//	}
	@Override
	public List<Article_LikesVO> findByMbr_no(Integer mbr_no) {
		List<Article_LikesVO> list = new ArrayList<Article_LikesVO>();
		Article_LikesVO Article_LikesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Bymbr_no_STMT);
			
			pstmt.setInt(1, mbr_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Article_LikesVO = new Article_LikesVO();
				Article_LikesVO.setMbr_no(rs.getInt("mbr_no"));
				Article_LikesVO.setArt_no(rs.getInt("art_no"));
				list.add(Article_LikesVO); // Store the row in the list
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
	public List<Article_LikesVO> findByArt_no(Integer art_no) {
		List<Article_LikesVO> list = new ArrayList<Article_LikesVO>();
		Article_LikesVO Article_LikesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Byart_no_STMT);
			
			pstmt.setInt(1, art_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Article_LikesVO = new Article_LikesVO();
				Article_LikesVO.setMbr_no(rs.getInt("mbr_no"));
				Article_LikesVO.setArt_no(rs.getInt("art_no"));
				list.add(Article_LikesVO); // Store the row in the list
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