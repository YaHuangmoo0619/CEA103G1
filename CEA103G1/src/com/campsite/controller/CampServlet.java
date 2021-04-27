package com.campsite.controller;

import java.io.File;
import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import com.campsite.model.*;
import com.district.model.DistrictService;
import com.district.model.DistrictVO;

@MultipartConfig
public class CampServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("camp_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite/listAllCamp.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer camp_no = null;
				try {
					camp_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite/listAllCamp.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				CampService campSvc = new CampService();
				CampVO campVO = campSvc.getOneCamp(camp_no);
				if (campVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite/listAllCamp.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("campVO", campVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/campsite/listOneCamp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite/listAllCamp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Part part = req.getPart("config");
				InputStream in = part.getInputStream();
				byte[] config = new byte[in.available()];
				in.read(config);
				in.close();

				String str = req.getParameter("cso_no");
				String strReg = "^[7][0-9]{4}$";
				if (str == null || str.trim().length() == 0) {
					errorMsgs.add("營主編號: 請勿空白");
				} else if (!str.trim().matches(strReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("營主編號: 只能是數字,第一個數字為7且長度必需為5");
				}
				Integer cso_no = Integer.parseInt(str);

				String camp_name = req.getParameter("camp_name");
				String camp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (camp_name == null || camp_name.trim().length() == 0) {
					errorMsgs.add("營區名稱: 請勿空白");
				} else if (!camp_name.trim().matches(camp_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("營區名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String campInfo = req.getParameter("campInfo");
				String note = req.getParameter("note");

				String values[] = req.getParameterValues("wireless");
				String wireless = "";
				for (int i = 0; i < values.length; i++) {
					if (i == values.length - 1) {
						wireless += values[i];
					} else {
						wireless = wireless + values[i] + "、";
					}
				}

				str = req.getParameter("pet");
				Integer pet = Integer.parseInt(str);

				String facility = req.getParameter("facility");

				str = req.getParameter("operate_Date");
				Integer operate_date = Integer.parseInt(str);

				String park = req.getParameter("park");
				String parkReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (park == null || park.trim().length() == 0) {
					errorMsgs.add("停車方式: 請勿空白");
				} else if (!park.trim().matches(parkReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("停車方式: 只能是中文");
				}

				String county = req.getParameter("county");
				String countyReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (county == null || county.trim().length() == 0) {
					errorMsgs.add("縣市: 請勿空白");
				} else if (!county.trim().matches(countyReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("縣市: 只能是中文");
				}
				String district = req.getParameter("district");
				String districtReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (district == null || district.trim().length() == 0) {
					errorMsgs.add("鄉鎮市區: 請勿空白");
				} else if (!district.trim().matches(districtReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("鄉鎮市區: 只能是中文");
				}
				String address = req.getParameter("address");
				String addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				} else if (!address.trim().matches(addressReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地址: 只能是中文、數字");
				}

				CampVO campVO = new CampVO();
				campVO.setCso_no(cso_no);
				campVO.setCamp_name(camp_name);
				campVO.setCampInfo(campInfo);
				campVO.setNote(note);
				campVO.setConfig(config);
				campVO.setWireless(wireless);
				campVO.setPet(pet);
				campVO.setFacility(facility);
				campVO.setOperate_Date(operate_date);
				campVO.setPark(park);
				campVO.setCounty(county);
				campVO.setDistrict(district);
				campVO.setAddress(address);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("campVO", campVO); // 含有輸入格式錯誤的campVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite/addCamp.jsp");
					failureView.forward(req, res);
					return;
				}

				address = county + district + address;
				List<Double> location = getLocation(address);
				Double latitude = location.get(0);
				Double longitude = location.get(1);
				Double elevation = getElevation(location);
				String height = elevation + "m";
				/*************************** 2.開始新增資料 ***************************************/
				DistrictService districtSvc = new DistrictService();
				Integer dist_no = districtSvc.addDistrict(district, county).getDist_no();
				CampService campSvc = new CampService();
				campVO = campSvc.addCamp(cso_no, dist_no, camp_name, campInfo, note, config, height, wireless, pet,
						facility, operate_date, park, address, latitude, longitude);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String forwardurl = "/back-end/campsite/listAllCamp.jsp";
				req.setAttribute("campVO", campVO);
				RequestDispatcher successView = req.getRequestDispatcher(forwardurl); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite/addCamp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer camp_no = new Integer(req.getParameter("camp_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				CampService campSvc = new CampService();
				CampVO campVO = campSvc.getOneCamp(camp_no);
				DistrictService districtSvc = new DistrictService();
				DistrictVO districtVO = districtSvc.getOneDistrict(campVO.getDist_no());
				String county = districtVO.getCty();
				String district = districtVO.getDist_name();
				String address = campVO.getAddress();
				int length = (county + district).length();
				address = address.substring(length);
				campVO.setCounty(county);
				campVO.setDistrict(district);
				campVO.setAddress(address);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("campVO", campVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/campsite/updateCamp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite/listAllCamp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Part part = req.getPart("config");
				InputStream in = part.getInputStream();
				byte[] config = new byte[in.available()];
				in.read(config);
				in.close();
                
				Integer camp_no = new Integer(req.getParameter("camp_no").trim());
				
				String str;
				String camp_name = req.getParameter("camp_name");
				String camp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (camp_name == null || camp_name.trim().length() == 0) {
					errorMsgs.add("營區名稱: 請勿空白");
				} else if (!camp_name.trim().matches(camp_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("營區名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
                System.out.println(camp_name);
				String campInfo = req.getParameter("campInfo");
				System.out.println(campInfo);
				String note = req.getParameter("note");
				System.out.println(note);
				String values[] = req.getParameterValues("wireless");
				String wireless = "";
				for (int i = 0; i < values.length; i++) {
					if (i == values.length - 1) {
						wireless += values[i];
					} else {
						wireless = wireless + values[i] + "、";
					}
				}
				System.out.println(wireless);
				str = req.getParameter("pet");
				Integer pet = Integer.parseInt(str);
				System.out.println(pet);
				String facility = req.getParameter("facility");
				System.out.println(facility);
				str = req.getParameter("operate_Date");
				Integer operate_date = Integer.parseInt(str);
				System.out.println(operate_date);
				String park = req.getParameter("park");
				String parkReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (park == null || park.trim().length() == 0) {
					errorMsgs.add("停車方式: 請勿空白");
				} else if (!park.trim().matches(parkReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("停車方式: 只能是中文");
				}
				System.out.println(park);
				String county = req.getParameter("county");
				String countyReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (county == null || county.trim().length() == 0) {
					errorMsgs.add("縣市: 請勿空白");
				} else if (!county.trim().matches(countyReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("縣市: 只能是中文");
				}
				String district = req.getParameter("district");
				String districtReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (district == null || district.trim().length() == 0) {
					errorMsgs.add("鄉鎮市區: 請勿空白");
				} else if (!district.trim().matches(districtReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("鄉鎮市區: 只能是中文");
				}
				String address = req.getParameter("address");
				String addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				} else if (!address.trim().matches(addressReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地址: 只能是中文、數字");
				}

				CampVO campVO = new CampVO();
				campVO.setCamp_name(camp_name);
				campVO.setCampInfo(campInfo);
				campVO.setNote(note);
				campVO.setConfig(config);
				campVO.setWireless(wireless);
				campVO.setPet(pet);
				campVO.setFacility(facility);
				campVO.setOperate_Date(operate_date);
				campVO.setPark(park);
				campVO.setCounty(county);
				campVO.setDistrict(district);
				campVO.setAddress(address);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("campVO", campVO); // 含有輸入格式錯誤的campVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite/updateCamp.jsp");
					failureView.forward(req, res);
					return;
				}

				address = county + district + address;
				System.out.println(address);
				List<Double> location = getLocation(address);
				Double latitude = location.get(0);
				Double longitude = location.get(1);
				Double elevation = getElevation(location);
				String height = elevation + "m";
				/*************************** 2.開始新增資料 ***************************************/
				DistrictService districtSvc = new DistrictService();
				Integer dist_no = districtSvc.updateDistrict(district, county).getDist_no();
				System.out.println(dist_no);
				CampService campSvc = new CampService();
				campVO = campSvc.updateCamp(dist_no, camp_name, campInfo, note, config, height, wireless, pet,
						facility, operate_date, park, address, latitude, longitude, camp_no);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String forwardurl = "/back-end/campsite/listOneCamp.jsp";
				req.setAttribute("campVO", campVO);
				RequestDispatcher successView = req.getRequestDispatcher(forwardurl); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite/updateCamp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer camp_no = new Integer(req.getParameter("camp_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				CampService campSvc = new CampService();
				campSvc.deleteCamp(camp_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/campsite/listAllCamp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite/listAllCamp.jsp");
				failureView.forward(req, res);
			}
		}
	}

	public List<Double> getLocation(String address) throws IOException, JSONException {
		String GOOGLE_URL = "https://maps.google.com/maps/api/geocode/json?" + "address=" + address + "&language=zh-TW&"
				+ "key=AIzaSyAS3FFLkorvfSYZ9kblu3kplAs2a1H8dO8";
		String jsonStr = Jsoup.connect(GOOGLE_URL).ignoreContentType(true).execute().body().toString();

		List<Double> locat = new ArrayList<Double>();
		JSONObject jsonObj = new JSONObject(jsonStr.trim());
		JSONArray results = jsonObj.getJSONArray("results");
		JSONObject campsite = results.getJSONObject(0);
		JSONObject geometry = campsite.getJSONObject("geometry");
		JSONObject location = geometry.getJSONObject("location");
		locat.add(location.getDouble("lat"));
		locat.add(location.getDouble("lng"));
		return locat;
	}

	public Double getElevation(List<Double> location) throws IOException, JSONException {
		String GOOGLE_URL = "https://maps.googleapis.com/maps/api/elevation/json?" + "locations=" + location.get(0)
				+ "," + location.get(1) + "&" + "language=zh-TW&" + "key=AIzaSyAS3FFLkorvfSYZ9kblu3kplAs2a1H8dO8";
		String jsonStr = Jsoup.connect(GOOGLE_URL).ignoreContentType(true).execute().body().toString();

		JSONObject jsonObj = new JSONObject(jsonStr.trim());
		JSONArray results = jsonObj.getJSONArray("results");
		JSONObject campsite = results.getJSONObject(0);
		Double elevation = campsite.getDouble("elevation");
		return elevation;
	}
}
