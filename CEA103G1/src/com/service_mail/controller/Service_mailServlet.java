package com.service_mail.controller;

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

import com.service_mail.model.Service_mailService;
import com.service_mail.model.Service_mailVO;

@WebServlet("/service_mail/service_mail.do")
public class Service_mailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("compositeSearch".equals(action)) {
			System.out.println(1);
			Map<String,String[]> map = req.getParameterMap();
			System.out.println(2);
			Service_mailService service_mailSvc = new Service_mailService();
			Set<Service_mailVO> service_mailVOSet = service_mailSvc.getWhereCondition(map);
			System.out.println(3);
			for(Service_mailVO service_mailVO : service_mailVOSet) {
				System.out.println(4);
				System.out.print(service_mailVO.getMail_no()+"<br>");
				System.out.print(service_mailVO.getEmp_no()+"<br>");
				System.out.print(service_mailVO.getMbr_no()+"<br>");
				System.out.print(service_mailVO.getMail_cont().replace("\n", "<br>")+"<br>");
				System.out.print(service_mailVO.getMail_stat()+"<br>");
				System.out.print(service_mailVO.getMail_read_stat()+"<br>");
				System.out.print(service_mailVO.getMail_time()+"<br>");
			}
			System.out.println(5);
		}
	}
}