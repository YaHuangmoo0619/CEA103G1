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
		
		if ("getDate_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
   
			try {
				if (req.getParameter("rpt_time").equals("0")) {
					errorMsgs.add("�п�����|���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment_report/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date dateU = dsf.parse(req.getParameter("rpt_time"));
				java.sql.Timestamp rpt_time = new java.sql.Timestamp(dateU.getTime());
							
				Product_comment_reportService product_comment_reportSvc = new Product_comment_reportService();
				List<Product_comment_reportVO> product_comment_reportVO_date = product_comment_reportSvc.getTimestampProd_cmnt_no(rpt_time);
				if (product_comment_reportVO_date.size() == 0) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment_report/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				req.setAttribute("product_comment_reportVO_date", product_comment_reportVO_date);
				String url = "/back-end/product_comment_report/listDateProduct_comment_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listDateProduct_comment_report.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment_report/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("prod_cmnt_rpt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�ӫ~�������|�s��");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment_report/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				if(str.equals("0")) {
					errorMsgs.add("�п�ܰӫ~�������|�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment_report/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer prod_cmnt_rpt_no = null;
				try {
					prod_cmnt_rpt_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�ӫ~�������|�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment_report/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Product_comment_reportService product_comment_reportSvc = new Product_comment_reportService();
				Product_comment_reportVO product_comment_reportVO = product_comment_reportSvc.getOneProduct_comment_report(prod_cmnt_rpt_no);
				if (product_comment_reportVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment_report/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("product_comment_reportVO", product_comment_reportVO); // ��Ʈw���X��product_comment_reportVO����,�s�Jreq
				String url = "/back-end/product_comment_report/listOneProduct_comment_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneProduct_comment_report.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment_report/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllProduct_comment_report.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer prod_cmnt_rpt_no = new Integer(req.getParameter("prod_cmnt_rpt_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				Product_comment_reportService product_comment_reportSvc = new Product_comment_reportService();
				Product_comment_reportVO product_comment_reportVO = product_comment_reportSvc.getOneProduct_comment_report(prod_cmnt_rpt_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("product_comment_reportVO", product_comment_reportVO);         // ��Ʈw���X��product_comment_reportVO����,�s�Jreq
				String url = "/back-end/product_comment_report/update_product_comment_report_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_product_comment_report_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment_report/listAllProduct_comment_report.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_product_comment_report_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer prod_cmnt_rpt_no = new Integer(req.getParameter("prod_cmnt_rpt_no"));
				Integer prod_cmnt_no = new Integer(req.getParameter("prod_cmnt_no"));
				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
				if(mbr_no == 0) {
					errorMsgs.add("�п�ܵo���");
				}							
				String rpt_cont = req.getParameter("rpt_cont");
				if(rpt_cont == null || rpt_cont.trim().isEmpty()) {
					errorMsgs.add("�м��g���|���e");
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
					req.setAttribute("product_comment_reportVO", product_comment_reportVO); // �t����J�榡���~��product_comment_reportVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment_report/update_product_comment_report_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Product_comment_reportService product_comment_reportSvc = new Product_comment_reportService();
				product_comment_reportVO = product_comment_reportSvc.updateProduct_comment_report(prod_cmnt_rpt_no, mbr_no, prod_cmnt_no, rpt_cont, rpt_time, proc_stat);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("product_comment_reportVO", product_comment_reportVO); // ��Ʈwupdate���\��,���T����product_comment_reportVO����,�s�Jreq
				String url = "/back-end/product_comment_report/listOneProduct_comment_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneProduct_comment_report.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment_report/update_product_comment_report_input.jsp");
				failureView.forward(req, res);
			}
		}

		
        if ("insert".equals(action)) { // �Ӧ�addProduct_comment_report.jsp���ШD  
       	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
				Integer prod_cmnt_no = new Integer(req.getParameter("prod_no"));
				if(prod_cmnt_no == 0) {
					errorMsgs.add("�п�ܵ��׽s��");
				}							
				String rpt_cont = req.getParameter("rpt_cont");
				if(rpt_cont == null || rpt_cont.trim().isEmpty()) {
					errorMsgs.add("�м��g����");
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
				if (!errorMsgs.isEmpty()) { // �t����J�榡���~��product_comment_reportVO����,�]�s�Jreq
					req.setAttribute("product_comment_reportVO", product_comment_reportVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment_report/addProduct_comment_report.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				Product_comment_reportService product_comment_reportSvc = new Product_comment_reportService();
				product_comment_reportVO = product_comment_reportSvc.addProduct_comment_report(prod_cmnt_no, mbr_no, rpt_cont, rpt_time, proc_stat);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back-end/product_comment_report/listAllProduct_comment_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllProduct_comment_report.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment_report/addProduct_comment_report.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllProduct_comment_report.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer prod_cmnt_rpt_no = new Integer(req.getParameter("prod_cmnt_rpt_no"));
				
				/***************************2.�}�l�R�����***************************************/
				Product_comment_reportService product_comment_reportSvc = new Product_comment_reportService();
				product_comment_reportSvc.deleteProduct_comment_report(prod_cmnt_rpt_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/product_comment_report/listAllProduct_comment_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment_report/listAllProduct_comment_report.jsp");
				failureView.forward(req, res);
			}
		}
	}
}