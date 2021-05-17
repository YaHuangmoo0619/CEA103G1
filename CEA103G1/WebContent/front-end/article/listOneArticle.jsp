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


<html>
<head>
<title>文章資料 - listOneArticle.jsp 前台</title>

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
		 <h3>文章資料 - ListOneArticle.jsp 前台</h3>
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
		<!-- 	如果有登入的話  可以連到該發文者的簡介-->
	<c:if test="${not empty memberVO}"> 
	<td><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=<%=articleVO.getMbr_no()%>&action=getProfile&mbr_no_mine=<%=pageContext.getAttribute("ajax_mbr_no")%>"><%=articleVO.getMbr_no()%></a></td>
	</c:if>
<!-- 	如果沒有登入的話  要打開名為登入的燈箱-->	
	<c:if test="${empty memberVO}"> 
	<td><div class= to_login_listOneArticle><%=articleVO.getMbr_no()%></div></td>
	</c:if>
		
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
							type="hidden" name="action" value="hide">
					</FORM>
				</td> 
   </tr>
   
   
   <tr>
		<td>讚數</td>
		<td id = like_td>${articleVO.likes}</td>
  </tr>
  
     <tr>
		<td>留言數</td>
		<td id = like_td>${articleVO.replies}</td>
  </tr>
   
</table>

<div><%=(String)request.getAttribute("requestURI")%></div>

<div>
<div>回應  </div>
	<!-- 	如果有登入的話  可以對文章按/收回讚 加入/取消收藏-->
	<c:if test="${not empty memberVO}"> 
	<!--    判斷這篇文章是否按過讚，如果還沒，那先出現的就是按讚   收回讚則設為隱藏 -->
	<c:if test="${like_status eq true }">
	<button class="like" style="display:none;"></button>
	<button class="unlike" ></button>
	</c:if>
	<c:if test="${like_status eq false }">
	<button class="like" ></button>
	<button class="unlike"style="display:none;"></button>	
	</c:if>
	
	<!--    判斷這篇文章是否收藏過，如果還沒，那先出現的就是加入收藏   取消收藏則設為隱藏 -->
	<c:if test="${collection_status eq true }">
	<button class="collection" style="display:none;"></button>
	<button class="uncollection"></button>
	</c:if>
	
	<c:if test="${collection_status eq false }">
	<button class="collection"></button>
	<button class="uncollection" style="display:none;"></button>
	</c:if>

	</c:if>
<!-- 	如果沒有登入的話  只會顯示按讚、收藏的按紐，點擊要打開名為登入的燈箱-->	
	<c:if test="${empty memberVO}"> 
	<button class="like_no_login to_login_listOneArticle" type="button"></button>
	<button class="collection_no_login to_login_listOneArticle" type="button"></button>
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
	<button id=rep_login>新增文章留言</button>
	</c:if>
<!-- 	如果沒有登入的話  要打開名為登入的燈箱-->	
	<c:if test="${empty memberVO}"> 
	<button type="button" class=to_login_listOneArticle>新增文章留言</button>
	</c:if>



		<div class="modal fade" id="login_confirm_listOneArticle" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5>您尚未登入</h5>
      </div>
      <div class="modal-body">
        <div>想要一起加入討論，要先登入 Campion 唷！</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary close" data-bs-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/front-end/member/login.jsp'">登入</button>
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
 	 	 
			$.ajax({ //第一個ajax 負責傳到article_likesServlet 新增某人對某文章的按讚  需要的參數: art_no mbr_no 
				type : "POST",
				url : "http://localhost:8081/CEA103G1/article_likes/article_likes.do",
				data : {action: "plus_like",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,art_no:<%=articleVO.getArt_no()%>}, //參數傳遞 : action傳遞「加一」 mbr_no art_no 傳遞要加一的資訊
				success : function(data) {
					alert("新增"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"對此篇文章的按讚成功");
				}
			});
				
				$.ajax({ //第二個ajax 負責傳到articleServlet，更新某文章的讚數  需要的參數 art_no
					type : "POST",
					url : "http://localhost:8081/CEA103G1/article/article.do",
					data : {action: "plus_like",art_no:<%=articleVO.getArt_no()%>}, //參數傳遞 : action傳遞「加一」 mbr_no art_no 傳遞要加一的資訊
					success : function(data) {
						alert("某文章的讚數+1成功");
						//雅凰嘗試加上系統通知
						websocket.send(<%=articleVO.getMbr_no()%>);
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
			url : "http://localhost:8081/CEA103G1/article_likes/article_likes.do",
			data : {action: "minus_like",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,art_no:<%=articleVO.getArt_no()%>}, 
			success : function(data) {
				alert("收回"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"對此文章的按讚成功");
			}
		});
			
			$.ajax({ //第二個ajax 負責傳到articleServlet，更新某文章的讚數  需要的參數 art_no
				type : "POST",
				url : "http://localhost:8081/CEA103G1/article/article.do",
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
 	 				url : "http://localhost:8081/CEA103G1/article_collection/article_collection.do",
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
 	 				url : "http://localhost:8081/CEA103G1/article_collection/article_collection.do",
 	 				data : {action: "minus_collection",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,art_no:<%=articleVO.getArt_no()%>}, 
 	 				success : function(data) {
 	 					alert("取消"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"對此文章的收藏成功");
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