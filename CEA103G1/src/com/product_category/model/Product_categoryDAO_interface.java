package com.product_category.model;

import java.util.*;

public interface Product_categoryDAO_interface {
          public void insert(Product_categoryVO product_categoryVO);
          public void update(Product_categoryVO product_categoryVO);
          public void delete(Integer prod_cat_no);
          public Product_categoryVO findByPrimaryKey(Integer prod_cat_no);
          public List<Product_categoryVO> getAll();
}
