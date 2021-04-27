package com.district.model;

import java.util.List;

public class DistrictService {

	private DistrictDAO_interface dao;

	public DistrictService() {
		dao = new DistrictDAO();
	}

	public DistrictVO addDistrict(String dist_name, String cty) {
		String area = "";
		if (cty.substring(0, 1).equals("�O")) {
			cty = "�x" + cty.substring(1);
		}
		String city = cty.substring(0, 2);
		if (city.equals("�x�_") || city.equals("�s�_") || city.equals("�s��") || city.equals("���")) {
			area = "�_";
		} else if (city.equals("�]��") || city.equals("����") || city.equals("�x��") || city.equals("�n��")
				|| city.equals("���L")) {
			area = "��";
		} else if (city.equals("�Ÿq") || city.equals("�x�n") || city.equals("����") || city.equals("�̪F")) {
			area = "�n";
		} else if (city.equals("�y��") || city.equals("�Ὤ") || city.equals("�x�F")) {
			area = "�F";
		} else {
			area = "���q";
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
		if (cty.substring(0, 1).equals("�O")) {
			cty = "�x" + cty.substring(1);
		}
		String city = cty.substring(0, 2);
		if (city.equals("�x�_") || city.equals("�s�_") || city.equals("�s��") || city.equals("���")) {
			area = "�_";
		} else if (city.equals("�]��") || city.equals("����") || city.equals("�x��") || city.equals("�n��")
				|| city.equals("���L")) {
			area = "��";
		} else if (city.equals("�Ÿq") || city.equals("�x�n") || city.equals("����") || city.equals("�̪F")) {
			area = "�n";
		} else if (city.equals("�y��") || city.equals("�Ὤ") || city.equals("�x�F")) {
			area = "�F";
		} else {
			area = "���q";
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
