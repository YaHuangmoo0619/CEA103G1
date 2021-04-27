<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	CampVO campVO = (CampVO) request.getAttribute("campVO");
%>

<html>
<head>
<title>��ϸ��</title>

<style>
img {
	width: 150px;
	height: 100px;
}
table, th, td {
	border: 1px solid black;
	background-color: white;
	text-align: center;
}

div {
	background-color: lightgreen;
}
</style>
</head>
<body bgcolor='white'>
	<div>
		<h1>��ϸ��</h1>
		<h3>
			<a href="<%=request.getContextPath()%>/back-end/campsite/listAllCamp.jsp">�^�M��</a>
		</h3>
	</div>
	<table>
		<tr>
			<th>��D�s��</th>
			<td>${campVO.cso_no}</td>
		</tr>
		<tr>
			<th>��ϦW��</th>
			<td>${campVO.camp_name}</td>
		</tr>
		<tr>
			<th>��ϸ�T</th>
			<td>${campVO.campInfo}</td>
		</tr>
		<tr>
			<th>�`�N�ƶ�</th>
			<td>${campVO.note}</td>
		</tr>
		<tr>
			<th>�t�m��</th>
			<td><img
				src="<%=request.getContextPath()%>/camp/campconfig.do?camp_no=${campVO.camp_no}"></td>
		</tr>
		<tr>
			<th>����</th>
			<td>${campVO.height}</td>
		</tr>
		<tr>
			<th>�L�u�q�T</th>
			<td>${campVO.wireless}</td>
		</tr>
		<tr>
			<th>�d��</th>
			<c:if test="${campVO.pet==0}">
				<td><c:out value="���i��a�d��" /></td>
			</c:if>
			<c:if test="${campVO.pet==1}">
				<td><c:out value="�i��a�d��" /></td>
			</c:if>
		</tr>
		<tr>
			<th>��ϳ]�I</th>
			<td>${campVO.facility}</td>
		</tr>
		<tr>
			<th>��~��</th>
			<td>${(campVO.operate_Date >= 1) ? ((campVO.operate_Date ==2) ? "������" : "����") : "����"}</td>
		</tr>
		<tr>
			<th>�����覡</th>
			<td>${campVO.park}</td>
		</tr>
		<tr>
			<th>�a�}</th>
			<td>${campVO.address}</td>
		</tr>
		<tr>
			<th>�n�g��</th>
			<td>${campVO.latitude} , ${campVO.longitude}</td>
		</tr>
	</table>

</body>
</html>