<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.board_class.model.*"%>
<%
  Board_ClassVO board_classVO = (Board_ClassVO) request.getAttribute("board_classVO"); 
%>


<html>
<head>
<title>看板資料 - listOneBoard_Class.jsp</title>

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

<table id="table-1">
	<tr><td>
		 <h3>看板資料 - listOneBoard_Class.jsp</h3>
		 				<h4>
					<a href="/CEA103G1/back-end/board_class/select_page.jsp"><img src="/CEA103G1/images/Campion.png"
						width="100" height="100" border="0"></a>
				</h4>
	</td></tr>
</table>

<table>
	<tr>
		<td>看板編號</td>
		<td><%=board_classVO.getBd_cl_no()%></td>
   </tr>
	<tr>
		<td>看板名稱</td>
		<td><%=board_classVO.getBd_name()%></td>
   </tr>
    <tr>
		<td>看板狀態</td>
		<td><%=board_classVO.getBd_stat()%></td>
   </tr>
</table>

</body>
</html>