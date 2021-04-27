package com.follow.model;
import java.util.List;
//訂閱關係包含以下五種
//1. 新增訂閱關係
//2. 刪除訂閱關係
//3. 訂閱關係總表 其實不重要
//4. 查看一個人有多少粉絲
//5. 查看一個人追蹤那些人
public interface FollowDAO_Interface {
	public void insert(FollowVO FollowVO); //不用做呈現頁面

	public void delete(Integer flwed_mbr_no,Integer flw_mbr_no); //不用做呈現頁面

	public List<FollowVO> getAll();

	public List<FollowVO> findByflwed_mbr_no(Integer flwed_mbr_no);

	public List<FollowVO> findByflw_mbr_no(Integer flw_mbr_no);
}