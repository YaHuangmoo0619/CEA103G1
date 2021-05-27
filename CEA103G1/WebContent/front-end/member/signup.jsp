<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>


<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<% 
  java.sql.Date bday = null;
  try {
	    bday = memberVO.getBday();
   } catch (Exception e) {
	    bday = new java.sql.Date(System.currentTimeMillis());
   }
%>
<!-- <script src="https://cdn.jsdelivr.net/npm/tw-city-selector@2.1.1/dist/tw-city-selector.min.js"></script> -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/signup.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
    <title>Campion��a | ���U�|��</title>
    
   
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
	<a href="<%=request.getContextPath()%>/front-end/front-index.jsp">�^����</a>
	<section id="Steps" class="steps-section">
    <h2 class="steps-header">���U����Campion��a�|��</h2>

  </section>
<form method="post"
		action="<%=request.getContextPath()%>/member/member.do" name="form1"
		enctype="multipart/form-data">
      <div class="title">
      </div>
      <div class="row info-form">
        <h3>�򥻸��</h3>
        <div class="input-group input-group-icon">
        		<div class="input-name">�m�W�G</div>
          <input type="text" name="name" minlength="1" maxlength="12" />
          <div class="input-icon"><i class="fal fa-id-card-alt"></i></div>
          	<p id="errorMsgName" style="font-size:2px; color:red; margin-left:105px;"></p>
        </div>
        <div class="input-group input-group-icon">
        		<div class="input-name">�b���G</div>
          <input type="text" name="acc" minlength="1" />
          <div class="input-icon"><i class="fal fa-user-circle"></i></div>
          	<p id="errorMsgUserId" style="font-size:2px; color:red; margin-left:105px;"></p>
        </div>
        <div class="input-group input-group-icon">
        		<div class="input-name">�K�X�G</div>
          <input type="password" name="pwd" minlength="1" maxlength="12" />
          <div class="input-icon"><i class="fal fa-user-lock"></i></div>
          	<p id="errorMsgUserPwd" style="font-size:2px; color:red; margin-left:105px;"></p>
        </div>
        <div class="input-group input-group-icon">
        		<div class="input-name">�K�X�T�{�G</div>
          <input id="check-repeat-pwd" type="password" name="re_enter_pwd"/>
          <div class="input-icon"><i class="fal fa-lock-alt"></i></div>
          	<p id="errorMsgUserRePwd" style="font-size:2px; color:red; margin-left:105px;"></p>
        </div>
        <div class="input-group input-group-icon">
        		<div class="input-name">�q�l�H�c�G</div>
          <input id="check-repeat-email" type="email" name="mail" />
          <div class="input-icon"><i class="fal fa-envelope"></i></div>
          	<p id="errorMsgEmail" style="font-size:2px; color:red; margin-left:105px;"></p>
        </div>
        <div class="input-group input-group-icon">
        		<div class="input-name">������X�G</div>
          <input type="text" name="mobile" />
          <div class="input-icon"><i class="far fa-phone-alt"></i></div>
        </div>
        <div class="input-group input-group-icon">
        		<div class="input-name">�X�ͦ~���G</div>
          <input id="bday" type="text" name="bday"/>
          <div class="input-icon"><i class="far fa-birthday-cake"></i></div>
        </div>
        <div class="input-group input-group-icon">
        		<div class="input-name">�����Ҧr���G</div>
          <input type="text" name="id" />
          <div class="input-icon"><i class="fal fa-id-card"></i></div>
          <p id="errorMsgID" style="font-size:2px; color:red; margin-left:105px;"></p>
        </div>
        
        <div class="input-group input-group-icon">
        <table style= "margin-left:58px;">		
        	<tr>
				<td>����    :  </td>
				<td><select id="city" name="city" style="width:120px; color:#000000;  "></select></td>
				<td>�ϰ�:</td>
				<td><select id="dist" name="dist" style="width:120px; color:#000000;  "></select></td>
			</tr>
        </table>
        <div class="input-group input-group-icon">
        		<div class="input-name">�a�}�G</div>
          <input type="text" id="add" >
          <input type="hidden" name="add" />
          <div class="input-icon"><i class="fas fa-home"></i></div>
        </div>
        </div>


      <div class="row info-form">
        <div class="col-half">
          <h3>�ʧO</h3>
          <div class="input-group">
            <input type="radio" name="gender" value="1" id="gender-male"/>
            <label for="gender-male">�k</label>
            <input type="radio" name="gender" value="2" id="gender-female"/>
            <label for="gender-female">�k</label>
          </div>
        </div>
      </div>

      <div class="row info-form" id="info-form-button"> <input type="hidden" name="action" value="register_Member">
      	<button  class="next-step">���U</button>
      </div>

    </form>
    
    
    	<script>
		$.datetimepicker.setLocale('zh');
		$(function() {
			$('#announce_date').datetimepicker({
				format : 'Y-m-d',
				minDate : '-1970/01/01',
				timepicker : false
			});

		});
	</script>

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="<%=request.getContextPath()%>/front-end/member/city_dist.js"></script>
<script type="text/javascript">
 window.onload = function () {
		 AddressSeleclList.Initialize('city', 'dist');
		
   };
</script>
    
    
  </div>
  	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
  	<script src="https://unpkg.com/imask"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/front-end/signup.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
    <script src="<%=request.getContextPath()%>/build/js/countrySelect.min.js"></script>
 <script>
        $.datetimepicker.setLocale('zh');
        $('#bday').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=bday%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
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
            		$("#errorMsgUserRePwd").text("���K�X����")
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
		   				$("#errorMsgEmail").text("�����q�l�H�c�w�Q���U")
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
        			$("#errorMsgName").text("������줣�o����")
        			$("input[name=name]").focus()
        			$("input[name=name]").addClass("errormsg")
	        		return false;
        		}
        		if(user_id.trim() === ""){
        			$("#errorMsgUserId").text("������줣�o����")
        			$("input[name=user_id]").focus()
        			$("input[name=user_id]").addClass("errormsg")
	        		return false;
        		}
        		if(user_pwd.trim() === ""){
        			$("#errorMsgUserPwd").text("������줣�o����")
        			$("input[name=user_pwd]").focus()
        			$("input[name=user_pwd]").addClass("errormsg")
	        		return false;
        		}
        		if(isValidID(personal_id.trim()) === false){
        			$("#errorMsgID").text("�������Ҹ�Ʀ��~")
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