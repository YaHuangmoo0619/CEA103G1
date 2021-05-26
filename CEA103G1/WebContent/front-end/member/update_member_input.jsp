<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*" %>
<%@ page import="com.member.model.*" %>

<jsp:useBean id="member_rankSvc" class="com.member_rank.model.Member_rankService"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<title>所有會員站內信列表</title>
<%@ include file="/part-of/partOfCampion_frontTop_css.txt"%>
<%-- <%@ include file="/part-of/partOfCampion_backLeft_css.txt"%> --%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
<style>
/*
*{
	border: solid 1px black;
}
*/
body{
/*   	background-color: #fff;   */
   	background-color: #4e5452;
	color: #4e5452;
	height:55em;
}

div.box{
	display:flex;
	justify-content: center;
 	height:100%; 
}

div.left{
	margin-top:3em; 
}
ul {
	font-size: 1.2em;
	font-weight: 888;
}
li {
	list-style: none;
	padding: 10px 0px;
}
a {
	text-decoration: none;
	color: #80c344;
	margin: 10px;
}
	
a:hover {
	text-decoration: none;
	color: #eee;
}

div.right{
	width:70%;
	height:90%;
 	margin-top:3em; 
 	margin-left:5em; 
 	border-radius: 5px;
 	background-color: #fff;
 	position:relative;
}
div.photo{
	width:10em;
	height:10em;
	margin: 4em auto;
	border-radius: 50%;
 	overflow: hidden; 
	display:flex;
	justify-content: center;
}
div.photo:hover{
	cursor: pointer;
}
img.personBig{
/* 	width:100%; */
}
#changePic{
	position:absolute;
	right: 60%;
	top: 27%;
}
img.camera{
	width: 2em;
}
img.camera:hover{
	cursor: pointer;
}

form{
	width:80%;
	margin: 0 auto;
}
div.infoRow{
	margin-top: 1em;
	text-align: center;
}
input{
/*  	border:none;  */
	margin-left: 1em;
}
label{
	margin-left: 1em;
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

#preview{
	display:flex;
	justify-content: center;
}
label{
	font-weight:555;
}
</style>

</head>
<body onload="connection()">
<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
<div class="box">
	<div class= "left">
		<ul> 
			<li><a href="<%=request.getContextPath() %>/front-end/place_order/listAllPlace_order.html">營位訂單管理</a></li>
			<li><a href="<%=request.getContextPath() %>/front-end/campsite_collection/listAllCollection.html">營區收藏管理</a></li>
			<li><a href="<%=request.getContextPath() %>/back-end/product_category/select_page.jsp">商城訂單管理</a></li>
			<li><a href="<%=request.getContextPath() %>/back-end/article/select_page.jsp">論壇資訊管理</a></li>
			<li><a href="">修改會員資料</a></li>
		</ul>
	</div>
	<div class="right">
		<div class="photo">
			<img src="<%=request.getContextPath() %>/member/GetPhoto?mbr_no=${memberVO.mbr_no}" class="personBig" onclick="changePic()">
		</div>
		<div id="changePic">
			<img src="<%=request.getContextPath() %>/images/camera-outline.svg" class="camera" title="更新大頭照" onclick="changePic()">
		</div>
		<div class="info">
<%-- 			${errorMsgs.acc}${errorMsgs.pwd}${errorMsgs.name}${errorMsgs.id}${errorMsgs.bdayStr}${errorMsgs.mobile}${errorMsgs.mail}${errorMsgs.city}${errorMsgs.dist}${errorMsgs.add}${errorMsgs.card} --%>
			${errorMsgs}
			<form method="post" action="<%=request.getContextPath()%>/MemberChangeInfo">
				<div class="infoRow">
					<label for="mbr_no">會員編號：</label><input type="text" id="mbr_no" name="mbr_no" value="${memberVO.mbr_no}" style="width:3em;border:none;" readonly>
					<label for="rank_no">會員等級：</label><input type="text" id="rank_no" value="${member_rankSvc.getOneMember_rank(memberVO.rank_no).getRank_name()}" style="width:5em;border:none;" readonly>
					<label for="pt">點&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;數：</label><input type="text" id="pt" name="pt" value="${memberVO.pt}" style="width:2em;border:none;" readonly>
					<label for="exp">會員經驗值：</label><input type="text" id="exp" name="exp" value="${memberVO.exp}" style="width:2em;border:none;" readonly>
				</div>
				<div class="infoRow">
					<label for="name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label><input type="text" id="name" name="name" value="${memberVO.name}">
					<label for="id">身&nbsp;&nbsp;份&nbsp;證：</label><input type="text" id="id" name="id" value="${memberVO.id}">
				</div>
				<div class="infoRow">
					<label for="acc">帳&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;號：</label><input type="text" id="acc" name="acc" value="${memberVO.acc}" style="width:6em;">
					<label for="pwd">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;碼：</label><input type="password" id="pwd" name="pwd" value="${memberVO.pwd}" style="width:5em;">
					<label for="pwd2">再次輸入密碼：</label><input type="password" id="pwd2" style="width:5em;">
				</div>
				<div class="infoRow">
					<label for="bday">生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日：</label><input type="text" id="bday" name="bday" value="${memberVO.bday}">
					<label for="sex">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;別：</label>
					<input type="radio" id="sexM" name="sex" value="0" ${memberVO.sex == 0?'checked':'' }>
					<label for="sexM">男&nbsp;&nbsp;&nbsp;&nbsp;性</label>
					<input type="radio" id="sexF" name="sex" value="1" ${memberVO.sex == 1?'checked':'' }>
					<label for="sexF">女&nbsp;&nbsp;&nbsp;&nbsp;性</label>
				</div>
				<div class="infoRow">
					<label for="mobile">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;機：</label><input type="text" id="mobile" name="mobile" value="${memberVO.mobile}">
					<label for="mail">信&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</label><input type="text" id="mail" name="mail" value="${memberVO.mail}">
				</div>
				<div class="infoRow">
					<label>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</label>
					<select size="1" name="city" id="city">
						<option value="no">請選擇縣市
					</select>
					<select size="1" name="dist" id="dist" style="width:5em;"></select>
					<input type="text" name="add" value="${memberVO.add}" style="width:19.5em;">
				</div>
				<div class="infoRow">
					<label>信用卡卡號：</label>
					<input type="text" name="card" value="${memberVO.card}" style="width:31em;">
				</div>
				<input type="hidden" name="rank_no" value="${memberVO.rank_no}">
				<input type="hidden" name="join_time" value="${memberVO.join_time}">
				<input type="hidden" name="acc_stat" value="${memberVO.acc_stat}">
				<input type="hidden" name="rmk" value="${memberVO.rmk}">
				<input type="hidden" name="action" value="update_info">
				<div class="infoRow">
					<input type="submit" value="送出修改" class="confirm">
				</div>
			</form>
		</div>
	</div>
</div>	

		<div class="modal" tabindex="-1" role="dialog" id="Modal">
		     <div class="modal-dialog" role="document"> 
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title">照片預覽</h5>
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button> 
		            </div>
		            <div class="modal-body">
						<div id="preview"></div>
		            </div>
		            <div class="modal-footer">
		            	<form method="post" action="<%=request.getContextPath()%>/member/GetPhoto" enctype="multipart/form-data" style="text-align:right;">
							<input type="file" id="file-upload-button" name="sticker" style="display:none;">
							<input type="hidden" name="mbr_no" value="${memberVO.mbr_no}">
							<input type="hidden" name="action" value="updatePic">
							<input type="submit" class="btn btn-secondary" value="確定上傳">
						</form>
		            </div>
		       </div>
		   </div>
		</div>


<%@ include file="/part-of/partOfCampion_frontTop_js.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
<script>
	$("#mbr_no").focus(function(){
		$("#mbr_no").css("outline","none");
	});
	$("#rank_no").focus(function(){
		$("#rank_no").css("outline","none");
	});
	$("#pt").focus(function(){
		$("#pt").css("outline","none");
	});
	$("#exp").focus(function(){
		$("#exp").css("outline","none");
	});
</script>
<script>
function changePic(event){
	document.getElementById('file-upload-button').value=''; //為了能夠上傳同一張照片
	let e = document.createEvent("MouseEvent");
	e.initEvent("click",true,true);
	document.getElementById('file-upload-button').dispatchEvent(e);
}
</script>
<script>
let myFile = document.getElementById('file-upload-button');
let input = document.getElementById('preview');
let imgs = document.getElementsByClassName('img');
myFile.addEventListener('change',function(e){
	let files = e.target.files;
	for(let i = 0; i < files.length; i++){
		if(files[0].type.indexOf('image') > -1 && (files[0].size)/1024/1024 < 1){
			fileName = files[0].name;
// 			alert(files[0].size/1024/1024+"MB");
			let reader = new FileReader();					
			reader.addEventListener('load', function(e){
				let result = e.target.result;			
				let img = document.createElement('img');
				img.setAttribute('class', 'img');
				img.style.width='200px';
				img.src = result;
				input.append(img);
			});
			reader.readAsDataURL(files[i]);
			if(imgs[0] !== undefined){ //不同檔案時執行
				imgs[0].remove();
			}
			showModal(); //彈出預覽圖燈箱
		}else{
			alert('請上傳1MB以內的圖檔');
		}
	}
});
</script>
<script>
function showModal() {
	 $('#Modal').modal('show'); 
}
</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
// 	console.log('${memberVO.city}');
	$.ajax({
		type: "POST",
		url: '<%=request.getScheme()%>://'+'<%=request.getServerName()%>'+':'+'<%=request.getServerPort()%>'+'<%=request.getContextPath()%>'+'/MemberGetDistrict',
		data: {action:"city"},
		dataType: "json",
// 		scriptCharset: 'big5',
		success: function(data){
// 			console.log(Object.values(data).length);
			for(let i = 0; i < Object.values(data).length; i++){
// 				console.log(Object.values(data)[i] + "/" +'${memberVO.city}');
				if(Object.values(data)[i] === '${memberVO.city}'){
					$('#city').append("<option value=\""+ Object.values(data)[i] +"\" selected>"+ Object.values(data)[i] +"</option>");
				}else{
					$('#city').append("<option value=\""+ Object.values(data)[i] +"\">"+ Object.values(data)[i] +"</option>");
				}
			}
			$.ajax({
				type: "POST",
				url: '<%=request.getScheme()%>://'+'<%=request.getServerName()%>'+':'+'<%=request.getServerPort()%>'+'<%=request.getContextPath()%>'+'/MemberGetDistrict',
				data: {action:"dist",city:$('#city').find("option:selected").text()},//取得選取的縣市文字
				dataType: "json",
//		 		scriptCharset: 'big5',
				success: function(data){
//		 			console.log(Object.values(data).length);
					if($('#dist').find("*")!==null||$('#dist').find("*")!==undefined){
						$('#dist').find("*").remove();
					}
					
					for(let i = 0; i < Object.values(data).length; i++){
						if(Object.values(data)[i] === '${memberVO.dist}'){
							$('#dist').append("<option value=\""+ Object.values(data)[i] +"\" selected>"+ Object.values(data)[i] +"</option>");
						}else{
							$('#dist').append("<option value=\""+ Object.values(data)[i] +"\">"+ Object.values(data)[i] +"</option>");
						}
					}
				}
			});
		}
	});
});

$('#city').change(function(){
// 	console.log($('#city').find("option:selected").text());
	$.ajax({
		type: "POST",
		url: '<%=request.getScheme()%>://'+'<%=request.getServerName()%>'+':'+'<%=request.getServerPort()%>'+'<%=request.getContextPath()%>'+'/MemberGetDistrict',
		data: {action:"dist",city:$('#city').find("option:selected").text()},//取得選取的縣市文字
		dataType: "json",
// 		scriptCharset: 'big5',
		success: function(data){
// 			console.log(Object.values(data).length);
			if($('#dist').find("*")!==null||$('#dist').find("*")!==undefined){
				$('#dist').find("*").remove();
			}
			
			for(let i = 0; i < Object.values(data).length; i++){
				if(Object.values(data)[i] === '${memberVO.dist}'){
					$('#dist').append("<option value=\""+ Object.values(data)[i] +"\" selected>"+ Object.values(data)[i] +"</option>");
				}else{
					$('#dist').append("<option value=\""+ Object.values(data)[i] +"\">"+ Object.values(data)[i] +"</option>");
				}
			}
		}
	});
});

$('#acc').keyup(function(e){
	console.log(e);
	console.log($(this).val());
	
	$.ajax({
		type: "POST",
		url: '<%=request.getScheme()%>://'+'<%=request.getServerName()%>'+':'+'<%=request.getServerPort()%>'+'<%=request.getContextPath()%>'+'/member/MemberCheck',
		data: {action:"checkAcc",acc:$(this).val()},
		dataType: "json",
		success: function(data){
// 			alert('${memberVO.acc}');
			
			if($('#acc').val() !== '${memberVO.acc}' && data.acc === "duplicate"){
				$('#acc').css("outline-color","red");
			}else if(data.acc === "notFound" && $('#acc').val().trim() !== ''){
				$('#acc').css("outline-color","#80c344");
			}else{
				$('#acc').css("outline-color","black");
			}
		}
	});
});

$('#pwd2').keyup(function(e){
	console.log($('#pwd').val() !== $('#pwd2').val());
	$('#pwd').css("outline-color","red");
	if($('#pwd').val() !== $('#pwd2').val()){
		$('#pwd').css("outline-color","red");
		$('#pwd').css("border-color","red");
	}else if($('#pwd').val() === $('#pwd2').val()){
		$('#pwd').css("outline-color","#80c344");
		$('#pwd').css("border-color","#80c344");
	}else{
		$('#pwd').css("outline-color","black");
		$('#pwd').css("border-color","black");
	}
});


</script>

</body>
</html>