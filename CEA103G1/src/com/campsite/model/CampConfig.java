package com.campsite.model;

import java.io.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class CampConfig extends HttpServlet {
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/jpeg");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String camp_no = req.getParameter("camp_no").trim();
			ResultSet rs = stmt.executeQuery("SELECT CONFIG FROM CAMPSITE WHERE CAMP_NO = " + camp_no);
//			ResultSet rs = stmt.executeQuery("SELECT CONFIG FROM CAMPSITE WHERE CAMP_NO = 1");
		

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("CONFIG"));
				byte[] buf = new byte[in.available()]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			}
		} catch (Exception e) {
			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
		}
	}

	public void init() throws ServletException {
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
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
