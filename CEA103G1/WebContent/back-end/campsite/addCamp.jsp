<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite.model.*"%>

<%
	CampVO campVO = (CampVO) request.getAttribute("campVO");
%>
<%-- <%=campVO == null%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
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
<body>
	<div>
		<h1>��Ϸs�W</h1>

		<h3>
			<a
				href="<%=request.getContextPath()%>/back-end/campsite/listAllCamp.jsp">�^�M��</a>
		</h3>
	</div>

	<h3>��Ʒs�W:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camp/camp.do"
		name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>��D�s��:</td>
				<td><input type="TEXT" name="cso_no" size="45"
					value="<%=(campVO == null) ? "70001" : campVO.getCso_no()%>" /></td>
			</tr>
			<tr>
				<td>��ϦW��:</td>
				<td><input type="TEXT" name="camp_name" size="45"
					value="<%=(campVO == null) ? "" : campVO.getCamp_name()%>" /></td>
			</tr>
			<tr>
				<td>��ϸ�T:</td>
				<td><input type="TEXT" name="campInfo" size="45"
					value="<%=(campVO == null) ? "�L" : campVO.getCampInfo()%>" /></td>
			</tr>
			<tr>
				<td>�`�N�ƶ�:</td>
				<td><input type="TEXT" name="note" size="45"
					value="<%=(campVO == null) ? "�L" : campVO.getNote()%>" /></td>
			</tr>
			<tr>
				<td>�t�m��:</td>
				<td><input type="file" name="config"><input type="hidden" name="image" value="<%=(campVO == null) ? "�L" : campVO.getConfig()%>"></td>
			</tr>
			<tr>
				<td>�L�u�q�T:</td>
				<td><input type="checkbox" name="wireless" value="��wifi">wifi

					<input type="checkbox" name="wireless" value="���عq�H���T�� ">
					���عq�H <input type="checkbox" name="wireless" value="���Ǧ��T��">
					���� <input type="checkbox" name="wireless" value="�x���j���T��">
					�x���j <input type="checkbox" name="wireless" value="�ȤӦ��T��">
					�Ȥ� <input type="checkbox" name="wireless" value="�x�W���P���T��">
					�x�W���P</td>
			</tr>
			<tr>
				<td>�d��:</td>
				<td><input type="radio" name="pet" value="0"
					check="<%=(campVO == null) ? "" : (campVO.getPet() == 0 ? "true" : "")%>">���i��a�d��
					<input type="radio" name="pet" value="1"
					check="<%=(campVO == null) ? "" : (campVO.getPet() == 1 ? "true" : "")%>">�i��a�d��</td>
			</tr>
			<tr>
				<td>��ϳ]�I:</td>
				<td><input type="TEXT" name="facility" size="45"
					value="<%=(campVO == null) ? "�L" : campVO.getFacility()%>" /></td>
			</tr>
			<tr>
				<td>��~��:</td>
				<td><input type="radio" name="operate_Date" value="0"
					check="<%=(campVO == null) ? "" : (campVO.getOperate_Date() == 0 ? "true" : "")%>">�u������
					<input type="radio" name="operate_Date" value="1"
					check="<%=(campVO == null) ? "" : (campVO.getOperate_Date() == 1 ? "true" : "")%>">�u������
					<input type="radio" name="operate_Date" value="2"
					check="<%=(campVO == null) ? "" : (campVO.getOperate_Date() == 2 ? "true" : "")%>">���B�������~</td>
			</tr>
			<tr>
				<td>�����覡:</td>
				<td><input type="TEXT" name="park" size="45"
					value="<%=(campVO == null) ? "��������" : campVO.getPark()%>" /></td>
			</tr>
			<tr>
				<td>����:</td>
				<td><input name="county" type="text"
					value="<%=(campVO == null) ? "��饫" : campVO.getCounty()%>"></td>
			</tr>
			<tr>
				<td>�m����:</td>
				<td><input name="district" type="text"
					value="<%=(campVO == null) ? "���c��" : campVO.getDistrict()%>"></td>
			</tr>
			<tr>
				<td>�a�}:</td>
				<td><input name="address" type="text"
					value="<%=(campVO == null) ? "�_����46��" : campVO.getAddress()%>"></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="�e�X�s�W">
	</FORM>
</body>
</html>