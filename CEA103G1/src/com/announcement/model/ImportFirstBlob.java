package com.announcement.model;

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

@WebServlet("/ImportFirstBlob")
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
			"UPDATE campion.announcement SET an_pic =? WHERE an_no =?";
	private static final String GET_ALL_STMT = 
			"SELECT an_no FROM campion.announcement";
	
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
			
			pstmt = con.prepareStatement(GET_ALL_STMT);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				pstmt = con.prepareStatement(UPDATE_BLOB);
				int an_no = rs.getInt("an_no");
				pstmt.setInt(2, an_no);
				InputStream in = getServletContext().getResourceAsStream("/images/an_pic/an_pic"+ an_no+ ".jpg");
//				InputStream in = getServletContext().getResourceAsStream("/images/logo.png");
				byte[] pic = new byte[in.available()];
				in.read(pic);
				
				pstmt.setBytes(1, pic);

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
