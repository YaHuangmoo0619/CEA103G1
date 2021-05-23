package com.article_report.model;

import java.sql.Timestamp;
import java.util.List;



public class Article_ReportService {
	private Article_ReportDAO_Interface dao;
	
	public Article_ReportService() {
		dao = new Article_ReportDAO(); 
	}

	public Article_ReportVO addArticle_Report(Integer art_no, Integer mbr_no,String rpt_cont,Timestamp rpt_time, Integer proc_stat) {

		Article_ReportVO article_reportVO = new Article_ReportVO();
	

		article_reportVO.setArt_no(art_no);
		article_reportVO.setMbr_no(mbr_no);
		article_reportVO.setRpt_cont(rpt_cont);
		article_reportVO.setRpt_time(rpt_time);
		article_reportVO.setProc_stat(proc_stat);
		dao.insert(article_reportVO);

		return article_reportVO;
	}

	public Article_ReportVO updateArticle_Report(Integer art_rpt_no,Integer art_no,Integer mbr_no,String rpt_cont,Timestamp rpt_time,Integer proc_stat) {

		Article_ReportVO article_reportVO = new Article_ReportVO();

		article_reportVO.setArt_rpt_no(art_rpt_no);
		article_reportVO.setArt_no(art_no);
		article_reportVO.setMbr_no(mbr_no);
		article_reportVO.setRpt_cont(rpt_cont);
		article_reportVO.setRpt_time(rpt_time);
		article_reportVO.setProc_stat(proc_stat);
		dao.update(article_reportVO);

		return article_reportVO;
	}

	public void deleteArticle_Report(Integer art_rpt_no) {
		dao.delete(art_rpt_no);
	}

	public Article_ReportVO getOneArticle_Report(Integer art_rpt_no) {
		return dao.findByPrimaryKey(art_rpt_no);
	}

	public List<Article_ReportVO> getAll() {
		return dao.getAll();
	}
	
	public List<Article_ReportVO> get_wait_for_judge() {
		return dao.get_wait_for_judge();
	}
	
	public List<Article_ReportVO> get_already_judge() {
		return dao.get_already_judge();
	}
}
