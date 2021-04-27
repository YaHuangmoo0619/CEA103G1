package com.campsite.model;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.*;
import javax.sql.DataSource;

public class CampDAO implements CampDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String GET_ONE_STMT = "SELECT * FROM campsite where camp_no = ?";
	private static final String GET_DIST_STMT = "SELECT * FROM campsite where dist_no = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM campsite order by camp_no";
	private static final String INSERT_STMT = "INSERT INTO campsite (cso_no,dist_no,camp_name,campInfo,note,config,height,wireless,pet,facility,operate_Date,park,address,latitude,longitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE campsite set dist_no=?,camp_name=?,campInfo=?,note=?,config=?,height=?,wireless=?,pet=?,facility=?,operate_Date=?,park=?,address=?,latitude=?,longitude=? where camp_no = ?";
	private static final String DELETE = "DELETE FROM campsite where camp_no = ?";

	public CampVO findByPrimaryKey(Integer camp_no) {

		CampVO campVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, camp_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				campVO = new CampVO();
				campVO.setCamp_no(rs.getInt("camp_no"));
				campVO.setCso_no(rs.getInt("cso_no"));
				campVO.setDist_no(rs.getInt("dist_no"));
				campVO.setCamp_name(rs.getString("camp_name"));
				campVO.setCampsite_Status(rs.getInt("campsite_Status"));
				campVO.setCampInfo(rs.getString("campinfo"));
				campVO.setNote(rs.getString("note"));
				campVO.setConfig(rs.getBytes("config"));
				campVO.setReview_Status(rs.getInt("review_Status"));
				campVO.setHeight(rs.getString("height"));
				campVO.setWireless(rs.getString("wireless"));
				campVO.setPet(rs.getInt("pet"));
				campVO.setFacility(rs.getString("facility"));
				campVO.setOperate_Date(rs.getInt("operate_Date"));
				campVO.setPark(rs.getString("park"));
				campVO.setAddress(rs.getString("address"));
				campVO.setLatitude(rs.getDouble("latitude"));
				campVO.setLongitude(rs.getDouble("longitude"));
				campVO.setTotal_Stars(rs.getInt("total_Stars"));
				campVO.setTotal_Cmnt(rs.getInt("total_Cmnt"));
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
		return campVO;
	}
	
	public List<Integer> findByDist(Integer dist_no) {
		
		List<Integer> list = new ArrayList<Integer>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DIST_STMT);
			pstmt.setInt(1, dist_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getInt("camp_no"));
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

	public List<CampVO> getAll() {
		List<CampVO> list = new ArrayList<CampVO>();
		CampVO campVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				campVO = new CampVO();
				campVO.setCamp_no(rs.getInt("camp_no"));
				campVO.setCso_no(rs.getInt("cso_no"));
				campVO.setDist_no(rs.getInt("dist_no"));
				campVO.setCamp_name(rs.getString("camp_name"));
				campVO.setCampsite_Status(rs.getInt("campsite_Status"));
				campVO.setCampInfo(rs.getString("campinfo"));
				campVO.setNote(rs.getString("note"));
				campVO.setConfig(rs.getBytes("config"));
				campVO.setReview_Status(rs.getInt("review_Status"));
				campVO.setHeight(rs.getString("height"));
				campVO.setWireless(rs.getString("wireless"));
				campVO.setPet(rs.getInt("pet"));
				campVO.setFacility(rs.getString("facility"));
				campVO.setOperate_Date(rs.getInt("operate_Date"));
				campVO.setPark(rs.getString("park"));
				campVO.setAddress(rs.getString("address"));
				campVO.setLatitude(rs.getDouble("latitude"));
				campVO.setLongitude(rs.getDouble("longitude"));
				campVO.setTotal_Stars(rs.getInt("total_Stars"));
				campVO.setTotal_Cmnt(rs.getInt("total_Cmnt"));
				list.add(campVO);
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
	public void insert(CampVO campVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, campVO.getCso_no());
			pstmt.setInt(2, campVO.getDist_no());
			pstmt.setString(3, campVO.getCamp_name());
			pstmt.setString(4, campVO.getCampInfo());
			pstmt.setString(5, campVO.getNote());
			pstmt.setBytes(6, campVO.getConfig());
			pstmt.setString(7, campVO.getHeight());
			pstmt.setString(8, campVO.getWireless());
			pstmt.setInt(9, campVO.getPet());
			pstmt.setString(10, campVO.getFacility());
			pstmt.setInt(11, campVO.getOperate_Date());
			pstmt.setString(12, campVO.getPark());
			pstmt.setString(13, campVO.getAddress());
			pstmt.setDouble(14, campVO.getLatitude());
			pstmt.setDouble(15, campVO.getLongitude());
			pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(CampVO campVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, campVO.getDist_no());
			pstmt.setString(2, campVO.getCamp_name());
			pstmt.setString(3, campVO.getCampInfo());
			pstmt.setString(4, campVO.getNote());
			pstmt.setBytes(5, campVO.getConfig());
			pstmt.setString(6, campVO.getHeight());
			pstmt.setString(7, campVO.getWireless());
			pstmt.setInt(8, campVO.getPet());
			pstmt.setString(9, campVO.getFacility());
			pstmt.setInt(10, campVO.getOperate_Date());
			pstmt.setString(11, campVO.getPark());
			pstmt.setString(12, campVO.getAddress());
			pstmt.setDouble(13, campVO.getLatitude());
			pstmt.setDouble(14, campVO.getLongitude());
			pstmt.setInt(15, campVO.getCamp_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer camp_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, camp_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
