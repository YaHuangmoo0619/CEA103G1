package com.member.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface MemberDAO_interface {
	
          public void insert(MemberVO memberVO);
          public void update(MemberVO memberVO);
          public void delete(Integer mbr_no);
          public MemberVO findByPrimaryKey(Integer mbr_no);
          public List<MemberVO> getAll();
          public List<MemberVO> getDateMbr_no(Date bday);
          public List<MemberVO> getTimestampMbr_no(Timestamp join_time);
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<MemberVO> getAll(Map<String, String[]> map);
		
}