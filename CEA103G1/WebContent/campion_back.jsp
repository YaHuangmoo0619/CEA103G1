<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="BIG5">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<link rel="icon"
	href="<%=request.getContextPath()%>/images/campionLogoIcon.png"
	type="image/png">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>��aCampion</title>

<style>
/*
�L��:#80c344
�`��:#4e5452
�`��:#4B7F52
�L��:#eee
�¥�:#fff
*/
/*
* {
	border: solid 1px #4e5452;
}
*/
html {
	overflow-x: hidden;
}

body {
	background-color: #4e5452;
	color: #80c344;
	overflow-x: hidden;
}

a {
	text-decoration: none;
	color: #80c344;
	margin: 10px;
}

a:hover {
	text-decoration: none;
	color: #eee;
}

div.top {
	display: flex;
	justify-content: space-between;
	padding: 10px 15px 10px 15px;
	background-color: #eee;
	width: 100%;
}

img.logo {
	max-height: 70px;
}

img.person {
	max-width: 5%;
}

div.left {
	padding: 0px;
}

ul {
	display: flex;
	flex-wrap: wrap;
	flex-direction: column;
	padding: 5px;
	margin: 15px 15px 15px 30px;
	font-size: 1.5em;
	font-weight: 888;
}

li {
	list-style: none;
	padding: 10px 0px;
}

div.right {
	padding: 5px 40px 5px 20px;
	margin-top: 50px;
}

div.countBox {
	margin: 10px 10px;
	border-radius: 5px;
	background-color: #eee;
	padding: 5px;
}

h5 {
	margin: 15px 20px 15px 25px;
	font-weight: 888;
	color: #4B7F52;
}

p {
	margin: 15px 30px 15px 35px;
	font-size: 1.2em;
	color: #4e5452;
}

</style>
</head>
<body>
	<div class="top">
		<a href="<%=request.getContextPath()%>/campion_back.jsp"><img src="<%=request.getContextPath()%>/images/campionLogoLong.png"
			class="logo"></a>
		<img src="<%=request.getContextPath()%>/front-images/person-circle-outline.svg" class="person">
	</div>
	<div class="content">
		<div class="row">
			<div class="left col-3">
				<ul>
					<li><a
						href="<%=request.getContextPath()%>/back-end/employee/select_page.jsp">���u�޲z</a></li>
					<li><a href="<%=request.getContextPath() %>/back-end/place_order/listAllPlace_order.jsp">���q�ʥ��x�޲z</a></li>
					<li><a href="<%=request.getContextPath() %>/back-end/product_category/select_page.jsp">�ӫ��޲z</a></li>
					<li><a href="<%=request.getContextPath() %>/back-end/article/select_page.jsp">�׾º޲z</a></li>
					<li><a href="<%=request.getContextPath() %>/back-end/member_rank/select_page.jsp">�b���޲z</a></li>
					<li><a href="<%=request.getContextPath() %>/back-end/announcement/select_page.jsp">���i�޲z</a></li>
					<li><a href="">�ȪA�޲z</a></li>
					<li><a href="">�Y�ɤp����޲z</a></li>
				</ul>
			</div>
			<div class="right col-9">
				<div class="row">
					<div class="col">
					
					<!-- �H�U���e�i�H�������۰O�d��X�Ӫ����A�ηs�W�ק諸�e�� -->
						<div class="countBox">
							<h5>�Τ��`��</h5>
							<!--<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>-->
							<p>100��|��</p>
							<!--<jsp:useBean id="campsite_ownerSvc" class="com.campsite_owner.model.Campsite_ownerService"/>-->
							<p>20����D</p>
						</div>
					</div>
					<div class="col">
						<div class="countBox">
							<h5>�ӫ~�q��</h5>
							<jsp:useBean id="product_orderSvc" class="com.product_order.model.Product_orderService"/>
							<p>�֭p�@��${product_orderSvc.getAll().size()}��</p>
							<p>�ӫ~�q��</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<div class="countBox">
							<h5>���q��</h5>
							<jsp:useBean id="place_orderSvc" class="com.place_order.model.Place_OrderService"/>
							<p>�֭p�@��${place_orderSvc.getAll().size()}��</p>
							<p>���q��</p>
						</div>
					</div>
					<div class="col">
						<div class="countBox">
							<h5>���|�^��</h5>
<%-- 							<jsp:useBean id="article_replay_reportSvc" class="com.article_replay_report.model.Article_Rep_ReportService"/> --%>
<%-- 							<jsp:useBean id="article_reportSvc" class="com.article_report.model.Article_ReportService"/> --%>
<%-- 							<jsp:useBean id="campsite_comment_reportSvc" class="com.campsite_comment_report.model.Campsite_comment_reportService"/> --%>
<%-- 							<jsp:useBean id="campsite_reportSvc" class="com.campsite_report.model.Campsite_reportService"/> --%>
<%-- 							<jsp:useBean id="member_reportSvc" class="com.member_report.model.Member_reportService"/> --%>
<%-- 							<jsp:useBean id="product_comment_reportSvc" class="com.product_comment_report.model.Product_comment_reportService"/> --%>
<%-- 							<jsp:useBean id="product_reportSvc" class="com.product_report.model.Product_reportService"/> --%>
<%-- 							<p>�ثe�|��${article_replay_reportSvc.getAll().size()+ --%>
<%-- 							article_reportSvc.getAll().size()+ --%>
<%-- 							campsite_comment_reportSvc.getAll().size()+ --%>
<%-- 							campsite_reportSvc.getAll().size()+ --%>
<%-- 							member_reportSvc.getAll().size()+ --%>
<%-- 							product_comment_reportSvc.getAll().size()+ --%>
<%-- 							product_reportSvc.getAll().size()}��</p> --%>
							<p>�ثe�|��12��</p>
							<p>���|���B��</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>




	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</body>
</html>