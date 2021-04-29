package com.campsite_picture.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.campsite_picture.model.Camp_PictureService;
import com.campsite_picture.model.Camp_PictureVO;

@WebServlet("/campsite_picture/InsertPicture.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class InsertCamp_Picture extends HttpServlet {
	private static final long serialVersionUID = 2L;
	String saveDirectory = "/images";

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String realPath = getServletContext().getRealPath(saveDirectory);// ªüÄÆ¸ô®|
		File fsaveDirectory = new File(realPath);

		Camp_PictureService camp_pictureSvc = new Camp_PictureService();
		Collection<Part> parts = req.getParts();
		for (Part part : parts) {
			String filename = getFileNameFromPart(part);
			Camp_PictureVO camp_pictureVO = new Camp_PictureVO();
			if (filename != null && part.getContentType() != null) {
				File f = new File(fsaveDirectory, filename);

				part.write(f.toString());
			}
			camp_pictureVO.setCamp_pic(req.getContextPath() + saveDirectory + filename);
		}

	}
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
