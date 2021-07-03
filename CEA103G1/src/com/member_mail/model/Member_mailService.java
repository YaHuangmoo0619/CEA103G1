package com.member_mail.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.member_mail_picture.model.Member_mail_pictureHBVO;

public class Member_mailService {
	
	private Member_mailHBDAO_interface dao;
	
	public Member_mailService() {
		dao = new Member_mailHBDAO();
	}
	
	public Member_mailVO addMember_mail(Integer send_no, Integer rcpt_no, Integer mail_read_stat, Integer mail_stat, String mail_cont, String mail_time) {
		
		Member_mailVO member_mailVO = new Member_mailVO();
		
		member_mailVO.setSend_no(send_no);
		member_mailVO.setRcpt_no(rcpt_no);
		member_mailVO.setMail_read_stat(mail_read_stat);
		member_mailVO.setMail_stat(mail_stat);
		member_mailVO.setMail_cont(mail_cont);
		member_mailVO.setMail_time(mail_time);
		dao.insert(member_mailVO);//package attributes to VO, and then use insert(...VO) from DAO to insert data into table
		
		return member_mailVO;// no return is OK, but has return may be can do more things
	}
	
	// prepare for Struts2
	public void addMember_mail(Member_mailVO member_mailVO) {
		dao.insert(member_mailVO);
	}
	
	public Member_mailVO updateMember_mail(Integer mail_no, Integer send_no, Integer rcpt_no, Integer mail_read_stat, Integer mail_stat, String mail_cont, String mail_time) {
		
		Member_mailVO member_mailVO = new Member_mailVO();
		
		member_mailVO.setMail_no(mail_no);
		member_mailVO.setSend_no(send_no);
		member_mailVO.setRcpt_no(rcpt_no);
		member_mailVO.setMail_read_stat(mail_read_stat);
		member_mailVO.setMail_stat(mail_stat);
		member_mailVO.setMail_cont(mail_cont);
		member_mailVO.setMail_time(mail_time);
		dao.update(member_mailVO);
		
		return dao.findByPrimaryKey(mail_no);
	}
	
	// prepare for Struts2
	public void updateMember_mail(Member_mailVO member_mailVO) {
		dao.update(member_mailVO);
	}
	
	public void deleteMember_mail(Integer mail_no) {
		dao.delete(mail_no);
	}

	public Member_mailVO getOneMember_mail(Integer mail_no) {
		return dao.findByPrimaryKey(mail_no);
	}

	public List<Member_mailVO> getAll() {
		return dao.getAll();
	}

	public Set<Member_mailVO> getWhereCondition(Map<String,String[]> map){
		return new Member_mailDAO().getWhereCondition(map);
	}
	
	public Member_mailVO insertWithPic(Integer send_no, Integer rcpt_no, Integer mail_read_stat, Integer mail_stat, String mail_cont, String mail_time, Set<Member_mail_pictureHBVO> set) {
		System.out.println("member_mailService");
		Member_mailVO member_mailVO = new Member_mailVO();
		
		member_mailVO.setSend_no(send_no);
		member_mailVO.setRcpt_no(rcpt_no);
		member_mailVO.setMail_cont(mail_cont);
		member_mailVO.setMail_stat(mail_stat);
		member_mailVO.setMail_read_stat(mail_read_stat);
		member_mailVO.setMail_time(mail_time);
		dao.insertWithPic(member_mailVO, set);
		
		return member_mailVO;
	}
	
	public List<Member_mailVO> getSend(Integer send_no){
		return dao.getSend(send_no);
	}
    public List<Member_mailVO> getRcpt(Integer rcpt_no){
    	return dao.getSend(rcpt_no);
    }
    public List<Member_mailVO> getStat(Integer mail_stat){
//    	System.out.println("service");
    	return dao.getStat(mail_stat);
    }
}
