package com.product_report.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.product_report.model.*;

@WebServlet("/product_report/product_report.do")
public class Product_reportServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("prod_rpt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�ӫ~�^���s��");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_report/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer prod_rpt_no = null;
				try {
					prod_rpt_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�ӫ~�^���s���榡�����T");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_report/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Product_reportService product_reportSvc = new Product_reportService();
				Product_reportVO product_reportVO = product_reportSvc.getOneProduct_report(prod_rpt_no);
				if (product_reportVO == null) {
					errorMsgs.add("�d�L���");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_report/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("product_reportVO", product_reportVO); 
				String url = "/front-end/product_report/listOneProduct_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_report/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer prod_rpt_no = new Integer(req.getParameter("prod_rpt_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				Product_reportService product_reportSvc = new Product_reportService();
				Product_reportVO product_reportVO = product_reportSvc.getOneProduct_report(prod_rpt_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("product_reportVO", product_reportVO);        
				String url = "/front-end/product_report/update_Product_report_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_report/listAllProduct_report.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer prod_rpt_no = new Integer(req.getParameter("prod_rpt_no").trim());
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				Integer prod_no = new Integer(req.getParameter("prod_no").trim());
				String rpt_cont = req.getParameter("rpt_cont");
				Timestamp rpt_time = java.sql.Timestamp.valueOf(req.getParameter("rpt_time").trim());
				Integer proc_stat = new Integer(req.getParameter("proc_stat").trim());

				Product_reportVO product_reportVO = new Product_reportVO();
				product_reportVO.setProd_rpt_no(prod_rpt_no);
				product_reportVO.setMbr_no(mbr_no);
				product_reportVO.setProd_no(prod_no);
				product_reportVO.setRpt_cont(rpt_cont);
				product_reportVO.setRpt_time(rpt_time);
				product_reportVO.setProc_stat(proc_stat);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_reportVO", product_reportVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_report/update_Product_report_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Product_reportService product_reportSvc = new Product_reportService();
				product_reportVO = product_reportSvc.updateProduct_report(prod_rpt_no, mbr_no, prod_no, rpt_cont, rpt_time, proc_stat);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("product_reportVO", product_reportVO);
				String url = "/front-end/product_report/listOneProduct_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_report/update_Product_report_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				Integer prod_no = new Integer(req.getParameter("prod_no").trim());
				String rpt_cont = req.getParameter("rpt_cont");
				Timestamp rpt_time = java.sql.Timestamp.valueOf(req.getParameter("rpt_time").trim());
				Integer proc_stat = new Integer(req.getParameter("proc_stat").trim());

				Product_reportVO product_reportVO = new Product_reportVO();
				product_reportVO.setMbr_no(mbr_no);
				product_reportVO.setProd_no(prod_no);
				product_reportVO.setRpt_cont(rpt_cont);
				product_reportVO.setRpt_time(rpt_time);
				product_reportVO.setProc_stat(proc_stat);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_reportVO", product_reportVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_report/addProduct_report.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				Product_reportService product_reportSvc = new Product_reportService();
				product_reportVO = product_reportSvc.addProduct_report(mbr_no, prod_no, rpt_cont, rpt_time, proc_stat);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/product_report/listAllProduct_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllProduct_report.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/product_report/addProduct_report.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer prod_rpt_no = new Integer(req.getParameter("prod_rpt_no"));
				
				/***************************2.�}�l�R�����***************************************/
				Product_reportService product_reportSvc = new Product_reportService();
				product_reportSvc.deleteProduct_report(prod_rpt_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-end/product_report/listAllProduct_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_report/listAllProduct_report.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
