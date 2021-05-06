<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article_reply.model.*"%>


<%int art_no = Integer.parseInt(request.getParameter("art_no")); %>
<%
  Article_ReplyService article_replySvc = new Article_ReplyService();
  List<Article_ReplyVO> list = article_replySvc.getOneArticle_Replies(art_no);
  pageContext.setAttribute("list", list); 
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>listOneArticle_Replies �e�x</title>
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>


<table>
		<tr>
			<th>�峹�d���s��</th>
			<th>�峹�s��</th>
			<th>�|���s��</th>
			<th>�d�����e</th>
			<th>�o��ɶ�</th>

			<th>�g��</th>
			<th>�ק�</th>
			<th>�R��</th>
		</tr>
		
		
<%@ include file="pageforhome.file"%>
		<c:forEach var="article_replyVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

<c:if test="${article_replyVO.getRep_stat() == 0}">
			

			<tr>
				<td>${article_replyVO.art_rep_no}</td>
			    <td>${article_replyVO.art_no}</td>
                <td>${article_replyVO.mbr_no}</td>
                <td>${article_replyVO.rep_cont}</td>
				<td><fmt:formatDate value="${article_replyVO.rep_time}" pattern="MM��dd��  HH:mm"/></td>


			    <td>${article_replyVO.likes}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/article_reply/article_reply.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�">
						<input type="hidden" name="art_rep_no" value="${article_replyVO.art_rep_no}">
						<input type="hidden" name="action" value="getOne_For_Update">
						 <input type="hidden" name="requestURL"	value="<%=request.getParameter("requestURL")%>">
<!-- 						 �NlistOneArticle��URL��article_replyServlet�� -->
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/article_reply/article_reply.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="art_rep_no" value="${article_replyVO.art_rep_no}"> <input
							type="hidden" name="action" value="hide">
					</FORM>
				</td>
			</tr>
			
	</c:if>		
			
		</c:forEach>
		
						
	</table>
	<%@ include file="page2.file"%>

</body>
</html>