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
  List<FollowVO> followVO_mine = (List<FollowVO>)request.getAttribute("followVO_mine");
  pageContext.setAttribute("fans_list", fans_list); 
  pageContext.setAttribute("follows_list", follows_list); 
  pageContext.setAttribute("followVO_mine", followVO_mine); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<link rel="stylesheet" type="text/css" href="/CEA103G1/profile.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
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
.fans_list{
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

    <header>
        <div class="container">
            <div class="profile">
                <div class="profile-image">
                    <img src="/CEA103G1/images/profile.png" width="150px" height="150px">
                </div>
                <div class="profile-user-settings">
                    <h1 class="profile-user-name"><%=mbr_no%></h1>
                    
                    <button class="btn profile-edit-btn">�s��ӤH�ɮ�</button>
                    <button class="btn profile-edit-btn" id=follow-btn onclick="add_follow()">�l�ܹ��</button>
                    <button class="btn profile-edit-btn" id=cancel-follow-btn onclick="cancel_follow()" style="display:none">�����l��</button>
                    <button class="btn profile-edit-btn">�o�e�����H</button>
                    <button class="btn profile-settings-btn" aria-label="profile settings"><i class="fas fa-cog" aria-hidden="true"></i></button>
                </div>
                <div class="profile-stats">
                        <div class="profile-stat-count article"><%=article_num%> �K��</div>
<%--                         <div class="profile-stat-count fans"><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${mbr_no}&action=getFollowers" ><span id=follower><%=fans_num%></span>�l�ܪ�</a></div> --%>
                        <div class="profile-stat-count fans" onclick="showModal1()"><span id=follower><%=fans_num%></span>�l�ܪ�</div>
                        <div class="profile-stat-count follows" onclick="showModal2()"><span id=following><%=follows_num%></span>�l�ܤ�</div>
 
                </div>
                <div class="profile-bio">
                    <p><span class="profile-real-name">lalalal</span>teststsestrseseseseeseewqewqeqw</p>
                </div>
            </div>
            <!-- End of profile section -->
        </div>
        <!-- End of container -->
    </header>

<div>�o�L���峹�M��</div>
<br>
<br>
<c:forEach var="articleVO" items="${articleVO}">
<%-- <div class=released_articles><a href="<%=request.getContextPath()%>/article/article.do?art_no=${articleVO.art_no}&action=getOne_From2">${articleVO.art_title}</a></div> --%>
<div class=released_articles id="${articleVO.art_no}" data-toggle="modal" data-target="#exampleModal" data-whatever="${articleVO.art_no}">${articleVO.art_title}<div class=released_articles_no style="display:none">${articleVO.art_no}</div></div>
	

</c:forEach>

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
					<%System.out.println("hello"); %>
				<li class=fans>
					<div class=fans_list style="width:47px;height:38px;"><img src="/CEA103G1/images/profile.png" width="30" height="30"></div>
					<div class=fans_list style="width:241px;height:36px;"><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${fans_list.flw_mbr_no}&action=getProfile" >${fans_list.flw_mbr_no}</a></div>
					<div class=fans_list style="width:48px;height:38px;"><button style="width:28;height:18;padding:9 5;border:1">�l��</button></div>
<!-- 					<div class=fans_list style="width:48px;height:38px;"><button style="width:28;height:18;padding:9 5;border:1">�l�ܤ�</button></div> -->
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
					<%System.out.println("hello"); %>
				<li class=fans>
					<div class=fans_list style="width:47px;height:38px;"><img src="/CEA103G1/images/profile.png" width="30" height="30"></div>
					<div class=fans_list style="width:241px;height:36px;"><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${follows_list.flwed_mbr_no}&action=getProfile">${follows_list.flwed_mbr_no}</a></div>
					<div class=fans_list style="width:48px;height:38px;"><button style="width:28;height:18;padding:9 5;border:1">�l��</button></div>
<!-- 					<div class=fans_list style="width:48px;height:38px;"><button style="width:28;height:18;padding:9 5;border:1">�l�ܤ�</button></div> -->
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
			data : {action: "add_follow",flw_mbr_no:"10001",flwed_mbr_no:<%=mbr_no%>}, //�Ѽƶǻ� 
			success : function(data) {
				alert("�s�W�Y�H��Y�H���l�ܦ��\");
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
 	 
 	 
			$.ajax({ //�t�d�Ǩ�followServlet �����Y�H��Y�H���l��  �ݭn���Ѽ�: �l�ܪ�mbr_no �Q�l�ܪ̪� mbr_no   �ثe�l�ܪ�mbr_no�g�� ����n�qsession get��ثe�O���ӷ|����o�ӤH�l��
			type : "POST",
			url : "http://localhost:8081/CEA103G1/follow/follow.do",
			data : {action: "cancel_follow",flw_mbr_no:"10001",flwed_mbr_no:<%=mbr_no%>}, //�Ѽƶǻ� 
			success : function(data) {
				alert("�����Y�H��Y�H���l�ܦ��\");
			}
		}); 
 	}
	
	
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