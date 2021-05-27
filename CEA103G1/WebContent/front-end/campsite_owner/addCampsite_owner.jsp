<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite_owner.model.*"%>

<%
	Campsite_ownerVO campsite_ownerVO = (Campsite_ownerVO) request.getAttribute("campsite_ownerVO");
%>



<html>
<head>
<meta charset="BIG5">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<title>register</title>
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
</style>

</head>
<body bgcolor='white'>
	<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
	<div id="addcso">
<table id="table-1">
	<tr><td>
		 <h3>註冊營主</h3></td><td>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form class="form" method="post" action="<%=request.getContextPath()%>/campsite_owner/campsite_owner.do" enctype="multipart/form-data">
<table>
	<tr>
		<td>大頭照</td>
		<td>
			<input type="file" id="sticker" name="sticker">
			<div class="container">
				<img id="showsticker" style="width:100px;height:100px;">
			</div>
		</td>
	</tr>
	<tr>
		<td>姓名</td>
		<td><input type="TEXT" name="name" id="name" size="45" value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getName()%>" required></td>
	</tr>	
	<tr>
		<td>帳號</td>
		<td><input type="TEXT" name="acc" id="acc" size="45"  value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getAcc()%>" required></td>
	</tr>
	<tr>
		<td>密碼</td>
		<td><input type="password" name="pwd" id="pwd" size="45" required></td>
	</tr>
	<tr>
		<td>確認密碼</td>
		<td><input type="password" name="pwd" id="confirm_pwd" size="45" required></td>
	</tr>
	<tr>
		<td>身分證字號</td>
		<td><input type="TEXT" name="id" id="id" size="45"  value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getId()%>" required></td>
	</tr>
	<tr>
		<td>生日</td>
		<td><input  name="bday" id="bday" type="text" value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getBday()%>" required></td>
	</tr>
	<tr>
		<td>性別</td>
		<td>
			<input  type="radio" name="sex" value="0" <%=(campsite_ownerVO == null) ? "" : (campsite_ownerVO.getSex() == 0 ? "checked" : "")%> required>男
			<input  type="radio" name="sex" value="1" <%=(campsite_ownerVO == null) ? "" : (campsite_ownerVO.getSex() == 1 ? "checked" : "")%>>女
		</td>
	</tr>
	<tr>
		<td>手機</td>
		<td><input type="tel" name="mobile" id="mobile" size="45"value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getMobile()%>" required></td>
	</tr>
	<tr>
		<td>電子信箱</td>
		<td><input type="email" name="mail" id="mail" size="45"value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getMail()%>" required></td>
	</tr>
	<tr>
		<td>地址</td>
		<td>
			<div id="twzipcode"></div>
			<input type="TEXT" name="address" id="address" size="45" value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getAdd()%>" required>
		</td>
	</tr>
	<tr>
		<td>銀行代碼</td>
		<td><input type="TEXT" name="bank_no" id="bank_no" size="45" value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getBank_no()%>" required></td>
	</tr>
	<tr>
		<td>銀行帳戶</td>
		<td><input type="TEXT" name="bank_acc" id="bank_acc" size="45" value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getBank_acc()%>" required></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<div style="margin-left:38%;"><div style="display:inline-block;"><input type="submit" class="confirm" value="送出"></div>
<div style="display:inline-block;"><input type="reset" class="confirm" value="重置"></div></div>
</FORM></div>
<%@ include file="/part-of/partOfCampion_frontTop_js.txt"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="/CEA103G1/datetimepicker/jquery.js"></script>
<script type="text/javascript" language="javascript" src="/CEA103G1/datetimepicker/jquery.datetimepicker.full.js" charset="UTF-8"></script>
<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
<script src="<%=request.getContextPath()%>/twzipcode/old/jquery.twzipcode-1.4.1.min.js"></script>
<script>
$('#twzipcode').twzipcode();
$.datetimepicker.setLocale('zh'); // kr ko ja en
$('#bday').datetimepicker({
   theme: '',          //theme: 'dark',
   timepicker: false,   //timepicker: false,
   step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
   format: 'Y-m-d',
//    value: default1,
//        disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   startDate:	        '1990-01-01',
//    minDate:           '-1969-12-31', // 去除今日(不含)之前
   maxDate:           '+1970-01-01'  // 去除今日(不含)之後
});
$('#sticker').on('change', function(e) {
	const file = this.files[0];
	const fr = new FileReader();
	fr.onload = function(e) {
		$('#showsticker').attr('src', e.target.result);
	};
	fr.readAsDataURL(file);
});
$('#confirm_pwd').change(function(){
	if(!($('#confirm_pwd').val() === $('#pwd').val())){
		$('br').before(`<li style="color:red">請再確認密碼</li>`);
	}else{
		$('#reset').attr("disabled",false);
	}	
})
</script>
</body>
</html>