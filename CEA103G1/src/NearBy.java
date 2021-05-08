import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import com.google.gson.Gson;

@WebServlet("/near.do")
public class NearBy extends HttpServlet {
	private String name;
	private String locat;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocat() {
		return locat;
	}
	public void setLocat(String locat) {
		this.locat = locat;
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		String jsonObject;
		Gson gson = new Gson();
		String locat = req.getParameter("locat");
		String GOOGLE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + "location=" + locat + "&"
				+ "radius=20000&" + "types=food&" + "name=´ºÂI&" + "language=zh-TW&"
				+ "key=AIzaSyAS3FFLkorvfSYZ9kblu3kplAs2a1H8dO8";
		String jsonStr = Jsoup.connect(GOOGLE_URL).ignoreContentType(true).execute().body().toString();
		List<NearBy> list = new ArrayList();
		try {
			JSONObject jsonObj = new JSONObject(jsonStr.trim());
			JSONArray results = jsonObj.getJSONArray("results");
			for(int i = 0; i < results.length(); i++) {
				NearBy nearby = new NearBy();
				JSONObject near = results.getJSONObject(i);
				JSONObject geometry = near.getJSONObject("geometry");
				JSONObject location = geometry.getJSONObject("location");
				
				String spot = location.getDouble("lat") + "," + location.getDouble("lng");
				String name = near.getString("name");
				nearby.setName(name);
				nearby.setLocat(spot);
				list.add(nearby);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		jsonObject = gson.toJson(list);
		out.println(jsonObject);
	}
}
