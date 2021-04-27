package com.message_record.model;

import java.util.List;

public class Message_recordService {
	
	private Message_recordDAO_interface dao;
	
	public Message_recordService() {
		dao = new Message_recordDAO();
	}
	
	public Message_recordVO addMessage_record(Integer emp_no, Integer mbr_no, String msg_cont, java.sql.Timestamp msg_time) {
		
		Message_recordVO message_recordVO = new Message_recordVO();
		
		message_recordVO.setEmp_no(emp_no);
		message_recordVO.setMbr_no(mbr_no);
		message_recordVO.setMsg_cont(msg_cont);
		message_recordVO.setMsg_time(msg_time);
		dao.insert(message_recordVO);//package attributes to VO, and then use insert(...VO) from DAO to insert data into table
		
		return message_recordVO;// no return is OK, but has return may be can do more things
	}
	
	// prepare for Struts2
	public void addMessage_record(Message_recordVO message_recordVO) {
		dao.insert(message_recordVO);
	}
	
	public Message_recordVO updateMessage_record(Integer msg_no, Integer emp_no, Integer mbr_no, String msg_cont, java.sql.Timestamp msg_time) {
		
		Message_recordVO message_recordVO = new Message_recordVO();
		
		message_recordVO.setMsg_no(msg_no);
		message_recordVO.setEmp_no(emp_no);
		message_recordVO.setMbr_no(mbr_no);
		message_recordVO.setMsg_cont(msg_cont);
		message_recordVO.setMsg_time(msg_time);
		dao.update(message_recordVO);
		
		return dao.findByPrimaryKey(msg_no);
	}
	
	// prepare for Struts2
	public void updateMessage_record(Message_recordVO message_recordVO) {
		dao.update(message_recordVO);
	}
	
	public void deleteMessage_record(Integer msg_no) {
		dao.delete(msg_no);
	}

	public Message_recordVO getOneMessage_record(Integer msg_no) {
		return dao.findByPrimaryKey(msg_no);
	}

	public List<Message_recordVO> getAll() {
		return dao.getAll();
	}

}
