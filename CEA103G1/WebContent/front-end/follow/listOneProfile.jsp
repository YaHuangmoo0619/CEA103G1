<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.follow.model.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.member.model.*" %>

<%int fans_num = (Integer)request.getAttribute("fans_num"); %>
<%int follows_num = (Integer)request.getAttribute("follows_num"); %>
<%int article_num = (Integer)request.getAttribute("article_num"); %>
<%String mbr_no = String.valueOf(request.getAttribute("mbr_no"));%>
<%MemberVO memberVO = (MemberVO)session.getAttribute("memberVO"); %>
<%int mbr_no_self=memberVO.getMbr_no(); %>



<%
  ArticleService articleSvc = new ArticleService();
  List<ArticleVO> articleVO = (List<ArticleVO>)request.getAttribute("articleVO"); //發表過的文章
  FollowService followSvc = new FollowService();
  List<FollowVO> fans_list = (List<FollowVO>)request.getAttribute("followVO_fans"); //這個人的粉絲
  List<FollowVO> follows_list = (List<FollowVO>)request.getAttribute("followVO_follows"); //這個人追蹤的人
  List<Integer> followVO_mine = (List<Integer>)request.getAttribute("followVO_mine"); //我追蹤的人
  ArrayList<Integer> follow_mine = new ArrayList<Integer>(); //專門用來放我追蹤的人的號碼
  
  pageContext.setAttribute("fans_list", fans_list); 
  pageContext.setAttribute("follows_list", follows_list); 
  pageContext.setAttribute("followVO_mine", followVO_mine);
  pageContext.setAttribute("memberVO", memberVO);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">

<link rel="stylesheet" type="text/css" href="/CEA103G1/profile.css">
<%@ include file="/part-of/partOfCampion_frontTop_css.txt"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
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
list-style:none;
}
.follows{
padding: 8px 16px;
list-style:none;
}
.fans_list{
display:inline-block;
}
.follows_list
{
display:inline-block;
}

.follow{
padding: 8px 16px;
}

.clear{
clear: both;
}
</style>
<title>Profile</title>

</head>
<body>
	<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
    <header>
        <div class="container">
            <div class="profile">
                <div class="profile-image">
                    <img src="/CEA103G1/images/profile.png" width="150px" height="150px">
                </div>
                <div class="profile-user-settings">
                    <h1 class="profile-user-name"><%=mbr_no%></h1>
                    
                    
                    
                    
                  	<%boolean status=false; %>

                    <c:forEach var="followVO_mine" items="${followVO_mine}">

					<c:if test="${followVO_mine==Integer.valueOf(mbr_no)}">

                    <!--   如果在我的追蹤名單中有這個人的話，則顯示button為追蹤中 並將status改為1-->
                    <button class="btn profile-edit-btn" id=cancel-follow-btn onclick="cancel_follow()">追蹤中</button>
                    <button class="btn profile-edit-btn" id=follow-btn onclick="add_follow()" style="display:none">追蹤對方</button>
                    <%status=true;%>
                    </c:if>
		            </c:forEach>   
		            
		                             

					<!-- 如果在我的追蹤名單中沒有這個人，而且這個人不等於「我」 則顯示button為「追蹤對方」 -->
					<c:if test="<%=status==false&&Integer.valueOf(mbr_no)!=mbr_no_self%>">
					<button class="btn profile-edit-btn" id=follow-btn onclick="add_follow()">追蹤對方</button>
					<button class="btn profile-edit-btn" id=cancel-follow-btn onclick="cancel_follow()" style="display:none">追蹤中</button>
					<%status=true;%>
					</c:if>

					
<!-- 					如果這個人就是我，那就出現「編輯個人檔案」 -->
					<c:if test="<%=Integer.valueOf(mbr_no)==mbr_no_self%>"> 
					<button class="btn profile-edit-btn">編輯個人檔案</button>
					</c:if>

					

                    <button class="btn profile-edit-btn" onclick="location.href='/CEA103G1/front-end/member_mail/addMember_mail.jsp?mbr_no=<%=mbr_no%>'">發送站內信</button>
                    <button class="btn profile-settings-btn" aria-label="profile settings"><i class="fas fa-cog" aria-hidden="true"></i></button>
                </div>
                <div class="profile-stats">
                        <div class="profile-stat-count article"><%=article_num%> 貼文</div>

                        <div class="profile-stat-count fans" onclick="showModal1()"><span id=follower><%=fans_num%></span>追蹤者</div>
                        <div class="profile-stat-count follows" onclick="showModal2()"><span id=following><%=follows_num%></span>追蹤中</div>
 
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

<div class="posted_arts">
<c:forEach var="articleVO" items="${articleVO}">
<%-- <div class=released_articles><a href="<%=request.getContextPath()%>/article/article.do?art_no=${articleVO.art_no}&action=getOne_From2">${articleVO.art_title}</a></div> --%>
<div class=released_articles id="${articleVO.art_no}" data-toggle="modal" data-target="#basicModal">${articleVO.art_title}<div class=released_articles_no style="display:none">${articleVO.art_no}</div></div>	
</c:forEach>
</div>

<br>
<br>
<br>


<!-- 粉絲名單 -->


<!-- <br> -->
<!-- <br> -->
<!-- <br> -->

<!-- 追蹤名單 -->
<%-- <c:forEach var="follows_list" items="${follows_list}"> --%>
<%-- <div class=follow><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${follows_list.flwed_mbr_no}&action=getProfile">${follows_list.flwed_mbr_no}</a></div> --%>
<%-- </c:forEach> --%>

<div>
<div class="modal" tabindex="-1" role="dialog" id="test1">
     <div class="modal-dialog" role="document"> 
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">粉絲</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button> 
            </div>
            <div class="modal-body">
				<c:forEach var="fans_list" items="${fans_list}"> 
<!-- 				對每個粉絲清單中的人跟我的追蹤清單的人做比對進行檢查 -->
				<li class=fans>
					<div class=fans_list style="width:47px;height:38px;"><img src="/CEA103G1/images/profile.png" width="30" height="30"></div>
					<div class=fans_list style="width:241px;height:36px;"><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${fans_list.flw_mbr_no}&action=getProfile" >${fans_list.flw_mbr_no}</a></div>
					
<!-- 					如果我追蹤的人裡面有這個人 -->
					<c:if test="${followVO_mine.contains(fans_list.flw_mbr_no)}">
					<div class="fans_list cancel_follow_in_list" style="width:48px;height:38px; ">追蹤中</div>
					<div class="fans_list add_follow_in_list" style="width:48px;height:38px;display:none;">追蹤</div>
					</c:if>

<!-- 					如果我追蹤的人裡面沒有這個人 -->					
					<c:if test="${followVO_mine.contains(fans_list.flw_mbr_no)==false}">
					<div class="fans_list cancel_follow_in_list" style="width:48px;height:38px;display:none">追蹤中</div>
					<div class="fans_list add_follow_in_list" style="width:48px;height:38px;">追蹤</div>
					</c:if>
					</li>
					
				</c:forEach>	
 
            </div>
       </div>
   </div>
</div>
</div>


<div>
<div class="modal" tabindex="-1" role="dialog" id="test2">
     <div class="modal-dialog" role="document"> 
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">追蹤名單</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button> 
            </div>
            <div class="modal-body">
				<c:forEach var="follows_list" items="${follows_list}">
				<li class=follows>
					<div class=follows_list style="width:47px;height:38px;"><img src="/CEA103G1/images/profile.png" width="30" height="30"></div>
					<div class=follows_list style="width:241px;height:36px;"><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${follows_list.flwed_mbr_no}&action=getProfile">${follows_list.flwed_mbr_no}</a></div>

<!-- 					如果我追蹤的人裡面有這個人 -->
					<c:if test="${followVO_mine.contains(follows_list.flwed_mbr_no)}">
					<div class="follows_list cancel_follow_in_list" style="width:48px;height:38px; ">追蹤中</div>
					<div class="follows_list add_follow_in_list" style="width:48px;height:38px;display:none;">追蹤</div>
					</c:if>
					
<!-- 					如果我追蹤的人裡面沒有這個人 -->					
					<c:if test="${followVO_mine.contains(follows_list.flwed_mbr_no)==false}">
					<div class="follows_list cancel_follow_in_list" style="width:48px;height:38px;display:none">追蹤中</div>
					<div class="follows_list add_follow_in_list" style="width:48px;height:38px;">追蹤</div>
					</c:if>
					</li>
				</c:forEach> 
            </div>
       </div>
   </div>
</div>
</div>






		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-body">
						<!-- =========================================以下為原listOneArticle.jsp的內容========================================== -->
						<div id=oneArticle></div>
						<!-- =========================================以上為原listOneArticle.jsp的內容========================================== -->
					</div>
				</div>
			</div>
		</div>



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
			data : {action: "add_follow",flw_mbr_no:<%=memberVO.getMbr_no()%>,flwed_mbr_no:<%=mbr_no%>}, //參數傳遞 
			success : function(data) {
				alert("新增"+<%=memberVO.getMbr_no()%>+"對"+<%=mbr_no%>+"的追蹤成功");
				window.location.reload()  //重新整理頁面
			}
		}); 
		 	 $(this).hide();
		 	 $(this).prevAll().show();
		 	 $(this).nextAll().show();
 	}
	
	
	
	$(".add_follow_in_list").click(function(){
	 	 var want_follow_no = $(this).prev().prev().text(); //前一個元素的內容，即想追蹤的人的號碼 
			$.ajax({ //負責傳到followServlet 新增某人對某人的追蹤  需要的參數: 追蹤者mbr_no 被追蹤者的 mbr_no 
				type : "POST",
				url : "http://localhost:8081/CEA103G1/follow/follow.do",
				data : {action: "add_follow",flw_mbr_no:<%=memberVO.getMbr_no()%>,flwed_mbr_no:want_follow_no}, //參數傳遞 
				success : function(data) {
					alert("新增"+<%=memberVO.getMbr_no()%>+"對"+want_follow_no+"的追蹤成功");
				}
			});
	 	 $(this).hide();
	 	 $(this).prevAll().show();
	 	 $(this).nextAll().show();
	});

	
	
	function cancel_follow()
 	{
 	 var x=document.getElementById("follower").innerHTML;
 	 x=parseInt(x)-1;
 	 document.getElementById("follower").innerHTML=x;
 	 document.getElementById("follow-btn").style.display="inline-block"; 
 	 document.getElementById("cancel-follow-btn").style.display="none"; 
 	 
 	 
			$.ajax({ //負責傳到followServlet 取消某人對某人的追蹤  需要的參數: 追蹤者mbr_no 被追蹤者的 mbr_no 
			type : "POST",
			url : "http://localhost:8081/CEA103G1/follow/follow.do",
			data : {action: "cancel_follow",flw_mbr_no:<%=memberVO.getMbr_no()%>,flwed_mbr_no:<%=mbr_no%>}, //參數傳遞 
			success : function(data) {
				alert("取消"+<%=memberVO.getMbr_no()%>+"對"+<%=mbr_no%>+"的追蹤成功");
				window.location.reload()  //重新整理頁面
			}
		}); 
			
		 	 $(this).hide();
		 	 $(this).prevAll().show();
		 	 $(this).nextAll().show();
		 	 
// 		 	待解決: 還要ajax刷新這個人的粉絲名單名單，不然會造成明明追蹤/取消追蹤，但點進去還沒有更新

 	}
	
	
	
	
	$(".cancel_follow_in_list").click(function(){
	 	 var want_cancel_follow_no = $(this).prev().text(); //前一個元素的內容，即想取消追蹤的人的號碼 
			$.ajax({ //負責傳到followServlet 取消某人對某人的追蹤  需要的參數: 追蹤者mbr_no 被追蹤者的 mbr_no
				type : "POST",
				url : "http://localhost:8081/CEA103G1/follow/follow.do",
				data : {action: "cancel_follow",flw_mbr_no:<%=memberVO.getMbr_no()%>,flwed_mbr_no:want_cancel_follow_no}, //參數傳遞 
				success : function(data) {
					alert("取消"+<%=memberVO.getMbr_no()%>+"對"+want_cancel_follow_no+"的追蹤成功");
				}
			});
	 	 $(this).hide();
	 	 $(this).prevAll().show();
	 	 $(this).nextAll().show();
	});
	
	
    function showModal1() {
         $('#test1').modal('show'); 
    }
    
    function showModal2() {
         $('#test2').modal('show'); 
   }
   
    function showModal3(){
    	$("#basicModal").modal('show');    	
    }


    $(".released_articles").click(function(){ //某篇文章的區塊被點到的話
    	$(this).removeData("bs.modal");
    	var the_art_no = $(this).attr("id"); //拿到該篇文章的號碼
		$.ajax({
			type : "POST",
			url : "http://localhost:8081/CEA103G1/article/article.do",
			data : {action: "getOne_From",art_no:the_art_no}, //參數傳遞 
			success : function(data) {
				$("#oneArticle").html(data);
			}
		});
		$("#basicModal").modal('show'); 
		
    });
    

</script>
</body>
</html>