<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.member.model.*" %>

<!-- 測試登入狀態及畫面改變 -->
<%
// MemberService memberSvc = new MemberService();
// MemberVO memberVOLogin = memberSvc.getOneMember(10010);
// session.setAttribute("memberVO",memberVOLogin);
%>

<% MemberVO memberVO = (MemberVO)session.getAttribute("memberVO"); %>

<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<link rel="icon" href="<%=request.getContextPath() %>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<link   rel="stylesheet" type="text/css" href="/CEA103G1/datetimepicker/jquery.datetimepicker.css" />
<title>營家Campion</title>
<style>
html, body {
	margin: 0;
	padding: 0;
	background-color: #4e5452;
	color: #80c344;
}

#backtobrowse{
 	margin-top: -5px;
	padding: 10px;
	color: white;
	border: 1px solid white;
	background-color: transparent;
	border-radius: 8%;
}
#backtobrowse:hover{
/* 	margin-top: 10px; */
	padding: 10px;
	color: white;
	border: 2px solid white;
	background-color: transparent;
	cursor: pointer;
	border-radius: 8%;
}

section {
	text-align: center;
}

div.fixedTop {
	background-color: #eee;
	position: relative;
	z-index: 10;
	left: 50%;
	transform: translateX(-50%);
	width: 100%;
	text-align: center;
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 0px 15px;
}

img.logo {
	width: 150px;
	margin: 10px;
}

a {
	text-decoration: none;
}

a:hover {
	text-decoration: none;
}

a.button{
	margin: 5px;
}

form.form-inline {
	display: inline;
	border: none;
}

img.searchIcon {
	display: none;
}

img.cart {
	width: 30px;
	margin: 10px;
}

img.cart:hover {
	cursor: pointer;
}

img.announcement{
	width: 30px;
	margin: 10px;
}
img.announcement:hover{
	cursor: pointer;
}

img.menu {
	width: 40px;
	margin: 10px;
	display: none;
}

img.menu:hover {
	cursor: pointer;
}

img.person {
	width: 40px;
	margin: 10px;
	right: 1px;
}

img.person:hover {
	cursor: pointer;
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

section.jumbotron {
	border-radius: 0px;
	background-image: url("<%=request.getContextPath()%>/front-images/topphoto.jpg");
	background-position: center;
	background-size: cover;
	height: 600px;
}

div.slogan h1 {
	color: #fff;
	font-size: 3em;
	font-weight: 999;
	margin: 0px auto;
	margin-top: 30px;
}

div.header {
	color: #fff;
	font-size: 1.1em;
	font-weight: 777;
	margin-top: 200px;
	opacity: 0.5;
	margin-top: 200px;
}

div.header:hover {
	opacity: 1;
}

form {
	width:95%;
	border: solid 1px #fff;
	padding: 15px;
}

div.where {
	display: inline;
}

div.photo {
	width: 200px;
	height: 100px;
	margin: 20px auto;
	background-color: #eee;
	overflow: hidden;
}

img {
	width: 100%;
	margin-top: 30px;
	margin-bottom: 10px;
}

div.row {
	margin-top: 30px;
}

div.more {
	/*color: #7DD181;*/
	margin-top: 0px;
	padding-bottom: 20px;
	display: inline;
	font-size: 1.5em;
}

div.article {
	width: 100%;
	margin: 0px auto;
	text-align: left;
	background-color: #eee;
	border-radius: 5px;
	padding: 10px 50px 20px 50px;
}

h5 {
	padding-top: 10px;
}

p {
	margin: 0px;
}

section.articles {
	padding-bottom: 20px;
}

section.products {
	background-color: #fff;
	padding-bottom: 20px;
}

section.footer {
	background-color: #4B7F52;
	color: #80c344;
	height: 100px;
	padding-top: 10px;
}

@media screen and (max-width: 575px) {
	container {
		width: 100%;
	}
	form.form-inline {
		display: none;
	}
	form.secSearch {
		display: none;
	}
	img.searchIcon {
		display: inline;
		width: 30px;
		margin: 0px;
	}
	img.searchIcon:hover {
		cursor: pointer;
	}
	div.btn-group {
		display: none;
	}
	img.menu {
		width: 30px;
		display: inline-block;
	}
	div.sec {
		display: none;
		background-color: #eee;
	}
	div.where {
		display: block;
		padding: 5px 100px;
		text-align: left;
	}
	div.slogan h1 {
		font-size: 2em;
		margin: 50px auto;
	}
	div.row {
		margin-top: 0px;
	}
	div.article {
		width: 90%;
		margin: 20px auto;
	}
	div.more {
		display: inline;
	}
	section.footer {
		padding-top: 30px;
	}
}

@media ( min-width : 576px) and (max-width: 767px) {
	container {
		width: 540px;
		margin: 0px auto;
	}
	form.form-inline {
		display: none;
	}
	form.secSearch {
		display: none;
	}
	img.searchIcon {
		display: inline;
		width: 30px;
		margin: 0px;
	}
	img.searchIcon:hover {
		cursor: pointer;
	}
	div.btn-group {
		display: none;
	}
	img.menu {
		width: 30px;
		display: inline-block;
	}
	div.sec {
		display: none;
		background-color: #eee;
	}
	div.where {
		display: block;
		padding: 5px 130px;
		text-align: left;
	}
}

@media ( min-width : 768px) and (max-width: 991px) {
	container {
		width: 720px;
		margin: 0px auto;
	}
	form.form-inline {
		display: none;
	}
	form.secSearch {
		display: none;
	}
	img.searchIcon {
		display: inline;
		width: 30px;
		margin: 0px;
	}
	img.searchIcon:hover {
		cursor: pointer;
	}
	div.btn-group {
		display: none;
	}
	img.menu {
		width: 30px;
		display: inline-block;
	}
	div.sec {
		display: none;
		background-color: #eee;
	}
	div.where {
		display: block;
		padding: 5px 220px;
		text-align: left;
	}
	div.photo {
		width: 300px;
		height: 150px;
	}
}

@media ( min-width : 992px) and (max-width: 1199px) {
	container {
		width: 960px;
		margin: 0px auto;
	}
	div.menuForButton {
		display: none;
	}
	div.forSearch {
		display: none;
	}
	div.photo {
		width: 400px;
		height: 200px;
	}
}

@media ( min-width : 1200px) {
	container {
		width: 1140px;
		margin: 0px auto;
	}
	div.menuForButton {
		display: none;
	}
	div.forSearch {
		display: none;
	}
	div.photo {
		width: 400px;
		height: 200px;
	}
}
</style>
</head>

<body onload="connection()">
	<div class="fixedTop">
		<div>
			<a href="<%=request.getContextPath() %>/campion_front.jsp"> <img src="<%=request.getContextPath() %>/front-images/campionLogoLong.png" class="logo">
			</a>
<!-- 			<form class="form-inline my-2 my-lg-0"> -->
<!-- 				<input class="form-control mr-sm-2" type="search" -->
<!-- 					placeholder="營位/商品/文章" aria-label="Search"> -->
<!-- 				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜尋</button> -->
<!-- 			</form> -->
		</div>
		<div>
<%-- 			 <img src="<%=request.getContextPath() %>/front-images/search-circle-outline.svg" id="searchIcon" class="searchIcon"> --%>
			<div class="btn-group" role="group" aria-label="Basic example">
				<a class="button" href="<%=request.getContextPath() %>/front-end/campsite/listAllCamp.html"><button type="button" class="btn btn-secondary">露營</button></a>
				<a class="button" href="<%=request.getContextPath() %>/front-end/article/listAllArticle.jsp"><button type="button" class="btn btn-secondary">論壇</button></a>
				<a class="button" href="<%=request.getContextPath() %>/front-end/product/listAllProduct.jsp"><button type="button" class="btn btn-secondary">商城</button></a>
			</div>
			<a href="<%=request.getContextPath() %>/front-end/shopping_cart/listOneShoppingCart.jsp"><img src="<%=request.getContextPath() %>/front-images/cart-outline.svg" class="cart"></a>
			<a href="<%=request.getContextPath() %>/front-end/announcement/listAllAnnouncement.jsp"><img src="<%=request.getContextPath() %>/front-images/megaphone-outline.svg" class="announcement"></a>
			<c:if test="${memberVO == null}">
			<div class="btn-group" role="group" aria-label="Basic example">
				<a class="button" href="<%=request.getContextPath() %>/campion_campsiteOwner.jsp"><button type="button" class="btn btn-outline-secondary">營主</button></a>
				<a class="button" href="<%=request.getContextPath() %>/front-end/member/register.jsp"><button type="button" class="btn btn-outline-secondary">註冊</button></a>
				<a class="button" href="<%=request.getContextPath() %>/front-end/member/login.jsp"><button type="button" class="btn btn-outline-secondary">登入</button></a>
<!-- 				<a class="button" href=""><button type="button" class="btn btn-outline-secondary">FAQ</button></a> -->
<!-- 				<a class="button" href=""><button type="button" class="btn btn-outline-secondary">聯絡我們</button></a> -->
			</div>
			</c:if>
			<img src="<%=request.getContextPath() %>/front-images/menu-outline.svg" id="menu" class="menu">
			<c:if test="${memberVO != null}">
			<a href="<%=request.getContextPath() %>/front-end/member_mail/listAllMember_mail.jsp">
				<div style="position:relative;display:inline;">
					<img src="<%=request.getContextPath() %>/front-images/mail-outline.svg" class="announcement">
					<div style="background-color: #80c344; color: #fff; width:20px; height:20px;border-radius: 50%; position:absolute; font-size:0.5em;display:inline; right:20%; bottom:50%;"  id="countNoReadMail"></div>
				</div>
			</a>
			<a href="<%=request.getContextPath() %>/front-end/personal_system_notify/listAllPersonal_system_notify.jsp">
				<div style="position:relative;display:inline;">
					<img src="<%=request.getContextPath() %>/front-images/notifications-outline.svg" class="announcement">
					<div style="background-color: #80c344; color: #fff; width:20px; height:20px;border-radius: 50%; position:absolute; font-size:0.5em;display:inline; right:20%; bottom:50%;"  id="countNoReadNotify"></div>
				</div>
			</a>
			<a class="button" href="<%=request.getContextPath()%>/member/member.do?action=logout"><button type="button" class="btn btn-outline-secondary">登出</button></a>
				${memberVO.name}
			<a href="<%=request.getContextPath() %>/front-end/member/viewMember.jsp"><div class="person" style="display:inline;border-radius:50%;"> <img src="<%=request.getContextPath() %>/member/GetPhoto?mbr_no=${memberVO.mbr_no}" class="person"></div></a>
			</c:if>
		</div>
	</div>

	<div class="forSearch">
		<form class="form-inline secSearch my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="search"
				placeholder="營位/商品/文章" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜尋</button>
		</form>
	</div>
	<div class="menuForButton">
		<div class="btn-group sec" role="group" aria-label="Basic example">
			<a class="button" href="<%=request.getContextPath() %>/front-end/campsite/listAllCamp.jsp"><button type="button" class="btn btn-secondary">露營</button></a>
			<a class="button" href="<%=request.getContextPath() %>/front-end/article/listAllArticle.jsp"><button type="button" class="btn btn-secondary">論壇</button></a>
			<a class="button" href="<%=request.getContextPath() %>/front-end/product/listAllProduct.jsp"><button type="button" class="btn btn-secondary">商城</button></a>
		</div>
		<c:if test="${memberVO == null}">
		<div class="btn-group sec" role="group" aria-label="Basic example">
			<a class="button" href="<%=request.getContextPath() %>/campion_campsiteOwner.jsp"><button type="button" class="btn btn-outline-secondary">營主</button></a>
			<a class="button" href="<%=request.getContextPath() %>/front-end/member/register.jsp"><button type="button" class="btn btn-outline-secondary">註冊</button></a>
			<a class="button" href="<%=request.getContextPath() %>/front-end/member/login.jsp"><button type="button" class="btn btn-outline-secondary">登入</button></a>
<!-- 			<a class="button" href=""><button type="button" class="btn btn-outline-secondary">FAQ</button></a> -->
<!-- 			<a class="button" href=""><button type="button" class="btn btn-outline-secondary">聯絡我們</button></a> -->
		</div>
		</c:if>
	</div>
	<div class="backToTop">
		<a href="#"><img src="<%=request.getContextPath() %>/front-images/arrow-up-circle-outline.svg"
			class="backToTop"></a>
	</div>
	<section class="jumbotron">
		<div class="container">
			<div class="row slogan">
				<h1>開啟一段新的旅程吧！</h1>
			</div>
			<div class="row header">
				<div class="col-sm">
					<form class="search" method="get" id="condition" onsubmit="return false">
						<div class="row">
							<div class="where col-sm">
								<label for="county2"> 縣　　市: <select name="c" id="c"><option value="不拘">不拘</option></select></label>
							</div>
							<br>
							<div class="where col-sm">
								<label for="f_date1"> 入住日期: <input name="st" id="st" type="text"></label>
							</div>

							<div class="where col-sm">
								<label for="f_date2"> 退房日期: <input name="ed" id="ed" type="text"></label>
							</div>

							<div class="where col-sm">
								<label for="people"> 人　　數: <input name="p" id="p" type="number"></label>
							</div>
							<div class="where col-sm" style="padding-top: 15px;">
								<input type="hidden" name="action" value="search"> 
								<input type="button" id="backtobrowse" value="搜      尋">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	
	<jsp:useBean id="campsiteSvc" class="com.campsite.model.CampService"/>
	<section class="campsites">
		<div class="container">
			<div class="row">
				<c:forEach var="campsiteVO" items="${campsiteSvc.all}" begin="0" end="2">
				<div class="col-sm-4">
					<a href="<%=request.getContextPath() %>/front-end/campsite/listOneCamp.html?camp_no=${campsiteVO.camp_no}&action=getone">
					<img src="https://images.unsplash.com/photo-1557292916-eaa52c7e5939?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Y2FtcHNpdGV8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60">
					</a>
					<a class="text" href="<%=request.getContextPath() %>/front-end/campsite/listOneCamp.html?camp_no=${campsiteVO.camp_no}&action=getone">${campsiteVO.camp_name}</a>
				</div>
				</c:forEach>
			</div>
			<div class="row">
				<c:forEach var="campsiteVO" items="${campsiteSvc.all}" begin="3" end="5">
				<div class="col-sm-4">
					<a href="<%=request.getContextPath() %>/front-end/campsite/listOneCamp.html?camp_no=${campsiteVO.camp_no}&action=getone">
					<img src="https://images.unsplash.com/photo-1557292916-eaa52c7e5939?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Y2FtcHNpdGV8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60">
					</a>
					<a class="text" href="<%=request.getContextPath() %>/front-end/campsite/listOneCamp.html?camp_no=${campsiteVO.camp_no}&action=getone">${campsiteVO.camp_name}</a>
				</div>
				</c:forEach>
			</div>
			
			<div class="row">
				<div class="col-sm">
					<a href="<%=request.getContextPath() %>/front-end/campsite/listAllCamp.html"><div class="more">> 更多營區</div></a>
				</div>
			</div>
		</div>
	</section>
	
	<jsp:useBean id="productSvc" class="com.product.model.ProductService"/>
	<section class="products">
		<div class="container">
			<div class="row">
				<c:forEach var="productVO" items="${productSvc.all}" begin="0" end="3">
				<div class="col-sm-3">
					<a href="<%=request.getContextPath() %>/front-end/product/listOneProduct.jsp">
					<img src="https://images.unsplash.com/photo-1444012104069-996724bf4a0a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=632&q=80">
					</a>
					<a class="text" href="<%=request.getContextPath() %>/front-end/product/listOneProduct.jsp">${productVO.prod_name}</a>
				</div>
				</c:forEach>
			</div>
			<div class="row">
				<c:forEach var="productVO" items="${productSvc.all}" begin="4" end="7">
				<div class="col-sm-3">
					<a href="<%=request.getContextPath() %>/front-end/product/listOneProduct.jsp">
					<img src="https://images.unsplash.com/photo-1444012104069-996724bf4a0a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=632&q=80">
					</a>
					<a class="text" href="<%=request.getContextPath() %>/front-end/product/listOneProduct.jsp">${productVO.prod_name}</a>
				</div>
				</c:forEach>
			</div>
			<div class="row">
				<div class="col-sm">
					<a href="<%=request.getContextPath() %>/front-end/product/listAllProduct.jsp"><div class="more">> 更多商品</div></a>
				</div>
			</div>
		</div>
	</section>

	<jsp:useBean id="articleSvc" class="com.article.model.ArticleService"/>
	<section class="articles">
		<div class="container">
			<c:forEach var="articleVO" items="${articleSvc.all_Front}" begin="0" end="2">
			<div class="row">
				<div class="col-sm">
					<a href="<%=request.getContextPath() %>/article/article.do?art_no=${articleVO.art_no}&action=getOne_From4">
						<div class="article">
							<h5>${articleVO.art_title}</h5>
							<c:set var="art_cont" value="${articleVO.art_cont}" />
							<c:if test="${art_cont.length() > 50}">
								<p>${fn:substring(art_cont, 0, 50)}...</p>
							</c:if>
							<c:if test="${art_cont.length() <= 50}">
								<p>${art_cont}</p>
							</c:if>
							<p>> 看更多</p>
						</div>
					</a>
				</div>
			</div>
			</c:forEach>
<!-- 			<div class="row"> -->
<!-- 				<div class="col-sm"> -->
<!-- 					<a href=""><div class="article"> -->
<!-- 							<h5>Title標題標題</h5> -->
<!-- 							<p>One column內文部分內容，可能可以放100字...</p> -->
<!-- 							<p>> 看更多</p> -->
<!-- 						</div></a> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="row"> -->
<!-- 				<div class="col-sm"> -->
<!-- 					<a href=""><div class="article"> -->
<!-- 							<h5>Title標題標題</h5> -->
<!-- 							<p>One column內文部分內容，可能可以放100字...</p> -->
<!-- 							<p>> 看更多</p> -->
<!-- 						</div></a> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<div class="row">
				<div class="col-sm">
					<a href="<%=request.getContextPath() %>/front-end/article/listAllArticle.jsp"><div class="more">> 更多文章</div></a>
				</div>
			</div>
		</div>
	</section>

	<section class="footer">
		<div class="container">
			<div class="row">
				<div class="col-sm">Copyright2020-2021,Campion Group or its
					Affiliates.</div>
			</div>
		</div>
	</section>


	<c:if test="${openModal!=null}">

		<div class="modal fade" id="basicModal_homepage" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-body">
						<!-- =========================================以下為原listOneArticle.jsp的內容========================================== -->
						<jsp:include page="front-end/article/listOneArticle.jsp" />
						
						<!-- =========================================以上為原listOneArticle.jsp的內容========================================== -->
					</div>
				</div>
			</div>
		</div>

	</c:if>


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js" charset="UTF-8"></script>
	<script>
	var default1 = new Date();
	default1.setTime(default1.getTime()+24*60*60*1000); 
    $.datetimepicker.setLocale('zh'); // kr ko ja en
    $('#st').datetimepicker({
       theme: '',          //theme: 'dark',
       timepicker: false,   //timepicker: false,
       step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
       format: 'Y-m-d',
//	       value: default1,
//        disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
       startDate:	        default1,  // 起始日
       minDate:           '-1969-12-31', // 去除今日(不含)之前
       //maxDate:           '+1970-01-01'  // 去除今日(不含)之後
    });
	default1.setTime(default1.getTime()+24*60*60*1000); 
    $.datetimepicker.setLocale('zh'); // kr ko ja en
    $('#ed').datetimepicker({
       theme: '',          //theme: 'dark',
       timepicker: false,   //timepicker: false,
       step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
       format: 'Y-m-d',
//	       value: default1,
//        disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
       startDate:	        default1,  // 起始日
       minDate:           '-1969-12-30', // 去除今日(不含)之前
       //maxDate:           '+1970-01-01'  // 去除今日(不含)之後
    });
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "<%=request.getContextPath()%>/district/district.do",
		success : function(data) {
			showDistrict(data);
		}
	});

	function showDistrict(data) {
		for (i in data) {
			var opt = $("<option>").val(data[i]).text(data[i]);
			$("#c").append(opt);
		}
	}
		let countMenu = 0;
		$("#menu").click(function() {
			countMenu++;
			if (countMenu % 2 == 1) {
				let secArray = document.getElementsByClassName("sec");
				for (let i = 0; i < secArray.length; i++) {
					secArray[i].style.display = "flex";
				}
			} else {
				let secArray = document.getElementsByClassName("sec");
				for (let i = 0; i < secArray.length; i++) {
					secArray[i].style.display = "none";
				}
			}
		});
		$("#backtobrowse").click(function(){
			let start = Date.parse($('#st').val()).valueOf();
	    	let end = Date.parse($('#ed').val()).valueOf();
	    	if (start >= end) {
				alert("入住日期不得大於或等於退房日期");
				return false;
			};
			let st = $('#st').val();
			let ed = $('#ed').val();
			let p = $('#p').val();
			let c = $('#c').val();
			sessionStorage.setItem('county',c)
			window.location.href="<%=request.getContextPath()%>/front-end/campsite/listAllCamp.html?startdate=" + st + "&enddate=" + ed + "&people=" + p;
		});
		let countSearch = 0;
		$("#searchIcon").click(function() {
			countSearch++;
			if (countSearch % 2 == 1) {
				let formArray = document.getElementsByClassName("secSearch");
				for (let i = 0; i < formArray.length; i++) {
					formArray[i].style.display = "flex";
				}
			} else {
				let formArray = document.getElementsByClassName("secSearch");
				for (let i = 0; i < formArray.length; i++) {
					formArray[i].style.display = "none";
				}
			}
		});

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
		<script>
		$("#basicModal").modal({
			show : true
		});
	</script>
<script>
	let countMenu = 0;
	$("#menu").click(function() {
		countMenu++;
		if (countMenu % 2 == 1) {
			let secArray = document.getElementsByClassName("sec");
			for (let i = 0; i < secArray.length; i++) {
				secArray[i].style.display = "flex";
			}
		} else {
			let secArray = document.getElementsByClassName("sec");
			for (let i = 0; i < secArray.length; i++) {
				secArray[i].style.display = "none";
			}
		}
	});

//	let countSearch = 0;
//	$("#searchIcon").click(function() {
//		countSearch++;
//		if (countSearch % 2 == 1) {
//			let formArray = document.getElementsByClassName("secSearch");
//			for (let i = 0; i < formArray.length; i++) {
//				formArray[i].style.display = "flex";
//			}
//		} else {
//			let formArray = document.getElementsByClassName("secSearch");
//			for (let i = 0; i < formArray.length; i++) {
//				formArray[i].style.display = "none";
//			}
//		}
//	});

</script>
<script>
		function writeToScreen(input){
			let noRead = JSON.parse(input);
			
			var notifyMail = document.getElementById('countNoReadMail');
			notifyMail.innerText = noRead.countNoReadMail;
			var notifyNotify = document.getElementById('countNoReadNotify');
			notifyNotify.innerText = noRead.countNoReadNotify;
			
		}
		function connection(){
			let wsUri = 'ws://'+'<%=request.getServerName()%>'+':'+'<%=request.getServerPort()%>'+'<%=request.getContextPath()%>'+'/Member_mailNotify/${memberVO.mbr_no}';
			websocket = new WebSocket(wsUri);
			websocket.onmessage = function(event){
				let noRead = event.data;
				writeToScreen(noRead);
			};
		}
		
</script>
	
		<script>
		
		$('#basicModal_homepage').modal('show')
	</script>
	
</body>

</html>