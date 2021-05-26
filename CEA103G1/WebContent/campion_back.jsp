<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*" %>

<!-- ���յn�J���A�εe������ -->
<%
// EmployeeService employeeSvcLogin = new EmployeeService();
// EmployeeVO employeeVOLogin = employeeSvcLogin.getOneEmployee(90002);
// session.setAttribute("employeeVO",employeeVOLogin);
%>

<% 
	EmployeeVO employeeVO = (EmployeeVO)session.getAttribute("employeeVO");
	if(employeeVO == null){
		response.sendRedirect(request.getContextPath()+"/campion_back_login.jsp");
		return;
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
<title>��aCampion</title>

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

div.login {
	background-color: #80c344;
	color: #4e5452;
	margin-top:15%;
	padding: 10px;
	border-radius: 5px;
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
	animation: showBox 3s;
}
@keyframes showBox
{
from{opacity:0;}
to{opacity: 1;}
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
		<c:if test="${employeeVO != null}">
			<div style="margin-right:5px;"><a href="<%=request.getContextPath()%>/back-end/employee/listOneEmployee_person.jsp?emp_no=${employeeVO.emp_no}"><div class="login">${employeeVO.name}�u�@��</div></a></div>
		</c:if>
		<c:if test="${employeeVO == null}">
			<div style="margin-right:5px;"><a href="<%=request.getContextPath()%>/front-end/login.html"><div class="login">�n�J</div></a></div>
		</c:if>
	</div>
	<div class="container">
		<div class="row">
			<div class="left col-3">
				<c:if test="${employeeVO != null}">
					<ul>
						<jsp:useBean id="authoritySvc" class="com.authority.model.AuthorityService"/>
						<c:forEach var="authorityVO" items="${authoritySvc.findByEmp_no(employeeVO.emp_no)}">
						<c:if test="${authorityVO.fx_no == 1}">
						<li><a href="<%=request.getContextPath()%>/back-end/employee/listAllEmployee.jsp">���u�޲z</a></li>
						</c:if>
						<c:if test="${authorityVO.fx_no == 2}">
						<li><a href="<%=request.getContextPath() %>/back-end/product/listAllProduct.jsp">�ӫ��޲z</a></li>
						</c:if>
						<c:if test="${authorityVO.fx_no == 3}">
						<li><a href="<%=request.getContextPath() %>/back-end/campsite/UnreviewCamp.jsp">���q�ʥ��x�޲z</a></li>
						</c:if>
						<c:if test="${authorityVO.fx_no == 4}">
						<li><a href="<%=request.getContextPath() %>/back-end/article_report/listAllArticle_Report.jsp">�׾º޲z</a></li>
						</c:if>
						<c:if test="${authorityVO.fx_no == 5}">
						<li><a href="<%=request.getContextPath() %>/back-end/campsite_owner/listAllCampsite_owner.jsp">�b���޲z</a></li>
						</c:if>
						<c:if test="${authorityVO.fx_no == 6}">
						<li><a href="<%=request.getContextPath() %>/back-end/announcement/listAllAnnouncement.jsp">���i�޲z</a></li>
						</c:if>
						<c:if test="${authorityVO.fx_no == 7}">
						<li><a href="<%=request.getContextPath() %>/back-end/service_mail/listAllService_mail.jsp">�ȪA�޲z</a></li>
						</c:if>
						</c:forEach>
						<li><a href="<%=request.getContextPath()%>/employee/employee.do?action=logout">�n�X</a></li>
					</ul>
				</c:if>
				<c:if test="${employeeVO == null}"><img style="max-width: 100%; margin-top: 30%;opacity:0.8;" src="<%=request.getContextPath()%>/images/campionLogoCircle.png"></c:if>
<!-- 				<ul> -->
<!-- 					<li><a -->
<%-- 						href="<%=request.getContextPath()%>/back-end/employee/select_page.jsp">���u�޲z</a></li> --%>
<%-- 					<li><a href="<%=request.getContextPath() %>/back-end/place_order/PresentPlace_order.jsp">���q�ʥ��x�޲z</a></li> --%>
<%-- 					<li><a href="<%=request.getContextPath() %>/back-end/product_category/select_page.jsp">�ӫ��޲z</a></li> --%>
<%-- 					<li><a href="<%=request.getContextPath() %>/back-end/article/select_page.jsp">�׾º޲z</a></li> --%>
<%-- 					<li><a href="<%=request.getContextPath() %>/back-end/member_rank/select_page.jsp">�b���޲z</a></li> --%>
<%-- 					<li><a href="<%=request.getContextPath() %>/back-end/announcement/select_page.jsp">���i�޲z</a></li> --%>
<%-- 					<li><a href="<%=request.getContextPath() %>/back-end/service_mail/listAllService_mail.jsp">�ȪA�޲z</a></li> --%>
<!-- 					<li><a href="">�Y�ɤp����޲z</a></li> -->
<!-- 				</ul> -->
			</div>
			<div class="right col-9">
				<div class="row">
					<div class="col">
					
					<!-- �H�U���e�i�H�������۰O�d��X�Ӫ����A�ηs�W�ק諸�e�� -->
						<div class="countBox">
							<a href="<%=request.getContextPath() %>/back-end/campsite_owner/listAllCampsite_owner.jsp"><h5>�Τ��`��</h5></a>
							<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
							<p>${memberSvc.getAll().size()}��|��</p>
							<jsp:useBean id="campsite_ownerSvc" class="com.campsite_owner.model.Campsite_ownerService"/>
							<p>${campsite_ownerSvc.getAll().size()}����D</p>
						</div>
					</div>
					<div class="col">
						<div class="countBox">
							<a href="<%=request.getContextPath()%>/back-end/product_order/listAllProduct_order_fromList.jsp"><h5>�ӫ~�q��</h5></a>
							<jsp:useBean id="product_orderSvc" class="com.product_order.model.Product_orderService"/>
							<p>�֭p�@��${product_orderSvc.getAll().size()}��</p>
							<p>�ӫ~�q��</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<div class="countBox">
							<a href="<%=request.getContextPath()%>/back-end/place_order/UnpayPlace_order.jsp"><h5>���q��</h5></a>
							<jsp:useBean id="place_orderSvc" class="com.place_order.model.Place_OrderService"/>
							<p>�֭p�@��${place_orderSvc.getAll().size()}��</p>
							<p>���q��</p>
						</div>
					</div>
					<div class="col">
						<div class="countBox">
							<a href="<%=request.getContextPath()%>/back-end/service_mail/listAllService_mail.jsp"><h5>�ȪA�H��</h5></a>
<%-- 							<jsp:useBean id="article_replay_reportSvc" class="com.article_replay_report.model.Article_Rep_ReportService"/> --%>
<%-- 							<jsp:useBean id="article_reportSvc" class="com.article_report.model.Article_ReportService"/> --%>
<%-- 							<jsp:useBean id="campsite_comment_reportSvc" class="com.campsite_comment_report.model.Campsite_comment_reportService"/> --%>
<%-- 							<jsp:useBean id="campsite_reportSvc" class="com.campsite_report.model.Campsite_reportService"/> --%>
<%-- 							<jsp:useBean id="member_reportSvc" class="com.member_report.model.Member_reportService"/> --%>
<%-- 							<jsp:useBean id="product_comment_reportSvc" class="com.product_comment_report.model.Product_comment_reportService"/> --%>
<%-- 							<jsp:useBean id="product_reportSvc" class="com.product_report.model.Product_reportService"/> --%>
<%-- 							<p>�ثe�|��${article_replay_reportSvc.getAll().size()+ --%>
<%-- 							article_reportSvc.getAll().size()+ --%>
<%-- 							campsite_comment_reportSvc.getAll().size()+ --%>
<%-- 							campsite_reportSvc.getAll().size()+ --%>
<%-- 							member_reportSvc.getAll().size()+ --%>
<%-- 							product_comment_reportSvc.getAll().size()+ --%>
<%-- 							product_reportSvc.getAll().size()}��</p> --%>
							<jsp:useBean id="service_mailSvc" class="com.service_mail.model.Service_mailService"/>
							<% Map<String,String[]> map = new LinkedHashMap<String,String[]>();
								map.put("mail_read_stat", new String[]{"0"});
								request.setAttribute("map",map);
							%>
							<p>�ثe�|��${service_mailSvc.getWhereCondition(map).size()}��</p>
							<p>�H��Ū��</p>
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