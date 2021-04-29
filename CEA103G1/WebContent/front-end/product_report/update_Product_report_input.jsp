<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_report.model.*"%>

<%
  Product_reportVO product_reportVO = (Product_reportVO) request.getAttribute("product_reportVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ӫ~�^���ק� - update_Product_report_input.jsp</title>

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
		 <h3>�ӫ~�^���ק� - update_Product_report_input.jsp</h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product_report/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0">	</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_report/product_report.do" name="form1">
<table>
	<tr>
		<td>�ӫ~�^���s��:<font color=red><b>*</b></font></td>
		<td><%=product_reportVO.getProd_rpt_no()%></td>
	</tr>
	<tr>
		<td>�|���s��:</td>
		<td><input type="TEXT" name="ename" size="45" value="<%=product_reportVO.getMbr_no()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="prod_rpt_no" value="<%=product_reportVO.getProd_rpt_no()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>

</html>