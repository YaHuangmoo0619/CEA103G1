<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<link rel="icon" href="<%=request.getContextPath() %>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>測試頁-營家campion</title>
<style>
html, body {
	margin: 0;
	padding: 0;
	background-color: #4e5452;
	color: #80c344;
	position: relative;
}
a {
	text-decoration: none;
	color: #eee;
	margin: 10px;
}

a:hover {
	text-decoration: none;
	color: #80c344;
}

div.toFrontPage{
	background-color: #eee;
	text-align: center;
}

div.container{
	margin-top: 20px;
	margin-bottom: 20px;
	padding: 10px 50px 10px 50px;
	border: solid 1px #eee;
}

h1.back{
	color: #eee;
	margin-left: 5%
}

div.front-end{
	margin-top: 20px;
	margin-bottom: 20px;
	padding: 10px 50px 10px 50px;
	border: solid 1px #80c344;
}

h1.front{
	margin-left: 5%
}

div.footer{
	text-align: center;
	font-size: 2em;
	background-color: #4B7F52;
	padding: 20px 0px 20px 0px;
	width: 100%;
	font-weight: 999;
}
</style>
</head>
<body>
<div class="toFrontPage">
	--測試連結網頁--
  <a class="navbar-brand" href="campion_front.html">
    <img src="campionLogoLong.png" width="20%">
  </a>
  	--測試連結網頁--
</div>
<h1 class="back">back-end</h1>
<div class="container back-end">
	<div class="row">
		<div class="col">
			<div class="container YaHuang">
				<div class="row">
					<div class="col-1 name">YaHuang</div>
					<div class="col-11">
					<a href="<%=request.getContextPath() %>/back-end/announcement/select_page.jsp">announcement</a>
					<a href="<%=request.getContextPath() %>/back-end/function/select_page.jsp">function</a>
					<a href="<%=request.getContextPath() %>/back-end/employee/select_page.jsp">employee</a>
					</div>
				</div>
			</div>
			<div class="container DuTe">
				<div class="row">
					<div class="col-1 name">DuTe</div>
					<div class="col-11">
					<a href="<%=request.getContextPath() %>/back-end/feature_list/listAllFeature_list.jsp">feature_list</a>
					<a href="<%=request.getContextPath() %>/back-end/campsite/listAllCamp.jsp">campsite</a>
					<a href="<%=request.getContextPath() %>/back-end/place_order/listAllPlace_order.jsp">place_order</a>
					</div>
				</div>
			</div>
			<div class="container Jun">
				<div class="row">
					<div class="col-1 name">Jun</div>
					<div class="col-11">
					<a href="<%=request.getContextPath() %>/back-end/member_rank/select_page.jsp">member_rank</a>
					</div>
				</div>
			</div>
			<div class="container Drake">
				<div class="row">
					<div class="col-1 name">Drake</div>
					<div class="col-11">
					<a href="<%=request.getContextPath() %>/back-end/article/select_page.jsp">article</a>
					<a href="<%=request.getContextPath() %>/back-end/article_likes/select_page.jsp">article_likes</a>
					<a href="<%=request.getContextPath() %>/back-end/article_picture/select_page.jsp">article_picture</a>
					<a href="<%=request.getContextPath() %>/back-end/article_reply/select_page.jsp">article_reply</a>
					<a href="<%=request.getContextPath() %>/back-end/board_class/select_page.jsp">board_class</a>
					<a href="<%=request.getContextPath() %>/back-end/follow/select_page.jsp">follow</a>
					</div>
				</div>
			</div>
			<div class="container TC">
				<div class="row">
					<div class="col-1 name">TC</div>
					<div class="col-11">
					<a href="<%=request.getContextPath() %>/back-end/product_category/select_page.jsp">product_category</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<h1 class="front">front-end</h1>
<div class="container front-end">
	<div class="row">
		<div class="col">
			<div class="container YaHuang">
				<div class="row">
					<div class="col-1 name">YaHuang</div>
					<div class="col-11">
					
					</div>
				</div>
			</div>
			<div class="container DuTe">
				<div class="row">
					<div class="col-1 name">DuTe</div>
					<div class="col-11">
					<a href="<%=request.getContextPath() %>/front-end/NewFile.html">NewFile.html</a>
					</div>
				</div>
			</div>
			<div class="container Jun">
				<div class="row">
					<div class="col-1 name">Jun</div>
					<div class="col-11">
					
					</div>
				</div>
			</div>
			<div class="container Drake">
				<div class="row">
					<div class="col-1 name">Drake</div>
					<div class="col-11">
					
					</div>
				</div>
			</div>
			<div class="container TC">
				<div class="row">
					<div class="col-1 name">TC</div>
					<div class="col-11">
					
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="footer">We are Campion!!!</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</body>
</html>