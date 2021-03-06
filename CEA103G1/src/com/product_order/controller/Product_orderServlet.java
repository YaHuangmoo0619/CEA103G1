package com.product_order.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.place_order.model.Place_OrderService;
import com.place_order.model.Place_OrderVO;
import com.place_order_details.model.Place_Order_DetailsVO;
import com.product.model.ProductDAO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_order.model.*;
import com.product_order_details.model.*;

import redis.clients.jedis.Jedis;

@WebServlet("/product_order/product_order.do")
public class Product_orderServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
		String action = req.getParameter("action");
		MemberVO memberVO = (MemberVO) req.getSession().getAttribute("memberVO");
		
		
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("prod_ord_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品訂單編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer prod_ord_no = null;
				try {
					prod_ord_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("商品訂單編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				Product_orderVO product_orderVO = product_orderSvc.getOneProduct_order(prod_ord_no);
				if (product_orderVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_orderVO", product_orderVO); 
				String url = "/front-end/product_order/listOneProduct_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer prod_ord_no = new Integer(req.getParameter("prod_ord_no").trim());
				
				/***************************2.開始查詢資料****************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				Product_orderVO product_orderVO = product_orderSvc.getOneProduct_order(prod_ord_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("product_orderVO", product_orderVO);        
				String url = "/front-end/product_order/update_Product_order_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/listAllProduct_order.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer prod_ord_no = new Integer(req.getParameter("prod_ord_no").trim());
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				Timestamp prod_ord_time = java.sql.Timestamp.valueOf(req.getParameter("prod_ord_time").trim());
				Integer prod_ord_stat = new Integer(req.getParameter("prod_ord_stat").trim());
				Integer prod_ord_sum = new Integer(req.getParameter("prod_ord_sum").trim());
				Integer used_pt = new Integer(req.getParameter("used_pt").trim());
				Integer ship_meth = new Integer(req.getParameter("ship_meth").trim());
				Integer pay_meth = new Integer(req.getParameter("pay_meth").trim());
				String ship_cty = req.getParameter("ship_cty");
				String ship_dist = req.getParameter("ship_dist");
				String ship_add = req.getParameter("ship_add");
				Integer receipt = new Integer(req.getParameter("receipt").trim());
				String rmk = req.getParameter("rmk");
				
				Product_orderVO product_orderVO = new Product_orderVO();
				product_orderVO.setProd_ord_no(prod_ord_no);
				product_orderVO.setMbr_no(mbr_no);
				product_orderVO.setProd_ord_time(prod_ord_time);
				product_orderVO.setProd_ord_stat(prod_ord_stat);
				product_orderVO.setProd_ord_sum(prod_ord_sum);
				product_orderVO.setUsed_pt(used_pt);
				product_orderVO.setShip_meth(ship_meth);
				product_orderVO.setPay_meth(pay_meth);
				product_orderVO.setShip_cty(ship_cty);
				product_orderVO.setShip_dist(ship_dist);
				product_orderVO.setReceipt(receipt);
				product_orderVO.setRmk(rmk);
								
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_orderVO", product_orderVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/update_Product_order_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				product_orderVO = product_orderSvc.updateProduct_order(prod_ord_no, mbr_no, prod_ord_time, prod_ord_stat, prod_ord_sum, used_pt, ship_meth, pay_meth, ship_cty, ship_dist, ship_add, receipt, rmk);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_orderVO", product_orderVO);
				String url = "/front-end/product_order/listOneProduct_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/update_Product_order_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
//				Timestamp prod_ord_time = java.sql.Timestamp.valueOf(req.getParameter("prod_ord_time").trim());
				Integer prod_ord_stat = new Integer(req.getParameter("prod_ord_stat").trim());
				Integer prod_ord_sum = new Integer(req.getParameter("prod_ord_sum").trim());
				Integer used_pt = new Integer(req.getParameter("used_pt").trim());
				Integer ship_meth = new Integer(req.getParameter("ship_meth").trim());
				Integer pay_meth = new Integer(req.getParameter("pay_meth").trim());
				String ship_cty = req.getParameter("ship_cty");
				String ship_dist = req.getParameter("ship_dist");
				String ship_add = req.getParameter("ship_add");
				Integer receipt = new Integer(req.getParameter("receipt").trim());
				String rmk = req.getParameter("rmk");

				Product_orderVO product_orderVO = new Product_orderVO();
				product_orderVO.setMbr_no(mbr_no);
//				product_orderVO.setProd_ord_time(prod_ord_time);
				product_orderVO.setProd_ord_stat(prod_ord_stat);
				product_orderVO.setProd_ord_sum(prod_ord_sum);
				product_orderVO.setUsed_pt(used_pt);
				product_orderVO.setShip_meth(ship_meth);
				product_orderVO.setPay_meth(pay_meth);
				product_orderVO.setShip_cty(ship_cty);
				product_orderVO.setShip_dist(ship_dist);
				product_orderVO.setShip_dist(ship_add);
				product_orderVO.setReceipt(receipt);
				product_orderVO.setRmk(rmk);	
				
				List<Product_order_detailsVO> list = new ArrayList<Product_order_detailsVO>();
				Integer prod_no = new Integer(req.getParameter("prod_no"));
				Integer prod_amt = new Integer(req.getParameter("prod_amt"));
				Integer prod_unit_pc = new Integer(req.getParameter("prod_unit_pc"));
				Product_order_detailsVO product_order_detailsVO = new Product_order_detailsVO();
				product_order_detailsVO.setProd_no(prod_no);
				product_order_detailsVO.setProd_amt(prod_amt);
				product_order_detailsVO.setProd_unit_pc(prod_unit_pc);
				list.add(product_order_detailsVO);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_orderVO", product_orderVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/addProduct_order.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				product_orderVO = product_orderSvc.addProduct_order(mbr_no, prod_ord_stat, prod_ord_sum, used_pt, ship_meth, pay_meth, ship_cty, ship_dist, ship_add, receipt, rmk, list);

				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/product_order/listAllProduct_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct_order.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/product_order/addProduct_order.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer prod_ord_no = new Integer(req.getParameter("prod_ord_no").trim());
				
				/***************************2.開始刪除資料***************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				product_orderSvc.deleteProduct_order(prod_ord_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/product_order/listAllProduct_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/listAllProduct_order.jsp");
				failureView.forward(req, res);
			}
		}
		
		//雅凰加的
		if("read".equals(action)) {
			try {
				Product_orderService product_orderSvc = new Product_orderService();
				Product_orderVO product_orderVO = product_orderSvc.getOneProduct_order(Integer.valueOf(req.getParameter("prod_ord_no")));
				
				//Bootstrap_modal
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				req.setAttribute("product_orderVO", product_orderVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/product_order/listAllProduct_order_fromList.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_order/listAllProduct_order_fromList.jsp");
				failureView.forward(req, res);
			}
		}
		if("update_order_stat".equals(action)) {
			try {
				Integer prod_ord_stat = Integer.valueOf(req.getParameter("prod_ord_stat"));
//				System.out.println(prod_ord_stat);
				Integer prod_ord_no = Integer.valueOf(req.getParameter("prod_ord_no"));
//				System.out.println(prod_ord_no);
				
				Product_orderService product_orderSvc = new Product_orderService();
				product_orderSvc.update_order_stat(prod_ord_stat, prod_ord_no);
				Product_orderVO product_orderVO = product_orderSvc.getOneProduct_order(prod_ord_no);
				req.setAttribute("product_orderVO", product_orderVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/product_order/listAllProduct_order_fromList.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_order/listAllProduct_order_fromList.jsp");
				failureView.forward(req, res);
			}
			
		}
		//雅凰加的
		
		if ("getyMbr".equals(action)) { 
			
			Product_orderService product_orderSvc = new Product_orderService();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer prod_ord_no = new Integer(req.getParameter("prod_ord_no").trim());
				Integer mbr_no = new Integer(memberVO.getMbr_no());
				Timestamp prod_ord_time = java.sql.Timestamp.valueOf(req.getParameter("prod_ord_time").trim());
				Integer prod_ord_stat = new Integer(req.getParameter("prod_ord_stat").trim());
				Integer prod_ord_sum = new Integer(req.getParameter("prod_ord_sum").trim());
				Integer used_pt = new Integer(req.getParameter("used_pt").trim());
				Integer ship_meth = new Integer(req.getParameter("ship_meth").trim());
				Integer pay_meth = new Integer(req.getParameter("pay_meth").trim());
				String ship_cty = req.getParameter("ship_cty");
				String ship_dist = req.getParameter("ship_dist");
				String ship_add = req.getParameter("ship_add");
				Integer receipt = new Integer(req.getParameter("receipt").trim());
				String rmk = req.getParameter("rmk");
				
				Product_orderVO product_orderVO = new Product_orderVO();
				product_orderVO.setProd_ord_no(prod_ord_no);
				product_orderVO.setMbr_no(mbr_no);
				product_orderVO.setProd_ord_time(prod_ord_time);
				product_orderVO.setProd_ord_stat(prod_ord_stat);
				product_orderVO.setProd_ord_sum(prod_ord_sum);
				product_orderVO.setUsed_pt(used_pt);
				product_orderVO.setShip_meth(ship_meth);
				product_orderVO.setPay_meth(pay_meth);
				product_orderVO.setShip_cty(ship_cty);
				product_orderVO.setShip_dist(ship_dist);
				product_orderVO.setReceipt(receipt);
				product_orderVO.setRmk(rmk);
								
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_orderVO", product_orderVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/update_Product_order_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				product_orderVO = product_orderSvc.updateProduct_order(prod_ord_no, mbr_no, prod_ord_time, prod_ord_stat, prod_ord_sum, used_pt, ship_meth, pay_meth, ship_cty, ship_dist, ship_add, receipt, rmk);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_orderVO", product_orderVO);
				String url = "/front-end/product_order/listOneProduct_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/update_Product_order_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
<<<<<<< HEAD
		
        if ("insert_products".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
				System.out.println("mbr_no:"+mbr_no);
				Timestamp prod_ord_time =  new Timestamp(System.currentTimeMillis());
				Integer prod_ord_stat = 1;
				Integer prod_ord_sum = new Integer(req.getParameter("prod_ord_sum").trim());
				System.out.println("prod_ord_sum:"+prod_ord_sum);
				
				Integer used_pt = new Integer(req.getParameter("ptRadios").trim());
				//我的使用點數只分為兩種 要跟不要  要的話就取出這個會員現有的所有點數  不要的話就uesd_pt=0
				if(used_pt!=0) { //如果我有使用點數 要把這個資訊更新回memberVO
					MemberVO memberVO1 = new MemberVO();
					MemberService memberSvc = new MemberService();
					memberVO1 = memberSvc.getOneMember(mbr_no);
					int point_i_have = memberVO.getPt(); //取得我有的點數
				}
				System.out.println("used_pt:"+used_pt);
				
				Integer ship_meth = 2;
				Integer pay_meth = 0;
				String ship_cty = req.getParameter("county");
				System.out.println("ship_cty:"+ship_cty);
				String ship_dist = req.getParameter("district");
				System.out.println("ship_dist:"+ship_dist);
				String ship_add = req.getParameter("ship_add");
				System.out.println("ship_add:"+ship_add);
				Integer receipt = 0;
				String rmk = req.getParameter("rmk");
				System.out.println("rmk:"+rmk);
				
				List<Product_order_detailsVO> list = new ArrayList<>();//新建list準備放我的商品們
				Product_order_detailsVO product_order_detailVO = new Product_order_detailsVO();
				//開始從redis取出臨時訂單中的商品 並變成一個一個的VO
				
				Jedis jedis = new Jedis("localhost", 6379);
				jedis.auth("123456");
				jedis.select(4);
	
				
				Map<String,String> my_item_list = new HashMap<>();
				//取得臨時訂單的Map  key為prod_no  value為num
				for(String element : jedis.smembers("order:"+memberVO.getMbr_no()+":items")){
					String[]  strs=element.split(":");
					my_item_list.put(strs[0].toString(), strs[1].toString());
				}
				
				//至此已取得所有建立訂單需要的資訊
				
				jedis.close();
				
				Product_orderVO product_orderVO = new Product_orderVO();
				product_orderVO.setMbr_no(mbr_no);
				product_orderVO.setProd_ord_time(prod_ord_time);
				product_orderVO.setProd_ord_stat(prod_ord_stat);
				product_orderVO.setProd_ord_sum(prod_ord_sum);
				product_orderVO.setUsed_pt(used_pt);
				product_orderVO.setShip_meth(ship_meth);
				product_orderVO.setPay_meth(pay_meth);
				product_orderVO.setShip_cty(ship_cty);
				product_orderVO.setShip_dist(ship_dist);
				product_orderVO.setReceipt(receipt);
				product_orderVO.setRmk(rmk);	
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_orderVO", product_orderVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/addProduct_order.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				product_orderVO = product_orderSvc.addProduct_order(mbr_no, prod_ord_stat, prod_ord_sum, used_pt, ship_meth, pay_meth, ship_cty, ship_dist, ship_add, receipt, rmk, list);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/product_order/listAllProduct_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct_order.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/product_order/addProduct_order.jsp");
=======
		if("check".equals(action)) {
			try {
				Product_orderService product_orderSvc = new Product_orderService();
				Product_orderVO product_orderVO = product_orderSvc.getOneProduct_order(Integer.valueOf(req.getParameter("prod_ord_no")));
				
				//Bootstrap_modal
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				req.setAttribute("product_orderVO", product_orderVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/product_order/listAllProduct_order.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/listAllProduct_order.jsp");
>>>>>>> 0e627631ae4809f6e16a768a81d1fd3f70f47635
				failureView.forward(req, res);
			}
		}
		
<<<<<<< HEAD
=======
		if("cancel".equals(action)) {
			try {
				Integer prod_ord_no = Integer.valueOf(req.getParameter("prod_ord_no"));
				Product_orderService product_orderSvc = new Product_orderService();
				product_orderSvc.cancel(prod_ord_no);
				Product_orderVO product_orderVO = product_orderSvc.getOneProduct_order(prod_ord_no);
				req.setAttribute("product_orderVO", product_orderVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/product_order/listAllProduct_order.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product_order/listAllProduct_order.jsp");
				failureView.forward(req, res);
			}
			
		}
>>>>>>> 0e627631ae4809f6e16a768a81d1fd3f70f47635
	}
}
