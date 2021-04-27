package com.promotion_project.model;

import java.util.*;

public interface Promotion_projectDAO_interface {
          public void insert(Promotion_projectVO promotion_projectVO);
          public void update(Promotion_projectVO promotion_projectVO);
          public void delete(Integer promo_prj_no);
          public Promotion_projectVO findByPrimaryKey(Integer promo_prj_no);
          public List<Promotion_projectVO> getAll();
}
