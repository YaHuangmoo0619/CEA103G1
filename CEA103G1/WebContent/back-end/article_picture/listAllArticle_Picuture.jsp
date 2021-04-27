<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article_picture.model.*"%>

<%
	Article_PictureService article_pictureSvc = new Article_PictureService();
	List<Article_PictureVO> list = article_pictureSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<%
	request.setAttribute("vEnter", "\n");
%>


<html>
<head>
<title> listAll article_pictures </title>

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
				<h3> listAll article_pictures </h3>
				<h4>
					<a href="/CEA103G1/back-end/article_picture/select_page.jsp"><img src="/CEA103G1/images/Campion.png"
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
			<th>文章照片編號</th>
			<th>文章編號</th>
			<th>照片</th>

		</tr>
		<%@ include file="pageforhome.file"%>
		<c:forEach var="article_pictureVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${article_pictureVO.art_pic_no}</td>
				<td>${article_pictureVO.art_no}</td>
				<td><img src="<%=request.getContextPath()%>/article_picture/GetPhoto?art_pic_no=${article_pictureVO.art_pic_no}" style="width:200px"></td>
				
				
				

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/article_picture/article_picture.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="art_pic_no" value="${article_pictureVO.art_pic_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/article_picture/article_picture.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="art_pic_no" value="${article_pictureVO.art_pic_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
<%-- 	<%@ include file="page2.file"%> --%>

</body>
</html>