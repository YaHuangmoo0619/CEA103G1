package com.product_collection.model;

import java.util.*;
import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;

public class Product_collectionDAO implements Product_collectionDAO_interface {

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
		"insert into PRODUCT_COLLECTION (MBR_NO, PROD_NO, PROD_PC) values (?, ?, ?)";
	private static final String DELETE = 
		"delete from PRODUCT_COLLECTION where MBR_NO = ? and PROD_NO = ?";
	private static final String GET_ONE_STMT = 
		"select * from PRODUCT_COLLECTION where MBR_NO = ? and PROD_NO = ?";
	private static final String GET_MBR_STMT = 
		"select * from PRODUCT_COLLECTION where MBR_NO = ?";
	private static final String GET_PROD_STMT = 
		"select * from PRODUCT_COLLECTION where PROD_NO = ?";
	private static final String GET_ALL_STMT = 
		"select * from PRODUCT_COLLECTION order by MBR_NO";

	@Override
	public void insert(Product_collectionVO product_collectionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, product_collectionVO.getMbr_no());
			pstmt.setInt(2, product_collectionVO.getProd_no());
			
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
	public void delete(Integer mbr_no, Integer prod_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mbr_no);
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
	public Product_collectionVO findByPrimaryKey(Integer mbr_no, Integer prod_no) {

		Product_collectionVO product_collectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mbr_no);
			pstmt.setInt(2, prod_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_collectionVO = new Product_collectionVO();
				product_collectionVO.setMbr_no(rs.getInt("MBR_NO"));
				product_collectionVO.setProd_no(rs.getInt("PROD_NO"));
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
		return product_collectionVO;
	}
	
	@Override
	public List<Product_collectionVO> findByMbr_no(Integer mbr_no) {

		List<Product_collectionVO> list = new ArrayList<Product_collectionVO>();
		Product_collectionVO product_collectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MBR_STMT);

			pstmt.setInt(1, mbr_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_collectionVO = new Product_collectionVO();
				product_collectionVO.setMbr_no(rs.getInt("MBR_NO"));
				product_collectionVO.setProd_no(rs.getInt("PROD_NO"));
				list.add(product_collectionVO); 
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
	
	@Override
	public List<Product_collectionVO> findByProd_no(Integer prod_no) {
		List<Product_collectionVO> list = new ArrayList<Product_collectionVO>();
		Product_collectionVO product_collectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PROD_STMT);

			pstmt.setInt(1, prod_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_collectionVO = new Product_collectionVO();
				product_collectionVO.setMbr_no(rs.getInt("MBR_NO"));
				product_collectionVO.setProd_no(rs.getInt("PROD_NO"));
				list.add(product_collectionVO); 
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
	
	@Override
	public List<Product_collectionVO> getAll() {
		List<Product_collectionVO> list = new ArrayList<Product_collectionVO>();
		Product_collectionVO product_collectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_collectionVO = new Product_collectionVO();
				product_collectionVO.setMbr_no(rs.getInt("MBR_NO"));
				product_collectionVO.setProd_no(rs.getInt("PROD_NO"));
				list.add(product_collectionVO); 
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