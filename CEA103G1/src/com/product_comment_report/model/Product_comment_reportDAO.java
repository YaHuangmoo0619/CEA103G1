package com.product_comment_report.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Product_comment_reportDAO implements Product_comment_reportDAO_interface {

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
		"INSERT INTO campion.product_comment_report (mbr_no,prod_cmnt_no,rpt_cont,rpt_time,proc_stat) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT prod_cmnt_rpt_no,mbr_no,prod_cmnt_no,rpt_cont,rpt_time,proc_stat FROM campion.product_comment_report order by prod_cmnt_rpt_no";
	private static final String GET_ONE_STMT = 
		"SELECT prod_cmnt_rpt_no,mbr_no,prod_cmnt_no,rpt_cont,rpt_time,proc_stat FROM campion.product_comment_report where prod_cmnt_rpt_no = ?";
	private static final String DELETE = 
		"DELETE FROM campion.product_comment_report where prod_cmnt_rpt_no = ?";
	private static final String UPDATE = 
		"UPDATE campion.product_comment_report set mbr_no=?, prod_cmnt_no=?, rpt_cont=?, rpt_time=?, proc_stat=? where prod_cmnt_rpt_no=?";

	@Override
	public void insert(Product_comment_reportVO product_comment_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, product_comment_reportVO.getMbr_no());
			pstmt.setInt(2, product_comment_reportVO.getProd_cmnt_no());
			pstmt.setString(3, product_comment_reportVO.getRpt_cont());
			pstmt.setTimestamp(4, product_comment_reportVO.getRpt_time());
			pstmt.setInt(5, product_comment_reportVO.getProc_stat());

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
	public void update(Product_comment_reportVO product_comment_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, product_comment_reportVO.getMbr_no());
			pstmt.setInt(2, product_comment_reportVO.getProd_cmnt_no());
			pstmt.setString(3, product_comment_reportVO.getRpt_cont());
			pstmt.setTimestamp(4, product_comment_reportVO.getRpt_time());
			pstmt.setInt(5, product_comment_reportVO.getProc_stat());
			pstmt.setInt(6, product_comment_reportVO.getProd_cmnt_rpt_no());

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
	public void delete(Integer prod_cmnt_rpt_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, prod_cmnt_rpt_no);

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
	public Product_comment_reportVO findByPrimaryKey(Integer prod_cmnt_rpt_no) {

		Product_comment_reportVO product_comment_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, prod_cmnt_rpt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// product_comment_reportVO 也稱為 Domain objects
				product_comment_reportVO = new Product_comment_reportVO();
				product_comment_reportVO.setProd_cmnt_rpt_no(rs.getInt("prod_cmnt_rpt_no"));
				product_comment_reportVO.setMbr_no(rs.getInt("mbr_no"));
				product_comment_reportVO.setProd_cmnt_no(rs.getInt("prod_cmnt_no"));
				product_comment_reportVO.setRpt_cont(rs.getString("rpt_cont"));
				product_comment_reportVO.setRpt_time(rs.getTimestamp("rpt_time"));
				product_comment_reportVO.setProc_stat(rs.getInt("proc_stat"));
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
		return product_comment_reportVO;
	}

	@Override
	public List<Product_comment_reportVO> getAll() {
		List<Product_comment_reportVO> list = new ArrayList<Product_comment_reportVO>();
		Product_comment_reportVO product_comment_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// product_comment_reportVO 也稱為 Domain objects
				product_comment_reportVO = new Product_comment_reportVO();
				product_comment_reportVO.setProd_cmnt_rpt_no(rs.getInt("prod_cmnt_rpt_no"));
				product_comment_reportVO.setMbr_no(rs.getInt("mbr_no"));
				product_comment_reportVO.setProd_cmnt_no(rs.getInt("prod_cmnt_no"));
				product_comment_reportVO.setRpt_cont(rs.getString("rpt_cont"));
				product_comment_reportVO.setRpt_time(rs.getTimestamp("rpt_time"));
				product_comment_reportVO.setProc_stat(rs.getInt("proc_stat"));
				list.add(product_comment_reportVO); // Store the row in the list
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
	public List<Product_comment_reportVO> getTimestampProd_cmnt_no(Timestamp rpt_time) {
		// TODO Auto-generated method stub
		return null;
	}
}