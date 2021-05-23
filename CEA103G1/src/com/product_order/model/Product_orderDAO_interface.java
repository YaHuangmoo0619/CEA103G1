package com.product_order.model;

import java.util.*;

public interface Product_orderDAO_interface {
	 public void insert(Product_orderVO product_orderVO);
     public void update(Product_orderVO product_orderVO);
     public void delete(Integer prod_ord_no);
     public Product_orderVO findByPrimaryKey(Integer prod_ord_no);
     public List<Product_orderVO> getAll();

     //���ĥ[��
     public void update_order_stat(Integer prod_ord_stat, Integer prod_ord_no);
     //���ĥ[��

     public List<Product_orderVO> getAllByMbr();

}
