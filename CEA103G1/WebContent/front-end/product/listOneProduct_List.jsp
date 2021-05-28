<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*" %>
<%@ page import="com.product.model.*" %>

<%
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<title>�ӫ~�U�q</title>

<%@ include file="/part-of/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>

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

<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />

<div>�i�ӫ~�����W�١j  ${product_categorySvc.getOneProduct_category(productVO.prod_cat_no).prod_cat_name}</div>
<div>�i�ӫ~�W�١j ${productVO.prod_name}</div>
<div>�i�ӫ~����j${productVO.prod_pc}</div>
<hr />
<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_order/product_order.do" style="margin-bottom: 0px;">
	<div>
		<div>�i�|���m�W�j${memberVO.name}</div>
		<div>�i�ʶR�ƶq�j
			<input type="button" value="+" onclick="this.nextElementSibling.value++"/>
    		<input type="text" name="prod_amt" id="prod_amt" value="0" readonly="readonly" size="4"/>
    		<input type="button" value="-" onclick="(this.previousElementSibling.value<1 ? 0 : this.previousElementSibling.value--)"/>
		</div>
<!--	<div>�i�p�p�j
			<input type="hidden" value="prod_unit_pc"/>${productVO.prod_pc}
		</div> -->
		<div>�i�ϥ��I�ơj
			<input type="button" value="+" onclick="this.nextElementSibling.value++"/>
    		<input type="text" name="used_pt" id="used_pt" value="0" readonly="readonly" size="4"/>
    		<input type="button" value="-" onclick="(this.previousElementSibling.value<1 ? 0 : this.previousElementSibling.value--)"/>
		</div>
		<div>�i�B�e�覡�j
		<c:if test="${productVO.ship_meth==0}">
		<c:out value="�п�ܹB�e�覡" />
			<input type="radio" name="ship_meth" value="1">�v�t
			<input type="radio" name="ship_meth" value="2">�W�Ө��f
		</c:if>
		<c:if test="${productVO.ship_meth==1}">
			<c:out value="���v�t" />
			<input type="hidden" name="ship_meth" value="1">
		</c:if>
		<c:if test="${productVO.ship_meth==2}">
			<c:out value="���W�Ө��f" />
			<input type="hidden" name="ship_meth" value="2">
		</c:if>
		</div>
		
		<div>�i�B�e�����j<input type="text" name="ship_cty" value=" "></div>
		<div>�i�B�e�ϰ�j<input type="text" name="ship_dist" value=" "></div>
		<div>�i�B�e�a�}�j<input type="text" name="ship_cty" value=" "></div>


		<div>
		�i�q��Ƶ��j
		<textarea id="rmk" cols="50" rows="5">
�п�J:
		</textarea>
		</div>
		
		<script>
			var amt = $('#prod_amt').val();
			var rmk = $('#rmk').val()
		</script>
		
		<input type="submit" value="����">
		<input type="hidden" name="mbr_no" value="${memberVO.mbr_no}">
		<input type="hidden" name="prod_ord_stat" value="0">
		<input type="hidden" name="prod_ord_sum" value="${productVO.prod_pc}">
		
		<input type="hidden" name="pay_meth" value="1">
		<input type="hidden" name="receipt" value="1">
		<input type="hidden" name="rmk"	value="ttt">
		
		<input type="hidden" name="prod_no" value="${productVO.prod_no}">
		<input type="hidden" name="prod_unit_pc" value="${productVO.prod_pc}">
		<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
		<input type="hidden" name="action"	value="insert">
	</div>
</FORM>

</body>
</html>