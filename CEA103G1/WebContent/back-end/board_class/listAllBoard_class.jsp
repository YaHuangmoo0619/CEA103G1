<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.board_class.model.*"%>

<%
    Board_ClassService board_classSvc = new Board_ClassService();
    List<Board_ClassVO> list = board_classSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有看板資料 - listAllBoard_Class.jsp</title>

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
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>所有看板資料 - listAllBoard_Class.jsp</h3>
		 				<h4>
					<a href="/CEA103G1/back-end/board_class/select_page.jsp"><img src="/CEA103G1/images/Campion.png"
						width="100" height="100" border="0"></a>
				</h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<ul>
  <li><a href='/CEA103G1/back-end/board_class/addBoard_class.jsp'>新增看板</a></li>
</ul>


<table>
	<tr>
		<th>看板編號</th>
		<th>看板名稱</th>
		<th>看板狀態</th>
	</tr>
	<c:forEach var="board_classVO" items="${list}">
		
		<tr>
			<td>${board_classVO.bd_cl_no}</td>
			<td>${board_classVO.bd_name}</td>
			<td>${board_classVO.bd_stat}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/board_class/board_class.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="bd_cl_no"  value="${board_classVO.bd_cl_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/board_class/board_class.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="bd_cl_no"  value="${board_classVO.bd_cl_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>