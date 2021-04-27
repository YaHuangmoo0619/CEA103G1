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
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("fx_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�v���s��");
				}
				if(str.equals("0")) {
					errorMsgs.add("�п���v���s���ΦW��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/function/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer fx_no = null;
				try {
					fx_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�v���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/function/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				FunctionService functionSvc = new FunctionService();
				FunctionVO functionVO = functionSvc.getOneFunction(fx_no);
				if (functionVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/function/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("functionVO", functionVO); // ��Ʈw���X��functionVO����,�s�Jreq
				String url = "/back-end/function/listOneFunction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneFunction.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/function/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllfunction.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer fx_no = new Integer(req.getParameter("fx_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				FunctionService functionSvc = new FunctionService();
				FunctionVO functionVO = functionSvc.getOneFunction(fx_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("functionVO", functionVO);         // ��Ʈw���X��functionVO����,�s�Jreq
				String url = "/back-end/function/update_function_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_function_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/function/listAllFunction.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_function_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer fx_no = new Integer(req.getParameter("fx_no").trim());
				
				String fx_name = req.getParameter("fx_name");
				String fx_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,10}$";
				if (fx_name == null || fx_name.trim().length() == 0) {
					errorMsgs.add("�v���W��: �ФŪť�");
				} else if(!fx_name.trim().matches(fx_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�v���W��: �u��O���B�^��r���M_ , �B���ץ��ݦb2��10����");
	            }

				FunctionVO functionVO = new FunctionVO();
				functionVO.setFx_no(fx_no);
				functionVO.setFx_name(fx_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("functionVO", functionVO); // �t����J�榡���~��functionVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/function/update_function_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				FunctionService functionSvc = new FunctionService();
				functionVO = functionSvc.updateFunction(fx_no, fx_name);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("functionVO", functionVO); // ��Ʈwupdate���\��,���T����functionVO����,�s�Jreq
				String url = "/back-end/function/listOneFunction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneFunction.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/function/update_function_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addfunction.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String fx_name = req.getParameter("fx_name");
				String fx_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,10}$";
				if (fx_name == null || fx_name.trim().length() == 0) {
					errorMsgs.add("�v���W��: �ФŪť�");
				} else if(!fx_name.trim().matches(fx_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�v���W��: �u��O���B�^��r���M_ , �B���ץ��ݦb2��10����");
	            }

				FunctionVO functionVO = new FunctionVO();
				functionVO.setFx_name(fx_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("functionVO", functionVO); // �t����J�榡���~��functionVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/function/addFunction.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				FunctionService functionSvc = new FunctionService();
				functionVO = functionSvc.addFunction(fx_name);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back-end/function/listAllFunction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllfunction.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/function/addFunction.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllFunction.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer fx_no = new Integer(req.getParameter("fx_no"));
				
				/***************************2.�}�l�R�����***************************************/
				FunctionService functionSvc = new FunctionService();
				functionSvc.deleteFunction(fx_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/function/listAllFunction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/function/listAllFunction.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
