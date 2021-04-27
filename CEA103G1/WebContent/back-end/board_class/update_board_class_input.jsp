<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.board_class.model.*"%>

<%@ page import="java.sql.Timestamp"%>


<%
	Board_ClassVO board_classVO = (Board_ClassVO) request.getAttribute("board_classVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>看板資料修改 - update_board_class_input.jsp</title>

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
  div{
  width:1000px;
  height:500px;
  border:3px;
  margin:0px auto;
  }
  
  
  textarea{resize:none;}
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>看板資料修改 - update_board_class_input.jsp</h3>
		 				<h4>
					<a href="/CEA103G1/back-end/board_class/select_page.jsp"><img src="/CEA103G1/images/Campion.png"
						width="100" height="100" border="0"></a>
				</h4>
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

<FORM METHOD="post" ACTION="/CEA103G1/board_class/board_class.do" name="form1">
<table>
	<tr>
		<td>看板編號:<font color=red></font></td>
		<td><%=board_classVO.getBd_cl_no()%></td>
	</tr>
	<tr>
		<td>看板名稱:</td>
		<td><input type="TEXT" name="bd_name" size="45"	value="<%=board_classVO.getBd_name()%>" /></td>
	</tr>

	<tr>
		<td>看板狀態:</td>
		<td><input type="TEXT" name="bd_stat" size="45" value="<%=board_classVO.getBd_stat()%>" /></td>
	</tr>



</table>
<br>

<input type="hidden" name="action" value="update">
<input type="hidden" name="bd_cl_no" value="<%=board_classVO.getBd_cl_no()%>">
<input type="submit" value="送出修改"></FORM>
</body>



</body>
</html>