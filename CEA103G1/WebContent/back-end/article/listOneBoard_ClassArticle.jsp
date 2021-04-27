<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.article.model.*"%>
<%@ page import="java.util.*"%>

<%int bd_cl_no = Integer.parseInt(request.getParameter("bd_cl_no")); %>
<%
  ArticleService articleSvc = new ArticleService();

  List<ArticleVO> list = articleSvc.getByBoard_Class(bd_cl_no);
  pageContext.setAttribute("list", list);
  
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>listOneBoard_ClassArticle.jsp</title>
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
	width: 600px;
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
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>listOneBoard_ClassArticle.jsp</h3>
				 <h4><a href="/CEA103G1/back-end/article/select_page.jsp"><img src="/CEA103G1/images/Campion.png"
						width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>


<table>
		<tr>
			<th>文章編號</th>
			<th>看板編號</th>
			<th>會員編號</th>
			<th>發表時間</th>
			<th>文章標題</th>
<!-- 			<th>文章內容</th> -->
			<th>文章狀態</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		
		
<%@ include file="pageforhome.file"%>
		<c:forEach var="articleVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${articleVO.art_no}</td>
			    <td>${articleVO.bd_cl_no}</td>
                <td>${articleVO.mbr_no}</td>
				<td><fmt:formatDate value="${articleVO.art_rel_time}" pattern="MM月dd日  HH:mm"/></td>
<%-- 			    <td>${articleVO.art_title}</td> --%>
				<td><a
					href="<%=request.getContextPath()%>/article/article.do?art_no=${articleVO.art_no}&action=getOne_From">${articleVO.art_title}</a></td>
<%-- 			    <td>${articleVO.art_cont}</td> --%>
				<td><c:if test="${articleVO.getArt_stat() == 0}">顯示</c:if> <c:if
						test="${articleVO.getArt_stat() == 1}">不顯示</c:if></td>


				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/article/article.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="art_no" value="${articleVO.art_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/article/article.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="art_no" value="${articleVO.art_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			

			</tr>
		</c:forEach>
		
						
	</table>
	<%@ include file="page2.file"%>

</body>
</html>