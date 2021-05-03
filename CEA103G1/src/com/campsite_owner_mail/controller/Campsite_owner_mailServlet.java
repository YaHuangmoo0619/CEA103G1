package com.campsite_owner_mail.controller;

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

import com.campsite_owner_mail.model.Campsite_owner_mailService;
import com.campsite_owner_mail.model.Campsite_owner_mailVO;

@WebServlet("/campsite_owner_mail/campsite_owner_mail.do")
public class Campsite_owner_mailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("compositeSearch".equals(action)) {
			Map<String,String[]> map = req.getParameterMap();
			
			Campsite_owner_mailService campsite_owner_mailSvc = new Campsite_owner_mailService();
			Set<Campsite_owner_mailVO> campsite_owner_mailVOSet = campsite_owner_mailSvc.getWhereCondition(map);
			for(Campsite_owner_mailVO campsite_owner_mailVO : campsite_owner_mailVOSet) {
				System.out.print(campsite_owner_mailVO.getMail_no()+"<br>");
				System.out.print(campsite_owner_mailVO.getSend_no()+"<br>");
				System.out.print(campsite_owner_mailVO.getRcpt_no()+"<br>");
				System.out.print(campsite_owner_mailVO.getMail_cont().replace("\n", "<br>")+"<br>");
				System.out.print(campsite_owner_mailVO.getMail_stat()+"<br>");
				System.out.print(campsite_owner_mailVO.getMail_read_stat()+"<br>");
				System.out.print(campsite_owner_mailVO.getMail_time()+"<br>");
			}
		}
	}
}