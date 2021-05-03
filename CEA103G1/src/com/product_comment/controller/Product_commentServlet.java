package com.product_comment.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product_comment.model.Product_commentService;
import com.product_comment.model.Product_commentVO;


@WebServlet("/product_comment/product_comment.do")
public class Product_commentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getDate_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
   
			try {
				if (req.getParameter("cmnt_time").equals("0")) {
					errorMsgs.add("請選擇評論日期");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date dateU = dsf.parse(req.getParameter("cmnt_time"));
				java.sql.Timestamp cmnt_time = new java.sql.Timestamp(dateU.getTime());
							
				Product_commentService product_commentSvc = new Product_commentService();
				List<Product_commentVO> product_commentVO_date = product_commentSvc.getTimestampProd_no(cmnt_time);
				if (product_commentVO_date.size() == 0) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				req.setAttribute("product_commentVO_date", product_commentVO_date);
				String url = "/back-end/product_comment/listDateProduct_comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listDateProduct_comment.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("prod_cmnt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品評論編號");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				if(str.equals("0")) {
					errorMsgs.add("請選擇商品評論編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer prod_cmnt_no = null;
				try {
					prod_cmnt_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("商品評論編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Product_commentService product_commentSvc = new Product_commentService();
				Product_commentVO product_commentVO = product_commentSvc.getOneProduct_comment(prod_cmnt_no);
				if (product_commentVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_commentVO", product_commentVO); // 資料庫取出的product_commentVO物件,存入req
				String url = "/back-end/product_comment/listOneProduct_comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct_comment.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllProduct_comment.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer prod_cmnt_no = new Integer(req.getParameter("prod_cmnt_no"));
				
				/***************************2.開始查詢資料****************************************/
				Product_commentService product_commentSvc = new Product_commentService();
				Product_commentVO product_commentVO = product_commentSvc.getOneProduct_comment(prod_cmnt_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("product_commentVO", product_commentVO);         // 資料庫取出的product_commentVO物件,存入req
				String url = "/back-end/product_comment/update_product_comment_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_product_comment_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment/listAllProduct_comment.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_product_comment_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer prod_cmnt_no = new Integer(req.getParameter("prod_cmnt_no"));
				Integer prod_no = new Integer(req.getParameter("prod_no"));
				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
				if(mbr_no == 0) {
					errorMsgs.add("請選擇發文者");
				}							
				String cmnt_cont = req.getParameter("cmnt_cont");
				if(cmnt_cont == null || cmnt_cont.trim().isEmpty()) {
					errorMsgs.add("請撰寫評論");
				}
				SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date dateU = dsf.parse(req.getParameter("cmnt_time"));
				java.sql.Timestamp cmnt_time = new java.sql.Timestamp(dateU.getTime());
				Integer cmnt_stat = new Integer(req.getParameter("cmnt_stat"));

				Product_commentVO product_commentVO = new Product_commentVO();
				product_commentVO.setProd_cmnt_no(prod_cmnt_no);
				product_commentVO.setProd_no(prod_no);
				product_commentVO.setMbr_no(mbr_no);
				product_commentVO.setCmnt_cont(cmnt_cont);
				product_commentVO.setCmnt_time(cmnt_time);
				product_commentVO.setCmnt_stat(cmnt_stat);
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_commentVO", product_commentVO); // 含有輸入格式錯誤的product_commentVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment/update_product_comment_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Product_commentService product_commentSvc = new Product_commentService();
				product_commentVO = product_commentSvc.updateProduct_comment(prod_cmnt_no,prod_no, mbr_no, cmnt_cont, cmnt_time, cmnt_stat);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_commentVO", product_commentVO); // 資料庫update成功後,正確的的product_commentVO物件,存入req
				String url = "/back-end/product_comment/listOneProduct_comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProduct_comment.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment/update_product_comment_input.jsp");
				failureView.forward(req, res);
			}
		}

		
        if ("insert".equals(action)) { // 來自addProduct_comment.jsp的請求  
       	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
				Integer prod_no = new Integer(req.getParameter("prod_no"));
				if(prod_no == 0) {
					errorMsgs.add("請選擇商品編號");
				}							
				String cmnt_cont = req.getParameter("cmnt_cont");
				if(cmnt_cont == null || cmnt_cont.trim().isEmpty()) {
					errorMsgs.add("請撰寫評論");
				}
				SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date dateU = dsf.parse(req.getParameter("cmnt_time"));
				java.sql.Timestamp cmnt_time = new java.sql.Timestamp(dateU.getTime());
				
				Integer cmnt_stat = new Integer(req.getParameter("cmnt_stat"));
				
				Product_commentVO product_commentVO = new Product_commentVO();
				product_commentVO.setProd_no(prod_no);
				product_commentVO.setMbr_no(mbr_no);
				product_commentVO.setCmnt_cont(cmnt_cont);
				product_commentVO.setCmnt_time(cmnt_time);
				product_commentVO.setCmnt_stat(cmnt_stat);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) { // 含有輸入格式錯誤的product_commentVO物件,也存入req
					req.setAttribute("product_commentVO", product_commentVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment/addProduct_comment.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Product_commentService product_commentSvc = new Product_commentService();
				product_commentVO = product_commentSvc.addProduct_comment(prod_no, mbr_no, cmnt_cont, cmnt_time, cmnt_stat);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/product_comment/listAllProduct_comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct_comment.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment/addProduct_comment.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllProduct_comment.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer prod_cmnt_no = new Integer(req.getParameter("prod_cmnt_no"));
				
				/***************************2.開始刪除資料***************************************/
				Product_commentService product_commentSvc = new Product_commentService();
				product_commentSvc.deleteProduct_comment(prod_cmnt_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/product_comment/listAllProduct_comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment/listAllProduct_comment.jsp");
				failureView.forward(req, res);
			}
		}
	}
}