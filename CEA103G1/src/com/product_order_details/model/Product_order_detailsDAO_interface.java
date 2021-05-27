package com.product_order_details.model;

import java.util.*;

public interface Product_order_detailsDAO_interface {
	public void insert(Product_order_detailsVO product_order_detailsVO);  
	public void insertByOrder(Product_order_detailsVO product_order_detailsVO, java.sql.Connection con);
	public void update(Product_order_detailsVO product_order_detailsVO);
    public void delete(Integer prod_ord_no, Integer prod_no);
    public Product_order_detailsVO findByPrimaryKey(Integer prod_ord_no, Integer prod_no);
    public List<Product_order_detailsVO> findByProd_ord_no(Integer prod_ord_no);
//  public List<Product_order_detailsVO> findByProd_no(Integer prod_no);
    public List<Product_order_detailsVO> getAll();
}
