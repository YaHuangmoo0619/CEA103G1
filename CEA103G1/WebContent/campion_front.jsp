<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<link rel="icon" href="<%=request.getContextPath() %>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>營家Campion</title>
<style>
html, body {
	margin: 0;
	padding: 0;
	background-color: #4e5452;
	color: #80c344;
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

<body>
	<div class="fixedTop">
		<div>
			<a href="<%=request.getContextPath() %>/campion_front.jsp"> <img src="<%=request.getContextPath() %>/front-images/campionLogoLong.png" class="logo">
			</a>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="營位/商品/文章" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜尋</button>
			</form>
		</div>
		<div>
			 <img src="<%=request.getContextPath() %>/front-images/search-circle-outline.svg" id="searchIcon" class="searchIcon">
			<div class="btn-group" role="group" aria-label="Basic example">
				<a class="button" href=""><button type="button" class="btn btn-secondary">營區</button></a>
				<a class="button" href=""><button type="button" class="btn btn-secondary">論壇</button></a>
				<a class="button" href=""><button type="button" class="btn btn-secondary">商城</button></a>
			</div>
			<img src="<%=request.getContextPath() %>/front-images/cart-outline.svg" class="cart">
			<a href="<%=request.getContextPath() %>/front-end/announcement/listAllAnnouncement.jsp"><img src="<%=request.getContextPath() %>/front-images/megaphone-outline.svg" class="announcement"></a>
			<div class="btn-group" role="group" aria-label="Basic example">
				<a class="button" href="<%=request.getContextPath() %>/campion_campsiteOwner.jsp"><button type="button" class="btn btn-outline-secondary">營主</button></a>
				<a class="button" href=""><button type="button" class="btn btn-outline-secondary">註冊</button></a>
				<a class="button" href=""><button type="button" class="btn btn-outline-secondary">登入</button></a>
<!-- 				<a class="button" href=""><button type="button" class="btn btn-outline-secondary">FAQ</button></a> -->
<!-- 				<a class="button" href=""><button type="button" class="btn btn-outline-secondary">聯絡我們</button></a> -->
			</div>
			<img src="<%=request.getContextPath() %>/front-images/menu-outline.svg" id="menu" class="menu">
			<a href="<%=request.getContextPath() %>/front-end/member_mail/listAllMember_mail.jsp"> <img src="<%=request.getContextPath() %>/front-images/person-circle-outline.svg" class="person"></a>
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
			<a class="button" href=""><button type="button" class="btn btn-secondary">營區</button></a>
			<a class="button" href=""><button type="button" class="btn btn-secondary">論壇</button></a>
			<a class="button" href=""><button type="button" class="btn btn-secondary">商城</button></a>
		</div>
		<div class="btn-group sec" role="group" aria-label="Basic example">
			<a class="button" href="<%=request.getContextPath() %>/campion_campsiteOwner.jsp"><button type="button" class="btn btn-outline-secondary">營主</button></a>
			<a class="button" href=""><button type="button" class="btn btn-outline-secondary">註冊</button></a>
			<a class="button" href=""><button type="button" class="btn btn-outline-secondary">登入</button></a>
<!-- 			<a class="button" href=""><button type="button" class="btn btn-outline-secondary">FAQ</button></a> -->
<!-- 			<a class="button" href=""><button type="button" class="btn btn-outline-secondary">聯絡我們</button></a> -->
		</div>
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
					<form class="search">
						<div class="row">
							<div class="where col-sm">
								<label for="county1"> 縣　　市: <select id="county1"
									name="county"><option>中壢區</option></select></label>
							</div>
							<div class="where col-sm">
								<label for="county2"> 縣　　市: <input type="type"
									id="county2" name="county" size="8"></label>
							</div>
							<br>
							<div class="where col-sm">
								<label for="f_date1"> 入住日期: <input name="startdate"
									id="f_date1" type="text" size="8"></label>
							</div>

							<div class="where col-sm">
								<label for="f_date2"> 退房日期: <input name="enddate"
									id="f_date2" type="text" size="8"></label>
							</div>

							<div class="where col-sm">
								<label for="people"> 人　　數: <input name="people"
									id="people" type="text" maxlength="4" size="4"></label>
							</div>
							<div class="where col-sm" style="padding-top: 15px;">
								<input type="submit" value="搜尋">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<section class="campsites">
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
					<a href=""><img
						src="https://images.unsplash.com/photo-1557292916-eaa52c7e5939?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Y2FtcHNpdGV8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"></a>
					<a class="text" href="">將會是營區</a>
				</div>
				<div class="col-sm-4">
					<a href=""><img
						src="https://images.unsplash.com/photo-1465418138967-67a3d24f8085?ixid=MnwxMjA3fDB8MHxzZWFyY2h8OTF8fGNhbXBzaXRlfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"></a>
					<a class="text" href="">將會是營區</a>
				</div>
				<div class="col-sm-4">
					<a href=""><img
						src="https://images.unsplash.com/photo-1557292916-eaa52c7e5939?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Y2FtcHNpdGV8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"></a>
					<a class="text" href="">將會是營區</a>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-4">
					<a href=""><img
						src="https://images.unsplash.com/photo-1593093735059-eaf60625e6e2?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzZ8fGNhbXBzaXRlfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"></a>
					<a class="text" href="">將會是營區</a>
				</div>
				<div class="col-sm-4">
					<a href=""><img
						src="https://images.unsplash.com/photo-1557292916-eaa52c7e5939?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Y2FtcHNpdGV8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"></a>
					<a class="text" href="">將會是營區</a>
				</div>
				<div class="col-sm-4">
					<a href=""><img
						src="https://images.unsplash.com/photo-1593093735059-eaf60625e6e2?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzZ8fGNhbXBzaXRlfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"></a>
					<a class="text" href="">將會是營區</a>
				</div>
			</div>
			<div class="row">
				<div class="col-sm">
					<a href=""><div class="more">> 更多營區</div></a>
				</div>
			</div>
		</div>
	</section>

	<section class="products">
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<a href=""><img
						src="https://images.unsplash.com/photo-1444012104069-996724bf4a0a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=632&q=80"></a>
					<a class="text" href="">將會是商品</a>
				</div>
				<div class="col-sm-3">
					<a href=""><img
						src="https://images.unsplash.com/photo-1609168867437-3f09a1f04b8a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDN8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=600&q=60"></a>
					<a class="text" href="">將會是商品</a>
				</div>
				<div class="col-sm-3">
					<a href=""><img
						src="https://images.unsplash.com/photo-1474376962954-d8a681cc53b2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=634&q=80"></a>
					<a class="text" href="">將會是商品</a>
				</div>
				<div class="col-sm-3">
					<a href=""><img
						src="https://images.unsplash.com/photo-1520963959303-a5cc3bdf9260?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=701&q=80"></a>
					<a class="text" href="">將會是商品</a>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-3">
					<a href=""><img
						src="https://images.unsplash.com/photo-1444012104069-996724bf4a0a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=632&q=80"></a>
					<a class="text" href="">將會是商品</a>
				</div>
				<div class="col-sm-3">
					<a href=""><img
						src="https://images.unsplash.com/photo-1503469519549-4415016d57a3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"></a>
					<a class="text" href="">將會是商品</a>
				</div>
				<div class="col-sm-3">
					<a href=""><img
						src="https://images.unsplash.com/photo-1474376962954-d8a681cc53b2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=634&q=80"></a>
					<a class="text" href="">將會是商品</a>
				</div>
				<div class="col-sm-3">
					<a href=""><img
						src="https://images.unsplash.com/photo-1605620622858-ea62b0a2059c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDF8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=600&q=60"></a>
					<a class="text" href="">將會是商品</a>
				</div>
			</div>
			<div class="row">
				<div class="col-sm">
					<a href=""><div class="more">> 更多商品</div></a>
				</div>
			</div>
		</div>
	</section>

	<section class="articles">
		<div class="container">
			<div class="row">
				<div class="col-sm">
					<a href=""><div class="article">
							<h5>Title標題標題</h5>
							<p>One column內文部分內容，可能可以放100字...</p>
							<p>> 看更多</p>
						</div></a>
				</div>
			</div>
			<div class="row">
				<div class="col-sm">
					<a href=""><div class="article">
							<h5>Title標題標題</h5>
							<p>One column內文部分內容，可能可以放100字...</p>
							<p>> 看更多</p>
						</div></a>
				</div>
			</div>
			<div class="row">
				<div class="col-sm">
					<a href=""><div class="article">
							<h5>Title標題標題</h5>
							<p>One column內文部分內容，可能可以放100字...</p>
							<p>> 看更多</p>
						</div></a>
				</div>
			</div>
			<div class="row">
				<div class="col-sm">
					<a href=""><div class="more">> 更多文章</div></a>
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



	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

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
</body>

</html>