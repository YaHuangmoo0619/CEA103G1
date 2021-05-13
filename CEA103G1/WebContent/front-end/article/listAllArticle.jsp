<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.board_class.model.*"%>

<%
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list = articleSvc.getAll_Front();
	double max_page = Math.ceil(list.size()/5);
	pageContext.setAttribute("list", list);
%>

<%
	Board_ClassService board_classSvc = new Board_ClassService();
	List<Board_ClassVO> bd_list = board_classSvc.getAll();
	pageContext.setAttribute("bd_list", bd_list);
%>


<jsp:useBean id="bd_clSvc" scope="page"
	class="com.board_class.model.Board_ClassService" />
<jsp:useBean id="bd_clDAO" scope="page"
	class="com.board_class.model.Board_ClassDAO" />


<html>
<head>
<meta charset="Big5">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

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

</style>

</head>
<body>
	<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
	<a class=write title="發文" href="<%=request.getContextPath()%>/front-end/article/addArticle.jsp"><img src="/CEA103G1/images/write.svg" width="24px" height="24px"></a>
	



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
                                            <div class="date"><fmt:formatDate value="${articleVO.art_rel_time}"
			pattern="MM月dd日  HH:mm" /></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <h2 class="title_box">
                                <a class="title" href="<%=request.getContextPath()%>/article/article.do?art_no=${articleVO.art_no}&action=getOne_From2">${articleVO.art_title}</a></h2>
                            <div class="post">
                                <div class="post_0">
                                    <span>#會計</span><span>#交換</span></div>
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
                                    <span class="response">回應</span><span>283</span>
                                </div>
                                <div class="archieve">
                                    <div class="archieve_0">
                                        <svg viewBox="0 0 24 24" focusable="false" role="img" aria-hidden="true" class="icon_size archieve_pic">
                                            <path d="M17.65 21.39L12 17.5l-5.65 3.88A1.5 1.5 0 014 20.15V5a2.5 2.5 0 012.5-2.5h11A2.5 2.5 0 0120 5v15.15a1.5 1.5 0 01-2.35 1.24z"></path>
                                        </svg>
                                        <span>收藏</span></div>
                                </div>
                            </div>
                            <img class="pic" src="https://imgur.dcard.tw/GFvQZ7Gb.jpg" width="84px" height="84px" alt="" loading="lazy" referrerpolicy="no-referrer">
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
				<div class="modal-content">
					<div class="modal-body">
						<!-- =========================================以下為原listOneArticle.jsp的內容========================================== -->
						<jsp:include page="listOneArticle.jsp" />
						
						<!-- =========================================以上為原listOneArticle.jsp的內容========================================== -->
					</div>
				</div>
			</div>
		</div>

	</c:if>

  	<!-- 捲軸狀態 -->
  	<div class="scroller-status">
  		<div class="infinite-scroll-request loader-ellips"></div>
  	</div>

</div>



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

</body>
</html>