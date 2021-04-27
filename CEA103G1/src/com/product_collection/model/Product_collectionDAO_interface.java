package com.product_collection.model;

import java.util.*;

public interface Product_collectionDAO_interface {
          public void insert(Product_collectionVO product_collectionVO);
          public void delete(Integer mbr_no, Integer prod_no);
          public Product_collectionVO findByPrimaryKey(Integer mbr_no, Integer prod_no);
          public List<Product_collectionVO> findByMbr_no(Integer mbr_no);
          public List<Product_collectionVO> findByProd_no(Integer prod_no);
          public List<Product_collectionVO> getAll();
}
