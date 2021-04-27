package com.article_picture.model;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestDAO3")
public class TestDAO3 extends HttpServlet{

		public void doGet(HttpServletRequest req, HttpServletResponse res) {
			Article_PictureDAO dao = new Article_PictureDAO();
			dao.insert(new Article_PictureVO(20,10,null));
			//dao.update(Article_PictureVO);
			res.setContentType("text/html; charset=UTF-8");
			List<Article_PictureVO> list = dao.getAll();
			try {
				PrintWriter out = res.getWriter();
				out.println("<HTML>");
				out.println("<HEAD><TITLE>Hello World</TITLE></HEAD>");
				out.println("<BODY>");
				out.println("<BIG>Success</BIG>");
				out.println("</BODY></HTML>");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		

}
