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
<title>listOneArticle_Replies �e�x</title>
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





<!-- �Ω�Ӽh�έp		 -->
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
<%--         <div class="floor"><%=floor%>��</div> --%>
		<div class="rep_time">&nbsp;|&nbsp;<%=floor%>��&nbsp;|&nbsp;<fmt:formatDate value="${article_replyVO.rep_time}" pattern="MM��dd��  HH:mm"/></div>
		</div>
		
		</div>
		
		
			
		
		<!-- ���n�J���H�~�ݪ��� -->
<c:if test="${not empty memberVO}">	
	<img class="dropdown-toggle dropdown-img" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" src="/CEA103G1/images/threedots.svg" width="30px" height="30px">
  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
  					<!-- 		�p�G���n�J�B�ӽg�d���@�̴N�O�ۤv ���i�H����R���έק�峹 -->
   	<c:if test="${not empty memberVO &&(memberVO.mbr_no==article_replyVO.mbr_no)}">
   	<div class=modify_art_rep_no style="display:none">${article_replyVO.art_rep_no}</div>
   	<div style="display:none">getOne_For_Update</div> 
    <a class="dropdown-item modify" href="<%=request.getContextPath()%>/article_reply/article_reply.do?action=getOne_For_Update&art_rep_no=${article_replyVO.art_rep_no}">�ק�d��</a>
    <div style="display:none">${article_replyVO.art_rep_no}</div>
   	<div style="display:none">hide</div>
    <a class="dropdown-item hide" href="#">�R���d��</a>
    </c:if>
    <c:if test="${not empty memberVO &&(memberVO.mbr_no!=article_replyVO.mbr_no)}">
    <a class="dropdown-item reply_a" href="#">���|�d��</a>
    </c:if>
  </div>
  <div class=likes>${article_replyVO.likes}</div>
  <img class= reply_heart src="/CEA103G1/images/heart_like_final.svg" >
 	
</c:if>	
<div class=rep_cont>${article_replyVO.rep_cont}</div>			
	</c:if>
	<!-- �D��R�����峹 -->
<c:if test="${article_replyVO.getRep_stat() == 1}">
<div class=art_rep_no style="display:none">${article_replyVO.getArt_rep_no()}</div>
<div class=top>
			
		<div class=sticker><img class = reply_author src="/CEA103G1/images/profile.png"></div>

        <div class="mbr_no test">�o�h�^���w�Q�R��</div>

        <div class="floorandtime test">
        <div class="floor"><%=floor%>��</div>
		<div class="rep_time"><fmt:formatDate value="${article_replyVO.rep_time}" pattern="MM��dd��  HH:mm"/></div>
		</div>
		</div>
		<div class=rep_cont>�w�g�R�������e�N���ܤF�ߪ��k�ͤ@�ˡA���L�O�L�k�A�ۨ����I</div>
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
        <h5>�ק�d�����e</h5>
      </div>
      <div class="modal-body">
        <div class="modal_rep_cont"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary close_modal" data-bs-dismiss="modal">����</button>
        <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/front-end/member/login.jsp'">�n�J</button>
      </div>
    </div>
  </div>
</div>


		<div class="modal" id="login_confirm_listOneArticle" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5>�z�|���n�J</h5>
      </div>
      <div class="modal-body">
        <div>�Q�n�@�_�[�J�Q�סA�n���n�J Campion ��I</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary close_modal" data-bs-dismiss="modal">����</button>
        <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/front-end/member/login.jsp'">�n�J</button>
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
		var floor_html = $(this).html(); //���A�ntag���H��html �Ҧp #B1
		console.log("floor_html:"+floor_html);
		var Array_Split = floor_html.split("B"); //��B����  ��o�A�n���O�X��  �Ҧp�u1�v
		alert("�{�b�Q�I�쪺�O�X��:"+Array_Split[1]); //test
		var real_rep_no_wait_split = $('#reply'+Array_Split[1]).children(".art_rep_no").html(); //��o1�Ӫ��d���s���A�����~��i��ajax�d��
		alert("real_rep_no_wait_split:"+real_rep_no_wait_split);
// 		var real_rep_no_split = real_rep_no_wait_split.split(":");
// 		alert("�����d���s����:"+real_rep_no_split[1]); //���Q�n���H���d���s�� test
		$.ajax({ //�t�d�Ǩ�article_replyServlet 
			type : "POST",
			url : "http://localhost:8081/CEA103G1/article_reply/article_reply.do",
			data : {action: "getOneReply_For_Display_front",art_rep_no:real_rep_no_wait_split}, //�Ѽƶǻ� 
			success : function(data) {
				$('#rep_cont_div').html(data); //���Ʃ��modal��
				$("#test2").modal('show'); //��modal�q�X
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
		$.ajax({ //�t�d�Ǩ�article_replyServlet 
			type : "POST",
			url : "http://localhost:8081/CEA103G1/article_reply/article_reply.do",
			data : {action: action,art_rep_no:art_rep_no}, //�Ѽƶǻ� 
			success : function(data) {
				alert("�R���d�����\");
				window.location.reload();
			}
		}); 
	})
	</script>
</body>
</html>