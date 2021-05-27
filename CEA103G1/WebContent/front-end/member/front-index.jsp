<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.member.model.*" %>
<%
	MemberVO memVO = (MemberVO) session.getAttribute("memVO");
	String pleaseLogin = (String)request.getAttribute("pleaseLogin");
	List<String> errorMsgs = (List<String>) request.getAttribute("errorMsgs");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-end/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-end/login.css">
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/img/logo.png">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<title>Maison Camp | 露營家</title>
</head>
<body>
 <div id="parallax-world-of-ugg">
        <div class="block">
        <header class="header">
            <nav role="navigation">
                <div id="menuToggle">
                    <input type="checkbox" id="checkboxtoggle"/>
                    <span></span>
                    <span></span>
                    <span></span>
                    <ul id="menu">
                        <a href="<%=request.getContextPath()%>/front-end/front-index.jsp"><li>首頁</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/news/News.jsp"><li>最新消息</li></a>
                        <a class="enterAlert" href="<%=request.getContextPath()%>/front-end/member/Member.jsp"><li>會員中心</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/room-type/RoomType.jsp"><li>帳型介紹</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/room-booking/RoomBooking.jsp"><li>立即訂房</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/item/shoppingMall.jsp"><li>精選商城</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/activity/selectPage.jsp"><li>活動預約</li></a>
                        <a href="#"><li>聯絡我們</li></a>
                    </ul>
                </div>
            </nav>
            <div class="col-xs-4 col-12 logo">
                <a href="#"><img id="logoo" class="img-logo" src="<%=request.getContextPath()%>/img/logo.png" alt=""></a>
            </div> 
            <c:choose>
            	<c:when test="${memVO == null}">
	            	<ul class="signin-links">
	        			<li><a class="" href="<%=request.getContextPath()%>/front-end/signup/SignUp.jsp"><i class="material-icons signup-icon">account_circle</i>註冊</a></li>
	        			<li><a class="signin" href="#"><i class="material-icons login-icon">face</i>登入</a></li>
	      			</ul>	
            	</c:when>
            	<c:otherwise>
            		<ul class="signin-links">
	        			<li><i style="margin-right:7px; color:#c15c16;" class="fas fa-child fa-1x"></i>${memVO.name} 歡迎回來<i style="color:#496b6b; margin: 0 10px 0 5px;" class="fas fa-exclamation"></i><a class="signin" href="<%=request.getContextPath()%>/Member.do?action=logout"><i class="fas fa-sign-out-alt"></i></a></li>
	      			</ul>
            	</c:otherwise>
            </c:choose>
        </header>
        <div id="lightBox" style="display: none;">
           <div class="loginpanel">
            <div class="content-1" style="display: block;">
            <div class="loginTop">
                <div>
                    <h2 class="title">會員登入</h2>
                </div>
                <div id="cancelLogin">
           			<i class="fas fa-times fa-2x"></i>
          		</div> 
            </div>
                <form method="post" action="<%=request.getContextPath()%>/MemberLoginHandler">
                <div class="txt">
                  <input id="user" type="text" placeholder="請輸入Email" name="mem_email"/>
                  <label for="user" class="entypo-user"></label>
                </div>
                <div class="txt">
                  <input id="pwd" type="password" placeholder="請輸入密碼" name="mem_user_pwd"/>
                  <label for="pwd" class="entypo-lock"></label>
                </div>
                <div class="buttons">
                  <input type="submit" value="登入"/>
                  <span>
                    <a href="<%=request.getContextPath()%>/front-end/signup/SignUp.jsp" class="entypo-user-add register">加入會員</a>
                  </span>
                </div>
                </form>
            </div>
           <div class="content-3" style="display: none;">
                <div>
                    <h2 class="title">忘記密碼</h2>
                </div>
                <div class="txt">
                  <input id="user" type="email" placeholder="Email" />
                  <label for="user" class="entypo-mail"></label>
                </div>
                <div class="txt">
                  <input id="pwd" type="date" />
                  <label for="pwd" class="entypo-calendar"></label>
                </div>
                <div class="buttons">
                  <input type="submit" value="重寄密碼"/>
                </div>
            </div>
             <div class="hr">
              <div></div>
              <div></div>
              <div></div>
            </div>      
            <div class="social">
                <div class="align">
                    <li class="login"></li>
                    <p>會員登入</p>
                </div>
                <div class="align">
                    <li class="forget"></li>
                    <p>忘記密碼</p>
                </div>
            </div>
          </div>
        </div> 
         <section>
            <div id="carouselExampleControls" class="carousel slide lickme" data-ride="carousel">
                <div class="carousel-inner">
                  <div class="carousel-item active">
                    <a href=""><img src="<%=request.getContextPath()%>/img/slide2.png" class="d-block w-100" alt="..."></a>
                  </div>
                  <div class="carousel-item">
                    <a href=""><img src="<%=request.getContextPath()%>/img/slide1.jpg" class="d-block w-100" alt="..."></a>
                  </div>
                  <div class="carousel-item">
                    <a href=""><img src="<%=request.getContextPath()%>/img/slide3.jpg" class="d-block w-100" alt="..."></a>
                  </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="sr-only">Next</span>
                </a>
            </div>
            <div class="marquee">
              <p class="news-title">NEWS</p>
              <ul>
                <a href=""><li>【2020-12-31】Maison Camp 集聚一堂草地音樂會！</li></a>
                <a href=""><li>【2021-01-20】Maison Camp 最新訂房優惠開跑囉！</li></a>
                <a href=""><li>【2021-03-31】Maison Camp 商城農產品大特價！</li></a>
              </ul>
            </div>
        </section>
         <section>
          <div id="book-checking">
            <div class="book-title">豪華露營預約狀況即時查詢</div>
            <div class="checking-date">
              <div class="mobile-checking">
                <a href="<%=request.getContextPath()%>/front-end/room-booking/RoomBooking.jsp">
                  <img src="<%=request.getContextPath()%>/img/bed-4.svg" alt="">
                  <span>四人帳</span>
                </a>
              </div>
              <div class="mobile-checking">
                <a href="<%=request.getContextPath()%>/front-end/room-booking/RoomBooking.jsp">
                  <img src="<%=request.getContextPath()%>/img/bed-2.svg" alt="">
                  <span>二人帳</span>
                </a>
              </div>
              <div class="mobile-checking">
                <a href="<%=request.getContextPath()%>/front-end/room-booking/RoomBooking.jsp">
                  <img src="<%=request.getContextPath()%>/img/bed-1.svg" alt="">
                  <span>單人帳</span>
                </a>
              </div>
            </div>
          </div>
        </section> 
        <section>
          <div class="rest-title">
            <div class="rest-title-container">
              <h1>林間休憩</h1>
              數種住宿選擇，讓您和家人朋友一起迎接自然<br>
              我們提供現在最流行的 Glamping 住宿， Glamping是一種豪華露營、 野奢莊園 、野奢酒店的概念，要帶給您一種全新的露營體驗
            </div>
            <div class="rest">
              <figure class="rest-box">
                <img class="img-responsive" src="<%=request.getContextPath()%>/img/house1.png" alt="">
                <figcaption class="rest-box-text">豐庭度假屋</figcaption>
              </figure>
              <figure class="rest-box">
                <img class="img-responsive" src="<%=request.getContextPath()%>/img/house2.png" alt="">
                <figcaption class="rest-box-text"><i>Maison Camp</i>豪華露營</figcaption>
              </figure>
            </div>
          </div>
        </section>
      </div>
         <section>
            <div class="parallax-two">
              <div class="presentation-nav">
                <ul>
                  <li class="presentation-nav-normal">
                    <span>精選商城</span>
                    <i class="fa fa-shopping-cart"></i>
                  </li>
                  <li class="presentation-nav-normal">
                    <span>活動預定</span>
                    <i class="fa fa-gavel"></i>
                  </li>
                  <li class="presentation-nav-normal">
                    <span>人文休憩</span>
                    <i class="fa fa-heart"></i>
                  </li>        
                </ul>
              </div>
              <div class="presentation-text">
                <div class="presentation-bg">
                  <h1>精選商城</h1>
                  <h4>多種自有農場水果加工品</h4>
                </div>
              </div>
            </div>
          </section>
            <section>
            <div class="block">
                <img style="width: 100%; position: relative;" src="<%=request.getContextPath()%>/img/brooke-cagle-xJ0Isuqt9ys-unsplash.jpg" alt="">
                <div class="presentation-text2">
                  <div class="presentation-bg2">
                    <h1>活動預定</h1>
                    <h4>多種精彩採果趣</h4>
                  </div>
                </div>
            </div>         
          </section>
          <section>
            <div class="parallax-three">
              <div class="presentation-text">
                <div class="presentation-bg">
                  <h1>休憩體驗</h1>
                  <h4>與家人朋友一同享受美好環境</h4>
                </div>
              </div>
            </div>
          </section>
       <div class="block"> 
           <footer class="site-footer">
          <div class="container">
            <div class="row">
              <div class="col-sm-12 col-md-6">
                <h6>關於我們</h6>
                <p class="text-justify"><i>Maison Camp</i> 豪華露營提供多項的活動的安排與遊憩設施。<br>適合親子或三五好友一同前來放鬆體驗。<br>豪華露營、 野奢莊園 、野奢酒店的概念，要帶給您一種全新的露營體驗。<br>快來與我們一起聽流水蟲鳴鳥叫 與森林一同呼吸。</p>
              </div>
              <div class="col-xs-6 col-md-2">
                <h6>快速連結</h6>
                <ul class="footer-links">
                  <li><a href="<%=request.getContextPath()%>/front-end/member/Member.jsp">會員中心</a></li>
                  <li><a href="<%=request.getContextPath()%>/front-end/room-booking/RoomBooking.jsp">立即訂房</a></li>
                  <li><a href="<%=request.getContextPath()%>/front-end/item/shoppingMall.jsp">精選商城</a></li>
                  <li><a href="<%=request.getContextPath()%>/front-end/activity/selectPage.jsp">預約活動</a></li>
                  <li><a href="">聯繫我們</a></li>
                </ul>
              </div>
              <div class="col-xs-6 col-md-3">
                <a href=""><img class="footer-map" src="<%=request.getContextPath()%>/img/footer.png" style="height: 160px; width: 300px;" alt=""></a>
              </div>
            </div>
            <hr>
          </div>
          <div class="container">
            <div class="row">
              <div class="col-md-8 col-sm-6 col-xs-12">
                <p class="copyright-text">Copyright &copy; 2021 All Rights Reserved by 
             <a href="#">Maison Camp</a>.
                </p>
              </div>
              <div class="col-md-4 col-sm-6 col-xs-12">
                <ul class="social-icons">
                  <li><a class="facebook" href="#"><i class="fa fa-facebook"></i></a></li>
                  <li><a class="twitter" href="#"><i class="fa fa-twitter"></i></a></li>
                  <li><a class="dribbble" href="#"><i class="fa fa-dribbble"></i></a></li>
                  <li><a class="linkedin" href="#"><i class="fa fa-linkedin"></i></a></li>   
                </ul>
              </div>
            </div>
          </div>
    </footer>
    </div>
   </div>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/bootstrap/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/front-end/index.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <c:if test="${not empty errorMsgs}">
			<script type="text/javascript">
				swal("帳密錯誤","請重新輸入","error");
				$("#lightBox").css("display","");
			</script>
		</c:if>	
</body>




</html>