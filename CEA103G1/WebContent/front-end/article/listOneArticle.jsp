<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.board_class.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
  ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO"); //ArticleServlet.java(Concroller), 存入req的articleVO物件
%>

<%
  Set<String> tag_list = (Set<String>) request.getAttribute("tag_list"); //ArticleServlet.java(Concroller), 存入req的articleVO物件
%>

<%String requestURL = (String)request.getAttribute("requestURL"); %>


<% 
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO"); 
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
   #like{ 
   background-image:url('/CEA103G1/images/heart-outline.svg');
   width:20px; 
   height:18px;
   border:0px; 
   background-size: 20px 18px; 
   background-repeat: no-repeat; 
   } 
   
   #like:hover{
   background-image:url('/CEA103G1/images/heart.svg'); 
   }
   #unlike{ 
   background-image:url('/CEA103G1/images/heart.svg'); 
   width:20px; 
   height:18px; 
   border:0px; 
   background-size: 20px 18px; 
   background-repeat: no-repeat; 
   }

   
   
   
   #collection{ 
   background-image:url('/CEA103G1/images/bookmarks-outline.svg');
   width:20px; 
   height:18px;
   border:0px; 
   background-size: 20px 18px; 
   background-repeat: no-repeat; 
   } 
   
   #collection:hover{
   background-image:url('/CEA103G1/images/bookmarks.svg'); 
   }
   #uncollection{ 
   background-image:url('/CEA103G1/images/bookmarks.svg'); 
   width:20px; 
   height:18px; 
   border:0px; 
   background-size: 20px 18px; 
   background-repeat: no-repeat; 
   }
   #uncollection:hover{
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
		<!-- 	如果有登入的話 -->
	<c:if test="${not empty memberVO }"> 
	<td><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=<%=articleVO.getMbr_no()%>&action=getProfile"><%=articleVO.getMbr_no()%></a></td>
	</c:if>
<!-- 	如果沒有登入的話  要打開名為登入的燈箱-->	
	<c:if test="${empty memberVO }"> 
	<td><div id="to_login"><%=articleVO.getMbr_no()%></div></td>
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
<button id="like" onclick="add_like()"></button>
<button id="unlike" onclick="minus_like()" style="display:none;"></button>
<button id="collection" onclick="add_collection()"></button>
<button id="uncollection" onclick="minus_collection()" style="display:none;"></button>
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
<button id=rep>新增文章留言</button>


		<div class="modal fade" id="login_confirm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5>您尚未登入</h5>
      </div>
      <div class="modal-body">
        <div>想要一起加入討論，要先登入 Campion 唷！</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/front-end/member/login.jsp'">登入</button>
      </div>
    </div>
  </div>
</div>



	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script>
 var stateObj = { foo: "bar" };
 history.pushState(stateObj, "page 2", "${requestURL}");
</script>

	<script>   
  	$("#rep").click(function(){
		$.ajax({
			url:"/CEA103G1/back-end/article_reply/addArticle_reply.jsp?art_no=<%=articleVO.getArt_no()%>",
			type: "GET",
			success: function(data){
				$("#addrep").html(data);
			}
		});
  	});
	
//  	$("#get").click(function(){
		$.ajax({
			url:"/CEA103G1/front-end/article_reply/listOneArticle_Replies.jsp?art_no=<%=articleVO.getArt_no()%>&requestURL=<%=request.getServletPath()%>",
			type: "GET",
			success: function(data){
				$("#demo").html(data);
			}
		});
//  	});
 	
 	
 	function add_like()
 	{
 	 var x=document.getElementById("like_td").innerHTML;
 	 x=parseInt(x)+1;
 	 document.getElementById("like_td").innerHTML=x;
 	 document.getElementById("like").style.display="none"; 
 	 document.getElementById("unlike").style.display="inline-block"; 
 	 
 	 
			$.ajax({ //第一個ajax 負責傳到article_likesServlet 新增某人對某文章的按讚  需要的參數: art_no mbr_no   目前mbr_no寫死 之後要從session get到目前是哪個會員對這篇文章按讚 
			type : "POST",
			url : "http://localhost:8081/CEA103G1/article_likes/article_likes.do",
			data : {action: "plus_like",mbr_no:"10001",art_no:<%=articleVO.getArt_no()%>}, //參數傳遞 : action傳遞「加一」 mbr_no art_no 傳遞要加一的資訊
			success : function(data) {
				alert("新增某人對某文章的按讚成功");
			}
		});
			
			$.ajax({ //第二個ajax 負責傳到articleServlet，更新某文章的讚數  需要的參數 art_no
				type : "POST",
				url : "http://localhost:8081/CEA103G1/article/article.do",
				data : {action: "plus_like",art_no:<%=articleVO.getArt_no()%>}, //參數傳遞 : action傳遞「加一」 mbr_no art_no 傳遞要加一的資訊
				success : function(data) {
					alert("某文章的讚數+1成功");
				}
			});
 	 
 	}
 	
 	
 	function minus_like()
 	{
 	 var x=document.getElementById("like_td").innerHTML;
 	 x=parseInt(x)-1; 
 	 document.getElementById("like_td").innerHTML=x; //網頁上顯示的按讚-1
 	 
 	 
 	 document.getElementById("unlike").style.display="none"; //收回讚設為隱藏
 	 document.getElementById("like").style.display="inline-block";  //按讚設為顯示
 	 
 	 
 	 
		$.ajax({ //第一個ajax 負責傳到article_likesServlet 刪除某人對某文章的按讚  需要的參數: art_no mbr_no   目前mbr_no寫死 之後要從session get到目前是哪個會員對這篇文章按讚 
			type : "POST",
			url : "http://localhost:8081/CEA103G1/article_likes/article_likes.do",
			data : {action: "minus_like",mbr_no:"10001",art_no:<%=articleVO.getArt_no()%>}, 
			success : function(data) {
				alert("刪除某人對某文章的按讚成功");
			}
		});
			
			$.ajax({ //第二個ajax 負責傳到articleServlet，更新某文章的讚數  需要的參數 art_no
				type : "POST",
				url : "http://localhost:8081/CEA103G1/article/article.do",
				data : {action: "plus_like",art_no:<%=articleVO.getArt_no()%>}, 
				success : function(data) {
					alert("某文章的讚數-1成功");
				}
			});
 	}
 	
 	
 	
 	
 	function add_collection()
 	{
	 document.getElementById("collection").style.display="none"; 
	 document.getElementById("uncollection").style.display="inline-block"; 
 	
			$.ajax({ //負責傳到article_collectionServlet 新增某人對某文章的按讚  需要的參數: art_no mbr_no   目前mbr_no寫死 之後要從session get到目前是哪個會員對這篇文章收藏 
			type : "POST",
			url : "http://localhost:8081/CEA103G1/article_collection/article_collection.do",
			data : {action: "plus_collection",mbr_no:"10001",art_no:<%=articleVO.getArt_no()%>}, //參數傳遞 : action傳遞「加一」 mbr_no art_no 傳遞要加一的資訊
			success : function(data) {
				alert("新增某人對某文章的收藏成功");
			}
		}); 
 	}

 	
 	
 	function minus_collection()
 	{
 	 document.getElementById("uncollection").style.display="none"; 
	 document.getElementById("collection").style.display="inline-block"; 
 	
		$.ajax({ //負責傳到article_collectionServlet 刪除某人對某文章的收藏  需要的參數: art_no mbr_no   目前mbr_no寫死 之後要從session get到目前是哪個會員對這篇文章按讚 
			type : "POST",
			url : "http://localhost:8081/CEA103G1/article_collection/article_collection.do",
			data : {action: "minus_collection",mbr_no:"10001",art_no:<%=articleVO.getArt_no()%>}, 
			success : function(data) {
				alert("刪除某人對某文章的收藏成功");
			}
		});
 	}
 	
	</script>
  <script>
  $("#to_login").click(function(){
	  $('#login_confirm').modal('show');
  })
  </script>
	
</body>
</html>