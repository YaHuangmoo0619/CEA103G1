package com.announcement.model;

import java.sql.Date;
import java.util.List;

public interface AnnouncementDAO_interface {

	 public void insert(AnnouncementVO announcementVO);
     public void update(AnnouncementVO announcementVO);
     public void delete(Integer an_no);
     public AnnouncementVO findByPrimaryKey(Integer an_no);
     public List<AnnouncementVO> getAll();
     public List<AnnouncementVO> getAlltoShow();
     public List<AnnouncementVO> getDateEmp_no(Date an_skd_date);
}
