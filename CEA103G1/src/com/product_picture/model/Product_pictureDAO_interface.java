package com.product_picture.model;

import java.sql.Connection;
import java.util.*;

public interface Product_pictureDAO_interface {
          public void insert(Product_pictureVO product_pictureVO,Connection con);
//          public void update(Product_pictureVO product_pictureVO);
//          public void delete(Integer prod_pic_no);
          public void updateFromProd(Integer prod_no,Set<Product_pictureVO> set,Connection con);
          public Product_pictureVO findByPrimaryKey(Integer prod_pic_no);
          public List<Product_pictureVO> findByProd_no(Integer prod_no);
//          public List<Product_pictureVO> getAll();
}
