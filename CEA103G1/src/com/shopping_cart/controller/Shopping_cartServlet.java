package com.shopping_cart.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.RepaintManager;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

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
		
	
		if ("add_collection".equals(action)) { // 為某篇人新增一個購物車商品

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer prod_no = null;
			prod_no = new Integer(req.getParameter("prod_no").trim());
			System.out.println("要加入購物車的商品編號:"+prod_no);
			Integer mbr_no = null;
			mbr_no = new Integer(req.getParameter("mbr_no").trim());
			System.out.println("會員號碼:"+mbr_no);
			Integer prod_amt= null;
			prod_amt = new Integer(req.getParameter("prod_amt").trim());
			System.out.println("要加入購物車的數量"+prod_amt);

			
			boolean check = false; //設一個變數為check 檢視要加入購物車的商品是否原先就存在於購物車中
			int num_origin = 0; //原先的數量
			Shopping_cartService shopping_cartSvc = new Shopping_cartService();
			List<Shopping_cartVO> shopping_cart_now = shopping_cartSvc.getByMbr_no(mbr_no); //先查出這個人目前購物車有的清單

			
			for (Shopping_cartVO count : shopping_cart_now) {
				if(count.getProd_no().intValue()==prod_no.intValue()) { //如果這個人目前的購物車清單中  與現在要加入的商品編號重複
				num_origin = count.getProd_amt();
				System.out.println("原先的數量"+count.getProd_amt());
				check = true; //將check 設為 true 	
				}
			}
			System.out.println("check:"+check);
			/*************************** 2.開始新增資料 ***************************************/
			if(check==true) {
				shopping_cartSvc.updateShopping_cart(mbr_no, prod_no, prod_amt+num_origin); //更新資料 數量為原先的數量+現在要加入的數量
			}
			if(check==false) {
				Shopping_cartVO shopping_cartVO = new Shopping_cartVO();
				shopping_cartVO.setMbr_no(mbr_no);
				shopping_cartVO.setProd_no(prod_no);
				shopping_cartVO.setProd_amt(prod_amt);
				shopping_cartVO = shopping_cartSvc.addShopping_cart(mbr_no, prod_no, prod_amt);
			}
			

		} //end of add_collection
		
		
		
		
		
		if ("cancel_collection".equals(action)) { // 為某篇人刪除一個購物車商品

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer prod_no = null;
			prod_no = new Integer(req.getParameter("prod_no").trim());

			Integer mbr_no = null;
			mbr_no = new Integer(req.getParameter("mbr_no").trim());

			/*************************** 2.開始新增資料 ***************************************/
			Shopping_cartService shopping_cartSvc = new Shopping_cartService();
			shopping_cartSvc.deleteShopping_cart(mbr_no, prod_no);


		} //end of minus_collection
		
		
		
		
		if ("getMemberShoppingCart".equals(action)) { // 列出某會員購物車有哪些商品
			
			
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			
				String str = req.getParameter("mbr_no");
				Integer mbr_no = null;
				mbr_no = new Integer(str);


				/*************************** 2.開始查詢資料 *****************************************/
				Shopping_cartService shopping_cartSvc = new Shopping_cartService();
				List<Shopping_cartVO> shopping_cartVO = shopping_cartSvc.getByMbr_no(mbr_no); //查到這個人購物車的所有商品
				
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("shopping_cartVO", shopping_cartVO); // 資料庫取出的article_collectionVO物件,存入req
				String url = "/front-end/shopping_cart/listOneMember_Shopping_cart.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember_Shopping_cart.jsp
				successView.forward(req, res);
			}   //end of getMemberShoppingCart
		
		
		
		if ("generate".equals(action)) { // 為某人新增一筆臨時訂單

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer mbr_no = new Integer(req.getParameter("mbr_no").trim());
			Integer prod_no = new Integer(req.getParameter("prod_no").trim());
			Integer num = new Integer(req.getParameter("num").trim());
			String  prod_noAndNum = req.getParameter("prod_no").trim()+":"+req.getParameter("num").trim();
			//例如 ~ 平底鍋:1
			
			
			Jedis jedis = new Jedis("localhost", 6379);
			jedis.auth("123456");
			jedis.select(4); //4:臨時訂單專用
			
			jedis.sadd("order:"+req.getParameter("mbr_no").trim()+":items", prod_noAndNum);
			//會變成  key >>  order:10001:items    value = 平底鍋:1  、  帳篷:2   ....等等

			
			/*************************** 2.開始新增資料 ***************************************/
			jedis.close();
		} //end of minus_collection
	}
}
