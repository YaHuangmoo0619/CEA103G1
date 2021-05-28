<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*" %>
<%@ page import="com.article_reply.model.*"%>


<%int art_no = Integer.parseInt(request.getParameter("art_no")); %>
<%
  Article_ReplyService article_replySvc = new Article_ReplyService();
  List<Article_ReplyVO> list = article_replySvc.getOneArticle_Replies(art_no);
  pageContext.setAttribute("list", list); 
  MemberVO memberVO_mine = (MemberVO)session.getAttribute("memberVO");
  pageContext.setAttribute("memberVO_mine", memberVO_mine);
%>

<jsp:useBean id="memberDAO" scope="page"
	class="com.member.model.MemberDAO" />
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>listOneArticle_Replies 前台</title>
<style>


.top{
padding: 0px 0px 5px 0px;
}
.top div{
display:inline-block;
}

.reply_author{
width:25px;
height:25px;
}

.reply{
padding:10px 0px 15px 0px;
}

.test{
display:block;
}

.dropdown-img{
float:right;
}

.reply_heart{
width:25px;
height:25px;
float:right;
margin: 0px 10px 0px 0px;
}

.likes{
float:right;
margin: 0px 10px 0px 0px;
}

</style>


</head>
<body bgcolor='white'>





<!-- 用於樓層統計		 -->
<%int floor = 1;%>	

<%-- <%@ include file="pageforhome.file"%> --%>
<%-- 		<c:forEach var="article_replyVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
<c:forEach var="article_replyVO" items="${list}">
<div class="reply border-bottom"id=reply<%=floor%>>
<c:if test="${article_replyVO.getRep_stat() == 0}">
<div class=art_rep_no style="display:none">${article_replyVO.getArt_rep_no()}</div>
<div class=top>
			
		<c:forEach var="member" items="${memberDAO.all}">
        <c:if test="${member.mbr_no==article_replyVO.mbr_no && not empty member.sticker}"><div class=sticker><img class=author_sticker width="50px" height="50px" src="<%=request.getContextPath()%>/member/GetPhoto?mbr_no=${member.mbr_no} "></div></c:if>
        
        <c:if test="${member.mbr_no==article_replyVO.mbr_no && empty member.sticker}"><div class=sticker><img class = reply_author src="/CEA103G1/images/profile.png"></div></c:if>
        </c:forEach>
        <c:forEach var="member2" items="${memberDAO.all}">
        <c:if test="${member2.mbr_no==article_replyVO.mbr_no}"><c:if test="${not empty memberVO_mine}"><div class="mbr_no test"><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${member2.mbr_no}&action=getProfile&mbr_no_mine=<%= memberVO_mine.getMbr_no()%>">&nbsp;&nbsp;${member2.acc}</a></div></c:if></c:if>
        <c:if test="${member2.mbr_no==article_replyVO.mbr_no}"><c:if test="${empty memberVO_mine}"><div><a class=to_login href="#">&nbsp;&nbsp;${member2.acc}</a></div></c:if></c:if>
        </c:forEach>
        <div class="floorandtime test">
<%--         <div class="floor"><%=floor%>樓</div> --%>
		<div class="rep_time">&nbsp;|&nbsp;<%=floor%>樓&nbsp;|&nbsp;<fmt:formatDate value="${article_replyVO.rep_time}" pattern="MM月dd日  HH:mm"/></div>
		</div>
		
		</div>
		
		
			
		
		<!-- 有登入的人才看的到 -->
<c:if test="${not empty memberVO}">	
	<img class="dropdown-toggle dropdown-img" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" src="/CEA103G1/images/threedots.svg" width="30px" height="30px">
  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
  					<!-- 		如果有登入且該篇留言作者就是自己 那可以執行刪除及修改文章 -->
   	<c:if test="${not empty memberVO &&(memberVO.mbr_no==article_replyVO.mbr_no)}">
   	<div class=modify_art_rep_no style="display:none">${article_replyVO.art_rep_no}</div>
   	<div style="display:none">getOne_For_Update</div> 
    <a class="dropdown-item modify" href="<%=request.getContextPath()%>/article_reply/article_reply.do?action=getOne_For_Update&art_rep_no=${article_replyVO.art_rep_no}">修改留言</a>
    <div style="display:none">${article_replyVO.art_rep_no}</div>
   	<div style="display:none">hide</div>
    <a class="dropdown-item hide" href="#">刪除留言</a>
    </c:if>
    <c:if test="${not empty memberVO &&(memberVO.mbr_no!=article_replyVO.mbr_no)}">
    <a class="dropdown-item reply_a" href="#">檢舉留言</a>
    </c:if>
  </div>
  <div class=likes>${article_replyVO.likes}</div>
  <img class= reply_heart src="/CEA103G1/images/heart_like_final.svg" >
 	
</c:if>	
<div class=rep_cont>${article_replyVO.rep_cont}</div>			
	</c:if>
	<!-- 遭到刪除的文章 -->
<c:if test="${article_replyVO.getRep_stat() == 1}">
<div class=art_rep_no style="display:none">${article_replyVO.getArt_rep_no()}</div>
<div class=top>
			
		<div class=sticker><img class = reply_author src="/CEA103G1/images/profile.png"></div>

        <div class="mbr_no test">這則回應已被刪除</div>

        <div class="floorandtime test">
        <div class="floor"><%=floor%>樓</div>
		<div class="rep_time"><fmt:formatDate value="${article_replyVO.rep_time}" pattern="MM月dd日  HH:mm"/></div>
		</div>
		</div>
		<div class=rep_cont>已經刪除的內容就像變了心的女友一樣，錯過是無法再相見的！</div>
		</c:if>
	
			<div style="display:none"><%=floor++%></div>		
	</div>		
		</c:forEach>
						


	
	
	
<div class="modal modal_replies" tabindex="-1" role="dialog" id="test2">
     <div class="modal-dialog" role="document"> 
        <div class="modal-content">
            <div class="modal-body">
 				<div id=rep_cont_div></div>
            </div>
       </div>
   </div>
</div>
	

		<div class="modal" id="modify_modal" tabindex="-1" aria-labelledby="modify_modal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5>修改留言內容</h5>
      </div>
      <div class="modal-body">
        <div class="modal_rep_cont"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary close_modal" data-bs-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/front-end/member/login.jsp'">登入</button>
      </div>
    </div>
  </div>
</div>


		<div class="modal" id="login_confirm_listOneArticle" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5>您尚未登入</h5>
      </div>
      <div class="modal-body">
        <div>想要一起加入討論，要先登入 Campion 唷！</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary close_modal" data-bs-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/front-end/member/login.jsp'">登入</button>
      </div>
    </div>
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	<script>
    $(".to_login").click(function(){
    	$('#login_confirm_listOneArticle').modal('show');
  })
  
  $(".close_modal").click(function(){
	  $('#login_confirm_listOneArticle').modal('hide');
  })
	
	
	$('.oneReply').click(function(){
		var floor_html = $(this).html(); //抓到你要tag的人的html 例如 #B1
		console.log("floor_html:"+floor_html);
		var Array_Split = floor_html.split("B"); //用B切割  獲得你要的是幾樓  例如「1」
		alert("現在被點到的是幾樓:"+Array_Split[1]); //test
		var real_rep_no_wait_split = $('#reply'+Array_Split[1]).children(".art_rep_no").html(); //獲得1樓的留言編號，等等才能進行ajax查詢
		alert("real_rep_no_wait_split:"+real_rep_no_wait_split);
// 		var real_rep_no_split = real_rep_no_wait_split.split(":");
// 		alert("它的留言編號為:"+real_rep_no_split[1]); //抓到想要的人的留言編號 test
		$.ajax({ //負責傳到article_replyServlet 
			type : "POST",
			url : "http://localhost:8081/CEA103G1/article_reply/article_reply.do",
			data : {action: "getOneReply_For_Display_front",art_rep_no:real_rep_no_wait_split}, //參數傳遞 
			success : function(data) {
				$('#rep_cont_div').html(data); //把資料放到modal中
				$("#test2").modal('show'); //讓modal秀出
				  $('#test2').on("hidden.bs.modal",function(){
			            $(document.body).addClass("modal-open");
			   });
			}
		}); 
	});
	
	

	
	$(".hide").click(function(){
		var action=$(this).prev().text();
		console.log("action:"+action);
		var art_rep_no=$(this).prev().prev().text();
		console.log("art_rep_no:"+art_rep_no);
		// ajax to sever >> hide
		$.ajax({ //負責傳到article_replyServlet 
			type : "POST",
			url : "http://localhost:8081/CEA103G1/article_reply/article_reply.do",
			data : {action: action,art_rep_no:art_rep_no}, //參數傳遞 
			success : function(data) {
				alert("刪除留言成功");
				window.location.reload();
			}
		}); 
	})
	</script>
</body>
</html>