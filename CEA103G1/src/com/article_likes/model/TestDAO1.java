package com.article_likes.model;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestDAO1")
public class TestDAO1 extends HttpServlet{

		public void doGet(HttpServletRequest req, HttpServletResponse res) {
			//Article_LikesVO vo = new Article_LikesVO(10001,2);
			Article_LikesDAO dao = new Article_LikesDAO();
			dao.insert(new Article_LikesVO(10001,2));
			//dao.update(Article_LikesVO);
			res.setContentType("text/html; charset=UTF-8");

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
