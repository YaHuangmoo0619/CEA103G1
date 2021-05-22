<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.product.model.*"%>
<jsp:useBean id="product_pictureSvc" class="com.product_picture.model.Product_pictureService"/>
<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>商品修改 - update_Product_input.jsp</title>

<%@ include file="/part-of/partOfCampion_backTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_backLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>

<style>
body{
	background-color: #4e5452;
	color: #4e5452;
}
div.left{
	margin-top: 20px;
}
div.right{
	background-color: #fff;
	margin-top: 40px;
	padding: 50px 50px;
	border-radius: 5px;
}
a.content{
	color: #80c344;
	font-size: 0.6em;
}
a.content:hover {
	color: #4B7F52;
}

table{
	width: 700px;
	margin: 30px auto;
	border: 1px solid #4e5452;
}
th, td{
	text-align: center;
	border: 1px solid #4e5452;
	padding: 10px 15px;
}
td.function{
	text-align: justify;	
}
label.spotlight{
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}
form{
	text-align: center;
}
textarea{
	resize: none;
}
input.confirm{
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
	margin: 0px 10px;
}
input.confirm:hover{
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}
</style>
<style>
#container {
	padding: 10px;
	max-width: 250px;
	margin: 0px auto;
}
.align{
	display: inline;
	vertical-align: text-top;
}
#preview, .change{
	margin: 10px 0px;
	
}
img{
	max-width: 100%;
	margin: 10px;
}
.delete{
	display: none;
}
textarea{
	resize: none;
}
</style>

</head>
<body>
<%@ include file="/part-of/partOfCampion_backTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
<div class="container">
	<div class="row">
		<div class= "left col-3">
		<%@ include file="/part-of/partOfCampion_backLeft_body.txt"%></div>
		<div class="right col-9">
			<h2>修改商品資料&nbsp;<a class="content" href="<%=request.getContextPath()%>/back-end/product/listAllProduct_update.jsp">回商品修改列表</a></h2>
			<hr>

<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" name="form1">
<input type="reset" value="重置" class="confirm">
<input type="button" id="blank" value="清空" class="confirm">
<input type="submit" value="送出新增商品" name="addFromUpdate" class="confirm">

<table>
	<tr>
		<td>商品分類:</td>	
		<td id="prod_cat_no">
			<select size="1" name="prod_cat_no">
				<option value="no">請選商品類別</option>
				<c:forEach var="product_categoryVO" items="${product_categorySvc.all}">
					<c:if test="${productVO.getProd_cat_no() == product_categoryVO.getProd_cat_no()}">
						<option value="${product_categoryVO.getProd_cat_no()}" selected>${product_categoryVO.getProd_cat_name()}
					</c:if>
					<c:if test="${productVO.getProd_cat_no() != product_categoryVO.getProd_cat_no()}">
						<option value="${product_categoryVO.getProd_cat_no()}">${product_categoryVO.getProd_cat_name()}
					</c:if>
				</c:forEach>			
			</select>
		</td>
<!-- 		<td><input type="TEXT" name="prod_cat_no" size="45"  -->
<%-- 			 value="<%= (productVO==null)? "" : productVO.getProd_cat_no()%>" /></td> --%>
	</tr>
	<tr>
		<td>商品狀態:</td>
		<td id="prod_stat">
			<input type="radio" name="prod_stat" value="0">下架<br>
			<input type="radio" name="prod_stat" value="1">上架<br> 
		</td>
	</tr>
	<tr>
		<td>商品名稱:<h5 style="color:#80c344;">${errorMsgs.prod_name}</h5></td>
		<td><input type="TEXT" name="prod_name" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_name()%>" /></td>
	</tr>
	<tr>
		<td>商品價格:<h5 style="color:#80c344;">${errorMsgs.prod_pc}</h5></td>
		<td><input type="number" name="prod_pc" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_pc()%>" /></td>
	</tr>
	<tr>
		<td>商品庫存:<h5 style="color:#80c344;">${errorMsgs.prod_stg}</h5></td>
		<td><input type="number" name="prod_stg" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_stg()%>" /></td>
	</tr>
	<tr>
		<td>商品資訊:<h5 style="color:#80c344;">${errorMsgs.prod_info}</h5></td>
		<td><textarea name="prod_info" rows="20" cols="45"><%= (productVO==null)? "" : productVO.getProd_info()%></textarea></td>
	</tr>
	<tr>
		<td>商品品牌:<h5 style="color:#80c344;">${errorMsgs.prod_bnd}</h5></td>
		<td><input type="TEXT" name="prod_bnd" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_bnd()%>" /></td>
	</tr>
	<tr>
		<td>商品顏色:<h5 style="color:#80c344;">${errorMsgs.prod_clr}</h5></td>
		<td><input type="TEXT" name="prod_clr" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_clr()%>" /></td>
	</tr>
	<tr>
		<td>商品大小:<h5 style="color:#80c344;">${errorMsgs.prod_size}</h5></td>
		<td><input type="TEXT" name="prod_size" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_size()%>" /></td>
	</tr>
	<tr>
		<td>運送方式:</td>
		<td>
			<select id="ship_meth" name="ship_meth">
			    <option value="no">請選擇運送方式</option>
			    <option value="0" ${productVO.ship_meth == 0? 'selected':''}>不限運送方式</option>
			    <option value="1" ${productVO.ship_meth == 1? 'selected':''}>限宅配</option>
			    <option value="2" ${productVO.ship_meth == 2? 'selected':''}>限超商取貨</option>
			</select>
		</td>
	</tr>

</table>



<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="prod_no" value="${productVO.prod_no}">
<input type="submit" value="送出修改" class="confirm">
</FORM>
</div>
</div>
</div>

<script>
	var prod_stat = `<%=productVO.getProd_stat()%>`;
	$("#prod_stat").find(":input").each(function() {
		if($(this).val()===prod_stat){
			$(this).prop("checked",true);
		}
	});
	
<%-- 	var prod_cat_no = `<%=productVO.getProd_cat_no()%>`; --%>
// 	$("#prod_cat_no").find(":option").each(function() {
// 		if($(this).val()===prod_cat_no){
// 			$(this).prop("selected",true);
// 		}
// 	});
	
	$("#blank").click(function(){
// 		if($("input[type='text']")||$("input[type='number']")){
// 			$("td > :input").val(null);
// 		}
		$("input[type='text']").val('');
		$("input[type='number']").val('0');
		$("textarea").val('');
		$("select").val('no');
	});
</script>
</body>

</html>