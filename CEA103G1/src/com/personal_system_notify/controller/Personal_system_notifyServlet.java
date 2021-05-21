package com.personal_system_notify.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.personal_system_notify.model.Personal_System_NotifyService;
import com.personal_system_notify.model.Personal_System_NotifyVO;


@WebServlet("/personal_system_notify/personal_system_notify.do")
public class Personal_system_notifyServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("read".equals(action)) {
			try {
				Personal_System_NotifyService personal_System_NotifySvc = new Personal_System_NotifyService();
				Personal_System_NotifyVO personal_System_NotifyVO = personal_System_NotifySvc.getOnePersonal_System_Notify(Integer.valueOf(req.getParameter("ntfy_no")));
				personal_System_NotifyVO.setNtfy_stat(1);
				
				Integer ntfy_no =  personal_System_NotifyVO.getNtfy_no();
				Integer mbr_no =  personal_System_NotifyVO.getMbr_no();
				Integer ntfy_stat =  personal_System_NotifyVO.getNtfy_stat();
				String ntfy_cont =  personal_System_NotifyVO.getNtfy_cont();
				String ntfy_time =  personal_System_NotifyVO.getNtfy_time();
				Personal_System_NotifyVO personal_System_NotifyVO2 = personal_System_NotifySvc.updatePersonal_System_Notify(ntfy_no, mbr_no,  ntfy_stat, ntfy_cont,  ntfy_time);
				
//				req.setAttribute("personal_System_NotifyVO", personal_System_NotifyVO2);
//				RequestDispatcher successView = req.getRequestDispatcher("/front-end/personal_system_notify/listAllPersonal_system_notify.jsp");
//				successView.forward(req, res);
				res.sendRedirect(req.getContextPath()+"/front-end/personal_system_notify/listAllPersonal_system_notify.jsp");
			}catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/personal_system_notify/listAllPersonal_system_notify.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
