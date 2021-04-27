package com.personal_system_notify.model;
import java.util.List;
import java.util.Map;
public interface Personal_System_NotifyDAO_Interface {
	public void insert(Personal_System_NotifyVO Personal_System_NotifyVO);

	public void update(Personal_System_NotifyVO Personal_System_NotifyVO);

	public void delete(Integer ntfy_no);

	public Personal_System_NotifyVO findByPrimaryKey(Integer ntfy_no);

	public List<Personal_System_NotifyVO> getAll();
}
