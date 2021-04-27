package com.board_class.model;
import java.util.List;
import java.util.Map;
public interface Board_ClassDAO_Interface {
	public void insert(Board_ClassVO board_ClassVO);

	public void update(Board_ClassVO board_ClassVO);

	public void delete(Integer bd_cl_no);

	public Board_ClassVO findByPrimaryKey(Integer bd_cl_no);

	public List<Board_ClassVO> getAll();
}
