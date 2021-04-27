<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.function.model.*"%>

<%
	FunctionService functionSvc = new FunctionService();
	List<FunctionVO> list = functionSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<meta charset="Big5">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="icon"
	href="<%=request.getContextPath()%>/images/campionLogoIcon.png"
	type="image/png">
<title>所有權限列表 - listAllFunction.jsp</title>

<style>
table#table-1 {
	background-color: #FFF;
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
	width: 80%;
	background-color: white;
	margin: 5px auto;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

div.update {
	width: 250px;
	padding: 10px;
	border: 1px solid #ccc;
	box-sizing: border-sizing;
	margin: 5px auto;
	background-color: #eee;
	text-align: center;
}

a {
	text-decoration: none;
}

div.update:hover {
	background-color: #98FB98;
	cursor: pointer;
	border: 1px solid #98FB98;
}

#back:hover {
	background-color: #98FB98;
	cursor: pointer;
}
</style>

<!-- 整塊style為上方導覽列的css及下方form的css及RWD -->
<style>
html, body {
	margin: 0;
	padding: 0;
	/*background-color: #4e5452;*/
	background-color: #4e5452;
	/*color: #80c344;*/
	color: #4B7F52;
}

div.fixedTop {
	background-color: #eee;
	position: relative;
	z-index: 10;
	left: 50%;
	transform: translateX(-50%);
	width: 100%;
	text-align: center;
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

form {
	border: none;
	padding: 15px;
}

@media screen and (max-width: 575px) {
	container {
		width: 100%;
	}
	div.fixedTop {
		background-color: #eee;
		position: relative;
		z-index: 10;
		left: 50%;
		transform: translateX(-50%);
		width: 100%;
		text-align: center;
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
		margin: 10px;
		display: inline-block;
	}
	div.sec {
		display: none;
		background-color: #eee;
	}
}

@media ( min-width : 576px) and (max-width: 767px) {
	container {
		width: 540px;
		margin: 0px auto;
	}
	div.fixedTop {
		background-color: #eee;
		position: relative;
		z-index: 10;
		left: 50%;
		transform: translateX(-50%);
		width: 100%;
		text-align: center;
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
		margin: 10px;
		display: inline-block;
	}

}

@media ( min-width : 768px) and (max-width: 991px) {
	container {
		width: 720px;
		margin: 0px auto;
	}
	div.fixedTop {
		background-color: #eee;
		position: relative;
		z-index: 10;
		left: 50%;
		transform: translateX(-50%);
		width: 100%;
		text-align: center;
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
		margin: 10px;
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
		margin: 20px auto;
		background-color: #eee;
		overflow: hidden;
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
		margin: 20px auto;
		background-color: #eee;
		overflow: hidden;
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
		margin: 20px auto;
		background-color: #eee;
		overflow: hidden;
	}
}
</style>
</head>
<body>
	<div class="fixedTop">
		<a
			href="<%=request.getContextPath()%>/back-end/function/select_page.jsp">
			<img
			src="<%=request.getContextPath()%>/front-images/campionLogoLong.png"
			class="logo">
		</a>
		<form class="form-inline my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="search"
				placeholder="營位/商品/文章" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜尋</button>
		</form>
		<img
			src="<%=request.getContextPath()%>/front-images/search-circle-outline.svg"
			class="searchIcon" onclick="showSearch()">
		<div class="btn-group" role="group" aria-label="Basic example">
			<button type="button" class="btn btn-secondary">營區</button>
			<button type="button" class="btn btn-secondary">商城</button>
			<button type="button" class="btn btn-secondary">論壇</button>
		</div>
		<img src="<%=request.getContextPath()%>/front-images/cart-outline.svg"
			class="cart">
		<div class="btn-group" role="group" aria-label="Basic example">
			<button type="button" class="btn btn-outline-secondary">營主</button>
			<button type="button" class="btn btn-outline-secondary">註冊</button>
			<button type="button" class="btn btn-outline-secondary">登入</button>
			<button type="button" class="btn btn-outline-secondary">FAQ</button>
			<button type="button" class="btn btn-outline-secondary">聯絡我們</button>
		</div>
		<img src="<%=request.getContextPath()%>/front-images/menu-outline.svg"
			class="menu" onclick="showMenu()"> <img
			src="<%=request.getContextPath()%>/front-images/person-circle-outline.svg"
			class="person">

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
			<button type="button" class="btn btn-secondary">營區</button>
			<button type="button" class="btn btn-secondary">商城</button>
			<button type="button" class="btn btn-secondary">論壇</button>
		</div>
		<div class="btn-group sec" role="group" aria-label="Basic example">
			<button type="button" class="btn btn-outline-secondary">營主</button>
			<button type="button" class="btn btn-outline-secondary">註冊</button>
			<button type="button" class="btn btn-outline-secondary">登入</button>
			<button type="button" class="btn btn-outline-secondary">FAQ</button>
			<button type="button" class="btn btn-outline-secondary">聯絡我們</button>
		</div>
	</div>
	<div class="backToTop">
		<a href="#"><img
			src="<%=request.getContextPath()%>/front-images/arrow-up-circle-outline.svg"
			class="backToTop"></a>
	</div>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有權限列表 - listAllFunction.jsp</h3>
				<div class="update">
					<a href="#focus" style="text-decoration: none;">查看當筆新增或修改的資料</a><a
						id="first" style="text-decoration: none;"></a>
				</div>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>權限編號</th>
			<th>權限名稱</th>

		</tr>
		<c:forEach var="functionVO" items="${list}">
			<tr
				${(functionVO.fx_no==param.fx_no || functionVO.fx_no==fx_no) ? 'bgcolor=#98FB98' : '' }>
				<c:if
					test="${functionVO.fx_no==param.fx_no || functionVO.fx_no==fx_no}">
					<td>${functionVO.fx_no}<a id="focus"></a></td>
				</c:if>
				<c:if
					test="${functionVO.fx_no!=param.fx_no && functionVO.fx_no!=fx_no}">
					<td>${functionVO.fx_no}</td>
				</c:if>
				<td>${functionVO.fx_name}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/function/function.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="fx_no" value="${functionVO.fx_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/function/function.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="fx_no" value="${functionVO.fx_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
				<td id="back" style="width: 80px;"><a href="#first"
					style="font-size: 0.5em;">回到第一筆</a></td>
			</tr>
		</c:forEach>
	</table>
	<script>
		let countMenu = 0;
		function showMenu() {
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
		}

		let countSearch = 0;
		function showSearch() {
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
		}

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

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous"></script>

</body>
</html>