<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_order.model.*"%>

<%
  Product_orderVO product_orderVO = (Product_orderVO) request.getAttribute("product_orderVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ӫ~�q��ק� - update_Product_order_input.jsp</title>

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
		 <h3>�ӫ~�q��ק� - update_Product_order_input.jsp</h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product_order/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0">	</a></h4>
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

<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order/product_order.do" name="form1">
<table>
	<tr>
		<td>�ӫ~�q��s��:<font color=red><b>*</b></font></td>
		<td>${product_orderVO.prod_ord_no}</td>
	</tr>
	<tr>
		<td>�|���s��:<font color=red><b>*</b></font></td>
		<td>${product_orderVO.mbr_no}</td>
	</tr>
<tr>
		<td>�U�q�ɶ�:</td>	
		<td><input type="TEXT" name="prod_cat_no" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getProd_ord_time()%>" /></td>
	</tr>
	<tr>
		<td>�q�檬�A:</td>
		<td>
			<select id="prod_stat" name="prod_stat">
				<option value="0">���I��</option>
				<option value="1">�w�I��</option>
				<option value="2">�X�f��</option> 
				<option value="3">�w���f</option> 
				<option value="4">�����f</option>
			</select>
		</td>
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
		<td>
			<select id="ship_meth" name="ship_meth">
			    <option disabled value="">�п�ܹB�e�覡</option>
			    <option value="0">�����B�e�覡</option>
			    <option value="1">���v�t</option>
			    <option value="2">���W�Ө��f</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>�I�ڤ覡:</td>
		<td><input type="TEXT" name="pay_meth" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getPay_meth()%>" /></td>
	</tr>
	<tr>
		<td>�B�e�a�}_����:</td>
		<td><input type="TEXT" name="ship_cty" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getShip_cty()%>" /></td>
	</tr>
	<tr>
		<td>�B�e�a�}_�ϰ�:</td>
		<td><input type="TEXT" name="ship_dist" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getShip_dist()%>" /></td>
	</tr>
	<tr>
		<td>�B�e�a�}:</td>
		<td><input type="TEXT" name="ship_add" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getShip_add()%>" /></td>
	</tr>
	<tr>
		<td>�o���Φ�:</td>
		<td><input type="TEXT" name="receipt" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getReceipt()%>" /></td>

	</tr>
	<tr>
		<td>�q��Ƶ�:</td>
		<td><input type="TEXT" name="rmk" size="45" 
			 value="<%= (product_orderVO==null)? "" : product_orderVO.getRmk()%>" /></td>

	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="prod_ord_no" value="<%=product_orderVO.getProd_ord_no()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>

</html>