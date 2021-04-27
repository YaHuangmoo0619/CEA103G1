package com.announcement.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/TestAnnouncement")
public class TestAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		AnnouncementDAO announcementDAO = new AnnouncementDAO();
		
		out.print("<HTML>");
		out.print("<HEAD><TITLE>TestAnnouncement</TITLE><STYLE>tr{border:solid 1px black;}</STYLE></HEAD>");		
		out.print("<BODY>");
		
//		//testInsert		
//		InputStream in = getServletContext().getResourceAsStream("/images/relax.png");
//		long millis = System.currentTimeMillis();
//		java.sql.Date date = new java.sql.Date(millis);
//		byte[] b = new byte[in.available()];
//		in.read(b);
//		AnnouncementVO announcementVO = new AnnouncementVO(90002, "我是測試", date, b);
//		announcementDAO.insert(announcementVO);
//		out.print("insert ok");
//		
//		in.close();
		
//		//testUpdate
//		InputStream in2 = getServletContext().getResourceAsStream("/images/campionLogoLong.png");
//		long millis2 = System.currentTimeMillis();
//		java.sql.Date date2 = new java.sql.Date(millis2);
//		byte[] b2 = new byte[in2.available()];
//		in2.read(b2);
//		AnnouncementVO announcementVO2 = new AnnouncementVO(90002, "測試update", date2, b2);
//		announcementVO2.setAn_no(12);
//		
//		announcementDAO.update(announcementVO2);
//		out.print("update ok" + announcementVO2.getAn_no() + announcementVO2.getAn_cont());
//		
//		in2.close();
		
//		//testFindByPrimaryKey
//		AnnouncementVO announcementVO3 = announcementDAO.findByPrimaryKey(2);
//		out.print(announcementVO3.getAn_no()+"<br>");
//		out.print(announcementVO3.getEmp_no()+"<br>");
//		out.print(announcementVO3.getAn_cont().replace("\n", "<br>")+"<br>");
//		out.print(announcementVO3.getAn_skd_date()+"<br>");
//		out.print("<img src=\"/CEA103G1/announcement/GetPhoto?an_no="+announcementVO3.getAn_no()+"\" style=\"width:50%\"><br>");
		
//		//testDelete
//		announcementDAO.delete(13);
//		out.print("delete ok");
			
//		//testGetAll
//		List<AnnouncementVO> list = announcementDAO.getAll();
//		for(AnnouncementVO announcementVO4 : list) {
//			out.print(announcementVO4.getAn_no()+"<br>");
//			out.print(announcementVO4.getEmp_no()+"<br>");
//			out.print(announcementVO4.getAn_cont().replace("\n", "<br>")+"<br>");
//			out.print(announcementVO4.getAn_skd_date()+"<br>");
//			out.print("<img src=\"/CEA103G1/announcement/GetPhoto?an_no="+announcementVO4.getAn_no()+"\" style=\"width:50%\"><br>");
//		}		
		
		//testGetAll
		long millis2 = System.currentTimeMillis();
		java.sql.Date date2 = new java.sql.Date(millis2);
		List<AnnouncementVO> list = announcementDAO.getDateEmp_no(date2);
		for(AnnouncementVO announcementVO4 : list) {
			out.print(announcementVO4.getAn_no()+"<br>");
			out.print(announcementVO4.getEmp_no()+"<br>");
			out.print(announcementVO4.getAn_cont().replace("\n", "<br>")+"<br>");
			out.print(announcementVO4.getAn_skd_date()+"<br>");
			out.print("<img src=\"/CEA103G1/announcement/GetPhoto?an_no="+announcementVO4.getAn_no()+"\" style=\"width:50%\"><br>");
		}		
		
		out.print("</BODY>");
	}
}
