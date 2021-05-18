package com.campsite_owner.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Campsite_ownerDAO implements Campsite_ownerDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
		"INSERT INTO campion.campsite_owner (acc,pwd,id,`name`,bday,sex,mobile,mail,city,dist,`add`,sticker,bank_no,bank_acc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM campion.campsite_owner order by cso_no";
	private static final String GET_ONE_STMT = 
		"SELECT cso_no,acc,pwd,id,`name`,bday,sex,mobile,mail,city,dist,`add`,join_time,id_picf,id_picb,id_pic2f,stat,sticker,license,bank_no,bank_acc FROM campion.campsite_owner where cso_no = ?";
	private static final String DELETE = 
		"DELETE FROM campion.campsite_owner where cso_no = ?";
	private static final String UPDATE = 
		"UPDATE campion.campsite_owner set acc=?, pwd=?, id=?, name=?, bday=?, sex=?, mobile=?, mail=?, city=?, dist=?, add=?, join_time=?, id_picf=?, id_picb=?, id_pic2f=?, stat=?, sticker=?, license=?, bank_no=?, bank_acc=? where cso_no=?";

	@Override
	public void insert(Campsite_ownerVO campsite_ownerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, campsite_ownerVO.getAcc());
			pstmt.setString(2, campsite_ownerVO.getPwd());
			pstmt.setString(3, campsite_ownerVO.getId());
			pstmt.setString(4, campsite_ownerVO.getName());
			pstmt.setDate(5, campsite_ownerVO.getBday());
			pstmt.setInt(6, campsite_ownerVO.getSex());
			pstmt.setInt(7, campsite_ownerVO.getMobile());
			pstmt.setString(8, campsite_ownerVO.getMail());
			pstmt.setString(9, campsite_ownerVO.getCity());
			pstmt.setString(10, campsite_ownerVO.getDist());
			pstmt.setString(11, campsite_ownerVO.getAdd());
			pstmt.setBytes(12, campsite_ownerVO.getSticker());
			pstmt.setInt(13, campsite_ownerVO.getBank_no());
			pstmt.setString(14, campsite_ownerVO.getBank_acc());
			
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
	public void update(Campsite_ownerVO campsite_ownerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, campsite_ownerVO.getAcc());
			pstmt.setString(2, campsite_ownerVO.getPwd());
			pstmt.setString(3, campsite_ownerVO.getId());
			pstmt.setString(4, campsite_ownerVO.getName());
			pstmt.setDate(5, campsite_ownerVO.getBday());
			pstmt.setInt(6, campsite_ownerVO.getSex());
			pstmt.setInt(7, campsite_ownerVO.getMobile());
			pstmt.setString(8, campsite_ownerVO.getMail());
			pstmt.setString(9, campsite_ownerVO.getCity());
			pstmt.setString(10, campsite_ownerVO.getDist());
			pstmt.setString(11, campsite_ownerVO.getAdd());
			pstmt.setInt(12, campsite_ownerVO.getStat());
			pstmt.setBytes(13, campsite_ownerVO.getSticker());
			pstmt.setInt(14, campsite_ownerVO.getBank_no());
			pstmt.setString(15, campsite_ownerVO.getBank_acc());
			pstmt.setInt(16, campsite_ownerVO.getCso_no());

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
	public void delete(Integer cso_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, cso_no);

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
	public Campsite_ownerVO findByPrimaryKey(Integer cso_no) {

		Campsite_ownerVO campsite_ownerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, cso_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// campsite_ownerVO 也稱為 Domain objects
				campsite_ownerVO = new Campsite_ownerVO();
				campsite_ownerVO = new Campsite_ownerVO();
				campsite_ownerVO.setCso_no(rs.getInt("cso_no"));
				campsite_ownerVO.setAcc(rs.getString("acc"));
				campsite_ownerVO.setPwd(rs.getString("pwd"));
				campsite_ownerVO.setId(rs.getString("id"));
				campsite_ownerVO.setName(rs.getString("name"));
				campsite_ownerVO.setBday(rs.getDate("bday"));
				campsite_ownerVO.setSex(rs.getInt("sex"));
				campsite_ownerVO.setMobile(rs.getInt("mobile"));
				campsite_ownerVO.setMail(rs.getString("mail"));
				campsite_ownerVO.setCity(rs.getString("city"));
				campsite_ownerVO.setDist(rs.getString("dist"));
				campsite_ownerVO.setAdd(rs.getString("add"));
				campsite_ownerVO.setJoin_time(rs.getTimestamp("join_time"));
				campsite_ownerVO.setStat(rs.getInt("stat"));
				campsite_ownerVO.setSticker(rs.getBytes("sticker"));
				campsite_ownerVO.setBank_no(rs.getInt("bank_no"));
				campsite_ownerVO.setBank_acc(rs.getString("bank_acc"));
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
		return campsite_ownerVO;
	}

	@Override
	public List<Campsite_ownerVO> getAll() {
		List<Campsite_ownerVO> list = new ArrayList<Campsite_ownerVO>();
		Campsite_ownerVO campsite_ownerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// campsite_ownerVO 也稱為 Domain objects
				campsite_ownerVO = new Campsite_ownerVO();
				campsite_ownerVO.setCso_no(rs.getInt("cso_no"));
				campsite_ownerVO.setAcc(rs.getString("acc"));
				campsite_ownerVO.setPwd(rs.getString("pwd"));
				campsite_ownerVO.setId(rs.getString("id"));
				campsite_ownerVO.setName(rs.getString("name"));
				campsite_ownerVO.setBday(rs.getDate("bday"));
				campsite_ownerVO.setSex(rs.getInt("sex"));
				campsite_ownerVO.setMobile(rs.getInt("mobile"));
				campsite_ownerVO.setMail(rs.getString("mail"));
				campsite_ownerVO.setCity(rs.getString("city"));
				campsite_ownerVO.setDist(rs.getString("dist"));
				campsite_ownerVO.setAdd(rs.getString("add"));
				campsite_ownerVO.setJoin_time(rs.getTimestamp("join_time"));
				campsite_ownerVO.setStat(rs.getInt("stat"));
				campsite_ownerVO.setSticker(rs.getBytes("sticker"));
				campsite_ownerVO.setBank_no(rs.getInt("bank_no"));
				campsite_ownerVO.setBank_acc(rs.getString("bank_acc"));
				
				list.add(campsite_ownerVO); // Store the row in the list
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
}