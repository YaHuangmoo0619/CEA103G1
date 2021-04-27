package com.product_comment_report.model;

import java.util.List;

public interface Product_comment_reportDAO_interface {
	
          public void insert(Product_comment_reportVO product_comment_reportVO);
          public void update(Product_comment_reportVO product_comment_reportVO);
          public void delete(Integer rank_no);
          public Product_comment_reportVO findByPrimaryKey(Integer rank_no);
          public List<Product_comment_reportVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<Product_comment_reportVO> getAll(Map<String, String[]> map);
}