<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.board_class.model.*"%>
<%@ page import="com.member.model.*" %>
<%@ page import="redis.clients.jedis.Jedis"%>
<%@ page import="com.article_collection.model.*"%>
<%
	Jedis jedis = new Jedis("localhost", 6379);
	jedis.auth("123456");
	jedis.select(6);
%>

<%int bd_cl_no = Integer.parseInt(request.getParameter("bd_cl_no")); %>
<%
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list = articleSvc.getByBoard_Class_Front_By_Likes(bd_cl_no);
	pageContext.setAttribute("bd_cl_no", bd_cl_no);
	pageContext.setAttribute("list", list);
%>
<%

	Map<Integer,String> simple_art_cont = new HashMap<>();
	for(ArticleVO count : list){
		if(jedis.exists("post:"+count.getArt_no()+":art_simple_cont")){
		simple_art_cont.put(count.getArt_no(),jedis.get("post:"+count.getArt_no()+":art_simple_cont"));
		System.out.println(jedis.get("post:"+count.getArt_no()+":art_simple_cont"));
		};
	};
	
	
	System.out.println(simple_art_cont.get("12"));
	double max_page = Math.ceil(list.size()/5);
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
	Set<Integer> my_subscribe_board = new HashSet<>();
	int ajax_mbr_no = 0;
	long banned=0;
	String banned_chinese="";
	if(memberVO!=null){
		jedis.select(5);
		//查詢是否有被ban的紀錄
		if(jedis.exists("article_report:"+memberVO.getMbr_no()+":banned")){
			//如果有，還有多久小時 
			banned = Long.valueOf(jedis.ttl("article_report:"+memberVO.getMbr_no()+":banned"));
			if(banned>86400){//還超過一天的話  用天數報
				banned = banned/86400;
				banned_chinese = banned+"天";
			}else if(banned<86400 && banned >= 3600){
				banned = banned/3600;
				banned_chinese = banned+"小時";
			}else if(banned<3600){
				banned = banned/60;
				banned_chinese = banned+"分鐘";
			}
		}
		System.out.println("banned"+banned);
		ajax_mbr_no = memberVO.getMbr_no();
		jedis.select(6);
		for(String element : jedis.smembers("board:"+ajax_mbr_no+":subscribe")){ //取得我訂閱的看板list
			if(element!=null && !element.equals("")){
				my_subscribe_board.add(Integer.valueOf(element).intValue());	
			}
			
		}
		
		
		
		Article_CollectionService article_collectionSvc = new Article_CollectionService();
		//取得我收藏的文章的
		List<Article_CollectionVO> my_collection_list	= article_collectionSvc.findbymbr_no(memberVO.getMbr_no());
		

		
		pageContext.setAttribute("my_collection_list", my_collection_list);
	}
	if(memberVO==null){
		ajax_mbr_no=0;
	}
	
	
	

	pageContext.setAttribute("banned", banned);
	pageContext.setAttribute("banned_chinese", banned_chinese);
	pageContext.setAttribute("ajax_mbr_no", ajax_mbr_no);
	pageContext.setAttribute("my_subscribe_board", my_subscribe_board);
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

<title>listOneBoard_ClassArticleByLikes</title>
<%@ include file="/article_css/article_css.txt"%>
<%@ include file="/part-of/partOfCampion_frontTop_css.txt"%>
<link rel="icon" href="campionLogoIcon.png" type="image/png">
<link rel="stylesheet"	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
	    <link rel="stylesheet" href="sample.css" />
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>    

<style>
html, body {
	margin: 0;
	padding: 0;
	/*background-color: #4e5452;*/
	background-color: 		#8FBC8F;
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
	font-family: Microsoft JhengHei;
	font-size:20px;
	padding: 0px 10px 0px 0px;
}
.article_sort_parent{
	padding:10px 0px 0px 60px;
}

.bd_title{
	padding:0px 0px 0px 55px;
	color: black;
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
width: 90px !important;
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


.modal{
	color: black
}
</style>

</head>
<body onload="connection()">
	<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
	
<!-- 	如果有登入的話且沒被ban的話 -->
	<c:if test="${not empty memberVO && banned==0}"> 
	<a class=write title="發文" href="<%=request.getContextPath()%>/front-end/article/addArticle.jsp"><img src="/CEA103G1/images/write.svg" width="24px" height="24px"></a>
	</c:if>
<!-- 	如果有登入的話但被ban的話 -->
	<c:if test="${not empty memberVO && banned>0}">
	<div class="write banned"><img src="/CEA103G1/images/write.svg" width="24px" height="24px"></div>
	</c:if>
<!-- 	如果沒有登入的話  要打開名為登入的燈箱-->	
	<c:if test="${empty memberVO }"> 
	<div class="no_login write to_login"><img src="/CEA103G1/images/write.svg" width="24px" height="24px"></div>
	</c:if>




<div id="sidebar">
  <div class="list">
			<c:forEach var="board_classVO" items="${bd_list}">
				<div class="item board board_name" ><a href="<%=request.getContextPath()%>/front-end/article/listOneBoard_ClassArticle.jsp?bd_cl_no=${board_classVO.bd_cl_no}"  style="color:white;">${board_classVO.bd_name}</a></div>
				<div class=this_bd_bl_no style="display:none">${board_classVO.bd_cl_no}</div>
				
				
				<!-- 	如果有登入的話 -->
				<c:if test="${not empty memberVO }">
				
				
				<!--    設一個變數subscribe_status表示我對這個看板的訂閱狀況 預設為0 (未訂閱) -->
				
				<c:set var="subscribe_status" value="0"></c:set> 
				
				<!--   遍歷我訂閱看板的list 看list中有沒有號碼等於這個看板的bd_cl_no -->
				<c:set var = "testtest" value="${my_subscribe_board}"></c:set>
				<c:forEach var="testtest" items="${testtest}">
				<c:if test="${testtest==board_classVO.bd_cl_no}">
				
				
				<!-- 如果有 那就放實心星星  並設一個參數按下可取消訂閱 -->
				<div class="board cancel_subscribe"><img src="/CEA103G1/images/star_new.svg" width="24px" height="24px"></div>
				<c:set var="subscribe_status" value="1"></c:set>
				</c:if>
				</c:forEach>

				
				<!--    有登入但我我沒有訂閱這個看板 那就放空心星星，可訂閱 -->
				<c:if test="${subscribe_status==0}">
				<div class="board subscribe"><img src="/CEA103G1/images/star-outline_new.svg" width="24px" height="24px"></div>
				</c:if>
				
				</c:if> 

				<!--    沒登入放空心星星，按下跳出登入確認的燈箱   -->
					<c:if test="${empty memberVO }"> 
				<div class="board to_login"><img src="/CEA103G1/images/star-outline_new.svg" width="24px" height="24px"></div>
				</c:if>
				
				<br>
			</c:forEach>
  </div>
</div>


<div class=main_content id=main_content>
	<%@ include file="pageforhome.file"%>


        <div class="container">
 				
	<c:forEach var="bd_clVO" items="${bd_clDAO.all}">
			<c:if test="${bd_cl_no==bd_clVO.bd_cl_no}">
	                   <h2 class=bd_title>${bd_clVO.bd_name}</h2>
                    </c:if>
		</c:forEach>
        		<div class=article_sort_parent>
<%--         			<div class=article_sort onclick="location.href='<%=request.getContextPath()%>/front-end/article/listAllArticle.jsp';">最新</div> --%>
        			<a class=article_sort href="<%=request.getContextPath()%>/front-end/article/listOneBoard_ClassArticle.jsp?bd_cl_no=${bd_cl_no}">最新</a>
<%--         			<div class=article_sort onclick="location.href='<%=request.getContextPath()%>/front-end/article/listAllArticleByLikes.jsp';">熱門</div> --%>
        			<a class=article_sort href="<%=request.getContextPath()%>/front-end/article/listOneBoard_ClassArticleByLikes.jsp?bd_cl_no=${bd_cl_no}">熱門</a>					
        		</div>
     
     
     

        
        <!-- 雅凰加的，為了嘗試啟動通知的推播 -->
<%--         --${articleVO != null? articleVO.mbr_no:'123' }-- --%>
			<c:if test="${articleVO != null}">
			<!-- insert回傳的VO沒有文章編號 -->
					<div onclick="sendNotify()" id="sendNotify" style="display:none;">${articleVO.mbr_no}/article</div>
			</c:if>
			<c:if test="${article_ReplyVO != null}">
			<!-- insert回傳的VO沒有留言編號 -->
					<div onclick="sendNotify()" id="sendNotify" style="display:none;">${article_ReplyVO.art_no}/reply</div>
			</c:if>
		<!-- 雅凰加的，為了嘗試啟動通知的推播 -->

            <div class="each_article">
                    <c:forEach var="articleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
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
                                            <img src="https://megapx-assets.dcard.tw/images/52057289-337a-4f2f-88c0-cb8a77ee422a/orig.png" title="愛心" style="z-index:3" class=" icon_size icon_pic"></div>
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
                 							<c:set var="collection_status" value="1"></c:set>
                 							<c:if test="${my_collection_list_replace.art_no==articleVO.art_no}">
                 							<img src="/CEA103G1/images/bookmarks.svg" width="15px" height="15px">
                 							</c:if>
											</c:forEach>
											<c:if test="${collection_status==0}"><img src="/CEA103G1/images/bookmarks-outline.svg" width="15px" height="15px"></c:if>
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



	<c:if test="${openModal!=null}">

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content" >
					<div class="modal-body">
						<!-- =========================================以下為原listOneArticle.jsp的內容========================================== -->
						<jsp:include page="listOneArticle.jsp" />
						
						<!-- =========================================以上為原listOneArticle.jsp的內容========================================== -->
					</div>
				</div>
			</div>
		</div>

	</c:if>



		
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
        <button type="button" class="btn btn-secondary close_modal" data-bs-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/front-end/member/login.jsp'">登入</button>
      </div>
    </div>
  </div>
</div>



  	<!-- 捲軸狀態 -->
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
		<script src="jquery.hover.js"></script>
		

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
		var subscribe_bd_cl_no = $(this).prev(".this_bd_bl_no").text();
		console.log(subscribe_bd_cl_no);
		$.ajax({ // 負責傳到board_classServlet 新增某人對某看板的訂閱  需要的參數: mbr_no bd_cl_no 
			type : "POST",
			url : "/CEA103G1/board_class/board_class.do",
			data : {action: "subscribe",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,bd_cl_no:subscribe_bd_cl_no},
			success : function(data) {
				alert("新增"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"對看板"+subscribe_bd_cl_no+"的訂閱成功");
			}
		});
		window.location.reload();
 	})
 	
 	
 		$(".cancel_subscribe").click(function(){
		var subscribe_bd_cl_no = $(this).prev(".this_bd_bl_no").text();
		console.log(subscribe_bd_cl_no);
		$.ajax({ // 負責傳到board_classServlet 取消某人對某看板的訂閱  需要的參數: mbr_no bd_cl_no 
			type : "POST",
			url : "/CEA103G1/board_class/board_class.do",
			data : {action: "cancel_subscribe",mbr_no:<%=pageContext.getAttribute("ajax_mbr_no")%>,bd_cl_no:subscribe_bd_cl_no},
			success : function(data) {
				alert("取消"+<%=pageContext.getAttribute("ajax_mbr_no")%>+"對看板"+subscribe_bd_cl_no+"的訂閱成功");
			}
		});
		window.location.reload();
 	})


		$("#basicModal").modal({
			show : true
		});
		
	

//   	var infScroll = new InfiniteScroll( ".container", {
//   		path: function() {
//   			// 頁面路徑
<%--   			if ( this.loadCount < <%=max_page%> ) { --%>
//   				// 只讀取到最後一頁的資料
//   				var nextIndex = this.loadCount + 2; // 2
<%--   				return "/CEA103G1/front-end/article/listOneBoard_ClassArticleByLikes.jsp?bd_cl_no=<%=bd_cl_no%>whichPage="+nextIndex; --%>
//   			}
//   		},
//   		append: ".each_article", // 匯入物件類別
//   		status: ".scroller-status" // 捲軸狀態類別
//   	})

  $(".to_login").click(function(){
	  $('#login_confirm').modal('show');
  })
  
  $('.close_modal').click(function(){
	  $('#login_confirm').modal('hide');
  })
  
  
  
   $(".banned").click(function(){
	 swal({
         title: "由於觸犯板規，您已被禁發文章！",
         text: "處分時間尚餘:"+"${banned_chinese}"+"，3秒後自動關閉視窗",
         timer: 3000
     }).then(
         function() {},
         // handling the promise rejection
         function(dismiss) {
             if (dismiss === 'timer') {
                 console.log('I was closed by the timer')
             }
         }
     )
 });
  
  
  </script>
   <!-- 雅凰嘗試加上首頁之頁首的WebSocket -->
  <script>
		function writeToScreen(input){
			let noRead = JSON.parse(input);
			
			var notifyMail = document.getElementById('countNoReadMail');
			notifyMail.innerText = noRead.countNoReadMail;
			var notifyNotify = document.getElementById('countNoReadNotify');
			notifyNotify.innerText = noRead.countNoReadNotify;
			
			if(noRead.mail_no !== undefined){
				let tableOri = document.getElementById('mailTable');
				let trOri = document.createElement('tr');
				trOri.innerHTML = "<td style='display:none;'>"+ noRead.mail_no+"</td><td>"+ noRead.send_no+"</td><td>"+noRead.rcpt_no+"</td><td>"+noRead.mail_cont+"</td><td>"+noRead.mail_time+"</td>";
//				console.log(tableOri);
//				console.log(trOri);
				trOri.style.fontWeight=555;
				tableOri.prepend(trOri);
			}
		}
		function connection(){
			let wsUri = 'ws://'+'<%=request.getServerName()%>'+':'+'<%=request.getServerPort()%>'+'<%=request.getContextPath()%>'+'/Member_mailNotify/${memberVO.mbr_no}';
			websocket = new WebSocket(wsUri);
			websocket.onopen = function(event){
				let e = document.createEvent("MouseEvent");
				e.initEvent("click",true,true);
				if(document.getElementById('sendNotify') !== null){
					document.getElementById('sendNotify').dispatchEvent(e);
				}
			};
			websocket.onmessage = function(event){
				let noRead = event.data;
				writeToScreen(noRead);
			};
		}
		function sendNotify(){
			let sendNotify = document.getElementById('sendNotify');
			websocket.send(sendNotify.innerText);
		}
		
</script>
  <!-- 雅凰嘗試加上首頁之頁首的WebSocket -->
</body>
</html>