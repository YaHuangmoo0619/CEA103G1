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

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("camp_fl_name");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�S��W��");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/feature_list/listAllFeature_list.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				Feature_ListService feature_listSvc = new Feature_ListService();
				List<Feature_ListVO> list = feature_listSvc.getOneFeature_List(str);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/feature_list/listAllFeature_list.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("feature_listVO", list); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/feature_list/listOneFeature_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/feature_list/listAllFeature_list.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String camp_fl_name = req.getParameter("camp_fl_name");
				String camp_fl_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (camp_fl_name == null || camp_fl_name.trim().length() == 0) {
					errorMsgs.add("�S��W��: �ФŪť�");
				} else if (!camp_fl_name.trim().matches(camp_fl_nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�S��W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb10����");
				}
				Feature_ListService feature_listSvc = new Feature_ListService();
				List<Feature_ListVO> feature_list = feature_listSvc.getAll();
				for (Object i : feature_list) {
					if (camp_fl_name.matches(((Feature_ListVO) i).getCamp_fl_name().trim())) {
						errorMsgs.add("�w���ۦP�W�٤��S��");
					}
				}
				Feature_ListVO feature_listVO = new Feature_ListVO();
				feature_listVO.setCamp_fl_name(camp_fl_name);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("feature_listVO", feature_listVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/feature_list/listAllFeature_list.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				feature_listVO = feature_listSvc.addFeature_List(camp_fl_name);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/back-end/feature_list/listAllFeature_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/feature_list/listAllFeature_list.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer camp_fl_no = new Integer(req.getParameter("camp_fl_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				Feature_ListService feature_listSvc = new Feature_ListService();
				Feature_ListVO feature_listVO = feature_listSvc.getOneFeature_List(camp_fl_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("feature_listVO", feature_listVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/feature_list/updateFeature_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/feature_list/listAllFeature_list.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer camp_fl_no = new Integer(req.getParameter("camp_fl_no").trim());
				String camp_fl_name = req.getParameter("camp_fl_name");
				String camp_fl_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				
				if (camp_fl_name == null || camp_fl_name.trim().length() == 0) {
					errorMsgs.add("���u�m�W: �ФŪť�");
				} else if (!camp_fl_name.trim().matches(camp_fl_nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb10����");
				}
				Feature_ListService feature_listSvc = new Feature_ListService();
				List<Feature_ListVO> feature_list = feature_listSvc.getAll();
				for (Object i : feature_list) {
					if (camp_fl_name.matches(((Feature_ListVO) i).getCamp_fl_name().trim())) {
						errorMsgs.add("�w���ۦP�W�٤��S��");
					}
				}
				Feature_ListVO feature_listVO = new Feature_ListVO();
				feature_listVO.setCamp_fl_no(camp_fl_no);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("feature_listVO", feature_listVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/feature_list/updateFeature_list.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				feature_listVO = feature_listSvc.updateFeature_List(camp_fl_no, camp_fl_name);
				List<Feature_ListVO> list = new ArrayList();
				list.add(feature_listVO);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("feature_listVO", list); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/feature_list/listOneFeature_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/feature_list/updateFeature_list.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer camp_fl_no = new Integer(req.getParameter("camp_fl_no"));
				
				/***************************2.�}�l�R�����***************************************/
				Feature_ListService feature_listSvc = new Feature_ListService();
				feature_listSvc.deleteFeature_List(camp_fl_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/feature_list/listAllFeature_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/feature_list/listAllFeature_list.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
