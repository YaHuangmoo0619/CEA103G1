package com.article_reply_report.model;
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


public class Article_Rep_ReportDAO implements Article_Rep_ReportDAO_Interface {
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
		"INSERT INTO ARTICLE_REP_REPORT (mbr_no,art_rep_no,rpt_cont,rpt_time,proc_stat) VALUES (?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT art_rep_rpt_no,mbr_no,art_rep_no,rpt_cont,rpt_time,proc_stat FROM ARTICLE_REP_REPORT order by art_rep_rpt_no";
	private static final String GET_ONE_STMT = 
		"SELECT art_rep_rpt_no,mbr_no,art_rep_no,rpt_cont,rpt_time,proc_stat FROM ARTICLE_REP_REPORT where art_rep_rpt_no = ?";
	private static final String DELETE = 
		"DELETE FROM ARTICLE_REP_REPORT where art_rep_rpt_no = ?";
	private static final String UPDATE = 
			"UPDATE ARTICLE_REP_REPORT set mbr_no=?, art_rep_no=?, rpt_cont=? ,rpt_time=? ,proc_stat=? where art_rep_rpt_no = ?";
	@Override
	public void insert(Article_Rep_ReportVO article_Rep_ReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, article_Rep_ReportVO.getMbr_no());
			pstmt.setInt(2, article_Rep_ReportVO.getArt_rep_no());
			pstmt.setString(3, article_Rep_ReportVO.getRpt_cont());
			pstmt.setTimestamp(4, article_Rep_ReportVO.getRpt_time());
			pstmt.setInt(5, article_Rep_ReportVO.getProc_stat());


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
	public void update(Article_Rep_ReportVO article_Rep_ReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, article_Rep_ReportVO.getMbr_no());
			pstmt.setInt(2, article_Rep_ReportVO.getArt_rep_no());
			pstmt.setString(3, article_Rep_ReportVO.getRpt_cont());
			pstmt.setTimestamp(4, article_Rep_ReportVO.getRpt_time());
			pstmt.setInt(5, article_Rep_ReportVO.getProc_stat());
			pstmt.setInt(6, article_Rep_ReportVO.getArt_rep_rpt_no());
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
	public void delete(Integer art_rep_rpt_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, art_rep_rpt_no);

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
	public Article_Rep_ReportVO findByPrimaryKey(Integer art_rep_rpt_no) {
		Article_Rep_ReportVO article_Rep_ReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, art_rep_rpt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				article_Rep_ReportVO = new Article_Rep_ReportVO();
				article_Rep_ReportVO.setArt_rep_rpt_no(rs.getInt("art_rpt_no"));
				article_Rep_ReportVO.setMbr_no(rs.getInt("mbr_no"));
				article_Rep_ReportVO.setArt_rep_no(rs.getInt("art_rep_no"));
				article_Rep_ReportVO.setRpt_cont(rs.getString("rpt_cont"));
				article_Rep_ReportVO.setRpt_time(rs.getTimestamp("rpt_time"));
				article_Rep_ReportVO.setProc_stat(rs.getInt("proc_stat"));
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
		return article_Rep_ReportVO;
	}
	@Override
	public List<Article_Rep_ReportVO> getAll() {
		List<Article_Rep_ReportVO> list = new ArrayList<Article_Rep_ReportVO>();
		Article_Rep_ReportVO article_Rep_ReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				article_Rep_ReportVO = new Article_Rep_ReportVO();
				article_Rep_ReportVO.setArt_rep_rpt_no(rs.getInt("art_rpt_no"));
				article_Rep_ReportVO.setMbr_no(rs.getInt("mbr_no"));
				article_Rep_ReportVO.setArt_rep_no(rs.getInt("art_rep_no"));
				article_Rep_ReportVO.setRpt_cont(rs.getString("rpt_cont"));
				article_Rep_ReportVO.setRpt_time(rs.getTimestamp("rpt_time"));
				article_Rep_ReportVO.setProc_stat(rs.getInt("proc_stat"));
				list.add(article_Rep_ReportVO); // Store the row in the list
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
	
