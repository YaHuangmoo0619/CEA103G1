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
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list = articleSvc.getAll_Front();
// 	Map<Integer,String> article_first_img_map = new HashMap<Integer,String>(); //這是一個存有「有首圖」的Map，key為該文章號碼，value為base64編碼
// 	int start_index;
// 	int end_index;
// 	String first_img_base64;
// 	for(ArticleVO x : list){
// 		start_index = x.getArt_cont().indexOf("<p><img");
// 		if(start_index>=0){ //有首圖的話
// 		System.out.println("start_index:"+start_index);
// 		end_index   = x.getArt_cont().indexOf("</p>", start_index)+4; //從第一張圖片<p><img的位置以後開始搜尋到的第一個 </p>，即為第一張圖的結束
// 			first_img_base64 = x.getArt_cont().substring(start_index, end_index); //擷取到第一張圖片的base64編碼
// 			System.out.println("i'm here");
// 			System.out.println(first_img_base64);
// 			article_first_img_map.put(x.getArt_no(), first_img_base64); //放入Map中	
// 		}
// 	}


	double max_page = Math.ceil(list.size()/5);
	pageContext.setAttribute("list", list);
// 	pageContext.setAttribute("article_first_img_map", article_first_img_map);
%>

<%
	Board_ClassService board_classSvc = new Board_ClassService();
	List<Board_ClassVO> bd_list = board_classSvc.getAll();
	pageContext.setAttribute("bd_list", bd_list);
%>

<% 
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO"); 
%>


<%
	Jedis jedis = new Jedis("localhost", 6379);
	jedis.auth("123456");
	jedis.select(6);
	

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

<title>列出所有文章</title>
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



/* -----------------------------以下為側欄css------------------------------ */
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
  font-family:微軟正黑體;
}

/* -----------------------------以下為主欄css------------------------------ */
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
<!-- 雅凰嘗試加上onload事件 -->
<body onload="connection()">
	<div>目前登入的人是: ${memberVO.mbr_no}</div>
	<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
	
<!-- 	如果有登入的話 -->
	<c:if test="${not empty memberVO }"> 
	<a class=write title="發文" href="<%=request.getContextPath()%>/front-end/article/addArticle.jsp"><img src="/CEA103G1/images/write.svg" width="24px" height="24px"></a>
	</c:if>
<!-- 	如果沒有登入的話  要打開名為登入的燈箱-->	
	<c:if test="${empty memberVO }"> 
	<div class="no_login write" id="to_login"><img src="/CEA103G1/images/write.svg" width="24px" height="24px"></div>
	</c:if>



<div id="sidebar">
  <div class="list">
			<c:forEach var="board_classVO" items="${bd_list}">
				<div class="item" ><a href="<%=request.getContextPath()%>/article/article.do?bd_cl_no=${board_classVO.bd_cl_no}&action=getOneArticle_ByBoard_Clss_For_Display"  style="color:white;">${board_classVO.bd_name}</a></div>
			</c:forEach>
  </div>
</div>


<div class=main_content id=main_content>
<div class="scroll"> 
	<%@ include file="pageforhome.file"%>


        <div class="container">
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
                                <c:set var="cont_test" value="${fn:substring(articleVO.art_cont, 0, 35)}"></c:set> 
                                <c:if test="${not fn:contains(cont_test, '<im')}">
                                <p>${fn:substring(articleVO.art_cont, 0, 35)}...</p>
                                </c:if>
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
                                        <svg viewBox="0 0 24 24" focusable="false" role="img" aria-hidden="true" class="icon_size archieve_pic">
                                            <path d="M17.65 21.39L12 17.5l-5.65 3.88A1.5 1.5 0 014 20.15V5a2.5 2.5 0 012.5-2.5h11A2.5 2.5 0 0120 5v15.15a1.5 1.5 0 01-2.35 1.24z"></path>
                                        </svg>
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
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
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
	</script>


	<script>
		$("#basicModal").modal({
			show : true
		});
		
		
		$('.article').click(function(){
			
		})
	</script>
	
	  <script>
  	var infScroll = new InfiniteScroll( ".scroll", {
  		path: function() {
  			// 頁面路徑
  			if ( this.loadCount < <%=max_page%> ) {
  				// 只讀取到最後一頁的資料
  				var nextIndex = this.loadCount + 2; // 2
  				return "http://localhost:8081/CEA103G1/front-end/article/listAllArticle.jsp?whichPage="+nextIndex;
  			}
  		},
  		append: ".container", // 匯入物件類別
  		status: ".scroller-status" // 捲軸狀態類別
  	})
  </script>

  <script>
  $("#to_login").click(function(){
	  $('#login_confirm').modal('show');
  })
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