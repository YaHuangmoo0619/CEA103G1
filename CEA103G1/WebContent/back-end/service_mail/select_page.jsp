<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-tw">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>Service_mail: Home</title>
<%@ include file="/part-of/partOfCampion_backTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_backLeft_css.txt"%>
<style>
body{
	background-color: #4e5452;
	color: #4e5452;
}
div.left{
	margin-top: 20px;
}
div.right{
	background-color: #fff;
	margin-top: 40px;
	padding: 50px 50px;
	border-radius: 5px;
}
a.content{
	color: #80c344;
}
a.content:hover {
	color: #4B7F52;
}

form{
	margin: 0px auto;
}

span{
	color: #80c344;
}
</style>
</head>
<body>
<%@ include file="/part-of/partOfCampion_backTop_body.txt"%>
<div class="container">
	<div class="row">
		<div class= "left col-3">
		<%@ include file="/part-of/partOfCampion_backLeft_body.txt"%></div>
		<div class="right col-9">
			<h2>This is the Home page for Service_mail</h2>
			<hr>
			<h3>資料查詢:</h3>
			<%-- 錯誤列表 map ${errorMsgs}--%>
			
			<ul>
				<li>
					<a class="content" href="<%=request.getContextPath() %>/back-end/service_mail/listAllService_mail.jsp">List</a><br>
				</li>
				
			</ul>
<!-- 		<h3>網站管理員權限管理:</h3>
			<ul>
				<li>
					<a class="content" href="<%=request.getContextPath()%>/authority/authority.do">add</a>
				</li>
			</ul> -->
		</div>
	</div>
</div>
</body>
</html>