package com.campsite_comment_report.model;

import java.util.List;

public interface Campsite_comment_reportDAO_interface {
	
          public void insert(Campsite_comment_reportVO campsite_comment_reportVO);
          public void update(Campsite_comment_reportVO campsite_comment_reportVO);
          public void delete(Integer camp_cmnt_rpt_no);
          public Campsite_comment_reportVO findByPrimaryKey(Integer camp_cmnt_rpt_no);
          public List<Campsite_comment_reportVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<Campsite_comment_reportVO> getAll(Map<String, String[]> map);
}