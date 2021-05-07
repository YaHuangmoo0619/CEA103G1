package com.place_order.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.place_order_details.model.Place_Order_DetailsVO;

public class Place_OrderService {
	private Place_OrderDAO_interface dao;

	public Place_OrderService() {
		dao = new Place_OrderDAO();
	}

	public Place_OrderVO addPlace_Order(Integer mbr_no, Integer camp_no, Date ckin_date,
			Date ckout_date, Integer plc_amt, Integer plc_ord_sum, Integer ex_ppl, Integer pay_meth, Integer pay_stat,
			Integer used_pt, Integer receipt, String rmk, List<Place_Order_DetailsVO> list) {

		Place_OrderVO place_orderVO = new Place_OrderVO();
		place_orderVO.setMbr_no(mbr_no);
		place_orderVO.setCamp_no(camp_no);
		place_orderVO.setCkin_date(ckin_date);
		place_orderVO.setCkout_date(ckout_date);
		place_orderVO.setPlc_amt(plc_amt);
		place_orderVO.setPlc_ord_sum(plc_ord_sum);
		place_orderVO.setEx_ppl(ex_ppl);
		place_orderVO.setPay_meth(pay_meth);
		place_orderVO.setPay_stat(pay_stat);
		place_orderVO.setUsed_pt(used_pt);
		place_orderVO.setReceipt(receipt);
		place_orderVO.setRmk(rmk);
		place_orderVO = dao.insert(place_orderVO, list);

		return place_orderVO;
	}

	public Place_OrderVO updatePlace_Order(Integer plc_ord_no, Integer pay_stat, Integer ckin_stat) {

		Place_OrderVO place_orderVO = new Place_OrderVO();		
		place_orderVO.setPlc_ord_no(plc_ord_no);
		place_orderVO.setPay_stat(pay_stat);
		place_orderVO.setCkin_stat(ckin_stat);
		dao.update(place_orderVO);

		return place_orderVO;
	}

	public void deletePlace_Order(Integer place_order_no) {
		dao.delete(place_order_no);
	}

	public Place_OrderVO getOnePlace_Order(Integer place_order_no) {
		return dao.findByPrimaryKey(place_order_no);
	}
	
	public List<Place_OrderVO> getByCamp(Integer camp_no) {
		return dao.findByCamp(camp_no);
	}
	
	public List<Place_OrderVO> getByMember(Integer mbr_no) {
		return dao.findByMember(mbr_no);
	}

	public List<Place_OrderVO> getAll() {
		return dao.getAll();
	}
}
