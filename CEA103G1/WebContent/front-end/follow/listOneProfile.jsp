<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.follow.model.*"%>
<%@ page import="com.article.model.*"%>


<%int fans_num = (Integer)request.getAttribute("fans_num"); %>
<%int follows_num = (Integer)request.getAttribute("follows_num"); %>
<%int article_num = (Integer)request.getAttribute("article_num"); %>
<%String mbr_no = String.valueOf(request.getAttribute("mbr_no"));%>
<%
  ArticleService articleSvc = new ArticleService();
  List<ArticleVO> articleVO = (List<ArticleVO>)request.getAttribute("articleVO");
  FollowService followSvc = new FollowService();
  List<FollowVO> fans_list = (List<FollowVO>)request.getAttribute("followVO_fans");
  List<FollowVO> follows_list = (List<FollowVO>)request.getAttribute("followVO_follows");
  pageContext.setAttribute("fans_list", fans_list); 
  pageContext.setAttribute("follows_list", follows_list); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<link rel="stylesheet" type="text/css" href="/CEA103G1/profile.css">
<style>
.profile-stat-count{
    display: inline-block;
    font-size: 1.6rem;
    line-height: 1.5;
    margin-right: 4rem;
    cursor: pointer;
}
a {
    text-decoration:none;
    color:black;
}
a:link {

}
a:visited {

}
a:hover {

}
a:active {

}

.fans{
padding: 8px 16px;
}
.follow{
padding: 8px 16px;
}
</style>
<title>Profile</title>
</head>
<body>

    <header>
        <div class="container">
            <div class="profile">
                <div class="profile-image">
                    <img src="/CEA103G1/images/profile.png" width="150px" height="150px">
                </div>
                <div class="profile-user-settings">
                    <h1 class="profile-user-name"><%=mbr_no%></h1>
                    
                    <button class="btn profile-edit-btn">編輯個人檔案</button>
                    <button class="btn profile-edit-btn" id=follow-btn onclick="add_follow()">追蹤對方</button>
                    <button class="btn profile-edit-btn" id=cancel-follow-btn onclick="cancel_follow()" style="display:none">取消追蹤</button>
                    <button class="btn profile-edit-btn">發送站內信</button>
                    <button class="btn profile-settings-btn" aria-label="profile settings"><i class="fas fa-cog" aria-hidden="true"></i></button>
                </div>
                <div class="profile-stats">
<!--                     <ul> -->
<%--                         <li><span class="profile-stat-count article"><%=article_num%></span> 貼文</li> --%>
<%--                         <li><span class="profile-stat-count fans"><%=fans_num%></span> 追蹤者</li> --%>
<%--                         <li><span class="profile-stat-count follows"><%=follows_num %></span> 追蹤中</li> --%>
<!--                     </ul> -->

                        <div class="profile-stat-count article"><%=article_num%> 貼文</div>
                        <div class="profile-stat-count fans"><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${mbr_no}&action=getFollowers" ><span id=follower><%=fans_num%></span>追蹤者</a></div>
                        <div class="profile-stat-count follows"><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${mbr_no}&action=getFollowing" ><span id=following><%=follows_num%></span>追蹤中</a></div>
 
                </div>
                <div class="profile-bio">
                    <p><span class="profile-real-name">lalalal</span>teststsestrseseseseeseewqewqeqw</p>
                </div>
            </div>
            <!-- End of profile section -->
        </div>
        <!-- End of container -->
    </header>

<div>發過的文章清單</div>
<br>
<br>
<c:forEach var="articleVO" items="${articleVO}">
<div><a href="<%=request.getContextPath()%>/article/article.do?art_no=${articleVO.art_no}&action=getOne_From2">${articleVO.art_title}</a></div>
</c:forEach>

<br>
<br>
<br>


<!-- 粉絲名單 -->
<%-- <c:forEach var="fans_list" items="${fans_list}"> --%>
<%-- <div class=fans><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${fans_list.flw_mbr_no}&action=getProfile" >${fans_list.flw_mbr_no}</a></div> --%>
<%-- </c:forEach> --%>

<!-- <br> -->
<!-- <br> -->
<!-- <br> -->

<!-- 追蹤名單 -->
<%-- <c:forEach var="follows_list" items="${follows_list}"> --%>
<%-- <div class=follow><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${follows_list.flwed_mbr_no}&action=getProfile">${follows_list.flwed_mbr_no}</a></div> --%>
<%-- </c:forEach> --%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	function add_follow()
 	{
 	 var x=document.getElementById("follower").innerHTML;
 	 x=parseInt(x)+1;
 	 document.getElementById("follower").innerHTML=x;
 	 document.getElementById("follow-btn").style.display="none"; 
 	 document.getElementById("cancel-follow-btn").style.display="inline-block"; 
 	 
 	 
			$.ajax({ //負責傳到followServlet 新增某人對某人的追蹤  需要的參數: 追蹤者mbr_no 被追蹤者的 mbr_no   目前追蹤者mbr_no寫死 之後要從session get到目前是哪個會員對這個人追蹤
			type : "POST",
			url : "http://localhost:8081/CEA103G1/follow/follow.do",
			data : {action: "add_follow",flw_mbr_no:"10001",flwed_mbr_no:<%=mbr_no%>}, //參數傳遞 
			success : function(data) {
				alert("新增某人對某人的追蹤成功");
			}
		}); 
 	}
	
	
	function cancel_follow()
 	{
 	 var x=document.getElementById("follower").innerHTML;
 	 x=parseInt(x)-1;
 	 document.getElementById("follower").innerHTML=x;
 	 document.getElementById("follow-btn").style.display="inline-block"; 
 	 document.getElementById("cancel-follow-btn").style.display="none"; 
 	 
 	 
			$.ajax({ //負責傳到followServlet 取消某人對某人的追蹤  需要的參數: 追蹤者mbr_no 被追蹤者的 mbr_no   目前追蹤者mbr_no寫死 之後要從session get到目前是哪個會員對這個人追蹤
			type : "POST",
			url : "http://localhost:8081/CEA103G1/follow/follow.do",
			data : {action: "cancel_follow",flw_mbr_no:"10001",flwed_mbr_no:<%=mbr_no%>}, //參數傳遞 
			success : function(data) {
				alert("取消某人對某人的追蹤成功");
			}
		}); 
 	}
	
</script>
</body>
</html>