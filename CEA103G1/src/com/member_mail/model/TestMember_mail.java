package com.member_mail.model;

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

import com.member_mail.model.Member_mailDAO;
import com.member_mail.model.Member_mailVO;
import com.service_mail.model.Service_mailVO;


@WebServlet("/TestMember_mail")
public class TestMember_mail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		Member_mailDAO member_mailDAO = new Member_mailDAO();
		
		out.print("<HTML>");
		out.print("<HEAD><TITLE>TestAuthority</TITLE><STYLE>tr{border:solid 1px black;}</STYLE></HEAD>");
		out.print("<BODY>");
		
//		//testInsert
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		sdf.format(new java.util.Date());
//		Member_mailVO member_mailVO = new Member_mailVO(90003, 10008, 0, 0, "test", sdf.format(new java.util.Date()));
//		member_mailDAO.insert(member_mailVO);
//		out.print("insert ok");
		
//		//testUpdate	
//		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		sdf2.format(new java.util.Date());
//		Member_mailVO member_mailVO2 = new Member_mailVO(70003, 10008, 0, 0, "test2", sdf2.format(new java.util.Date()));
//		member_mailVO2.setMail_no(30012);
//		member_mailDAO.update(member_mailVO2);
//		out.print("update ok");		
		
//		//testDelete	
//		member_mailDAO.delete(30013);
//		out.print("delete ok");		
		
//		//testFindByPrimaryKey
//		Member_mailVO member_mailVO3 = member_mailDAO.findByPrimaryKey(30012);
//		out.print(member_mailVO3.getMail_no()+"<br>");
//		out.print(member_mailVO3.getSend_no()+"<br>");
//		out.print(member_mailVO3.getRcpt_no()+"<br>");
//		out.print(member_mailVO3.getMail_cont().replace("\n", "<br>")+"<br>");
//		out.print(member_mailVO3.getMail_stat()+"<br>");
//		out.print(member_mailVO3.getMail_read_stat()+"<br>");
//		out.print(member_mailVO3.getMail_time()+"<br>");
		
//		//testGetAll
//		List<Member_mailVO> list = member_mailDAO.getAll();
//		for(Member_mailVO member_mailVO4 : list) {
//			out.print(member_mailVO4.getMail_no()+"<br>");
//			out.print(member_mailVO4.getSend_no()+"<br>");
//			out.print(member_mailVO4.getRcpt_no()+"<br>");
//			out.print(member_mailVO4.getMail_cont().replace("\n", "<br>")+"<br>");
//			out.print(member_mailVO4.getMail_stat()+"<br>");
//			out.print(member_mailVO4.getMail_read_stat()+"<br>");
//			out.print(member_mailVO4.getMail_time()+"<br>");
//		}
		
		//testGetWhereCondition
		Map<String,String[]> map = new LinkedHashMap<String,String[]>();
		map.put("mail_no", new String[] {"30012"});
		map.put("send_no", new String[] {"70003"});
		map.put("rcpt_no", new String[] {"10008"});
		map.put("mail_time", new String[] {"2021-05-03"});
		map.put("mail_cont", new String[] {"test2"});
		map.put("mail_stat", new String[] {"0"});
		map.put("mail_read_stat", new String[] {"0"});
				
		Set<Member_mailVO> set = member_mailDAO.getWhereCondition(map);
		for(Member_mailVO member_mailVO5 : set) {
			out.print(member_mailVO5.getMail_no()+"<br>");
			out.print(member_mailVO5.getSend_no()+"<br>");
			out.print(member_mailVO5.getRcpt_no()+"<br>");
			out.print(member_mailVO5.getMail_cont().replace("\n", "<br>")+"<br>");
			out.print(member_mailVO5.getMail_stat()+"<br>");
			out.print(member_mailVO5.getMail_read_stat()+"<br>");
			out.print(member_mailVO5.getMail_time()+"<br>");
		}
		
		out.print("</BODY>");
	}

}
