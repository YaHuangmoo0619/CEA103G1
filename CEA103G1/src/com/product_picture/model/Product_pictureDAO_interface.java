package com.product_picture.model;

import java.util.*;

public interface Product_pictureDAO_interface {
//          public void insert(Product_pictureVO product_pictureVO);
//          public void update(Product_pictureVO product_pictureVO);
//          public void delete(Integer prod_pic_no);
          public Product_pictureVO findByPrimaryKey(Integer prod_pic_no);
//          public List<Product_pictureVO> getAll();
}
