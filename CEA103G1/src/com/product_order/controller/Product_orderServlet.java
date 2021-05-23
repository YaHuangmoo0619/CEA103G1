package com.product_order.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.product.model.ProductDAO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_order.model.*;

@WebServlet("/product_order/product_order.do")
public class Product_orderServlet extends HttpServlet {

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
				String str = req.getParameter("prod_ord_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�ӫ~�q��s��");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer prod_ord_no = null;
				try {
					prod_ord_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�ӫ~�q��s���榡�����T");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				Product_orderVO product_orderVO = product_orderSvc.getOneProduct_order(prod_ord_no);
				if (product_orderVO == null) {
					errorMsgs.add("�d�L���");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("product_orderVO", product_orderVO); 
				String url = "/front-end/product_order/listOneProduct_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer prod_ord_no = new Integer(req.getParameter("prod_ord_no").trim());
				
				/***************************2.�}�l�d�߸��****************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				Product_orderVO product_orderVO = product_orderSvc.getOneProduct_order(prod_ord_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("product_orderVO", product_orderVO);        
				String url = "/front-end/product_order/update_Product_order_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/listAllProduct_order.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer prod_ord_no = new Integer(req.getParameter("prod_ord_no").trim());
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				Timestamp prod_ord_time = java.sql.Timestamp.valueOf(req.getParameter("prod_ord_time").trim());
				Integer prod_ord_stat = new Integer(req.getParameter("prod_ord_stat").trim());
				Integer prod_ord_sum = new Integer(req.getParameter("prod_ord_sum").trim());
				Integer used_pt = new Integer(req.getParameter("used_pt").trim());
				Integer ship_meth = new Integer(req.getParameter("ship_meth").trim());
				Integer pay_meth = new Integer(req.getParameter("pay_meth").trim());
				String ship_cty = req.getParameter("ship_cty");
				String ship_dist = req.getParameter("ship_dist");
				String ship_add = req.getParameter("ship_add");
				Integer receipt = new Integer(req.getParameter("receipt").trim());
				String rmk = req.getParameter("rmk");
				
				Product_orderVO product_orderVO = new Product_orderVO();
				product_orderVO.setProd_ord_no(prod_ord_no);
				product_orderVO.setMbr_no(mbr_no);
				product_orderVO.setProd_ord_time(prod_ord_time);
				product_orderVO.setProd_ord_stat(prod_ord_stat);
				product_orderVO.setProd_ord_sum(prod_ord_sum);
				product_orderVO.setUsed_pt(used_pt);
				product_orderVO.setShip_meth(ship_meth);
				product_orderVO.setPay_meth(pay_meth);
				product_orderVO.setShip_cty(ship_cty);
				product_orderVO.setShip_dist(ship_dist);
				product_orderVO.setReceipt(receipt);
				product_orderVO.setRmk(rmk);
								
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_orderVO", product_orderVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/update_Product_order_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				product_orderVO = product_orderSvc.updateProduct_order(prod_ord_no, mbr_no, prod_ord_time, prod_ord_stat, prod_ord_sum, used_pt, ship_meth, pay_meth, ship_cty, ship_dist, ship_add, receipt, rmk);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("product_orderVO", product_orderVO);
				String url = "/front-end/product_order/listOneProduct_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/update_Product_order_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				Timestamp prod_ord_time = java.sql.Timestamp.valueOf(req.getParameter("prod_ord_time").trim());
				Integer prod_ord_stat = new Integer(req.getParameter("prod_ord_stat").trim());
				Integer prod_ord_sum = new Integer(req.getParameter("prod_ord_sum").trim());
				Integer used_pt = new Integer(req.getParameter("used_pt").trim());
				Integer ship_meth = new Integer(req.getParameter("ship_meth").trim());
				Integer pay_meth = new Integer(req.getParameter("pay_meth").trim());
				String ship_cty = req.getParameter("ship_cty");
				String ship_dist = req.getParameter("ship_dist");
				String ship_add = req.getParameter("ship_add");
				Integer receipt = new Integer(req.getParameter("receipt").trim());
				String rmk = req.getParameter("rmk");

				Product_orderVO product_orderVO = new Product_orderVO();
				product_orderVO.setMbr_no(mbr_no);
				product_orderVO.setProd_ord_time(prod_ord_time);
				product_orderVO.setProd_ord_stat(prod_ord_stat);
				product_orderVO.setProd_ord_sum(prod_ord_sum);
				product_orderVO.setUsed_pt(used_pt);
				product_orderVO.setShip_meth(ship_meth);
				product_orderVO.setPay_meth(pay_meth);
				product_orderVO.setShip_cty(ship_cty);
				product_orderVO.setShip_dist(ship_dist);
				product_orderVO.setReceipt(receipt);
				product_orderVO.setRmk(rmk);	
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_orderVO", product_orderVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/addProduct_order.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				product_orderVO = product_orderSvc.addProduct_order(mbr_no, prod_ord_time, prod_ord_stat, prod_ord_sum, used_pt, ship_meth, pay_meth, ship_cty, ship_dist, ship_add, receipt, rmk);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/product_order/listAllProduct_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllProduct_order.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/product_order/addProduct_order.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer prod_ord_no = new Integer(req.getParameter("prod_ord_no").trim());
				
				/***************************2.�}�l�R�����***************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				product_orderSvc.deleteProduct_order(prod_ord_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-end/product_order/listAllProduct_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/listAllProduct_order.jsp");
				failureView.forward(req, res);
			}
		}
		
		//���ĥ[��
		if("read".equals(action)) {
			try {
				Product_orderService product_orderSvc = new Product_orderService();
				Product_orderVO product_orderVO = product_orderSvc.getOneProduct_order(Integer.valueOf(req.getParameter("prod_ord_no")));
				
				//Bootstrap_modal
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				req.setAttribute("product_orderVO", product_orderVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/product_order/listAllProduct_order_fromList.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_order/listAllProduct_order_fromList.jsp");
				failureView.forward(req, res);
			}
		}
		if("update_order_stat".equals(action)) {
			try {
				Integer prod_ord_stat = Integer.valueOf(req.getParameter("prod_ord_stat"));
//				System.out.println(prod_ord_stat);
				Integer prod_ord_no = Integer.valueOf(req.getParameter("prod_ord_no"));
//				System.out.println(prod_ord_no);
				
				Product_orderService product_orderSvc = new Product_orderService();
				product_orderSvc.update_order_stat(prod_ord_stat, prod_ord_no);
				Product_orderVO product_orderVO = product_orderSvc.getOneProduct_order(prod_ord_no);
				req.setAttribute("product_orderVO", product_orderVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/product_order/listAllProduct_order_fromList.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_order/listAllProduct_order_fromList.jsp");
				failureView.forward(req, res);
			}
			
		}
		//���ĥ[��
	}
}
