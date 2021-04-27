package com.product_comment.model;

import java.util.List;

public interface Product_commentDAO_interface {
	
          public void insert(Product_commentVO product_commentVO);
          public void update(Product_commentVO product_commentVO);
          public void delete(Integer prod_cmnt_no);
          public Product_commentVO findByPrimaryKey(Integer prod_cmnt_no);
          public List<Product_commentVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<Product_commentVO> getAll(Map<String, String[]> map);
}