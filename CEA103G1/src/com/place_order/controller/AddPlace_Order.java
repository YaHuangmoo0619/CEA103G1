package com.place_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.member.model.*;
import com.place_order.model.Place_OrderService;
import com.place_order.model.Place_OrderVO;
import com.place_order_details.model.Place_Order_DetailsService;
import com.place_order_details.model.Place_Order_DetailsVO;

@WebServlet("/place_order/Place_order.do")
public class AddPlace_Order extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
//		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		Gson gson = new Gson();

		String action = req.getParameter("action");
		Place_OrderService place_orderSvc = new Place_OrderService();
		Place_OrderVO place_orderVO = new Place_OrderVO();
		if (!action.equals("confirm")) {

			Integer mbr_no = new Integer(req.getParameter("member"));
			System.out.println("會員編號:" + mbr_no);
			Integer camp_no = new Integer(req.getParameter("camp"));
			System.out.println("營區編號:" + camp_no);
			java.sql.Date ckin_date = java.sql.Date.valueOf(req.getParameter("startdate"));
			System.out.println("入住日期:" + ckin_date);
			java.sql.Date ckout_date = java.sql.Date.valueOf(req.getParameter("enddate"));
			System.out.println("退房日期:" + ckout_date);
			String place = req.getParameter("place");
			System.out.println("營位:" + place);
			Integer plc_ord_sum = new Integer(req.getParameter("total"));
			System.out.println("總金額:" + plc_ord_sum);
			Integer ex_ppl = new Integer(req.getParameter("ex_ppl"));
			System.out.println("加購人數:" + ex_ppl);
			Integer deposit = new Integer(req.getParameter("deposit"));
			System.out.println("是否全額:" + deposit);
			Integer point = new Integer(req.getParameter("point"));
			System.out.println("是否點數:" + point);
			Integer pay_meth = new Integer(req.getParameter("pay_meth"));
			System.out.println("付款方式:" + pay_meth);
			Integer receipt = new Integer(req.getParameter("receipt"));
			System.out.println("發票方式:" + receipt);
			String rmk = req.getParameter("rmk");
			System.out.println("備註:" + rmk);
			String[] tempplace = place.split(",");
			Integer[] chosenplace = new Integer[tempplace.length];
			int pay_stat;
			int used_pt;
			if (point == 0) {
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mbr_no);
				used_pt = memberVO.getPt();
				if (used_pt > plc_ord_sum) {
					used_pt = plc_ord_sum;
				}
			} else {
				used_pt = 0;
			}
			System.out.println("使用點數:" + used_pt);
			if (pay_meth == 0 && deposit == 1) {
				pay_stat = 1;
			} else if (pay_meth == 0 && deposit == 0) {
				pay_stat = 2;
			} else {
				pay_stat = 0;
			}
			System.out.println("付款狀態:" + pay_stat);
			for (int i = 0; i < tempplace.length; i++) {
				int plc_no = new Integer(tempplace[i]);
				chosenplace[i] = plc_no;
			}
			Integer plc_amt = chosenplace.length;
			System.out.println("營位數量:" + plc_amt);
			List<Place_Order_DetailsVO> list = new ArrayList();
			for (int i = 0; i < chosenplace.length; i++) {
				Place_Order_DetailsVO detailsVO = new Place_Order_DetailsVO();
				detailsVO.setPlc_no(chosenplace[i]);
				list.add(detailsVO);
			}

			if ("creditcard".equals(action)) {
				place_orderVO.setMbr_no(mbr_no);
				place_orderVO.setCamp_no(camp_no);
				place_orderVO.setCkin_date(ckin_date);
				place_orderVO.setCkout_date(ckout_date);
				place_orderVO.setPlc_amt(plc_amt);
				place_orderVO.setPlc_ord_sum(plc_ord_sum);
				place_orderVO.setEx_ppl(ex_ppl);
				place_orderVO.setPay_meth(pay_meth);
				place_orderVO.setPay_stat(pay_stat);
				place_orderVO.setUsed_pt(used_pt);
				place_orderVO.setReceipt(receipt);
				place_orderVO.setRmk(rmk);
				session.setAttribute("new_place_order", place_orderVO);
				session.setAttribute("new_place_order_details", list);

				String jsonObject = gson.toJson(place_orderVO);
				out.println(jsonObject);
			} else {
				List<Object> payWithTransfer = new ArrayList();
				MemberVO member = (MemberVO) session.getAttribute("member");
				String id = member.getId();
				String atm = 99227 + id.substring(1);

				place_orderVO = place_orderSvc.addPlace_Order(mbr_no, camp_no, ckin_date, ckout_date, plc_amt,
						plc_ord_sum, ex_ppl, pay_meth, pay_stat, used_pt, receipt, rmk, list);
				
				place_orderVO = place_orderSvc.getOnePlace_Order(place_orderVO.getPlc_ord_no());
				payWithTransfer.add(place_orderVO);
				payWithTransfer.add(atm);
				String jsonObject = gson.toJson(payWithTransfer);
				out.println(jsonObject);
			}
		} else {
			place_orderVO = (Place_OrderVO) session.getAttribute("new_place_order");
			List list = (List) session.getAttribute("new_place_order_details");
			int mbr_no = place_orderVO.getMbr_no();
			int camp_no = place_orderVO.getCamp_no();
			Date ckin_date = place_orderVO.getCkin_date();
			Date ckout_date = place_orderVO.getCkout_date();
			int plc_amt = place_orderVO.getPlc_amt();
			int plc_ord_sum = place_orderVO.getPlc_ord_sum();
			int ex_ppl = place_orderVO.getEx_ppl();
			int pay_meth = place_orderVO.getPay_meth();
			int pay_stat = place_orderVO.getPay_stat();
			int used_pt = place_orderVO.getUsed_pt();
			int receipt = place_orderVO.getReceipt();
			String rmk = place_orderVO.getRmk();

			String number = req.getParameter("number");
			String name = req.getParameter("name");
			String expiry = req.getParameter("expiry");
			int cvc = new Integer(req.getParameter("cvc"));
			System.out.println("卡號:" + number);
			System.out.println("姓名:" + name);
			System.out.println("到期日:" + expiry);
			System.out.println("安全碼:" + cvc);

			place_orderVO = place_orderSvc.addPlace_Order(mbr_no, camp_no, ckin_date, ckout_date, plc_amt, plc_ord_sum,
					ex_ppl, pay_meth, pay_stat, used_pt, receipt, rmk, list);

			res.sendRedirect(req.getContextPath() + "/front-end/place_order/finish.html");
		}
	}
}
