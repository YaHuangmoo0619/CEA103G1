package com.service_mail_picture.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/TestPutPhoto")
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
public class TestPutPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; chartset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Part part = request.getPart("myfile");
		String path = request.getContextPath()+"/images/service_mail_picture/test.jpg";
		part.write("test.jpg");
		
		File file = new File(getServletContext().getRealPath("test.jpg"));
//		FileInputStream
		
		
		out.print(getServletContext().getRealPath("test.jpg"));
	}

}
