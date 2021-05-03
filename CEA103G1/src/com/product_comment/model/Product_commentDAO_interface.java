package com.product_comment.model;

import java.sql.Timestamp;
import java.util.List;

import com.product_comment.model.Product_commentVO;

public interface Product_commentDAO_interface {
	
          public void insert(Product_commentVO product_commentVO);
          public void update(Product_commentVO product_commentVO);
          public void delete(Integer prod_cmnt_no);
          public Product_commentVO findByPrimaryKey(Integer prod_cmnt_no);
          public List<Product_commentVO> getAll();
          public List<Product_commentVO> getTimestampProd_no(Timestamp cmnt_time);
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<Product_commentVO> getAll(Map<String, String[]> map);
}