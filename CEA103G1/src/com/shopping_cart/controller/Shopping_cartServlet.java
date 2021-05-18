package com.shopping_cart.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping_cart.model.Shopping_cartService;
import com.shopping_cart.model.Shopping_cartVO;


@WebServlet("/shopping_cart/shopping_cart.do")
public class Shopping_cartServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	
		if ("add_collection".equals(action)) { // ���Y�g�H�s�W�@���ʪ����ӫ~

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			Integer prod_no = null;
			prod_no = new Integer(req.getParameter("prod_no").trim());
			System.out.println("�n�[�J�ʪ������ӫ~�s��:"+prod_no);
			Integer mbr_no = null;
			mbr_no = new Integer(req.getParameter("mbr_no").trim());
			System.out.println("�|�����X:"+mbr_no);
			Integer prod_amt= null;
			prod_amt = new Integer(req.getParameter("prod_amt").trim());
			System.out.println("�n�[�J�ʪ������ƶq"+prod_amt);
			Shopping_cartVO shopping_cartVO = new Shopping_cartVO();
			shopping_cartVO.setMbr_no(mbr_no);
			shopping_cartVO.setProd_no(prod_no);
			shopping_cartVO.setProd_amt(prod_amt);

			/*************************** 2.�}�l�s�W��� ***************************************/
			Shopping_cartService shopping_cartSvc = new Shopping_cartService();
			shopping_cartVO = shopping_cartSvc.addShopping_cart(mbr_no, prod_no, prod_amt);

		} //end of add_collection
		
		
		
		
		
		if ("cancel_collection".equals(action)) { // ���Y�g�H�R���@���ʪ����ӫ~

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			Integer prod_no = null;
			prod_no = new Integer(req.getParameter("prod_no").trim());

			Integer mbr_no = null;
			mbr_no = new Integer(req.getParameter("mbr_no").trim());

			/*************************** 2.�}�l�s�W��� ***************************************/
			Shopping_cartService shopping_cartSvc = new Shopping_cartService();
			shopping_cartSvc.deleteShopping_cart(mbr_no, prod_no);


		} //end of minus_collection
		
		
		
		
		if ("getMemberShoppingCart".equals(action)) { // �C�X�Y�|���ʪ��������ǰӫ~
			
			
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
			
				String str = req.getParameter("mbr_no");
				Integer mbr_no = null;
				mbr_no = new Integer(str);


				/*************************** 2.�}�l�d�߸�� *****************************************/
				Shopping_cartService shopping_cartSvc = new Shopping_cartService();
				List<Shopping_cartVO> shopping_cartVO = shopping_cartSvc.getByMbr_no(mbr_no); //�d��o�ӤH�ʪ������Ҧ��ӫ~
				
				
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("shopping_cartVO", shopping_cartVO); // ��Ʈw���X��article_collectionVO����,�s�Jreq
				String url = "/front-end/shopping_cart/listOneMember_Shopping_cart.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneMember_Shopping_cart.jsp
				successView.forward(req, res);
			}   //end of getMemberShoppingCart
		
		
		
	}
}
