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
  ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO"); //ArticleServlet.java(Concroller), 存入req的articleVO物件
%>

<%
  Set<String> tag_list = (Set<String>) request.getAttribute("tag_list"); //ArticleServlet.java(Concroller), 存入req的articleVO物件
%>

<%String requestURL = (String)request.getAttribute("requestURL"); %>




<% 
	boolean like_status=false; // 設一個布林值變數為like_status為false
	boolean collection_status=false;
	int ajax_mbr_no = 0;
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	//如果memberVO不為空，代表他有登入，接著查詢他是否對這篇文章按過讚
	if(memberVO!=null){
		ajax_mbr_no = memberVO.getMbr_no();
		Article_LikesService article_likesSvc = new Article_LikesService();
		//取得我按讚過的清單
		List<Article_LikesVO> my_likes_list = article_likesSvc.findbymbr_no(memberVO.getMbr_no());
		//從我按讚過的清單搜尋有沒有這篇文章
		for(Article_LikesVO element : my_likes_list){
			if(element.getArt_no()==articleVO.getArt_no()){	//如果按讚過這篇文章，like_status為true
				like_status=true;
			}
		};
		
		Article_CollectionService article_collectionSvc = new Article_CollectionService();
		//取得我收藏過的清單
		List<Article_CollectionVO> my_collection_list = article_collectionSvc.findbymbr_no(memberVO.getMbr_no());
		//從我收藏過的清單搜尋有沒有這篇文章
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
		<!-- 	如果有登入的話  可以連到該發文者的簡介-->
		<c:if test="${not empty memberVO}"> 
		<div class="author"><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=<%=articleVO.getMbr_no()%>&action=getProfile&mbr_no_mine=<%=pageContext.getAttribute("ajax_mbr_no")%>"><c:forEach var="memberVO" items="${memberDAO.all}"><c:if test="${articleVO.mbr_no==memberVO.mbr_no}">${memberVO.acc}</c:if></c:forEach></a></div>
		</c:if>
		<!-- 	如果沒有登入的話  要打開名為登入的燈箱-->	
		<c:if test="${empty memberVO}"> 
		<div class="author to_login_listOneArticle"><c:forEach var="memberVO" items="${memberDAO.all}"><c:if test="${articleVO.mbr_no==memberVO.mbr_no}">${memberVO.acc}</c:if></c:forEach></div>
		</c:if>
		</div>

<!-- 有登入的人才看的到 -->
<c:if test="${not empty memberVO}">	
		<div class="dropdown">
<!--   <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->

<!--   </a> -->
	<img class="dropdown-toggle" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" src="/CEA103G1/images/threedots.svg" width="30px" height="30px">
  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
  					<!-- 		如果有登入且該篇文章作者就是自己 那可以執行刪除及修改文章 -->
   	<c:if test="${not empty memberVO &&(articleVO.mbr_no==memberVO.mbr_no)}"> 
    <a class="dropdown-item" href="<%=request.getContextPath()%>/article/article.do?action=getOne_For_Update&art_no=${articleVO.art_no}">修改文章</a>
    <a class="dropdown-item" href="<%=request.getContextPath()%>/article/article.do?action=hide&art_no=${articleVO.art_no}">刪除文章</a>
    </c:if>
    <c:if test="${not empty memberVO &&(articleVO.mbr_no!=memberVO.mbr_no)}">
    <a class="dropdown-item reply_a" href="#">檢舉文章</a>
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
		<div class="rel_time bdAandReltime"><fmt:formatDate value="${articleVO.art_rel_time}" pattern="MM月dd日  HH:mm"/></div>
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

	<!-- 	如果有登入的話  可以對文章按/收回讚 加入/取消收藏-->
	<c:if test="${not empty memberVO}"> 
	<!--    判斷這篇文章是否按過讚，如果還沒，那先出現的就是按讚   收回讚則設為隱藏 -->
	<c:if test="${like_status eq true }">
	<div>
	<button class="like likegroup" style="display:none;"></button>
	<button class="unlike likegroup" ></button>	
	</c:if>
	<c:if test="${like_status eq false }">
	<button class="like likegroup" ></button>
	<button class="unlike likegroup"style="display:none;"></button>	
	</c:if>
	<!--    判斷這篇文章是否收藏過，如果還沒，那先出現的就是加入收藏   取消收藏則設為隱藏 -->
	<c:if test="${collection_status eq true }">
	<button class="collection likegroup" style="display:none;"></button>
	<button class="uncollection likegroup"></button>
	</c:if>
	<c:if test="${collection_status eq false }">
	<button class="collection likegroup"></button>
	<button class="uncollection likegroup" style="display:none;"></button>
	</c:if>
	
	</c:if>
<!-- 	如果沒有登入的話  只會顯示按讚、收藏的按紐，點擊要打開名為登入的燈箱-->	
	<c:if test="${empty memberVO}"> 
	<button class="like_no_login to_login_listOneArticle likegroup" type="button"></button>
	<button class="collection_no_login to_login_listOneArticle likegroup" type="button"></button>
	</c:if>
	
	

</div>

<br>
<br>
<br>
<br>

<div>文章標籤</div>
<c:forEach var="tag_list" items="${tag_list}">
<div><a href="<%=request.getContextPath()%>/article/article.do?tag=${tag_list}&action=getArticlesByTagFor_Display">${tag_list}</a></div>
</c:forEach>


<div id = "addrep"></div>
<div id="demo"></div>
		<!-- 	如果有登入的話  可以對文章留言-->
	<c:if test="${not empty memberVO}"> 
	<button id=rep_login onclick="location.href='/CEA103G1/front-end/article_reply/addArticle_reply.jsp?art_no=<%=articleVO.getArt_no()%>&mbr_no=<%=memberVO.getMbr_no()%>'">我要回應</button>
	</c:if>
<!-- 	如果沒有登入的話  要打開名為登入的燈箱-->	
	<c:if test="${empty memberVO}"> 
	<button type="button" class=to_login_listOneArticle>新增文章留言</button>
	</c:if>



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


			<div class="modal" id="reply_modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5>檢舉文章</h5>
      </div>
      <div class="modal-body">
        <form METHOD="post" ACTION="/CEA103G1/article_report/article_report.do" name="form1" autocomplete>
        	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios" value="中傷、歧視、挑釁或謾罵他人">
  					<label class="form-check-label" for="exampleRadios1">
    					中傷、歧視、挑釁或謾罵他人
  					</label>
	   		 </div>
	   		         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios" value="中傷、歧視、挑釁或謾罵他人">
  					<label class="form-check-label" for="exampleRadios1">
    					商業廣告宣傳內容
  					</label>
	   		 </div>
	   	         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios" value="惡意洗板、重複張貼">
  					<label class="form-check-label" for="exampleRadios1">
    					惡意洗板、重複張貼
  					</label>
	   	 </div>
	   	         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios"  value="包含色情、露點、性騷擾或血腥恐怖等讓人不舒服之內容">
  					<label class="form-check-label" for="exampleRadios1">
    					包含色情、露點、性騷擾或血腥恐怖等讓人不舒服之內容
  					</label>
	   	 </div>
	   	         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios" value="暴力、傷害他人或傷害動物的內容">
  					<label class="form-check-label" for="exampleRadios1">
    					暴力、傷害他人或傷害動物的內容
  					</label>
	   	 </div>
	   	         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios" value="包含廣告、商業宣傳之內容">
  					<label class="form-check-label" for="exampleRadios1">
    					包含廣告、商業宣傳之內容
  					</label>
	   	 </div>
	   	         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios"  value="文章發表在不適當的看板">
  					<label class="form-check-label" for="exampleRadios1">
    					文章發表在不適當的看板
  					</label>
	   	 </div>
	   	         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios"  value="文章內容空泛或明顯無意義內容">
  					<label class="form-check-label" for="exampleRadios1">
    					文章內容空泛或明顯無意義內容
  					</label>
	   	 </div>
	   	         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios"  value="嚴禁發表、轉貼未經證實的謠言或不實訊息">
  					<label class="form-check-label" for="exampleRadios1">
    					嚴禁發表、轉貼未經證實的謠言或不實訊息
  					</label>
	   	 </div>
	   	         	<div class="form-check">
  				<input class="form-check-input" type="radio" name="replyRadios"  value="禁止發表太過偏離主題的文章或留言。">
  					<label class="form-check-label" for="exampleRadios1">
    					禁止發表太過偏離主題的文章或留言。
  					</label>
  							
		<input type="hidden" name="mbr_no" value="${memberVO.getMbr_no()}" />
		<input type="hidden" name="art_no" value="${articleVO.getArt_no()}" />
		<input type="hidden" name="action" value="insert">
		
	   	 </div>
	   	 <div class="modal-footer">
        <button type="button" class="btn btn-secondary close_reply_modal" data-bs-dismiss="modal">取消</button>
        <button type="submit" class="btn btn-primary">完成</button>
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
 	 	 
			$.ajax({ //第一個ajax 負責傳到article_likesServlet 新增某人對某文章的按讚  需要的參數: art_no mbr_no 
				type : "POST",
				url : "/CEA103G1/article_likes/article_likes.do",
				data : {action: "plus_like",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,art_no:<%=articleVO.getArt_no()%>}, //參數傳遞 : action傳遞「加一」 mbr_no art_no 傳遞要加一的資訊
				success : function(data) {
					alert("新增"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"對此篇文章的按讚成功");
				}
			});
				
				$.ajax({ //第二個ajax 負責傳到articleServlet，更新某文章的讚數  需要的參數 art_no
					type : "POST",
					url : "/CEA103G1/article/article.do",
					data : {action: "plus_like",art_no:<%=articleVO.getArt_no()%>}, //參數傳遞 : action傳遞「加一」 mbr_no art_no 傳遞要加一的資訊
					success : function(data) {
						alert("某文章的讚數+1成功");
						//雅凰嘗試加上系統通知
						websocket.send(<%=articleVO.getMbr_no()%>+'/like');
						//雅凰嘗試加上系統通知
					}
				});
 	})
 	
 	 	$(".unlike").click(function(){
 		var x=document.getElementById("like_td").innerHTML;
 	 	 x=parseInt(x)-1;
 	 	 document.getElementById("like_td").innerHTML=x;
 	 	 $(this).hide();
 	 	 $(".like").show();
 	 	 
 		$.ajax({ //第一個ajax 負責傳到article_likesServlet 刪除某人對某文章的按讚  
			type : "POST",
			url : "/CEA103G1/article_likes/article_likes.do",
			data : {action: "minus_like",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,art_no:<%=articleVO.getArt_no()%>}, 
			success : function(data) {
				alert("收回"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"對此文章的按讚成功");
			}
		});
			
			$.ajax({ //第二個ajax 負責傳到articleServlet，更新某文章的讚數  需要的參數 art_no
				type : "POST",
				url : "/CEA103G1/article/article.do",
				data : {action: "minus_like",art_no:<%=articleVO.getArt_no()%>}, 
				success : function(data) {
					alert("某文章的讚數-1成功");
				}
			});
 	})
 	
 	
 	
 	 	$(".collection").click(function(){
 	 		$(this).hide();
 	 		$(".uncollection").show();

 	 	 	
 	 				$.ajax({ //負責傳到article_collectionServlet 新增某人對某文章的收藏  
 	 				type : "POST",
 	 				url : "/CEA103G1/article_collection/article_collection.do",
 	 				data : {action: "plus_collection",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,art_no:<%=articleVO.getArt_no()%>}, //參數傳遞 : action傳遞「加一」 mbr_no art_no 傳遞要加一的資訊
 	 				success : function(data) {
 	 					alert("新增"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"對此文章的收藏成功");
 	 				}
 	 			}); 
 	})
 	
 	 	$(".uncollection").click(function(){
 	 		$(this).hide();
 	 		$(".collection").show();
			
 	 		$.ajax({ //負責傳到article_collectionServlet 刪除某人對某文章的收藏  需要的參數: art_no mbr_no   目前mbr_no寫死 之後要從session get到目前是哪個會員對這篇文章按讚 
 	 				type : "POST",
 	 				url : "/CEA103G1/article_collection/article_collection.do",
 	 				data : {action: "minus_collection",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,art_no:<%=articleVO.getArt_no()%>}, 
 	 				success : function(data) {
 	 					alert("取消"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"對此文章的收藏成功");
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