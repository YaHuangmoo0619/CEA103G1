package com.campsite_owner.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/campsite_owner/ImportFirstBlob")
public class ImportFirstBlob extends HttpServlet{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String UPDATE_BLOB = 
			"UPDATE campion.campsite_owner SET sticker =? WHERE cso_no =?";
	private static final String UPDATE_BLOB_IDF = 
			"UPDATE campion.campsite_owner SET id_picf =? WHERE cso_no =?";
	private static final String UPDATE_BLOB_IDB = 
			"UPDATE campion.campsite_owner SET id_picb =? WHERE cso_no =?";
	private static final String UPDATE_BLOB_IDF2 = 
			"UPDATE campion.campsite_owner SET id_pic2f =? WHERE cso_no =?";
	private static final String UPDATE_LICENSE = 
			"UPDATE campion.campsite_owner SET license =? WHERE cso_no =?";
	private static final String GET_ALL_STMT = 
			"SELECT cso_no FROM campion.campsite_owner";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = pstmt.executeQuery();
			
			//放大頭照
			while(rs.next()) {
				pstmt = con.prepareStatement(UPDATE_BLOB);
				int cso_no = rs.getInt("cso_no");
				pstmt.setInt(2, cso_no);
				InputStream in = getServletContext().getResourceAsStream("/images/an_pic/an_pic1.jpg");
				byte[] pic = new byte[in.available()];
				in.read(pic);
				
				pstmt.setBytes(1, pic);

				pstmt.executeUpdate();
			}	
			//放身份證正面
			rs.beforeFirst();//指針回到第一筆之前
			while(rs.next()) {
				pstmt = con.prepareStatement(UPDATE_BLOB_IDF);
				int cso_no = rs.getInt("cso_no");
				pstmt.setInt(2, cso_no);
				InputStream in = getServletContext().getResourceAsStream("/images/an_pic/an_pic2.jpg");
				byte[] pic2 = new byte[in.available()];
				in.read(pic2);
					
				pstmt.setBytes(1, pic2);

				pstmt.executeUpdate();
			}
			//放身份證反面
			rs.beforeFirst();//指針回到第一筆之前
			while(rs.next()) {
				pstmt = con.prepareStatement(UPDATE_BLOB_IDB);
				int cso_no = rs.getInt("cso_no");
				pstmt.setInt(2, cso_no);
				InputStream in = getServletContext().getResourceAsStream("/images/an_pic/an_pic3.jpg");
				byte[] pic2 = new byte[in.available()];
				in.read(pic2);
					
				pstmt.setBytes(1, pic2);

				pstmt.executeUpdate();
			}
			//放第二證件正面
			rs.beforeFirst();//指針回到第一筆之前
			while(rs.next()) {
				pstmt = con.prepareStatement(UPDATE_BLOB_IDF2);
				int cso_no = rs.getInt("cso_no");
				pstmt.setInt(2, cso_no);
				InputStream in = getServletContext().getResourceAsStream("/images/an_pic/an_pic4.jpg");
				byte[] pic2 = new byte[in.available()];
				in.read(pic2);
					
				pstmt.setBytes(1, pic2);

				pstmt.executeUpdate();
			}
			//放營業證明
			rs.beforeFirst();//指針回到第一筆之前
			while(rs.next()) {
				pstmt = con.prepareStatement(UPDATE_LICENSE);
				int cso_no = rs.getInt("cso_no");
				pstmt.setInt(2, cso_no);
				InputStream in = getServletContext().getResourceAsStream("/images/an_pic/an_pic5.jpg");
				byte[] pic2 = new byte[in.available()];
				in.read(pic2);
					
				pstmt.setBytes(1, pic2);

				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
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
}
