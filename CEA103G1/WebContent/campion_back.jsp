<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
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
<title>營家Campion</title>

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
						href="<%=request.getContextPath()%>/back-end/employee/select_page.jsp">員工管理</a></li>
					<li><a href="<%=request.getContextPath() %>/back-end/place_order/listAllPlace_order.jsp">營位訂購平台管理</a></li>
					<li><a href="<%=request.getContextPath() %>/back-end/product_category/select_page.jsp">商城管理</a></li>
					<li><a href="<%=request.getContextPath() %>/back-end/article/select_page.jsp">論壇管理</a></li>
					<li><a href="<%=request.getContextPath() %>/back-end/member_rank/select_page.jsp">帳號管理</a></li>
					<li><a href="<%=request.getContextPath() %>/back-end/announcement/select_page.jsp">公告管理</a></li>
					<li><a href="">客服管理</a></li>
					<li><a href="">即時小幫手管理</a></li>
				</ul>
			</div>
			<div class="right col-9">
				<div class="row">
					<div class="col">
					
					<!-- 以下內容可以替換成自記查找出來的表格，或新增修改的畫面 -->
						<div class="countBox">
							<h5>用戶總數</h5>
							<p>1582位會員</p>
							<p>51位營主</p>
						</div>
					</div>
					<div class="col">
						<div class="countBox">
							<h5>商品訂單</h5>
							<p>本週共有57筆</p>
							<p>商品訂單</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<div class="countBox">
							<h5>營位訂單</h5>
							<p>本週共有57筆</p>
							<p>營位訂單</p>
						</div>
					</div>
					<div class="col">
						<div class="countBox">
							<h5>檢舉回報</h5>
							<p>本週尚有12件</p>
							<p>檢舉未處裡</p>
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