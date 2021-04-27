<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article_reply.model.*"%>

<%
  Article_ReplyVO article_replyVO = (Article_ReplyVO) request.getAttribute("article_replyVO"); //Article_ReplyServlet.java(Concroller), �s�Jreq��articleVO����
%>



<html>
<head>
<title>��@�d����� - listOneArticle_Reply.jsp</title>

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

<table id="table-1">
	<tr><td>
		 <h3>��@�d����� - listOneArticle_Reply.jsp</h3>
		 <h4><a href="/CEA103G1/back-end/article_reply/select_page.jsp"><img src="/CEA103G1/images/Campion.png"
						width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<td>�峹�d���s��</td>
		<td><%=article_replyVO.getArt_rep_no()%></td>
   </tr>
	<tr>
		<td>�峹�s��</td>
		<td><%=article_replyVO.getArt_no()%></td>
   </tr>
    <tr>
		<td>�|���s��</td>
		<td><%=article_replyVO.getMbr_no()%></td>
   </tr>
   <tr>
		<td>�d�����e</td>
		<td><%=article_replyVO.getRep_cont()%></td>
  </tr>
   <tr>
		<td>�d���ɶ�</td>
		<td><%=article_replyVO.getRep_time()%></td>
  </tr>
   <tr>
		<td>�d�����A</td>
		<td>
		    <c:if test="${article_replyVO.getRep_stat() == 0}">���</c:if>
		    <c:if test="${article_replyVO.getRep_stat() == 1}">�����</c:if>
	   </td>
   </tr>
</table>

</body>
</html>