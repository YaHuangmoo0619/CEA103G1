package com.article_reply_report.model;
import java.util.List;
import java.util.Map;
public interface Article_Rep_ReportDAO_Interface {
	public void insert(Article_Rep_ReportVO article_Rep_ReportVO);

	public void update(Article_Rep_ReportVO article_Rep_ReportVO);

	public void delete(Integer art_rep_rpt_no);

	public Article_Rep_ReportVO findByPrimaryKey(Integer art_rep_rpt_no);

	public List<Article_Rep_ReportVO> getAll();
}
