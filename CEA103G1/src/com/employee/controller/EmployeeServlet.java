package com.employee.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.json.*;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;



@WebServlet("/employee/employee.do")
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String change = req.getParameter("change");
		String sendingEmail = req.getParameter("sendingEmail");
		

		if ("getName_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
   
			try {
				String name = req.getParameter("name");
				if (name.equals("0")) {
					errorMsgs.add("請選擇網站管理員姓名");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				EmployeeService employeeSvc = new EmployeeService();
				List<EmployeeVO> employeeVO_name = employeeSvc.getNameEmp_no(name);
				if (employeeVO_name.isEmpty()) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				req.setAttribute("employeeVO_name", employeeVO_name);
				String url = "/back-end/employee/listNameEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listDateAnnouncement.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getFunction_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
   
			try {
				Integer fx_no = new Integer(req.getParameter("fx_no"));
				if (fx_no == 0) {
					errorMsgs.add("請選擇權限名稱");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				EmployeeService employeeSvc = new EmployeeService();
				List<EmployeeVO> employeeVO_fx_no = employeeSvc.getFunctionEmp_no(fx_no);
				if (employeeVO_fx_no.isEmpty()) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				req.setAttribute("employeeVO_fx_no", employeeVO_fx_no);
				String url = "/back-end/employee/listFunctionEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listDateAnnouncement.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("emp_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入網站管理員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				if (str.equals("0")) {
					errorMsgs.add("請選擇網站管理員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer emp_no = null;
				try {
					emp_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("網站管理員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmployee(emp_no);
				if (employeeVO == null || employeeVO.getEmp_no() == 90001) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("employeeVO", employeeVO); // 資料庫取出的employeeVO物件,存入req
				String url = "/back-end/employee/listOneEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmployee.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmployee.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer emp_no = new Integer(req.getParameter("emp_no"));
				
				/***************************2.開始查詢資料****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmployee(emp_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("employeeVO", employeeVO);         // 資料庫取出的employeeVO物件,存入req
				String url = "/back-end/employee/update_employee_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_employee_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/listAllEmployee.jsp");
				failureView.forward(req, res);
			}
		}
				
		
		if ("update".equals(action) || "密碼更新".equals(change)) { // 來自update_employee_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer emp_no = new Integer(req.getParameter("emp_no"));
				
				String accPart = req.getParameter("acc");
				String accReg = "^[(0-9)]{5,5}$";
				if(accPart == null ||accPart.trim().isEmpty()) {
					errorMsgs.add("帳號: 請輸入帳號");
				}else if(!accPart.trim().matches(accReg)) {
					errorMsgs.add("帳號: 請輸入5個數字");
				}
				String acc = "CEA" + accPart;
				
				String pwdOrigin = "pwd";
				if("密碼更新".equals(change)) {
					req.setAttribute("sendingEmail", "toSend");
					String pwdNew = genAuthCode();
					req.setAttribute("code", pwdNew);
					pwdOrigin = pwdNew;
				}
				if("update".equals(action)){
					pwdOrigin = req.getParameter("pwd");
				}
				//pwdOrigin簡易轉碼，準備存進資料庫
			    String pwd = changePwdforDatabase(pwdOrigin);
				
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,20}$";
				if(name == null || name.trim().isEmpty()) {
					errorMsgs.add("姓名: 請輸入姓名");
				}else if(!name.trim().matches(nameReg)) {
					errorMsgs.add("姓名: 只能是中、英文字母和_ , 且長度必需在2到10之間");
				}
				
				String email = req.getParameter("email");
				if(email == null ||email.trim().isEmpty()) {
					errorMsgs.add("EMAIL: 請輸入EMAIL");
				}else if(!email.trim().contains("@")) {
					errorMsgs.add("EMAIL: 請在電子郵件中包含@, " + email +"並未包含@");
				}else if(email.trim().contains("@") && email.trim().length() == 1) {
					errorMsgs.add("EMAIL: 請輸入@前面和後面的部分, " + email +"不是完整值");
				}else if(email.trim().indexOf("@") == 0) {
					errorMsgs.add("EMAIL: 請輸入@前面的部分, " + email +"不是完整值");
				}else if(email.trim().lastIndexOf("@") == email.trim().length()-1) {
					errorMsgs.add("EMAIL: 請輸入@後面的部分, " + email +"不是完整值");
				}
				
				Integer emp_stat = null;
				try{
				emp_stat = new Integer(req.getParameter("emp_stat"));
				}catch(NumberFormatException ne) {
					errorMsgs.add("在職狀況: 請選擇在職狀況");
				}
				
				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setEmp_no(emp_no);
				employeeVO.setAcc(acc);
				employeeVO.setPwd(pwd);
				employeeVO.setName(name);
				employeeVO.setEmail(email);
				employeeVO.setEmp_stat(emp_stat);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty() || "密碼更新".equals(change)) {
					req.setAttribute("employeeVO", employeeVO); // 含有輸入格式錯誤的employeeVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/update_employee_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				if(("sendingEmail").equals(sendingEmail)) {
					//密碼寄出至輸入的email
					String to = email;
					String subject = "密碼通知";
					String messageText = name + ", 您好, 您的帳號為 "+acc+"。 您更新的密碼為 " + pwdOrigin + "。 請謹記此密碼。\n" +" (已經啟用)";
					String result = sendMail(to, subject, messageText);
					if(result.equals("fail")) {
					    errorMsgs.add("EMAIL發送失敗，請聯繫工程師處理");
					}			    
					    
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) { // 含有輸入格式錯誤的employeeVO物件,也存入req
						req.setAttribute("employeeVO", employeeVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/employee/addEmployee.jsp");
						failureView.forward(req, res);
						return;
					}
			}
	
				/***************************2.開始修改資料*****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeVO = employeeSvc.updateEmployee(emp_no, acc, pwd, name, email, emp_stat);
		
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("employeeVO", employeeVO); // 資料庫update成功後,正確的的employeeVO物件,存入req
				String url = "/back-end/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmployee.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/update_employee_input.jsp");
				failureView.forward(req, res);
			}
		}

		
        if ("insert".equals(action)) { // 來自addEmployee.jsp的請求  
       	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/							
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,20}$";
				if(name == null || name.trim().isEmpty()) {
					errorMsgs.add("姓名: 請輸入姓名");
				}else if(!name.trim().matches(nameReg)) {
					errorMsgs.add("姓名: 只能是中、英文字母和_ , 且長度必需在2到10之間");
				}
				
				String accPart = req.getParameter("acc");
				String accReg = "^[(0-9)]{5,5}$";
				if(accPart == null ||accPart.trim().isEmpty()) {
					errorMsgs.add("帳號: 請輸入帳號");
				}else if(!accPart.trim().matches(accReg)) {
					errorMsgs.add("帳號: 請輸入5個數字");
				}
				String acc = "CEA" + accPart;	
				
				String email = req.getParameter("email");
				if(email == null ||email.trim().isEmpty()) {
					errorMsgs.add("EMAIL: 請輸入EMAIL");
				}else if(!email.trim().contains("@")) {
					errorMsgs.add("EMAIL: 請在電子郵件中包含@, " + email +"並未包含@");
				}else if(email.trim().contains("@") && email.trim().length() == 1) {
					errorMsgs.add("EMAIL: 請輸入@前面和後面的部分, " + email +"不是完整值");
				}else if(email.trim().indexOf("@") == 0) {
					errorMsgs.add("EMAIL: 請輸入@前面的部分, " + email +"不是完整值");
				}else if(email.trim().lastIndexOf("@") == email.trim().length()-1) {
					errorMsgs.add("EMAIL: 請輸入@後面的部分, " + email +"不是完整值");
				}				
				
				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setAcc(acc);
				employeeVO.setPwd("pwd");
				employeeVO.setName(name);
				employeeVO.setEmail(email);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) { // 含有輸入格式錯誤的employeeVO物件,也存入req
					req.setAttribute("employeeVO", employeeVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/addEmployee.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//確認資料都正確後取得密碼
				String pwdOrigin = genAuthCode();
				
				//密碼寄出至輸入的email
				String to = email;
			    String subject = "密碼通知";
			    String messageText = name + ", 您好, 您的帳號為 "+acc+"。 您的密碼為 " + pwdOrigin + "。 請謹記此密碼。\n" +" (已經啟用)";
			    String result = sendMail(to, subject, messageText);
			    if(result.equals("fail")) {
			    	errorMsgs.add("EMAIL發送失敗，請聯繫工程師處理");
			    }			    
			    
			    // Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) { // 含有輸入格式錯誤的employeeVO物件,也存入req
					req.setAttribute("employeeVO", employeeVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/addEmployee.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//pwdOrigin簡易轉碼，準備存進資料庫
			    String pwd = changePwdforDatabase(pwdOrigin);
			    
				/***************************2.開始新增資料***************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeVO = employeeSvc.addEmployee(acc, pwd, name, email);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				List<EmployeeVO> employeeVONew = employeeSvc.getAll();
				Integer emp_no = employeeVONew.get(employeeVONew.size() - 1).getEmp_no();
				req.setAttribute("emp_no", emp_no);
				String url = "/back-end/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmployee.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/addEmployee.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmployee.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer emp_no = new Integer(req.getParameter("emp_no"));
				
				/***************************2.開始刪除資料***************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeSvc.deleteEmployee(emp_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/listAllEmployee.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	public static String genAuthCode() {
//		列出要去掉的符號，符號對應的數字來自Unicode表，將Unicode表中16進位數字轉10進位之後再使用
		int[] check = { 58, 59, 60, 61, 62, 63, 64, 91, 92, 93, 94, 95, 96 };
//		建立要放入亂碼的字元陣列
		char[] code = new char[8];
//		取得亂數，接著比對是否為要避開的符號，皆不是的話再放入陣列，是的話就重取一次亂數，並再次比對，重複動作直到陣列放滿為止
		for (int i = 0; i < 8; i++) {
			int num = (int) ((Math.random() * 75) + 48);
			for (int j = 0; j < check.length; j++) {
				if (num == check[j]) {
					num = (int) ((Math.random() * 75) + 48);
					j = -1;
				}
			}
			code[i] = (char) num;
		}
//		檢查是否同時擁有英文字母大小寫及數字，沒有的話隨機抽一個位置替換
//		檢查數字
		int countNum = 0;
		out: for (int i = 0; i < 8; i++) {
			for (int j = 48; j < 58; j++) {
				if ((int) code[i] != j) {
					countNum += 1;
				} else {
					break out;
				}
			}

		}
		int a1 = (int) ((Math.random() * 8));
		if (countNum == 80) {
			code[a1] = (char) ((int) ((Math.random() * 10) + 48));
		}
//		檢查英文字母大寫
		int countUpper = 0;
		out: for (int i = 0; i < 8; i++) {
			for (int j = 65; j < 91; j++) {
				if ((int) code[i] != j) {
					countUpper += 1;
				} else {
					break out;
				}
			}
		}
//		避免跟上面改到同樣的位置
		int a2 = (int) ((Math.random() * 8));
		while (true) {
			if (a2 == a1) {
				a2 = (int) ((Math.random() * 8));
			} else {
				break;
			}
		}
		if (countUpper == 208) {
			code[a2] = (char) ((int) ((Math.random() * 26) + 65));
		}
//		檢查英文小寫字母
		int countLower = 0;
		out: for (int i = 0; i < 8; i++) {
			for (int j = 97; j < 123; j++) {
				if ((int) code[i] != j) {
					countLower += 1;
				} else {
					break out;
				}
			}
		}
//		避免跟上面改到同樣的位置
		int a3 = (int) ((Math.random() * 8));
		while (true) {
			if (a3 == a1 || a3 == a2) {
				a3 = (int) ((Math.random() * 8));
			} else {
				break;
			}
		}
		if (countLower == 208) {
			code[a3] = (char) ((int) ((Math.random() * 26) + 97));
		}
//		用String物件中的valueOf(char[])方法，將字元陣列轉為字串並回傳
		return String.valueOf(code);

	}
	
	//可顯示正確的密碼
	public String showPwdformDatabase(String newPwd) {
	char[] forShow = newPwd.toCharArray();
	char[] gobackCode = new char[newPwd.length()];
	for(int i = 0 ; i < newPwd.length(); i++) {
		if(i < 2) {
			gobackCode[i + ((newPwd.length()-2))] = forShow[i]; 
		}else {
			gobackCode[i -2] = forShow[i];
		}
	}
	String showPwd = String.valueOf(gobackCode);
	return showPwd;
	}
	
	//可簡易更動將要傳進資料庫的密碼
	public String changePwdforDatabase(String originPwd) {
		char[] forCode = originPwd.toCharArray();
		char[] newCode = new char[originPwd.length()];
		
		for(int i = 0 ; i < originPwd.length(); i++) {
			if(i < (originPwd.length()-2)) {
				newCode[i + 2] = forCode[i]; 
			}else {
				newCode[i - (originPwd.length()-2)] = forCode[i];
			}
		}
		String newPwd = String.valueOf(newCode);
		return newPwd;
	}
	
	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容

	public String sendMail(String to, String subject, String messageText) {

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
			// ●須將myGmail的【安全性較低的應用程式存取權】打開
			final String myGmail = "ixlogic.wu@gmail.com";
			final String myGmail_password = "CCC45678CCC";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
			// 設定信中的內容
			message.setText(messageText);

			Transport.send(message);
			System.out.println("傳送成功!");
			String ok = "ok";
			return ok;
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			String fail = "fail";
			e.printStackTrace();
			return fail;
		}
	}
}