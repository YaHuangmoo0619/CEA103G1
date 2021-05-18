package com.campsite_owner.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.campsite_owner.model.Campsite_ownerService;
import com.campsite_owner.model.Campsite_ownerVO;

@MultipartConfig
@WebServlet("/campsite_owner/campsite_owner.do")
public class Campsite_ownerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("cso_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廣告編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/campsite_owner/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer cso_no = null;
				try {
					cso_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("廣告編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/campsite_owner/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Campsite_ownerService campsite_ownerSvc = new Campsite_ownerService();
				Campsite_ownerVO campsite_ownerVO = campsite_ownerSvc.getOneCampsite_owner(cso_no);
				if (campsite_ownerVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/campsite_owner/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("campsite_ownerVO", campsite_ownerVO); // 資料庫取出的member_rankVO物件,存入req
				String url = "/front-end/campsite_owner/listOnecampsite_owner.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnecampsite_owner.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite_owner/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllcampsite_owner.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer cso_no = new Integer(req.getParameter("cso_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				Campsite_ownerService campsite_ownerSvc = new Campsite_ownerService();
				Campsite_ownerVO campsite_ownerVO = campsite_ownerSvc.getOneCampsite_owner(cso_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("campsite_ownerVO", campsite_ownerVO); // 資料庫取出的campsite_ownerVO物件,存入req
				String url = "/front-end/campsite_owner/update_campsite_owner_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_campsite_owner_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/campsite_owner/listAllcampsite_owner.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addMember_rank.jsp的請求
			Part part = req.getPart("sticker");
			InputStream in = part.getInputStream();
			byte[] sticker = new byte[in.available()];
			in.read(sticker);
			in.close();
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String acc = req.getParameter("acc");
				Campsite_ownerService campsite_ownerSvc = new Campsite_ownerService();
				List<Campsite_ownerVO> csolist = campsite_ownerSvc.getAll();
				if (acc == null || acc.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}
				for (Campsite_ownerVO csoVO : csolist) {
					if (acc.equals(csoVO.getAcc())) {
						errorMsgs.add("此帳號已有人使用");
					}
				}
				String pwd = req.getParameter("pwd");
				if (pwd == null || pwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				String id = req.getParameter("id");
				if (id == null || id.trim().length() == 0) {
					errorMsgs.add("身分證:請勿空白");
				}
				if (!checkId(id)) {
					errorMsgs.add("身分證:格式錯誤");
				}
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,60}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("姓名: 請勿空白");
				} else if (!name.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("姓名: 只能是中、英文letter");
				}

				java.sql.Date bday = java.sql.Date.valueOf(req.getParameter("bday").trim());
				int sex = new Integer(req.getParameter("sex"));
				int mobile = new Integer(req.getParameter("mobile"));
				String mail = req.getParameter("mail");
				if (mail == null || mail.trim().length() == 0) {
					errorMsgs.add("信箱: 請勿空白");
				}
				String city = req.getParameter("county");
				String dist = req.getParameter("district");
				String add = req.getParameter("address");
				if (add == null || add.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				}

				int bank_no = new Integer(req.getParameter("bank_no"));
				String bank_acc = req.getParameter("bank_acc");
				Campsite_ownerVO campsite_ownerVO = new Campsite_ownerVO();
				campsite_ownerVO.setAcc(acc);
				campsite_ownerVO.setPwd(pwd);
				campsite_ownerVO.setId(id);
				campsite_ownerVO.setName(name);
				campsite_ownerVO.setBday(bday);
				campsite_ownerVO.setSex(sex);
				campsite_ownerVO.setMobile(mobile);
				campsite_ownerVO.setMail(mail);
				campsite_ownerVO.setCity(city);
				campsite_ownerVO.setDist(dist);
				campsite_ownerVO.setAdd(add);
				campsite_ownerVO.setBank_no(bank_no);
				campsite_ownerVO.setBank_acc(bank_acc);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("campsite_ownerVO", campsite_ownerVO); // 含有輸入格式錯誤的member_rankVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/campsite_owner/addCampsite_owner.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				campsite_ownerVO = campsite_ownerSvc.addCampsite_owner(acc, pwd, id, name, bday, sex, mobile, mail,
						city, dist, add, sticker, bank_no, bank_acc);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/campsite_owner/listAllcampsite_owner.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember_rank.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/campsite_owner/addCampsite_owner.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllMember_rank.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer cso_no = new Integer(req.getParameter("cso_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				Campsite_ownerService campsite_ownerSvc = new Campsite_ownerService();
				campsite_ownerSvc.deleteCampsite_owner(cso_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/campsite_owner/listAllcampsite_owner.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/campsite_owner/listAllcampsite_owner.jsp");
				failureView.forward(req, res);
			}
		}
	}

	public Boolean checkId(String id) {
		Boolean checkid = true;
		int[] reference = { 10, 11, 12, 13, 14, 15, 16, 17, 34, 18, 19, 20, 21, 22, 35, 23, 24, 25, 26, 27, 28, 29, 30,
				31, 32, 33, 34 };
		int length = id.length();
		id = id.toUpperCase();
		if (!Character.isLetter(id.charAt(0))) {
			checkid = false;
		}
		if (length != 10) {
			checkid = false;
		}
		if (id.charAt(1) != '1' && id.charAt(1) != '2') {
			checkid = false;
		}
		char num;
		int sum = 0, number, letter;
		letter = reference[id.charAt(0) - 65];
		sum = (letter / 10) + ((letter % 10) * 9);

		for (int i = 1; i <= length - 2; ++i) {
			num = id.charAt(i);
			number = num - 48;
			sum += number * (9 - i);
		}

		num = id.charAt(9);
		number = num - 48;

		if (10 != (sum % 10) + number) {
			checkid = false;
		}
		return checkid;
	}
}
