package com.campsite_owner.model;

import java.util.List;

public interface Campsite_ownerDAO_interface {
	
          public Campsite_ownerVO insert(Campsite_ownerVO campsite_ownerVO);
          public void enable(Campsite_ownerVO campsite_ownerVO);
          public void delete(Integer cao_no);
          public Campsite_ownerVO findByPrimaryKey(Integer cso_no);
          public List<Campsite_ownerVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<Campsite_ownerVO> getAll(Map<String, String[]> map);
}