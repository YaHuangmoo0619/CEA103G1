<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.campsite.model.*"%>
<%
	CampService campSvc = new CampService();
	List<CampVO> camplist = campSvc.getAll();
	List<CampVO> list = new ArrayList();
	for(CampVO campVO : camplist){
		if(campVO.getReview_Status()==0){
			list.add(campVO);
		}
	}
	pageContext.setAttribute("list", list);
	CampVO campVO = (CampVO) request.getAttribute("campVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<link rel="icon"
	href="<%=request.getContextPath()%>/images/campionLogoIcon.png"
	type="image/png">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>Insert title here</title>
<%@ include file="/part-of/partOfCampion_backTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_backLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
<style>
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

#camp_plc tr td input {
	width: 80%;
}
th, td {
	text-align: left;
	/* 	border: 1px solid #4e5452; */
	padding: 10px 10px;
	border-bottom:solid 1px;
}
</style>
</head>
<body>
	<%@ include file="/part-of/partOfCampion_backTop_body.txt"%>

	<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>


	<%-- 錯誤表列 --%>
	<div class="container">
		<div class="row">
			<div class="left col-3">
				<%@ include file="/part-of/partOfCampion_backLeft_body.txt"%></div>
			<div class="right col-9">
			<h3>未審核營區</h3>
				<div style="display: inline-block;">
					<a href="UnreviewCamp.jsp">未審核營區</a>
				</div>
				<div style="display: inline-block;">
					<a href="ReviewedCamp.jsp">已審核營區</a>
				</div>
				<div style="display: inline-block;">
					<a href="<%=request.getContextPath()%>/back-end/place_order/UnpayPlace_order.jsp">未付款訂單</a>
				</div>
				<div style="display: inline-block;">
					<a href="<%=request.getContextPath()%>/back-end/place_order/PaidPlace_order.jsp">已付款訂單</a>
				</div>
				<table>
				<table>
					<tr>
						<th>營區編號</th>
						<th>營主姓名</th>
						<th>營區名稱</th>
						<th>審核狀態</th>
						<th>地址</th>
					</tr>
					<c:forEach var="campVO" items="${list}">
						<tr>

							<td>${campVO.camp_no}</td>
							<td>${campVO.cso_no}</td>
							<td>${campVO.camp_name}</td>
							<c:if test="${campVO.review_Status==0}">
								<td><c:out value="待審核" /></td>
							</c:if>
							<c:if test="${campVO.review_Status==1}">
								<td><c:out value="通過" /></td>
							</c:if>
							<c:if test="${campVO.review_Status==2}">
								<td><c:out value="未通過" /></td>
							</c:if>
							<td>${campVO.address}</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/camp/camp.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="查看" class="confirm"> <input
										type="hidden" name="camp_no"
										value="${campVO.camp_no}"> <input
										type="hidden" name="action" value="getOne_For_DisplayFromBack">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>