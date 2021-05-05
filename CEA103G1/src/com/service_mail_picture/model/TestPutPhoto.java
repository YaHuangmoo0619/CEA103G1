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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.setContentType("text/html; chartset=UTF-8");
//		PrintWriter out = response.getWriter();
		
		Part part = request.getPart("myfile");
//		String path = request.getContextPath()+"/images/service_mail_picture/test.jpg";
		part.write(part.getSubmittedFileName());
		
//		out.print("getContentType()="+part.getContentType()+"<br>");
//		out.print("getName()="+part.getName()+"<br>");
//		out.print("getSubmittedFileName()="+part.getSubmittedFileName()+"<br>");
//		out.print("part.getHeader(\"content-disposition\")="+part.getHeader("content-disposition"));
//		
//		out.print("test.jpg part.write(\"test.jpg\")="+getServletContext().getRealPath("/"+part.getSubmittedFileName())+"<br>");
//		
//		File file = new File(getServletContext().getRealPath("/"+part.getSubmittedFileName()));
//		out.print("test.jpg file="+file.getAbsolutePath());
//		FileInputStream fin = new FileInputStream(file);
//		BufferedInputStream bin = new BufferedInputStream(fin);
//		byte[] b = new byte[bin.available()];
//		bin.read(b);
//		File file2 = new File(getServletContext().getRealPath("/WebContent/images/service_mail_picture")+"/testtest.jsp");
//		FileOutputStream fout = new FileOutputStream(file2);
//		BufferedOutputStream bout = new BufferedOutputStream(fout);
//		bout.write(b);
		
//		bout.close();
//		fout.close();
//		bin.close();
//		fin.close();
//		
//		out.print("testtest.jpg="+getServletContext().getRealPath("testtest.jpg"));
	}

//	public String getFileNameFromPart(Part part) {
//		String header = part.getHeader("content-disposition");
//System.out.println(new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName());
//		String[] filename = (new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName()).split("\\.");
//		for(int i = 0; i < filename.length; i++) {
//System.out.println(filename[i]);
//		}
//		String extension = filename[1];
//System.out.println(extension);
//		if (extension.length() == 0) {
//			return null;
//		}
//		return extension;
//	}
//	
//	public List<String> savePictureAtLocal(HttpServletRequest req, String camp_name) throws IOException, ServletException{
//		List<String> fileDirectory = new ArrayList();
//		String realPath = getServletContext().getRealPath(saveDirectory);// ªüÄÆ¸ô®|
//		File fsaveDirectory = new File(realPath);
//System.out.println("¦n");
//		Camp_PictureService camp_pictureSvc = new Camp_PictureService();
//		int count = 1;
//		Collection<Part> parts = req.getParts();
//		for (Part part : parts) {
//			if(!("photo".equals(part.getName()))) {
//				continue;
//			}
//			String extension = getFileNameFromPart(part);
//			String filename = camp_name + count + "." + extension;
//			count++;
//			Camp_PictureVO camp_pictureVO = new Camp_PictureVO();
//			if (filename != null && part.getContentType() != null) {
//				File f = new File(fsaveDirectory, filename);
//				part.write(f.toString());
//			}
//			camp_pictureVO.setCamp_pic(req.getContextPath() + saveDirectory + filename);
//			filename = req.getContextPath() + saveDirectory + "/" + filename;
//			fileDirectory.add(filename);
//		}
//		
//		return fileDirectory;		
//	}
}
