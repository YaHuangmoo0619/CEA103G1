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
//		EmployeeVO employeeVO = new EmployeeVO(acc, code, "�ڬO����");
//		employeeDAO.insert(employeeVO);
//		out.print("insert ok");

//		//testUpdate
//		String acc2 = "CEA" + 90017;
//		String code2 = genAuthCode();
//		EmployeeVO employeeVO2 = new EmployeeVO(acc2, code2, "�ڬO����2");
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
//		List<EmployeeVO> list2 = employeeDAO.getNameEmp_no("���e�P");
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
		List<EmployeeVO> employeeVO_name = employeeSvc.getNameEmp_no("���e�P");
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

}
