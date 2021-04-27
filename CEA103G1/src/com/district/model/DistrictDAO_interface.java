package com.district.model;

import java.util.List;

public interface DistrictDAO_interface {
	public void insert(DistrictVO districtVO);

	public void update(DistrictVO districtVO);

	public void delete(Integer dist_no);

	public DistrictVO findByPrimaryKey(Integer dist_no);

	public List<DistrictVO> getAll();
}
