package com.promotion_product.model;

import java.util.*;
import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;

public class Promotion_productDAO implements Promotion_productDAO_interface {

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
		"insert into PROMOTION_PRODUCT (PROMO_PRJ_NO, PROD_NO, PROD_PC) values (?, ?, ?)";
	private static final String UPDATE = 
		"update PROMOTION_PRODUCT set PROD_PC = ? where PROMO_PRJ_NO = ? and PROD_NO = ?";
	private static final String DELETE = 
		"delete from PROMOTION_PRODUCT where PROMO_PRJ_NO = ? and PROD_NO = ?";
	private static final String GET_ONE_STMT = 
		"select * from PROMOTION_PRODUCT where PROMO_PRJ_NO = ? and PROD_NO = ?";
	private static final String GET_PROMO_PRJ_STMT = 
		"select * from PROMOTION_PRODUCT where PROMO_PRJ_NO = ?";
	private static final String GET_PROD_CAT_STMT = 
		"select * from PROMOTION_PRODUCT where PROD_NO = ?";
	private static final String GET_ALL_STMT = 
		"select * from PROMOTION_PRODUCT order by PROMO_PRJ_NO";

	@Override
	public void insert(Promotion_productVO promotion_productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, promotion_productVO.getPromo_prj_no());
			pstmt.setInt(2, promotion_productVO.getProd_no());
			pstmt.setInt(3, promotion_productVO.getProd_pc());
			
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
	public void update(Promotion_productVO promotion_productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, promotion_productVO.getProd_pc());
			pstmt.setInt(2, promotion_productVO.getPromo_prj_no());
			pstmt.setInt(3, promotion_productVO.getProd_no());

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
	public void delete(Integer promo_prj_no, Integer prod_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, promo_prj_no);
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
	public Promotion_productVO findByPrimaryKey(Integer promo_prj_no, Integer prod_no) {

		Promotion_productVO promotion_productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, promo_prj_no);
			pstmt.setInt(2, prod_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotion_productVO = new Promotion_productVO();
				promotion_productVO.setPromo_prj_no(rs.getInt("PROMO_PRJ_NO"));
				promotion_productVO.setProd_no(rs.getInt("PROD_NO"));
				promotion_productVO.setProd_pc(rs.getInt("PROD_PC"));
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
		return promotion_productVO;
	}
	
	@Override
	public List<Promotion_productVO> findByProd_prj_no(Integer promo_prj_no) {

		List<Promotion_productVO> list = new ArrayList<Promotion_productVO>();
		Promotion_productVO promotion_productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PROMO_PRJ_STMT);

			pstmt.setInt(1, promo_prj_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotion_productVO = new Promotion_productVO();
				promotion_productVO.setPromo_prj_no(rs.getInt("PROMO_PRJ_NO"));
				promotion_productVO.setProd_no(rs.getInt("PROD_NO"));
				promotion_productVO.setProd_pc(rs.getInt("PROD_PC"));
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
	public List<Promotion_productVO> findByProd_no(Integer prod_no) {
		List<Promotion_productVO> list = new ArrayList<Promotion_productVO>();
		Promotion_productVO promotion_productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PROD_CAT_STMT);

			pstmt.setInt(1, prod_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotion_productVO = new Promotion_productVO();
				promotion_productVO.setPromo_prj_no(rs.getInt("PROMO_PRJ_NO"));
				promotion_productVO.setProd_no(rs.getInt("PROD_NO"));
				promotion_productVO.setProd_pc(rs.getInt("PROD_PC"));
				list.add(promotion_productVO); 
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
	public List<Promotion_productVO> getAll() {
		List<Promotion_productVO> list = new ArrayList<Promotion_productVO>();
		Promotion_productVO promotion_productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotion_productVO = new Promotion_productVO();
				promotion_productVO.setPromo_prj_no(rs.getInt("PROMO_PRJ_NO"));
				promotion_productVO.setProd_no(rs.getInt("PROD_NO"));
				promotion_productVO.setProd_pc(rs.getInt("PROD_PC"));
				list.add(promotion_productVO); 
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