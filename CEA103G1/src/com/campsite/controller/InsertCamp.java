package com.campsite.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import com.campsite.model.CampService;
import com.campsite.model.CampVO;
import com.campsite_feature.model.Camp_FeatureVO;
import com.campsite_picture.model.Camp_PictureService;
import com.campsite_picture.model.Camp_PictureVO;
import com.district.model.DistrictService;
import com.district.model.DistrictVO;
import com.place.model.PlaceService;
import com.place.model.PlaceVO;
import com.place_order_details.model.Place_Order_DetailsService;

@MultipartConfig
@WebServlet("/campsite/insertcamp.do")
public class InsertCamp extends HttpServlet {
	private static final long serialVersionUID = 2L;
	String saveDirectory = "/images";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
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
System.out.println("營主編號:" + cso_no);
			
			String camp_name = req.getParameter("camp_name");
			String camp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
			if (camp_name == null || camp_name.trim().length() == 0) {
				errorMsgs.add("營區名稱: 請勿空白");
			} else if (!camp_name.trim().matches(camp_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("營區名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
			}
System.out.println("營區名稱:" + camp_name);
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
			
			List<String> fileDirectory = savePictureAtLocal(req, camp_name);
			List<Camp_PictureVO> camp_picturelist = new ArrayList();
			for(String camp_pic : fileDirectory) {
				Camp_PictureVO camp_pictureVO = new Camp_PictureVO();
				camp_pictureVO.setCamp_pic(camp_pic);
				camp_picturelist.add(camp_pictureVO);
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

			String[] feature_list = req.getParameterValues("feature_list");
			List<Camp_FeatureVO> camp_featurelist = new ArrayList();
				for(int i = 0; i < feature_list.length; i++) {
					Camp_FeatureVO camp_featureVO = new Camp_FeatureVO();
					camp_featureVO .setCamp_fl_no(new Integer(feature_list[i]));
					camp_featurelist.add(camp_featureVO);
				}
				
			Integer plc_amt = new Integer(req.getParameter("plc_amt"));
			List<PlaceVO> placelist = new ArrayList();
			for(int i = 0; i <= plc_amt; i++) {
				String[] plc = req.getParameterValues("plc" + i);
				for(int j = 1; j <= new Integer(plc[1]); j++) {
					PlaceVO placeVO = new PlaceVO();
					placeVO.setPlc_name(plc[0] + j);
					placeVO.setPpl(new Integer(plc[2]));
					placeVO.setMax_ppl(new Integer(plc[3]));
					placeVO.setPc_wkdy(new Integer(plc[4]));
					placeVO.setPc_wknd(new Integer(plc[5]));
					placelist.add(placeVO);
				}
			}
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("campVO", campVO); // 含有輸入格式錯誤的campVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/addCamp.jsp");
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
					facility, operate_date, park, address, latitude, longitude, camp_featurelist, placelist, camp_picturelist);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String forwardurl = "/front-end/campsite/listAllCamp.jsp";
			req.setAttribute("campVO", campVO);
			RequestDispatcher successView = req.getRequestDispatcher(forwardurl); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/addCamp.jsp");
			failureView.forward(req, res);
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
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");

		String[] filename = (new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName()).split("\\.");
		for(int i = 0; i < filename.length; i++) {

		}
		String extension = filename[1];

		if (extension.length() == 0) {
			return null;
		}
		return extension;
	}
	
	public List<String> savePictureAtLocal(HttpServletRequest req, String camp_name) throws IOException, ServletException{
		List<String> fileDirectory = new ArrayList();
		String realPath = getServletContext().getRealPath(saveDirectory);// 阿飄路徑
		File fsaveDirectory = new File(realPath);
		Camp_PictureService camp_pictureSvc = new Camp_PictureService();
		int count = 1;
		Collection<Part> parts = req.getParts();
		for (Part part : parts) {
			if(!("photo".equals(part.getName()))) {
				continue;
			}
			String extension = getFileNameFromPart(part);
			String filename = camp_name + count + "." + extension;
			count++;
			Camp_PictureVO camp_pictureVO = new Camp_PictureVO();
			if (filename != null && part.getContentType() != null) {
				File f = new File(fsaveDirectory, filename);
				part.write(f.toString());
			}
			camp_pictureVO.setCamp_pic(req.getContextPath() + saveDirectory + filename);
			filename = req.getContextPath() + saveDirectory + "/" + filename;
			fileDirectory.add(filename);
		}
		
		return fileDirectory;		
	}
}
