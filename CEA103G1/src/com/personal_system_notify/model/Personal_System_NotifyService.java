package com.personal_system_notify.model;

import java.sql.Timestamp;
import java.util.List;

public class Personal_System_NotifyService {
	private Personal_System_NotifyDAO_Interface dao;
	
	public Personal_System_NotifyService() {
		dao = new Personal_System_NotifyDAO(); 
	}

	public Personal_System_NotifyVO addPersonal_System_Notify(Integer mbr_no, Integer ntfy_stat,String ntfy_cont, Timestamp ntfy_time) {

		Personal_System_NotifyVO personal_system_notifyVO = new Personal_System_NotifyVO();
	
		personal_system_notifyVO.setMbr_no(mbr_no);
		personal_system_notifyVO.setNtfy_stat(ntfy_stat);
		personal_system_notifyVO.setNtfy_cont(ntfy_cont);
		personal_system_notifyVO.setNtfy_time(ntfy_time);
		dao.insert(personal_system_notifyVO);

		return personal_system_notifyVO;
	}

	public Personal_System_NotifyVO updatePersonal_System_Notify(Integer ntfy_no,Integer mbr_no, Integer ntfy_stat,String ntfy_cont, Timestamp ntfy_time) {

		Personal_System_NotifyVO personal_system_notifyVO = new Personal_System_NotifyVO();

		personal_system_notifyVO.setNtfy_no(ntfy_no);
		personal_system_notifyVO.setMbr_no(mbr_no);
		personal_system_notifyVO.setNtfy_stat(ntfy_stat);
		personal_system_notifyVO.setNtfy_cont(ntfy_cont);
		personal_system_notifyVO.setNtfy_time(ntfy_time);
		dao.update(personal_system_notifyVO);

		return personal_system_notifyVO;
	}

	public void deletePersonal_System_Notify(Integer ntfy_no) {
		dao.delete(ntfy_no);
	}

	public Personal_System_NotifyVO getOnePersonal_System_Notify(Integer ntfy_no) {
		return dao.findByPrimaryKey(ntfy_no);
	}

	public List<Personal_System_NotifyVO> getAll() {
		return dao.getAll();
	}
}
