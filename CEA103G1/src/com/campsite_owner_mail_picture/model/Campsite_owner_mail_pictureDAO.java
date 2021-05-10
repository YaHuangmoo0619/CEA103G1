package com.campsite_owner_mail_picture.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
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

import com.member_mail_picture.model.Member_mail_pictureVO;

public class Campsite_owner_mail_pictureDAO implements Campsite_owner_mail_pictureDAO_interface {

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
			"INSERT INTO campion.campsite_owner_mail_picture (mail_no,mail_pic) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT mail_pic_no,mail_no,mail_pic FROM campion.campsite_owner_mail_picture order by mail_pic_no";
		private static final String GET_ByMail_no_STMT = 
				"SELECT mail_pic_no,mail_no,mail_pic FROM campion.campsite_owner_mail_picture where mail_no = ?";
		private static final String GET_ONE_STMT = 
			"SELECT mail_pic_no,mail_no,mail_pic FROM campion.campsite_owner_mail_picture where mail_pic_no = ?";
		private static final String DELETE = 
			"DELETE FROM campion.campsite_owner_mail_picture where mail_pic_no = ?";
		private static final String UPDATE = 
			"UPDATE campion.campsite_owner_mail_picture set mail_pic_no=?, mail_no=?, mail_pic=? where mail_pic_no = ?";
	
	
	@Override
	public void insert(Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, campsite_owner_mail_pictureVO.getMail_no());
			pstmt.setString(2, campsite_owner_mail_pictureVO.getMail_pic());

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
	public void update(Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, campsite_owner_mail_pictureVO.getMail_pic_no());
			pstmt.setInt(2, campsite_owner_mail_pictureVO.getMail_no());
			pstmt.setString(3, campsite_owner_mail_pictureVO.getMail_pic());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer mail_pic_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mail_pic_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public Campsite_owner_mail_pictureVO findByPrimaryKey(Integer mail_pic_no) {

		Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, mail_pic_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				campsite_owner_mail_pictureVO = new Campsite_owner_mail_pictureVO();
				campsite_owner_mail_pictureVO.setMail_pic_no(rs.getInt("mail_pic_no"));
				campsite_owner_mail_pictureVO.setMail_no(rs.getInt("mail_no"));
				campsite_owner_mail_pictureVO.setMail_pic(rs.getString("mail_pic"));
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return campsite_owner_mail_pictureVO;
	}


	@Override
	public List<Campsite_owner_mail_pictureVO> getAll() {
		List<Campsite_owner_mail_pictureVO> list = new ArrayList<Campsite_owner_mail_pictureVO>();
		Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				// empVo 也稱為 Domain objects
				campsite_owner_mail_pictureVO = new Campsite_owner_mail_pictureVO();
				campsite_owner_mail_pictureVO.setMail_pic_no(rs.getInt("mail_pic_no"));
				campsite_owner_mail_pictureVO.setMail_no(rs.getInt("mail_no"));
				campsite_owner_mail_pictureVO.setMail_pic(rs.getString("mail_pic"));
				
				list.add(campsite_owner_mail_pictureVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	public List<Campsite_owner_mail_pictureVO> findByMail_no(Integer mail_no){
		List<Campsite_owner_mail_pictureVO> list = new ArrayList<Campsite_owner_mail_pictureVO>();
		Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ByMail_no_STMT);
			pstmt.setInt(1, mail_no);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				// empVo 也稱為 Domain objects
				campsite_owner_mail_pictureVO = new Campsite_owner_mail_pictureVO();
				campsite_owner_mail_pictureVO.setMail_pic_no(rs.getInt("mail_pic_no"));
				campsite_owner_mail_pictureVO.setMail_no(rs.getInt("mail_no"));
				campsite_owner_mail_pictureVO.setMail_pic(rs.getString("mail_pic"));

				list.add(campsite_owner_mail_pictureVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	public void insertWithMail (Campsite_owner_mail_pictureVO campsite_owner_mail_pictureVO , Connection con) {
		PreparedStatement pstmt = null;
		try {

			pstmt = con.prepareStatement(INSERT_STMT);
//			System.out.println(pstmt == null);

			pstmt.setInt(1, campsite_owner_mail_pictureVO.getMail_no());
			pstmt.setString(2, campsite_owner_mail_pictureVO.getMail_pic());

			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-campsite_owner_mail_picture");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}
	}
}
