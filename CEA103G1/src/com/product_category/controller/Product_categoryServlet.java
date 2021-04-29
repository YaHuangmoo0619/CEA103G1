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
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("prod_cat_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品分類編號");
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
					errorMsgs.add("商品分類編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_category/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				Product_categoryVO product_categoryVO = product_categorySvc.getOneProduct_category(prod_cat_no);
				if (product_categoryVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_category/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_categoryVO", product_categoryVO); 
				String url = "/back-end/product_category/listOneProduct_category.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_category/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer prod_cat_no = new Integer(req.getParameter("prod_cat_no").trim());
				
				/***************************2.開始查詢資料****************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				Product_categoryVO product_categoryVO = product_categorySvc.getOneProduct_category(prod_cat_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("product_categoryVO", product_categoryVO);        
				String url = "/back-end/product_category/update_Product_category_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_category/listAllProduct_category.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer prod_cat_no = new Integer(req.getParameter("prod_cat_no").trim());
				
				String prod_cat_name = req.getParameter("prod_cat_name");
				String prod_cat_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (prod_cat_name == null || prod_cat_name.trim().length() == 0) {
					errorMsgs.add("商品分類名稱: 請勿空白");
				} else if(!prod_cat_name.trim().matches(prod_cat_nameReg)) { 
					errorMsgs.add("商品分類名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
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
				
				/***************************2.開始修改資料*****************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				product_categoryVO = product_categorySvc.updateProduct_category(prod_cat_no, prod_cat_name);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_categoryVO", product_categoryVO);
				String url = "/back-end/product_category/listOneProduct_category.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_category/update_Product_category_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String prod_cat_name = req.getParameter("prod_cat_name");
				String prod_cat_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (prod_cat_name == null || prod_cat_name.trim().length() == 0) {
					errorMsgs.add("商品分類名稱: 請勿空白");
				} else if(!prod_cat_name.trim().matches(prod_cat_nameReg)) { 
					errorMsgs.add("商品分類名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }

				Product_categoryVO product_categoryVO = new Product_categoryVO();
				product_categoryVO.setProd_cat_name(prod_cat_name);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_categoryVO", product_categoryVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_category/addProduct_category.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				product_categoryVO = product_categorySvc.addProduct_category(prod_cat_name);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/product_category/listAllProduct_category.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct_category.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
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
				/***************************1.接收請求參數***************************************/
				Integer prod_cat_no = new Integer(req.getParameter("prod_cat_no").trim());
				
				/***************************2.開始刪除資料***************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				product_categorySvc.deleteProduct_category(prod_cat_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/product_category/listAllProduct_category.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_category/listAllProduct_category.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
