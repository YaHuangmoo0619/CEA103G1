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

import org.json.JSONArray;

import net.sf.json.JSONObject;

import com.shopping_cart.model.Shopping_cartService;
import com.shopping_cart.model.Shopping_cartVO;


@WebServlet("/shopping_cart/shopping_cartTest.do")
public class Shopping_cartTest extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			res.setContentType("application/json;charset=utf-8");
			Integer mbr_no = null;
			mbr_no = new Integer(req.getParameter("mbr_no").trim());
			System.out.println(mbr_no);
			
			String jsonStr = "";
			jsonStr = req.getParameter("map");
		

			
			/*************************** 2.開始新增資料 ***************************************/


		} //end of minus_collection
	}

