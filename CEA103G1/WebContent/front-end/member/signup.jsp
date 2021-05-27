<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
  java.sql.Date birthday = null;
  try {
	    birthday = java.sql.Date.valueOf(request.getParameter("birthday").trim());
   } catch (Exception e) {
	    birthday = new java.sql.Date(System.currentTimeMillis());
   }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/signup.css">
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/build/css/countrySelect.css"> --%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
    <title>Campion營家 | 加入會員</title>
    
   
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
</head>
<body>
<div class="container">
	<a href="<%=request.getContextPath()%>/front-end/front-index.jsp">回首頁</a>
	<section id="Steps" class="steps-section">
    <h2 class="steps-header">
      加入 Campion營家 會員
    </h2>
<!--     <div class="steps-timeline"> -->
<!--       <div class="steps-one"> -->
<!--         <img class="steps-img" src="http://placehold.it/50/3498DB/FFFFFF" alt="" /> -->
<!--         <h5 class="steps-name"> -->
<!--           填寫基本資料 -->
<!--         </h5> -->
<!--         <p class="steps-description"> -->
<!--           正確填寫您的資料可完整保障您的權益 -->
<!--         </p> -->
<!--       </div> -->
      
<!--       <div class="steps-two"> -->
<!--         <img class="steps-img" src="http://placehold.it/50/3498DB/FFFFFF" alt="" /> -->
<!--         <h5 class="steps-name"> -->
<!--           	付款資訊 -->
<!--         </h5> -->
<!--         <p class="steps-description"> -->
<!--           填寫您的信用卡付款資訊可方便您使用本網站功能 -->
<!--         </p> -->
<!--       </div> -->

<!--       <div class="steps-three"> -->
<!--         <img class="steps-img" src="http://placehold.it/50/3498DB/FFFFFF" alt="" /> -->
<!--         <h3 class="steps-name"> -->
<!--           確認驗證信 -->
<!--         </h3> -->
<!--         <p class="steps-description"> -->
<!--            我們將發送驗證信到您填寫的電子信箱 -->
<!--         </p> -->
<!--       </div> -->

<!--       <div class="steps-four"> -->
<!--         <img class="steps-img" src="http://placehold.it/50/3498DB/FFFFFF" alt="" /> -->
<!--         <h3 class="steps-name"> -->
<!--           完成註冊 -->
<!--         </h3> -->
<!--         <p class="steps-description"> -->
<!--            歡迎您加入<br> -->
<!--            Maison Camp! -->
<!--         </p> -->
<!--       </div> -->
<!--     </div> -->
  </section>
    <form name="memberForm" method="post" action="<%=request.getContextPath()%>/Member.do">
      <div class="title">
      </div>
      <div class="row info-form">
        <h3>基本資料</h3>
        <div class="input-group input-group-icon">
        		<div class="input-name">姓名：</div>
          <input type="text" placeholder="姓名 (長度6-12字)" name="name" minlength="1" maxlength="12"/>
          <div class="input-icon"><i class="fa fa-user"></i></div>
          	<p id="errorMsgName" style="font-size:2px; color:red; margin-left:105px;"></p>
        </div>
        <div class="input-group input-group-icon">
        		<div class="input-name">帳號：</div>
          <input type="text" placeholder="帳號 (長度8-12字 )" name="user_id" minlength="1" maxlength="12"/>
          <div class="input-icon"><i class="far fa-user-circle"></i></div>
          	<p id="errorMsgUserId" style="font-size:2px; color:red; margin-left:105px;"></p>
        </div>
        <div class="input-group input-group-icon">
        		<div class="input-name">密碼：</div>
          <input type="password" placeholder="密碼 (長度8-12字且不包含特殊符號)" name="user_pwd" minlength="1" maxlength="12"/>
          <div class="input-icon"><i class="fa fa-key"></i></div>
          	<p id="errorMsgUserPwd" style="font-size:2px; color:red; margin-left:105px;"></p>
        </div>
        <div class="input-group input-group-icon">
        		<div class="input-name">密碼確認：</div>
          <input id="check-repeat-pwd" type="password" placeholder="再次輸入密碼" name="re_enter_pwd"/>
          <div class="input-icon"><i class="fa fa-key"></i></div>
          	<p id="errorMsgUserRePwd" style="font-size:2px; color:red; margin-left:105px;"></p>
        </div>
        <div class="input-group input-group-icon">
        		<div class="input-name">電子信箱：</div>
          <input id="check-repeat-email" type="email" placeholder="電子信箱" name="email"/>
          <div class="input-icon"><i class="fa fa-envelope"></i></div>
          	<p id="errorMsgEmail" style="font-size:2px; color:red; margin-left:105px;"></p>
        </div>
        <div class="input-group input-group-icon">
        		<div class="input-name">手機號碼：</div>
          <input type="text" placeholder="手機號碼" name="phone"/>
          <div class="input-icon"><i class="far fa-phone-alt"></i></div>
        </div>
        <div class="input-group input-group-icon">
        		<div class="input-name">出生年月日：</div>
          <input id="birthday" type="text" placeholder="生日" name="birthday"/>
          <div class="input-icon"><i class="far fa-birthday-cake"></i></div>
        </div>
        <div class="input-group input-group-icon">
        		<div class="input-name">身分證字號：</div>
          <input type="text" placeholder="身分證字號" name="personal_id"/>
          <div class="input-icon"><i class="far fa-user-circle"></i></div>
          <p id="errorMsgID" style="font-size:2px; color:red; margin-left:105px;"></p>
        </div>
        <div class="input-group input-group-icon">
        		<div class="input-name">國籍：</div>
          <input type="text" id="country" placeholder="國籍">
          <input type="hidden" name="nation" id="country_code" />
          <div class="input-icon"><i class="fas fa-globe-asia"></i></div>
        </div>
      </div>
      <div class="row info-form">
        <div class="col-half">
          <h3>性別</h3>
          <div class="input-group">
            <input type="radio" name="gender" value="male" id="gender-male"/>
            <label for="gender-male">男</label>
            <input type="radio" name="gender" value="female" id="gender-female"/>
            <label for="gender-female">女</label>
          </div>
        </div>
      </div>
      <div class="row info-form" >
        <h3>Terms and Conditions</h3>
        <div class="input-group">
          <input type="checkbox" id="terms"/>
          <label for="terms">I accept the terms and conditions for signing up to this service, and hereby confirm I have read the privacy policy.</label>
        </div>
      </div>
      <div class="row info-form" id="info-form-button" >
      	<button type="button" class="next-step">下一步，填寫付款資訊</button>
      </div>
      <div class="row" id="info-payment" style="display:none;">
	      	 <div class="payment-title">
	        <h2>付款資訊</h2>
	    </div>
	    <div class="container-card preload">
	        <div class="creditcard">
	            <div class="front">
	                <div id="ccsingle"></div>
	                <svg version="1.1" id="cardfront" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
	                    x="0px" y="0px" viewBox="0 0 750 471" style="enable-background:new 0 0 750 471;" xml:space="preserve">
	                    <g id="Front">
	                        <g id="CardBackground">
	                            <g id="Page-1_1_">
	                                <g id="amex_1_">
	                                    <path id="Rectangle-1_1_" class="lightcolor grey" d="M40,0h670c22.1,0,40,17.9,40,40v391c0,22.1-17.9,40-40,40H40c-22.1,0-40-17.9-40-40V40
	                            C0,17.9,17.9,0,40,0z" />
	                                </g>
	                            </g>
	                            <path class="darkcolor greydark" d="M750,431V193.2c-217.6-57.5-556.4-13.5-750,24.9V431c0,22.1,17.9,40,40,40h670C732.1,471,750,453.1,750,431z" />
	                        </g>
	                        <text transform="matrix(1 0 0 1 60.106 295.0121)" id="svgnumber" class="st2 st3 st4">0123 4567 8910 1112</text>
	                        <text transform="matrix(1 0 0 1 54.1064 428.1723)" id="svgname" class="st2 st5 st6">JOHN DOE</text>
	                        <text transform="matrix(1 0 0 1 54.1074 389.8793)" class="st7 st5 st8">cardholder name</text>
	                        <text transform="matrix(1 0 0 1 479.7754 388.8793)" class="st7 st5 st8">expiration</text>
	                        <text transform="matrix(1 0 0 1 65.1054 241.5)" class="st7 st5 st8">card number</text>
	                        <g>
	                            <text transform="matrix(1 0 0 1 574.4219 433.8095)" id="svgexpire" class="st2 st5 st9">01/23</text>
	                            <text transform="matrix(1 0 0 1 479.3848 417.0097)" class="st2 st10 st11">VALID</text>
	                            <text transform="matrix(1 0 0 1 479.3848 435.6762)" class="st2 st10 st11">THRU</text>
	                            <polygon class="st2" points="554.5,421 540.4,414.2 540.4,427.9 		" />
	                        </g>
	                        <g id="cchip">
	                            <g>
	                                <path class="st2" d="M168.1,143.6H82.9c-10.2,0-18.5-8.3-18.5-18.5V74.9c0-10.2,8.3-18.5,18.5-18.5h85.3
	                        c10.2,0,18.5,8.3,18.5,18.5v50.2C186.6,135.3,178.3,143.6,168.1,143.6z" />
	                            </g>
	                            <g>
	                                <g>
	                                    <rect x="82" y="70" class="st12" width="1.5" height="60" />
	                                </g>
	                                <g>
	                                    <rect x="167.4" y="70" class="st12" width="1.5" height="60" />
	                                </g>
	                                <g>
	                                    <path class="st12" d="M125.5,130.8c-10.2,0-18.5-8.3-18.5-18.5c0-4.6,1.7-8.9,4.7-12.3c-3-3.4-4.7-7.7-4.7-12.3
	                            c0-10.2,8.3-18.5,18.5-18.5s18.5,8.3,18.5,18.5c0,4.6-1.7,8.9-4.7,12.3c3,3.4,4.7,7.7,4.7,12.3
	                            C143.9,122.5,135.7,130.8,125.5,130.8z M125.5,70.8c-9.3,0-16.9,7.6-16.9,16.9c0,4.4,1.7,8.6,4.8,11.8l0.5,0.5l-0.5,0.5
	                            c-3.1,3.2-4.8,7.4-4.8,11.8c0,9.3,7.6,16.9,16.9,16.9s16.9-7.6,16.9-16.9c0-4.4-1.7-8.6-4.8-11.8l-0.5-0.5l0.5-0.5
	                            c3.1-3.2,4.8-7.4,4.8-11.8C142.4,78.4,134.8,70.8,125.5,70.8z" />
	                                </g>
	                                <g>
	                                    <rect x="82.8" y="82.1" class="st12" width="25.8" height="1.5" />
	                                </g>
	                                <g>
	                                    <rect x="82.8" y="117.9" class="st12" width="26.1" height="1.5" />
	                                </g>
	                                <g>
	                                    <rect x="142.4" y="82.1" class="st12" width="25.8" height="1.5" />
	                                </g>
	                                <g>
	                                    <rect x="142" y="117.9" class="st12" width="26.2" height="1.5" />
	                                </g>
	                            </g>
	                        </g>
	                    </g>
	                    <g id="Back">
	                    </g>
	                </svg>
	            </div>
	            <div class="back">
	                <svg version="1.1" id="cardback" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
	                    x="0px" y="0px" viewBox="0 0 750 471" style="enable-background:new 0 0 750 471;" xml:space="preserve">
	                    <g id="Front">
	                        <line class="st0" x1="35.3" y1="10.4" x2="36.7" y2="11" />
	                    </g>
	                    <g id="Back">
	                        <g id="Page-1_2_">
	                            <g id="amex_2_">
	                                <path id="Rectangle-1_2_" class="darkcolor greydark" d="M40,0h670c22.1,0,40,17.9,40,40v391c0,22.1-17.9,40-40,40H40c-22.1,0-40-17.9-40-40V40
	                        C0,17.9,17.9,0,40,0z" />
	                            </g>
	                        </g>
	                        <rect y="61.6" class="st2" width="750" height="78" />
	                        <g>
	                            <path class="st3" d="M701.1,249.1H48.9c-3.3,0-6-2.7-6-6v-52.5c0-3.3,2.7-6,6-6h652.1c3.3,0,6,2.7,6,6v52.5
	                    C707.1,246.4,704.4,249.1,701.1,249.1z" />
	                            <rect x="42.9" y="198.6" class="st4" width="664.1" height="10.5" />
	                            <rect x="42.9" y="224.5" class="st4" width="664.1" height="10.5" />
	                            <path class="st5" d="M701.1,184.6H618h-8h-10v64.5h10h8h83.1c3.3,0,6-2.7,6-6v-52.5C707.1,187.3,704.4,184.6,701.1,184.6z" />
	                        </g>
	                        <text transform="matrix(1 0 0 1 621.999 227.2734)" id="svgsecurity" class="st6 st7">985</text>
	                        <g class="st8">
	                            <text transform="matrix(1 0 0 1 518.083 280.0879)" class="st9 st6 st10">security code</text>
	                        </g>
	                        <rect x="58.1" y="378.6" class="st11" width="375.5" height="13.5" />
	                        <rect x="58.1" y="405.6" class="st11" width="421.7" height="13.5" />
	                        <text transform="matrix(1 0 0 1 59.5073 228.6099)" id="svgnameback" class="st12 st13">John Doe</text>
	                    </g>
	                </svg>
	            </div>
	        </div>
	    </div>
	    <div class="form-container">
	        <div class="field-container">
	            <label for="name">姓名</label>
	            <input class="credit-input" id="name" maxlength="20" type="text">
	        </div>
	        <div class="field-container">
	            <label for="cardnumber">卡號</label><span id="generatecard">generate random</span>
	            <input class="credit-input" id="cardnumber" name="payment" type="text">
	            <svg id="ccicon" class="ccicon" width="750" height="471" viewBox="0 0 750 471" version="1.1" xmlns="http://www.w3.org/2000/svg"
	                xmlns:xlink="http://www.w3.org/1999/xlink">
	
	            </svg>
	        </div>
	        <div class="field-container">
	            <label for="expirationdate">到期日 (月/年)</label>
	            <input id="expirationdate" type="text" inputmode="numeric">
	        </div>
	        <div class="field-container">
	            <label for="securitycode">卡片安全碼</label>
	            <input id="securitycode" type="text" inputmode="numeric">
	        </div>
	    </div>
      </div>
      <div class="row" id="info-payment-button" style="display:none;">
      	<input type="hidden" name="action" value="insert">
      	<button type="button" class="back-to-prev">上一步</button>
      	<button type="submit" class="form-submit">送出申請</button>
      </div>
    </form>
  </div>
  	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
  	<script src="https://unpkg.com/imask"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/front-end/signup.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
    <script src="<%=request.getContextPath()%>/build/js/countrySelect.min.js"></script>
 <script>
        $.datetimepicker.setLocale('zh');
        $('#birthday').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=birthday%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $("#country").countrySelect({
        		defaultCountry:"tw"
        });
        
    	$(".back-to-prev").click(function(){
    		$(".info-form").css("display", "")
    		$("#info-payment-button").css("display","")
    		$("#info-payment").css("display", "none")
    		$(".back-to-prev").css("display", "none")
    		$(".form-submit").css("display", "none")
    	});
        	
        	$("#info-form-button").click(function(){
        		if(validForm() === false){
        			
        		}else {    				
	        		$(".info-form").css("display", "none")
	        		$("#info-payment").css("display", "")
	        		$("#info-payment-button").css("display", "")
	        		$(".back-to-prev").css("display", "")
	        		$(".form-submit").css("display", "")		
        		}     		
        	});
        	
        	//check password repeat
        	$("#check-repeat-pwd").blur(function(){
        		let user_pwd = $("input[name=user_pwd]").val();
            	let re_enter_pwd = $("input[name=re_enter_pwd]").val();
            	if(re_enter_pwd != user_pwd){
            		$("#errorMsgUserRePwd").text("＊密碼不符")
        			$("input[name=re_enter_pwd]").focus()
        			$("input[name=re_enter_pwd]").addClass("errormsg")
            	}
        	})
        	
        	//check email repeat with ajax
        	$("#check-repeat-email").blur(function(){
	        	$.ajax({
	       		url: "SelectEmail.jsp",
	       		type: "GET",
	       		success: function(data){
	       			checkEmail(data)
	       		}
	        	});      		
        	});
        	
        	function checkEmail(data){
        		let emailArr = data.trim().split(",");
        		let email = $("input[name=email]").val();
	   			for(i in emailArr){
	   				if(emailArr[i] === email){
		   				$("#errorMsgEmail").text("＊此電子信箱已被註冊")
		   				$("input[name=email]").focus()
	        			$("input[name=email]").addClass("errormsg")
	   				}
	   			}  		
        	}
        	function validForm(){
        		let name = $("input[name=name]").val();
        		let user_id = $("input[name=user_id]").val();
        		let user_pwd = $("input[name=user_pwd]").val();
        		let personal_id= $("input[name=personal_id]").val();
        		if(name.trim() === ""){
        			$("#errorMsgName").text("＊此欄位不得為空")
        			$("input[name=name]").focus()
        			$("input[name=name]").addClass("errormsg")
	        		return false;
        		}
        		if(user_id.trim() === ""){
        			$("#errorMsgUserId").text("＊此欄位不得為空")
        			$("input[name=user_id]").focus()
        			$("input[name=user_id]").addClass("errormsg")
	        		return false;
        		}
        		if(user_pwd.trim() === ""){
        			$("#errorMsgUserPwd").text("＊此欄位不得為空")
        			$("input[name=user_pwd]").focus()
        			$("input[name=user_pwd]").addClass("errormsg")
	        		return false;
        		}
        		if(isValidID(personal_id.trim()) === false){
        			$("#errorMsgID").text("＊身分證資料有誤")
        			$("input[name=personal_id]").focus()
        			$("input[name=personal_id]").addClass("errormsg")
        			return false;
        		}
        	}
        	
        	// personal-id valid or not    	
        	function isValidID(str){
			  if (str === "Y10000001") return true
			  if (str.length !== 10) return false
			  if (!(str[0] >= "A" && "Z" >= str[0])) return false
			  
			  let n = alphaToNumber(str[0])
			  let n1 = Math.floor(n / 10)
			  let n2 = n % 10
			  
			  let sum = n1*1 + n2*9
			  for (i = 1; i < str.length - 1; i++){
			   sum += str[i] * (9 - i)
			  }
			  sum += Number(str[9])
			  
			  return sum % 10 === 0
			}
				
			function alphaToNumber(s){
			  let scope = {
			    A : 10, B : 11, C : 12, D : 13, E : 14, F : 15,
			    G : 16, H : 17, I : 34, J : 18, K : 19, M : 21,
			    N : 22, O : 35, P : 23, Q : 24, T : 27, U : 28,
			    V : 29, W : 32, X : 30, Z : 33, L : 20, R : 25,
			    S : 26, Y : 31
			  }
			  return scope[s] // scope["A"] = 10
			}
        	    	
        //input detect typing to remove the error class
      	$("input[name=name]").keydown(function (){
      		$("#errorMsgName").text("")
			$("input[name=name]").removeClass("errormsg")
  		})
  		
  		$("input[name=user_id]").keydown(function (){
      		$("#errorMsgUserId").text("")
			$("input[name=user_id]").removeClass("errormsg")
  		})
  		
  		$("input[name=user_pwd]").keydown(function (){
      		$("#errorMsgUserPwd").text("")
			$("input[name=user_pwd]").removeClass("errormsg")
  		})
  		
  		$("input[name=re_enter_pwd]").keydown(function (){
      		$("#errorMsgUserRePwd").text("")
			$("input[name=re_enter_pwd]").removeClass("errormsg")
  		})
  		
  		$("input[name=email]").keydown(function (){
      		$("#errorMsgEmail").text("")
			$("input[name=email]").removeClass("errormsg")
  		})
  		
  		$("input[name=personal_id]").keydown(function (){
      		$("#errorMsgID").text("")
			$("input[name=personal_id]").removeClass("errormsg")
  		})
        	    	
  </script>
</body>
</html>