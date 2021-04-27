package com.article_reply.model;

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


public class Article_ReplyDAO implements Article_ReplyDAO_Interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO ARTICLE_REPLY (art_no,mbr_no,rep_cont,rep_time,rep_stat) VALUES (?,?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT art_rep_no,art_no,mbr_no,rep_cont,rep_time,rep_stat FROM ARTICLE_REPLY order by art_rep_no";
	private static final String GET_ALL_REPLY_IN_ONE_ARTICLE_STMT = "Select art_rep_no,art_no,mbr_no,rep_cont,rep_time,rep_stat FROM ARTICLE_REPLY WHERE art_no = ?";
	private static final String GET_ONE_STMT = "SELECT art_rep_no,art_no,mbr_no,rep_cont,rep_time,rep_stat FROM ARTICLE_REPLY where art_rep_no = ?";
	private static final String DELETE = "DELETE FROM ARTICLE_REPLY where art_rep_no = ?";
	private static final String UPDATE = "UPDATE ARTICLE_REPLY set art_no=?, mbr_no=? ,rep_cont=? ,rep_time=? ,rep_stat=? where art_rep_no = ?";

	@Override
	public void insert(Article_ReplyVO article_ReplyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, article_ReplyVO.getArt_no());
			pstmt.setInt(2, article_ReplyVO.getMbr_no());
			pstmt.setString(3, article_ReplyVO.getRep_cont());
			pstmt.setTimestamp(4, article_ReplyVO.getRep_time());
			pstmt.setInt(5, article_ReplyVO.getRep_stat());

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
	public void update(Article_ReplyVO article_ReplyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setInt(1, article_ReplyVO.getArt_no());
			pstmt.setInt(2, article_ReplyVO.getMbr_no());
			pstmt.setString(3, article_ReplyVO.getRep_cont());
			pstmt.setTimestamp(4, article_ReplyVO.getRep_time());
			pstmt.setInt(5, article_ReplyVO.getRep_stat());
			pstmt.setInt(6, article_ReplyVO.getArt_rep_no());
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer art_rep_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, art_rep_no);

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
	public Article_ReplyVO findByPrimaryKey(Integer art_rep_no) {
		Article_ReplyVO article_ReplyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, art_rep_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				article_ReplyVO = new Article_ReplyVO();
				article_ReplyVO.setArt_rep_no(rs.getInt("art_no"));
				article_ReplyVO.setArt_no(rs.getInt("art_no"));
				article_ReplyVO.setMbr_no(rs.getInt("mbr_no"));
				article_ReplyVO.setRep_cont(rs.getString("rep_cont"));
				article_ReplyVO.setRep_time(rs.getTimestamp("rep_time"));
				article_ReplyVO.setRep_stat(rs.getInt("rep_stat"));
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
		return article_ReplyVO;
	}

	@Override
	public List<Article_ReplyVO> getAll() {
		List<Article_ReplyVO> list = new ArrayList<Article_ReplyVO>();
		Article_ReplyVO article_ReplyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// article_ReplyVO ¤]ºÙ¬° Domain objects
				article_ReplyVO = new Article_ReplyVO();
				article_ReplyVO.setArt_rep_no(rs.getInt("art_rep_no"));
				article_ReplyVO.setArt_no(rs.getInt("art_no"));
				article_ReplyVO.setMbr_no(rs.getInt("mbr_no"));
				article_ReplyVO.setRep_cont(rs.getString("rep_cont"));
				article_ReplyVO.setRep_time(rs.getTimestamp("rep_time"));
				article_ReplyVO.setRep_stat(rs.getInt("rep_stat"));
				list.add(article_ReplyVO); // Store the row in the list
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
	public List<Article_ReplyVO> findByArt_no(Integer art_no) {
		List<Article_ReplyVO> list = new ArrayList<Article_ReplyVO>();
		System.out.println("artno in DAO 1 :" + art_no);
		Article_ReplyVO article_replyVO = null;
		System.out.println("artno in DAO 2 :" + art_no);
		Connection con = null;
		System.out.println("artno in DAO 3 :" + art_no);
		PreparedStatement pstmt = null;
		System.out.println("artno in DAO 4 :" + art_no);
		ResultSet rs = null;
		System.out.println("artno in DAO 5 :" + art_no);

		try {
			System.out.println("artno in DAO 6 :" + art_no);
			con = ds.getConnection();
			System.out.println("artno in DAO 7 :" + art_no);
			pstmt = con.prepareStatement(GET_ALL_REPLY_IN_ONE_ARTICLE_STMT);
			System.out.println("artno in DAO 8 :" + art_no);
			pstmt.setInt(1, art_no);
			System.out.println("artno in DAO 9 :" + art_no);
			rs = pstmt.executeQuery();
			System.out.println("artno in DAO 10 :" + art_no);


			while (rs.next()) {
				article_replyVO = new Article_ReplyVO();
				article_replyVO.setArt_rep_no(rs.getInt("art_rep_no"));
				article_replyVO.setArt_no(rs.getInt("art_no"));
				article_replyVO.setMbr_no(rs.getInt("mbr_no"));
				article_replyVO.setRep_cont(rs.getString("rep_cont"));
				article_replyVO.setRep_time(rs.getTimestamp("rep_time"));
				article_replyVO.setRep_stat(rs.getInt("rep_stat"));
				list.add(article_replyVO);
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
		//System.out.println("artno in DAO 7 :" + art_no);
		return list;
	}

	
	
	}


