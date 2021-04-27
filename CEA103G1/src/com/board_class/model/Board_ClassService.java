package com.board_class.model;
import java.util.List;


public class Board_ClassService {

	private Board_ClassDAO_Interface dao;
	////private Board_ClassDAO_Interface dao =  new Board_ClassDAO();
	
	public Board_ClassService() {
		dao = new Board_ClassDAO(); 
	}

	public Board_ClassVO addBoard_Class(String bd_name,Integer bd_stat) {

		Board_ClassVO board_classVO = new Board_ClassVO();
	

		board_classVO.setBd_name(bd_name);
		board_classVO.setBd_stat(bd_stat);

		dao.insert(board_classVO);

		return board_classVO;
	}

	public Board_ClassVO updateBoard_Class(Integer bd_cl_no,String bd_name,Integer bd_stat ) {

		Board_ClassVO board_classVO = new Board_ClassVO();

		board_classVO.setBd_cl_no(bd_cl_no);
		board_classVO.setBd_name(bd_name);
		board_classVO.setBd_stat(bd_stat);
		dao.update(board_classVO);

		return board_classVO;
	}

	public void deleteBoard_Class(Integer bd_cl_no) {
		dao.delete(bd_cl_no);
	}

	public Board_ClassVO getOneBoard_Class(Integer bd_cl_no) {
		return dao.findByPrimaryKey(bd_cl_no);
	}

	public List<Board_ClassVO> getAll() {
		return dao.getAll();
	}
}