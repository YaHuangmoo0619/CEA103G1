<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article_reply.model.*"%>


<%int art_no = Integer.parseInt(request.getParameter("art_no")); %>
<%
  Article_ReplyService article_replySvc = new Article_ReplyService();
  List<Article_ReplyVO> list = article_replySvc.getOneArticle_Replies(art_no);
  pageContext.setAttribute("list", list); 
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>listOneArticle_Replies 前台</title>
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


<table>


<!-- 用於樓層統計		 -->
<%int floor = 1;%>	

<%-- <%@ include file="pageforhome.file"%> --%>
<%-- 		<c:forEach var="article_replyVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
<c:forEach var="article_replyVO" items="${list}">
<div class=reply id=reply<%=floor%>>
<c:if test="${article_replyVO.getRep_stat() == 0}">

			
				<%=floor%>樓
		<div class=art_rep_no>留言編號:${article_replyVO.art_rep_no}</div>
		<div>文章編號:${article_replyVO.art_no}</div>
        <div>會員編號:${article_replyVO.mbr_no}</div>
        <div>留言內容:${article_replyVO.rep_cont}</div>
		<div><fmt:formatDate value="${article_replyVO.rep_time}" pattern="MM月dd日  HH:mm"/></div>
		<div>留言讚數:${article_replyVO.likes}</div>
		<div>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article_reply/article_reply.do" style="margin-bottom: 0px;">
				<input type="submit" value="修改">
				<input type="hidden" name="art_rep_no" value="${article_replyVO.art_rep_no}">
				<input type="hidden" name="action" value="getOne_For_Update">
				<input type="hidden" name="requestURL"	value="<%=request.getParameter("requestURL")%>">
<!-- 						 將listOneArticle的URL往article_replyServlet丟 -->
			</FORM>
		</div>
		<div>					
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article_reply/article_reply.do" style="margin-bottom: 0px;">
		<input type="submit" value="刪除"> <input type="hidden" name="art_rep_no" value="${article_replyVO.art_rep_no}">
		<input type="hidden" name="action" value="hide">
		</FORM>
		</div>



			
	</c:if>
			<%=floor++%>		
	</div>		
		</c:forEach>
						
	</table>
<%-- 	<%@ include file="page2.file"%> --%>
	
	
	
<div class="modal modal_replies" tabindex="-1" role="dialog" id="test2">
     <div class="modal-dialog" role="document"> 
        <div class="modal-content">
            <div class="modal-body">
 				<div id=rep_cont_div></div>
            </div>
       </div>
   </div>
</div>
	


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	<script>
	$('.oneReply').click(function(){
		var floor_html = $(this).html(); //抓到你要tag的人的html 例如 #B1 
		var Array_Split = floor_html.split("B"); //用B切割  獲得你要的是幾樓  例如「1」
// 		alert("現在被點到的是幾樓:"+Array_Split[1]); //test
		var real_rep_no_wait_split = $('#reply'+Array_Split[1]).children("div.art_rep_no").html(); //獲得1樓的留言編號，等等才能進行ajax查詢
		var real_rep_no_split = real_rep_no_wait_split.split(":");
// 		alert("它的留言編號為:"+real_rep_no_split[1]); //抓到想要的人的留言編號 test
		$.ajax({ //負責傳到article_replyServlet 
			type : "POST",
			url : "http://localhost:8081/CEA103G1/article_reply/article_reply.do",
			data : {action: "getOneReply_For_Display_front",art_rep_no:real_rep_no_split[1]}, //參數傳遞 
			success : function(data) {
				$('#rep_cont_div').html(data); //把資料放到modal中
				$("#test2").modal('show'); //讓modal秀出
				  $('#test2').on("hidden.bs.modal",function(){
			            $(document.body).addClass("modal-open");
			   });
			}
		}); 
	});
	

	</script>
</body>
</html>