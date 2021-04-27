package com.product_report.model;

import java.util.*;
import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;

public class Product_reportDAO implements Product_reportDAO_interface {

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
		"insert into PRODUCT_REPORT (MBR_NO, PROD_NO, RPT_CONT, RPT_TIME, PROC_STAT) values (?, ?, ?, ?, ?)";
	private static final String UPDATE = 
		"update PRODUCT_REPORT set MBR_NO = ?, PROD_NO = ?, RPT_CONT = ?, RPT_TIME = ?, PROC_STAT = ? where PROD_RPT_NO = ?";
	private static final String DELETE = 
		"delete from PRODUCT_REPORT where PROD_RPT_NO = ?";
	private static final String GET_ONE_STMT = 
		"select * from PRODUCT_REPORT where PROD_RPT_NO = ?";
	private static final String GET_ALL_STMT = 
		"select * from PRODUCT_REPORT order by PROD_RPT_NO";

	@Override
	public void insert(Product_reportVO product_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, product_reportVO.getMbr_no());
			pstmt.setInt(2, product_reportVO.getProd_no());
			pstmt.setString(3, product_reportVO.getRpt_cont());
			pstmt.setTimestamp(4, product_reportVO.getRpt_time());
			pstmt.setInt(5, product_reportVO.getProc_stat());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
	public void update(Product_reportVO product_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, product_reportVO.getMbr_no());
			pstmt.setInt(2, product_reportVO.getProd_no());
			pstmt.setString(3, product_reportVO.getRpt_cont());
			pstmt.setTimestamp(4, product_reportVO.getRpt_time());
			pstmt.setInt(5, product_reportVO.getProc_stat());
			pstmt.setInt(6, product_reportVO.getProd_rpt_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(Integer prod_rpt_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, prod_rpt_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
	public Product_reportVO findByPrimaryKey(Integer prod_rpt_no) {

		Product_reportVO product_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, prod_rpt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_reportVO = new Product_reportVO();
				product_reportVO.setProd_rpt_no(rs.getInt("PROD_RPT_NO"));
				product_reportVO.setMbr_no(rs.getInt("MBR_NO"));
				product_reportVO.setProd_no(rs.getInt("PROD_NO"));
				product_reportVO.setRpt_cont(rs.getString("RPT_CONT"));
				product_reportVO.setRpt_time(rs.getTimestamp("RPT_TIME"));
				product_reportVO.setProc_stat(rs.getInt("PROC_STAT"));
				
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
		return product_reportVO;
	}

	@Override
	public List<Product_reportVO> getAll() {
		List<Product_reportVO> list = new ArrayList<Product_reportVO>();
		Product_reportVO product_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_reportVO = new Product_reportVO();
				product_reportVO.setProd_rpt_no(rs.getInt("PROD_RPT_NO"));
				product_reportVO.setMbr_no(rs.getInt("MBR_NO"));
				product_reportVO.setProd_no(rs.getInt("PROD_NO"));
				product_reportVO.setRpt_cont(rs.getString("RPT_CONT"));
				product_reportVO.setRpt_time(rs.getTimestamp("RPT_TIME"));
				product_reportVO.setProc_stat(rs.getInt("PROC_STAT"));
				list.add(product_reportVO); 
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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