package com.member.model;

import java.sql.*;
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
		"INSERT INTO campion.member (rank_no,acc,pwd,id,`name`,bday,sex,mobile,mail,city,dist,`add`,join_time,card,pt,acc_stat,exp,sticker,rmk) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT mbr_no,rank_no,acc,pwd,id,`name`,bday,sex,mobile,mail,city,dist,`add`,join_time,card,pt,acc_stat,exp,sticker,rmk FROM campion.member order by mbr_no";
	private static final String GET_ONE_STMT = 
		"SELECT mbr_no,rank_no,acc,pwd,id,`name`,bday,sex,mobile,mail,city,dist,`add`,join_time,card,pt,acc_stat,exp,sticker,rmk FROM campion.member where mbr_no = ?";
	private static final String DELETE = 
		"DELETE FROM campion.member where mbr_no = ?";
	private static final String UPDATE = 
		"UPDATE campion.member set rank_no=?, acc=?, pwd=?, id=?, `name`=?, bday=?, sex=?, mobile=?, mail=?, city=?, dist=?, `add`=?, join_time=?, card=?, pt=?, acc_stat=?, exp=?, sticker=?, rmk=? where mbr_no=?";
	//雅凰加的
	private static final String UPDATE_INFO = 
			"UPDATE campion.member set rank_no=?, acc=?, pwd=?, id=?, `name`=?, bday=?, sex=?, mobile=?, mail=?, city=?, dist=?, `add`=?, join_time=?, card=?, pt=?, acc_stat=?, exp=?, rmk=? where mbr_no=?";
	private static final String GET_ONE_ACC_STMT = 
			"SELECT acc FROM campion.member where BINARY acc = ?";
	//雅凰加的
	private static final String LOGIN_MEMBER =
		"SELECT mbr_no,rank_no,acc,pwd,id,`name`,bday,sex,mobile,mail,city,dist,`add`,join_time,card,pt,acc_stat,exp,sticker,rmk FROM campion.member where acc = ? and pwd = ?";
	private static final String REGISTER_MEMBER = 
		"INSERT INTO campion.member (acc,pwd,id,`name`,bday,sex,mobile,mail,city,dist,`add`,join_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,NOW())";
	private static final String FIND_BY_EMAIL = 
		"SELECT * FROM campion.member WHERE mail=?";
	private static final String UPDATE_PWD_BY_EMAIL = 
		"UPDATE campion.member set pwd=? WHERE mail=?";
	private static final String UPDATE_MEMSTATUS = 
		"UPDATE campion.member SET acc_stat=? WHERE acc=?";
	private static final String LOGIN_STMT = 
		"SELECT * FROM campion.member WHERE acc=?";
	//新增會員
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
	//修改會員
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
	//刪除會員
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
	//查詢(單一)會員
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
	//查詢(所有)會員
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
	//登入會員
	@Override
	public MemberVO findByPrimaryKey_login(String acc, String pwd) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN_MEMBER);

			pstmt.setString(1, acc);
			pstmt.setString(2, pwd);
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
	//註冊會員
	@Override
	public void register_Member(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(REGISTER_MEMBER);

			
			pstmt.setString(1, memberVO.getAcc());
			pstmt.setString(2, memberVO.getPwd());
			pstmt.setString(3, memberVO.getId());
			pstmt.setString(4, memberVO.getName());
			pstmt.setDate(5, memberVO.getBday());
			pstmt.setInt(6, memberVO.getSex());
			pstmt.setString(7, memberVO.getMobile());
			pstmt.setString(8, memberVO.getMail());
			pstmt.setString(9, memberVO.getCity());
			pstmt.setString(10, memberVO.getDist());
			pstmt.setString(11, memberVO.getAdd());

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

	//改變會員狀態
	public void updateMemberStatus(String acc, Integer acc_stat) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MEMSTATUS);

			pstmt.setInt(1, acc_stat);
			pstmt.setString(2, acc);
			
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
	//改變密碼
	public void updatePwd(String mail, String pwd) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PWD_BY_EMAIL);

			pstmt.setString(1, pwd);
			pstmt.setString(2, mail);
			
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
	//會員驗證信
	public MemberVO findByEmail(String mail) {
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(FIND_BY_EMAIL);

			pstmt.setString(1, mail);


			rs = pstmt.executeQuery();

			while (rs.next()) {

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
	//檢查帳號狀態
	public MemberVO loginCheck(String acc) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN_STMT);
			
			pstmt.setString(1, acc);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
			throw new RuntimeException("A database error occured. " + se.getMessage());
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


	//雅凰加的
	@Override
	public MemberVO update_info(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_INFO);

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
			pstmt.setString(18, memberVO.getRmk());
			pstmt.setInt(19, memberVO.getMbr_no());

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
	return memberVO;
	}
	
	@Override
	public String findByAcc(String acc) {
		
		String accFound = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ACC_STMT);

			pstmt.setString(1, acc);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				accFound = rs.getString("acc");
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
	
		return accFound;
	}
	//雅凰加的
}