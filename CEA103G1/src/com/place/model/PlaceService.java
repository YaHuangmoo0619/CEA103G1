package com.place.model;

import java.sql.Connection;
import java.util.List;

public class PlaceService {
	private PlaceDAO_interface dao;

	public PlaceService() {
		dao = new PlaceDAO();
	}

	public PlaceVO addPlace(Integer camp_no, String plc_name, Integer ppl, Integer max_ppl, Integer pc_wkdy,
			Integer pc_wknd, Integer open_stat, Integer plc_stat, Connection con) {

		PlaceVO placeVO = new PlaceVO();
		placeVO.setCamp_no(camp_no);
		placeVO.setPlc_name(plc_name);
		placeVO.setPpl(ppl);
		placeVO.setMax_ppl(max_ppl);
		placeVO.setPc_wkdy(pc_wkdy);
		placeVO.setPc_wknd(pc_wknd);
		placeVO.setOpen_stat(open_stat);
		placeVO.setPlc_stat(plc_stat);
		dao.insert(placeVO, con);

		return placeVO;
	}

	public List<PlaceVO> updatePlace(List<PlaceVO> placelist) {
		dao.update(placelist);

		return placelist;
	}

	public void deletePlace(Integer camp_no, Connection con) {
		dao.delete(camp_no, con);
	}

	public PlaceVO getOnePlace(Integer plc_no) {
		return dao.findByPrimaryKey(plc_no);
	}

	public List<PlaceVO> getAll() {
		return dao.getAll();
	}
	
	public List<PlaceVO> getByCamp(Integer camp_no){
		return dao.findByCampno(camp_no);
	}
}
