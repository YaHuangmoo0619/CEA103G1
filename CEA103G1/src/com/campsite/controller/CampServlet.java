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
import com.place.model.PlaceService;
import com.place.model.PlaceVO;

@MultipartConfig
public class CampServlet extends HttpServlet {
	String saveDirectory = "/images";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("camp_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/listAllCamp.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer camp_no = null;
				try {
					camp_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/listAllCamp.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				CampService campSvc = new CampService();
				CampVO campVO = campSvc.getOneCamp(camp_no);
				if (campVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/listAllCamp.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("campVO", campVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/campsite/listOneCamp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/listAllCamp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_DisplayFromBack".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("camp_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite/listAllCamp.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer camp_no = null;
				try {
					camp_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite/listAllCamp.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				CampService campSvc = new CampService();
				CampVO campVO = campSvc.getOneCamp(camp_no);
				if (campVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite/listAllCamp.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("campVO", campVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/campsite/listOneCamp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite/listAllCamp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer camp_no = new Integer(req.getParameter("camp_no"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
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
							if(plc_name.equals(placelist.get(i).getPlc_name().substring(0, j))) {
								amt = new Integer(placelist.get(i).getPlc_name().substring(j));	
								
							}else if(i != 0){
								PlaceVO placeVO = new PlaceVO();
								placeVO.setPlc_name(plc_name +"," + amt);
								placeVO.setPpl(placelist.get(i-1).getPpl());
								placeVO.setMax_ppl(placelist.get(i-1).getMax_ppl());
								placeVO.setPc_wkdy(placelist.get(i-1).getPc_wkdy());
								placeVO.setPc_wknd(placelist.get(i-1).getPc_wknd());
								newplacelist.add(placeVO);
								plc_name = placelist.get(i).getPlc_name().substring(0, j);								
								amt = new Integer(placelist.get(i).getPlc_name().substring(j));
							}else {
								plc_name = placelist.get(i).getPlc_name().substring(0, j);								
								amt = new Integer(placelist.get(i).getPlc_name().substring(j));
							}
						}
					}
					if(i == placelist.size()-1) {
						PlaceVO placeVO = new PlaceVO();
						placeVO.setPlc_name(plc_name +"," + amt);
						placeVO.setPpl(placelist.get(i).getPpl());
						placeVO.setMax_ppl(placelist.get(i).getMax_ppl());
						placeVO.setPc_wkdy(placelist.get(i).getPc_wkdy());
						placeVO.setPc_wknd(placelist.get(i).getPc_wknd());
						newplacelist.add(placeVO);
					}
				}
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("campVO", campVO); // ��Ʈw���X��empVO����,�s�Jreq
				req.setAttribute("camp_featurelist", camp_featurelist);
				req.setAttribute("placelist", newplacelist);
				System.out.println(req.getContextPath() + "/front-end/campsite/updateCamp.jsp");
				String url = "/front-end/campsite/updateCamp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				System.out.println(e.getMessage());
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/listAllCamp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
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
					errorMsgs.add("��ϦW��: �ФŪť�");
				} else if (!camp_name.trim().matches(camp_nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("��ϦW��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				System.out.println(camp_name);
				String campInfo = req.getParameter("campInfo");
				System.out.println(campInfo);
				System.out.println(campInfo);
				String note = req.getParameter("note");
				System.out.println(note);
				String values[] = req.getParameterValues("wireless");
				String wireless = "";
				for (int i = 0; i < values.length; i++) {
					if (i == values.length - 1) {
						wireless += values[i];
					} else {
						wireless = wireless + values[i] + "�B";
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
					errorMsgs.add("�����覡: �ФŪť�");
				} else if (!park.trim().matches(parkReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�����覡: �u��O����");
				}
				System.out.println(park);
				String county = req.getParameter("county");
				String countyReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (county == null || county.trim().length() == 0) {
					errorMsgs.add("����: �ФŪť�");
				} else if (!county.trim().matches(countyReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("����: �u��O����");
				}
				String district = req.getParameter("district");
				String districtReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (district == null || district.trim().length() == 0) {
					errorMsgs.add("�m����: �ФŪť�");
				} else if (!district.trim().matches(districtReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�m����: �u��O����");
				}
				System.out.println(district);
				String address = req.getParameter("address");
				String addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (address == null) {
					errorMsgs.add("�a�}: �ФŪť�");
				}
//				} else if (!address.trim().matches(addressReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("�a�}: �u��O����B�Ʀr");
//				}
				System.out.println(address);
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
					req.setAttribute("campVO", campVO); // �t����J�榡���~��campVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/updateCamp.jsp");
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
				/*************************** 2.�}�l�s�W��� ***************************************/
				DistrictService districtSvc = new DistrictService();
				Integer dist_no = districtSvc.updateDistrict(district, county).getDist_no();
				System.out.println(dist_no);
				CampService campSvc = new CampService();
				campVO = campSvc.updateCamp(dist_no, camp_name, campInfo, note, config, height, wireless, pet, facility,
						operate_date, park, address, latitude, longitude, camp_no);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String forwardurl = "/front-end/campsite/listOneCamp.jsp";
				req.setAttribute("campVO", campVO);
				RequestDispatcher successView = req.getRequestDispatcher(forwardurl); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/updateCamp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("updatereview".equals(action)) {
			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer camp_no = new Integer(req.getParameter("camp_no").trim());
				Integer review_status = new Integer(req.getParameter("review_status").trim());

				CampService campSvc = new CampService();
				CampVO campVO = campSvc.getOneCamp(camp_no);
				int campsite_status = campVO.getCampsite_Status();

				campSvc.updateCamp2(campsite_status, review_status, camp_no);
				campVO = campSvc.getOneCamp(camp_no);
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("campVO", campVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/campsite/listOneCamp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/campsite/listOneCamp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("updatestatus".equals(action)) {
			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer camp_no = new Integer(req.getParameter("camp_no").trim());
				Integer campsite_status = new Integer(req.getParameter("campsite_status").trim());
				CampService campSvc = new CampService();
				CampVO campVO = campSvc.getOneCamp(camp_no);
				int review_status = campVO.getReview_Status();
				campSvc.updateCamp2(campsite_status, review_status, camp_no);
				campVO = campSvc.getOneCamp(camp_no);
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("campVO", campVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front-end/campsite/listOneCamp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/campsite/listOneCamp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // �Ӧ�updateEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer camp_no = new Integer(req.getParameter("camp_no"));

				/*************************** 2.�}�l�R����� ***************************************/
				CampService campSvc = new CampService();
				campSvc.deleteCamp(camp_no);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/front-end/campsite/listAllCamp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
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
		String realPath = getServletContext().getRealPath(saveDirectory);// ���Ƹ��|
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
