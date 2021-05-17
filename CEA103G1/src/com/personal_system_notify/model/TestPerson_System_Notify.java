package com.personal_system_notify.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member_mail.model.Member_mailDAO;

@WebServlet("/TestPerson_System_Notify")
public class TestPerson_System_Notify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		Personal_System_NotifyDAO personal_System_NotifyDAO = new Personal_System_NotifyDAO();
		
		out.print("<HTML>");
		out.print("<HEAD><TITLE>TestAuthority</TITLE><STYLE>tr{border:solid 1px black;}</STYLE></HEAD>");
		out.print("<BODY>");
		
		//testInsert
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.format(new java.util.Date());
		Personal_System_NotifyVO personal_System_NotifyVO = new Personal_System_NotifyVO(10002, 0, "test", sdf.format(new java.util.Date()));
		personal_System_NotifyDAO.insert(personal_System_NotifyVO);
		out.print("insert ok");
	
	}

}
