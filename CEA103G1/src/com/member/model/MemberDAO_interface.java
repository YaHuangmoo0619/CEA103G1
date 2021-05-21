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

          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<MemberVO> getAll(Map<String, String[]> map)	
}