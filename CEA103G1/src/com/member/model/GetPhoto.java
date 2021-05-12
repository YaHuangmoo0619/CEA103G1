package com.member.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



@WebServlet("/member/GetPhoto")
public class GetPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection con;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			Statement stmt = con.createStatement();
			String mbr_no = req.getParameter("mbr_no").trim();
			ResultSet rs = stmt.executeQuery(
					"SELECT sticker FROM campion.announcement WHERE mbr_no = " + mbr_no);
			
			if(rs.next()) {
				BufferedInputStream bInput= new BufferedInputStream(rs.getBinaryStream("sticker"));
				byte[] buf = new byte[4*1024];
				int len;
				while((len = bInput.read(buf)) != -1) {
					out.write(buf,0,len);
				}
				bInput.close();
			}else {
				InputStream in = getServletContext().getResourceAsStream("/images/campionLogoLong.png");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/images/campionLogoCircle.png");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

	public void init() throws ServletException{
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() {		
		try {
			if(con != null)	con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
