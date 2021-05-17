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


<html>
<head>
<title>�峹��� - listOneArticle.jsp �e�x</title>

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
	
	
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�峹��� - ListOneArticle.jsp �e�x</h3>
		 <h4><a href="/CEA103G1/back-end/article/select_page.jsp"><img src="/CEA103G1/images/Campion.png"
						width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<td>�峹�s��</td>
		<td><%=articleVO.getArt_no()%></td>
   </tr>
	<tr>
		<td>�ݪO�s��</td>
		<td><%=articleVO.getBd_cl_no()%></td>
   </tr>
    <tr>
		<td>�|���s��</td>
		<!-- 	�p�G���n�J����  �i�H�s��ӵo��̪�²��-->
	<c:if test="${not empty memberVO}"> 
	<td><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=<%=articleVO.getMbr_no()%>&action=getProfile&mbr_no_mine=<%=pageContext.getAttribute("ajax_mbr_no")%>"><%=articleVO.getMbr_no()%></a></td>
	</c:if>
<!-- 	�p�G�S���n�J����  �n���}�W���n�J���O�c-->	
	<c:if test="${empty memberVO}"> 
	<td><div class= to_login_listOneArticle><%=articleVO.getMbr_no()%></div></td>
	</c:if>
		
   </tr>
   <tr>
		<td>�o��ɶ�</td>
<%-- 		<td><%=articleVO.getArt_rel_time()%></td> --%>
		<td><fmt:formatDate value="${articleVO.art_rel_time}" pattern="MM��dd��  HH:mm"/></td>
  </tr>
   <tr>
		<td>�峹���D</td>
		<td><%=articleVO.getArt_title()%></td>
  </tr>
   <tr>
		<td>�峹���e</td>
		<td><%=articleVO.getArt_cont().replaceAll("\n","<br>")%></td>
  </tr>
   <tr>
		<td>�峹���A</td>
		<td>
		    <c:if test="${articleVO.getArt_stat() == 0}">���</c:if>
		    <c:if test="${articleVO.getArt_stat() == 1}">�����</c:if>
	   </td>  	   
   </tr>
   
   <tr>
   				<td>�ק�</td>	
   				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/article/article.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="art_no" value="${articleVO.art_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
   </tr>
   <tr>
   				<td>�R��</td>
   				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/article/article.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="art_no" value="${articleVO.art_no}"> <input
							type="hidden" name="action" value="hide">
					</FORM>
				</td> 
   </tr>
   
   
   <tr>
		<td>�g��</td>
		<td id = like_td>${articleVO.likes}</td>
  </tr>
  
     <tr>
		<td>�d����</td>
		<td id = like_td>${articleVO.replies}</td>
  </tr>
   
</table>

<div><%=(String)request.getAttribute("requestURI")%></div>

<div>
<div>�^��  </div>
	<!-- 	�p�G���n�J����  �i�H��峹��/���^�g �[�J/��������-->
	<c:if test="${not empty memberVO}"> 
	<!--    �P�_�o�g�峹�O�_���L�g�A�p�G�٨S�A�����X�{���N�O���g   ���^�g�h�]������ -->
	<c:if test="${like_status eq true }">
	<button class="like" style="display:none;"></button>
	<button class="unlike" ></button>
	</c:if>
	<c:if test="${like_status eq false }">
	<button class="like" ></button>
	<button class="unlike"style="display:none;"></button>	
	</c:if>
	
	<!--    �P�_�o�g�峹�O�_���ùL�A�p�G�٨S�A�����X�{���N�O�[�J����   �������ëh�]������ -->
	<c:if test="${collection_status eq true }">
	<button class="collection" style="display:none;"></button>
	<button class="uncollection"></button>
	</c:if>
	
	<c:if test="${collection_status eq false }">
	<button class="collection"></button>
	<button class="uncollection" style="display:none;"></button>
	</c:if>

	</c:if>
<!-- 	�p�G�S���n�J����  �u�|��ܫ��g�B���ê����áA�I���n���}�W���n�J���O�c-->	
	<c:if test="${empty memberVO}"> 
	<button class="like_no_login to_login_listOneArticle" type="button"></button>
	<button class="collection_no_login to_login_listOneArticle" type="button"></button>
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
	<button id=rep_login>�s�W�峹�d��</button>
	</c:if>
<!-- 	�p�G�S���n�J����  �n���}�W���n�J���O�c-->	
	<c:if test="${empty memberVO}"> 
	<button type="button" class=to_login_listOneArticle>�s�W�峹�d��</button>
	</c:if>



		<div class="modal fade" id="login_confirm_listOneArticle" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5>�z�|���n�J</h5>
      </div>
      <div class="modal-body">
        <div>�Q�n�@�_�[�J�Q�סA�n���n�J Campion ��I</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary close" data-bs-dismiss="modal">����</button>
        <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/front-end/member/login.jsp'">�n�J</button>
      </div>
    </div>
  </div>
</div>



	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script>
 var stateObj = { foo: "bar" };
 history.pushState(stateObj, "page 2", "${requestURL}");

</script>

	<script>   
  	$("#rep_login").click(function(){
		$.ajax({
			url:"/CEA103G1/back-end/article_reply/addArticle_reply.jsp?art_no=<%=articleVO.getArt_no()%>",
			type: "GET",
			success: function(data){
				$("#addrep").html(data);
			}
		});
  	});
	

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
				url : "http://localhost:8081/CEA103G1/article_likes/article_likes.do",
				data : {action: "plus_like",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,art_no:<%=articleVO.getArt_no()%>}, //�Ѽƶǻ� : action�ǻ��u�[�@�v mbr_no art_no �ǻ��n�[�@����T
				success : function(data) {
					alert("�s�W"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"�惡�g�峹�����g���\");
				}
			});
				
				$.ajax({ //�ĤG��ajax �t�d�Ǩ�articleServlet�A��s�Y�峹���g��  �ݭn���Ѽ� art_no
					type : "POST",
					url : "http://localhost:8081/CEA103G1/article/article.do",
					data : {action: "plus_like",art_no:<%=articleVO.getArt_no()%>}, //�Ѽƶǻ� : action�ǻ��u�[�@�v mbr_no art_no �ǻ��n�[�@����T
					success : function(data) {
						alert("�Y�峹���g��+1���\");
						//���Ĺ��ե[�W�t�γq��
						websocket.send(<%=articleVO.getMbr_no()%>);
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
			url : "http://localhost:8081/CEA103G1/article_likes/article_likes.do",
			data : {action: "minus_like",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,art_no:<%=articleVO.getArt_no()%>}, 
			success : function(data) {
				alert("���^"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"�惡�峹�����g���\");
			}
		});
			
			$.ajax({ //�ĤG��ajax �t�d�Ǩ�articleServlet�A��s�Y�峹���g��  �ݭn���Ѽ� art_no
				type : "POST",
				url : "http://localhost:8081/CEA103G1/article/article.do",
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
 	 				url : "http://localhost:8081/CEA103G1/article_collection/article_collection.do",
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
 	 				url : "http://localhost:8081/CEA103G1/article_collection/article_collection.do",
 	 				data : {action: "minus_collection",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,art_no:<%=articleVO.getArt_no()%>}, 
 	 				success : function(data) {
 	 					alert("����"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"�惡�峹�����æ��\");
 	 				}
 	 			});
 	})
 	


    $(".to_login_listOneArticle").click(function(){
    	$('#login_confirm_listOneArticle').modal('show');
  })
  
  $(".close").click(function(){
	  $('#login_confirm_listOneArticle').modal('hide');
  })
  </script>
	
</body>
</html>