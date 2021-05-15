import java.io.IOException;
import java.io.PrintWriter;
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
import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/Member.do")
public class GetMember extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		
		HttpSession session = req.getSession();
		Object memberVO = session.getAttribute("memberVO");
		String uri = req.getParameter("uri");

		if (memberVO == null) {
			session.setAttribute("location", uri);
			List<Object> list = new ArrayList();
			list.add("請先登入會員");
			list.add(req.getContextPath() + "/front-end/member/login.jsp");
			String jsonObject = gson.toJson(list);
			out.println(jsonObject);
		} else {			
			String jsonObject = gson.toJson(memberVO);
			out.println(jsonObject);
		}
	}
}
