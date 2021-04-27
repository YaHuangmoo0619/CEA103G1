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
		<h1>所有營區資料</h1>
		<h3>
			<a href="<%=request.getContextPath()%>/back-end/select_page.jsp">回首頁</a>
		</h3>
	</div>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>營區編號</th>
			<th>營主姓名</th>
			<th>營區名稱</th>
			<th>營業狀態</th>
			<th>營區資訊</th>
			<th>注意事項</th>
			<th>配置圖</th>
			<th>審核狀態</th>
			<th>海拔</th>
			<th>無線通訊</th>
			<th>寵物</th>
			<th>營區設施</th>
			<th>營業日</th>
			<th>停車方式</th>
			<th>地址</th>
			<th>緯經度</th>
			<th>總星數</th>
			<th>總評論數</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="campVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>

				<td>${campVO.camp_no}</td>
				<td>${campVO.cso_no}</td>
				<td>${campVO.camp_name}</td>
				<c:if test="${campVO.campsite_Status==0}">
					<td><c:out value="不隱藏" /></td>
				</c:if>
				<c:if test="${campVO.campsite_Status==1}">
					<td><c:out value="隱藏" /></td>
				</c:if>
				<td>${campVO.campInfo}</td>
				<td>${campVO.note}</td>
				<td><img src="/CEA103G1/camp/campconfig.do?camp_no=${campVO.camp_no}"></td>
				<c:if test="${campVO.review_Status==0}">
					<td><c:out value="待審核" /></td>
				</c:if>
				<c:if test="${campVO.review_Status==1}">
					<td><c:out value="通過" /></td>
				</c:if>
				<c:if test="${campVO.review_Status==2}">
					<td><c:out value="未通過" /></td>
				</c:if>
				<td>${campVO.height}</td>
				<td>${campVO.wireless}</td>
				<c:if test="${campVO.pet==0}">
					<td><c:out value="不可攜帶寵物" /></td>
				</c:if>
				<c:if test="${campVO.pet==1}">
					<td><c:out value="可攜帶寵物" /></td>
				</c:if>
				<td>${campVO.facility}</td>
				<c:if test="${campVO.operate_Date==0}">
					<td><c:out value="平日" /></td>
				</c:if>
				<c:if test="${campVO.operate_Date==1}">
					<td><c:out value="假日" /></td>
				</c:if>
				<c:if test="${campVO.operate_Date==2}">
					<td><c:out value="平假日" /></td>
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
						<input type="submit" value="修改"> <input type="hidden"
							name="camp_no" value="${campVO.camp_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/camp/camp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="camp_no" value="${campVO.camp_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
	<ul>
  <li><a href='addCamp.jsp'>刊登營區</a></li>
  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camp/camp.do" >
        <b>輸入編號:</b>
        <input type="text" name="camp_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
</ul>
</body>
</html>