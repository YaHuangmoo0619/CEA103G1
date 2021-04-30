<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.member_rank.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  Member_rankVO member_rankVO = (Member_rankVO) request.getAttribute("member_rankVO"); //Member_rankServlet.java(Concroller), 存入req的member_rankVO物件
%>

<html>
<head>
<title>會員等級資料 - listOneMember_rank.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>會員等級資料 - ListOneMember_rank.jsp</h3>
		 <h4><a href="/CEA103G1/back-end/member_rank/select_page.jsp"><img src="/CEA103G1/images/logo.png" width="50" height="50" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員等級編號</th>
		<th>會員等級名稱</th>
		<th>點數回饋比例</th>
	</tr>
	<tr>
		<td><%=member_rankVO.getRank_no()%></td>
		<td><%=member_rankVO.getRank_name()%></td>
		<td><%=member_rankVO.getPt_rwd_rto()%></td>
	</tr>
</table>

</body>
</html>