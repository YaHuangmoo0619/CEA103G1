<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>

<%
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list = articleSvc.getAll_Back();
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
<title>列出所有文章</title>

<style>
body {
  font-family: "Open Sans", sans-serif;
  line-height: 1.25;
}

table {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: 0;
  padding: 0;
  width: 100%;
  table-layout: fixed;
}

table caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

table tr {
  background-color: #f8f8f8;
  border: 1px solid #ddd;
  padding: .35em;
}

table th,
table td {
  padding: .625em;
  text-align: center;
}

table th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
}

@media screen and (max-width: 600px) {
  table {
    border: 0;
  }

  table caption {
    font-size: 1.3em;
  }
  
  table thead {
    border: none;
    clip: rect(0 0 0 0);
    height: 1px;
    margin: -1px;
    overflow: hidden;
    padding: 0;
    position: absolute;
    width: 1px;
  }
  
  table tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: .625em;
  }
  
  table td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: .8em;
    text-align: right;
  }
  
  table td::before {
    /*
    * aria-label has no advantage, it won't be read inside a table
    content: attr(aria-label);
    */
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
  }
  
  table td:last-child {
    border-bottom: 0;
  }
}
</style>

</head>
<body bgcolor='white'>



				<h3>文章管理</h3>


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
			<th>作者</th>
			<th>發表時間</th>
			<th>文章標題</th>
			<th>讚數</th>
			<th>文章狀態</th>
			<th>刪除</th>
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
					href="<%=request.getContextPath()%>/article/article.do?art_no=${articleVO.art_no}&action=getOne_From">${articleVO.art_title}</a></td>
<%-- 				<td>${fn:replace(articleVO.art_cont,vEnter,"<br>")}</td> --%>
						<td>${articleVO.likes}</td>
				<td><c:if test="${articleVO.getArt_stat() == 0}">顯示</c:if> <c:if
						test="${articleVO.getArt_stat() == 1}">不顯示</c:if></td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/article/article.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="art_no" value="${articleVO.art_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
				<td>
					<input type="button" value="設為公告">
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
	


</body>
</html>