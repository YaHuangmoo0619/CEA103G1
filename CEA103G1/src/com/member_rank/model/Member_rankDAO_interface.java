package com.member_rank.model;

import java.util.List;

public interface Member_rankDAO_interface {
	
          public void insert(Member_rankVO member_rankVO);
          public void update(Member_rankVO member_rankVO);
          public void delete(Integer rank_no);
          public Member_rankVO findByPrimaryKey(Integer rank_no);
          public List<Member_rankVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<Member_rankVO> getAll(Map<String, String[]> map);
}