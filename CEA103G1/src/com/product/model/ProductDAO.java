package com.product.model;

import java.util.*;
import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;

public class ProductDAO implements ProductDAO_interface {

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
		"insert into PRODUCT (PROD_CAT_NO, PROD_STAT, PROD_NAME, PROD_PC, PROD_STG, PROD_INFO, PROD_BND, PROD_CLR, PROD_SIZE, SHIP_METH) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
		"update PRODUCT set PROD_CAT_NO = ?, PROD_STAT = ?, PROD_NAME = ?, PROD_PC = ?, PROD_STG = ?, PROD_INFO = ?, PROD_BND = ?, PROD_CLR = ?, PROD_SIZE = ?, SHIP_METH = ? where PROD_NO = ?";
	private static final String DELETE = 
		"delete from PRODUCT where PROD_NO = ?";
	private static final String GET_ONE_STMT = 
		"select * from PRODUCT where PROD_NO = ?";
	private static final String GET_ALL_STMT = 
		"select * from PRODUCT order by PROD_NO";

	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productVO.getProd_cat_no());
			pstmt.setInt(2, productVO.getProd_stat());
			pstmt.setString(3, productVO.getProd_name());
			pstmt.setInt(4, productVO.getProd_pc());
			pstmt.setInt(5, productVO.getProd_stg());
			pstmt.setString(6, productVO.getProd_info());
			pstmt.setString(7, productVO.getProd_bnd());
			pstmt.setString(8, productVO.getProd_clr());
			pstmt.setString(9, productVO.getProd_size());
			pstmt.setInt(10, productVO.getShip_meth());

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
	public void update(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, productVO.getProd_cat_no());
			pstmt.setInt(2, productVO.getProd_stat());
			pstmt.setString(3, productVO.getProd_name());
			pstmt.setInt(4, productVO.getProd_pc());
			pstmt.setInt(5, productVO.getProd_stg());
			pstmt.setString(6, productVO.getProd_info());
			pstmt.setString(7, productVO.getProd_bnd());
			pstmt.setString(8, productVO.getProd_clr());
			pstmt.setString(9, productVO.getProd_size());
			pstmt.setInt(10, productVO.getShip_meth());
			pstmt.setInt(11, productVO.getProd_no());

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
	public void delete(Integer prod_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, prod_no);

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
	public ProductVO findByPrimaryKey(Integer prod_no) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, prod_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProd_no(rs.getInt("PROD_NO"));
				productVO.setProd_cat_no(rs.getInt("PROD_CAT_NO"));
				productVO.setProd_stat(rs.getInt("PROD_STAT"));
				productVO.setProd_name(rs.getString("PROD_NAME"));
				productVO.setProd_pc(rs.getInt("PROD_PC"));
				productVO.setProd_stg(rs.getInt("PROD_STG"));
				productVO.setProd_info(rs.getString("PROD_INFO"));
				productVO.setProd_bnd(rs.getString("PROD_BND"));
				productVO.setProd_clr(rs.getString("PROD_CLR"));
				productVO.setProd_size(rs.getString("PROD_SIZE"));
				productVO.setShip_meth(rs.getInt("SHIP_METH"));
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
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProd_no(rs.getInt("PROD_NO"));
				productVO.setProd_cat_no(rs.getInt("PROD_CAT_NO"));
				productVO.setProd_stat(rs.getInt("PROD_STAT"));
				productVO.setProd_name(rs.getString("PROD_NAME"));
				productVO.setProd_pc(rs.getInt("PROD_PC"));
				productVO.setProd_stg(rs.getInt("PROD_STG"));
				productVO.setProd_info(rs.getString("PROD_INFO"));
				productVO.setProd_bnd(rs.getString("PROD_BND"));
				productVO.setProd_clr(rs.getString("PROD_CLR"));
				productVO.setProd_size(rs.getString("PROD_SIZE"));
				productVO.setShip_meth(rs.getInt("SHIP_METH"));
				list.add(productVO); 
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
	
	public List<ProductVO> getAll(Map<String, String[]> map) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "SELECT * FROM PRODUCT"
		          + jdbc_CQ_Product.get_WhereCondition(map)
		          + "order by product_no";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("¡´¡´finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProd_no(rs.getInt("PROD_NO"));
				productVO.setProd_cat_no(rs.getInt("PROD_CAT_NO"));
				productVO.setProd_stat(rs.getInt("PROD_STAT"));
				productVO.setProd_name(rs.getString("PROD_NAME"));
				productVO.setProd_pc(rs.getInt("PROD_PC"));
				productVO.setProd_stg(rs.getInt("PROD_STG"));
				productVO.setProd_info(rs.getString("PROD_INFO"));
				productVO.setProd_bnd(rs.getString("PROD_BND"));
				productVO.setProd_clr(rs.getString("PROD_CLR"));
				productVO.setProd_size(rs.getString("PROD_SIZE"));
				productVO.setShip_meth(rs.getInt("SHIP_METH"));
				list.add(productVO); // Store the row in the List
			}
	
			// Handle any SQL errors
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