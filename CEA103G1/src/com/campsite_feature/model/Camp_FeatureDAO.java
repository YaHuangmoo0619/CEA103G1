package com.campsite_feature.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class Camp_FeatureDAO implements Camp_FeatureDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String GET_ONE_STMT = "SELECT * FROM campsite_feature where camp_no = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM campsite_feature";
	private static final String INSERT_STMT = "INSERT INTO campsite_feature (camp_fl_no,camp_no) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE campsite_feature set camp_name=?,campsite_Status=?,campInfo=?,note=?,config=?,review_Status=?,height=?,wireless=?,pet=?,facility=?,operate_Date=?,park=?,address=?,longtitude=?,latitude=?,total_Star=?,total_Comment=? where campno = ?";
	private static final String DELETE = "DELETE FROM campsite_feature where camp_no = ?";

	public Camp_FeatureVO findByPrimaryKey(Integer camp_fl_no, Integer camp_no) {

		Camp_FeatureVO camp_featureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, camp_fl_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				camp_featureVO = new Camp_FeatureVO();
				camp_featureVO.setCamp_fl_no(rs.getInt("camp_fl_no"));
				camp_featureVO.setCamp_no(rs.getInt("camp_no"));
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
		return camp_featureVO;
	}

	public List<Camp_FeatureVO> getAll() {
		List<Camp_FeatureVO> list = new ArrayList<Camp_FeatureVO>();
		Camp_FeatureVO camp_featureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				camp_featureVO = new Camp_FeatureVO();
				camp_featureVO.setCamp_fl_no(rs.getInt("camp_fl_no"));
				camp_featureVO.setCamp_no(rs.getInt("camp_no"));
				list.add(camp_featureVO);
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
	public void insert(Camp_FeatureVO camp_featureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, camp_featureVO.getCamp_fl_no());
			pstmt.setInt(2, camp_featureVO.getCamp_no());

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
	public void update(Camp_FeatureVO camp_featureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, camp_featureVO.getCamp_fl_no());
			pstmt.setInt(2, camp_featureVO.getCamp_no());
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
	public void delete(Integer camp_fl_no, Integer camp_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, camp_fl_no);
			pstmt.setInt(2, camp_no);

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
	public void insert2(Camp_FeatureVO camp_featureVO, Connection con) {
	PreparedStatement pstmt = null;

	try {

		pstmt = con.prepareStatement(INSERT_STMT);

		pstmt.setInt(1, camp_featureVO.getCamp_fl_no());
		pstmt.setInt(2, camp_featureVO.getCamp_no());

		Statement stmt = con.createStatement();
//stmt.executeUpdate("set auto_increment_offset=7001;"); //自增主鍵-初始值
		stmt.executeUpdate("set auto_increment_increment=1;"); // 自增主鍵-遞增
		pstmt.executeUpdate();

		// Handle any SQL errors
	} catch (SQLException se) {
		if (con != null) {
			try {
				// 3●設定於當有exception發生時之catch區塊內
				System.err.print("Transaction is being ");
				System.err.println("rolled back-由-emp");
				con.rollback();
			} catch (SQLException excep) {
				throw new RuntimeException("rollback error occured. " + excep.getMessage());
			}
		}
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
	}
	}
}
