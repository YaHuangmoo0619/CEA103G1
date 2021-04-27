package com.member_rank.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Member_rankDAO implements Member_rankDAO_interface {

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
		"INSERT INTO campion.member_rank (rank_name,pt_rwd_rto) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT rank_no,rank_name,pt_rwd_rto FROM campion.member_rank order by rank_no";
	private static final String GET_ONE_STMT = 
		"SELECT rank_no,rank_name,pt_rwd_rto FROM campion.member_rank where rank_no = ?";
	private static final String DELETE = 
		"DELETE FROM campion.member_rank where rank_no = ?";
	private static final String UPDATE = 
		"UPDATE campion.member_rank set rank_name=?, pt_rwd_rto=? where rank_no=?";

	@Override
	public void insert(Member_rankVO member_rankVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, member_rankVO.getRank_name());
			pstmt.setInt(2, member_rankVO.getPt_rwd_rto());

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
	public void update(Member_rankVO member_rankVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, member_rankVO.getRank_name());
			pstmt.setInt(2, member_rankVO.getPt_rwd_rto());
			pstmt.setInt(3, member_rankVO.getRank_no());

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
	public void delete(Integer rank_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, rank_no);

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
	public Member_rankVO findByPrimaryKey(Integer rank_no) {

		Member_rankVO member_rankVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rank_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// member_rankVO 也稱為 Domain objects
				member_rankVO = new Member_rankVO();
				member_rankVO.setRank_no(rs.getInt("rank_no"));
				member_rankVO.setRank_name(rs.getString("rank_name"));
				member_rankVO.setPt_rwd_rto(rs.getInt("pt_rwd_rto"));
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
		return member_rankVO;
	}

	@Override
	public List<Member_rankVO> getAll() {
		List<Member_rankVO> list = new ArrayList<Member_rankVO>();
		Member_rankVO member_rankVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// member_rankVO 也稱為 Domain objects
				member_rankVO = new Member_rankVO();
				member_rankVO.setRank_no(rs.getInt("rank_no"));
				member_rankVO.setRank_name(rs.getString("rank_name"));
				member_rankVO.setPt_rwd_rto(rs.getInt("pt_rwd_rto"));
				list.add(member_rankVO); // Store the row in the list
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