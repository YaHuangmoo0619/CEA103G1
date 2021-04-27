package com.campsite_report.model;

import java.util.List;

public interface Campsite_reportDAO_interface {
	
          public void insert(Campsite_reportVO campsite_reportVO);
          public void update(Campsite_reportVO campsite_reportVO);
          public void delete(Integer camp_rpt_no);
          public Campsite_reportVO findByPrimaryKey(Integer camp_rpt_no);
          public List<Campsite_reportVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<Campsite_reportVO> getAll(Map<String, String[]> map);
}