package com.personal_system_notify.model;
<<<<<<< .merge_file_a13200
=======
import java.sql.Connection;
>>>>>>> .merge_file_a08108
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.member_mail.model.Member_mailVO;
public interface Personal_System_NotifyDAO_Interface {
	public void insert(Personal_System_NotifyVO Personal_System_NotifyVO);
<<<<<<< .merge_file_a13200

=======
	
	public void insertWithLike(Personal_System_NotifyVO personal_System_NotifyVO,Connection con);

	public void insertWithArticle(Personal_System_NotifyVO personal_System_NotifyVO,Connection con);
	
>>>>>>> .merge_file_a08108
	public void update(Personal_System_NotifyVO Personal_System_NotifyVO);

	public void delete(Integer ntfy_no);

	public Personal_System_NotifyVO findByPrimaryKey(Integer ntfy_no);

	public List<Personal_System_NotifyVO> getAll();
	
	public Set<Personal_System_NotifyVO> getWhereCondition(Map<String,String[]> map);
}
