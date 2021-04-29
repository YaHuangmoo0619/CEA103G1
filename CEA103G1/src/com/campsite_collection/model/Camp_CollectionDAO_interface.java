package com.campsite_collection.model;

import java.util.List;

public interface Camp_CollectionDAO_interface {
	public void insert(Camp_CollectionVO camp_collectionVO);

	public void update(Camp_CollectionVO camp_collectionVO);

	public void delete(Integer camp_no, Integer mbr_no);

	public Camp_CollectionVO findByPrimaryKey(Integer camp_no, Integer mbr_no);

	public List<Camp_CollectionVO> getAll();
}

