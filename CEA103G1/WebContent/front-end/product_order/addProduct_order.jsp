<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
	Product_orderVO product_orderVO = (Product_orderVO) request.getAttribute("product_orderVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ӫ~�q��s�W - addProduct_order.jsp</title>

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
		 <h3>�ӫ~�q��s�W - addProduct_order.jsp</h3></td><td>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product_order/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order/product_order.do" name="form1">
<table>
	<tr>
		<td>�|���s��:</td>
		<td><input type="TEXT" name="mbr_no" size="45" 
			 value="<%= (memberVO==null)? "" : memberVO.getName()%>" /></td>
	</tr>
	<tr>
		<td>�U�q�ɶ�:</td>
		<td><input type="TEXT" name="prod_ord_time" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getProd_ord_time()%>" /></td>
	</tr>
	<tr>
		<td>�U�q�ӫ~:</td>
		<td><%= productVO.getProd_name() %></td>
	</tr>
	<tr>
		<td>�q���`���B:</td>
		<td><input type="TEXT" name="prod_ord_sum" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getProd_ord_sum()%>" /></td>
	</tr>
	<tr>
		<td>�ϥ��I��:</td>
		<td><input type="TEXT" name="used_pt" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getUsed_pt()%>" /></td>
	</tr>
	<tr>
		<td>�B�e�覡:</td>
		<td><input type="TEXT" name="ship_meth" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getMbr_no()%>" /></td>
	</tr>
	<tr>
		<td>�I�ڤ覡:</td>
		<td><input type="TEXT" name="pay_meth" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getMbr_no()%>" /></td>
	</tr>
	<tr>
		<td>�B�e�a�}_����:</td>
		<td><input type="TEXT" name="ship_cty" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getMbr_no()%>" /></td>
	</tr>
	<tr>
		<td>�B�e�a�}_�ϰ�:</td>
		<td><input type="TEXT" name="ship_dist" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getMbr_no()%>" /></td>
	</tr>
	<tr>
		<td>�B�e�a�}:</td>
		<td><input type="TEXT" name="ship_add" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getMbr_no()%>" /></td>
	</tr>
	<tr>
		<td>�o���Φ�:</td>
		<td><input type="TEXT" name="receipt" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getMbr_no()%>" /></td>
	</tr>
	<tr>
		<td>�q��Ƶ�:</td>
		<td><input type="TEXT" name="rmk" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getMbr_no()%>" /></td>
	</tr>

</table>
<br>

<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>

</body>

</html>