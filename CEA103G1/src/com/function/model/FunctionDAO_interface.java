package com.function.model;

import java.util.List;

public interface FunctionDAO_interface {

		public void insert(FunctionVO functionVO);
		public void update(FunctionVO functionVO);
		public void delete(Integer fx_no);
		public FunctionVO findByPrimaryKey(Integer fx_no);
		public List<FunctionVO> getAll();

}
