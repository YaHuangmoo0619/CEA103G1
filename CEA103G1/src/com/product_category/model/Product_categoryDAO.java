package com.product_category.model;

import java.util.*;
import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;

public class Product_categoryDAO implements Product_categoryDAO_interface {

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
		"insert into PRODUCT_CATEGORY (PROD_CAT_NAME) values (?)";
	private static final String UPDATE = 
		"update PRODUCT_CATEGORY set PROD_CAT_NAME=? where PROD_CAT_NO = ?";
	private static final String DELETE = 
		"delete from PRODUCT_CATEGORY where PROD_CAT_NO = ?";
	private static final String GET_ONE_STMT = 
		"select * from PRODUCT_CATEGORY where PROD_CAT_NO = ?";
	private static final String GET_ALL_STMT = 
		"select * from PRODUCT_CATEGORY order by PROD_CAT_NO";

	@Override
	public void insert(Product_categoryVO product_categoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, product_categoryVO.getProd_cat_name());

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
	public void update(Product_categoryVO product_categoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, product_categoryVO.getProd_cat_name());
			pstmt.setInt(2, product_categoryVO.getProd_cat_no());

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
	public void delete(Integer prod_cat_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, prod_cat_no);

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
	public Product_categoryVO findByPrimaryKey(Integer prod_cat_no) {

		Product_categoryVO product_categoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, prod_cat_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_categoryVO = new Product_categoryVO();
				product_categoryVO.setProd_cat_no(rs.getInt("PROD_CAT_NO"));
				product_categoryVO.setProd_cat_name(rs.getString("PROD_CAT_NAME"));
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
		return product_categoryVO;
	}

	@Override
	public List<Product_categoryVO> getAll() {
		List<Product_categoryVO> list = new ArrayList<Product_categoryVO>();
		Product_categoryVO product_categoryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_categoryVO = new Product_categoryVO();
				product_categoryVO.setProd_cat_no(rs.getInt("PROD_CAT_NO"));
				product_categoryVO.setProd_cat_name(rs.getString("PROD_CAT_NAME"));
				list.add(product_categoryVO); 
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