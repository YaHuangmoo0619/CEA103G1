<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article_reply.model.*"%>

<%@ page import="java.sql.Timestamp"%>

<%
	Article_ReplyVO article_replyVO = (Article_ReplyVO) request.getAttribute("article_replyVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>文章留言資料修改 - update_article_reply_input.jsp</title>

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
		 <h3>文章留言資料修改 - update_article_reply_input.jsp</h3>
 <h4><a href="/CEA103G1/back-end/article_reply/select_page.jsp"><img src="/CEA103G1/images/Campion.png"
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

<FORM METHOD="post" ACTION="/CEA103G1/article_reply/article_reply.do" name="form1">
<table>
	<tr>
		<td>文章留言編號:<font color=red></font></td>
		<td><%=article_replyVO.getArt_rep_no()%></td>
	</tr>
	<tr>
		<td>文章編號:</td>
		<td><%=article_replyVO.getArt_no()%></td>
	</tr>
	
	<tr>
		<td>會員編號:</td>
		<td><%=article_replyVO.getMbr_no()%></td>
	</tr>

	<tr>
		<td>發表時間:</td>
		<td><%=article_replyVO.getRep_time()%></td>
	</tr>

	<tr>
		<td>留言內容:</td>
		<td><input type="TEXT" name="rep_cont" size="45" value="<%=article_replyVO.getRep_cont()%>" /></td>
<%-- 		<td><textarea col="43" rows="5"><%=articleVO.getArt_cont()%></textarea></td> --%>
		
		
	</tr>
	   
	<tr>
		<td>留言狀態:</td>
		<td><input type="TEXT" name="rep_stat" size="45" value="<%=article_replyVO.getRep_stat()%>" /></td>
	</tr>



</table>
<br>


<input type="hidden" name="art_rep_no" value="<%=article_replyVO.getArt_rep_no()%>">
<input type="hidden" name="art_no" value="<%=article_replyVO.getArt_no()%>">
<input type="hidden" name="mbr_no" value="<%=article_replyVO.getMbr_no()%>">
<input type="hidden" name="rep_time" value="<%=article_replyVO.getRep_time()%>">
<input type="hidden" name="action" value="update">
<input type="submit" value="送出修改"></FORM>
</body>

</html>