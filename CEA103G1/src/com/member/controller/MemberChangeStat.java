package com.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/MemberChangeStat")
public class MemberChangeStat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String UPDATE_STAT = 
			"UPDATE campion.member SET acc_stat =? WHERE mbr_no =?";
	private static final String UPDATE_RMK = 
			"UPDATE campion.member SET rmk =? WHERE mbr_no =?";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=big5");
		PrintWriter out = res.getWriter();
		
		if("stop".equals(action)) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STAT);
				
				pstmt.setInt(1, 3);
				pstmt.setInt(2, Integer.valueOf(req.getParameter("mbr_no")));
	
				pstmt.executeUpdate();
				
//				System.out.println("success");
				
				JSONObject jsonString = null;
				try {
					jsonString = new JSONObject("{stop:"+req.getParameter("mbr_no")+"}");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				out.println(jsonString);
				
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
		
		if("recover".equals(action)) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STAT);
				
				pstmt.setInt(1, 1);
				pstmt.setInt(2, Integer.valueOf(req.getParameter("mbr_no")));
	
				pstmt.executeUpdate();
				
//				System.out.println("success2");
				
				JSONObject jsonString = null;
				try {
					jsonString = new JSONObject("{recover:"+req.getParameter("mbr_no")+"}");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				out.println(jsonString);
				
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
		
		if("write".equals(action)) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
//			System.out.println(req.getParameter("rmk"));
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_RMK);
				
				pstmt.setString(1, req.getParameter("rmk"));
				pstmt.setInt(2, Integer.valueOf(req.getParameter("mbr_no")));
	
				pstmt.executeUpdate();
				
//				System.out.println("success3");
				
				JSONObject jsonString = null;
				try {
					jsonString = new JSONObject("{writer:"+req.getParameter("mbr_no")+"}");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				out.println(jsonString);
				
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
}
