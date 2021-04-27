<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>

<%@ page import="java.sql.Timestamp"%>

<%
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�s�W�峹 - addArticle.jsp</title>

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

	<table id="table-1">
		<tr>
			<td>
				<h3>�峹��Ʒs�W - addArticle.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="/CEA103G1/back-end/article/select_page.jsp"><img src="/CEA103G1/images/Campion.png"
						width="100" height="100" border="0"></a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>�峹��Ʒs�W:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="/CEA103G1/article/article.do" name="form1" autocomplete>
		<table>
			<jsp:useBean id="bd_clSvc" scope="page"
				class="com.board_class.model.Board_ClassService" />
			<tr>
				<td>�o��ݪO:<font color=red><b>*</b></font></td>
				<td><select size="1" name="bd_cl_no">
						<c:forEach var="board_classVO" items="${bd_clSvc.all}">
							<option value="${board_classVO.bd_cl_no}"
								${(articleVO.bd_cl_no==board_classVO.bd_cl_no)? 'selected':'' }>${board_classVO.bd_name}
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>�o���:</td>
				<td><input type="TEXT" name="mbr_no" size="45" 
				value="<%= (articleVO==null)? "" : articleVO.getMbr_no()%>"/></td>
			</tr>
			<tr>
				<td>�峹���D:</td>
				<td><input type="TEXT" name="art_title" size="45" 
				value="<%= (articleVO==null)? "" : articleVO.getArt_title()%>"/></td>
			</tr>
			<tr>
				<td>�峹���e:</td>
				<td><input type="TEXT" name="art_cont" size="45" 
				value="<%= (articleVO==null)? "" : articleVO.getArt_cont()%>"/></td>
			</tr>

			<tr>
				<td>�g��:</td>
				<td><input type="TEXT" name="likes" size="45" 
				value="<%= (articleVO==null)? "" : articleVO.getLikes()%>"/></td>
			</tr>

			<tr>
				<td>�峹���A:</td>
<!-- 				<td><input type="TEXT" name="art_stat" size="45"  -->
<%-- 				value="<%= (articleVO==null)? "" : articleVO.getArt_stat()%>"/></td> --%>
				<td>
				<label>���<input type="radio" name="art_stat" value="0"/></label>
				<label>�����<input type="radio" name="art_stat" value="1"/></label>
				</td>

			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="�e�X�s�W">
	</FORM>
</body>
</html>