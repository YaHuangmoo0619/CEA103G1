package com.campsite_owner.controller;

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

@WebServlet("/Campsite_ownerChangeStat")
public class Campsite_ownerChangeStat extends HttpServlet {
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
			"UPDATE campion.campsite_owner SET stat =? WHERE cso_no =?";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=big5");
		
		if("pass".equals(action)) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STAT);
				
				pstmt.setInt(1, 1);
				pstmt.setInt(2, Integer.valueOf(req.getParameter("cso_no")));
	
				pstmt.executeUpdate();
				
//				req.setAttribute("stat", 1); 
				String url = "/back-end/campsite_owner/listAllCampsite_owner.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);
				
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
		
		if("stop".equals(action)) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STAT);
				
				pstmt.setInt(1, 2);
				pstmt.setInt(2, Integer.valueOf(req.getParameter("cso_no")));
	
				pstmt.executeUpdate();
				
//				req.setAttribute("stat", 2); 
				String url = "/back-end/campsite_owner/listAllCampsite_owner.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);
				
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
