package com.campsite_owner.model;

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



@WebServlet("/campsite_owner/GetPhoto")
public class GetPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection con;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		String action = req.getParameter("action");
		
		//取得身份證正面照
		if("ciYezz5F".equals(action)) {
			try {
				Statement stmt = con.createStatement();
				String cso_no = req.getParameter("cso_no").trim();
				ResultSet rs = stmt.executeQuery(
						"SELECT id_picf FROM campion.campsite_owner WHERE cso_no = " + cso_no);
				
				if(rs.next()) {
					BufferedInputStream bInput= new BufferedInputStream(rs.getBinaryStream("id_picf"));
					byte[] buf = new byte[4*1024];
					int len;
					while((len = bInput.read(buf)) != -1) {
						out.write(buf,0,len);
					}
					bInput.close();
				}else {
					InputStream in = getServletContext().getResourceAsStream("/images/campionLogoCircle.png");
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
		}else if("1YYmZMYO".equals(action)) {//取得身份證反面照
			try {
				Statement stmt = con.createStatement();
				String cso_no = req.getParameter("cso_no").trim();
				ResultSet rs = stmt.executeQuery(
						"SELECT id_picb FROM campion.campsite_owner WHERE cso_no = " + cso_no);
						
				if(rs.next()) {
					BufferedInputStream bInput= new BufferedInputStream(rs.getBinaryStream("id_picb"));
					byte[] buf = new byte[4*1024];
					int len;
					while((len = bInput.read(buf)) != -1) {
						out.write(buf,0,len);
					}
					bInput.close();
				}else {
					InputStream in = getServletContext().getResourceAsStream("/images/campionLogoCircle.png");
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
		}else if("3M0vLk3a".equals(action)) {//取得第二證件照正面
			try {
				Statement stmt = con.createStatement();
				String cso_no = req.getParameter("cso_no").trim();
				ResultSet rs = stmt.executeQuery(
						"SELECT id_pic2f FROM campion.campsite_owner WHERE cso_no = " + cso_no);
						
				if(rs.next()) {
					BufferedInputStream bInput= new BufferedInputStream(rs.getBinaryStream("id_pic2f"));
					byte[] buf = new byte[4*1024];
					int len;
					while((len = bInput.read(buf)) != -1) {
						out.write(buf,0,len);
					}
					bInput.close();
				}else {
					InputStream in = getServletContext().getResourceAsStream("/images/campionLogoCircle.png");
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
		}else if("SiTVUb7c".equals(action)) {//取得第二證件照正面
			try {
				Statement stmt = con.createStatement();
				String cso_no = req.getParameter("cso_no").trim();
				ResultSet rs = stmt.executeQuery(
						"SELECT license FROM campion.campsite_owner WHERE cso_no = " + cso_no);
						
				if(rs.next()) {
					BufferedInputStream bInput= new BufferedInputStream(rs.getBinaryStream("license"));
					byte[] buf = new byte[4*1024];
					int len;
					while((len = bInput.read(buf)) != -1) {
						out.write(buf,0,len);
					}
					bInput.close();
				}else {
					InputStream in = getServletContext().getResourceAsStream("/images/campionLogoCircle.png");
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
		}else {//取得大頭照
			try {
				Statement stmt = con.createStatement();
				String cso_no = req.getParameter("cso_no").trim();
				ResultSet rs = stmt.executeQuery(
						"SELECT sticker FROM campion.campsite_owner WHERE cso_no = " + cso_no);
				
				if(rs.next()) {
					BufferedInputStream bInput= new BufferedInputStream(rs.getBinaryStream("sticker"));
					byte[] buf = new byte[4*1024];
					int len;
					while((len = bInput.read(buf)) != -1) {
						out.write(buf,0,len);
					}
					bInput.close();
				}else {
					InputStream in = getServletContext().getResourceAsStream("/images/campionLogoCircle.png");
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
