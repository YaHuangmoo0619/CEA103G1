package com.shopping_cart.model;

import java.util.*;

public interface Shopping_cartDAO_interface {
          public void insert(Shopping_cartVO shopping_cartVO);
          public void update(Shopping_cartVO shopping_cartVO);
          public void delete(Integer mbr_no, Integer prod_no);
          public Shopping_cartVO findByPrimaryKey(Integer mbr_no, Integer prod_no);
          public List<Shopping_cartVO> findByMbr_no(Integer mbr_no);
          public List<Shopping_cartVO> getAll();
}
