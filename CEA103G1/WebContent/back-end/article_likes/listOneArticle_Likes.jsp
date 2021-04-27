<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.article_likes.model.*"%>
<%@ page import="java.util.*"%>

<%int art_no = Integer.parseInt(request.getParameter("art_no")); %>
<%
  Article_LikesService article_likesSvc = new Article_LikesService();

  List<Article_LikesVO> list = article_likesSvc.findbyart_no(art_no);
  pageContext.setAttribute("list", list);
  
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>listOneArticle_Likes</title>
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
		 <h3>按讚資料 - listOneArticle_Likes.jsp</h3>
				 <h4><a href="/CEA103G1/back-end/article_likes/select_page.jsp"><img src="/CEA103G1/images/Campion.png"
						width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>


<table>
		<tr>

			<th>文章編號</th>
			<th>會員編號</th>

		</tr>
		
		
<%@ include file="pageforhome.file"%>
		<c:forEach var="article_likesVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>

			    <td>${article_likesVO.art_no}</td>
                <td>${article_likesVO.mbr_no}</td>		

			</tr>
		</c:forEach>
		
						
	</table>
	<%@ include file="page2.file"%>

</body>
</html>