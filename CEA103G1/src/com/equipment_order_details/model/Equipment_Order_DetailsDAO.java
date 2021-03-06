package com.equipment_order_details.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class Equipment_Order_DetailsDAO implements Equipment_Order_DetailsDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String GET_ONE_STMT = "SELECT * FROM equipment_order_details where plc_ord_no = ? and eq_no = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM equipment_order_details order by plc_ord_no";
	private static final String INSERT_STMT = "INSERT INTO campsite (cso_no,dist_no,camp_name,campsite_Status,campInfo,note,config,review_Status,height,wireless,pet,facility,operate_Date,park,address,longtitude,latitude,total_Star,total_Comment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE campsite set camp_name=?,campsite_Status=?,campInfo=?,note=?,config=?,review_Status=?,height=?,wireless=?,pet=?,facility=?,operate_Date=?,park=?,address=?,longtitude=?,latitude=?,total_Star=?,total_Comment=? where campno = ?";
	private static final String DELETE = "DELETE FROM campsite where camp_no = ?";

	public Equipment_Order_DetailsVO findByPrimaryKey(Integer plc_ord_no, Integer eq_no) {

		Equipment_Order_DetailsVO equipment_order_detailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, plc_ord_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				equipment_order_detailsVO = new Equipment_Order_DetailsVO();
				equipment_order_detailsVO.setPlc_ord_no(rs.getInt("plc_ord_no"));
				equipment_order_detailsVO.setEq_no(rs.getInt("eq_no"));
				equipment_order_detailsVO.setEq_amt(rs.getInt("eq_amt"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return equipment_order_detailsVO;
	}

	public List<Equipment_Order_DetailsVO> getAll() {
		List<Equipment_Order_DetailsVO> list = new ArrayList<Equipment_Order_DetailsVO>();
		Equipment_Order_DetailsVO equipment_order_detailsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				equipment_order_detailsVO = new Equipment_Order_DetailsVO();
				equipment_order_detailsVO.setPlc_ord_no(rs.getInt("plc_ord_no"));
				equipment_order_detailsVO.setEq_no(rs.getInt("eq_no"));
				equipment_order_detailsVO.setEq_amt(rs.getInt("eq_amt"));
				list.add(equipment_order_detailsVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void insert(Equipment_Order_DetailsVO equipment_order_detailsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, equipment_order_detailsVO.getPlc_ord_no());
			pstmt.setInt(2, equipment_order_detailsVO.getEq_no());
			pstmt.setInt(3, equipment_order_detailsVO.getEq_amt());

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
	public void update(Equipment_Order_DetailsVO equipment_order_detailsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, equipment_order_detailsVO.getPlc_ord_no());
			pstmt.setInt(2, equipment_order_detailsVO.getEq_no());
			pstmt.setInt(3, equipment_order_detailsVO.getEq_amt());
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
	public void delete(Integer plc_ord_no, Integer eq_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, plc_ord_no);
			pstmt.setInt(2, eq_no);

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
}
