package com.authority.controller;

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

import com.authority.model.AuthorityService;
import com.authority.model.AuthorityVO;
import com.function.model.FunctionService;

@WebServlet("/authority/authority.do")
public class AuthorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getName_For_Display".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String name = req.getParameter("name");
				if(name.equals("0")) {
					errorMsgs.put("name", "請選擇網站管理員姓名");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/authority/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/authority/listNameAuthority.jsp");
				successView.forward(req, res);
			}catch(Exception e){
				errorMsgs.put("Exception" , e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/authority/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("getFunction_For_Display".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String number = req.getParameter("fx_no");
				if(number.equals("0")) {
					errorMsgs.put("fx_no", "請選擇權限名稱");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/authority/select_page.jsp");
					failureView.forward(req,res);
					return;
				}
				
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/authority/listFx_noAuthority.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/authority/select_page.jsp");
				failureView.forward(req, res);
			}	
		}
		
		if("getOne_For_Update".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
//				Integer emp_no = new Integer(req.getParameter("emp_no"));
				
//				AuthorityService  authoritySvc = new AuthorityService();
//				List<AuthorityVO> list = authoritySvc.findByEmp_no(emp_no);
//				req.setAttribute("list", list);
				
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/authority/update_authority_input.jsp");
				successView.forward(req,res);
				
			}catch(Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/authority/listAllAuthority.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			FunctionService functionSvc = new FunctionService();
			AuthorityService authoritySvc = new AuthorityService();
			
			try {
				//取得前端的參數資料
				Integer emp_no = new Integer(req.getParameter("emp_no"));
				Set<Integer> fx_noSet = new LinkedHashSet<Integer>();
				for(int i = 1; i <= functionSvc.getAll().size(); i++) {
					if(req.getParameter("fx_no"+i) == null) {
						continue;
					}else {
						fx_noSet.add(Integer.valueOf(req.getParameter("fx_no"+i)));
					}
				}
					
				//跟資料庫的資料進行比對
				List<AuthorityVO> authorityVOlist = authoritySvc.findByEmp_no(emp_no);
				Set<Integer> fx_noOriginSet = new LinkedHashSet<Integer>();
				for(AuthorityVO authorityVO : authorityVOlist) {
					fx_noOriginSet.add(authorityVO.getFx_no());
				}
				for(Integer fx_noOrigin : fx_noOriginSet) {
					if(!fx_noSet.contains(fx_noOrigin)){
						authoritySvc.deleteAuthority(emp_no, fx_noOrigin);
					}
				}
				for(Integer fx_no : fx_noSet) {
					if(!fx_noOriginSet.contains(fx_no)){
						authoritySvc.addAuthority(emp_no, fx_no);
					}
				}
				
				//將更新結果回到listAllAuthority.jsp作呈現
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/authority/listAllAuthority.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/authority/listAllAuthority.jsp");
				failureView.forward(req, res);
			}
		}
	}
}