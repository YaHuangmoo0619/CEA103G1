package com.campsite_equipment.model;

import java.util.List;
import java.util.Map;


public interface Camp_EquipmentDAO_interface {
	public void insert(Camp_EquipmentVO camp_equipmentVO);

	public void update(Camp_EquipmentVO camp_equipmentVO);

	public void delete(Integer eq_no, Integer camp_no);

	public Camp_EquipmentVO findByPrimaryKey(Integer eq_no, Integer camp_no);

	public List<Camp_EquipmentVO> getAll();
}
