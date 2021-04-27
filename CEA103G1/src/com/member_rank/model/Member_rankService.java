package com.member_rank.model;

import java.util.List;

public class Member_rankService {

	private Member_rankDAO_interface dao;

	public Member_rankService() {
		dao = new Member_rankDAO();
	}

	public Member_rankVO addMember_rank(String rank_name, Integer pt_rwd_rto) {

		Member_rankVO member_rankVO = new Member_rankVO();

		member_rankVO.setRank_name(rank_name);
		member_rankVO.setPt_rwd_rto(pt_rwd_rto);
		dao.insert(member_rankVO);
		
		return member_rankVO;
	}
	
	public Member_rankVO updateMember_rank(Integer rank_no, String rank_name, Integer pt_rwd_rto) {

		Member_rankVO member_rankVO = new Member_rankVO();
		
		member_rankVO.setRank_no(rank_no);
		member_rankVO.setRank_name(rank_name);
		member_rankVO.setPt_rwd_rto(pt_rwd_rto);
		dao.update(member_rankVO);
		
		return member_rankVO;
	}

	public void deleteMember_rank(Integer rank_no) {
		dao.delete(rank_no);
	}

	public Member_rankVO getOneMember_rank(Integer rank_no) {
		return dao.findByPrimaryKey(rank_no);
	}

	public List<Member_rankVO> getAll() {
		return dao.getAll();
	}
}
