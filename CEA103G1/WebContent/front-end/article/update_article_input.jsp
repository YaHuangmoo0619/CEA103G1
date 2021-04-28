<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.article.model.*"%>

<%@ page import="java.sql.Timestamp"%>

<%
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>

<html>
<head>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<title>修改文章 - updateArticle.jsp前台</title>


</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>文章資料修改 - update_article_input.jsp 前台</h3>
 <h4><a href="/CEA103G1/back-end/article/select_page.jsp"><img src="/CEA103G1/images/Campion.png"
						width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="/CEA103G1/article/article.do" name="form1">
<table>


	<tr>
		<td>發表時間:</td>
		<td><fmt:formatDate value="${articleVO.art_rel_time}" pattern="MM月dd日  HH:mm"/></td>
	</tr>
	<tr>
		<td>文章標題:</td>
		<td><input type="TEXT" name="art_title" size="45"	value="<%=articleVO.getArt_title()%>" /></td>
	</tr>

	
			<tr>
				<td>文章內容:</td>
				<td><textarea id="art_cont" name="art_cont"><%=articleVO.getArt_cont()%></textarea></td>
			</tr>
	




</table>
<br>

<input type="hidden" name="mbr_no" size="45" value="<%=articleVO.getLikes()%>" />
<input type="hidden" name="bd_cl_no" size="45" value="<%=articleVO.getLikes()%>" />
<input type="hidden" name="likes" size="45" value="<%=articleVO.getLikes()%>" />
<input type="hidden" name="art_stat" size="45" value="<%=articleVO.getArt_stat()%>" />
<input type="hidden" name="art_rel_time" value="<%=articleVO.getArt_rel_time()%>">
<input type="hidden" name="action" value="update">
<input type="hidden" name="art_no" value="<%=articleVO.getArt_no()%>">
<input type="submit" value="送出修改"></FORM>


<!-- <div id="summernote"></div> -->
    <script>
      $('#art_cont').summernote({
        placeholder: '請輸入文字',
        tabsize: 2,
        height: 100,
  	    toolbar: [
		    // [groupName, [list of button]]
		    ['style', ['bold', 'italic', 'underline']],
		    ['fontsize', ['fontsize']],
		    ['color', ['color']],
		    ['para', ['paragraph']],
		    ['insert', ['link', 'picture']],
		  ]
      });
    </script>


</body>

</html>