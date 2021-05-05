package com.service_mail_picture.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service_mail_picture.model.Service_mail_pictureDAO;
import com.service_mail_picture.model.Service_mail_pictureVO;

@WebServlet("/TestService_mail_picture")
public class TestService_mail_picture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		Service_mail_pictureDAO service_mail_pictureDAO = new Service_mail_pictureDAO();
		
		out.print("<HTML>");
		out.print("<HEAD><TITLE>TestService_mail_picture</TITLE><STYLE>tr{border:solid 1px black;}</STYLE></HEAD>");		
		out.print("<BODY>");
		
//		//testInsert		
//		InputStream in = getServletContext().getResourceAsStream("/images/relax.png");
//		long millis = System.currentTimeMillis();
//		java.sql.Date date = new java.sql.Date(millis);
//		byte[] b = new byte[in.available()];
//		in.read(b);
//		Service_mail_pictureVO service_mail_pictureVO = new Service_mail_pictureVO(90002, "abc");
//		service_mail_pictureDAO.insert(service_mail_pictureVO);
//		out.print("insert ok");
//		
//		in.close();
		
//		//testUpdate
//		InputStream in2 = getServletContext().getResourceAsStream("/images/campionLogoLong.png");
//		long millis2 = System.currentTimeMillis();
//		java.sql.Date date2 = new java.sql.Date(millis2);
//		byte[] b2 = new byte[in2.available()];
//		in2.read(b2);
//		Service_mail_pictureVO service_mail_pictureVO2 = new Service_mail_pictureVO(90002, "´ú¸Õupdate", date2, b2);
//		service_mail_pictureVO2.setAn_no(12);
//		
//		service_mail_pictureDAO.update(service_mail_pictureVO2);
//		out.print("update ok" + service_mail_pictureVO2.getAn_no() + service_mail_pictureVO2.getAn_cont());
//		
//		in2.close();
		
//		//testFindByPrimaryKey
//		Service_mail_pictureVO service_mail_pictureVO3 = service_mail_pictureDAO.findByPrimaryKey(2);
//		out.print(service_mail_pictureVO3.getAn_no()+"<br>");
//		out.print(service_mail_pictureVO3.getEmp_no()+"<br>");
//		out.print(service_mail_pictureVO3.getAn_cont().replace("\n", "<br>")+"<br>");
//		out.print(service_mail_pictureVO3.getAn_skd_date()+"<br>");
//		out.print("<img src=\"/CEA103G1/service_mail_picture/GetPhoto?an_no="+service_mail_pictureVO3.getAn_no()+"\" style=\"width:50%\"><br>");
		
//		//testDelete
//		service_mail_pictureDAO.delete(13);
//		out.print("delete ok");
			
//		//testGetAll
//		List<Service_mail_pictureVO> list = service_mail_pictureDAO.getAll();
//		for(Service_mail_pictureVO service_mail_pictureVO4 : list) {
//			out.print(service_mail_pictureVO4.getAn_no()+"<br>");
//			out.print(service_mail_pictureVO4.getEmp_no()+"<br>");
//			out.print(service_mail_pictureVO4.getAn_cont().replace("\n", "<br>")+"<br>");
//			out.print(service_mail_pictureVO4.getAn_skd_date()+"<br>");
//			out.print("<img src=\"/CEA103G1/service_mail_picture/GetPhoto?an_no="+service_mail_pictureVO4.getAn_no()+"\" style=\"width:50%\"><br>");
//		}		
		
//		//testGetAll
//		long millis2 = System.currentTimeMillis();
//		java.sql.Date date2 = new java.sql.Date(millis2);
//		List<Service_mail_pictureVO> list = service_mail_pictureDAO.getDateEmp_no(date2);
//		for(Service_mail_pictureVO service_mail_pictureVO4 : list) {
//			out.print(service_mail_pictureVO4.getAn_no()+"<br>");
//			out.print(service_mail_pictureVO4.getEmp_no()+"<br>");
//			out.print(service_mail_pictureVO4.getAn_cont().replace("\n", "<br>")+"<br>");
//			out.print(service_mail_pictureVO4.getAn_skd_date()+"<br>");
//			out.print("<img src=\"/CEA103G1/service_mail_picture/GetPhoto?an_no="+service_mail_pictureVO4.getAn_no()+"\" style=\"width:50%\"><br>");
//		}		
		
		out.print("</BODY>");
	}

}
