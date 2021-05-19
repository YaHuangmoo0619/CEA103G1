package com.shopping_cart.model;

import java.util.*;
import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;

public class Shopping_cartDAO implements Shopping_cartDAO_interface {

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
		"insert into SHOPPING_CART (MBR_NO, PROD_NO, PROD_AMT) values (?, ?, ?)";
	private static final String UPDATE = 
		"update SHOPPING_CART set PROD_AMT = ? where MBR_NO = ? and PROD_NO = ?";
	private static final String DELETE = 
		"delete from SHOPPING_CART where MBR_NO = ? and PROD_NO = ?";
	private static final String GET_ONE_STMT = 
		"select * from SHOPPING_CART where MBR_NO = ? and PROD_NO = ?";
	private static final String GET_MBR_STMT = 
		"select * from SHOPPING_CART where MBR_NO = ?";
	private static final String GET_ALL_STMT = 
		"select * from SHOPPING_CART order by MBR_NO";

	@Override
	public void insert(Shopping_cartVO shopping_cartVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, shopping_cartVO.getMbr_no());
			pstmt.setInt(2, shopping_cartVO.getProd_no());
			pstmt.setInt(3, shopping_cartVO.getProd_amt());
			
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
	public void update(Shopping_cartVO shopping_cartVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, shopping_cartVO.getProd_amt());
			pstmt.setInt(2, shopping_cartVO.getMbr_no());
			pstmt.setInt(3, shopping_cartVO.getProd_no());

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
	public Shopping_cartVO findByPrimaryKey(Integer mbr_no, Integer prod_no) {

		Shopping_cartVO shopping_cartVO = null;
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
				shopping_cartVO = new Shopping_cartVO();
				shopping_cartVO.setMbr_no(rs.getInt("MBR_NO"));
				shopping_cartVO.setProd_no(rs.getInt("PROD_NO"));
				shopping_cartVO.setProd_amt(rs.getInt("PROD_AMT"));
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
		return shopping_cartVO;
	}
	
	@Override
	public List<Shopping_cartVO> findByMbr_no(Integer mbr_no) {

		List<Shopping_cartVO> list = new ArrayList<Shopping_cartVO>();
		Shopping_cartVO shopping_cartVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MBR_STMT);

			pstmt.setInt(1, mbr_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopping_cartVO = new Shopping_cartVO();
				shopping_cartVO.setMbr_no(rs.getInt("MBR_NO"));
				shopping_cartVO.setProd_no(rs.getInt("PROD_NO"));
				shopping_cartVO.setProd_amt(rs.getInt("PROD_AMT"));
				list.add(shopping_cartVO);
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
	public List<Shopping_cartVO> getAll() {
		List<Shopping_cartVO> list = new ArrayList<Shopping_cartVO>();
		Shopping_cartVO shopping_cartVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopping_cartVO = new Shopping_cartVO();
				shopping_cartVO.setMbr_no(rs.getInt("MBR_NO"));
				shopping_cartVO.setProd_no(rs.getInt("PROD_NO"));
				shopping_cartVO.setProd_amt(rs.getInt("PROD_AMT"));
				list.add(shopping_cartVO); 
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