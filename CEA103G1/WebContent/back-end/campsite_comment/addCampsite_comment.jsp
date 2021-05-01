<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member_rank.model.*"%>

<%
  Member_rankVO member_rankVO = (Member_rankVO) request.getAttribute("member_rankVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>營區評論資料新增 - addMember_rank.jsp</title>

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
	<tr><td>
		 <h3>營區評論資料新增 - addMember_rank.jsp</h3></td><td>
		 <h4><a href="/CEA103G1/back-end/member_rank/select_page.jsp"><img src="/CEA103G1/images/logo.png" width="50" height="50" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="/CEA103G1/member_rank/member_rank.do" name="form1">
<table>
	<tr>
		<td>營區評論名稱:</td>
		<td><input type="TEXT" name="rank_name" size="45"
			 value="<%= (member_rankVO==null)? "露營新手" : member_rankVO.getRank_name()%>" /></td>
	</tr>
	<tr>
		<td>營區評論編號:</td>
		<td><input type="TEXT" name="pt_rwd_rto" size="45"
			 value="<%= (member_rankVO==null)? "1" : member_rankVO.getPt_rwd_rto()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>