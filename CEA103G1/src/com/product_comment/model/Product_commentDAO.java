package com.product_comment.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Product_commentDAO implements Product_commentDAO_interface {

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
		"INSERT INTO campion.product_comment (prod_no,mbr_no,cmnt_cont,cmnt_time,cmnt_stat) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT prod_cmnt_no,prod_no,mbr_no,cmnt_cont,cmnt_time,cmnt_stat FROM campion.product_comment order by prod_cmnt_no";
	private static final String GET_ONE_STMT = 
		"SELECT prod_cmnt_no,prod_no,mbr_no,cmnt_cont,cmnt_time,cmnt_stat FROM campion.product_comment where prod_cmnt_no = ?";
	private static final String DELETE = 
		"DELETE FROM campion.product_comment where prod_cmnt_no = ?";
	private static final String UPDATE = 
		"UPDATE campion.product_comment set prod_no=?, mbr_no=?, cmnt_cont=?, cmnt_time=?, cmnt_stat=? where prod_cmnt_no=?";

	@Override
	public void insert(Product_commentVO product_commentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, product_commentVO.getProd_no());
			pstmt.setInt(2, product_commentVO.getMbr_no());
			pstmt.setString(3, product_commentVO.getCmnt_cont());
			pstmt.setTimestamp(4, product_commentVO.getCmnt_time());
			pstmt.setInt(5, product_commentVO.getCmnt_stat());

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
	public void update(Product_commentVO product_commentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, product_commentVO.getProd_no());
			pstmt.setInt(2, product_commentVO.getMbr_no());
			pstmt.setString(3, product_commentVO.getCmnt_cont());
			pstmt.setTimestamp(4, product_commentVO.getCmnt_time());
			pstmt.setInt(5, product_commentVO.getCmnt_stat());
			pstmt.setInt(6, product_commentVO.getProd_cmnt_no());

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
	public void delete(Integer prod_cmnt_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, prod_cmnt_no);

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
	public Product_commentVO findByPrimaryKey(Integer prod_cmnt_no) {

		Product_commentVO product_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, prod_cmnt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// product_commentVO 也稱為 Domain objects
				product_commentVO = new Product_commentVO();
				product_commentVO.setProd_cmnt_no(rs.getInt("prod_cmnt_no"));
				product_commentVO.setProd_no(rs.getInt("prod_no"));
				product_commentVO.setMbr_no(rs.getInt("mbr_no"));
				product_commentVO.setCmnt_cont(rs.getString("cmnt_cont"));
				product_commentVO.setCmnt_time(rs.getTimestamp("cmnt_time"));
				product_commentVO.setCmnt_stat(rs.getInt("cmnt_stat"));
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
		return product_commentVO;
	}

	@Override
	public List<Product_commentVO> getAll() {
		List<Product_commentVO> list = new ArrayList<Product_commentVO>();
		Product_commentVO product_commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// product_commentVO 也稱為 Domain objects
				product_commentVO = new Product_commentVO();
				product_commentVO.setProd_cmnt_no(rs.getInt("prod_cmnt_no"));
				product_commentVO.setProd_no(rs.getInt("prod_no"));
				product_commentVO.setMbr_no(rs.getInt("mbr_no"));
				product_commentVO.setCmnt_cont(rs.getString("cmnt_cont"));
				product_commentVO.setCmnt_time(rs.getTimestamp("cmnt_time"));
				product_commentVO.setCmnt_stat(rs.getInt("cmnt_stat"));
				list.add(product_commentVO); // Store the row in the list
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
	public List<Product_commentVO> getTimestampProd_no(Timestamp cmnt_time) {
		// TODO Auto-generated method stub
		return null;
	}
}