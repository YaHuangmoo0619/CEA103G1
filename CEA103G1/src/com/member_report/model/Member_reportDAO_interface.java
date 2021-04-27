package com.member_report.model;

import java.util.List;

public interface Member_reportDAO_interface {
	
          public void insert(Member_reportVO member_reportVO);
          public void update(Member_reportVO member_reportVO);
          public void delete(Integer mbr_rpt_no);
          public Member_reportVO findByPrimaryKey(Integer mbr_rpt_no);
          public List<Member_reportVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<Member_reportVO> getAll(Map<String, String[]> map);
}