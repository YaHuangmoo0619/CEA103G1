<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.follow.model.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.member.model.*" %>
<%@ page import="redis.clients.jedis.Jedis"%>
<%
	Jedis jedis = new Jedis("localhost", 6379);
	jedis.auth("123456");

	
	jedis.select(6);
	
%>

<%int fans_num = (Integer)request.getAttribute("fans_num"); %>
<%int follows_num = (Integer)request.getAttribute("follows_num"); %>
<%int article_num = (Integer)request.getAttribute("article_num"); %>
<%String mbr_no = String.valueOf(request.getAttribute("mbr_no"));
  int mbr_no_author = Integer.valueOf(mbr_no);
  String author_profile = jedis.get("user:"+mbr_no+":profile");
%>
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
  pageContext.setAttribute("author_profile", author_profile);
  pageContext.setAttribute("mbr_no_self", mbr_no_self);
  pageContext.setAttribute("mbr_no_author", mbr_no_author);
  pageContext.setAttribute("fans_list", fans_list); 
  pageContext.setAttribute("follows_list", follows_list); 
  pageContext.setAttribute("followVO_mine", followVO_mine);
  pageContext.setAttribute("memberVO", memberVO);
%>
<jsp:useBean id="memberDAO" scope="page" class="com.member.model.MemberDAO" />
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">

<link rel="stylesheet" type="text/css" href="/CEA103G1/profile.css">
<%-- <%@ include file="/part-of/partOfCampion_frontTop_css.txt"%> --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<%@ include file="/article_css/article_css.txt"%>
<style>
.container{
width:1000px;
}
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

.transform_text{
cursor: pointer;
}

.transform_text:hover{
transform:scale(1.5);
}

.follow{
padding: 8px 16px;
}

.clear{
clear: both;
}

</style>

<style>


section {
	text-align: center;
}

.author{
	color:black;
}


.board{
	display:inline-block;
}

.board_name{
padding:0px 10px 0px 10px;
}
}
.subscribe{
padding:0px 0px 0px 10px;
}
.article_sort{
	display:inline-block;
	font-family: Microsoft JhengHei;
	font-size:20px;
	padding: 0px 10px 0px 0px;
}
.article_sort_parent{
	padding:10px 0px 20px 60px;
	
}
/* -----------------------------以下為側欄css------------------------------ */
#sidebar {
  position:absolute;
  top:115px;
  left:0px;
  width:208px;
  height:100%;
/*   background:#151719; */
  transition:all 300ms linear;
}

.link_to_board{
color:FFFDD0;
font-family: Microsoft JhengHei;
}
.sidebar_around:hover{
 background-color:	#019858;
}

#sidebar div.list div.item {
  padding:15px 10px;
  text-transform:uppercase;
  font-size:14px;
  font-family:微軟正黑體;
}

.board_name{
width:120px;
}

.board_icon{
padding:0px 0px 0px 10px;
}

/* -----------------------------以下為主欄css------------------------------ */
  div.main_content{
  	  top:60px;
  	  position:absolute;
	  left:200px;
	  right:150px;
	  padding:20px 20px 20px 20px;

	  
}

/* --------------------------------------------------------------------- */
.write{
  position: fixed;
  bottom: 100px;
  right: 75px;
}

.pic img{
width: 110px !important;
height: 90px !important;
}

.pic{
margin: 0px !important;
border: 0px !important;
}


#basicModal{

height: 500px;
 overflow-y: initial !important; 
 overflow-y: auto; 

}

/* #basicModal.modal-dialog{ */

/* overflow-y: initial !important */

/* } */

/* #basicModal.modal-body{ */

/* height: 500px; */

/* overflow-y: auto; */

/* } */
/* .modal{ */
/* 	color: black */
/* } */

.title_box{
padding: 0px 0px 10px 0px;
}

.btn-group-article_sort{
float:right;
margin:0px 50px 0px 0px;
}
.sort_text{
float:right;
font-size:14px;
color:black;
margin:10px 0px 0px 0px;
}

.drop-family{
display:inline-block;
}

a.dropdown-item{
padding:0px;
}

.dropdown-menu{
min-width:0;
width:78px;
}

#dropdownbtn{
min-width:75px;
min-hight:38px;
}
/* div.fixedTop{ */
/* background-color:; */
/* } */

.each_article{
padding:50px 0px 0px 0px;
width:1000px;
}

.modal-title{
margin:0px 0px 0px 20px;
}
</style>
<title>Profile</title>

</head>
<body>
<%-- 	<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%> --%>
    <header>
        <div class="container border">
            <div class="profile">
                <div class="profile-image">
<!--                     <img src="/CEA103G1/images/profile.png" width="150px" height="150px"> -->
                    <img class=author_sticker width="150px" height="150px" src="<%=request.getContextPath()%>/member/GetPhoto?mbr_no=<%=mbr_no%>">
                </div>
                <div class="profile-user-settings">
                <c:forEach var="memberVO_all" items="${memberDAO.all}">
                    <c:if test="${memberVO_all.mbr_no==mbr_no_author}"><h3 class="profile-user-name">${memberVO_all.acc}</h3></c:if>
                  </c:forEach>  
                    
                    
                    
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
					<button class="btn profile-edit-btn profile-edit-btn-self">編輯簡介</button>
					</c:if>

					

                    <button class="btn profile-edit-btn" onclick="location.href='/CEA103G1/front-end/member_mail/addMember_mail.jsp?mbr_no=<%=mbr_no%>'">發送站內信</button>
                    <button class="btn profile-settings-btn" aria-label="profile settings"><i class="fas fa-cog" aria-hidden="true"></i></button>
                </div>
                <div class="profile-stats">
                        <div class="profile-stat-count article"><%=article_num%> 貼文</div>

                        <div class="profile-stat-count " onclick="showModal1()"><span id=follower><%=fans_num%></span>追蹤者</div>
                        <div class="profile-stat-count follows" onclick="showModal2()"><span id=following><%=follows_num%></span>追蹤中</div>
 
                </div>
                <div class="profile-bio">
                    <p><span class="profile-real-name">${author_profile}</span></p>
                </div>
            </div>
            <!-- End of profile section -->
            
            
            
            
                        <div class="each_article">
                    <c:forEach var="articleVO" items="${articleVO}">
                    <div class=article  style="cursor:pointer;" onclick="location.href='<%=request.getContextPath()%>/article/article.do?art_no=${articleVO.art_no}&action=getOne_From2';">
                        <article class="content" role="article" data-post-list-viewed-cell-no="2">
                            <div class="top_in">
                                <div class="top_in1">
                                    <div class="genre_uni">
                                        <div class="genre_0">
                                            <div class="genre"><c:forEach var="bd_clVO" items="${bd_clDAO.all}">
			<c:if test="${articleVO.bd_cl_no==bd_clVO.bd_cl_no}">
	                    ${bd_clVO.bd_name}
                    </c:if>
		</c:forEach></div>					
<%--                                             <div class="date"><fmt:formatDate value="${articleVO.art_rel_time}" pattern="MM月dd日  HH:mm" /></div> --%>
                                            <div class="author"><c:forEach var="memberVO_loop" items="${memberDAO.all}">
			<c:if test="${articleVO.mbr_no==memberVO_loop.mbr_no}">&nbsp;&nbsp;${memberVO_loop.acc}</c:if>
		</c:forEach></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                           <div class="title title_box">${articleVO.art_title}</div>
                            <div class="post">
                                <div class="post_0">
                                <p>${simple_art_cont[articleVO.art_no]}</p>
                                </div>
                            </div>
                            <div class="bottom_in">
                                <div class="emoji">
                                    <div class="emoji_inner">
                                        <div class="emoji_pic">
                                            <img src="/CEA103G1/images/heart_already.svg" width="25px" height="25px"></div>
                                        <div class=" amount">${articleVO.likes}</div>
                                    </div>
                                </div>
                                <div class="response_box">
                                    <span class="response">回應</span><span>${articleVO.replies}</span>
                                </div>
                                <div class="archieve">
                                    <div class="archieve_0">
                              				<c:set var="collection_status" value="0"></c:set>
                              				<c:set var="my_collection_list_replace" value="${my_collection_list}"></c:set>
                 							<c:forEach var="my_collection_list_replace" items="${my_collection_list_replace}">
                 							<c:if test="${my_collection_list_replace.art_no==articleVO.art_no}">
                 							<c:set var="collection_status" value="1"></c:set>
                 							<img src="/CEA103G1/images/bookmark_already.svg" width="20px" height="20px">
                 							</c:if>
											</c:forEach>
											<c:if test="${collection_status==0}"><img src="/CEA103G1/images/bookmark_notyet.svg" width="20px" height="20px"></c:if>
											
                                        <span>收藏</span></div>
                                </div>
                            </div>
								<c:if test="${not empty articleVO.art_first_img}">
								<div class=pic>${articleVO.art_first_img}</div>
								</c:if>             
                        </article>
                        </div>
                        </c:forEach>
                    </div>
            
            
        </div>
        <!-- End of container -->
    </header>



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
                <h3 class="modal-title">粉絲</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button> 
            </div>
            <div class="modal-body">
				<c:forEach var="fans_list" items="${fans_list}"> 
<!-- 				對每個粉絲清單中的人跟我的追蹤清單的人做比對進行檢查 -->
				<li class=fans>
					<div class=fans_list style="width:47px;height:38px;"><img src="<%=request.getContextPath()%>/member/GetPhoto?mbr_no=${fans_list.flw_mbr_no}" width="50px" height="50px" style="border-radius: 50%;"></div>
					<div class=fans_list style="width:241px;height:36px;"><a style="font-size:16px;margin-left:15px;" href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${fans_list.flw_mbr_no}&action=getProfile&mbr_no_mine=${mbr_no_self}">
					<c:forEach var="memberVO_all" items="${memberDAO.all}">
					<c:if test="${fans_list.flw_mbr_no==memberVO_all.mbr_no}">${memberVO_all.acc}</c:if>
					</c:forEach>
					</a></div>
					
<!-- 					如果我追蹤的人裡面有這個人 -->
					<c:if test="${followVO_mine.contains(fans_list.flw_mbr_no)}">
					<div class="fans_list cancel_follow_in_list transform_text" style="width:48px;height:38px; ">追蹤中</div>
					<div class=just_for_hint style="display:none">${fans_list.flw_mbr_no}</div>
					<div class="fans_list add_follow_in_list transform_text" style="width:48px;height:38px;display:none;">追蹤</div>
					
					</c:if>

<!-- 					如果我追蹤的人裡面沒有這個人 -->					
					<c:if test="${followVO_mine.contains(fans_list.flw_mbr_no)==false}">
					<div class="fans_list cancel_follow_in_list transform_text" style="width:48px;height:38px;display:none">追蹤中</div>
					<div class="fans_list add_follow_in_list transform_text" style="width:48px;height:38px;">追蹤</div>
					<div class=just_for_hint style="display:none">${fans_list.flw_mbr_no}</div>
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
                <h3 class="modal-title">追蹤名單</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button> 
            </div>
            <div class="modal-body">
				<c:forEach var="follows_list" items="${follows_list}">
				<li class=follows>
					<div class="follows_list" style="width:47px;height:38px;"><img src="<%=request.getContextPath()%>/member/GetPhoto?mbr_no=${follows_list.flwed_mbr_no}" width="50px" height="50px" style="border-radius: 50%;"></div>
					<div class="follows_list" style="width:241px;height:36px;"><a style="font-size:16px;margin-left:15px;" href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${follows_list.flwed_mbr_no}&action=getProfile&mbr_no_mine=${mbr_no_self}">
					<c:forEach var="memberVO_all" items="${memberDAO.all}">
					<c:if test="${follows_list.flwed_mbr_no==memberVO_all.mbr_no}">${memberVO_all.acc}</c:if>
					</c:forEach>
					</a></div>

<!-- 					如果我追蹤的人裡面有這個人 -->
					<c:if test="${followVO_mine.contains(follows_list.flwed_mbr_no)}">
					<div class="follows_list cancel_follow_in_list transform_text" style="width:48px;height:38px; ">追蹤中</div>
					<div class=just_for_hint style="display:none">${follows_list.flwed_mbr_no}</div>
					<div class="follows_list add_follow_in_list transform_text" style="width:48px;height:38px;display:none;">追蹤</div>
					</c:if>
					
<!-- 					如果我追蹤的人裡面沒有這個人 -->					
					<c:if test="${followVO_mine.contains(follows_list.flwed_mbr_no)==false}">
					<div class="follows_list cancel_follow_in_list transform_text" style="width:48px;height:38px;display:none">追蹤中</div>
					<div class="follows_list add_follow_in_list transform_text" style="width:48px;height:38px;">追蹤</div>
					<div class=just_for_hint style="display:none">${follows_list.flwed_mbr_no}</div>
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

		
		
				<div class="modal fade" id="profile-edit-Modal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
				<form METHOD="post" ACTION="/CEA103G1/follow/follow.do" id="myform" name="form1" autocomplete>
					<div class="modal-body">
						  <div class="form-group">
    						<div style="font-size:30px">編輯簡介</div>
    						<textarea name="new_profile" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
  							</div>
					</div>
						<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">返回</button>
						<input type="hidden" name="mbr_no" value="<%=mbr_no_self%>">		
						<input type="hidden" name="action" value="modify_profile">	
						<input type="button" value="送出修改" class="btn btn-primary to_modify_ajax">
					</div>
					</form>
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
	 	 var want_follow_no = $(this).next().text(); //前一個元素的內容，即想追蹤的人的號碼 
	 	 console.log("want_follow_no"+want_follow_no);
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
	 	 $(this).prev(".just_for_hint").hide();
	 	 $(this).next(".just_for_hint").hide();
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
		 	 


 	}
	
	
	
	
	$(".cancel_follow_in_list").click(function(){
	 	 var want_cancel_follow_no = $(this).next().text(); //前一個元素的內容，即想取消追蹤的人的號碼 
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
	 	 $(this).prev(".just_for_hint").hide();
	 	 $(this).next(".just_for_hint").hide();
	});
	
	
	$(".to_modify_ajax").click(function(){
	 	 var update_profile = $("#exampleFormControlTextarea1").val();
	 	 console.log(update_profile);
			$.ajax({
				type : "POST",
				url : "http://localhost:8081/CEA103G1/follow/follow.do",
				data : {action: "modify_profile",mbr_no:<%=mbr_no_self%>,new_profile:update_profile}, //參數傳遞 
				success : function(data) {
					alert("更新簡介成功");
				}
			});
			window.location.reload();
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
    
	
    $(".profile-edit-btn-self").click(function(){
    	$("#profile-edit-Modal").modal('show');
    })
</script>
</body>
</html>