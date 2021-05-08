package com.member.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO implements MemberDAO_interface {

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
		"INSERT INTO campion.member (rank_no,acc,pwd,id,name,bday,sex,mobile,mail,city,dist,add,join_time,card,pt,acc_stat,exp,sticker,rmk) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT mbr_no,rank_no,acc,pwd,id,name,bday,sex,mobile,mail,city,dist,add,join_time,card,pt,acc_stat,exp,sticker,rmk FROM campion.member order by mbr_no";
	private static final String GET_ONE_STMT = 
		"SELECT mbr_no,rank_no,acc,pwd,id,name,bday,sex,mobile,mail,city,dist,add,join_time,card,pt,acc_stat,exp,sticker,rmk FROM campion.member where mbr_no = ?";
	private static final String DELETE = 
		"DELETE FROM campion.member where mbr_no = ?";
	private static final String UPDATE = 
		"UPDATE campion.member set rank_no=?, acc=?, pwd=?, id=?, name=?, bday=?, sex=?, mobile=?, mail=?, city=?, dist=?, add=?, join_time=?, card=?, pt=?, acc_stat=?, exp=?, sticker=?, rmk=? where mbr_no=?";

	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, memberVO.getRank_no());
			pstmt.setString(2, memberVO.getAcc());
			pstmt.setString(3, memberVO.getPwd());
			pstmt.setString(4, memberVO.getId());
			pstmt.setString(5, memberVO.getName());
			pstmt.setDate(6, memberVO.getBday());
			pstmt.setInt(7, memberVO.getSex());
			pstmt.setString(8, memberVO.getMobile());
			pstmt.setString(9, memberVO.getMail());
			pstmt.setString(10, memberVO.getCity());
			pstmt.setString(11, memberVO.getDist());
			pstmt.setString(12, memberVO.getAdd());
			pstmt.setTimestamp(13, memberVO.getJoin_time());
			pstmt.setString(14, memberVO.getCard());
			pstmt.setInt(15, memberVO.getPt());
			pstmt.setInt(16, memberVO.getAcc_stat());
			pstmt.setInt(17, memberVO.getExp());
			pstmt.setBytes(18, memberVO.getSticker());
			pstmt.setString(19, memberVO.getRmk());

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
	public void update(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, memberVO.getRank_no());
			pstmt.setString(2, memberVO.getAcc());
			pstmt.setString(3, memberVO.getPwd());
			pstmt.setString(4, memberVO.getId());
			pstmt.setString(5, memberVO.getName());
			pstmt.setDate(6, memberVO.getBday());
			pstmt.setInt(7, memberVO.getSex());
			pstmt.setString(8, memberVO.getMobile());
			pstmt.setString(9, memberVO.getMail());
			pstmt.setString(10, memberVO.getCity());
			pstmt.setString(11, memberVO.getDist());
			pstmt.setString(12, memberVO.getAdd());
			pstmt.setTimestamp(13, memberVO.getJoin_time());
			pstmt.setString(14, memberVO.getCard());
			pstmt.setInt(15, memberVO.getPt());
			pstmt.setInt(16, memberVO.getAcc_stat());
			pstmt.setInt(17, memberVO.getExp());
			pstmt.setBytes(18, memberVO.getSticker());
			pstmt.setString(19, memberVO.getRmk());
			pstmt.setInt(20, memberVO.getMbr_no());

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
	public void delete(Integer mbr_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mbr_no);

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
	public MemberVO findByPrimaryKey(Integer mbr_no) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mbr_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memberVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMbr_no(rs.getInt("mbr_no"));
				memberVO.setRank_no(rs.getInt("rank_no"));
				memberVO.setAcc(rs.getString("acc"));
				memberVO.setPwd(rs.getString("pwd"));
				memberVO.setId(rs.getString("id"));
				memberVO.setName(rs.getString("name"));
				memberVO.setBday(rs.getDate("bday"));
				memberVO.setSex(rs.getInt("sex"));
				memberVO.setMobile(rs.getString("mobile"));
				memberVO.setMail(rs.getString("mail"));
				memberVO.setCity(rs.getString("city"));
				memberVO.setDist(rs.getString("dist"));
				memberVO.setAdd(rs.getString("add"));
				memberVO.setJoin_time(rs.getTimestamp("join_time"));
				memberVO.setCard(rs.getString("card"));
				memberVO.setPt(rs.getInt("pt"));
				memberVO.setAcc_stat(rs.getInt("acc_stat"));
				memberVO.setExp(rs.getInt("exp"));
				memberVO.setSticker(rs.getBytes("sticker"));
				memberVO.setRmk(rs.getString("rmk"));
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memberVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMbr_no(rs.getInt("mbr_no"));
				memberVO.setRank_no(rs.getInt("rank_no"));
				memberVO.setAcc(rs.getString("acc"));
				memberVO.setPwd(rs.getString("pwd"));
				memberVO.setId(rs.getString("id"));
				memberVO.setName(rs.getString("name"));
				memberVO.setBday(rs.getDate("bday"));
				memberVO.setSex(rs.getInt("sex"));
				memberVO.setMobile(rs.getString("mobile"));
				memberVO.setMail(rs.getString("mail"));
				memberVO.setCity(rs.getString("city"));
				memberVO.setDist(rs.getString("dist"));
				memberVO.setAdd(rs.getString("add"));
				memberVO.setJoin_time(rs.getTimestamp("join_time"));
				memberVO.setCard(rs.getString("card"));
				memberVO.setPt(rs.getInt("pt"));
				memberVO.setAcc_stat(rs.getInt("acc_stat"));
				memberVO.setExp(rs.getInt("exp"));
				memberVO.setSticker(rs.getBytes("sticker"));
				memberVO.setRmk(rs.getString("rmk"));
				list.add(memberVO); // Store the row in the list
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

	@Override
	public List<MemberVO> getDateMbr_no(Date bday) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> getTimestampMbr_no(Timestamp join_time) {
		// TODO Auto-generated method stub
		return null;
	}
}