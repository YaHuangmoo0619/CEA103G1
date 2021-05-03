package com.service_mail.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestService_mail")
public class TestService_mail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		Service_mailDAO service_mailDAO = new Service_mailDAO();
		
		out.print("<HTML>");
		out.print("<HEAD><TITLE>TestAuthority</TITLE><STYLE>tr{border:solid 1px black;}</STYLE></HEAD>");
		out.print("<BODY>");
		
//		//testInsert
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		sdf.format(new java.util.Date());
//		Service_mailVO service_mailVO = new Service_mailVO(90003, 10008, "test", 0, 0, sdf.format(new java.util.Date()));
//		service_mailDAO.insert(service_mailVO);
//		out.print("insert ok");
		
//		//testUpdate	
//		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		sdf2.format(new java.util.Date());
//		Service_mailVO service_mailVO2 = new Service_mailVO(90003, 10008, "test3", 0, 0, sdf2.format(new java.util.Date()));
//		service_mailVO2.setMail_no(80011);
//		service_mailDAO.update(service_mailVO2);
//		out.print("update ok");		
		
//		//testDelete	
//		service_mailDAO.delete(80012);
//		out.print("delete ok");		
		
//		//testFindByPrimaryKey
//		Service_mailVO service_mailVO3 = service_mailDAO.findByPrimaryKey(80011);
//		out.print(service_mailVO3.getMail_no()+"<br>");
//		out.print(service_mailVO3.getEmp_no()+"<br>");
//		out.print(service_mailVO3.getMbr_no()+"<br>");
//		out.print(service_mailVO3.getMail_cont().replace("\n", "<br>")+"<br>");
//		out.print(service_mailVO3.getMail_stat()+"<br>");
//		out.print(service_mailVO3.getMail_read_stat()+"<br>");
//		out.print(service_mailVO3.getMail_time()+"<br>");
		
//		//testGetAll
//		List<Service_mailVO> list = service_mailDAO.getAll();
//		for(Service_mailVO service_mailVO4 : list) {
//			out.print(service_mailVO4.getMail_no()+"<br>");
//			out.print(service_mailVO4.getEmp_no()+"<br>");
//			out.print(service_mailVO4.getMbr_no()+"<br>");
//			out.print(service_mailVO4.getMail_cont().replace("\n", "<br>")+"<br>");
//			out.print(service_mailVO4.getMail_stat()+"<br>");
//			out.print(service_mailVO4.getMail_read_stat()+"<br>");
//			out.print(service_mailVO4.getMail_time()+"<br>");
//		}
		
		//testGetWhereCondition
		Map<String,String[]> map = new LinkedHashMap<String,String[]>();
		map.put("mail_no", new String[] {"80011"});
		map.put("emp_no", new String[] {"90003"});
		map.put("mbr_no", new String[] {"10008"});
		map.put("mail_time", new String[] {"2021-05-03"});
		map.put("mail_cont", new String[] {"test3"});
		map.put("mail_stat", new String[] {"0"});
		map.put("mail_read_stat", new String[] {"0"});
		
		Set<Service_mailVO> set = service_mailDAO.getWhereCondition(map);
		for(Service_mailVO service_mailVO5 : set) {
			out.print(service_mailVO5.getMail_no()+"<br>");
			out.print(service_mailVO5.getEmp_no()+"<br>");
			out.print(service_mailVO5.getMbr_no()+"<br>");
			out.print(service_mailVO5.getMail_cont().replace("\n", "<br>")+"<br>");
			out.print(service_mailVO5.getMail_stat()+"<br>");
			out.print(service_mailVO5.getMail_read_stat()+"<br>");
			out.print(service_mailVO5.getMail_time()+"<br>");
		}
			
		out.print("</BODY>");
	}

}
