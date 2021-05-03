package com.product_comment_report.controller;

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

import com.product_comment_report.model.Product_comment_reportService;
import com.product_comment_report.model.Product_comment_reportVO;


@WebServlet("/product_comment_report/product_comment_report.do")
public class Product_comment_reportServlet extends HttpServlet {
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
				if (req.getParameter("rpt_time").equals("0")) {
					errorMsgs.add("請選擇檢舉日期");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment_report/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date dateU = dsf.parse(req.getParameter("rpt_time"));
				java.sql.Timestamp rpt_time = new java.sql.Timestamp(dateU.getTime());
							
				Product_comment_reportService product_comment_reportSvc = new Product_comment_reportService();
				List<Product_comment_reportVO> product_comment_reportVO_date = product_comment_reportSvc.getTimestampProd_cmnt_no(rpt_time);
				if (product_comment_reportVO_date.size() == 0) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment_report/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				req.setAttribute("product_comment_reportVO_date", product_comment_reportVO_date);
				String url = "/back-end/product_comment_report/listDateProduct_comment_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listDateProduct_comment_report.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment_report/select_page.jsp");
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
				String str = req.getParameter("prod_cmnt_rpt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品評論檢舉編號");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment_report/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				if(str.equals("0")) {
					errorMsgs.add("請選擇商品評論檢舉編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment_report/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer prod_cmnt_rpt_no = null;
				try {
					prod_cmnt_rpt_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("商品評論檢舉編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment_report/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Product_comment_reportService product_comment_reportSvc = new Product_comment_reportService();
				Product_comment_reportVO product_comment_reportVO = product_comment_reportSvc.getOneProduct_comment_report(prod_cmnt_rpt_no);
				if (product_comment_reportVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment_report/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_comment_reportVO", product_comment_reportVO); // 資料庫取出的product_comment_reportVO物件,存入req
				String url = "/back-end/product_comment_report/listOneProduct_comment_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct_comment_report.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment_report/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllProduct_comment_report.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer prod_cmnt_rpt_no = new Integer(req.getParameter("prod_cmnt_rpt_no"));
				
				/***************************2.開始查詢資料****************************************/
				Product_comment_reportService product_comment_reportSvc = new Product_comment_reportService();
				Product_comment_reportVO product_comment_reportVO = product_comment_reportSvc.getOneProduct_comment_report(prod_cmnt_rpt_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("product_comment_reportVO", product_comment_reportVO);         // 資料庫取出的product_comment_reportVO物件,存入req
				String url = "/back-end/product_comment_report/update_product_comment_report_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_product_comment_report_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment_report/listAllProduct_comment_report.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_product_comment_report_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer prod_cmnt_rpt_no = new Integer(req.getParameter("prod_cmnt_rpt_no"));
				Integer prod_cmnt_no = new Integer(req.getParameter("prod_cmnt_no"));
				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
				if(mbr_no == 0) {
					errorMsgs.add("請選擇發文者");
				}							
				String rpt_cont = req.getParameter("rpt_cont");
				if(rpt_cont == null || rpt_cont.trim().isEmpty()) {
					errorMsgs.add("請撰寫檢舉內容");
				}
				SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date dateU = dsf.parse(req.getParameter("rpt_time"));
				java.sql.Timestamp rpt_time = new java.sql.Timestamp(dateU.getTime());
				Integer proc_stat = new Integer(req.getParameter("proc_stat"));

				Product_comment_reportVO product_comment_reportVO = new Product_comment_reportVO();
				product_comment_reportVO.setProd_cmnt_rpt_no(prod_cmnt_rpt_no);
				product_comment_reportVO.setProd_cmnt_no(prod_cmnt_no);
				product_comment_reportVO.setMbr_no(mbr_no);
				product_comment_reportVO.setRpt_cont(rpt_cont);
				product_comment_reportVO.setRpt_time(rpt_time);
				product_comment_reportVO.setProc_stat(proc_stat);
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_comment_reportVO", product_comment_reportVO); // 含有輸入格式錯誤的product_comment_reportVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment_report/update_product_comment_report_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Product_comment_reportService product_comment_reportSvc = new Product_comment_reportService();
				product_comment_reportVO = product_comment_reportSvc.updateProduct_comment_report(prod_cmnt_rpt_no, mbr_no, prod_cmnt_no, rpt_cont, rpt_time, proc_stat);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_comment_reportVO", product_comment_reportVO); // 資料庫update成功後,正確的的product_comment_reportVO物件,存入req
				String url = "/back-end/product_comment_report/listOneProduct_comment_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProduct_comment_report.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment_report/update_product_comment_report_input.jsp");
				failureView.forward(req, res);
			}
		}

		
        if ("insert".equals(action)) { // 來自addProduct_comment_report.jsp的請求  
       	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
				Integer prod_cmnt_no = new Integer(req.getParameter("prod_no"));
				if(prod_cmnt_no == 0) {
					errorMsgs.add("請選擇評論編號");
				}							
				String rpt_cont = req.getParameter("rpt_cont");
				if(rpt_cont == null || rpt_cont.trim().isEmpty()) {
					errorMsgs.add("請撰寫評論");
				}
				SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date dateU = dsf.parse(req.getParameter("rpt_time"));
				java.sql.Timestamp rpt_time = new java.sql.Timestamp(dateU.getTime());
				
				Integer proc_stat = new Integer(req.getParameter("proc_stat"));
				
				Product_comment_reportVO product_comment_reportVO = new Product_comment_reportVO();
				product_comment_reportVO.setProd_cmnt_no(prod_cmnt_no);
				product_comment_reportVO.setMbr_no(mbr_no);
				product_comment_reportVO.setRpt_cont(rpt_cont);
				product_comment_reportVO.setRpt_time(rpt_time);
				product_comment_reportVO.setProc_stat(proc_stat);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) { // 含有輸入格式錯誤的product_comment_reportVO物件,也存入req
					req.setAttribute("product_comment_reportVO", product_comment_reportVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment_report/addProduct_comment_report.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Product_comment_reportService product_comment_reportSvc = new Product_comment_reportService();
				product_comment_reportVO = product_comment_reportSvc.addProduct_comment_report(prod_cmnt_no, mbr_no, rpt_cont, rpt_time, proc_stat);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/product_comment_report/listAllProduct_comment_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct_comment_report.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment_report/addProduct_comment_report.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllProduct_comment_report.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer prod_cmnt_rpt_no = new Integer(req.getParameter("prod_cmnt_rpt_no"));
				
				/***************************2.開始刪除資料***************************************/
				Product_comment_reportService product_comment_reportSvc = new Product_comment_reportService();
				product_comment_reportSvc.deleteProduct_comment_report(prod_cmnt_rpt_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/product_comment_report/listAllProduct_comment_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment_report/listAllProduct_comment_report.jsp");
				failureView.forward(req, res);
			}
		}
	}
}