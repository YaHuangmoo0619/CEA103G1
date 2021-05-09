<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.member_mail.model.*" %>

<% Member_mailVO member_mailVO = (Member_mailVO)request.getAttribute("member_mailVO"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>新增信件</title>
<%@ include file="/part-of/partOfCampion_backTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_backLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
<style>
body{
	background-color: #4e5452;
	color: #4e5452;
}
div.left{
	margin-top: 20px;
}
div.right{
	background-color: #fff;
	margin-top: 40px;
	padding: 50px 50px;
	border-radius: 5px;
}
a.content{
	color: #80c344;
	font-size: 0.6em;
}
a.content:hover {
	color: #4B7F52;
}

table{
	width: 700px;
	margin: 30px auto;
	border: 1px solid #4e5452;
}
th, td{
	text-align: center;
	border: 1px solid #4e5452;
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
form{
	text-align: center;
}
textarea{
	resize: none;
}
input.confirm{
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
	margin: 0px 10px;
}
input.confirm:hover{
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}
</style>
<style>
#container {
	padding: 10px;
	max-width: 250px;
	margin: 0px auto;
}
.align{
	display: inline;
	vertical-align: text-top;
}
#preview, .change{
	margin: 10px 0px;
	
}
img{
	max-width: 100%;
	margin: 10px;
}
.delete{
	display: none;
}
</style>
</head>
<body>
<%@ include file="/part-of/partOfCampion_backTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
<div class="container">
	<div class="row">
		<div class= "left col-3">
		<%@ include file="/part-of/partOfCampion_backLeft_body.txt"%></div>
		<div class="right col-9">
			<h2>新增信件&nbsp;<a class="content" href="<%=request.getContextPath()%>/back-end/service_mail/listAllService_mail.jsp">回會員信件列表</a></h2>
			<hr>
			<h5 style="color:#80c344;">${errorMsgs.notFound[0]}${errorMsgs.exception[0]}</h5>
			<h3>信件撰寫:</h3>
			<form method="post" action="<%=request.getContextPath()%>/member_mail/member_mail.do" enctype="multipart/form-data">
			<jsp:useBean id="member_mailSvc" class="com.member_mail.model.Member_mailService"/>
			<jsp:useBean id="employeeSvc" class="com.employee.model.EmployeeService"/>
			<jsp:useBean id="campsite_ownerSvc" class="com.campsite_owner.model.Campsite_ownerService"/>
			<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
			<table>
				<tr>
					<td>
						<label for="send_no">寄件人</label>
						<br><h5 style="color:#80c344;">${errorMsgs.send_no[0]}</h5>
					</td>
					<td>
						<select size="1" name="send_no" id="send_no">
						<option value="99">--請選擇--</option>
						<c:forEach var="memberVO" items="${memberSvc.all}">
							<option value="${memberVO.mbr_no}" ${memberVO.mbr_no == param.send_no? 'selected':''}>${memberVO.mbr_no}${memberVO.name}</option>
						</c:forEach>
						<c:forEach var="campsite_ownerVO" items="${campsite_ownerSvc.all}">
							<option value="${campsite_ownerVO.cso_no}" ${campsite_ownerVO.cso_no == param.rcpt_no? 'selected':''}>${campsite_ownerVO.cso_no}${campsite_ownerVO.name}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="rcpt_no">收件人</label>
						<br><h5 style="color:#80c344;">${errorMsgs.rcpt_no[0]}</h5>
					</td>
					<td>
						<select size="1" name="rcpt_no" id="rcpt_no">
						<option value="99">--請選擇--</option>
						<c:forEach var="memberVO" items="${memberSvc.all}">
							<option value="${memberVO.mbr_no}" ${memberVO.mbr_no == param.rcpt_no? 'selected':''}>${memberVO.mbr_no}${memberVO.name}</option>
						</c:forEach>
						<option value="90001">客服人員</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="mail_cont">內容</label>
						<br><h5 style="color:#80c344;">${errorMsgs.mail_cont[0]}</h5>	
					</td>
					<td>
						<textarea name="mail_cont" rows="10" cols="45" class="mail_cont">${param.mail_cont.trim().isEmpty()? '':param.mail_cont.trim()}</textarea>
					</td>
				</tr>
				<tr>
					<td>附件照片</td>
					<td>
						<div id='container'>
							<label>請上傳圖片檔案：</label>
				            <input type="file" id='myFile' name='files' multiple>
					        <div id='preview'>               
					        </div>        
					    </div>
					</td>
				</tr>
			</table>
					<input type="hidden" name="mail_stat" value="0">
					<input type="hidden" name="mail_read_stat" value="0">
					<input type="hidden" name="action" value="insert">
					<input type="submit" value="發送" class="confirm">
<!-- 					<input type="submit" value="存入草稿" class="confirm"> -->
			</form>
		</div>
	</div>
</div>
<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
<script>
        let myFile = document.getElementById('myFile');
        let preview = document.getElementById('preview');
	let imgs = document.getElementsByClassName('img');        

        myFile.addEventListener('change', function(e) {
	    while(imgs.length != 0){
		imgs[0].remove();
	    }
        	let files = e.target.files;
            for (let i = 0; i < files.length; i++) {
                if (files[i].type.indexOf('image') > -1) {
                    fileName = files[i].name;
                    //console.log(files[i]);
                    let reader = new FileReader();
                    reader.addEventListener('load', function(e) {
                        let result = e.target.result;
                        //console.log(result);
                        let img = document.createElement('img');
                        img.setAttribute('class', 'img');
                        img.classList.add('align');
                        img.src = result;
                        preview.append(img);
                    });
                    reader.readAsDataURL(files[i]);
                } else {
                    alert('請上傳圖檔');
                }
            }
        });

    </script>
</body>
</html>