package com.product_order.model;

import java.util.*;

import com.place_order_details.model.Place_Order_DetailsVO;
import com.product_order_details.model.Product_order_detailsVO;

public interface Product_orderDAO_interface {
	 public Product_orderVO insert(Product_orderVO product_orderVO, List<Product_order_detailsVO> list);
     public void update(Product_orderVO product_orderVO);
     public void delete(Integer prod_ord_no);
     public Product_orderVO findByPrimaryKey(Integer prod_ord_no);
     public List<Product_orderVO> getAll();

     //雅凰加的
     public void update_order_stat(Integer prod_ord_stat, Integer prod_ord_no);
     //雅凰加的

     public List<Product_orderVO> getByMbr(Integer mbr_no);

}
