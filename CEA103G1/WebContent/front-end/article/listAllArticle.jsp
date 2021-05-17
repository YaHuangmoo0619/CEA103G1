<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.board_class.model.*"%>
<%@ page import="com.member.model.*" %>
<%@ page import="redis.clients.jedis.Jedis"%>
<%
	Jedis jedis = new Jedis("localhost", 6379);
	jedis.auth("123456");
	jedis.select(6);
%>


<%
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list = articleSvc.getAll_Front();
// 	Map<Integer,String> article_first_img_map = new HashMap<Integer,String>(); //³o¬O¤@­Ó¦s¦³¡u¦³­º¹Ï¡vªºMap¡Akey¬°¸Ó¤å³¹¸¹½X¡Avalue¬°base64½s½X
// 	int start_index;
// 	int end_index;
// 	String first_img_base64;
// 	for(ArticleVO x : list){
// 		start_index = x.getArt_cont().indexOf("<p><img");
// 		if(start_index>=0){ //¦³­º¹Ïªº¸Ü
// 		System.out.println("start_index:"+start_index);
// 		end_index   = x.getArt_cont().indexOf("</p>", start_index)+4; //±q²Ä¤@±i¹Ï¤ù<p><imgªº¦ì¸m¥H«á¶}©l·j´M¨ìªº²Ä¤@­Ó </p>¡A§Y¬°²Ä¤@±i¹Ïªºµ²§ô
// 			first_img_base64 = x.getArt_cont().substring(start_index, end_index); //Â^¨ú¨ì²Ä¤@±i¹Ï¤ùªºbase64½s½X
// 			System.out.println("i'm here");
// 			System.out.println(first_img_base64);
// 			article_first_img_map.put(x.getArt_no(), first_img_base64); //©ñ¤JMap¤¤	
// 		}
// 	}
	Map<Integer,String> simple_art_cont = new HashMap<>();
	for(ArticleVO count : list){
		if(jedis.exists("post:"+count.getArt_no()+":art_simple_cont")){
		simple_art_cont.put(count.getArt_no(),jedis.get("post:"+count.getArt_no()+":art_simple_cont"));
		System.out.println(jedis.get("post:"+count.getArt_no()+":art_simple_cont"));
		};
	};
	
	System.out.println(simple_art_cont.get("12"));
	double max_page = Math.ceil(list.size()/5);
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("simple_art_cont", simple_art_cont);
// 	pageContext.setAttribute("article_first_img_map", article_first_img_map);
%>

<%
	Board_ClassService board_classSvc = new Board_ClassService();
	List<Board_ClassVO> bd_list = board_classSvc.getAll();
	pageContext.setAttribute("bd_list", bd_list);
%>

<% 
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	int ajax_mbr_no = 0;
	//¦pªGmemberVO¤£¬°ªÅ¡A¥Nªí¥L¦³µn¤J¡A±µµÛ¬d¸ß¥L¬O§_¹ï³o½g¤å³¹«ö¹LÆg
	if(memberVO!=null){
		ajax_mbr_no = memberVO.getMbr_no();
	}
	if(memberVO==null){
		ajax_mbr_no=0;
	}
	pageContext.setAttribute("ajax_mbr_no", ajax_mbr_no);
%>


<jsp:useBean id="bd_clDAO" scope="page"
	class="com.board_class.model.Board_ClassDAO" />
<jsp:useBean id="memberDAO" scope="page"
	class="com.member.model.MemberDAO" />

<html>
<head>
<meta charset="Big5">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>¦C¥X©Ò¦³¤å³¹</title>
<%@ include file="/article_css/article_css.txt"%>
<%@ include file="/part-of/partOfCampion_frontTop_css.txt"%>
<link rel="icon" href="campionLogoIcon.png" type="image/png">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

<style>
html, body {
	margin: 0;
	padding: 0;
	/*background-color: #4e5452;*/
	background-color: #4e5452;
	color: #80c344;
}

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
}
.article_sort_parent{
	padding:0px 0px 0px 60px;
}
/* -----------------------------¥H¤U¬°°¼Äæcss------------------------------ */
#sidebar {
  position:absolute;
  top:100px;
  left:0px;
  width:208px;
  height:100%;
/*   background:#151719; */
  transition:all 300ms linear;
}


#sidebar div.list div.item {
  padding:15px 10px;
  text-transform:uppercase;
  font-size:14px;
  font-family:·L³n¥¿¶ÂÅé;
}

/* -----------------------------¥H¤U¬°¥DÄæcss------------------------------ */
  div.main_content{
  	  top:60px;
  	  position:absolute;
	  left:150px;
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
width: 90px !important;
height: 90px !important;
}

.pic{
margin: 0px !important;
border: 0px !important;
}

#basicModal{

overflow-y: initial !important

}

#basicModal{

height: 500px;

overflow-y: auto;

}

</style>

</head>
<body>
	<div>¥Ø«eµn¤Jªº¤H¬O: ${memberVO.mbr_no}</div>
	<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
	
<!-- 	¦pªG¦³µn¤Jªº¸Ü -->
	<c:if test="${not empty memberVO }"> 
	<a class=write title="µo¤å" href="<%=request.getContextPath()%>/front-end/article/addArticle.jsp"><img src="/CEA103G1/images/write.svg" width="24px" height="24px"></a>
	</c:if>
<!-- 	¦pªG¨S¦³µn¤Jªº¸Ü  ­n¥´¶}¦W¬°µn¤Jªº¿O½c-->	
	<c:if test="${empty memberVO }"> 
	<div class="no_login write to_login"><img src="/CEA103G1/images/write.svg" width="24px" height="24px"></div>
	</c:if>



<div id="sidebar">
  <div class="list">
			<c:forEach var="board_classVO" items="${bd_list}">
				<div class="item board board_name" ><a href="<%=request.getContextPath()%>/article/article.do?bd_cl_no=${board_classVO.bd_cl_no}&action=getOneArticle_ByBoard_Clss_For_Display"  style="color:white;">${board_classVO.bd_name}</a></div>
				<div style="display:none">${board_classVO.bd_cl_no}</div>
				<!-- 	¦pªG¦³µn¤Jªº¸Ü -->
				<c:if test="${not empty memberVO }"> 
				<div class="board subscribe"><img src="/CEA103G1/images/star-outline.svg" width="24px" height="24px"></div>
				</c:if>
					<c:if test="${empty memberVO }"> 
				<div class="board to_login"><img src="/CEA103G1/images/star-outline.svg" width="24px" height="24px"></div>
				</c:if>
				
				<br>
			</c:forEach>
  </div>
</div>


<div class=main_content id=main_content>
<div class="scroll"> 
	<%@ include file="pageforhome.file"%>


        <div class="container">
<<<<<<< HEAD
 
        		<div class=article_sort_parent>
        			<div class=article_sort onclick="location.href='<%=request.getContextPath()%>/front-end/article/listAllArticle.jsp';">³Ì·s</div>
        			<div class=article_sort onclick="location.href='<%=request.getContextPath()%>/front-end/article/listAllArticleByLikes.jsp';">¼öªù</div>
					<!-- 	¦pªG¦³µn¤Jªº¸Ü -->
					<c:if test="${not empty memberVO }">
					<div class=article_sort onclick="location.href='<%=request.getContextPath()%>/front-end/article/addArticle.jsp';">°lÂÜ</div> 
					</c:if>
					<!-- 	¦pªG¨S¦³µn¤Jªº¸Ü  ­n¥´¶}¦W¬°µn¤Jªº¿O½c-->	
					<c:if test="${empty memberVO }"> 
					<div class="article_sort to_login">°lÂÜ</div>
					</c:if>
					
        		</div>
     
     
     
=======
        
        <!-- ¶®°Ä¥[ªº¡A¬°¤F¹Á¸Õ±Ò°Ê³qª¾ªº±À¼½ -->
        --${articleVO != null? articleVO.mbr_no:'123' }--
			<c:if test="${articleVO != null}">
			<!-- insert¦^¶ÇªºVO¨S¦³¤å³¹½s¸¹ -->
					<div onclick="sendNotify()" id="sendNotify">${articleVO.mbr_no}/article</div>
			</c:if>
			<c:if test="${article_ReplyVO != null}">
			<!-- insert¦^¶ÇªºVO¨S¦³¯d¨¥½s¸¹ -->
					<div onclick="sendNotify()" id="sendNotify">${article_ReplyVO.art_no}/reply</div>
			</c:if>
		<!-- ¶®°Ä¥[ªº¡A¬°¤F¹Á¸Õ±Ò°Ê³qª¾ªº±À¼½ -->
>>>>>>> parent of 09af1b2 (æ­£åœ¨è™•ç†è¨‚ç‡Ÿä½çš„ç³»çµ±é€šçŸ¥ï¼Œæ™‚é–“åˆ°äº†å…ˆpush)
            <div class="body">
                    <c:forEach var="articleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                    <div class=article>
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
<%--                                             <div class="date"><fmt:formatDate value="${articleVO.art_rel_time}" pattern="MM¤ëdd¤é  HH:mm" /></div> --%>
                                            <div class="author"><c:forEach var="memberVO_loop" items="${memberDAO.all}">
			<c:if test="${articleVO.mbr_no==memberVO_loop.mbr_no}">
	                    ${memberVO_loop.acc}
                    </c:if>
		</c:forEach></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <h2 class="title_box">
                                <a class="title" href="<%=request.getContextPath()%>/article/article.do?art_no=${articleVO.art_no}&action=getOne_From2">${articleVO.art_title}</a></h2>
                            <div class="post">
                                <div class="post_0">
                                <p>${simple_art_cont[articleVO.art_no]}</p>
                                </div>
                            </div>
                            <div class="bottom_in">
                                <div class="emoji">
                                    <div class="emoji_inner">
                                        <div class="emoji_pic">
                                            <img src="https://megapx-assets.dcard.tw/images/52057289-337a-4f2f-88c0-cb8a77ee422a/orig.png" title="·R¤ß" style="z-index:3" class=" icon_size icon_pic"></div>
                                        <div class=" amount">${articleVO.likes}</div>
                                    </div>
                                </div>
                                <div class="response_box">
                                    <span class="response">¦^À³</span><span>${articleVO.replies}</span>
                                </div>
                                <div class="archieve">
                                    <div class="archieve_0">
                                        <svg viewBox="0 0 24 24" focusable="false" role="img" aria-hidden="true" class="icon_size archieve_pic">
                                            <path d="M17.65 21.39L12 17.5l-5.65 3.88A1.5 1.5 0 014 20.15V5a2.5 2.5 0 012.5-2.5h11A2.5 2.5 0 0120 5v15.15a1.5 1.5 0 01-2.35 1.24z"></path>
                                        </svg>
                                        <span>¦¬ÂÃ</span></div>
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
    </div>


	<c:if test="${openModal!=null}">

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content" >
					<div class="modal-body">
						<!-- =========================================¥H¤U¬°­ìlistOneArticle.jspªº¤º®e========================================== -->
						<jsp:include page="listOneArticle.jsp" />
						
						<!-- =========================================¥H¤W¬°­ìlistOneArticle.jspªº¤º®e========================================== -->
					</div>
				</div>
			</div>
		</div>

	</c:if>



		
		<div class="modal fade" id="login_confirm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5>±z©|¥¼µn¤J</h5>
      </div>
      <div class="modal-body">
        <div>·Q­n¤@°_¥[¤J°Q½×¡A­n¥ıµn¤J Campion ­ò¡I</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">¨ú®ø</button>
        <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/front-end/member/login.jsp'">µn¤J</button>
      </div>
    </div>
  </div>
</div>



  	<!-- ±²¶bª¬ºA -->
  	<div class="scroller-status">
  		<div class="infinite-scroll-request loader-ellips"></div>
  	</div>

</div>


<%
jedis.close();
%>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	  <!-- Infinite Scroll v3.0.3 -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-infinitescroll/3.0.3/infinite-scroll.pkgd.min.js"></script>
		
		

	<script>
		let countMenu = 0;
		function showMenu() {
			countMenu++;
			if (countMenu % 2 == 1) {
				let secArray = document.getElementsByClassName("sec");
				for (let i = 0; i < secArray.length; i++) {
					secArray[i].style.display = "flex";
				}
			} else {
				let secArray = document.getElementsByClassName("sec");
				for (let i = 0; i < secArray.length; i++) {
					secArray[i].style.display = "none";
				}
			}
		}

		let countSearch = 0;
		function showSearch() {
			countSearch++;
			if (countSearch % 2 == 1) {
				let formArray = document.getElementsByClassName("secSearch");
				for (let i = 0; i < formArray.length; i++) {
					formArray[i].style.display = "flex";
				}
			} else {
				let formArray = document.getElementsByClassName("secSearch");
				for (let i = 0; i < formArray.length; i++) {
					formArray[i].style.display = "none";
				}
			}
		}

	$(".subscribe").click(function(){
		var subscribe_bd_cl_no = $(this).prev().text();
		console.log(subscribe_bd_cl_no);
		$.ajax({ //²Ä¤@­Óajax ­t³d¶Ç¨ìboard_classServlet ·s¼W¬Y¤H¹ï¬Y¬İªOªº­q¾\  »İ­nªº°Ñ¼Æ: mbr_no bd_cl_no 
			type : "POST",
			url : "http://localhost:8081/CEA103G1/board_class/board_class.do",
			data : {action: "subscribe",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,bd_cl_no:subscribe_bd_cl_no},
			success : function(data) {
				alert("·s¼W"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"¹ï¬İªO"+subscribe_bd_cl_no+"ªº­q¾\¦¨¥\");
			}
		});
 	})


		$("#basicModal").modal({
			show : true
		});
		
		
		$('.article').click(function(){
			
		})

  	var infScroll = new InfiniteScroll( ".scroll", {
  		path: function() {
  			// ­¶­±¸ô®|
  			if ( this.loadCount < <%=max_page%> ) {
  				// ¥uÅª¨ú¨ì³Ì«á¤@­¶ªº¸ê®Æ
  				var nextIndex = this.loadCount + 2; // 2
  				return "http://localhost:8081/CEA103G1/front-end/article/listAllArticle.jsp?whichPage="+nextIndex;
  			}
  		},
  		append: ".container", // ¶×¤Jª«¥óÃş§O
  		status: ".scroller-status" // ±²¶bª¬ºAÃş§O
  	})

  $(".to_login").click(function(){
	  $('#login_confirm').modal('show');
  })
  </script>
  
</body>
</html>