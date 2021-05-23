package com.campsite.controller;

import java.io.File;
import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
import com.campsite_feature.model.Camp_FeatureService;
import com.campsite_feature.model.Camp_FeatureVO;
import com.campsite_picture.model.Camp_PictureService;
import com.campsite_picture.model.Camp_PictureVO;
import com.district.model.DistrictService;
import com.district.model.DistrictVO;
import com.feature_list.model.Feature_ListService;
import com.feature_list.model.Feature_ListVO;
import com.place.model.PlaceService;
import com.place.model.PlaceVO;

@MultipartConfig
public class CampServlet extends HttpServlet {
	String saveDirectory = "/images/temp";

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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/listAllCamp.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/listAllCamp.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/listAllCamp.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("campVO", campVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/campsite/listOneCamp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/listAllCamp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_DisplayFromBack".equals(action)) { // 來自select_page.jsp的請求

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

				Camp_FeatureService camp_featureSvc = new Camp_FeatureService();
				List<Camp_FeatureVO> allcamp_featurelist = camp_featureSvc.getAll();
				List<Camp_FeatureVO> camp_featurelist = new ArrayList();
				for (Camp_FeatureVO camp_featureVO : allcamp_featurelist) {
					if ((int) camp_featureVO.getCamp_no() == (int) camp_no) {
						camp_featurelist.add(camp_featureVO);
					}
				}

				List<PlaceVO> placelist = new PlaceService().getByCamp(campVO.getCamp_no());
				List<PlaceVO> newplacelist = new ArrayList();
				String plc_name = "12345";
				int amt = 0;

				for (int i = 0; i < placelist.size(); i++) {
					for (int j = 0; j < placelist.get(i).getPlc_name().length(); j++) {
						if (Character.isDigit(placelist.get(i).getPlc_name().charAt(j)) == true) {
							if (plc_name.equals(placelist.get(i).getPlc_name().substring(0, j))) {
								amt = new Integer(placelist.get(i).getPlc_name().substring(j));

							} else if (i != 0) {
								PlaceVO placeVO = new PlaceVO();
								placeVO.setPlc_name(plc_name + "," + amt);
								placeVO.setPpl(placelist.get(i - 1).getPpl());
								placeVO.setMax_ppl(placelist.get(i - 1).getMax_ppl());
								placeVO.setPc_wkdy(placelist.get(i - 1).getPc_wkdy());
								placeVO.setPc_wknd(placelist.get(i - 1).getPc_wknd());
								newplacelist.add(placeVO);
								plc_name = placelist.get(i).getPlc_name().substring(0, j);
								amt = new Integer(placelist.get(i).getPlc_name().substring(j));
							} else {
								plc_name = placelist.get(i).getPlc_name().substring(0, j);
								amt = new Integer(placelist.get(i).getPlc_name().substring(j));
							}
						}
					}
					if (i == placelist.size() - 1) {
						PlaceVO placeVO = new PlaceVO();
						placeVO.setPlc_name(plc_name + "," + amt);
						placeVO.setPpl(placelist.get(i).getPpl());
						placeVO.setMax_ppl(placelist.get(i).getMax_ppl());
						placeVO.setPc_wkdy(placelist.get(i).getPc_wkdy());
						placeVO.setPc_wknd(placelist.get(i).getPc_wknd());
						newplacelist.add(placeVO);
					}
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("campVO", campVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("camp_featurelist", camp_featurelist);
				req.setAttribute("placelist", newplacelist);
				System.out.println(req.getContextPath() + "/front-end/campsite/updateCamp.jsp");
				String url = "/front-end/campsite/updateCamp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println(e.getMessage());
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/listAllCamp.jsp");
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
				Boolean flag = true;
				if (part.getSize() == 0) {
					flag = false;
				}
				InputStream in = part.getInputStream();
				byte[] config = new byte[in.available()];
				in.read(config);
				in.close();
				String camp_name = req.getParameter("camp_name");
				Integer camp_no = new Integer(req.getParameter("camp_no").trim());
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
				String str = req.getParameter("pet");
				Integer pet = Integer.parseInt(str);

				String facility = req.getParameter("facility");

				str = req.getParameter("operate_Date");
				Integer operate_date = Integer.parseInt(str);

				String park = req.getParameter("park");
				String parkReg = "^[(\u4e00-\u9fa5)]{1,10}$";
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
//				List<Camp_PictureVO> camp_picturelist = null;
//				try {
//					List<String> fileDirectory = savePictureAtLocal(req, camp_name);
//					camp_picturelist = new ArrayList();
//					for (String camp_pic : fileDirectory) {
//						Camp_PictureVO camp_pictureVO = new Camp_PictureVO();
//						camp_pictureVO.setCamp_pic(camp_pic);
//						camp_picturelist.add(camp_pictureVO);
//					}
//				} catch (Exception e1) {
//				}

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
				String[] feature_list = req.getParameterValues("feature_list");
				String other = req.getParameter("otherornot");
				List<Camp_FeatureVO> camp_featurelist = new ArrayList();
				Camp_FeatureVO camp_featureVO = new Camp_FeatureVO();
				if ("yes".equals(other) && !("".equals(feature_list[feature_list.length - 1]))) {
					other = feature_list[feature_list.length - 1];
					Feature_ListService featureSvc = new Feature_ListService();
					Feature_ListVO featureVO = featureSvc.addFeature_List(other);
					camp_featureVO.setCamp_fl_no(featureVO.getCamp_fl_no());
					System.out.println(featureVO.getCamp_fl_no());
					camp_featurelist.add(camp_featureVO);

					for (int i = 0; i < feature_list.length - 1; i++) {
						camp_featureVO = new Camp_FeatureVO();
						camp_featureVO.setCamp_fl_no(new Integer(feature_list[i]));
						camp_featurelist.add(camp_featureVO);
					}
				} else {
					for (int i = 0; i < feature_list.length; i++) {
						camp_featureVO = new Camp_FeatureVO();
						camp_featureVO.setCamp_fl_no(new Integer(feature_list[i]));
						camp_featurelist.add(camp_featureVO);
					}
				}
				Camp_FeatureService camp_featureSvc = new Camp_FeatureService();				
				for (Camp_FeatureVO camp_featureVO2 : camp_featurelist) {
					camp_featureVO2.setCamp_no(camp_no);
				}
				camp_featureSvc.updateCamp_Feature(camp_featurelist);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("campVO", campVO); // 含有輸入格式錯誤的campVO物件,也存入req
					req.setAttribute("camp_featurelist", camp_featurelist);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/updateCamp.jsp");
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
				Integer dist_no = districtSvc.updateDistrict(district, county).getDist_no();
				CampService campSvc = new CampService();
				if (flag) {
					campVO = campSvc.updateCamp(dist_no, camp_name, campInfo, note, config, height, wireless, pet,
							facility, operate_date, park, address, latitude, longitude, camp_no);
				} else {
					campVO = campSvc.updateCamp3(dist_no, camp_name, campInfo, note, height, wireless, pet, facility,
							operate_date, park, address, latitude, longitude, camp_no);
				}
				campVO = campSvc.getOneCamp(camp_no);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String forwardurl = "/front-end/campsite/listOneCamp.jsp";
				req.setAttribute("campVO", campVO);
				RequestDispatcher successView = req.getRequestDispatcher(forwardurl); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/updateCamp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("updatereview".equals(action)) {
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer camp_no = new Integer(req.getParameter("camp_no").trim());
				Integer review_status = new Integer(req.getParameter("review_status").trim());

				CampService campSvc = new CampService();
				CampVO campVO = campSvc.getOneCamp(camp_no);
				int campsite_status = campVO.getCampsite_Status();

				campSvc.updateCamp2(campsite_status, review_status, camp_no);
				campVO = campSvc.getOneCamp(camp_no);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("campVO", campVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/campsite/listOneCamp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite/listOneCamp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("updatestatus".equals(action)) {
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer camp_no = new Integer(req.getParameter("camp_no").trim());
				Integer campsite_status = new Integer(req.getParameter("campsite_status").trim());
				CampService campSvc = new CampService();
				CampVO campVO = campSvc.getOneCamp(camp_no);
				int review_status = campVO.getReview_Status();
				campSvc.updateCamp2(campsite_status, review_status, camp_no);
				campVO = campSvc.getOneCamp(camp_no);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("campVO", campVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/campsite/listOneCamp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/listOneCamp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // 來自updateEmp.jsp

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
				String url = "/front-end/campsite/listAllCamp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/listAllCamp.jsp");
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

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");

		String[] filename = (new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName())
				.split("\\.");
		for (int i = 0; i < filename.length; i++) {

		}
		String extension = filename[1];

		if (extension.length() == 0) {
			return null;
		}
		return extension;
	}

	public List<String> savePictureAtLocal(HttpServletRequest req, String camp_name)
			throws IOException, ServletException {
		List<String> fileDirectory = new ArrayList();
		String realPath = getServletContext().getRealPath(saveDirectory);// 阿飄路徑
		File fsaveDirectory = new File(realPath);
		Camp_PictureService camp_pictureSvc = new Camp_PictureService();
		int count = 1;
		Collection<Part> parts = req.getParts();
		for (Part part : parts) {
			if (!("photo".equals(part.getName()))) {
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
