<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.product.model.*"%>

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品修改 - update_Product_input.jsp</title>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

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
		 <h3>商品修改 - update_Product_input.jsp</h3>
		 <h4><a href="${pageContext.request.contextPath}/front-end/product/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0">	</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" name="form1">
<input type="reset" value="重置">
<input type="button" id="blank" value="清空">
<table>
	<tr>
		<td>商品分類編號:</td>	
		<td><input type="TEXT" name="prod_cat_no" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_cat_no()%>" /></td>
	</tr>
	<tr>
		<td>商品狀態:</td>
		<td id="prod_stat">
			<input type="radio" name="prod_stat" value="0">下架<br>
			<input type="radio" name="prod_stat" value="1">上架<br> 
		</td>
	</tr>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="prod_name" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_name()%>" /></td>
	</tr>
	<tr>
		<td>商品價格:</td>
		<td><input type="TEXT" name="prod_pc" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_pc()%>" /></td>
	</tr>
	<tr>
		<td>商品庫存:</td>
		<td><input type="TEXT" name="prod_stg" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_stg()%>" /></td>
	</tr>
	<tr>
		<td>商品資訊:</td>
		<td><input type="TEXT" name="prod_info" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_info()%>" /></td>
	</tr>
	<tr>
		<td>商品品牌:</td>
		<td><input type="TEXT" name="prod_bnd" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_bnd()%>" /></td>
	</tr>
	<tr>
		<td>商品顏色:</td>
		<td><input type="TEXT" name="prod_clr" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_clr()%>" /></td>
	</tr>
	<tr>
		<td>商品大小:</td>
		<td><input type="TEXT" name="prod_size" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_size()%>" /></td>
	</tr>
	<tr>
		<td>運送方式:</td>
		<td>
			<select id="ship_meth" name="ship_meth">
			    <option disabled value="">請選擇運送方式</option>
			    <option value="0">不限運送方式</option>
			    <option value="1">限宅配</option>
			    <option value="2">限超商取貨</option>
			</select>
		</td>
	</tr>

</table>

<script>
	var prod_stat = `<%=productVO.getProd_stat()%>`;
	$("#prod_stat").find(":input").each(function() {
		if($(this).val()===prod_stat){
			$(this).prop("checked",true);
		}
	});
	
	$("#blank").click(function(){
		$("td > :input").val(null);
	});
</script>


<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="prod_no" value="${productVO.prod_no}">
<input type="submit" value="送出修改"></FORM>

</body>

</html>