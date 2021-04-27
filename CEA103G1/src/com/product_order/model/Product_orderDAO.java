package com.product_order.model;

import java.util.*;
import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;

public class Product_orderDAO implements Product_orderDAO_interface {

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
		"insert into Product_Order (MBR_NO, PROD_ORD_TIME, PROD_ORD_STAT, PROD_ORD_SUM, USED_PT, SHIP_METH, PAY_METH, SHIP_CTY, SHIP_DIST, SHIP_ADD, RECEIPT, RMK) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
		"update Product_Order set MBR_NO=?, PROD_ORD_TIME=?, PROD_ORD_STAT=?, PROD_ORD_SUM=?, USED_PT=?, SHIP_METH=?, PAY_METH=?, SHIP_CTY=?, SHIP_DIST=?, SHIP_ADD=?, RECEIPT=?, RMK=? where PROD_ORD_NO = ?";
	private static final String DELETE = 
		"delete from Product_Order where PROD_ORD_NO = ?";
	private static final String GET_ONE_STMT = 
		"select * from Product_Order where PROD_ORD_NO = ?";
	private static final String GET_ALL_STMT = 
		"select * from Product_Order order by PROD_ORD_NO";
		
	@Override
	public void insert(Product_orderVO product_orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, product_orderVO.getMbr_no());
			pstmt.setTimestamp(2, product_orderVO.getProd_ord_time());
			pstmt.setInt(3, product_orderVO.getProd_ord_stat());
			pstmt.setInt(4, product_orderVO.getProd_ord_sum());
			pstmt.setInt(5, product_orderVO.getUsed_pt());
			pstmt.setInt(6, product_orderVO.getShip_meth());
			pstmt.setInt(7, product_orderVO.getPay_meth());
			pstmt.setString(8, product_orderVO.getShip_cty());
			pstmt.setString(9, product_orderVO.getShip_dist());
			pstmt.setString(10, product_orderVO.getShip_add());
			pstmt.setInt(11, product_orderVO.getReceipt());
			pstmt.setString(12, product_orderVO.getRmk());

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
	public void update(Product_orderVO product_orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, product_orderVO.getMbr_no());
			pstmt.setTimestamp(2, product_orderVO.getProd_ord_time());
			pstmt.setInt(3, product_orderVO.getProd_ord_stat());
			pstmt.setInt(4, product_orderVO.getProd_ord_sum());
			pstmt.setInt(5, product_orderVO.getUsed_pt());
			pstmt.setInt(6, product_orderVO.getShip_meth());
			pstmt.setInt(7, product_orderVO.getPay_meth());
			pstmt.setString(8, product_orderVO.getShip_cty());
			pstmt.setString(9, product_orderVO.getShip_dist());
			pstmt.setString(10, product_orderVO.getShip_add());
			pstmt.setInt(11, product_orderVO.getReceipt());
			pstmt.setString(12, product_orderVO.getRmk());

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
	public void delete(Integer prod_ord_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, prod_ord_no);

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
	public Product_orderVO findByPrimaryKey(Integer prod_ord_no) {

		Product_orderVO product_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, prod_ord_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_orderVO = new Product_orderVO();
				product_orderVO.setProd_ord_no(rs.getInt("PROD_ORD_NO"));
				product_orderVO.setMbr_no(rs.getInt("MBR_NO"));
				product_orderVO.setProd_ord_time(rs.getTimestamp("PROD_ORD_TIME"));
				product_orderVO.setProd_ord_stat(rs.getInt("PROD_ORD_STAT"));
				product_orderVO.setProd_ord_sum(rs.getInt("PROD_ORD_SUM"));
				product_orderVO.setUsed_pt(rs.getInt("USED_PT"));
				product_orderVO.setShip_meth(rs.getInt("SHIP_METH"));
				product_orderVO.setPay_meth(rs.getInt("PAY_METH"));
				product_orderVO.setShip_cty(rs.getString("SHIP_CTY"));
				product_orderVO.setShip_dist(rs.getString("SHIP_DIST"));
				product_orderVO.setShip_add(rs.getString("SHIP_ADD"));
				product_orderVO.setReceipt(rs.getInt("RECEIPT"));
				product_orderVO.setRmk(rs.getString("RMK"));
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
		return product_orderVO;
	}

	@Override
	public List<Product_orderVO> getAll() {
		List<Product_orderVO> list = new ArrayList<Product_orderVO>();
		Product_orderVO product_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_orderVO = new Product_orderVO();
				product_orderVO.setProd_ord_no(rs.getInt("PROD_ORD_NO"));
				product_orderVO.setMbr_no(rs.getInt("MBR_NO"));
				product_orderVO.setProd_ord_time(rs.getTimestamp("PROD_ORD_TIME"));
				product_orderVO.setProd_ord_stat(rs.getInt("PROD_ORD_STAT"));
				product_orderVO.setProd_ord_sum(rs.getInt("PROD_ORD_SUM"));
				product_orderVO.setUsed_pt(rs.getInt("USED_PT"));
				product_orderVO.setShip_meth(rs.getInt("SHIP_METH"));
				product_orderVO.setPay_meth(rs.getInt("PAY_METH"));
				product_orderVO.setShip_cty(rs.getString("SHIP_CTY"));
				product_orderVO.setShip_dist(rs.getString("SHIP_DIST"));
				product_orderVO.setShip_add(rs.getString("SHIP_ADD"));
				product_orderVO.setReceipt(rs.getInt("RECEIPT"));
				product_orderVO.setRmk(rs.getString("RMK"));
				list.add(product_orderVO); 
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