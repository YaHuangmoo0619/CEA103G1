package com.advertising_application.model;

import java.util.List;

public interface Advertising_applicationDAO_interface {
	
          public void insert(Advertising_applicationVO advertising_applicationVO);
          public void update(Advertising_applicationVO advertising_applicationVO);
          public void delete(Integer ad_no);
          public Advertising_applicationVO findByPrimaryKey(Integer ad_no);
          public List<Advertising_applicationVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<Advertising_applicationVO> getAll(Map<String, String[]> map);
}