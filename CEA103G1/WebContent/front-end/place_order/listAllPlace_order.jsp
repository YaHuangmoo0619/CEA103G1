<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.place_order.model.*"%>

<%-- <% --%>
<!-- // 	Place_OrderService place_orderSvc = new Place_OrderService(); -->
<!-- // 	List<Place_OrderVO> list = place_orderSvc.getAll(); -->
<!-- // 	pageContext.setAttribute("list", list); -->
<!-- // 	Place_OrderVO place_orderVO = (Place_OrderVO) request.getAttribute("place_orderVO"); -->
<%-- %> --%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>營位訂單管理</title>
<%@ include file="/part-of/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
<style>
body {
	background-color: #4e5452;
	color: #4e5452;
}

div.left {
	margin-top: 20px;
}

div.right {
	background-color: #fff;
	margin-top: 40px;
	padding: 50px 50px;
	border-radius: 5px;
}

a.content {
	color: #80c344;
}

a.content:hover {
	color: #4B7F52;
}

form {
	margin: 0px auto;
}

span {
	color: #80c344;
}
input.confirm{
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}
input.confirm:hover{
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}
</style>
</head>
<body>
	<%@ include file="/part-of/partOfCampion_COwnerTop_body.txt"%>

	<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>

	<%-- 錯誤表列 --%>
	<!-- 	<div class="container"> -->
	<!-- 		<div class="row"> -->
	<!-- 			<div class="left col-3"> -->
	<%-- 				<%@ include file="/part-of/partOfCampion_COwnerLeft_body.txt"%></div> --%>
	<!-- 			<div class="left col-9">123</div> -->

	<!-- 		</div> -->
	<!-- 	</div> -->
	<div class="container">
		<div class="row">
			<div class="left col-3">
				<%@ include file="/part-of/partOfCampion_COwnerLeft_body.txt"%></div>
			<div class="right col-9">
				<h3>所有訂單</h3>
				<div style="display: inline-block;">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/place_order/place_order.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="現有訂單" class="confirm"><input type="hidden"
							name="action" value="listPresent">
					</FORM>
				</div>
				<div style="display: inline-block;">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/place_order/place_order.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="歷史訂單" class="confirm"><input type="hidden"
							name="action" value="listHistory">
					</FORM>
				</div>
				<%
					if (request.getAttribute("list") != null) {
						String path = (String) request.getAttribute("list");
						System.out.println(path);
				%>
				<jsp:include page="<%=path%>" />
				<%
					} else {
				%>
				<jsp:include page="PresentPlace_order.jsp" />
				<%
					}
				%>
			</div>
		</div>
	</div>
	<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
</body>
</html>