<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.board_class.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*" %>
<%@ page import="com.article_likes.model.*" %>
<%@ page import="com.article_collection.model.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
  ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO"); //ArticleServlet.java(Concroller), �s�Jreq��articleVO����
%>

<%
  Set<String> tag_list = (Set<String>) request.getAttribute("tag_list"); //ArticleServlet.java(Concroller), �s�Jreq��articleVO����
%>

<%String requestURL = (String)request.getAttribute("requestURL"); %>




<% 
	boolean like_status=false; // �]�@�ӥ��L���ܼƬ�like_status��false
	boolean collection_status=false;
	int ajax_mbr_no = 0;
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	//�p�GmemberVO�����šA�N��L���n�J�A���۬d�ߥL�O�_��o�g�峹���L�g
	if(memberVO!=null){
		ajax_mbr_no = memberVO.getMbr_no();
		Article_LikesService article_likesSvc = new Article_LikesService();
		//���o�ګ��g�L���M��
		List<Article_LikesVO> my_likes_list = article_likesSvc.findbymbr_no(memberVO.getMbr_no());
		//�q�ګ��g�L���M��j�M���S���o�g�峹
		for(Article_LikesVO element : my_likes_list){
			if(element.getArt_no()==articleVO.getArt_no()){	//�p�G���g�L�o�g�峹�Alike_status��true
				like_status=true;
			}
		};
		
		Article_CollectionService article_collectionSvc = new Article_CollectionService();
		//���o�ڦ��ùL���M��
		List<Article_CollectionVO> my_collection_list = article_collectionSvc.findbymbr_no(memberVO.getMbr_no());
		//�q�ڦ��ùL���M��j�M���S���o�g�峹
		for(Article_CollectionVO element2 : my_collection_list){
			if(element2.getArt_no()==articleVO.getArt_no()){
				collection_status=true;
			}
		};
		
	}
	if(memberVO==null){
		ajax_mbr_no=0;
	}
	pageContext.setAttribute("like_status", like_status);
	pageContext.setAttribute("collection_status", collection_status);
	pageContext.setAttribute("ajax_mbr_no", ajax_mbr_no);
%>

<jsp:useBean id="memberDAO" scope="page" class="com.member.model.MemberDAO" />
<jsp:useBean id="bd_clDAO" scope="page"  class="com.board_class.model.Board_ClassDAO" />	
<html>
<head>
<title>listOneArticle</title>  
    <style>

<style>

   .like{ 
   background-image:url('/CEA103G1/images/heart-outline.svg');
   width:20px; 
   height:18px;
   border:0px; 
   background-size: 20px 18px; 
   background-repeat: no-repeat;  
   } 
   
   .like:hover{
   background-image:url('/CEA103G1/images/heart.svg'); 
   }
   
   
  	.like_no_login{ 
   background-image:url('/CEA103G1/images/heart-outline.svg');
   width:20px; 
   height:18px;
   border:0px; 
   background-size: 20px 18px; 
   background-repeat: no-repeat; 
   } 
   
   .like_no_login:hover{
   background-image:url('/CEA103G1/images/heart.svg'); 
   }
   
   
   .unlike{ 
   background-image:url('/CEA103G1/images/heart.svg'); 
   width:20px; 
   height:18px; 
   border:0px; 
   background-size: 20px 18px; 
   background-repeat: no-repeat; 
   }

   
   
   
   .collection{ 
   background-image:url('/CEA103G1/images/bookmarks-outline.svg');
   width:20px; 
   height:18px;
   border:0px; 
   background-size: 20px 18px; 
   background-repeat: no-repeat; 
   } 
   
   .collection:hover{
   background-image:url('/CEA103G1/images/bookmarks.svg'); 
   }

   .collection_no_login{ 
   background-image:url('/CEA103G1/images/bookmarks-outline.svg');
   width:20px; 
   height:18px;
   border:0px; 
   background-size: 20px 18px; 
   background-repeat: no-repeat; 
   } 
   
   .collection_no_login:hover{
   background-image:url('/CEA103G1/images/bookmarks.svg'); 
   }   
   
   .uncollection{ 
   background-image:url('/CEA103G1/images/bookmarks.svg'); 
   width:20px; 
   height:18px; 
   border:0px; 
   background-size: 20px 18px; 
   background-repeat: no-repeat; 
   }
   .uncollection:hover{
   background-image:url('/CEA103G1/images/bookmarks-outline.svg'); 
   } 
	
	.container{
	padding: 0px 50px 0px 50px;
	/* background:#ff7d40; */
	}

	
	.headshotAndAuthor{
	padding: 0px 0px 10px 0px;
	width:auto; 
	display:inline-block !important; 
	display:inline;
	}
	
	.bdAandReltime{
	display:inline-block;
	}
	.likegroup{
	display:inline-block;
	}
	.rel_time{
	color:rgba(0, 0, 0, 0.35);
	white-space: nowrap;
    text-overflow: ellipsis;
    font-size: 14px;
    padding:0px 0px 0px 10px;
	}
	.cont{
	padding: 20px 0px 0px 0px;
	color: black;
	}
	
	.heart_group{
	padding:20px 0px 0px 0px;
	}
	.heart_group div{
	display:inline-block;
	}
	.headshotAndAuthor div{
	display:inline-block;
	}
	
	.heart_for_like{
	width:24px;
	height:24px;
	padding:2px;
	}
	.modal div{
	color:black;
	}
	
	.btn-close{
	width: 30px;
	height:30px;
	}

	.dropdown{
	padding-left:500px;
	}
	
	
/* 	.tool div{ */
/* 	display:inline-block */
/* 	} */


	
	
</style>

</head>
<body bgcolor='white'>

<div class="container custom-container-width">

		<div class="main_div headshotAndAuthor">
		<div class="headshot">
		<c:forEach var="memberVO" items="${memberDAO.all}">
		<c:if test="${articleVO.mbr_no==memberVO.mbr_no && not empty memberVO.sticker}">${memberVO.sticker}</c:if>
		<c:if test="${articleVO.mbr_no==memberVO.mbr_no && empty memberVO.sticker}"><img src="/CEA103G1/images/profile.png" width="30px" height="30px"></c:if>
		</c:forEach>
		</div>
		<!-- 	�p�G���n�J����  �i�H�s��ӵo��̪�²��-->
		<c:if test="${not empty memberVO}"> 
		<div class="author"><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=<%=articleVO.getMbr_no()%>&action=getProfile&mbr_no_mine=<%=pageContext.getAttribute("ajax_mbr_no")%>"><c:forEach var="memberVO" items="${memberDAO.all}"><c:if test="${articleVO.mbr_no==memberVO.mbr_no}">${memberVO.acc}</c:if></c:forEach></a></div>
		</c:if>
		<!-- 	�p�G�S���n�J����  �n���}�W���n�J���O�c-->	
		<c:if test="${empty memberVO}"> 
		<div class="author to_login_listOneArticle"><c:forEach var="memberVO" items="${memberDAO.all}"><c:if test="${articleVO.mbr_no==memberVO.mbr_no}">${memberVO.acc}</c:if></c:forEach></div>
		</c:if>
		</div>

<!-- ���n�J���H�~�ݪ��� -->
<c:if test="${not empty memberVO}">	
		<div class="dropdown">
<!--   <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->

<!--   </a> -->
	<img class="dropdown-toggle" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" src="/CEA103G1/images/threedots.svg" width="30px" height="30px">
  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
  					<!-- 		�p�G���n�J�B�ӽg�峹�@�̴N�O�ۤv ���i�H����R���έק�峹 -->
   	<c:if test="${not empty memberVO &&(articleVO.mbr_no==memberVO.mbr_no)}"> 
    <a class="dropdown-item" href="<%=request.getContextPath()%>/article/article.do?action=getOne_For_Update&art_no=${articleVO.art_no}">�ק�峹</a>
    <a class="dropdown-item" href="<%=request.getContextPath()%>/article/article.do?action=hide&art_no=${articleVO.art_no}">�R���峹</a>
    </c:if>
    <c:if test="${not empty memberVO &&(articleVO.mbr_no!=memberVO.mbr_no)}">
    <a class="dropdown-item reply_a" href="#">���|�峹</a>
    </c:if>
  </div>
</div>
</c:if>


		<div class=title><h2><%=articleVO.getArt_title()%></h2></div>
				
		<div>
		
		<div class="bd bdAandReltime">
		<c:forEach var="bd_VO" items="${bd_clDAO.all}">
		<c:if test="${articleVO.bd_cl_no==bd_VO.bd_cl_no}"><a href="<%=request.getContextPath()%>/article/article.do?bd_cl_no=${bd_VO.bd_cl_no}&action=getOneArticle_ByBoard_Clss_For_Display">${bd_VO.bd_name}</a></c:if>
		</c:forEach>
		<div class="rel_time bdAandReltime"><fmt:formatDate value="${articleVO.art_rel_time}" pattern="MM��dd��  HH:mm"/></div>
		</div>
		</div>
		<div class=cont><%=articleVO.getArt_cont().replaceAll("\n","<br>")%></div>
		


<div class=heart_group>
<div><img class=heart_for_like src="/CEA103G1/images/heart_for_like.svg" ></div>
<div id=like_td>${articleVO.likes}</div>
<div><img class=heart_for_like src="/CEA103G1/images/reply.svg" ></div>
<div>${articleVO.replies}</div>
</div>
<div>

	<!-- 	�p�G���n�J����  �i�H��峹��/���^�g �[�J/��������-->
	<c:if test="${not empty memberVO}"> 
	<!--    �P�_�o�g�峹�O�_���L�g�A�p�G�٨S�A�����X�{���N�O���g   ���^�g�h�]������ -->
	<c:if test="${like_status eq true }">
	<div>
	<button class="like likegroup" style="display:none;"></button>
	<button class="unlike likegroup" ></button>	
	</c:if>
	<c:if test="${like_status eq false }">
	<button class="like likegroup" ></button>
	<button class="unlike likegroup"style="display:none;"></button>	
	</c:if>
	<!--    �P�_�o�g�峹�O�_���ùL�A�p�G�٨S�A�����X�{���N�O�[�J����   �������ëh�]������ -->
	<c:if test="${collection_status eq true }">
	<button class="collection likegroup" style="display:none;"></button>
	<button class="uncollection likegroup"></button>
	</c:if>
	<c:if test="${collection_status eq false }">
	<button class="collection likegroup"></button>
	<button class="uncollection likegroup" style="display:none;"></button>
	</c:if>
	
	</c:if>
<!-- 	�p�G�S���n�J����  �u�|��ܫ��g�B���ê����áA�I���n���}�W���n�J���O�c-->	
	<c:if test="${empty memberVO}"> 
	<button class="like_no_login to_login_listOneArticle likegroup" type="button"></button>
	<button class="collection_no_login to_login_listOneArticle likegroup" type="button"></button>
	</c:if>
	
	

</div>

<br>
<br>
<br>
<br>

<div>�峹����</div>
<c:forEach var="tag_list" items="${tag_list}">
<div><a href="<%=request.getContextPath()%>/article/article.do?tag=${tag_list}&action=getArticlesByTagFor_Display">${tag_list}</a></div>
</c:forEach>


<div id = "addrep"></div>
<div id="demo"></div>
		<!-- 	�p�G���n�J����  �i�H��峹�d��-->
	<c:if test="${not empty memberVO}"> 
	<button id=rep_login onclick="location.href='/CEA103G1/front-end/article_reply/addArticle_reply.jsp?art_no=<%=articleVO.getArt_no()%>&mbr_no=<%=memberVO.getMbr_no()%>'">�ڭn�^��</button>
	</c:if>
<!-- 	�p�G�S���n�J����  �n���}�W���n�J���O�c-->	
	<c:if test="${empty memberVO}"> 
	<button type="button" class=to_login_listOneArticle>�s�W�峹�d��</button>
	</c:if>



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


			<div class="modal" id="reply_modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5>���|�峹</h5>
      </div>
      <div class="modal-body">
        <form METHOD="post" ACTION="/CEA103G1/article_report/article_report.do" name="form1" autocomplete>
        	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios" value="���ˡB�[���B�D�]����|�L�H">
  					<label class="form-check-label" for="exampleRadios1">
    					���ˡB�[���B�D�]����|�L�H
  					</label>
	   		 </div>
	   		         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios" value="���ˡB�[���B�D�]����|�L�H">
  					<label class="form-check-label" for="exampleRadios1">
    					�ӷ~�s�i�ŶǤ��e
  					</label>
	   		 </div>
	   	         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios" value="�c�N�~�O�B���Ʊi�K">
  					<label class="form-check-label" for="exampleRadios1">
    					�c�N�~�O�B���Ʊi�K
  					</label>
	   	 </div>
	   	         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios"  value="�]�t�ⱡ�B�S�I�B�����Z�Φ�{���Ƶ����H���ΪA�����e">
  					<label class="form-check-label" for="exampleRadios1">
    					�]�t�ⱡ�B�S�I�B�����Z�Φ�{���Ƶ����H���ΪA�����e
  					</label>
	   	 </div>
	   	         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios" value="�ɤO�B�ˮ`�L�H�ζˮ`�ʪ������e">
  					<label class="form-check-label" for="exampleRadios1">
    					�ɤO�B�ˮ`�L�H�ζˮ`�ʪ������e
  					</label>
	   	 </div>
	   	         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios" value="�]�t�s�i�B�ӷ~�ŶǤ����e">
  					<label class="form-check-label" for="exampleRadios1">
    					�]�t�s�i�B�ӷ~�ŶǤ����e
  					</label>
	   	 </div>
	   	         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios"  value="�峹�o��b���A���ݪO">
  					<label class="form-check-label" for="exampleRadios1">
    					�峹�o��b���A���ݪO
  					</label>
	   	 </div>
	   	         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios"  value="�峹���e�Ūx�Ω���L�N�q���e">
  					<label class="form-check-label" for="exampleRadios1">
    					�峹���e�Ūx�Ω���L�N�q���e
  					</label>
	   	 </div>
	   	         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios"  value="�Y�T�o��B��K���g�ҹꪺ�����Τ���T��">
  					<label class="form-check-label" for="exampleRadios1">
    					�Y�T�o��B��K���g�ҹꪺ�����Τ���T��
  					</label>
	   	 </div>
	   	         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios"  value="�T��o��ӹL�����D�D���峹�ίd���C">
  					<label class="form-check-label" for="exampleRadios1">
    					�T��o��ӹL�����D�D���峹�ίd���C
  					</label>
  							
		<input type="hidden" name="mbr_no" value="${memberVO.getMbr_no()}" />
		<input type="hidden" name="art_no" value="${articleVO.getArt_no()}" />
		<input type="hidden" name="action" value="insert">
		
	   	 </div>
	   	 <div class="modal-footer">
        <button type="button" class="btn btn-secondary close_reply_modal" data-bs-dismiss="modal">����</button>
        <button type="submit" class="btn btn-primary">����</button>
      	</div>
        </form>
      </div>

    </div>
  </div>
</div>

</div>


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	
<script>
 var stateObj = { foo: "bar" };
 history.pushState(stateObj, "page 2", "${requestURL}");

</script>

	<script>   
	

//   	$("#rep_login").click(function(){
// 		$.ajax({
<%-- 			url:"/CEA103G1/front-end/article_reply/addArticle_reply.jsp?art_no=<%=articleVO.getArt_no()%>&mbr_no=<%=pageContext.getAttribute("ajax_mbr_no")%>", --%>
// 			type: "GET",
// 			success: function(data){
// 				$("#addrep").html(data);
// 			}
// 		});
//   	});
	

		$.ajax({
			url:"/CEA103G1/front-end/article_reply/listOneArticle_Replies.jsp?art_no=<%=articleVO.getArt_no()%>&requestURL=<%=request.getServletPath()%>",
			type: "GET",
			success: function(data){
				$("#demo").html(data);
			}
		});

 	
 	$(".like").click(function(){
 		var x=document.getElementById("like_td").innerHTML;
 	 	 x=parseInt(x)+1;
 	 	 document.getElementById("like_td").innerHTML=x;
 	 	 $(this).hide();
 	 	 $(".unlike").show();
 	 	 
			$.ajax({ //�Ĥ@��ajax �t�d�Ǩ�article_likesServlet �s�W�Y�H��Y�峹�����g  �ݭn���Ѽ�: art_no mbr_no 
				type : "POST",
				url : "/CEA103G1/article_likes/article_likes.do",
				data : {action: "plus_like",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,art_no:<%=articleVO.getArt_no()%>}, //�Ѽƶǻ� : action�ǻ��u�[�@�v mbr_no art_no �ǻ��n�[�@����T
				success : function(data) {
					alert("�s�W"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"�惡�g�峹�����g���\");
				}
			});
				
				$.ajax({ //�ĤG��ajax �t�d�Ǩ�articleServlet�A��s�Y�峹���g��  �ݭn���Ѽ� art_no
					type : "POST",
					url : "/CEA103G1/article/article.do",
					data : {action: "plus_like",art_no:<%=articleVO.getArt_no()%>}, //�Ѽƶǻ� : action�ǻ��u�[�@�v mbr_no art_no �ǻ��n�[�@����T
					success : function(data) {
						alert("�Y�峹���g��+1���\");
						//���Ĺ��ե[�W�t�γq��
						websocket.send(<%=articleVO.getMbr_no()%>+'/like');
						//���Ĺ��ե[�W�t�γq��
					}
				});
 	})
 	
 	 	$(".unlike").click(function(){
 		var x=document.getElementById("like_td").innerHTML;
 	 	 x=parseInt(x)-1;
 	 	 document.getElementById("like_td").innerHTML=x;
 	 	 $(this).hide();
 	 	 $(".like").show();
 	 	 
 		$.ajax({ //�Ĥ@��ajax �t�d�Ǩ�article_likesServlet �R���Y�H��Y�峹�����g  
			type : "POST",
			url : "/CEA103G1/article_likes/article_likes.do",
			data : {action: "minus_like",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,art_no:<%=articleVO.getArt_no()%>}, 
			success : function(data) {
				alert("���^"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"�惡�峹�����g���\");
			}
		});
			
			$.ajax({ //�ĤG��ajax �t�d�Ǩ�articleServlet�A��s�Y�峹���g��  �ݭn���Ѽ� art_no
				type : "POST",
				url : "/CEA103G1/article/article.do",
				data : {action: "minus_like",art_no:<%=articleVO.getArt_no()%>}, 
				success : function(data) {
					alert("�Y�峹���g��-1���\");
				}
			});
 	})
 	
 	
 	
 	 	$(".collection").click(function(){
 	 		$(this).hide();
 	 		$(".uncollection").show();

 	 	 	
 	 				$.ajax({ //�t�d�Ǩ�article_collectionServlet �s�W�Y�H��Y�峹������  
 	 				type : "POST",
 	 				url : "/CEA103G1/article_collection/article_collection.do",
 	 				data : {action: "plus_collection",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,art_no:<%=articleVO.getArt_no()%>}, //�Ѽƶǻ� : action�ǻ��u�[�@�v mbr_no art_no �ǻ��n�[�@����T
 	 				success : function(data) {
 	 					alert("�s�W"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"�惡�峹�����æ��\");
 	 				}
 	 			}); 
 	})
 	
 	 	$(".uncollection").click(function(){
 	 		$(this).hide();
 	 		$(".collection").show();
			
 	 		$.ajax({ //�t�d�Ǩ�article_collectionServlet �R���Y�H��Y�峹������  �ݭn���Ѽ�: art_no mbr_no   �ثembr_no�g�� ����n�qsession get��ثe�O���ӷ|����o�g�峹���g 
 	 				type : "POST",
 	 				url : "/CEA103G1/article_collection/article_collection.do",
 	 				data : {action: "minus_collection",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,art_no:<%=articleVO.getArt_no()%>}, 
 	 				success : function(data) {
 	 					alert("����"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"�惡�峹�����æ��\");
 	 				}
 	 			});
 	})
 	


    $(".to_login_listOneArticle").click(function(){
    	$('#login_confirm_listOneArticle').modal('show');
  })
  
  $(".close_modal").click(function(){
	  $('#login_confirm_listOneArticle').modal('hide');
  })
  
  

      $(".reply_a").click(function(){
    	$('#reply_modal').modal('show');
  })
  
  
    $(".close_reply_modal").click(function(){
	  $('#reply_modal').modal('hide');
  })
  </script>
	
</body>
</html>