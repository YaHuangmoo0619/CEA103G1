package com.product_category.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.product_category.model.*;

@WebServlet("/product_category/product_category.do")
public class Product_categoryServlet extends HttpServlet {

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
				String str = req.getParameter("prod_cat_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�ӫ~�����s��");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_category/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer prod_cat_no = null;
				try {
					prod_cat_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�ӫ~�����s���榡�����T");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_category/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				Product_categoryVO product_categoryVO = product_categorySvc.getOneProduct_category(prod_cat_no);
				if (product_categoryVO == null) {
					errorMsgs.add("�d�L���");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_category/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("product_categoryVO", product_categoryVO); 
				String url = "/back-end/product_category/listOneProduct_category.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_category/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer prod_cat_no = new Integer(req.getParameter("prod_cat_no").trim());
				
				/***************************2.�}�l�d�߸��****************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				Product_categoryVO product_categoryVO = product_categorySvc.getOneProduct_category(prod_cat_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("product_categoryVO", product_categoryVO);        
				String url = "/back-end/product_category/update_Product_category_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_category/listAllProduct_category.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer prod_cat_no = new Integer(req.getParameter("prod_cat_no").trim());
				
				String prod_cat_name = req.getParameter("prod_cat_name");
				String prod_cat_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (prod_cat_name == null || prod_cat_name.trim().length() == 0) {
					errorMsgs.add("�ӫ~�����W��: �ФŪť�");
				} else if(!prod_cat_name.trim().matches(prod_cat_nameReg)) { 
					errorMsgs.add("�ӫ~�����W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				

				Product_categoryVO product_categoryVO = new Product_categoryVO();
				product_categoryVO.setProd_cat_no(prod_cat_no);
				product_categoryVO.setProd_cat_name(prod_cat_name);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_categoryVO", product_categoryVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_category/update_Product_category_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				product_categoryVO = product_categorySvc.updateProduct_category(prod_cat_no, prod_cat_name);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("product_categoryVO", product_categoryVO);
				String url = "/back-end/product_category/listOneProduct_category.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_category/update_Product_category_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String prod_cat_name = req.getParameter("prod_cat_name");
				String prod_cat_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (prod_cat_name == null || prod_cat_name.trim().length() == 0) {
					errorMsgs.add("�ӫ~�����W��: �ФŪť�");
				} else if(!prod_cat_name.trim().matches(prod_cat_nameReg)) { 
					errorMsgs.add("�ӫ~�����W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }

				Product_categoryVO product_categoryVO = new Product_categoryVO();
				product_categoryVO.setProd_cat_name(prod_cat_name);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_categoryVO", product_categoryVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_category/addProduct_category.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				product_categoryVO = product_categorySvc.addProduct_category(prod_cat_name);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back-end/product_category/listAllProduct_category.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllProduct_category.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_category/addProduct_category.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer prod_cat_no = new Integer(req.getParameter("prod_cat_no").trim());
				
				/***************************2.�}�l�R�����***************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				product_categorySvc.deleteProduct_category(prod_cat_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/product_category/listAllProduct_category.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_category/listAllProduct_category.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
