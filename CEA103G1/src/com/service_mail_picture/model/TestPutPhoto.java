package com.service_mail_picture.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.campsite_picture.model.Camp_PictureService;
import com.campsite_picture.model.Camp_PictureVO;

@WebServlet("/TestPutPhoto")
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
public class TestPutPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String saveDirectory = "/images/service_mail_picture";
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; chartset=UTF-8");
		PrintWriter out = res.getWriter();
		
		//testInsert
		String realPath = getServletContext().getRealPath(saveDirectory);
//		System.out.println(realPath);
		File fsaveDirectory = new File(realPath);
		if(!fsaveDirectory.exists()) {
			fsaveDirectory.mkdirs();
		}
		int count = 1;
		Collection<Part> parts = req.getParts();
		for(Part part : parts) {
			String fileType = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
//			System.out.println(part.getSubmittedFileName()+"/"+fileType);
			
			File f = new File(fsaveDirectory, "service_mail_picture"+count+fileType);
			part.write(f.toString());
			count++;
			System.out.print(f.toString());
			Service_mail_pictureDAO service_mail_pictureDAO = new Service_mail_pictureDAO();
			Service_mail_pictureVO service_mail_pictureVO = new Service_mail_pictureVO(80004,req.getContextPath()+"/images/service_mail_picture/service_mail_picture"+count+fileType);
			service_mail_pictureDAO.insert(service_mail_pictureVO);
		}
		out.print("insert ok");
		
		//TestUpdate
		
	}
}
