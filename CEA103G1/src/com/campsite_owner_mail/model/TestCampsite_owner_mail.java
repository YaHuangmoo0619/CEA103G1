package com.campsite_owner_mail.model;

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

import com.campsite_owner_mail.model.Campsite_owner_mailDAO;
import com.campsite_owner_mail.model.Campsite_owner_mailVO;
import com.member_mail.model.Member_mailVO;

@WebServlet("/TestCampsite_owner_mail")
public class TestCampsite_owner_mail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		Campsite_owner_mailDAO campsite_owner_mailDAO = new Campsite_owner_mailDAO();

		out.print("<HTML>");
		out.print("<HEAD><TITLE>TestAuthority</TITLE><STYLE>tr{border:solid 1px black;}</STYLE></HEAD>");
		out.print("<BODY>");

//		//testInsert
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		sdf.format(new java.util.Date());
//		Campsite_owner_mailVO campsite_owner_mailVO = new Campsite_owner_mailVO(10006, 70010, 0, 0, "test", sdf.format(new java.util.Date()));
//		campsite_owner_mailDAO.insert(campsite_owner_mailVO);
//		out.print("insert ok");

//		//testUpdate	
//		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		sdf2.format(new java.util.Date());
//		Campsite_owner_mailVO campsite_owner_mailVO2 = new Campsite_owner_mailVO(10006, 70010, 0, 0, "test2", sdf2.format(new java.util.Date()));
//		campsite_owner_mailVO2.setMail_no(40011);
//		campsite_owner_mailDAO.update(campsite_owner_mailVO2);
//		out.print("update ok");		

//		//testDelete	
//		campsite_owner_mailDAO.delete(40012);
//		out.print("delete ok");		

//		//testFindByPrimaryKey
//		Campsite_owner_mailVO campsite_owner_mail3 = campsite_owner_mailDAO.findByPrimaryKey(40011);
//		out.print(campsite_owner_mail3.getMail_no()+"<br>");
//		out.print(campsite_owner_mail3.getSend_no()+"<br>");
//		out.print(campsite_owner_mail3.getRcpt_no()+"<br>");
//		out.print(campsite_owner_mail3.getMail_cont().replace("\n", "<br>")+"<br>");
//		out.print(campsite_owner_mail3.getMail_stat()+"<br>");
//		out.print(campsite_owner_mail3.getMail_read_stat()+"<br>");
//		out.print(campsite_owner_mail3.getMail_time()+"<br>");

//		//testGetAll
//		List<Campsite_owner_mailVO> list = campsite_owner_mailDAO.getAll();
//		for(Campsite_owner_mailVO campsite_owner_mailVO4 : list) {
//			out.print(campsite_owner_mailVO4.getMail_no()+"<br>");
//			out.print(campsite_owner_mailVO4.getSend_no()+"<br>");
//			out.print(campsite_owner_mailVO4.getRcpt_no()+"<br>");
//			out.print(campsite_owner_mailVO4.getMail_cont().replace("\n", "<br>")+"<br>");
//			out.print(campsite_owner_mailVO4.getMail_stat()+"<br>");
//			out.print(campsite_owner_mailVO4.getMail_read_stat()+"<br>");
//			out.print(campsite_owner_mailVO4.getMail_time()+"<br>");
//		}

		// testGetWhereCondition
		Map<String, String[]> map = new LinkedHashMap<String, String[]>();
		map.put("mail_no", new String[] { "40011" });
		map.put("send_no", new String[] { "10006" });
		map.put("rcpt_no", new String[] { "70010" });
		map.put("mail_time", new String[] { "2021-05-03" });
		map.put("mail_cont", new String[] { "test2" });
		map.put("mail_stat", new String[] { "0" });
		map.put("mail_read_stat", new String[] { "0" });

		Set<Campsite_owner_mailVO> set = campsite_owner_mailDAO.getWhereCondition(map);
		for (Campsite_owner_mailVO campsite_owner_mailVO5 : set) {
			out.print(campsite_owner_mailVO5.getMail_no() + "<br>");
			out.print(campsite_owner_mailVO5.getSend_no() + "<br>");
			out.print(campsite_owner_mailVO5.getRcpt_no() + "<br>");
			out.print(campsite_owner_mailVO5.getMail_cont().replace("\n", "<br>") + "<br>");
			out.print(campsite_owner_mailVO5.getMail_stat() + "<br>");
			out.print(campsite_owner_mailVO5.getMail_read_stat() + "<br>");
			out.print(campsite_owner_mailVO5.getMail_time() + "<br>");
		}

		out.print("</BODY>");

	}

}
