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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
  div{
  width:1000px;
  height:500px;
  border:3px;
  margin:0px auto;
  }
  

</style>

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
		<td>文章編號:<font color=red></font></td>
		<td><%=articleVO.getArt_no()%></td>
	</tr>
	<tr>
		<td>發文作者:</td>
		<td><input type="TEXT" name="mbr_no" size="45"	value="<%=articleVO.getMbr_no()%>" /></td>
	</tr>
	
				<jsp:useBean id="bd_clSvc" scope="page"
				class="com.board_class.model.Board_ClassService" />
			<tr>
				<td>發文看板:<font color=red></font></td>
				<td><select size="1" name="bd_cl_no">
						<c:forEach var="board_classVO" items="${bd_clSvc.all}">
							<option value="${board_classVO.bd_cl_no}"
								${(articleVO.bd_cl_no==board_classVO.bd_cl_no)? 'selected':'' }>${board_classVO.bd_name}
						</c:forEach>
				</select></td>
			</tr>


	<tr>
		<td>發表時間:</td>
		<td><fmt:formatDate value="${articleVO.art_rel_time}" pattern="MM月dd日  HH:mm"/></td>
	</tr>
	<tr>
		<td>文章標題:</td>
		<td><input type="TEXT" name="art_title" size="45"	value="<%=articleVO.getArt_title()%>" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>文章內容:</td> -->
<%-- 		<td><input type="TEXT" name="art_cont" size="45" value="<%=articleVO.getArt_cont()%>" /></td> --%>
		
<!-- 	</tr> -->
	
			<tr>
				<td>文章內容:</td>
				<td><textarea id="art_cont" name="art_cont"></textarea></td>
			</tr>
	
	   
	<tr>
		<td>讚數:</td>
		<td><input type="TEXT" name="likes" size="45" value="<%=articleVO.getLikes()%>" /></td>
	</tr>
	<tr>
		<td>文章狀態:</td>
		<td><input type="TEXT" name="art_stat" size="45" value="<%=articleVO.getArt_stat()%>" /></td>
	</tr>



</table>
<br>

<input type="hidden" name="art_rel_time" value="<%=articleVO.getArt_rel_time()%>">
<input type="hidden" name="action" value="update">
<input type="hidden" name="art_no" value="<%=articleVO.getArt_no()%>">
<input type="submit" value="送出修改"></FORM>


<div id="summernote"></div>
    <script>
      $('#summernote').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 100
      });
    </script>


</body>

</html>