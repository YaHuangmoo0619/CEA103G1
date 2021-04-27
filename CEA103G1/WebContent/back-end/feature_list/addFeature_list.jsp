<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.feature_list.model.*"%>

<%
  Feature_ListVO feature_listVO = (Feature_ListVO) request.getAttribute("feature_listVO");
%>
<%-- <%= feature_listVO==null %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>新增特色</title>
<style>
  table#table-1 {
	background-color: white;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: white;
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
    border: 0px solid black;
  }
  th, td {
    padding: 1px;
  }
</style>
</head>
<body>
	<table id="table-1">
		<tr>
			<td>
				<h3>特色資料新增</h3>
			</td>
			<td>
				<h4>
					<a href="listAllFeature_list.jsp">回首頁</a>
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/feature_list/feature_list.do" name="form1">
		<table>
			<tr>
				<th>特色名稱:</th>
				<td><input type="TEXT" name="camp_fl_name" size="45"
					value="<%=(feature_listVO == null) ? "吳永志" : feature_listVO.getCamp_fl_name()%>" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>
</html>