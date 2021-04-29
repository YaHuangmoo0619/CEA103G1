<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>


<%int bd_cl_no = Integer.parseInt(request.getParameter("bd_cl_no")); %>
<%
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list = articleSvc.getByBoard_Class_Front(bd_cl_no);
	pageContext.setAttribute("list", list);
%>

<%
	request.setAttribute("vEnter", "\n");
%>

<jsp:useBean id="bd_clSvc" scope="page"
	class="com.board_class.model.Board_ClassService" />
<jsp:useBean id="bd_clDAO" scope="page" class="com.board_class.model.Board_ClassDAO" />

<html>
<head>
<title>單一看板所有文章_前台</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

a {
	text-decoration: none
}

a:hover {
	text-decoration: none
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>單一看板所有文章_前台</h3>
				<h4>
					<a href="/CEA103G1/front-end/article/listAllArticle.jsp"><img src="/CEA103G1/images/Campion.png"
						width="100" height="100" border="0"></a>
				</h4>
			</td>
		</tr>
	</table>

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
			<th>文章編號</th>
			<th>看板</th>
			<th>會員編號</th>
			<th>發表時間</th>
			<th>文章標題</th>
			<th>讚數</th>

		</tr>
		<%@ include file="pageforhome.file"%>
		<c:forEach var="articleVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${articleVO.art_no}</td>
			<td><c:forEach var="bd_clVO" items="${bd_clDAO.all}">
                    <c:if test="${articleVO.bd_cl_no==bd_clVO.bd_cl_no}">
	                    ${bd_clVO.bd_name}
                    </c:if>
                </c:forEach></td>
                
                
				<td>${articleVO.mbr_no}</td>
				<td><fmt:formatDate value="${articleVO.art_rel_time}" pattern="MM月dd日  HH:mm"/></td>
				<td><a
					href="<%=request.getContextPath()%>/article/article.do?art_no=${articleVO.art_no}&action=getOne_From3&bd_cl_no=${articleVO.bd_cl_no}">${articleVO.art_title}</a></td>

						<td>${articleVO.likes}</td>

			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>


<c:if test="${openModal!=null}">

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">		
			<div class="modal-body">
<!-- =========================================以下為原listOneArticle.jsp的內容========================================== -->
               <jsp:include page="listOneArticle.jsp" />
<!-- =========================================以上為原listOneArticle.jsp的內容========================================== -->
			</div>	
		</div>
	</div>
</div>

 </c:if>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	     <script>
    		 $("#basicModal").modal({show: true});
        </script>

</body>
</html>