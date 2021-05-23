package com.article_report.model;
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



public class Article_ReportDAO implements Article_ReportDAO_Interface{
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
		"INSERT INTO ARTICLE_REPORT (art_no,mbr_no,rpt_cont,rpt_time,proc_stat) VALUES (?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT art_rpt_no,art_no,mbr_no,rpt_cont,rpt_time,proc_stat FROM ARTICLE_REPORT order by art_rpt_no desc";
	private static final String GET_WAIT_FOR_JUDGE = 
			"SELECT art_rpt_no,art_no,mbr_no,rpt_cont,rpt_time,proc_stat FROM ARTICLE_REPORT where proc_stat = 0 order by art_rpt_no desc";
	private static final String GET_ALREADY_JUDGE = 
			"SELECT art_rpt_no,art_no,mbr_no,rpt_cont,rpt_time,proc_stat FROM ARTICLE_REPORT where proc_stat = 1 order by art_rpt_no desc";
	private static final String GET_ONE_STMT = 
		"SELECT art_rpt_no,art_no,mbr_no,rpt_cont,rpt_time,proc_stat FROM ARTICLE_REPORT where art_rpt_no = ?";
	private static final String DELETE = 
		"DELETE FROM ARTICLE_REPORT where art_rpt_no = ?";
	private static final String UPDATE = 
			"UPDATE ARTICLE_REPORT set art_no=?, mbr_no=?, rpt_cont=? ,rpt_time=? ,proc_stat=? where art_rpt_no = ?";
	
	
	@Override
	public void insert(Article_ReportVO article_ReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, article_ReportVO.getArt_no());
			pstmt.setInt(2, article_ReportVO.getMbr_no());
			pstmt.setString(3, article_ReportVO.getRpt_cont());
			pstmt.setTimestamp(4, article_ReportVO.getRpt_time());
			pstmt.setInt(5, article_ReportVO.getProc_stat());


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
	public void update(Article_ReportVO article_ReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, article_ReportVO.getArt_no());
			pstmt.setInt(2, article_ReportVO.getMbr_no());
			pstmt.setString(3, article_ReportVO.getRpt_cont());
			pstmt.setTimestamp(4, article_ReportVO.getRpt_time());
			pstmt.setInt(5, article_ReportVO.getProc_stat());
			pstmt.setInt(6, article_ReportVO.getArt_rpt_no());
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
	public void delete(Integer art_rpt_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, art_rpt_no);

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
	public Article_ReportVO findByPrimaryKey(Integer art_rpt_no) {
		Article_ReportVO article_ReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, art_rpt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				article_ReportVO = new Article_ReportVO();
				article_ReportVO.setArt_rpt_no(rs.getInt("art_rpt_no"));
				article_ReportVO.setArt_no(rs.getInt("art_no"));
				article_ReportVO.setMbr_no(rs.getInt("mbr_no"));
				article_ReportVO.setRpt_cont(rs.getString("rpt_cont"));
				article_ReportVO.setRpt_time(rs.getTimestamp("rpt_time"));
				article_ReportVO.setProc_stat(rs.getInt("proc_stat"));
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
		return article_ReportVO;
	}
	@Override
	public List<Article_ReportVO> getAll() {
		List<Article_ReportVO> list = new ArrayList<Article_ReportVO>();
		Article_ReportVO article_ReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				article_ReportVO = new Article_ReportVO();
				article_ReportVO.setArt_rpt_no(rs.getInt("art_rpt_no"));
				article_ReportVO.setArt_no(rs.getInt("art_no"));
				article_ReportVO.setMbr_no(rs.getInt("mbr_no"));
				article_ReportVO.setRpt_cont(rs.getString("rpt_cont"));
				article_ReportVO.setRpt_time(rs.getTimestamp("rpt_time"));
				article_ReportVO.setProc_stat(rs.getInt("proc_stat"));
				list.add(article_ReportVO); // Store the row in the list
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
	
	
	
	public List<Article_ReportVO> get_wait_for_judge() {
		List<Article_ReportVO> list = new ArrayList<Article_ReportVO>();
		Article_ReportVO article_ReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_WAIT_FOR_JUDGE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				article_ReportVO = new Article_ReportVO();
				article_ReportVO.setArt_rpt_no(rs.getInt("art_rpt_no"));
				article_ReportVO.setArt_no(rs.getInt("art_no"));
				article_ReportVO.setMbr_no(rs.getInt("mbr_no"));
				article_ReportVO.setRpt_cont(rs.getString("rpt_cont"));
				article_ReportVO.setRpt_time(rs.getTimestamp("rpt_time"));
				article_ReportVO.setProc_stat(rs.getInt("proc_stat"));
				list.add(article_ReportVO); // Store the row in the list
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
	
	
	public List<Article_ReportVO> get_already_judge() {
		List<Article_ReportVO> list = new ArrayList<Article_ReportVO>();
		Article_ReportVO article_ReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALREADY_JUDGE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				article_ReportVO = new Article_ReportVO();
				article_ReportVO.setArt_rpt_no(rs.getInt("art_rpt_no"));
				article_ReportVO.setArt_no(rs.getInt("art_no"));
				article_ReportVO.setMbr_no(rs.getInt("mbr_no"));
				article_ReportVO.setRpt_cont(rs.getString("rpt_cont"));
				article_ReportVO.setRpt_time(rs.getTimestamp("rpt_time"));
				article_ReportVO.setProc_stat(rs.getInt("proc_stat"));
				list.add(article_ReportVO); // Store the row in the list
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




