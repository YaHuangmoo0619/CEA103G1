package com.campsite_comment.model;

import java.util.List;

public interface Campsite_commentDAO_interface {
	
          public void insert(Campsite_commentVO campsite_commentVO);
          public void update(Campsite_commentVO campsite_commentVO);
          public void delete(Integer camp_cmnt_no);
          public Campsite_commentVO findByPrimaryKey(Integer camp_cmnt_no);
          public List<Campsite_commentVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<Campsite_commentVO> getAll(Map<String, String[]> map);
}