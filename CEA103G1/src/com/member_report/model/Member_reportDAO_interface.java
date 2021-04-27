package com.member_report.model;

import java.util.List;

public interface Member_reportDAO_interface {
	
          public void insert(Member_reportVO member_reportVO);
          public void update(Member_reportVO member_reportVO);
          public void delete(Integer mbr_rpt_no);
          public Member_reportVO findByPrimaryKey(Integer mbr_rpt_no);
          public List<Member_reportVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<Member_reportVO> getAll(Map<String, String[]> map);
}