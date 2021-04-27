package com.article_collection.model;

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

import com.article.model.ArticleDAO_Interface;

public class Article_CollectionDAO implements Article_CollectionDAO_Interface{

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
		"INSERT INTO Article_Collection (mbr_no, art_no) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT mbr_no,art_no FROM Article_Collection order by mbr_no";
	private static final String GET_Bymbr_no_STMT = 
		"SELECT mbr_no,art_no FROM Article_Collection WHERE mbr_no = ?";
	private static final String GET_Byart_no_STMT = 
		"SELECT mbr_no,art_no FROM Article_Collection WHERE art_no = ?";
	private static final String DELETE = 
		"DELETE FROM Article_Collection WHERE mbr_no = ? AND art_no = ?";
	
	@Override
	public void insert(Article_CollectionVO Article_CollectionVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, Article_CollectionVO.getMbr_no());
			pstmt.setInt(2, Article_CollectionVO.getArt_no());

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
	
	@Override
	public List<Article_CollectionVO> getAll() {
		List<Article_CollectionVO> list = new ArrayList<Article_CollectionVO>();
		Article_CollectionVO Article_CollectionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Article_CollectionVO = new Article_CollectionVO();
				Article_CollectionVO.setMbr_no(rs.getInt("mbr_no"));
				Article_CollectionVO.setArt_no(rs.getInt("art_no"));
				list.add(Article_CollectionVO); // Store the row in the list
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
	public List<Article_CollectionVO> findByMbr_no(Integer mbr_no) {
		List<Article_CollectionVO> list = new ArrayList<Article_CollectionVO>();
		Article_CollectionVO Article_CollectionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Bymbr_no_STMT);
			
			pstmt.setInt(1, mbr_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Article_CollectionVO = new Article_CollectionVO();
				Article_CollectionVO.setMbr_no(rs.getInt("mbr_no"));
				Article_CollectionVO.setArt_no(rs.getInt("art_no"));
				list.add(Article_CollectionVO); // Store the row in the list
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
	public List<Article_CollectionVO> findByArt_no(Integer Art_no) {
		List<Article_CollectionVO> list = new ArrayList<Article_CollectionVO>();
		Article_CollectionVO Article_CollectionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Byart_no_STMT);
			
			pstmt.setInt(1, Art_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Article_CollectionVO = new Article_CollectionVO();
				Article_CollectionVO.setMbr_no(rs.getInt("mbr_no"));
				Article_CollectionVO.setArt_no(rs.getInt("art_no"));
				list.add(Article_CollectionVO); // Store the row in the list
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