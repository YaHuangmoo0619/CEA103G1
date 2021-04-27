package com.promotion_project.model;

import java.util.*;
import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;

public class Promotion_projectDAO implements Promotion_projectDAO_interface {

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
		"insert into PROMOTION_PROJECT (PROMO_PRJ_NAME, PROMO_COPY, PROMO_CNT, PROMO_ST_DATE, PROMO_END_DATE) values (?, ?, ?, ?, ?)";
	private static final String UPDATE = 
		"update PROMOTION_PROJECTY set PROMO_PRJ_NAME = ?, PROMO_COPY = ?, PROMO_CNT = ?, PROMO_ST_DATE = ?, PROMO_END_DATE = ? where PROD_CAT_NO = ?";
	private static final String DELETE = 
		"delete from PROMOTION_PROJECT where PROMO_PRJ_NO = ?";
	private static final String GET_ONE_STMT = 
		"select * from PROMOTION_PROJECT where PROMO_PRJ_NO = ?";
	private static final String GET_ALL_STMT = 
		"select * from PROMOTION_PROJECT order by PROMO_PRJ_NO";

	@Override
	public void insert(Promotion_projectVO promotion_projectVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, promotion_projectVO.getPromo_prj_name());
			pstmt.setString(2, promotion_projectVO.getPromo_copy());
			pstmt.setString(3, promotion_projectVO.getPromo_cnt());
			pstmt.setDate(4, promotion_projectVO.getPromo_st_date());
			pstmt.setDate(5, promotion_projectVO.getPromo_end_date());
			

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
	public void update(Promotion_projectVO promotion_projectVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, promotion_projectVO.getPromo_prj_name());
			pstmt.setString(2, promotion_projectVO.getPromo_copy());
			pstmt.setString(3, promotion_projectVO.getPromo_cnt());
			pstmt.setDate(4, promotion_projectVO.getPromo_st_date());
			pstmt.setDate(5, promotion_projectVO.getPromo_end_date());
			pstmt.setInt(6, promotion_projectVO.getPromo_prj_no());

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
	public void delete(Integer promo_prj_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, promo_prj_no);

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
	public Promotion_projectVO findByPrimaryKey(Integer promo_prj_no) {

		Promotion_projectVO promotion_projectVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, promo_prj_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotion_projectVO = new Promotion_projectVO();
				promotion_projectVO.setPromo_prj_no(rs.getInt("PROMO_PRJ_NO"));
				promotion_projectVO.setPromo_prj_name(rs.getString("PROMO_PRJ_NAME"));
				promotion_projectVO.setPromo_copy(rs.getString("PROMO_COPY"));
				promotion_projectVO.setPromo_cnt(rs.getString("PROMO_CNT"));
				promotion_projectVO.setPromo_st_date(rs.getDate("PROMO_ST_DATE"));
				promotion_projectVO.setPromo_end_date(rs.getDate("PROMO_END_DATE"));
								
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
		return promotion_projectVO;
	}

	@Override
	public List<Promotion_projectVO> getAll() {
		List<Promotion_projectVO> list = new ArrayList<Promotion_projectVO>();
		Promotion_projectVO promotion_projectVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotion_projectVO = new Promotion_projectVO();
				promotion_projectVO.setPromo_prj_no(rs.getInt("PROMO_PRJ_NO"));
				promotion_projectVO.setPromo_prj_name(rs.getString("PROMO_PRJ_NAME"));
				promotion_projectVO.setPromo_copy(rs.getString("PROMO_COPY"));
				promotion_projectVO.setPromo_cnt(rs.getString("PROMO_CNT"));
				promotion_projectVO.setPromo_st_date(rs.getDate("PROMO_ST_DATE"));
				promotion_projectVO.setPromo_end_date(rs.getDate("PROMO_END_DATE"));
				list.add(promotion_projectVO); 
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