<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.board_class.model.*"%>

<%
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list = articleSvc.getAll_Front();
	pageContext.setAttribute("list", list);
%>

<%
	Board_ClassService board_classSvc = new Board_ClassService();
	List<Board_ClassVO> bd_list = board_classSvc.getAll();
	pageContext.setAttribute("bd_list", bd_list);
%>

<%
	request.setAttribute("vEnter", "\n");
%>

<jsp:useBean id="bd_clSvc" scope="page"
	class="com.board_class.model.Board_ClassService" />
<jsp:useBean id="bd_clDAO" scope="page" class="com.board_class.model.Board_ClassDAO" />

<html>
<head>
<meta charset="Big5">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
<link rel="icon" href="campionLogoIcon.png" type="image/png">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>列出所有文章</title>
  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/simple-sidebar.css" rel="stylesheet">


<style>



html, body {
	margin: 0;
	padding: 0;
	/*background-color: #4e5452;*/
	background-color: #4e5452;
	color: #80c344;
}

section {
	text-align: center;
}

img.logo {
	width: 100px;
	margin: 10px;
	margin-left: 100px;
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
		margin: 10px;
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
		color: #fff;
		font-size: 2em;
		font-weight: 999;
		margin: 50px auto;
	}
	div.photo {
		width: 200px;
		height: 100px;
		margin: 20px auto;
		background-color: #eee;
		overflow: hidden;
	}
	div.row {
		margin-top: 0px;
	}
	div.article {
		width: 90%;
		margin: 20px auto;
		text-align: left;
		background-color: #eee;
		border-radius: 5px;
		padding: 10px 50px 20px 50px;
	}
	div.more {
		display: inline;
	}
	section.footer {
		background-color: #4B7F52;
		color: #80c344;
		height: 100px;
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
		margin: 10px;
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


#sidebar-wrapper{
	width:208px;
	height:2195px;
	padding:0px;
	border:0px;
	margin:0px;
	position:fixed;
}


</style>

</head>
<body bgcolor='white'>



	<div style="background-color: #eee;">
		<img src="/CEA103G1/campionLogoLong.png" class="logo">
		<form class="form-inline my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="search"
				placeholder="營位/商品/文章" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜尋</button>
		</form>
		<img src="/CEA103G1/search-circle-outline.svg" class="searchIcon"
			onclick="showSearch()">
		<div class="btn-group" role="group" aria-label="Basic example">
			<button type="button" class="btn btn-secondary">營區</button>
			<button type="button" class="btn btn-secondary">商城</button>
			<button type="button" class="btn btn-secondary">論壇</button>
		</div>
		<img src="/CEA103G1/cart-outline.svg" class="cart">
		<div class="btn-group" role="group" aria-label="Basic example">
			<button type="button" class="btn btn-outline-secondary">註冊</button>
			<button type="button" class="btn btn-outline-secondary">登入</button>
			<button type="button" class="btn btn-outline-secondary">FAQ</button>
			<button type="button" class="btn btn-outline-secondary">聯絡我們</button>
		</div>
		<img src="/CEA103G1/menu-outline.svg" class="menu" onclick="showMenu()"> <img
			src="/CEA103G1/person-circle-outline.svg" class="person">

	</div>


    <div>
      <div>
      	<c:forEach var="board_classVO" items="${bd_list}">
        <a href="<%=request.getContextPath()%>/article/article.do?bd_cl_no=${board_classVO.bd_cl_no}&action=getOneArticle_ByBoard_Clss_For_Display">${board_classVO.bd_name}</a>
        <br>
        	</c:forEach>
      </div>
    </div>




	<table>
		<tr>
			<th>文章編號</th>
			<th>看板</th>
			<th>會員編號</th>
			<th>發表時間</th>
			<th>文章標題</th>
			<th>讚數</th>

		</tr>
		<%@ include file="pageforhome.file"%>
		<c:forEach var="articleVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${articleVO.art_no}</td>
			<td><c:forEach var="bd_clVO" items="${bd_clDAO.all}">
                    <c:if test="${articleVO.bd_cl_no==bd_clVO.bd_cl_no}">
	                    ${bd_clVO.bd_name}
                    </c:if>
                </c:forEach></td>
                
                
				<td>${articleVO.mbr_no}</td>
				<td><fmt:formatDate value="${articleVO.art_rel_time}" pattern="MM月dd日  HH:mm"/></td>
				<td><a href="<%=request.getContextPath()%>/article/article.do?art_no=${articleVO.art_no}&action=getOne_From">${articleVO.art_title}</a></td>

						<td>${articleVO.likes}</td>

			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>


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