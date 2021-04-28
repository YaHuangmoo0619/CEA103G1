package com.article_picture.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.announcement.model.AnnouncementService;
import com.announcement.model.AnnouncementVO;
import com.article_picture.model.Article_PictureService;
import com.article_picture.model.Article_PictureVO;


@WebServlet("/article_picture/article_picture.do")
@MultipartConfig
public class Article_PictureServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	
	
	
		
		   if ("insert".equals(action)) {//addArticle.jsp��ajax�ШD
		       	
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.

				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
					Integer art_no = new Integer(req.getParameter("art_no"));
							

					
					Part pic = req.getPart("art_pic");
					InputStream in = pic.getInputStream();
					byte[] art_pic = new byte[in.available()];
					in.read(art_pic);
					if(art_pic.length == 0) {
						errorMsgs.add("�ФW�ǷӤ�");
					}
					
					Article_PictureVO article_pictureVO = new Article_PictureVO();
					article_pictureVO.setArt_no(art_no);
					article_pictureVO.setArt_pic(art_pic);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) { // �t����J�榡���~��announcementVO����,�]�s�Jreq
						req.setAttribute("article_pictureVO", article_pictureVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/article/addArticle.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.�}�l�s�W���***************************************/
					Article_PictureService article_pictureSvc = new Article_PictureService();
					article_pictureVO = article_pictureSvc.addArticle_Picture(art_no, art_pic);
					
					/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//					List<Article_PictureVO> article_pictureVONew = article_pictureSvc.getAll();
//					Integer an_no = article_pictureVONew.get(0).getArt_no();
					req.setAttribute("art_no", art_no);
					String url = "/front-end/article/listOneArticle.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listOneArticle.jsp
					successView.forward(req, res);				
					
					/***************************��L�i�઺���~�B�z**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/listOneArticle.jsp");
					failureView.forward(req, res);
				}
			}
		
		
	}
}
