package com.place_order_details.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

import com.feature_list.model.Feature_ListVO;

public class Place_Order_DetailsDAO implements Place_Order_DetailsDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String GET_ONE_PLC_NO = "SELECT * FROM place_order_details where plc_ord_no = ?";
	private static final String GET_ONE_PLC_ORD = "SELECT plc_ord_no FROM place_order_details where plc_no = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM place_order_details order by plc_ord_no";
	private static final String INSERT_STMT = "INSERT INTO campsite (cso_no,dist_no,camp_name,campsite_Status,campInfo,note,config,review_Status,height,wireless,pet,facility,operate_Date,park,address,longtitude,latitude,total_Star,total_Comment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE campsite set camp_name=?,campsite_Status=?,campInfo=?,note=?,config=?,review_Status=?,height=?,wireless=?,pet=?,facility=?,operate_Date=?,park=?,address=?,longtitude=?,latitude=?,total_Star=?,total_Comment=? where campno = ?";
	private static final String DELETE = "DELETE FROM campsite where camp_no = ?";

	public List<Place_Order_DetailsVO> findByPlaceOrder(Integer plc_ord_no) {
		List<Place_Order_DetailsVO> list = new ArrayList<Place_Order_DetailsVO>();
		Place_Order_DetailsVO place_order_detailsVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PLC_NO);
			pstmt.setInt(1, plc_ord_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				place_order_detailsVO = new Place_Order_DetailsVO();
				place_order_detailsVO.setPlc_ord_no(rs.getInt("plc_ord_no"));
				place_order_detailsVO.setPlc_no(rs.getInt("plc_no"));
				list.add(place_order_detailsVO);
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
	
	public Set<Integer> findByPlace(Integer plc_no) {
		Set<Integer> list = new TreeSet<Integer>();
		Place_Order_DetailsVO place_order_detailsVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PLC_ORD);
			pstmt.setInt(1, plc_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Integer plc_ord_no = new Integer(rs.getInt("plc_ord_no"));
				list.add(plc_ord_no);
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

	public List<Place_Order_DetailsVO> getAll() {
		List<Place_Order_DetailsVO> list = new ArrayList<Place_Order_DetailsVO>();
		Place_Order_DetailsVO place_order_detailsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				place_order_detailsVO = new Place_Order_DetailsVO();
				place_order_detailsVO.setPlc_ord_no(rs.getInt("plc_ord_no"));
				place_order_detailsVO.setPlc_no(rs.getInt("plc_no"));
				list.add(place_order_detailsVO);
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
	public void insert(Place_Order_DetailsVO place_order_detailsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, place_order_detailsVO.getPlc_ord_no());
			pstmt.setInt(2, place_order_detailsVO.getPlc_no());

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
	public void update(Place_Order_DetailsVO place_order_detailsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, place_order_detailsVO.getPlc_ord_no());
			pstmt.setInt(2, place_order_detailsVO.getPlc_no());
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


//	public void delete(Integer plc_ord_no, Integer plc_no) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, plc_ord_no);
//			pstmt.setInt(2, plc_no);
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//		} finally {
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
//	}

	@Override
	public void deletePlace_Order(Integer plc_ord_no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePlace(Integer plc_no) {
		// TODO Auto-generated method stub
		
	}
}
