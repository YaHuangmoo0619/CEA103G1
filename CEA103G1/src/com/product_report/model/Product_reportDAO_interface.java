package com.product_report.model;

import java.util.*;

public interface Product_reportDAO_interface {
          public void insert(Product_reportVO product_reportVO);
          public void update(Product_reportVO product_reportVO);
          public void delete(Integer prod_rpt_no);
          public Product_reportVO findByPrimaryKey(Integer prod_rpt_no);
          public List<Product_reportVO> getAll();
}
