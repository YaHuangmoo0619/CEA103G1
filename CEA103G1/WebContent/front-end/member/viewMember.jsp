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
 	border:none; 
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
</style>

</head>
<body onload="connection()">
<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
<div class="box">
	<div class= "left">
		<ul> 
			<li><a href="<%=request.getContextPath() %>/back-end/place_order/PresentPlace_order.jsp">營位訂單管理</a></li>
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
			<form method="post" >
				<div class="infoRow">
					<label for="mbr_no">會員編號：</label><input type="text" id="mbr_no" name="mbr_no" value="${memberVO.mbr_no}" readonly>
					<label for="rank_no">會員等級：</label><input type="text" id="rank_no" value="${member_rankSvc.getOneMember_rank(memberVO.rank_no).getRank_name()}" readonly>
				</div>
				<div class="infoRow">
					<label for="acc">帳&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;號：</label><input type="text" id="acc" name="acc" value="${memberVO.acc}" readonly>
					<label for="pwd">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;碼：</label><input type="text" id="pwd" name="pwd" value="${memberVO.pwd}" readonly>
				</div>
				<div class="infoRow">
					<label for="name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label><input type="text" id="name" name="name" value="${memberVO.name}" readonly>
					<label for="id">身&nbsp;&nbsp;份&nbsp;證：</label><input type="text" id="id" name="id" value="${memberVO.id}" readonly>
				</div>
				<div class="infoRow">
					<label for="bday">生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日：</label><input type="text" id="bday" name="bday" value="${memberVO.bday}" readonly>
					<label for="sex">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;別：</label><input type="text" id="sex" value="${memberVO.sex == 1?'女性':'男性'}" readonly>
				</div>
				<div class="infoRow">
					<label for="mobile">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;機：</label><input type="text" id="mobile" name="mobile" value="${memberVO.mobile}" readonly>
					<label for="mail">信&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</label><input type="text" id="mail" name="mobile" value="${memberVO.mail}" readonly>
				</div>
				<div class="infoRow">
					<label for="pt">點&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;數：</label><input type="text" id="pt" name="pt" value="${memberVO.pt}" style="width:10.5em;" readonly>
					<label for="exp">會員經驗值：</label><input type="text" id="exp" name="exp" value="${memberVO.exp}" style="width:11.5em;" readonly>
				</div>
				<div class="infoRow">
					<label>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</label>
					<input type="text" name="city" value="${memberVO.city}" style="width:4em;" readonly>
					<input type="text" name="dist" value="${memberVO.dist}" style="width:4em;" readonly>
					<input type="text" name="add" value="${memberVO.add}" style="width:19.5em;" readonly>
				</div>
				<div class="infoRow">
					<label>信用卡卡號：</label>
					<input type="text" name="card" value="${memberVO.card}" style="width:31em;" readonly>
				</div>
				<input type="hidden" name="rank_no" value="${memberVO.rank_no}">
				<input type="hidden" name="sex" value="${memberVO.sex}">
				<input type="hidden" name="join_time" value="${memberVO.join_time}">
				<input type="hidden" name="acc_stat" value="${memberVO.acc_stat}">
				<input type="hidden" name="rmk" value="${memberVO.rmk}">
				<input type="hidden" name="action" value="getOne_For_Update">
				<div class="infoRow">
					<input type="submit" value="修改會員資料" class="confirm">
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
	$("input").focus(function(){
		$("input").css("outline","none");
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
</body>
</html>