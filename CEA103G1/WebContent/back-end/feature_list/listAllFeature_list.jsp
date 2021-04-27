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
<title>�S���</title>
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
		<h1>�S��M��</h1>
		<h3>
			<a href="<%=request.getContextPath()%>/back-end/select_page.jsp">�^����</a>
		</h3>
	</div>
	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>�S��s��</th>
			<th id="name">�S��W��</th>
			<th colspan="2">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/feature_list/feature_list.do">
					<b>��J�S��W��:</b> <input type="text" name="camp_fl_name"> <input
						type="hidden" name="action" value="getOne_For_Display"> <input
						type="submit" value="�e�X">
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
						<input type="submit" value="�ק�"> <input type="hidden"
							name="camp_fl_no" value="${feature_listVO.camp_fl_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/feature_list/feature_list.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="camp_fl_no" value="${feature_listVO.camp_fl_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td>�s�W</td>
			<td colspan="3">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/feature_list/feature_list.do"
					name="form1">

					�W��: <input type="TEXT" name="camp_fl_name" size="45"
						value="<%=(feature_listVO == null) ? "" : feature_listVO.getCamp_fl_name()%>" />

					<input type="hidden" name="action" value="insert"> <input
						type="submit" value="�e�X�s�W">
				</FORM>
			</td>
		</tr>

	</table>
	<%@ include file="page2.file"%>

</body>
</html>