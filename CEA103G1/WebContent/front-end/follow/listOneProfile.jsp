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
<%String mbr_no = String.valueOf(request.getAttribute("mbr_no"));
  int mbr_no_author = Integer.valueOf(mbr_no);
%>
<%MemberVO memberVO = (MemberVO)session.getAttribute("memberVO"); %>
<%int mbr_no_self=memberVO.getMbr_no(); %>



<%
  ArticleService articleSvc = new ArticleService();
  List<ArticleVO> articleVO = (List<ArticleVO>)request.getAttribute("articleVO"); //�o��L���峹
  FollowService followSvc = new FollowService();
  List<FollowVO> fans_list = (List<FollowVO>)request.getAttribute("followVO_fans"); //�o�ӤH������
  List<FollowVO> follows_list = (List<FollowVO>)request.getAttribute("followVO_follows"); //�o�ӤH�l�ܪ��H
  List<Integer> followVO_mine = (List<Integer>)request.getAttribute("followVO_mine"); //�ڰl�ܪ��H
  ArrayList<Integer> follow_mine = new ArrayList<Integer>(); //�M���Ψө�ڰl�ܪ��H�����X
  
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
<%@ include file="/part-of/partOfCampion_frontTop_css.txt"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<%@ include file="/article_css/article_css.txt"%>
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
/* -----------------------------�H�U������css------------------------------ */
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
  font-family:�L�n������;
}

.board_name{
width:120px;
}

.board_icon{
padding:0px 0px 0px 10px;
}

/* -----------------------------�H�U���D��css------------------------------ */
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
</style>
<title>Profile</title>

</head>
<body>
	<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
    <header>
        <div class="container">
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

                    <!--   �p�G�b�ڪ��l�ܦW�椤���o�ӤH���ܡA�h���button���l�ܤ� �ñNstatus�אּ1-->
                    <button class="btn profile-edit-btn" id=cancel-follow-btn onclick="cancel_follow()">�l�ܤ�</button>
                    <button class="btn profile-edit-btn" id=follow-btn onclick="add_follow()" style="display:none">�l�ܹ��</button>
                    <%status=true;%>
                    </c:if>
		            </c:forEach>   
		            
		                             

					<!-- �p�G�b�ڪ��l�ܦW�椤�S���o�ӤH�A�ӥB�o�ӤH������u�ڡv �h���button���u�l�ܹ��v -->
					<c:if test="<%=status==false&&Integer.valueOf(mbr_no)!=mbr_no_self%>">
					<button class="btn profile-edit-btn" id=follow-btn onclick="add_follow()">�l�ܹ��</button>
					<button class="btn profile-edit-btn" id=cancel-follow-btn onclick="cancel_follow()" style="display:none">�l�ܤ�</button>
					<%status=true;%>
					</c:if>

					
<!-- 					�p�G�o�ӤH�N�O�ڡA���N�X�{�u�s��ӤH�ɮסv -->
					<c:if test="<%=Integer.valueOf(mbr_no)==mbr_no_self%>"> 
					<button class="btn profile-edit-btn">�s��ӤH�ɮ�</button>
					</c:if>

					

                    <button class="btn profile-edit-btn" onclick="location.href='/CEA103G1/front-end/member_mail/addMember_mail.jsp?mbr_no=<%=mbr_no%>'">�o�e�����H</button>
                    <button class="btn profile-settings-btn" aria-label="profile settings"><i class="fas fa-cog" aria-hidden="true"></i></button>
                </div>
                <div class="profile-stats">
                        <div class="profile-stat-count article"><%=article_num%> �K��</div>

                        <div class="profile-stat-count fans" onclick="showModal1()"><span id=follower><%=fans_num%></span>�l�ܪ�</div>
                        <div class="profile-stat-count follows" onclick="showModal2()"><span id=following><%=follows_num%></span>�l�ܤ�</div>
 
                </div>
                <div class="profile-bio">
                    <p><span class="profile-real-name">lalalal</span>teststsestrseseseseeseewqewqeqw</p>
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
<%--                                             <div class="date"><fmt:formatDate value="${articleVO.art_rel_time}" pattern="MM��dd��  HH:mm" /></div> --%>
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
                                    <span class="response">�^��</span><span>${articleVO.replies}</span>
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
											
                                        <span>����</span></div>
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







<!-- �����W�� -->


<!-- <br> -->
<!-- <br> -->
<!-- <br> -->

<!-- �l�ܦW�� -->
<%-- <c:forEach var="follows_list" items="${follows_list}"> --%>
<%-- <div class=follow><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${follows_list.flwed_mbr_no}&action=getProfile">${follows_list.flwed_mbr_no}</a></div> --%>
<%-- </c:forEach> --%>

<div>
<div class="modal" tabindex="-1" role="dialog" id="test1">
     <div class="modal-dialog" role="document"> 
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">����</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button> 
            </div>
            <div class="modal-body">
				<c:forEach var="fans_list" items="${fans_list}"> 
<!-- 				��C�ӯ����M�椤���H��ڪ��l�ܲM�檺�H�����i���ˬd -->
				<li class=fans>
					<div class=fans_list style="width:47px;height:38px;"><img src="/CEA103G1/images/profile.png" width="30" height="30"></div>
					<div class=fans_list style="width:241px;height:36px;"><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${fans_list.flw_mbr_no}&action=getProfile" >${fans_list.flw_mbr_no}</a></div>
					
<!-- 					�p�G�ڰl�ܪ��H�̭����o�ӤH -->
					<c:if test="${followVO_mine.contains(fans_list.flw_mbr_no)}">
					<div class="fans_list cancel_follow_in_list" style="width:48px;height:38px; ">�l�ܤ�</div>
					<div class="fans_list add_follow_in_list" style="width:48px;height:38px;display:none;">�l��</div>
					</c:if>

<!-- 					�p�G�ڰl�ܪ��H�̭��S���o�ӤH -->					
					<c:if test="${followVO_mine.contains(fans_list.flw_mbr_no)==false}">
					<div class="fans_list cancel_follow_in_list" style="width:48px;height:38px;display:none">�l�ܤ�</div>
					<div class="fans_list add_follow_in_list" style="width:48px;height:38px;">�l��</div>
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
                <h5 class="modal-title">�l�ܦW��</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button> 
            </div>
            <div class="modal-body">
				<c:forEach var="follows_list" items="${follows_list}">
				<li class=follows>
					<div class=follows_list style="width:47px;height:38px;"><img src="/CEA103G1/images/profile.png" width="30" height="30"></div>
					<div class=follows_list style="width:241px;height:36px;"><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${follows_list.flwed_mbr_no}&action=getProfile">${follows_list.flwed_mbr_no}</a></div>

<!-- 					�p�G�ڰl�ܪ��H�̭����o�ӤH -->
					<c:if test="${followVO_mine.contains(follows_list.flwed_mbr_no)}">
					<div class="follows_list cancel_follow_in_list" style="width:48px;height:38px; ">�l�ܤ�</div>
					<div class="follows_list add_follow_in_list" style="width:48px;height:38px;display:none;">�l��</div>
					</c:if>
					
<!-- 					�p�G�ڰl�ܪ��H�̭��S���o�ӤH -->					
					<c:if test="${followVO_mine.contains(follows_list.flwed_mbr_no)==false}">
					<div class="follows_list cancel_follow_in_list" style="width:48px;height:38px;display:none">�l�ܤ�</div>
					<div class="follows_list add_follow_in_list" style="width:48px;height:38px;">�l��</div>
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
						<!-- =========================================�H�U����listOneArticle.jsp�����e========================================== -->
						<div id=oneArticle></div>
						<!-- =========================================�H�W����listOneArticle.jsp�����e========================================== -->
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
 	 
 	 
			$.ajax({ //�t�d�Ǩ�followServlet �s�W�Y�H��Y�H���l��  �ݭn���Ѽ�: �l�ܪ�mbr_no �Q�l�ܪ̪� mbr_no   �ثe�l�ܪ�mbr_no�g�� ����n�qsession get��ثe�O���ӷ|����o�ӤH�l��
			type : "POST",
			url : "http://localhost:8081/CEA103G1/follow/follow.do",
			data : {action: "add_follow",flw_mbr_no:<%=memberVO.getMbr_no()%>,flwed_mbr_no:<%=mbr_no%>}, //�Ѽƶǻ� 
			success : function(data) {
				alert("�s�W"+<%=memberVO.getMbr_no()%>+"��"+<%=mbr_no%>+"���l�ܦ��\");
				window.location.reload()  //���s��z����
			}
		}); 
		 	 $(this).hide();
		 	 $(this).prevAll().show();
		 	 $(this).nextAll().show();
 	}
	
	
	
	$(".add_follow_in_list").click(function(){
	 	 var want_follow_no = $(this).prev().prev().text(); //�e�@�Ӥ��������e�A�Y�Q�l�ܪ��H�����X 
			$.ajax({ //�t�d�Ǩ�followServlet �s�W�Y�H��Y�H���l��  �ݭn���Ѽ�: �l�ܪ�mbr_no �Q�l�ܪ̪� mbr_no 
				type : "POST",
				url : "http://localhost:8081/CEA103G1/follow/follow.do",
				data : {action: "add_follow",flw_mbr_no:<%=memberVO.getMbr_no()%>,flwed_mbr_no:want_follow_no}, //�Ѽƶǻ� 
				success : function(data) {
					alert("�s�W"+<%=memberVO.getMbr_no()%>+"��"+want_follow_no+"���l�ܦ��\");
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
 	 
 	 
			$.ajax({ //�t�d�Ǩ�followServlet �����Y�H��Y�H���l��  �ݭn���Ѽ�: �l�ܪ�mbr_no �Q�l�ܪ̪� mbr_no 
			type : "POST",
			url : "http://localhost:8081/CEA103G1/follow/follow.do",
			data : {action: "cancel_follow",flw_mbr_no:<%=memberVO.getMbr_no()%>,flwed_mbr_no:<%=mbr_no%>}, //�Ѽƶǻ� 
			success : function(data) {
				alert("����"+<%=memberVO.getMbr_no()%>+"��"+<%=mbr_no%>+"���l�ܦ��\");
				window.location.reload()  //���s��z����
			}
		}); 
			
		 	 $(this).hide();
		 	 $(this).prevAll().show();
		 	 $(this).nextAll().show();
		 	 
// 		 	�ݸѨM: �٭najax��s�o�ӤH�������W��W��A���M�|�y�������l��/�����l�ܡA���I�i�h�٨S����s

 	}
	
	
	
	
	$(".cancel_follow_in_list").click(function(){
	 	 var want_cancel_follow_no = $(this).prev().text(); //�e�@�Ӥ��������e�A�Y�Q�����l�ܪ��H�����X 
			$.ajax({ //�t�d�Ǩ�followServlet �����Y�H��Y�H���l��  �ݭn���Ѽ�: �l�ܪ�mbr_no �Q�l�ܪ̪� mbr_no
				type : "POST",
				url : "http://localhost:8081/CEA103G1/follow/follow.do",
				data : {action: "cancel_follow",flw_mbr_no:<%=memberVO.getMbr_no()%>,flwed_mbr_no:want_cancel_follow_no}, //�Ѽƶǻ� 
				success : function(data) {
					alert("����"+<%=memberVO.getMbr_no()%>+"��"+want_cancel_follow_no+"���l�ܦ��\");
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


    $(".released_articles").click(function(){ //�Y�g�峹���϶��Q�I�쪺��
    	$(this).removeData("bs.modal");
    	var the_art_no = $(this).attr("id"); //����ӽg�峹�����X
		$.ajax({
			type : "POST",
			url : "http://localhost:8081/CEA103G1/article/article.do",
			data : {action: "getOne_From",art_no:the_art_no}, //�Ѽƶǻ� 
			success : function(data) {
				$("#oneArticle").html(data);
			}
		});
		$("#basicModal").modal('show'); 
		
    });
    

</script>
</body>
</html>