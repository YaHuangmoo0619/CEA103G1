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
		<h1>�Ҧ���ϸ��</h1>
		<h3>
			<a href="<%=request.getContextPath()%>/back-end/select_page.jsp">�^����</a>
		</h3>
	</div>

	<%-- ���~��C --%>
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
			<th>��ϸ�T</th>
			<th>�`�N�ƶ�</th>
			<th>�t�m��</th>
			<th>�f�֪��A</th>
			<th>����</th>
			<th>�L�u�q�T</th>
			<th>�d��</th>
			<th>��ϳ]�I</th>
			<th>��~��</th>
			<th>�����覡</th>
			<th>�a�}</th>
			<th>�n�g��</th>
			<th>�`�P��</th>
			<th>�`���׼�</th>
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
				<td>${campVO.campInfo}</td>
				<td>${campVO.note}</td>
				<td><img src="/CEA103G1/camp/campconfig.do?camp_no=${campVO.camp_no}"></td>
				<c:if test="${campVO.review_Status==0}">
					<td><c:out value="�ݼf��" /></td>
				</c:if>
				<c:if test="${campVO.review_Status==1}">
					<td><c:out value="�q�L" /></td>
				</c:if>
				<c:if test="${campVO.review_Status==2}">
					<td><c:out value="���q�L" /></td>
				</c:if>
				<td>${campVO.height}</td>
				<td>${campVO.wireless}</td>
				<c:if test="${campVO.pet==0}">
					<td><c:out value="���i��a�d��" /></td>
				</c:if>
				<c:if test="${campVO.pet==1}">
					<td><c:out value="�i��a�d��" /></td>
				</c:if>
				<td>${campVO.facility}</td>
				<c:if test="${campVO.operate_Date==0}">
					<td><c:out value="����" /></td>
				</c:if>
				<c:if test="${campVO.operate_Date==1}">
					<td><c:out value="����" /></td>
				</c:if>
				<c:if test="${campVO.operate_Date==2}">
					<td><c:out value="������" /></td>
				</c:if>
				<td>${campVO.park}</td>
				<td>${campVO.address}</td>
				<td>${campVO.latitude}, ${campVO.longitude}</td>
				<td>${campVO.total_Stars}</td>
				<td>${campVO.total_Cmnt}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/camp/camp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="camp_no" value="${campVO.camp_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/camp/camp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="camp_no" value="${campVO.camp_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
	<ul>
  <li><a href='addCamp.jsp'>�Z�n���</a></li>
  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camp/camp.do" >
        <b>��J�s��:</b>
        <input type="text" name="camp_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
</ul>
</body>
</html>