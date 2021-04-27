<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
</head>
<body>

<table id="table-1">
   <tr><td><h3> Article_Reply: Home</h3></td></tr>
</table>

<p>This is the Home page for Article_Reply</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
<!--   查看所有留言  必要性待商榷-->
  <li><a href='listAllArticle_Reply.jsp'>List</a> all Article_Replies.  <br><br></li>
  
  
  <!-- 查看留言的選擇1: 以留言編號選擇單一留言  : 必要 -->
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article_reply/article_reply.do" >
        <b>輸入留言編號 (如1):</b>
        <input type="text" name="art_rep_no">
        <input type="hidden" name="action" value="getOneReply_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="article_replySvc" scope="page" class="com.article_reply.model.Article_ReplyService" />
    <!-- 查看留言的選擇2: 以文章編號查看該文章所有留言  : 必要 --> 
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article_reply/article_reply.do" >
        <b>輸入文章編號 (如1):</b>
        <input type="text" name="art_no" >
        <input type="hidden" name="action" value="getOneArticle_Replies_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>


<h3>留言管理</h3>

<ul>
  <li><a href='addArticle_Reply.jsp'>Add</a> a new Article_Reply.</li>
</ul>



</body>
</html>