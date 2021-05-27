<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article_reply.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
  	Article_ReplyVO article_replyVO = (Article_ReplyVO) request.getAttribute("article_replyVO"); //Article_ReplyServlet.java(Concroller), 存入req的articleVO物件
	pageContext.setAttribute("article_replyVO", article_replyVO);
%>



<html>
<head>
<title>單一留言資料 - listOneArticle_Reply.jsp</title>

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

<c:if test="${article_replyVO.rep_stat==1}">
<h1>本篇留言已經被刪除囉!!</h1>
</c:if>

<c:if test="${article_replyVO.rep_stat==0}">
<table>

    <tr>
		<td>留言者</td>
		<td><%=article_replyVO.getMbr_no()%></td>
		
   </tr>
   <tr>
		<td>留言內容</td>
		<td><%=article_replyVO.getRep_cont()%></td>
  </tr>
   <tr>
		<td>留言時間</td>
		<td><fmt:formatDate value="<%=article_replyVO.getRep_time()%>" pattern="MM月dd日  HH:mm"/></td>
  </tr>

</table>
</c:if>
</body>
</html>