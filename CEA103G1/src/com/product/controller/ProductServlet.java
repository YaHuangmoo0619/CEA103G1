package com.product.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.product.model.*;


@WebServlet("/product/product.do")
public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("prod_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�ӫ~�s��");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer prod_no = null;
				try {
					prod_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�ӫ~�s���榡�����T");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(prod_no);
				if (productVO == null) {
					errorMsgs.add("�d�L���");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("productVO", productVO); 
				String url = "/front-end/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer prod_no = new Integer(req.getParameter("prod_no").trim());
				
				/***************************2.�}�l�d�߸��****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(prod_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("productVO", productVO);        
				String url = "/back-end/product/update_Product_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
			System.out.println("in1");
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();

			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer prod_no = new Integer(req.getParameter("prod_no").trim());
				
				Integer prod_cat_no = null;
				try {
					prod_cat_no = new Integer(req.getParameter("prod_cat_no").trim());
				} catch (NumberFormatException e) {
					prod_cat_no = 0;
					errorMsgs.put("prod_cat_no","�п�ܰӫ~���O");
				}
				System.out.println("middle");
				Integer prod_stat = null;
				try {
					prod_stat = new Integer(req.getParameter("prod_stat").trim());
				} catch (NumberFormatException e) {
					prod_stat = 0;
					errorMsgs.put("prod_stat","�п�ܰӫ~���A");
				}
				
				String prod_name = req.getParameter("prod_name");
				if (prod_name == null || prod_name.trim().length() == 0) {
					errorMsgs.put("prod_name","�п�J�ӫ~�W��");
				}	
				
				Integer prod_pc = null;
				try {
					prod_pc = new Integer(req.getParameter("prod_pc").trim());
					if (prod_pc <= 0) {
						errorMsgs.put("prod_pc","�ӫ~����ݤj��s");
					}
				} catch (NumberFormatException e) {
					prod_pc = 0;
					errorMsgs.put("prod_pc","�п�J�ӫ~����");
				}
				
				Integer prod_stg = null;
				try {
					prod_stg = new Integer(req.getParameter("prod_stg").trim());
					if (prod_stg <= 0) {
						errorMsgs.put("prod_stg","�ӫ~�w�s�ݤj��s");
					}
				} catch (NumberFormatException e) {
					prod_stg = 0;
					errorMsgs.put("prod_stg","�п�J�ӫ~�w�s");
				}
				
				String prod_info = req.getParameter("prod_info");
				if (prod_info == null || prod_info.trim().length() == 0) {
					errorMsgs.put("prod_info","�п�J�ӫ~��T");
				}
				String prod_bnd = req.getParameter("prod_bnd");
				if (prod_bnd == null || prod_bnd.trim().length() == 0) {
					errorMsgs.put("prod_bnd","�п�J�ӫ~�t�P");
				}
				String prod_clr = req.getParameter("prod_clr");
				if (prod_clr == null || prod_clr.trim().length() == 0) {
					errorMsgs.put("prod_clr","�п�J�ӫ~�C��");
				}
				String prod_size = req.getParameter("prod_size");
				if (prod_size == null || prod_size.trim().length() == 0) {
					errorMsgs.put("prod_size","�п�J�ӫ~�j�p");
				}
				
				Integer ship_meth = null;
				try {
					ship_meth = new Integer(req.getParameter("ship_meth").trim());
				} catch (NumberFormatException e) {
					ship_meth = 0;
					errorMsgs.put("ship_meth","�п�ܰӫ~�B�e�覡");
				}
				System.out.println("through");
				ProductVO productVO = new ProductVO();
				productVO.setProd_no(prod_no);
				productVO.setProd_cat_no(prod_cat_no);
				productVO.setProd_stat(prod_stat);
				productVO.setProd_name(prod_name);
				productVO.setProd_pc(prod_pc);
				productVO.setProd_stg(prod_stg);
				productVO.setProd_info(prod_info);
				productVO.setProd_bnd(prod_bnd);
				productVO.setProd_clr(prod_clr);
				productVO.setProd_size(prod_size);
				productVO.setShip_meth(ship_meth);
				System.out.println(prod_no+","+ prod_cat_no+","+prod_stat+","+ prod_name+","+ prod_pc+","+prod_stg+","+ prod_info+","+prod_bnd+","+ prod_clr+","+prod_size+","+ship_meth);
				if (!errorMsgs.isEmpty()) {
					System.out.println("error-f");
					req.setAttribute("productVO", productVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/update_Product_input.jsp");
					failureView.forward(req, res);
					return;
				}
				System.out.println("here?");
				/***************************2.�}�l�ק���*****************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProduct(prod_no, prod_cat_no, prod_stat, prod_name, prod_pc, prod_stg, prod_info, prod_bnd, prod_clr, prod_size, ship_meth);
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("productVO", productVO);
				String url = "/back-end/product/listAllProduct_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				System.out.println("error-e");
				errorMsgs.put("error","�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/update_Product_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				Integer prod_cat_no = new Integer(req.getParameter("prod_cat_no").trim());
//				if (prod_cat_no == null ) {
//					errorMsgs.add("�п�ܰӫ~���O�I");
//				}	
				
				Integer prod_stat = new Integer(req.getParameter("prod_stat"));
				
				String prod_name = req.getParameter("prod_name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (prod_name == null || prod_name.trim().length() == 0) {
					errorMsgs.add("�п�J�ӫ~�W�١I");
				} else if (!prod_name.trim().matches(nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ӫ~�W�٥u��O����Ʀr");
				}
								
				Integer prod_pc = null;
				try {
					prod_pc = new Integer(req.getParameter("prod_pc").trim());
				} catch (NumberFormatException e) {
					prod_pc = 0;
					errorMsgs.add("�ӫ~����ж�Ʀr�I");
				}
				
				Integer prod_stg = new Integer(req.getParameter("prod_stg").trim());
				try {
					prod_pc = new Integer(req.getParameter("prod_pc").trim());
				} catch (NumberFormatException e) {
					prod_pc = 0;
					errorMsgs.add("�ӫ~�w�s�ж�Ʀr�I");
				}
				
				String prod_info = req.getParameter("prod_info");
				String prod_bnd = req.getParameter("prod_bnd");
				String prod_clr = req.getParameter("prod_clr");
				String prod_size = req.getParameter("prod_size");
				
				Integer ship_meth = new Integer(req.getParameter("ship_meth").trim());
//				if (ship_meth == null ) {
//				errorMsgs.add("�п�ܹB�e�覡�I");
//			}	
				ProductVO productVO = new ProductVO();
				productVO.setProd_cat_no(prod_cat_no);
				productVO.setProd_stat(prod_stat);
				productVO.setProd_name(prod_name);
				productVO.setProd_pc(prod_pc);
				productVO.setProd_stg(prod_stg);
				productVO.setProd_info(prod_info);
				productVO.setProd_bnd(prod_bnd);
				productVO.setProd_clr(prod_clr);
				productVO.setProd_size(prod_size);
				productVO.setShip_meth(ship_meth);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.addProduct(prod_cat_no, prod_stat, prod_name, prod_pc, prod_stg, prod_info, prod_bnd, prod_clr, prod_size, ship_meth);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllProduct.jsp
				successView.forward(req, res);	
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/product/addProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer prod_no = new Integer(req.getParameter("prod_no").trim());
				
				/***************************2.�}�l�R�����***************************************/
				ProductService productSvc = new ProductService();
				productSvc.deleteProduct(prod_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-end/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listProduct_ByCQ".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.�N��J����ରMap**********************************/ 
				//�ĥ�Map<String,String[]> getParameterMap()����k 
				//�`�N:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				
				// �H�U�� if �϶��u��Ĥ@������ɦ���
				if (req.getParameter("whichPage") == null){
					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.�}�l�ƦX�d��***************************************/
				ProductService productSvc = new ProductService();
				List<ProductVO> list  = productSvc.getAll(map);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("listProduct_ByCQ", list); // ��Ʈw���X��list����,�s�Jrequest
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/listProduct_ByCQ.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("buyOne".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer prod_no = new Integer(req.getParameter("prod_no"));
				/***************************2.�}�l�d�߸��*****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(prod_no);
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("productVO", productVO); 
				String url = "/front-end/product/listOneProduct_List.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		//���ĥ[��
		if("�W�[".equals(action)) {
			try {
				if(req.getParameterValues("prod_no").length > 0 ) {
					String[] prod_nos = req.getParameterValues("prod_no");
					for(String prod_noString : prod_nos) {
						Integer prod_no = Integer.valueOf(prod_noString);
						ProductDAO productDAO = new ProductDAO();
						productDAO.updateUpOrDown(1, prod_no);
					}
					RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
					successView.forward(req, res);
				}else {
					RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
					successView.forward(req, res);
				}
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
			
		}
		if("�U�[".equals(action)) {
			try {
				if(req.getParameterValues("prod_no").length > 0 ) {
					String[] prod_nos = req.getParameterValues("prod_no");
					for(String prod_noString : prod_nos) {
						Integer prod_no = Integer.valueOf(prod_noString);
						ProductDAO productDAO = new ProductDAO();
						productDAO.updateUpOrDown(0, prod_no);
					}
					RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
					successView.forward(req, res);
				}else {
					RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
					successView.forward(req, res);
				}
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
			
		}
		if("read".equals(action)) {
			try {
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(Integer.valueOf(req.getParameter("prod_no")));
				
				//Bootstrap_modal
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				req.setAttribute("productVO", productVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/listAllProduct_update.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/listAllProduct_update.jsp");
				failureView.forward(req, res);
			}
		}
		//���ĥ[��
	}
	
}
