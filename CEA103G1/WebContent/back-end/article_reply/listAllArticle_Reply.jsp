<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article_reply.model.*"%>

<%
	Article_ReplyService article_replySvc = new Article_ReplyService();
	List<Article_ReplyVO> list = article_replySvc.getAll();
	pageContext.setAttribute("list", list);
%>

<%
	request.setAttribute("vEnter", "\n");
%>

<jsp:useBean id="bd_clSvc" scope="page"
	class="com.board_class.model.Board_ClassService" />
<jsp:useBean id="bd_clDAO" scope="page" class="com.board_class.model.Board_ClassDAO" />

<html>
<head>
<title>所有文章留言</title>

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
	width: 800px;
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

a {
	text-decoration: none
}

a:hover {
	text-decoration: none
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>所有文章留言</h3>
				<h4>
					<a href="/CEA103G1/back-end/article_reply/select_page.jsp"><img src="/CEA103G1/images/Campion.png"
						width="100" height="100" border="0"></a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>文章留言編號</th>
			<th>文章編號</th>
			<th>會員編號</th>
			<th>留言內容</th>
			<th>發表時間</th>
			<th>留言狀態</th>
			<th>留言讚數</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="pageforhome.file"%>
		<c:forEach var="article_replyVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${article_replyVO.art_rep_no}</td>
				<td>${article_replyVO.art_no}</td>  
				<td>${article_replyVO.mbr_no}</td>
				<td>${article_replyVO.rep_cont}</td>			
				<td><fmt:formatDate value="${article_replyVO.rep_time}" pattern="MM月dd日  HH:mm"/></td>
				<td><c:if test="${article_replyVO.getRep_stat() == 0}">顯示</c:if> <c:if
						test="${article_replyVO.getRep_stat() == 1}">不顯示</c:if></td>
				<td>${article_replyVO.likes}</td>		
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/article_reply/article_reply.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="art_rep_no" value="${article_replyVO.art_rep_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/article_reply/article_reply.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="art_rep_no" value="${article_replyVO.art_rep_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>