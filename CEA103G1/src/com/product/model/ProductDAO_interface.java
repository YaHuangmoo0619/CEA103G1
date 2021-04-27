package com.product.model;

import java.util.*;

public interface ProductDAO_interface {
          public void insert(ProductVO productVO);
          public void update(ProductVO productVO);
          public void delete(Integer prod_no);
          public ProductVO findByPrimaryKey(Integer prod_no);
          public List<ProductVO> getAll();
}
