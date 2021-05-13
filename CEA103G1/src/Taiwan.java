import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

@WebServlet("/Taiwan.do")
public class Taiwan extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("Big5");
		res.setContentType("text/html; charset=Big5");
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");
		String taiwan = jedis.get("Taiwan").replaceAll("\\n", "").replaceAll("\'", "\"");
//		taiwan = taiwan.replaceAll(" ","");
		PrintWriter out = res.getWriter();
		out.println(taiwan);

	}
}
