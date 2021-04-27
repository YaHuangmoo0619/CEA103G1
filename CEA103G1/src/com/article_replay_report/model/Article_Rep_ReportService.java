package com.article_replay_report.model;

import java.sql.Timestamp;
import java.util.List;



public class Article_Rep_ReportService {
	private Article_Rep_ReportDAO_Interface dao;
	
	public Article_Rep_ReportService() {
		dao = new Article_Rep_ReportDAO(); 
	}

	public Article_Rep_ReportVO addArticle_Rep_Report(Integer mbr_no,Integer art_rep_no,String rpt_cont,Timestamp rpt_time, Integer proc_stat) {

		Article_Rep_ReportVO article_Rep_ReportVO = new Article_Rep_ReportVO();
	


		article_Rep_ReportVO.setMbr_no(mbr_no);
		article_Rep_ReportVO.setArt_rep_no(art_rep_no);
		article_Rep_ReportVO.setRpt_cont(rpt_cont);
		article_Rep_ReportVO.setRpt_time(rpt_time);
		article_Rep_ReportVO.setProc_stat(proc_stat);
		dao.insert(article_Rep_ReportVO);

		return article_Rep_ReportVO;
	}

	public Article_Rep_ReportVO updateArticle_Rep_Report(Integer art_rep_rpt_no,Integer mbr_no,Integer art_rep_no,String rpt_cont,Timestamp rpt_time,Integer proc_stat) {

		Article_Rep_ReportVO article_Rep_ReportVO = new Article_Rep_ReportVO();

		article_Rep_ReportVO.setArt_rep_rpt_no(art_rep_rpt_no);
		article_Rep_ReportVO.setMbr_no(mbr_no);
		article_Rep_ReportVO.setArt_rep_no(art_rep_no);
		article_Rep_ReportVO.setRpt_cont(rpt_cont);
		article_Rep_ReportVO.setRpt_time(rpt_time);
		article_Rep_ReportVO.setProc_stat(proc_stat);
		dao.update(article_Rep_ReportVO);

		return article_Rep_ReportVO;
	}

	public void deleteArticle_Rep_Report(Integer art_rep_rpt_no) {
		dao.delete(art_rep_rpt_no);
	}

	public Article_Rep_ReportVO getOneArticle_Report(Integer art_rep_rpt_no) {
		return dao.findByPrimaryKey(art_rep_rpt_no);
	}

	public List<Article_Rep_ReportVO> getAll() {
		return dao.getAll();
	}
}
