package com.product_order_details.model;

import java.util.*;
import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;
import com.product_order_details.*;

public class Product_order_detailsDAO implements Product_order_detailsDAO_interface {

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
		"insert into PRODUCT_ORDER_DETAILS (PROD_ORD_NO, PROD_NO, PROD_AMT, PROD_UNIT_PC) values (?, ?, ?, ?)";
	private static final String UPDATE = 
		"update PRODUCT_ORDER_DETAILS set PROD_AMT, PROD_UNIT_PC where PROD_ORD_NO = ? and PROD_NO = ?";
	private static final String DELETE = 
		"delete from PRODUCT_ORDER_DETAILS where PROD_ORD_NO = ? and PROD_NO = ?";
	private static final String GET_ONE_STMT = 
		"select * from PRODUCT_ORDER_DETAILS where PROD_ORD_NO = ? and PROD_NO = ?";
	private static final String GET_PROD_ORD_STMT = 
		"select * from PRODUCT_ORDER_DETAILS where PROD_ORD_NO = ?";
//	private static final String GET_PROD_STMT = 
//		"select * from PRODUCT_ORDER_DETAILS where PROD_NO = ?";
	private static final String GET_ALL_STMT = 
		"select * from PRODUCT_ORDER_DETAILS order by PROD_ORD_NO";

	@Override
	public void insert(Product_order_detailsVO product_order_detailsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, product_order_detailsVO.getProd_ord_no());
			pstmt.setInt(2, product_order_detailsVO.getProd_no());
			pstmt.setInt(3, product_order_detailsVO.getProd_amt());
			pstmt.setInt(4, product_order_detailsVO.getProd_unit_pc());
			
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

	public void insertOrder(Product_order_detailsVO product_order_detailsVO, Connection con) {
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, product_order_detailsVO.getProd_ord_no());
			pstmt.setInt(2, product_order_detailsVO.getProd_no());

			Statement stmt = con.createStatement();
			//stmt.executeUpdate("set auto_increment_offset=7001;"); //自增主鍵-初始值
			stmt.executeUpdate("set auto_increment_increment=1;"); // 自增主鍵-遞增
			pstmt.executeUpdate();

		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back(ord_detail)");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}
	
	@Override
	public void update(Product_order_detailsVO product_order_detailsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, product_order_detailsVO.getProd_amt());
			pstmt.setInt(2, product_order_detailsVO.getProd_unit_pc());
			pstmt.setInt(3, product_order_detailsVO.getProd_ord_no());
			pstmt.setInt(4, product_order_detailsVO.getProd_no());

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
	public void delete(Integer prod_ord_no, Integer prod_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, prod_ord_no);
			pstmt.setInt(2, prod_no);

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
	public Product_order_detailsVO findByPrimaryKey(Integer prod_ord_no, Integer prod_no) {

		Product_order_detailsVO product_order_detailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, prod_ord_no);
			pstmt.setInt(2, prod_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_order_detailsVO = new Product_order_detailsVO();
				product_order_detailsVO.setProd_ord_no(rs.getInt("PROD_ORD_NO"));
				product_order_detailsVO.setProd_no(rs.getInt("PROD_NO"));
				product_order_detailsVO.setProd_amt(rs.getInt("PROD_AMT"));
				product_order_detailsVO.setProd_unit_pc(rs.getInt("PROD_UNIT_PC"));
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
		return product_order_detailsVO;
	}
	
	@Override
	public List<Product_order_detailsVO> findByProd_ord_no(Integer prod_ord_no) {

		List<Product_order_detailsVO> list = new ArrayList<Product_order_detailsVO>();
		Product_order_detailsVO product_order_detailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PROD_ORD_STMT);

			pstmt.setInt(1, prod_ord_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_order_detailsVO = new Product_order_detailsVO();
				product_order_detailsVO.setProd_ord_no(rs.getInt("PROD_ORD_NO"));
				product_order_detailsVO.setProd_no(rs.getInt("PROD_NO"));
				product_order_detailsVO.setProd_amt(rs.getInt("PROD_AMT"));
				product_order_detailsVO.setProd_unit_pc(rs.getInt("PROD_UNIT_PC"));
				//雅凰加的
				list.add(product_order_detailsVO);
				//雅凰加的
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
	
//	@Override
//	public List<Product_order_detailsVO> findByProd_no(Integer prod_no) {
//		List<Product_order_detailsVO> list = new ArrayList<Product_order_detailsVO>();
//		Product_order_detailsVO product_order_detailsVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_PROD_STMT);
//
//			pstmt.setInt(1, prod_no);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				product_order_detailsVO = new Product_order_detailsVO();
//				product_order_detailsVO.setProd_ord_no(rs.getInt("PROD_ORD_NO"));
//				product_order_detailsVO.setProd_no(rs.getInt("PROD_NO"));
//				product_order_detailsVO.setProd_amt(rs.getInt("PROD_AMT"));
//				product_order_detailsVO.setProd_unit_pc(rs.getInt("PROD_UNIT_PC"));
//				list.add(product_order_detailsVO); 
//			}
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//
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
	public List<Product_order_detailsVO> getAll() {
		List<Product_order_detailsVO> list = new ArrayList<Product_order_detailsVO>();
		Product_order_detailsVO product_order_detailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_order_detailsVO = new Product_order_detailsVO();
				product_order_detailsVO.setProd_ord_no(rs.getInt("PROD_ORD_NO"));
				product_order_detailsVO.setProd_no(rs.getInt("PROD_NO"));
				product_order_detailsVO.setProd_amt(rs.getInt("PROD_AMT"));
				product_order_detailsVO.setProd_unit_pc(rs.getInt("PROD_UNIT_PC"));
				list.add(product_order_detailsVO); 
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