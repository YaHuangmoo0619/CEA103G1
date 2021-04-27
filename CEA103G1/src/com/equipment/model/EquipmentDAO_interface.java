package com.equipment.model;

import java.util.List;
import java.util.Map;


public interface EquipmentDAO_interface {
	public void insert(EquipmentVO equipmentVO);

	public void update(EquipmentVO equipmentVO);

	public void delete(Integer eq_no);

	public EquipmentVO findByPrimaryKey(Integer eq_no);

	public List<EquipmentVO> getAll();
}
