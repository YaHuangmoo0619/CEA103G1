package com.district.model;

import java.util.List;

public class DistrictService {

	private DistrictDAO_interface dao;

	public DistrictService() {
		dao = new DistrictDAO();
	}

	public DistrictVO addDistrict(String dist_name, String cty) {
		String area = "";
		if (cty.substring(0, 1).equals("臺")) {
			cty = "台" + cty.substring(1);
		}
		String city = cty.substring(0, 2);
		if (city.equals("台北") || city.equals("新北") || city.equals("新竹") || city.equals("桃園")) {
			area = "北";
		} else if (city.equals("苗栗") || city.equals("彰化") || city.equals("台中") || city.equals("南投")
				|| city.equals("雲林")) {
			area = "中";
		} else if (city.equals("嘉義") || city.equals("台南") || city.equals("高雄") || city.equals("屏東")) {
			area = "南";
		} else if (city.equals("宜蘭") || city.equals("花蓮") || city.equals("台東")) {
			area = "東";
		} else {
			area = "離島";
		}
		List<DistrictVO> list = getAll();
		for (DistrictVO district : list) {
			if (dist_name.equals(district.getDist_name()) && cty.equals(district.getCty())) {
				return district;
			}
		}
		DistrictVO districtVO = new DistrictVO();
		districtVO.setDist_name(dist_name);
		districtVO.setCty(cty);
		districtVO.setArea(area);
		dao.insert(districtVO);
		list = getAll();
		for (DistrictVO district : list) {
			if (dist_name.equals(district.getDist_name()) && cty.equals(district.getCty())) {
				return district;
			}
		}
		return districtVO;
	}

	public DistrictVO updateDistrict(String dist_name, String cty) {
		String area = "";
		if (cty.substring(0, 1).equals("臺")) {
			cty = "台" + cty.substring(1);
		}
		String city = cty.substring(0, 2);
		if (city.equals("台北") || city.equals("新北") || city.equals("新竹") || city.equals("桃園")) {
			area = "北";
		} else if (city.equals("苗栗") || city.equals("彰化") || city.equals("台中") || city.equals("南投")
				|| city.equals("雲林")) {
			area = "中";
		} else if (city.equals("嘉義") || city.equals("台南") || city.equals("高雄") || city.equals("屏東")) {
			area = "南";
		} else if (city.equals("宜蘭") || city.equals("花蓮") || city.equals("台東")) {
			area = "東";
		} else {
			area = "離島";
		}
		DistrictVO districtVO = new DistrictVO();
		districtVO.setDist_name(dist_name);
		districtVO.setCty(cty);
		districtVO.setArea(area);
		List<DistrictVO> list = getAll();
		for (DistrictVO district : list) {
			if (dist_name.equals(district.getDist_name()) && cty.equals(district.getCty())) {
				return district;
			}
		}
		dao.insert(districtVO);
		list = getAll();
		for (DistrictVO district : list) {
			if (dist_name.equals(district.getDist_name()) && cty.equals(district.getCty())) {
				return district;
			}
		}
		return districtVO;
	}

	public void deleteDistrict(Integer camp_fl_no) {
		dao.delete(camp_fl_no);
	}

	public DistrictVO getOneDistrict(Integer camp_fl_no) {
		return dao.findByPrimaryKey(camp_fl_no);
	}

	public List<DistrictVO> getAll() {
		return dao.getAll();
	}
}
