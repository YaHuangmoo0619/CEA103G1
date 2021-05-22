package com.product.model;

import java.util.*;

import com.product_picture.model.Product_pictureVO;

public interface ProductDAO_interface {
          public void insert(ProductVO productVO);
          public void update(ProductVO productVO);
          public void delete(Integer prod_no);
          public ProductVO findByPrimaryKey(Integer prod_no);
          public List<ProductVO> getAll();
          public List<ProductVO> getShop();
          public List<ProductVO> getAll(Map<String, String[]> map); 
          //雅凰加的
          public void updateWithPic(ProductVO productVO,Set<Product_pictureVO> set);
          public void updateUpOrDown(Integer prod_stat, Integer prod_no);
          public List<ProductVO> getAllStat();
          public void insert(ProductVO productVO,Set<Product_pictureVO> set);
          //雅凰加的
}
