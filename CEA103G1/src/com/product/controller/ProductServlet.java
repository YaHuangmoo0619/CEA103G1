package com.product.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.product.model.*;

@WebServlet("/product/product.do")
public class ProductServlet extends HttpServlet {

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
				String str = req.getParameter("prod_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�ӫ~�s��");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer prod_no = null;
				try {
					prod_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�ӫ~�s���榡�����T");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(prod_no);
				if (productVO == null) {
					errorMsgs.add("�d�L���");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("productVO", productVO); 
				String url = "/front-end/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer prod_no = new Integer(req.getParameter("prod_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(prod_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("productVO", productVO);        
				String url = "/front-end/product/update_Product_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer prod_no = new Integer(req.getParameter("prod_no").trim());
				Integer prod_cat_no = new Integer(req.getParameter("prod_cat_no").trim());
				Integer prod_stat = new Integer(req.getParameter("prod_stat"));
				String prod_name = req.getParameter("prod_name");
				Integer prod_pc = new Integer(req.getParameter("prod_pc").trim());
				Integer prod_stg = new Integer(req.getParameter("prod_stg").trim());
				String prod_info = req.getParameter("prod_info");
				String prod_bnd = req.getParameter("prod_bnd");
				String prod_clr = req.getParameter("prod_clr");
				String prod_size = req.getParameter("prod_size");
				Integer ship_meth = new Integer(req.getParameter("ship_meth").trim());
				
				ProductVO productVO = new ProductVO();
				productVO.setProd_no(prod_no);
				productVO.setProd_cat_no(prod_cat_no);
				productVO.setProd_stat(prod_stat);
				productVO.setProd_name(prod_name);
				productVO.setProd_pc(prod_pc);
				productVO.setProd_stg(prod_stg);
				productVO.setProd_info(prod_info);
				productVO.setProd_bnd(prod_bnd);
				productVO.setProd_clr(prod_clr);
				productVO.setProd_size(prod_size);
				productVO.setShip_meth(ship_meth);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/update_Product_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�ק���*****************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProduct(prod_no, prod_cat_no, prod_stat, prod_name, prod_pc, prod_stg, prod_info, prod_bnd, prod_clr, prod_size, ship_meth);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("productVO", productVO);
				String url = "/front-end/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/update_Product_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				Integer prod_cat_no = new Integer(req.getParameter("prod_cat_no").trim());
				Integer prod_stat = new Integer(req.getParameter("prod_stat"));
				String prod_name = req.getParameter("prod_name");
				Integer prod_pc = new Integer(req.getParameter("prod_pc").trim());
				Integer prod_stg = new Integer(req.getParameter("prod_stg").trim());
				String prod_info = req.getParameter("prod_info");
				String prod_bnd = req.getParameter("prod_bnd");
				String prod_clr = req.getParameter("prod_clr");
				String prod_size = req.getParameter("prod_size");
				Integer ship_meth = new Integer(req.getParameter("ship_meth").trim());

				ProductVO productVO = new ProductVO();
				productVO.setProd_cat_no(prod_cat_no);
				productVO.setProd_stat(prod_stat);
				productVO.setProd_name(prod_name);
				productVO.setProd_pc(prod_pc);
				productVO.setProd_stg(prod_stg);
				productVO.setProd_info(prod_info);
				productVO.setProd_bnd(prod_bnd);
				productVO.setProd_clr(prod_clr);
				productVO.setProd_size(prod_size);
				productVO.setShip_meth(ship_meth);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.addProduct(prod_cat_no, prod_stat, prod_name, prod_pc, prod_stg, prod_info, prod_bnd, prod_clr, prod_size, ship_meth);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllProduct.jsp
				successView.forward(req, res);	
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/product/addProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer prod_no = new Integer(req.getParameter("prod_no"));
				
				/***************************2.�}�l�R�����***************************************/
				ProductService productSvc = new ProductService();
				productSvc.deleteProduct(prod_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-end/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
	}
}