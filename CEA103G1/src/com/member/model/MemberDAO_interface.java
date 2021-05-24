package com.member.model;

import java.util.List;

public interface MemberDAO_interface {
	
          public void insert(MemberVO memberVO);
          public void update(MemberVO memberVO);
          public void delete(Integer mbr_no);
          public void register_Member(MemberVO memberVO);
          public MemberVO findByPrimaryKey(Integer mbr_no);
          public MemberVO findByPrimaryKey_login(String acc, String pwd);
          public MemberVO findByEmail(String mail);
          public MemberVO loginCheck(String acc);
          public void updatePwd(String mail, String pwd);
          public void updateMemberStatus(String acc, Integer acc_stat);
          public List<MemberVO> getAll();
<<<<<<< HEAD
=======

          //���ĥ[��
          public MemberVO update_info(MemberVO memberVO);
          public String findByAcc(String acc);
          //���ĥ[��
>>>>>>> 8e7ffca81417310b61a8af379c26d26c5054451f
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<MemberVO> getAll(Map<String, String[]> map)	
}