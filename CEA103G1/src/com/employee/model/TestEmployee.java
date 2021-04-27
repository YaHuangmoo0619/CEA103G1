package com.employee.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.model.EmployeeDAO;

@WebServlet("/TestEmployee")
public class TestEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		EmployeeDAO employeeDAO = new EmployeeDAO();

		out.print("<HTML>");
		out.print("<HEAD><TITLE>TestEmployee</TITLE><STYLE>tr{border:solid 1px black;}</STYLE></HEAD>");
		out.print("<BODY>");

//		//testInsert
//		String acc = "CEA" + 90019;
//		String code = genAuthCode();
//		EmployeeVO employeeVO = new EmployeeVO(acc, code, "我是測試");
//		employeeDAO.insert(employeeVO);
//		out.print("insert ok");

//		//testUpdate
//		String acc2 = "CEA" + 90017;
//		String code2 = genAuthCode();
//		EmployeeVO employeeVO2 = new EmployeeVO(acc2, code2, "我是測試2");
//		employeeVO2.setEmp_no(90017);
//		employeeVO2.setEmp_stat(1);
//		
//		employeeDAO.update(employeeVO2);
//		out.print("update ok");

//		//testFindByPrimaryKey
//		EmployeeVO employeeVO3 = employeeDAO.findByPrimaryKey(90002);
//		out.print(employeeVO3.getEmp_no()+"<br>");
//		out.print(employeeVO3.getAcc()+"<br>");
//		out.print(employeeVO3.getPwd()+"<br>");
//		out.print(employeeVO3.getName()+"<br>");
//		out.print(employeeVO3.getEmp_stat()+"<br>");

//		//testDelete
//		employeeDAO.delete(90017);
//		out.print("delete ok");

//		//testGetAll
//		List<EmployeeVO> list = employeeDAO.getAll();
//		for(EmployeeVO employeeVO4 : list) {
//		out.print(employeeVO4.getEmp_no()+"<br>");
//		out.print(employeeVO4.getAcc()+"<br>");
//		out.print(employeeVO4.getPwd()+"<br>");
//		out.print(employeeVO4.getName()+"<br>");
//		out.print(employeeVO4.getEmp_stat()+"<br>");
//		}		

//		// testGetNameEmp_no(String name)
//		List<EmployeeVO> list2 = employeeDAO.getNameEmp_no("齊容仙");
//		for (EmployeeVO employeeVO5 : list2) {
//			out.print(employeeVO5.getEmp_no() + "<br>");
//			out.print(employeeVO5.getAcc() + "<br>");
//			out.print(employeeVO5.getPwd() + "<br>");
//			out.print(employeeVO5.getName() + "<br>");
//			out.print(employeeVO5.getEmp_stat() + "<br>");
//		}

//		// testGetFunctionEmp_no(String name)
//		List<EmployeeVO> list3 = employeeDAO.getFunctionEmp_no(6);
//		for (EmployeeVO employeeVO6 : list3) {
//			out.print(employeeVO6.getEmp_no() + "<br>");
//			out.print(employeeVO6.getAcc() + "<br>");
//			out.print(employeeVO6.getPwd() + "<br>");
//			out.print(employeeVO6.getName() + "<br>");
//			out.print(employeeVO6.getEmp_stat() + "<br>");
//		}
		
		EmployeeService employeeSvc = new EmployeeService();
		List<EmployeeVO> employeeVO_name = employeeSvc.getNameEmp_no("齊容仙");
		for (EmployeeVO employeeVO6 : employeeVO_name) {
			out.print(employeeVO6.getEmp_no() + "<br>");
			out.print(employeeVO6.getAcc() + "<br>");
			out.print(employeeVO6.getPwd() + "<br>");
			out.print(employeeVO6.getName() + "<br>");
			out.print(employeeVO6.getEmp_stat() + "<br>");
		}

		out.print("</BODY>");
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

}
