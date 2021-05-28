<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite_owner.model.*" %>

<!-- ���յn�J���A�εe������ -->
<%
Campsite_ownerService campsite_ownerSvcLogin = new Campsite_ownerService();
if(request.getParameter("cso_no") != null){
	int cso_no = new Integer(request.getParameter("cso_no"));
	session.setAttribute("campsite_ownerVO", campsite_ownerSvcLogin.enableCampsite_owner(cso_no, 1));
}
Campsite_ownerVO campsite_ownerVOLogin = (Campsite_ownerVO)session.getAttribute("campsite_ownerVO");
if(campsite_ownerVOLogin == null){
	String url = "front-end/campsite_owner/login.jsp";
	RequestDispatcher successView = request.getRequestDispatcher(url); // ���\��� campion_front.jsp
	successView.forward(request, response);
}
%>
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
<title>��aCampion ���޲z</title>
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
	position: relative;
}

.person{
	border-radius: 50%;
	width: 50px;
	height: 50px;
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
	width:300px;
	height:150px;
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
			<img src="<%=request.getContextPath()%>/images/campionLogoLong.svg"	class="logo"></a>
			<c:if test="${campsite_ownerVO != null}">
				<div>${campsite_ownerVO.name}
				<a href="" style="display:inline;"><img src="<%=request.getContextPath() %>/campsite_owner/GetPhoto?cso_no=${campsite_ownerVO.cso_no}" class="person"></a>
				</div>
			</c:if>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="left col-sm-3">
				<ul>
					<li><a
						href="<%=request.getContextPath()%>/front-end/place_order/PresentPlace_order.jsp">�w���q��C��</a></li>
					<li><a
						href="<%=request.getContextPath()%>/front-end/campsite/listAllCamp.jsp">��ϲM��</a></li>
					<li><a
						href="<%=request.getContextPath()%>/front-end/campsite/addCamp.jsp">�s�W��ϥӽ�</a></li>
<%-- 					<li><a href="<%=request.getContextPath()%>/front-end/campsite_owner_mail/listAllCampsite_owner_mail.jsp">�����H</a></li> --%>
<!-- 					<li><a href="">�ק���D���</a></li> -->
					<li><a class="button" href="<%=request.getContextPath()%>/campsite_owner/campsite_owner.do?action=logout"><button type="button" class="btn btn-outline-secondary">�n�X</button></a></li>
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