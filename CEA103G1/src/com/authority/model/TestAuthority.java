package com.authority.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.authority.model.AuthorityDAO;
import com.authority.model.AuthorityVO;

@WebServlet("/TestAuthority")
public class TestAuthority extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		AuthorityDAO authorityDAO = new AuthorityDAO();

		out.print("<HTML>");
		out.print("<HEAD><TITLE>TestAuthority</TITLE><STYLE>tr{border:solid 1px black;}</STYLE></HEAD>");
		out.print("<BODY>");

//		//testInsert		
//		AuthorityVO authorityVO = new AuthorityVO(90003, 4);
//		authorityDAO.insert(authorityVO);
//		out.print("insert ok");

//		//testFindByPrimaryKey
//		AuthorityVO authorityVO3 = authorityDAO.findByPrimaryKey(90002, 6);
//		out.print(authorityVO3.getEmp_no()+"<br>");
//		out.print(authorityVO3.getFx_no()+"<br>");

//		//testDelete
//		authorityDAO.delete(90003, 4);
//		out.print("delete ok");

//		//testGetAll
//		List<AuthorityVO> list = authorityDAO.getAll();
//		for(AuthorityVO authorityVO4 : list) {
//			out.print(authorityVO4.getEmp_no()+"<br>");
//			out.print(authorityVO4.getFx_no()+"<br>");
//		}		

//		// testFindByFx_no
//		List<AuthorityVO> list = authorityDAO.findByFx_no(3);
//		for (AuthorityVO authorityVO4 : list) {
//			out.print(authorityVO4.getEmp_no() + "<br>");
//			out.print(authorityVO4.getFx_no() + "<br>");
//		}

//		// testfindByEmp_no
//		List<AuthorityVO> list = authorityDAO.findByEmp_no(90003);
//		for (AuthorityVO authorityVO4 : list) {
//			out.print(authorityVO4.getEmp_no() + "<br>");
//			out.print(authorityVO4.getFx_no() + "<br>");
//		}

		out.print("</BODY>");
	}

}
