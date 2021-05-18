<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article_reply.model.*"%>

<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.member.model.*" %>

<%int art_no = Integer.parseInt(request.getParameter("art_no")); %>
<%int mbr_no = Integer.parseInt(request.getParameter("mbr_no")); 
	System.out.println("測試");
	System.out.println(mbr_no);
%>
<%
	Article_ReplyVO article_replyVO = (Article_ReplyVO) request.getAttribute("article_replyVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>新增文章留言 - addArticle_Reply.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="/CEA103G1/article_reply/article_reply.do" name="form1" autocomplete >
		<table>
			
			<tr>
				<td>留言內容:</td>
				<td><input type="TEXT" name="rep_cont" size="45" 
				value="<%= (article_replyVO==null)? "" : article_replyVO.getRep_cont()%>"/></td>
			</tr>



			

			
		</table>
		<br>
		<input type="hidden" name="mbr_no" value="<%=mbr_no%>">
		<input type="hidden" name="art_no" value="<%=art_no%>">  
		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="送出新增">
	</FORM>
</body>
</html>