package com.member.model;

import java.util.List;

public interface MemberDAO_interface {
	
          public void insert(MemberVO memberVO);
          public void update(MemberVO memberVO);
          public void delete(Integer mbr_no);
          public void register_Member(MemberVO memberVO);
          public MemberVO findByPrimaryKey(Integer mbr_no);
          public MemberVO findByPrimaryKey_login(String acc, String pwd);
          public List<MemberVO> getAll();

          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<MemberVO> getAll(Map<String, String[]> map)	
}