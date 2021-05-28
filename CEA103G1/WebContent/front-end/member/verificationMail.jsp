<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="BIG5">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<title>驗證成功</title>
<%@ include file="/part-of/partOfCampion_frontTop_css.txt"%>
<link   rel="stylesheet" type="text/css" href="/CEA103G1/datetimepicker/jquery.datetimepicker.css" />

<style>
body{
	 background-color: #4e5452;
}
div.left{
	margin-top: 20px;
}
#addcso{
width:60%;
	background-color: #fff;
	margin-top: 40px;
	margin-left: 20%;
	padding: 30px 30px;
	border-radius: 5px;
}
input.confirm{
	width: 100px;
	height: 50px;
	background-color: #33CCFF;
	color: #0088A8;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}
input.confirm:hover{
	background-color: #0088A8;
	color: 	#33CCFF;
	cursor: pointer;
}
a.content{
	color: #80c344;
	font-size: 0.6em;
}
a.content:hover {
	color: #4B7F52;
}

input.confirm {
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}

input.confirm:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}

#confirmTop:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}

div.forSearchs{
	margin: 0 auto;
	width: 70%;
	hieght: 50px;
	position: relative;
}
div.forSearchsMore{
	top: 110%;
	left: 15%;
	width: 70%;
	position: absolute;
	background-color: #fff;
	box-shadow: 0 1px 5px 0 #4e5452;
	display: none
}

#mail_cont{
	border-radius:5px;
	background-color:#eee;
	border:none;
	padding:5px 15px;
	width:50%;
}

span{
	 font-size:0.8em;
	 font-weight:444;
	 padding: 7px;
	 background-color: #eee;
	 border-radius:5px;
}
span:hover{
	cursor: pointer;
	background-color: #4e5452;
	color: #eee;
}

label, select, input {
	font-size: 0.8em;
}

table{
	width: 700px;
	margin: 30px auto;
/* 	border: 1px solid #4e5452; */
}
th, td{
	text-align: left;
/* 	border: 1px solid #4e5452; */
	padding: 10px 15px;
}
td.function{
	text-align: justify;	
}
label.spotlight{
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}
input.change{
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}
input.change:hover{
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}
#focus{
	margin-right: -5px;
}

tr {
/* 	border-top: 1px solid #eee; */
	border-bottom: 2px solid #eee;
}
.form .btn-group {
            font-size: 0;
            margin-top: 30px;
        }
        .form .btn {
            font-size: 17px;
            border-radius: 5px;
            border: none;
            background-color: #239ad1;
            width: 250px;
            padding: 10px 0;
            color: #fff;
            font-family: '微軟正黑體', sans-serif;
        }
        .form .btn+.btn {
            margin-left: 20px;
        }
        button:hover {
            background: linear-gradient(#6ab4fe, #6ab4fe);
        }
</style>
</head>
<body bgcolor='white'>
	<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
	<div id="addcso">
<table id="table-1">
	<tr>
		<td><h2 align="center">帳號驗證成功</h2></td>
	</tr>
</table>

</head>
<body>

	<br>
	<c:if test="${not empty errorMsgs}">
		<c:forEach var="message" items="${errorMsgs}">
			<h4 align="center" style="color: red">${message}</h4>
		</c:forEach>
		<script>
// 					if(${not empty errorMsgs}){
// 						console.log("AAAAAA");
// 						$('#successVerify').hide();
// 					} else {
// 						console.log("FFFFFFFFF");
// 						$('#failureVerify').show();	
// 					}
					
// 					console.log($('#successVerify'));
// 					console.log($('#failureVerify'));
								
				</script>
	</c:if>
	<c:if test="${empty errorMsgs}">
		<h4 align="center"></h4>
	</c:if>
	
	<button id="successVerify"
		class="btn btn-lg btn-primary btn-block btn-signin"
		onclick="location='<%=request.getContextPath()%>/front-end/member/login.jsp'">返回登入頁
	</button>
	<br>
	<button id="failureVerify"
		class="btn btn-lg btn-primary btn-block btn-signin"
		onclick="location='<%=request.getContextPath()%>/front-end/member/unverification.jsp?mail=${param.mail}'">重寄驗證信
	</button>
	
<!-- 	<div class="btn-group"> -->
<!--                 <button class="btn">登入</button>   -->
<!--                 <input type="hidden" name="action" value="login_Member"> -->
<!--             </div> -->


			<script>
					if(${empty errorMsgs}){
						$('#failureVerify').hide();	
					}else if(${not empty errorMsgs}) {
						$('#successVerify').hide();
					}
			</script>



</body>
</html>