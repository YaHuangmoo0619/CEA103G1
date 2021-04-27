import java.io.IOException;
import java.io.PrintWriter;

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
		Object member = session.getAttribute("member");
		if (member == null) {
			MemberService memberSvc = new MemberService();
	        MemberVO memberVO = memberSvc.getOneMember(10001);
	        session.setAttribute("member", memberVO);
	        String jsonObject = gson.toJson(memberVO);
			out.println(jsonObject);
		} else {
			String jsonObject = gson.toJson(member);
			out.println(jsonObject);
		}
	}
}
