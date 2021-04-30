package com.campsite_collection.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Camp_CollectionDAO implements Camp_CollectionDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String GET_ONE_STMT = "SELECT * FROM campsite where mbr_no = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM campsite_collection";
	private static final String INSERT_STMT = "INSERT INTO campsite (cso_no,dist_no,camp_name,campsite_Status,campInfo,note,config,review_Status,height,wireless,pet,facility,operate_Date,park,address,longtitude,latitude,total_Star,total_Comment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE campsite set camp_name=?,campsite_Status=?,campInfo=?,note=?,config=?,review_Status=?,height=?,wireless=?,pet=?,facility=?,operate_Date=?,park=?,address=?,longtitude=?,latitude=?,total_Star=?,total_Comment=? where campno = ?";
	private static final String DELETE = "DELETE FROM campsite where mbr_no = ?";

	public Camp_CollectionVO findByPrimaryKey(Integer camp_no, Integer mbr_no) {

		Camp_CollectionVO camp_collectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, camp_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				camp_collectionVO = new Camp_CollectionVO();
				camp_collectionVO.setCamp_no(rs.getInt("camp_no"));
				camp_collectionVO.setMbr_no(rs.getInt("mbr_no"));
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
		return camp_collectionVO;
	}

	public List<Camp_CollectionVO> getAll() {
		List<Camp_CollectionVO> list = new ArrayList<Camp_CollectionVO>();
		Camp_CollectionVO camp_collectionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				camp_collectionVO = new Camp_CollectionVO();
				camp_collectionVO.setCamp_no(rs.getInt("camp_no"));
				camp_collectionVO.setMbr_no(rs.getInt("mbr_no"));
				list.add(camp_collectionVO);
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
	public void insert(Camp_CollectionVO camp_collectionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, camp_collectionVO.getCamp_no());
			pstmt.setInt(2, camp_collectionVO.getMbr_no());

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
	public void update(Camp_CollectionVO camp_collectionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, camp_collectionVO.getCamp_no());
			pstmt.setInt(2, camp_collectionVO.getMbr_no());
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
	public void delete(Integer camp_no, Integer mbr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, camp_no);
			pstmt.setInt(2, mbr_no);

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
