package com.feature_list.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class Feature_ListDAO implements Feature_ListDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String GET_ONE_STMT = "SELECT * FROM campsite_feature_list where camp_fl_name like ";
	private static final String GET_ONE_STMT2 = "SELECT * FROM campsite_feature_list where camp_fl_no = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM campsite_feature_list order by camp_fl_no";
	private static final String INSERT_STMT = "INSERT INTO campsite_feature_list (camp_fl_name) VALUES (?)";
	private static final String UPDATE = "UPDATE campsite_feature_list set camp_fl_name=? where camp_fl_no = ?";
	private static final String DELETE = "DELETE FROM campsite_feature_list where camp_fl_no = ?";

	public List<Feature_ListVO> findByName(String camp_fl_name) {
		List<Feature_ListVO> list = new ArrayList<Feature_ListVO>();
		Feature_ListVO feature_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT + "'%" + camp_fl_name + "%'");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				feature_listVO = new Feature_ListVO();
				feature_listVO.setCamp_fl_no(rs.getInt("camp_fl_no"));
				feature_listVO.setCamp_fl_name(rs.getString("camp_fl_name"));
				list.add(feature_listVO);
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
	public Feature_ListVO findByPrimaryKey(Integer camp_fl_no) {
		
		Feature_ListVO feature_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT2);
			pstmt.setInt(1, camp_fl_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				feature_listVO = new Feature_ListVO();
				feature_listVO.setCamp_fl_no(rs.getInt("camp_fl_no"));
				feature_listVO.setCamp_fl_name(rs.getString("camp_fl_name"));
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
		return feature_listVO;
	}

	public List<Feature_ListVO> getAll() {
		List<Feature_ListVO> list = new ArrayList<Feature_ListVO>();
		Feature_ListVO feature_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				feature_listVO = new Feature_ListVO();
				feature_listVO.setCamp_fl_no(rs.getInt("camp_fl_no"));
				feature_listVO.setCamp_fl_name(rs.getString("camp_fl_name"));
				list.add(feature_listVO);
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
	public Feature_ListVO insert(Feature_ListVO feature_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			
			String cols[] = { "CAMP_FL_NO" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, feature_listVO.getCamp_fl_name());
			Statement stmt = con.createStatement();
			stmt.executeUpdate("set auto_increment_offset=1;"); // �ۼW�D��-��l��
			stmt.executeUpdate("set auto_increment_increment=1;"); // �ۼW�D��-���W
			pstmt.executeUpdate();
			
			String next_camp_fl_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_camp_fl_no = rs.getString(1);
			} else {
				System.out.println("�����o�ۼW�D���");
			}
			rs.close();
			feature_listVO.setCamp_fl_no(new Integer(next_camp_fl_no));
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
		return feature_listVO;
	}

	@Override
	public void update(Feature_ListVO feature_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, feature_listVO.getCamp_fl_name());
			pstmt.setInt(2, feature_listVO.getCamp_fl_no());
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
	public void delete(Integer camp_fl_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, camp_fl_no);

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
