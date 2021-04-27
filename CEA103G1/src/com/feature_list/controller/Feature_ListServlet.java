package com.feature_list.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.feature_list.model.Feature_ListService;
import com.feature_list.model.Feature_ListVO;

public class Feature_ListServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("camp_fl_name");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入特色名稱");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/feature_list/listAllFeature_list.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Feature_ListService feature_listSvc = new Feature_ListService();
				List<Feature_ListVO> list = feature_listSvc.getOneFeature_List(str);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/feature_list/listAllFeature_list.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("feature_listVO", list); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/feature_list/listOneFeature_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/feature_list/listAllFeature_list.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String camp_fl_name = req.getParameter("camp_fl_name");
				String camp_fl_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (camp_fl_name == null || camp_fl_name.trim().length() == 0) {
					errorMsgs.add("特色名稱: 請勿空白");
				} else if (!camp_fl_name.trim().matches(camp_fl_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("特色名稱: 只能是中、英文字母、數字和_ , 且長度必需在10之內");
				}
				Feature_ListService feature_listSvc = new Feature_ListService();
				List<Feature_ListVO> feature_list = feature_listSvc.getAll();
				for (Object i : feature_list) {
					if (camp_fl_name.matches(((Feature_ListVO) i).getCamp_fl_name().trim())) {
						errorMsgs.add("已有相同名稱之特色");
					}
				}
				Feature_ListVO feature_listVO = new Feature_ListVO();
				feature_listVO.setCamp_fl_name(camp_fl_name);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("feature_listVO", feature_listVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/feature_list/listAllFeature_list.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				feature_listVO = feature_listSvc.addFeature_List(camp_fl_name);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/feature_list/listAllFeature_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/feature_list/listAllFeature_list.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer camp_fl_no = new Integer(req.getParameter("camp_fl_no"));
				
				/***************************2.開始查詢資料****************************************/
				Feature_ListService feature_listSvc = new Feature_ListService();
				Feature_ListVO feature_listVO = feature_listSvc.getOneFeature_List(camp_fl_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("feature_listVO", feature_listVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/feature_list/updateFeature_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/feature_list/listAllFeature_list.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer camp_fl_no = new Integer(req.getParameter("camp_fl_no").trim());
				String camp_fl_name = req.getParameter("camp_fl_name");
				String camp_fl_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				
				if (camp_fl_name == null || camp_fl_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!camp_fl_name.trim().matches(camp_fl_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在10之內");
				}
				Feature_ListService feature_listSvc = new Feature_ListService();
				List<Feature_ListVO> feature_list = feature_listSvc.getAll();
				for (Object i : feature_list) {
					if (camp_fl_name.matches(((Feature_ListVO) i).getCamp_fl_name().trim())) {
						errorMsgs.add("已有相同名稱之特色");
					}
				}
				Feature_ListVO feature_listVO = new Feature_ListVO();
				feature_listVO.setCamp_fl_no(camp_fl_no);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("feature_listVO", feature_listVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/feature_list/updateFeature_list.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				feature_listVO = feature_listSvc.updateFeature_List(camp_fl_no, camp_fl_name);
				List<Feature_ListVO> list = new ArrayList();
				list.add(feature_listVO);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("feature_listVO", list); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/feature_list/listOneFeature_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/feature_list/updateFeature_list.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer camp_fl_no = new Integer(req.getParameter("camp_fl_no"));
				
				/***************************2.開始刪除資料***************************************/
				Feature_ListService feature_listSvc = new Feature_ListService();
				feature_listSvc.deleteFeature_List(camp_fl_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/feature_list/listAllFeature_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/feature_list/listAllFeature_list.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
