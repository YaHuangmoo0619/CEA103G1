<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.place_order.model.*"%>

<%
	Place_OrderService place_orderSvc = new Place_OrderService();
	List<Place_OrderVO> list = place_orderSvc.getAll();
	pageContext.setAttribute("list", list);
	Place_OrderVO place_orderVO = (Place_OrderVO) request.getAttribute("place_orderVO");
%>
<jsp:useBean id="campSvc" scope="page"
	class="com.campsite.model.CampService" />
<!DOCTYPE html>
<html>
<head>
<title>���q��޲z</title>
<style>
table, th, td {
	border: 0.1px solid black;
	background-color: white;
	text-align: center;
}

div {
	background-color: lightgreen;
}
#plc_ord_bo{
    background-color: white;
    border:0px;
}
</style>
</head>
<body>
	<div>
		<h1>�Ҧ��q��</h1>
		<h3>
			<a href="select_page.jsp">�^����</a>
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
			<th>�q��s��</th>
			<th>�|���m�W</th>
			<th>��ϦW��</th>
			<th>�q��ɶ�</th>
			<th>�J����</th>
			<th>�h�Ф��</th>
			<th>�J������</th>
			<th>�J����B</th>
			<th>�[�ʤH��</th>
			<th>�I�ڤ覡</th>
			<th>�I�ڪ��A</th>
			<th>�ϥ��I��</th>
			<th>�J���A</th>
			<th>�o���Φ�</th>
			<th>�q��Ƶ�</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="place_orderVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td><FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/place_order_details/place_order_details.do"
						style="margin-bottom: 0px;">
						<input type="submit" id="plc_ord_no" name="place_order_details" value="${place_orderVO.plc_ord_no}">
						<input type="hidden" name="action" value="getplace_order_details">
					</FORM></td>
				<td>${place_orderVO.mbr_no}</td>
				<td><c:forEach var="campVO" items="${campSvc.all}">
						<c:if test="${place_orderVO.camp_no==campVO.camp_no}">
	                    ${campVO.camp_name}
                    </c:if>
					</c:forEach></td>
				<td><fmt:formatDate value="${place_orderVO.plc_ord_time}"
						pattern="yyyy-MM-dd HH:mm:ss E" /></td>
				<td>${place_orderVO.ckin_date}</td>
				<td>${place_orderVO.ckout_date}</td>
				<td>${place_orderVO.plc_amt}</td>
				<td>${place_orderVO.plc_ord_sum}</td>
				<td>${place_orderVO.ex_ppl}</td>
				<c:if test="${place_orderVO.pay_meth==0}">
					<td><c:out value="�H�Υd" /></td>
				</c:if>
				<c:if test="${place_orderVO.pay_meth==1}">
					<td><c:out value="�״�" /></td>
				</c:if>
				<c:if test="${place_orderVO.pay_stat==0}">
					<td><c:out value="���I��" /></td>
				</c:if>
				<c:if test="${place_orderVO.pay_stat==1}">
					<td><c:out value="�w�I�q��" /></td>
				</c:if>
				<c:if test="${place_orderVO.pay_stat==2}">
					<td><c:out value="�w�I���B" /></td>
				</c:if>
				<td>${place_orderVO.used_pt}</td>
				<c:if test="${place_orderVO.ckin_stat==0}">
					<td><c:out value="���J��" /></td>
				</c:if>
				<c:if test="${place_orderVO.ckin_stat==1}">
					<td><c:out value="���X�u" /></td>
				</c:if>
				<c:if test="${place_orderVO.ckin_stat==2}">
					<td><c:out value="�w�J��" /></td>
				</c:if>
				<c:if test="${place_orderVO.ckin_stat==3}">
					<td><c:out value="�w�h��" /></td>
				</c:if>
				<c:if test="${place_orderVO.receipt==0}">
					<td><c:out value="�ȥ��o��" /></td>
				</c:if>
				<c:if test="${place_orderVO.receipt==1}">
					<td><c:out value="�q�l�o��" /></td>
				</c:if>
				<c:if test="${place_orderVO.receipt==2}">
					<td><c:out value="�o������" /></td>
				</c:if>
				<td>${place_orderVO.rmk}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="camp_no" value="${campVO.camp_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/emp/emp.do"
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
</body>
</html>