package com.promotion_product.model;

import java.util.*;

public interface Promotion_productDAO_interface {
          public void insert(Promotion_productVO promotion_productVO);
          public void update(Promotion_productVO promotion_productVO);
          public void delete(Integer promo_prj_no, Integer prod_no);
          public Promotion_productVO findByPrimaryKey(Integer promo_prj_no, Integer prod_no);
          public List<Promotion_productVO> findByProd_prj_no(Integer promo_prj_no);
          public List<Promotion_productVO> findByProd_no(Integer prod_no);
          public List<Promotion_productVO> getAll();
}
