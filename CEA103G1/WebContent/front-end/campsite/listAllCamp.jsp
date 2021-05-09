<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.campsite.model.*"%>
<%
	CampService campSvc = new CampService();
	List<CampVO> list = campSvc.getAll();
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
<%@ include file="/part-of/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerLeft_css.txt"%>
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
</style>
</head>
<body>
	<%@ include file="/part-of/partOfCampion_COwnerTop_body.txt"%>

	<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>


	<%-- ���~��C --%>
	<div class="container">
		<div class="row">
			<div class="left col-3">
				<%@ include file="/part-of/partOfCampion_COwnerLeft_body.txt"%></div>
			<div class="right col-9">
				<h3>�Ҧ���ϸ��</h3>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">�Эץ��H�U���~:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<table>
					<tr>
						<th>��Ͻs��</th>
						<th>��D�m�W</th>
						<th>��ϦW��</th>
						<th>��~���A</th>
						<th>�f�֪��A</th>
						<th>�a�}</th>
					</tr>
					<%@ include file="page1.file"%>
					<c:forEach var="campVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<tr>

							<td>${campVO.camp_no}</td>
							<td>${campVO.cso_no}</td>
							<td>${campVO.camp_name}</td>
							<c:if test="${campVO.campsite_Status==0}">
								<td><c:out value="������" /></td>
							</c:if>
							<c:if test="${campVO.campsite_Status==1}">
								<td><c:out value="����" /></td>
							</c:if>
							<c:if test="${campVO.review_Status==0}">
								<td><c:out value="�ݼf��" /></td>
							</c:if>
							<c:if test="${campVO.review_Status==1}">
								<td><c:out value="�q�L" /></td>
							</c:if>
							<c:if test="${campVO.review_Status==2}">
								<td><c:out value="���q�L" /></td>
							</c:if>
							<td>${campVO.address}</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/camp/camp.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="�d��" class="confirm"> <input
										type="hidden" name="camp_no"
										value="${campVO.camp_no}"> <input
										type="hidden" name="action" value="getOne_For_Display">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page2.file"%>
				<ul>
					<li><a href='addCamp.jsp'>�Z�n���</a></li>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/camp/camp.do">
						<b>��J�s��:</b> <input type="text" name="camp_no"> <input
							type="hidden" name="action" value="getOne_For_Display"> <input
							type="submit" value="�e�X">
					</FORM>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>