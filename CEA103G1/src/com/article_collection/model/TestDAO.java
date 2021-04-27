package com.article_collection.model;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestDAO")
public class TestDAO extends HttpServlet{

		public void doGet(HttpServletRequest req, HttpServletResponse res) {
			//Article_CollectionVO vo = new Article_CollectionVO(10009,5);
			Article_CollectionDAO dao = new Article_CollectionDAO();
			dao.insert(new Article_CollectionVO(10001,6));
			//dao.update(Article_CollectionVO);
			res.setContentType("text/html; charset=UTF-8");
//			List<Article_CollectionVO> list = dao.getAll();
			try {
				PrintWriter out = res.getWriter();
				out.println("<HTML>");
				out.println("<HEAD><TITLE>Hello World</TITLE></HEAD>");
				out.println("<BODY>");
//				out.println("<BIG>Success</BIG>"+list.get(3).getArt_no());
				out.println("</BODY></HTML>");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		

}
