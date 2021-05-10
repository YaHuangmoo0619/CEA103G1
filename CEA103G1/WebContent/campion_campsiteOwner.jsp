<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite_owner.model.*" %>

<!-- 測試登入狀態及畫面改變 -->
<%
Campsite_ownerService campsite_ownerSvcLogin = new Campsite_ownerService();
Campsite_ownerVO campsite_ownerVOLogin = campsite_ownerSvcLogin.getOneCampsite_owner(70003);
session.setAttribute("campsite_ownerVO",campsite_ownerVOLogin);
%>

<% Campsite_ownerVO campsite_ownerVO = (Campsite_ownerVO)session.getAttribute("campsite_ownerVO"); %>

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
<title>營家Campion 營位管理</title>
<style>
/*
淺綠:#80c344
深灰:#4e5452
深綠:#4B7F52
淺灰:#eee
純白:#fff
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
	position: relative;
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

div.colorPic {
	background-image:
		url("<%=request.getContextPath()%>/front-images/topphoto.jpg");
	background-position: center;
	background-size: cover;
	height: 150px;
	padding: 20px 0px;
}

div.top {
	display: flex;
	justify-content: space-between;
	padding: 5px 15px 5px 15px;
	background-color: #eee;
	width: 100%;
}

img.logo {
	max-height: 50px;
}

img.person {
	max-width: 40px;
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
	background-color: #eee;
}

div.welcomePic {
	display: flex;
	justify-content: space-around;
	padding-top: 20px;
	padding-bottom:20px;
	opacity: 0.7;
}

img.backToTop {
	width: 40px;
	position: fixed;
	left: 90%;
	bottom: 5%;
	background-color: #80c344;
	border-radius: 50%;
	z-index: 10;
	display: none;
}
div.backToTop{
	font-size: 0px;
}
</style>
</head>
<body>
	<div class="colorPic">
		<div class="top">
			<a href="<%=request.getContextPath()%>/campion_campsiteOwner.jsp">
			<img src="<%=request.getContextPath()%>/images/campionLogoLong.png"	class="logo"></a>
			<c:if test="${campsite_ownerVO != null}">
				<div>${campsite_ownerVO.name}
				<a href="" style="display:inline;"><img src="<%=request.getContextPath() %>/front-images/person-circle-outline.svg" class="person"></a>
				</div>
			</c:if>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="left col-sm-3">
				<ul>
					<li><a
						href="<%=request.getContextPath()%>/front-end/place_order/PresentPlace_order.jsp">預約訂單列表</a></li>
					<li><a
						href="<%=request.getContextPath()%>/front-end/campsite/listAllCamp.jsp">營區清單</a></li>
					<li><a
						href="<%=request.getContextPath()%>/front-end/campsite/addCamp.jsp">新增營區申請</a></li>
					<li><a href="<%=request.getContextPath()%>/front-end/campsite_owner_mail/listAllCampsite_owner_mail.jsp">站內信</a></li>
					<li><a href="">修改營主資料</a></li>
					<li><a href="<%=request.getContextPath()%>/campion_front.jsp">登出</a></li>
				</ul>
			</div>
			<div class="right col-sm-9">
				<div class="row">
					<div class="col welcomePic">
						<img
							src="<%=request.getContextPath()%>/images/campionLogoShort.png">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="backToTop">
		<a href="#"><img
			src="<%=request.getContextPath()%>/front-images/arrow-up-circle-outline.svg"
			class="backToTop"></a>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	<script>
		let backToTop = document.getElementsByClassName("backToTop");
		$(window).scroll(function(e) {
			console.log(e);
			if ($(window).scrollTop() <= 1) {
				backToTop[1].style.display = "none";
			} else {
				backToTop[1].style.display = "block";
			}
		});
	</script>

</body>
</html>