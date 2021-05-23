package com.article_report.model;
import java.util.List;
import java.util.Map;
public interface Article_ReportDAO_Interface {
	public void insert(Article_ReportVO article_ReportVO);

	public void update(Article_ReportVO article_ReportVO);

	public void delete(Integer art_rpt_no);

	public Article_ReportVO findByPrimaryKey(Integer art_rpt_no);

	public List<Article_ReportVO> getAll();
	
	public List<Article_ReportVO> get_wait_for_judge();
	
	public List<Article_ReportVO> get_already_judge();
}
