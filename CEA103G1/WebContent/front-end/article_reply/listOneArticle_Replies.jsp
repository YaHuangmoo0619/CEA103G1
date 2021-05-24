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

<jsp:useBean id="memberDAO" scope="page"
	class="com.member.model.MemberDAO" />
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>listOneArticle_Replies �e�x</title>
<style>

.top div{
display:inline-block;
}

.reply_author{
width:25px;
height:25px;
}

.reply{
padding:0px 0px 15px 0px;

}

.test{
display:block;
}
</style>


</head>
<body bgcolor='white'>





<!-- �Ω�Ӽh�έp		 -->
<%int floor = 1;%>	

<%-- <%@ include file="pageforhome.file"%> --%>
<%-- 		<c:forEach var="article_replyVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
<c:forEach var="article_replyVO" items="${list}">
<div class=reply id=reply<%=floor%>>
<c:if test="${article_replyVO.getRep_stat() == 0}">

<div class=top>
			
		<c:forEach var="member" items="${memberDAO.all}">
        <c:if test="${member.mbr_no==article_replyVO.mbr_no && not empty member.sticker}"><div class=sticker>${member.sticker}</div></c:if>
        <c:if test="${member.mbr_no==article_replyVO.mbr_no && empty member.sticker}"><div class=sticker><img class = reply_author src="/CEA103G1/images/profile.png"></div></c:if>
        </c:forEach>
        <c:forEach var="member2" items="${memberDAO.all}">
        <c:if test="${member2.mbr_no==article_replyVO.mbr_no}"><div class="mbr_no test">${member2.acc}</div></c:if>
        </c:forEach>
        <div class="floorandtime test">
        <div class="floor">B<%=floor%></div>
		<div class="rep_time"><fmt:formatDate value="${article_replyVO.rep_time}" pattern="MM��dd��  HH:mm"/></div>
		</div>
		<div class=likes>${article_replyVO.likes}</div>
		</div>
		<div class=rep_cont>${article_replyVO.rep_cont}</div>
		

        <c:if test="${memberVO.mbr_no==article_replyVO.mbr_no}">
        		<div class=modify>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article_reply/article_reply.do" style="margin-bottom: 0px;">
				<input type="submit" value="�ק�">
				<input type="hidden" name="art_rep_no" value="${article_replyVO.art_rep_no}">
				<input type="hidden" name="action" value="getOne_For_Update">
				<input type="hidden" name="requestURL"	value="<%=request.getParameter("requestURL")%>">
			</FORM>
		</div>
		
		<div class=delete>					
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article_reply/article_reply.do" style="margin-bottom: 0px;">
		<input type="submit" value="�R��"> <input type="hidden" name="art_rep_no" value="${article_replyVO.art_rep_no}">
		<input type="hidden" name="action" value="hide">
		</FORM>
		</div>
        
        </c:if>
			
	</c:if>
			<div style="display:none"><%=floor++%></div>		
	</div>		
		</c:forEach>
						

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
		var floor_html = $(this).html(); //���A�ntag���H��html �Ҧp #B1 
		var Array_Split = floor_html.split("B"); //��B����  ��o�A�n���O�X��  �Ҧp�u1�v
// 		alert("�{�b�Q�I�쪺�O�X��:"+Array_Split[1]); //test
		var real_rep_no_wait_split = $('#reply'+Array_Split[1]).children("div.art_rep_no").html(); //��o1�Ӫ��d���s���A�����~��i��ajax�d��
		var real_rep_no_split = real_rep_no_wait_split.split(":");
// 		alert("�����d���s����:"+real_rep_no_split[1]); //���Q�n���H���d���s�� test
		$.ajax({ //�t�d�Ǩ�article_replyServlet 
			type : "POST",
			url : "http://localhost:8081/CEA103G1/article_reply/article_reply.do",
			data : {action: "getOneReply_For_Display_front",art_rep_no:real_rep_no_split[1]}, //�Ѽƶǻ� 
			success : function(data) {
				$('#rep_cont_div').html(data); //���Ʃ��modal��
				$("#test2").modal('show'); //��modal�q�X
				  $('#test2').on("hidden.bs.modal",function(){
			            $(document.body).addClass("modal-open");
			   });
			}
		}); 
	});
	

	</script>
</body>
</html>