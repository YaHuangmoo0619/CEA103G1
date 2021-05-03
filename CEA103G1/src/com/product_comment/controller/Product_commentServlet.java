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
		
		if ("getDate_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
   
			try {
				if (req.getParameter("cmnt_time").equals("0")) {
					errorMsgs.add("�п�ܵ��פ��");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date dateU = dsf.parse(req.getParameter("cmnt_time"));
				java.sql.Timestamp cmnt_time = new java.sql.Timestamp(dateU.getTime());
							
				Product_commentService product_commentSvc = new Product_commentService();
				List<Product_commentVO> product_commentVO_date = product_commentSvc.getTimestampProd_no(cmnt_time);
				if (product_commentVO_date.size() == 0) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				req.setAttribute("product_commentVO_date", product_commentVO_date);
				String url = "/back-end/product_comment/listDateProduct_comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listDateProduct_comment.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment/select_page.jsp");
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
				String str = req.getParameter("prod_cmnt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�ӫ~���׽s��");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				if(str.equals("0")) {
					errorMsgs.add("�п�ܰӫ~���׽s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer prod_cmnt_no = null;
				try {
					prod_cmnt_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�ӫ~���׽s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Product_commentService product_commentSvc = new Product_commentService();
				Product_commentVO product_commentVO = product_commentSvc.getOneProduct_comment(prod_cmnt_no);
				if (product_commentVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("product_commentVO", product_commentVO); // ��Ʈw���X��product_commentVO����,�s�Jreq
				String url = "/back-end/product_comment/listOneProduct_comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneProduct_comment.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllProduct_comment.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer prod_cmnt_no = new Integer(req.getParameter("prod_cmnt_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				Product_commentService product_commentSvc = new Product_commentService();
				Product_commentVO product_commentVO = product_commentSvc.getOneProduct_comment(prod_cmnt_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("product_commentVO", product_commentVO);         // ��Ʈw���X��product_commentVO����,�s�Jreq
				String url = "/back-end/product_comment/update_product_comment_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_product_comment_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment/listAllProduct_comment.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_product_comment_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer prod_cmnt_no = new Integer(req.getParameter("prod_cmnt_no"));
				Integer prod_no = new Integer(req.getParameter("prod_no"));
				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
				if(mbr_no == 0) {
					errorMsgs.add("�п�ܵo���");
				}							
				String cmnt_cont = req.getParameter("cmnt_cont");
				if(cmnt_cont == null || cmnt_cont.trim().isEmpty()) {
					errorMsgs.add("�м��g����");
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
					req.setAttribute("product_commentVO", product_commentVO); // �t����J�榡���~��product_commentVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment/update_product_comment_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Product_commentService product_commentSvc = new Product_commentService();
				product_commentVO = product_commentSvc.updateProduct_comment(prod_cmnt_no,prod_no, mbr_no, cmnt_cont, cmnt_time, cmnt_stat);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("product_commentVO", product_commentVO); // ��Ʈwupdate���\��,���T����product_commentVO����,�s�Jreq
				String url = "/back-end/product_comment/listOneProduct_comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneProduct_comment.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment/update_product_comment_input.jsp");
				failureView.forward(req, res);
			}
		}

		
        if ("insert".equals(action)) { // �Ӧ�addProduct_comment.jsp���ШD  
       	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no"));
				Integer prod_no = new Integer(req.getParameter("prod_no"));
				if(prod_no == 0) {
					errorMsgs.add("�п�ܰӫ~�s��");
				}							
				String cmnt_cont = req.getParameter("cmnt_cont");
				if(cmnt_cont == null || cmnt_cont.trim().isEmpty()) {
					errorMsgs.add("�м��g����");
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
				if (!errorMsgs.isEmpty()) { // �t����J�榡���~��product_commentVO����,�]�s�Jreq
					req.setAttribute("product_commentVO", product_commentVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_comment/addProduct_comment.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				Product_commentService product_commentSvc = new Product_commentService();
				product_commentVO = product_commentSvc.addProduct_comment(prod_no, mbr_no, cmnt_cont, cmnt_time, cmnt_stat);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back-end/product_comment/listAllProduct_comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllProduct_comment.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment/addProduct_comment.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllProduct_comment.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer prod_cmnt_no = new Integer(req.getParameter("prod_cmnt_no"));
				
				/***************************2.�}�l�R�����***************************************/
				Product_commentService product_commentSvc = new Product_commentService();
				product_commentSvc.deleteProduct_comment(prod_cmnt_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/product_comment/listAllProduct_comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_comment/listAllProduct_comment.jsp");
				failureView.forward(req, res);
			}
		}
	}
}