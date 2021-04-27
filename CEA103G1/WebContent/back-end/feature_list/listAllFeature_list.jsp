<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.feature_list.model.*"%>

<%
	Feature_ListService feature_listSvc = new Feature_ListService();
	List<Feature_ListVO> list = feature_listSvc.getAll();
	pageContext.setAttribute("list", list);
	Feature_ListVO feature_listVO = (Feature_ListVO) request.getAttribute("feature_listVO");
%>

<!DOCTYPE html>
<html>
<head>
<title>特色表</title>
<style>
table, th, td {
	border: 1px solid black;
	background-color: white;
	text-align: center;
}
#name{
    width:250px;
}
div{
    background-color: lightgreen;
}
</style>
</head>
<body>
	<div>
		<h1>特色清單</h1>
		<h3>
			<a href="<%=request.getContextPath()%>/back-end/select_page.jsp">回首頁</a>
		</h3>
	</div>
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
			<th>特色編號</th>
			<th id="name">特色名稱</th>
			<th colspan="2">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/feature_list/feature_list.do">
					<b>輸入特色名稱:</b> <input type="text" name="camp_fl_name"> <input
						type="hidden" name="action" value="getOne_For_Display"> <input
						type="submit" value="送出">
				</FORM>
			</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="feature_listVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${feature_listVO.camp_fl_no}</td>
				<td>${feature_listVO.camp_fl_name}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/feature_list/feature_list.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="camp_fl_no" value="${feature_listVO.camp_fl_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/feature_list/feature_list.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="camp_fl_no" value="${feature_listVO.camp_fl_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td>新增</td>
			<td colspan="3">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/feature_list/feature_list.do"
					name="form1">

					名稱: <input type="TEXT" name="camp_fl_name" size="45"
						value="<%=(feature_listVO == null) ? "" : feature_listVO.getCamp_fl_name()%>" />

					<input type="hidden" name="action" value="insert"> <input
						type="submit" value="送出新增">
				</FORM>
			</td>
		</tr>

	</table>
	<%@ include file="page2.file"%>

</body>
</html>