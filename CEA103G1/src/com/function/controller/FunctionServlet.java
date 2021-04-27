package com.function.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.function.model.FunctionService;
import com.function.model.FunctionVO;


@WebServlet("/function/function.do")
public class FunctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("fx_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入權限編號");
				}
				if(str.equals("0")) {
					errorMsgs.add("請選擇權限編號或名稱");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/function/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer fx_no = null;
				try {
					fx_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("權限編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/function/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FunctionService functionSvc = new FunctionService();
				FunctionVO functionVO = functionSvc.getOneFunction(fx_no);
				if (functionVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/function/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("functionVO", functionVO); // 資料庫取出的functionVO物件,存入req
				String url = "/back-end/function/listOneFunction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneFunction.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/function/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllfunction.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer fx_no = new Integer(req.getParameter("fx_no"));
				
				/***************************2.開始查詢資料****************************************/
				FunctionService functionSvc = new FunctionService();
				FunctionVO functionVO = functionSvc.getOneFunction(fx_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("functionVO", functionVO);         // 資料庫取出的functionVO物件,存入req
				String url = "/back-end/function/update_function_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_function_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/function/listAllFunction.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_function_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer fx_no = new Integer(req.getParameter("fx_no").trim());
				
				String fx_name = req.getParameter("fx_name");
				String fx_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,10}$";
				if (fx_name == null || fx_name.trim().length() == 0) {
					errorMsgs.add("權限名稱: 請勿空白");
				} else if(!fx_name.trim().matches(fx_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("權限名稱: 只能是中、英文字母和_ , 且長度必需在2到10之間");
	            }

				FunctionVO functionVO = new FunctionVO();
				functionVO.setFx_no(fx_no);
				functionVO.setFx_name(fx_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("functionVO", functionVO); // 含有輸入格式錯誤的functionVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/function/update_function_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				FunctionService functionSvc = new FunctionService();
				functionVO = functionSvc.updateFunction(fx_no, fx_name);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("functionVO", functionVO); // 資料庫update成功後,正確的的functionVO物件,存入req
				String url = "/back-end/function/listOneFunction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFunction.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/function/update_function_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addfunction.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String fx_name = req.getParameter("fx_name");
				String fx_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,10}$";
				if (fx_name == null || fx_name.trim().length() == 0) {
					errorMsgs.add("權限名稱: 請勿空白");
				} else if(!fx_name.trim().matches(fx_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("權限名稱: 只能是中、英文字母和_ , 且長度必需在2到10之間");
	            }

				FunctionVO functionVO = new FunctionVO();
				functionVO.setFx_name(fx_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("functionVO", functionVO); // 含有輸入格式錯誤的functionVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/function/addFunction.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				FunctionService functionSvc = new FunctionService();
				functionVO = functionSvc.addFunction(fx_name);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/function/listAllFunction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllfunction.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/function/addFunction.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllFunction.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer fx_no = new Integer(req.getParameter("fx_no"));
				
				/***************************2.開始刪除資料***************************************/
				FunctionService functionSvc = new FunctionService();
				functionSvc.deleteFunction(fx_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/function/listAllFunction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/function/listAllFunction.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
