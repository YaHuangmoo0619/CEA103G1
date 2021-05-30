<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.article_report.model.*" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="redis.clients.jedis.Jedis"%>
<%
	Jedis jedis = new Jedis("localhost", 6379);
	jedis.auth("123456");
	jedis.select(5);
%> 
<%
Map<Integer,String> judge_record = new HashMap<>();
Article_ReportService article_reportSvc = new Article_ReportService();
List<Article_ReportVO> list = article_reportSvc.getAll();
for(Article_ReportVO element : list){ //���C�@�����|VO
	if(jedis.exists("article_report:"+element.getArt_rpt_no()+":judge_record")){//�d�ݬO�_���ӵ����|���f�֬����A�p�G��
		judge_record.put(element.getArt_rpt_no(), jedis.get("article_report:"+element.getArt_rpt_no()+":judge_record"));//�N�N�B�����G��Jjudge_record
	}
}

pageContext.setAttribute("list", list);
request.setAttribute("judge_record", judge_record);
%>

<!DOCTYPE html>

<jsp:useBean id="articleDAO" scope="page" class="com.article.model.ArticleDAO" />
<html>
<head>
<meta charset="BIG5">
<title>�Q���|�峹�`��</title>
<link rel="stylesheet"	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<%@ include file="/part-of/partOfCampion_backTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_backLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
<style>

.board_manage{
margin: 20px 20px 20px 20px;
font-size:30px;
display:inline-block;
}

body {
  font-family: "Open Sans", sans-serif;
  line-height: 1.25;
  
  background-color: #4e5452;
}

.article_title{
	color: #007bff;
    text-decoration: none;
}

.article_title:hover{
background-color: transparent;
color:red;
}

table {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: 0;
  padding: 0;
  width: 100%;
  table-layout: fixed;
}

table caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

table tr {
  background-color: #f8f8f8;
  border: 1px solid #ddd;
  padding: .35em;
}

table th,
table td {
  padding: .625em;
  text-align: center;
}

table th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
}

@media screen and (max-width: 600px) {
  table {
    border: 0;
  }

  table caption {
    font-size: 1.3em;
  }
  
  table thead {
    border: none;
    clip: rect(0 0 0 0);
    height: 1px;
    margin: -1px;
    overflow: hidden;
    padding: 0;
    position: absolute;
    width: 1px;
  }
  
  table tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: .625em;
  }
  
  table td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: .8em;
    text-align: right;
  }
  
  table td::before {
    /*
    * aria-label has no advantage, it won't be read inside a table
    content: attr(aria-label);
    */
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
  }
  
  table td:last-child {
    border-bottom: 0;
  }
}
</style>
</head>
<body>
<%@ include file="/part-of/partOfCampion_backTop_body.txt"%>
	<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
	<div class="container">
		<div class="row">
			<div class="left col-3">
				<%@ include file="/part-of/partOfCampion_backLeft_body.txt"%></div>
			<div class="right col-9">
<div class="board_manage" style="color:white" >�Q���|�峹�޲z</div>
<a class="board_manage" href="<%=request.getContextPath()%>/back-end/board_class/listAllBoard_class.jsp">�ݪO�޲z</a>
	  <table>
  <thead>
    <tr>
      <th scope="col">���|�s��</th>
      <th scope="col">�峹���D</th>
      <th scope="col">���|�|���s��</th>
      <th scope="col">���|�z��</th>
      <th scope="col">���|�ɶ�</th>
      <th scope="col">�B�z���A</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="article_reportVO" items="${list}">
    <tr>
      <td data-label="ART_RPT_NO">${article_reportVO.art_rpt_no}</td>
      <td data-label="ART_NO">
      <c:forEach var="articleVO" items="${articleDAO.all_Back}">
			<c:if test="${articleVO.art_no==article_reportVO.art_no}">
	                    <a class=article_title href="<%=request.getContextPath()%>/article/article.do?art_no=${articleVO.art_no}&action=getOne_From5">${articleVO.art_title}</a>
            </c:if>
		</c:forEach>
	  </td>
      <td data-label="MBR_NO">${article_reportVO.mbr_no}</td>
      <td data-label="RPT_CONT">${article_reportVO.rpt_cont}</td>
      <td data-label="RPT_TIME"><fmt:formatDate value="${article_reportVO.rpt_time}" pattern="MM��dd��  HH:mm"/></td>
      <c:if test="${article_reportVO.proc_stat==0}"><td data-label="PROC_STAT">���f��</td></c:if>
      <c:if test="${article_reportVO.proc_stat==0}"><td data-label="JUDGE"><div class=art_rpt_no style="display:none">${article_reportVO.art_rpt_no}</div><div style="display:none">${article_reportVO.art_no}</div><button class="go_judge_modal btn btn-primary">�f��</button></td></c:if>
      <c:if test="${article_reportVO.proc_stat==1}"><td data-label="PROC_STAT">�w�f�֡A�ثe�B��:<br>${judge_record[article_reportVO.art_rpt_no]}</td></c:if>
	  <c:if test="${article_reportVO.proc_stat==1}"><td data-label="JUDGE_CHANGE"><div class=art_rpt_no style="display:none">${article_reportVO.art_rpt_no}</div><div style="display:none">${article_reportVO.art_no}</div><button class="go_judge_change_modal btn btn-primary">�ܧ�B��</button></td></c:if>
      
    </tr>
    
    </c:forEach>
  </tbody>
</table>
</div>
</div>
</div>
	<c:if test="${openModal!=null}">

		<div class="modal fade" id="listAllArticle_Report_modal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content" >
					<div class="modal-body">
						<!-- =========================================�H�U����listOneArticle.jsp�����e========================================== -->
						<jsp:include page="../../front-end/article/listOneArticle.jsp" />
						
						<!-- =========================================�H�W����listOneArticle.jsp�����e========================================== -->
					</div>
				</div>
			</div>
		</div>

	</c:if>
	
	
			<div class="modal fade" id="judgement" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5>�峹���|�f��</h5>
      </div>
      <form METHOD="post" ACTION="/CEA103G1/article_report/article_report.do" name="form2" autocomplete>
      <div class="modal-body">
      
                	<div class="form-check">
  				<input class="form-check-input" type="radio" name="judgeRadios" value="1">
  					<label class="form-check-label" for="exampleRadios1">
    					���B��
  					</label>
	   		 </div>
	   		                 	<div class="form-check">
  				<input class="form-check-input" type="radio" name="judgeRadios" value="2">
  					<label class="form-check-label" for="exampleRadios1">
    					�ȧR��P�H�Hĵ�i
  					</label>
	   		 </div>
	   		 	   		                 	<div class="form-check">
  				<input class="form-check-input" type="radio" name="judgeRadios" value="3">
  					<label class="form-check-label" for="exampleRadios1">
    					�R��øT�o�峹�C�ѻP�H�Hĵ�i
  					</label>
	   		 </div>
	   		 	   		                 	<div class="form-check">
  				<input class="form-check-input" type="radio" name="judgeRadios" value="4">
  					<label class="form-check-label" for="exampleRadios1">
    					�R��øT�o�峹�Q���ѻP�H�Hĵ�i
  					</label>
	   		 </div>
	   		 	   		                 	<div class="form-check">
  				<input class="form-check-input" type="radio" name="judgeRadios" value="5">
  					<label class="form-check-label" for="exampleRadios1">
    					�R��øT�o�峹�T�Q�ѻP�H�Hĵ�i
  					</label>
	   		 </div>
					<input type="hidden" name="action" value="judge">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary close_modal_judgement" data-bs-dismiss="modal">����</button>
        <button type="submit" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/front-end/member/login.jsp'">�e�X�f��</button>
      </div>
      </form>
    </div>
  </div>
</div>

			<div class="modal fade" id="judgement_change" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5>�f�ֵ��G�ܧ�</h5>
      </div>
      <form METHOD="post" ACTION="/CEA103G1/article_report/article_report.do" name="form3" autocomplete>
      <div class="modal-body">
      
                	<div class="form-check">
  				<input class="form-check-input" type="radio" name="judge_changeRadios" value="1">
  					<label class="form-check-label" for="exampleRadios1">
    					���B��
  					</label>
	   		 </div>
	   		                 	<div class="form-check">
  				<input class="form-check-input" type="radio" name="judge_changeRadios" value="2">
  					<label class="form-check-label" for="exampleRadios1">
    					�ȧR��P�H�Hĵ�i
  					</label>
	   		 </div>
	   		 	   		                 	<div class="form-check">
  				<input class="form-check-input" type="radio" name="judge_changeRadios" value="3">
  					<label class="form-check-label" for="exampleRadios1">
    					�R��øT�o�峹�C�ѻP�H�Hĵ�i
  					</label>
	   		 </div>
	   		 	   		                 	<div class="form-check">
  				<input class="form-check-input" type="radio" name="judge_changeRadios" value="4">
  					<label class="form-check-label" for="exampleRadios1">
    					�R��øT�o�峹�Q���ѻP�H�Hĵ�i
  					</label>
	   		 </div>
	   		 	   		                 	<div class="form-check">
  				<input class="form-check-input" type="radio" name="judge_changeRadios" value="5">
  					<label class="form-check-label" for="exampleRadios1">
    					�R��øT�o�峹�T�Q�ѻP�H�Hĵ�i
  					</label>
	   		 </div>
					<input type="hidden" name="action" value="judge_change">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary close_modal_judgement_change" data-bs-dismiss="modal">����</button>
        <button type="submit" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/front-end/member/login.jsp'">�e�X�f��</button>
      </div>
      </form>
    </div>
  </div>
</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>	
	<script>
	
	$("#listAllArticle_Report_modal").modal({
		show : true
	});
	
	  $(".go_judge_modal").click(function(){
		  var art_no = $(this).prev().text();
		  var art_rpt_no = $(this).prev().prev().text();
		  $(".modal-footer").append('<input type="hidden" name="art_no" value="'+art_no+'" />');
		  $(".modal-footer").append('<input type="hidden" name="art_rpt_no" value="'+art_rpt_no+'" />');
		  $('#judgement').modal('show');
	  })
	  
	  	  $(".go_judge_change_modal").click(function(){
		  var art_no = $(this).prev().text();
		  var art_rpt_no = $(this).prev().prev().text();
		  $(".modal-footer").append('<input type="hidden" name="art_no" value="'+art_no+'" />');
		  $(".modal-footer").append('<input type="hidden" name="art_rpt_no" value="'+art_rpt_no+'" />');
		  $('#judgement_change').modal('show');
	  })
	  
	  
	  $(".close_modal_judgement").click(function(){
		  $('#judgement').modal('hide');
	  })

	  $(".close_modal_judgement_change").click(function(){
		  $('#judgement_change').modal('hide');
	  })
	</script>
</body>
</html>