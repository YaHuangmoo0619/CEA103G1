<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.employee.model.*"%>
<jsp:useBean id="product_pictureSvc" class="com.product_picture.model.Product_pictureService"/>
<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />
<% 
	EmployeeVO employeeVO = (EmployeeVO)session.getAttribute("employeeVO");
	if(employeeVO == null){
		response.sendRedirect(request.getContextPath()+"/campion_back_login.jsp");
		return;
	}
%>

<%
    Product_orderService product_orderSvc = new Product_orderService();
    List<Product_orderVO> list = product_orderSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>所有商品列表 </title>
<%@ include file="/part-of/partOfCampion_backTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_backLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
<style>
body {
	background-color: #4e5452;
	color: #4e5452;
}

div.left {
	margin-top: 20px;
}

div.right {
	background-color: #fff;
	margin-top: 40px;
	padding: 50px 50px;
	border-radius: 5px;
	overflow:scroll;
}

a.content {
	color: #80c344;
	font-size: 0.6em;
}

a.content:hover {
	color: #4B7F52;
}

input.confirm {
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}

input.confirm:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}

#confirmTop:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}

div.forSearch{
	margin: 0 auto;
	width: 70%;
	hieght: 50px;
	position: relative;
}
div.forSearchMore{
	top: 110%;
	left: 15%;
	width: 70%;
	position: absolute;
	background-color: #fff;
	box-shadow: 0 1px 5px 0 #4e5452;
	display: none
}

#mail_cont{
	border-radius:5px;
	background-color:#eee;
	border:none;
	padding:5px 15px;
	width:50%;
}

span{
	 font-size:0.8em;
	 font-weight:444;
	 padding: 7px;
	 background-color: #eee;
	 border-radius:5px;
}
span:hover{
	cursor: pointer;
	background-color: #4e5452;
	color: #eee;
}

label, select, input {
	font-size: 0.8em;
}

table {
	width: 700px;
	margin: 30px auto;
	/* 	border: 1px solid #4e5452; */
}

th, td {
	text-align: left;
	/* 	border: 1px solid #4e5452; */
	padding: 10px 10px;
}

td.function {
	text-align: justify;
}

label.spotlight {
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}

input.change {
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}

input.change:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}

#focus {
	margin-right: -5px;
}

tr {
/* 	border-top: 1px solid #eee; */
	border-bottom: 2px solid #eee;
}

tr:hover {
	box-shadow: 0 1px 5px 0 #4e5452 inset;
 	cursor: pointer;
}

img.inDiv{
	width:50px;
	margin:1px;
}
div.innerDiv{
 	display:inline;
}
hr{
	margin: 3px;
	width: 30%;
	border-color:#80c344;
}
div.L{
	display:inline-block;
	width:6em;
	font-weight: 555;
}
div.R{
	display:inline-block;
	width:4em;
}
div.inTr{
	display:inline-block;
}
label.spotlight{
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}
</style>


</head>
<body>
<%@ include file="/part-of/partOfCampion_backTop_body.txt"%>
	<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
	<div class="container">
		<div class="row">
			<div class="left col-3">
				<%@ include file="/part-of/partOfCampion_backLeft_body.txt"%></div>
			<div class="right col-9">
		 <h3>商品訂單列表&nbsp;
			<a class="content" href="<%=request.getContextPath()%>/back-end/product/listAllProduct.jsp">回到商品列表</a>
			<a href="#focus" class="content">看更新</a>
		</h3>

<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>



<table>

	<c:forEach var="product_orderVO" items="${list}" >
		<tr ${product_orderVO.prod_ord_no==param.prod_ord_no ? 'bgcolor=#eee' : '' }>
			<td style="width:5%;">
				${product_orderVO.prod_ord_no}
				${product_orderVO.prod_ord_no==param.prod_ord_no ? '<a id="focus"></a>' : '' }
			</td>
			<td style="width:35%;">
				<div class="inTr">
					<div class="L">會員編號:</div>
					<div class="R">${product_orderVO.mbr_no}</div>
				</div>
				<div class="inTr">
					<div class="L">訂單總金額:</div>
					<div class="R">${product_orderVO.prod_ord_sum}元</div>
				</div>
				<div class="inTr">
					<div class="L">使用點數:</div>
					<div class="R">${product_orderVO.used_pt}</div>
				</div>
				<div class="inTr">
					<div class="L">運送方式:</div>
					<div class="R">
						<c:if test="${product_orderVO.ship_meth==1}">
							<c:out value="宅配" />
						</c:if>
						<c:if test="${product_orderVO.ship_meth==2}">
							<c:out value="超商取貨" />
						</c:if>
					</div>
				</div>
				<div class="inTr">
					<div class="L">付款方式:</div>
					<div class="R">
						<c:if test="${product_orderVO.pay_meth==0}">
							<c:out value="信用卡" />
						</c:if>
						<c:if test="${product_orderVO.pay_meth==1}">
							<c:out value="匯款" />
						</c:if>
						<c:if test="${product_orderVO.pay_meth==2}">
							<c:out value="超商取貨付款" />
						</c:if>
					</div>
				</div>
				
			</td>
			
			<td style="width:45%;">
				<div class="inTr">
					<div class="L">發票形式:</div>
					<div class="R">
						<c:if test="${product_orderVO.receipt==0}">
							<c:out value="紙本發票" />
						</c:if>
						<c:if test="${product_orderVO.receipt==1}">
							<c:out value="電子發票" />
						</c:if>
						<c:if test="${product_orderVO.receipt==2}">
							<c:out value="發票捐贈" />
						</c:if>
					</div>
				</div>	
				<div class="inTr">
					<div class="L">運送地址:</div>
					<div>
						<div class="innerDiv">${product_orderVO.ship_cty}</div>
						<div class="innerDiv">${product_orderVO.ship_dist}</div>
						<div class="innerDiv">${product_orderVO.ship_add}</div>
					</div>
				</div>
				<div class="inTr">
					<div class="L">下訂時間:</div>
					<div><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${product_orderVO.prod_ord_time}"/></div>
				</div>
				<div class="inTr">
					<div class="L">訂單備註:</div>
					<div style="display:inline-block;width:8em;">${product_orderVO.rmk}</div>
				</div>
			</td>
			
			<td style="width:15%;">
				<div class="inTr">
					<label for="prod_ord_stat0" ${product_orderVO.prod_ord_stat==0?'class="spotlight"':''}>未付款</label>
					<input type="radio" id="prod_ord_stat0" value="0" ${product_orderVO.prod_ord_stat==0?'checked':''} disabled>
					<label for="prod_ord_stat1" ${product_orderVO.prod_ord_stat==1?'class="spotlight"':''}>已付款</label>
					<input type="radio" id="prod_ord_stat1" value="1" ${product_orderVO.prod_ord_stat==1?'checked':''} disabled>
					<label for="prod_ord_stat2" ${product_orderVO.prod_ord_stat==2?'class="spotlight"':''}>出貨中</label>
					<input type="radio" id="prod_ord_stat2" value="2" ${product_orderVO.prod_ord_stat==2?'checked':''} disabled>
					<label for="prod_ord_stat3" ${product_orderVO.prod_ord_stat==3?'class="spotlight"':''}>已收貨</label>
					<input type="radio" id="prod_ord_stat3" value="3" ${product_orderVO.prod_ord_stat==3?'checked':''} disabled>
					<label for="prod_ord_stat4" ${product_orderVO.prod_ord_stat==4?'class="spotlight"':''}>未取貨</label>
					<input type="radio" id="prod_ord_stat4" value="4" ${product_orderVO.prod_ord_stat==4?'checked':''} disabled>
					<label for="prod_ord_stat5" ${product_orderVO.prod_ord_stat==5?'class="spotlight"':''}>取消訂單</label>
					<input type="radio" id="prod_ord_stat5" value="5" ${product_orderVO.prod_ord_stat==5?'checked':''} disabled>
				</div>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
</div>
</div>
<c:if test="${openModal!=null}">
		<div class="modal" tabindex="-1" role="dialog" id="Modal">
		     <div class="modal-dialog modal-lg" role="document"> 
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title">${product_orderVO.mbr_no}會員之訂單編號為${product_orderVO.prod_ord_no}號的詳細資訊</h5>
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button> 
		            </div>
		            <div class="modal-body">
						<jsp:include page="listOneProduct_order.jsp" />
		            </div>
		       </div>
		   </div>
		</div>

<script>
	$('#Modal').modal({
	  	show :true
	}); 
</script>
</c:if>
<script>
$("tr").click(function(e){
	console.log('in');
	let prod_ord_no = e.currentTarget.children[0].innerText;
	window.location.href="<%=request.getContextPath()%>/product_order/product_order.do?prod_ord_no="+ prod_ord_no + "&action=read";
});
</script>
<script>
	let backToTop = document.getElementsByClassName("backToTop");
	$(window).scroll(function(e) {
		if ($(window).scrollTop() <= 1) {
			backToTop[1].style.display = "none";
		} else {
			backToTop[1].style.display = "block";
		}
	});
</script>
</body>
</html>