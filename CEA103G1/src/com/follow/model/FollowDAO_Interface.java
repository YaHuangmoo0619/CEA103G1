package com.follow.model;
import java.util.List;
//�q�\���Y�]�t�H�U����
//1. �s�W�q�\���Y
//2. �R���q�\���Y
//3. �q�\���Y�`�� ��ꤣ���n
//4. �d�ݤ@�ӤH���h�֯���
//5. �d�ݤ@�ӤH�l�ܨ��ǤH
public interface FollowDAO_Interface {
	public void insert(FollowVO FollowVO); //���ΰ��e�{����

	public void delete(Integer flwed_mbr_no,Integer flw_mbr_no); //���ΰ��e�{����

	public List<FollowVO> getAll();

	public List<FollowVO> findByflwed_mbr_no(Integer flwed_mbr_no);

	public List<FollowVO> findByflw_mbr_no(Integer flw_mbr_no);
}