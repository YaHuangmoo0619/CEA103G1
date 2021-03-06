<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member_rank.model.*"%>

<%
  Member_rankVO member_rankVO = (Member_rankVO) request.getAttribute("member_rankVO"); //Member_rankServlet.java (Concroller) 存入req的member_rankVO物件 (包括幫忙取出的member_rankVO, 也包括輸入資料錯誤時的member_rankVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員等級資料修改 - update_member_rank_input.jsp</title>

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
		 <h3>會員等級資料修改 - update_member_rank_input.jsp</h3>
		 <h4><a href="/CEA103G1/back-end/member_rank/select_page.jsp"><img src="/CEA103G1/images/logo.png" width="50" height="50" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member_rank/member_rank.do" name="form1">
<table>
	<tr>
		<td>等級編號:<font color=red><b>*</b></font></td>
		<td><%=member_rankVO.getRank_no()%></td>
	</tr>
	<tr>
		<td>等級名稱:</td>
		<td><input type="TEXT" name="rank_name" size="45" value="<%=member_rankVO.getRank_name()%>" /></td>
	</tr>
	<tr>
		<td>點數回饋:</td>
		<td><input type="TEXT" name="pt_rwd_rto" size="45"	value="<%=member_rankVO.getPt_rwd_rto()%>" /></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="rank_no" value="<%=member_rankVO.getRank_no()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</html>