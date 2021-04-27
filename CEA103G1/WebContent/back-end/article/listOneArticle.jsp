<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.board_class.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
  ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO"); //ArticleServlet.java(Concroller), 存入req的articleVO物件
%>


<html>
<head>
<title>文章資料 - listOneArticle.jsp</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
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
		 <h3>文章資料 - ListOneArticle.jsp</h3>
		 <h4><a href="/CEA103G1/back-end/article/select_page.jsp"><img src="/CEA103G1/images/Campion.png"
						width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<td>文章編號</td>
		<td><%=articleVO.getArt_no()%></td>
   </tr>
	<tr>
		<td>看板編號</td>
		<td><%=articleVO.getBd_cl_no()%></td>
   </tr>
    <tr>
		<td>會員編號</td>
		<td><%=articleVO.getMbr_no()%></td>
   </tr>
   <tr>
		<td>發表時間</td>
<%-- 		<td><%=articleVO.getArt_rel_time()%></td> --%>
		<td><fmt:formatDate value="${articleVO.art_rel_time}" pattern="MM月dd日  HH:mm"/></td>
  </tr>
   <tr>
		<td>文章標題</td>
		<td><%=articleVO.getArt_title()%></td>
  </tr>
   <tr>
		<td>文章內容</td>
		<td><%=articleVO.getArt_cont().replaceAll("\n","<br>")%></td>
  </tr>
   <tr>
		<td>文章狀態</td>
		<td>
		    <c:if test="${articleVO.getArt_stat() == 0}">顯示</c:if>
		    <c:if test="${articleVO.getArt_stat() == 1}">不顯示</c:if>
	   </td>  	   
   </tr>
   
   <tr>
   				<td>修改</td>	
   				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/article/article.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="art_no" value="${articleVO.art_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
   </tr>
   <tr>
   				<td>刪除</td>
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
   
   
   
</table>
<button id=like>like</button>
<button id=unlike>unlike</button>

<br>
<br>
<br>
<br>

<div id="demo"></div>
<button id=get>get</button>
	<script>   
 	$("#get").click(function(){
		$.ajax({
			url:"/CEA103G1/back-end/article_reply/listOneArticle_Replies.jsp?art_no=<%=articleVO.getArt_no()%>",
			type: "GET",
			success: function(data){
				$("#demo").html(data);
			}
		});
 	});
	</script>


</body>
</html>