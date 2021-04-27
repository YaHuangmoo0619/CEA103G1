package com.member_rank.model;

import java.util.List;

public interface Member_rankDAO_interface {
	
          public void insert(Member_rankVO member_rankVO);
          public void update(Member_rankVO member_rankVO);
          public void delete(Integer rank_no);
          public Member_rankVO findByPrimaryKey(Integer rank_no);
          public List<Member_rankVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<Member_rankVO> getAll(Map<String, String[]> map);
}