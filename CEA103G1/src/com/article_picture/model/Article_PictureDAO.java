package com.article_picture.model;
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


public class Article_PictureDAO implements Article_PictureDAO_Interface {

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
			"INSERT INTO ARTICLE_PICTURE (art_no,art_pic) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT art_pic_no,art_no,art_pic FROM ARTICLE_PICTURE order by art_pic_no";
		private static final String GET_ONE_STMT = 
			"SELECT art_pic_no,art_no,art_pic FROM ARTICLE_PICTURE where art_pic_no = ?";
		private static final String DELETE = 
			"DELETE FROM ARTICLE_PICTURE where art_pic_no = ?";
		private static final String UPDATE = 
			"UPDATE ARTICLE_PICTURE set art_pic_no=?, art_no=?, art_pic=? where art_pic_no = ?";	
		private static final String GET_BY_ART_NO = 
			"SELECT art_pic_no,art_no,art_pic FROM ARTICLE_PICTURE where art_no = ?";	
	@Override
	public void insert(Article_PictureVO Article_PictureVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, Article_PictureVO.getArt_no());
			byte[] pic = null;
			try {
				pic = getPictureByteArray("items/FC_Barcelona.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
			pstmt.setBytes(2, pic);

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
	public void update(Article_PictureVO Article_PictureVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, Article_PictureVO.getArt_pic_no());
			pstmt.setInt(2, Article_PictureVO.getArt_no());
			byte[] pic = null;
			try {
				pic = getPictureByteArray("items/FC_Barcelona.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
			pstmt.setBytes(3, pic);

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
	public void delete(Integer art_pic_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, art_pic_no);

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
	public Article_PictureVO findByPrimaryKey(Integer art_pic_no) {

		Article_PictureVO Article_PictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, art_pic_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Article_PictureVO = new Article_PictureVO();
				Article_PictureVO.setArt_pic_no(rs.getInt("art_pic_no"));
				Article_PictureVO.setArt_no(rs.getInt("art_no"));
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("art_pic"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				try {
					while ((in.read(buf)) != -1) {
						Article_PictureVO.setArt_pic(buf);;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
		return Article_PictureVO;
	}

	@Override
	public List<Article_PictureVO> getAll() {
		List<Article_PictureVO> list = new ArrayList<Article_PictureVO>();
		Article_PictureVO Article_PictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();


			while (rs.next()) {

				Article_PictureVO = new Article_PictureVO();
				Article_PictureVO.setArt_pic_no(rs.getInt("art_pic_no"));
				Article_PictureVO.setArt_no(rs.getInt("art_no"));
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("art_pic"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				try {
					while ((in.read(buf)) != -1) {
						Article_PictureVO.setArt_pic(buf);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				list.add(Article_PictureVO);
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

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	@Override
	public List<Article_PictureVO> findByArt_no(Integer art_no) {
		List<Article_PictureVO> list = new ArrayList<Article_PictureVO>();
		Article_PictureVO article_pictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_ART_NO);
			pstmt.setInt(1, art_no);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				article_pictureVO = new Article_PictureVO();
				article_pictureVO.setArt_no(rs.getInt("art_no"));
				article_pictureVO.setArt_pic_no(rs.getInt("art_pic_no"));
				article_pictureVO.setArt_pic(rs.getBytes("art_pic"));
				list.add(article_pictureVO); // Store the row in the list
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
