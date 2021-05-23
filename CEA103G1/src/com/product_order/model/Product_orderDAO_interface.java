package com.product_order.model;

import java.util.*;

public interface Product_orderDAO_interface {
	 public void insert(Product_orderVO product_orderVO);
     public void update(Product_orderVO product_orderVO);
     public void delete(Integer prod_ord_no);
     public Product_orderVO findByPrimaryKey(Integer prod_ord_no);
     public List<Product_orderVO> getAll();
<<<<<<< HEAD
     //雅凰加的
     public void update_order_stat(Integer prod_ord_stat, Integer prod_ord_no);
     //雅凰加的
=======
     public List<Product_orderVO> getAllByMbr();
>>>>>>> 39c78c37739c60e8e9bc21573896ec60409b4f4e
}
