package com.campsite_owner.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Campsite_ownerService {

	private Campsite_ownerDAO_interface dao;

	public Campsite_ownerService() {
		dao = new Campsite_ownerDAO();
	}

	public Campsite_ownerVO addCampsite_owner(String acc, String pwd, String id, String name, Date bday, Integer sex, Integer mobile, String mail, String city, String dist, String add, byte[] sticker,Integer bank_no, String bank_acc) {

		Campsite_ownerVO campsite_ownerVO = new Campsite_ownerVO();

		campsite_ownerVO.setAcc(acc);
		campsite_ownerVO.setPwd(pwd);
		campsite_ownerVO.setId(id);
		campsite_ownerVO.setName(name);
		campsite_ownerVO.setBday(bday);
		campsite_ownerVO.setSex(sex);
		campsite_ownerVO.setMobile(mobile);
		campsite_ownerVO.setMail(mail);
		campsite_ownerVO.setCity(city);
		campsite_ownerVO.setDist(dist);
		campsite_ownerVO.setAdd(add);
		campsite_ownerVO.setSticker(sticker);
		campsite_ownerVO.setBank_no(bank_no);
		campsite_ownerVO.setBank_acc(bank_acc);
		dao.insert(campsite_ownerVO);
		
		return campsite_ownerVO;
	}
	
	public Campsite_ownerVO enableCampsite_owner(Integer cso_no, Integer stat) {

		Campsite_ownerVO campsite_ownerVO = new Campsite_ownerVO();
		
		campsite_ownerVO.setCso_no(cso_no);
		campsite_ownerVO.setStat(stat);
		dao.enable(campsite_ownerVO);
		campsite_ownerVO = dao.findByPrimaryKey(cso_no);
		return campsite_ownerVO;
	}

	public void deleteCampsite_owner(Integer rank_no) {
		dao.delete(rank_no);
	}

	public Campsite_ownerVO getOneCampsite_owner(Integer cso_no) {
		return dao.findByPrimaryKey(cso_no);
	}

	public List<Campsite_ownerVO> getAll() {
		return dao.getAll();
	}
}
