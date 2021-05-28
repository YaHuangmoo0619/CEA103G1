<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*" %>
<jsp:useBean id="product_pictureSvc" scope="page" class="com.product_picture.model.Product_pictureService"/>
<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />
<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>



<% 
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	int ajax_mbr_no = 0;

	if(memberVO!=null){
		ajax_mbr_no = memberVO.getMbr_no();
	}
	if(memberVO==null){
		ajax_mbr_no=0;
	}
	pageContext.setAttribute("ajax_mbr_no", ajax_mbr_no);
%>

<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>�ӫ~ </title>
<%@ include file="/part-of/partOfCampion_frontTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>

<style>
	html, body {
		margin: 0;
		padding: 0;
	/* 	background: #fff; */
		background-image: linear-gradient(rgba(255,255,255,.5), rgba(255,255,255,.5)), url("/CEA103G1/front-images/ShopFront.jpg") ;
		background-size: 100%;
		background-attachment: fixed;
		color: #4B7F52;
	}
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
  .container{
  	opacity:0.7;
  }
</style>

<style>
.product-subtract,
.product-plus,
.product-qty {
  width: 33.330557%;
  background-color: transparent;
  color: #686868;
  text-align: center;
}

._column {
  display: inline-block;
  vertical-align: top;
  font-size: medium;
  text-align: left;
  text-rendering: optimizeLegibility;
}

._btn {
  display: inline-block;
  background-color: #bdc3c7;
  border: none;
  padding: .5em .75em;
  text-align: center;
  font-weight: 300;
}

._btn:hover,
.cart-totals:hover ._btn {
  background-color: #3498db;
  color: #ecf0f1;

</style>

</head>
<body bgcolor='white'>
<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
			
<section>
    <div class="container">
        <div class="card">
            <div class="card-wrapper">
                <div class="row align-items-center">
                    <div class="col-12 col-md-3">
                        <div class="showPic">
				            <c:forEach var="product_pictureVO" items="${product_pictureSvc.findByProd_no(productVO.prod_no)}">
								<img class="inDiv" src="${product_pictureVO.prod_pic}" style="max-width:100%; height:auto;">
							</c:forEach>
                        </div>
                    </div>
                    <div class="col-12 col-md">
                        <div class="card-box">
                            <div class="row">
                                <div class="col-md">
                                    <p>${product_categorySvc.getOneProduct_category(productVO.prod_cat_no).prod_cat_name}</p>
                                    <h6 class="card-title mbr-fonts-style display-5">
                                        <strong>�ӫ~�W��:</strong>
                                    </h6>
                                    <p class="mbr-text mbr-fonts-style display-7">
                                        ${productVO.prod_name}
                                    </p>
                                    <p><strong>�~�P:</strong>${productVO.prod_bnd}</p>
                                    <p><strong>�ӫ~��T:</strong></p>
                                    <p>${productVO.prod_info}</p>
									<p><strong>�ӫ~�C��:</strong>${productVO.prod_clr}</p>
									<p><strong>�ӫ~�j�p:</strong>${productVO.prod_size}</p>
									<p>
									<c:if test="${productVO.ship_meth==0}">
										<c:out value="�����B�e�覡" />
									</c:if>
									<c:if test="${productVO.ship_meth==1}">
										<c:out value="���v�t" />
									</c:if>
									<c:if test="${productVO.ship_meth==2}">
										<c:out value="���W�Ө��f" />
									</c:if>
									</p>                               
                                </div>
                                <div class="col-md-auto">
                              		<p>�ӫ~���� </p>
                                    <p class="price mbr-fonts-style display-4">${productVO.prod_pc}</p>
                                <div>
								    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" style="margin-bottom: 0px;">
									     <input type="submit" value="�����U�q" class="btn btn-white-outline display-4">
									     <input type="hidden" name="prod_no"  value="${productVO.prod_no}">
									     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
									     <input type="hidden" name="action"	value="buyOne">
									</FORM>
								</div>
							<div>
							  <button class="_btn _column product-subtract">&minus;</button>
				              <div class="_column product-qty" id="product-qty">0</div>
				              <button class="_btn _column product-plus">&plus;</button>
							</div>
							
							<div>�ثe�w�s�G</div>
							<div id="max_num">${productVO.prod_stg}</div>
							<!-- 	�p�G���n�J���� -->	
							<c:if test="${not empty memberVO }"> 
							<td><button class=addshopping_cart>�[�J�ʪ���</button></td>
							</c:if>
							<!-- 	�p�G�S���n�J����  �n���}�W���n�J���O�c-->	
							<c:if test="${empty memberVO }"> 
							<td><button class=to_login>�[�J�ʪ���</button></td>
							</c:if>
                                </div>
                                <div></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>      
    </div>

			
			
			
	<div class="modal fade" id="login_confirm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5>�z�|���n�J</h5>
	      </div>
	      <div class="modal-body">
	        <div>�Q�n�@�_�[�J�Q�סA�n���n�J Campion ��I</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary close_modal" data-bs-dismiss="modal">����</button>
	        <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/front-end/member/login.jsp'">�n�J</button>
	      </div>
	    </div>
	  </div>
	</div>
</section>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>


<script>
	$("#purchase").click(function(){
		"javascript:location.href='${pageContext.request.contextPath}/front-end/product_order/listOneProduct_List.jsp'"
	})

	$(".to_login").click(function(){
	  		$('#login_confirm').modal('show');
	})
	
	$(".close_modal").click(function(){
	  		$('#login_confirm').modal('hide');
	})
	
	$(".addshopping_cart").click(function(){
		var num_add = $("#product-qty").text();
		$.ajax({  
			type : "post",
			url : "http://localhost:8081/CEA103G1/shopping_cart/shopping_cart.do",
			data : {action: "add_collection",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,prod_no:<%=productVO.getProd_no()%>,prod_amt:num_add},
			success : function(data) {
				alert("���\�[�J�ʪ���");
			}
		});
	})
	
	$(".product-plus").click(function(){
		var max_num = parseInt($("#max_num").text());
		console.log("max_num:"+max_num);
		var num = parseInt($(this).prev().text());
		if(num<max_num){
			num = num + 1;
		}
		$(this).prev().text(num);
		
		})

	$(".product-subtract").click(function(){
		var num = parseInt($(this).next().text());
		if(num>0){
			num = num - 1;
			$(this).next().text(num);
		}

		$(this).next().text(num);
		})		
	
		
		
</script>
</body>
</html>