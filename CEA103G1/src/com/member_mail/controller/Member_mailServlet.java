package com.member_mail.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member_mail.model.Member_mailService;
import com.member_mail.model.Member_mailVO;

@WebServlet("/member_mail/member_mail.do")
public class Member_mailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("compositeSearch".equals(action)) {
			Map<String,String[]> map = req.getParameterMap();
			
			Member_mailService member_mailSvc = new Member_mailService();
			Set<Member_mailVO> member_mailVOSet = member_mailSvc.getWhereCondition(map);
			for(Member_mailVO member_mailVO : member_mailVOSet) {
				System.out.print(member_mailVO.getMail_no()+"<br>");
				System.out.print(member_mailVO.getSend_no()+"<br>");
				System.out.print(member_mailVO.getRcpt_no()+"<br>");
				System.out.print(member_mailVO.getMail_cont().replace("\n", "<br>")+"<br>");
				System.out.print(member_mailVO.getMail_stat()+"<br>");
				System.out.print(member_mailVO.getMail_read_stat()+"<br>");
				System.out.print(member_mailVO.getMail_time()+"<br>");
			}
		}
	}
}