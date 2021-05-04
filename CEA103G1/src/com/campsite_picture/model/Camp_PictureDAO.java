package com.campsite_picture.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

public class Camp_PictureDAO implements Camp_PictureDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String GET_ONE_STMT = "SELECT * FROM campsite_picture where camp_no = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM campsite_picture where camp_no = ?";
	private static final String INSERT_STMT = "INSERT INTO campsite_picture (camp_no,camp_pic) VALUES (?, ?)";
	private static final String DELETE = "DELETE FROM campsite_picture where camp_pic_no = ?";
	
	@Override
	public List<String> findByCamp(Integer camp_no) {
		List<String> list = new ArrayList<String>();
		Camp_PictureVO camp_pictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, camp_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("camp_pic"));
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
	
	public Camp_PictureVO findByPrimaryKey(Integer camp_pic_no) {

		Camp_PictureVO camp_pictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, camp_pic_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				camp_pictureVO = new Camp_PictureVO();
				camp_pictureVO.setCamp_pic_no(rs.getInt("camp_pic_no"));
				camp_pictureVO.setCamp_no(rs.getInt("camp_no"));
				camp_pictureVO.setCamp_pic(rs.getString("cmap_pic"));
				camp_pictureVO.setCamp_pic_time(rs.getTimestamp("camp_pic_time"));
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
		return camp_pictureVO;
	}

	public List<Camp_PictureVO> getAll() {
		List<Camp_PictureVO> list = new ArrayList<Camp_PictureVO>();
		Camp_PictureVO camp_pictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				camp_pictureVO = new Camp_PictureVO();
				camp_pictureVO.setCamp_pic_no(rs.getInt("camp_pic_no"));
				camp_pictureVO.setCamp_no(rs.getInt("camp_no"));
				camp_pictureVO.setCamp_pic(rs.getString("cmap_pic"));
				camp_pictureVO.setCamp_pic_time(rs.getTimestamp("camp_pic_time"));
				list.add(camp_pictureVO);
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
	public void insert(Camp_PictureVO camp_pictureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, camp_pictureVO.getCamp_pic_no());
			pstmt.setInt(2, camp_pictureVO.getCamp_no());
			pstmt.setString(3, camp_pictureVO.getCamp_pic());
			pstmt.setTimestamp(4, camp_pictureVO.getCamp_pic_time());

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
	public void delete(Integer camp_pic_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, camp_pic_no);

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

	public void insert2(Camp_PictureVO camp_pictureVO, Connection con) {
		PreparedStatement pstmt = null;
		System.out.println("照片第一站");
				try {

					pstmt = con.prepareStatement(INSERT_STMT);

					pstmt.setInt(1, camp_pictureVO.getCamp_no());
		System.out.println(camp_pictureVO.getCamp_no());
					pstmt.setString(2, camp_pictureVO.getCamp_pic());
		System.out.println(camp_pictureVO.getCamp_pic());
		System.out.println("照片第二站");
					Statement stmt = con.createStatement();
					stmt.executeUpdate("set auto_increment_offset=1;"); //自增主鍵-初始值
					stmt.executeUpdate("set auto_increment_increment=1;"); // 自增主鍵-遞增
		System.out.println("照片第三站");
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
