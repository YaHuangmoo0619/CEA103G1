package com.message_record.model;

import java.util.List;

public interface Message_recordDAO_interface {

	public void insert(Message_recordVO message_recordVO);
    public void update(Message_recordVO message_recordVO);
    public void delete(Integer msg_no);
    public Message_recordVO findByPrimaryKey(Integer msg_no);
    public List<Message_recordVO> getAll();
}
