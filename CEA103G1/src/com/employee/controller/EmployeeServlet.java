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
		

		if ("getName_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
   
			try {
				String name = req.getParameter("name");
				if (name.equals("0")) {
					errorMsgs.add("�п�ܺ����޲z���m�W");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				EmployeeService employeeSvc = new EmployeeService();
				List<EmployeeVO> employeeVO_name = employeeSvc.getNameEmp_no(name);
				if (employeeVO_name.isEmpty()) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				req.setAttribute("employeeVO_name", employeeVO_name);
				String url = "/back-end/employee/listNameEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listDateAnnouncement.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getFunction_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
   
			try {
				Integer fx_no = new Integer(req.getParameter("fx_no"));
				if (fx_no == 0) {
					errorMsgs.add("�п���v���W��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				EmployeeService employeeSvc = new EmployeeService();
				List<EmployeeVO> employeeVO_fx_no = employeeSvc.getFunctionEmp_no(fx_no);
				if (employeeVO_fx_no.isEmpty()) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				req.setAttribute("employeeVO_fx_no", employeeVO_fx_no);
				String url = "/back-end/employee/listFunctionEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listDateAnnouncement.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/select_page.jsp");
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
				String str = req.getParameter("emp_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�����޲z���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				if (str.equals("0")) {
					errorMsgs.add("�п�ܺ����޲z���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer emp_no = null;
				try {
					emp_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�����޲z���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmployee(emp_no);
				if (employeeVO == null || employeeVO.getEmp_no() == 90001) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("employeeVO", employeeVO); // ��Ʈw���X��employeeVO����,�s�Jreq
				String url = "/back-end/employee/listOneEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmployee.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmployee.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer emp_no = new Integer(req.getParameter("emp_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmployee(emp_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("employeeVO", employeeVO);         // ��Ʈw���X��employeeVO����,�s�Jreq
				String url = "/back-end/employee/update_employee_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_employee_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/listAllEmployee.jsp");
				failureView.forward(req, res);
			}
		}
				
		
		if ("update".equals(action) || "�K�X��s".equals(change)) { // �Ӧ�update_employee_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer emp_no = new Integer(req.getParameter("emp_no"));
				
				String accPart = req.getParameter("acc");
				String accReg = "^[(0-9)]{5,5}$";
				if(accPart == null ||accPart.trim().isEmpty()) {
					errorMsgs.add("�b��: �п�J�b��");
				}else if(!accPart.trim().matches(accReg)) {
					errorMsgs.add("�b��: �п�J5�ӼƦr");
				}
				String acc = "CEA" + accPart;
				
				String pwdOrigin = "pwd";
				if("�K�X��s".equals(change)) {
					req.setAttribute("sendingEmail", "toSend");
					String pwdNew = genAuthCode();
					req.setAttribute("code", pwdNew);
					pwdOrigin = pwdNew;
				}
				if("update".equals(action)){
					pwdOrigin = req.getParameter("pwd");
				}
				//pwdOrigin²����X�A�ǳƦs�i��Ʈw
			    String pwd = changePwdforDatabase(pwdOrigin);
				
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,20}$";
				if(name == null || name.trim().isEmpty()) {
					errorMsgs.add("�m�W: �п�J�m�W");
				}else if(!name.trim().matches(nameReg)) {
					errorMsgs.add("�m�W: �u��O���B�^��r���M_ , �B���ץ��ݦb2��10����");
				}
				
				String email = req.getParameter("email");
				if(email == null ||email.trim().isEmpty()) {
					errorMsgs.add("EMAIL: �п�JEMAIL");
				}else if(!email.trim().contains("@")) {
					errorMsgs.add("EMAIL: �Цb�q�l�l�󤤥]�t@, " + email +"�å��]�t@");
				}else if(email.trim().contains("@") && email.trim().length() == 1) {
					errorMsgs.add("EMAIL: �п�J@�e���M�᭱������, " + email +"���O�����");
				}else if(email.trim().indexOf("@") == 0) {
					errorMsgs.add("EMAIL: �п�J@�e��������, " + email +"���O�����");
				}else if(email.trim().lastIndexOf("@") == email.trim().length()-1) {
					errorMsgs.add("EMAIL: �п�J@�᭱������, " + email +"���O�����");
				}
				
				Integer emp_stat = null;
				try{
				emp_stat = new Integer(req.getParameter("emp_stat"));
				}catch(NumberFormatException ne) {
					errorMsgs.add("�b¾���p: �п�ܦb¾���p");
				}
				
				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setEmp_no(emp_no);
				employeeVO.setAcc(acc);
				employeeVO.setPwd(pwd);
				employeeVO.setName(name);
				employeeVO.setEmail(email);
				employeeVO.setEmp_stat(emp_stat);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty() || "�K�X��s".equals(change)) {
					req.setAttribute("employeeVO", employeeVO); // �t����J�榡���~��employeeVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/update_employee_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				if(("sendingEmail").equals(sendingEmail)) {
					//�K�X�H�X�ܿ�J��email
					String to = email;
					String subject = "�K�X�q��";
					String messageText = name + ", �z�n, �z���b���� "+acc+"�C �z��s���K�X�� " + pwdOrigin + "�C ���԰O���K�X�C\n" +" (�w�g�ҥ�)";
					String result = sendMail(to, subject, messageText);
					if(result.equals("fail")) {
					    errorMsgs.add("EMAIL�o�e���ѡA���pô�u�{�v�B�z");
					}			    
					    
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) { // �t����J�榡���~��employeeVO����,�]�s�Jreq
						req.setAttribute("employeeVO", employeeVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/employee/addEmployee.jsp");
						failureView.forward(req, res);
						return;
					}
			}
	
				/***************************2.�}�l�ק���*****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeVO = employeeSvc.updateEmployee(emp_no, acc, pwd, name, email, emp_stat);
		
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("employeeVO", employeeVO); // ��Ʈwupdate���\��,���T����employeeVO����,�s�Jreq
				String url = "/back-end/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmployee.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/update_employee_input.jsp");
				failureView.forward(req, res);
			}
		}

		
        if ("insert".equals(action)) { // �Ӧ�addEmployee.jsp���ШD  
       	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/							
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,20}$";
				if(name == null || name.trim().isEmpty()) {
					errorMsgs.add("�m�W: �п�J�m�W");
				}else if(!name.trim().matches(nameReg)) {
					errorMsgs.add("�m�W: �u��O���B�^��r���M_ , �B���ץ��ݦb2��10����");
				}
				
				String accPart = req.getParameter("acc");
				String accReg = "^[(0-9)]{5,5}$";
				if(accPart == null ||accPart.trim().isEmpty()) {
					errorMsgs.add("�b��: �п�J�b��");
				}else if(!accPart.trim().matches(accReg)) {
					errorMsgs.add("�b��: �п�J5�ӼƦr");
				}
				String acc = "CEA" + accPart;	
				
				String email = req.getParameter("email");
				if(email == null ||email.trim().isEmpty()) {
					errorMsgs.add("EMAIL: �п�JEMAIL");
				}else if(!email.trim().contains("@")) {
					errorMsgs.add("EMAIL: �Цb�q�l�l�󤤥]�t@, " + email +"�å��]�t@");
				}else if(email.trim().contains("@") && email.trim().length() == 1) {
					errorMsgs.add("EMAIL: �п�J@�e���M�᭱������, " + email +"���O�����");
				}else if(email.trim().indexOf("@") == 0) {
					errorMsgs.add("EMAIL: �п�J@�e��������, " + email +"���O�����");
				}else if(email.trim().lastIndexOf("@") == email.trim().length()-1) {
					errorMsgs.add("EMAIL: �п�J@�᭱������, " + email +"���O�����");
				}				
				
				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setAcc(acc);
				employeeVO.setPwd("pwd");
				employeeVO.setName(name);
				employeeVO.setEmail(email);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) { // �t����J�榡���~��employeeVO����,�]�s�Jreq
					req.setAttribute("employeeVO", employeeVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/addEmployee.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//�T�{��Ƴ����T����o�K�X
				String pwdOrigin = genAuthCode();
				
				//�K�X�H�X�ܿ�J��email
				String to = email;
			    String subject = "�K�X�q��";
			    String messageText = name + ", �z�n, �z���b���� "+acc+"�C �z���K�X�� " + pwdOrigin + "�C ���԰O���K�X�C\n" +" (�w�g�ҥ�)";
			    String result = sendMail(to, subject, messageText);
			    if(result.equals("fail")) {
			    	errorMsgs.add("EMAIL�o�e���ѡA���pô�u�{�v�B�z");
			    }			    
			    
			    // Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) { // �t����J�榡���~��employeeVO����,�]�s�Jreq
					req.setAttribute("employeeVO", employeeVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/addEmployee.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//pwdOrigin²����X�A�ǳƦs�i��Ʈw
			    String pwd = changePwdforDatabase(pwdOrigin);
			    
				/***************************2.�}�l�s�W���***************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeVO = employeeSvc.addEmployee(acc, pwd, name, email);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				List<EmployeeVO> employeeVONew = employeeSvc.getAll();
				Integer emp_no = employeeVONew.get(employeeVONew.size() - 1).getEmp_no();
				req.setAttribute("emp_no", emp_no);
				String url = "/back-end/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmployee.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/addEmployee.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmployee.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer emp_no = new Integer(req.getParameter("emp_no"));
				
				/***************************2.�}�l�R�����***************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeSvc.deleteEmployee(emp_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/listAllEmployee.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	public static String genAuthCode() {
//		�C�X�n�h�����Ÿ��A�Ÿ��������Ʀr�Ӧ�Unicode��A�NUnicode��16�i��Ʀr��10�i�줧��A�ϥ�
		int[] check = { 58, 59, 60, 61, 62, 63, 64, 91, 92, 93, 94, 95, 96 };
//		�إ߭n��J�ýX���r���}�C
		char[] code = new char[8];
//		���o�üơA���ۤ��O�_���n�׶}���Ÿ��A�Ҥ��O���ܦA��J�}�C�A�O���ܴN�����@���üơA�æA�����A���ưʧ@����}�C�񺡬���
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
//		�ˬd�O�_�P�ɾ֦��^��r���j�p�g�μƦr�A�S�������H����@�Ӧ�m����
//		�ˬd�Ʀr
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
//		�ˬd�^��r���j�g
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
//		�קK��W�����P�˪���m
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
//		�ˬd�^��p�g�r��
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
//		�קK��W�����P�˪���m
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
//		��String���󤤪�valueOf(char[])��k�A�N�r���}�C�ର�r��æ^��
		return String.valueOf(code);

	}
	
	//�i��ܥ��T���K�X
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
	
	//�i²����ʱN�n�Ƕi��Ʈw���K�X
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
	
	// �]�w�ǰe�l��:�ܦ��H�H��Email�H�c,Email�D��,Email���e

	public String sendMail(String to, String subject, String messageText) {

		try {
			// �]�w�ϥ�SSL�s�u�� Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// ���]�w gmail ���b�� & �K�X (�N�ǥѧA��Gmail�ӶǰeEmail)
			// �����NmyGmail���i�w���ʸ��C�����ε{���s���v�j���}
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

			// �]�w�H�����D��
			message.setSubject(subject);
			// �]�w�H�������e
			message.setText(messageText);

			Transport.send(message);
			System.out.println("�ǰe���\!");
			String ok = "ok";
			return ok;
		} catch (MessagingException e) {
			System.out.println("�ǰe����!");
			String fail = "fail";
			e.printStackTrace();
			return fail;
		}
	}
}