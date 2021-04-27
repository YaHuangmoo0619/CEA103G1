package com.article.model;
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



public class ArticleDAO implements ArticleDAO_Interface{
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
			"INSERT INTO ARTICLE (bd_cl_no,mbr_no,art_rel_time,art_title,art_cont,likes,art_stat) VALUES (?,?, ?, ?, ?, ?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT art_no,bd_cl_no,mbr_no,art_rel_time,art_title,art_cont,likes,art_stat FROM ARTICLE order by art_no";
		private static final String GET_ONE_STMT = 
			"SELECT art_no,bd_cl_no,mbr_no,art_rel_time,art_title,art_cont,likes,art_stat FROM ARTICLE where art_no = ?";
		private static final String DELETE = 
			"DELETE FROM ARTICLE where art_no = ?";
		private static final String UPDATE = 
				"UPDATE ARTICLE set bd_cl_no=?, mbr_no=?, art_rel_time=? ,art_title=? ,art_cont=? ,likes=? ,art_stat=? where art_no = ?";
		private static final String GET_BY_BD_CL_NO =
			"SELECT art_no,bd_cl_no,mbr_no,art_rel_time,art_title,art_cont,likes,art_stat FROM ARTICLE where bd_cl_no = ?";
	@Override
	public void insert(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articleVO.getBd_cl_no());
			pstmt.setInt(2, articleVO.getMbr_no());
			pstmt.setTimestamp(3, articleVO.getArt_rel_time());
			pstmt.setString(4, articleVO.getArt_title());
			pstmt.setString(5, articleVO.getArt_cont());
			pstmt.setInt(6, articleVO.getLikes());
			pstmt.setInt(7, articleVO.getArt_stat());

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
	public void update(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, articleVO.getBd_cl_no());
			pstmt.setInt(2, articleVO.getMbr_no());
			pstmt.setTimestamp(3, articleVO.getArt_rel_time());
			pstmt.setString(4, articleVO.getArt_title());
			pstmt.setString(5, articleVO.getArt_cont());
			pstmt.setInt(6, articleVO.getLikes());
			pstmt.setInt(7, articleVO.getArt_stat());
			pstmt.setInt(8, articleVO.getArt_no());
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
	public void delete(Integer art_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, art_no);

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
	public ArticleVO findByPrimaryKey(Integer art_no) {
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, art_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				articleVO = new ArticleVO();
				articleVO.setArt_no(rs.getInt("art_no"));
				articleVO.setBd_cl_no(rs.getInt("bd_cl_no"));
				articleVO.setMbr_no(rs.getInt("mbr_no"));
				articleVO.setArt_rel_time(rs.getTimestamp("art_rel_time"));
				articleVO.setArt_title(rs.getString("art_title"));
				articleVO.setArt_cont(rs.getString("art_cont"));
				articleVO.setLikes(rs.getInt("likes"));
				articleVO.setArt_stat(rs.getInt("art_stat"));
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
		return articleVO;
	}

	@Override
	public List<ArticleVO> getAll() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// articleVO 也稱為 Domain objects
				articleVO = new ArticleVO();
				articleVO.setArt_no(rs.getInt("art_no"));
				articleVO.setBd_cl_no(rs.getInt("bd_cl_no"));
				articleVO.setMbr_no(rs.getInt("mbr_no"));
				articleVO.setArt_rel_time(rs.getTimestamp("art_rel_time"));
				articleVO.setArt_title(rs.getString("art_title"));
				articleVO.setArt_cont(rs.getString("art_cont"));
				articleVO.setLikes(rs.getInt("likes"));
				articleVO.setArt_stat(rs.getInt("art_stat"));
				list.add(articleVO); // Store the row in the list
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
	public List<ArticleVO> findByBd_cl_no(Integer bd_cl_no) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_BD_CL_NO);
			pstmt.setInt(1, bd_cl_no);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArt_no(rs.getInt("art_no"));
				articleVO.setBd_cl_no(rs.getInt("bd_cl_no"));
				articleVO.setMbr_no(rs.getInt("mbr_no"));
				articleVO.setArt_rel_time(rs.getTimestamp("art_rel_time"));
				articleVO.setArt_title(rs.getString("art_title"));
				articleVO.setArt_cont(rs.getString("art_cont"));
				articleVO.setLikes(rs.getInt("likes"));
				articleVO.setArt_stat(rs.getInt("art_stat"));
				list.add(articleVO); // Store the row in the list
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
	

