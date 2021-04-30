<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<!DOCTYPE html>
<html lang="zh-tw">
<html>
<head>
<meta charset="BIG5">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<link rel="icon"
	href="<%=request.getContextPath()%>/images/campionLogoIcon.png"
	type="image/png">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>��aCampion</title>

<%@ include file="/partOfCampion_frontTop_css.txt"%>
<%@ include file="/partOfCampion_frontFooter_css.txt"%>
<!-- �H�W�O�e�x����������css -->

<%@ include file="/partOfCampion_backTop_css.txt"%>
<%@ include file="/partOfCampion_backLeft_css.txt"%>
<!-- �H�W�O��x�����ΰ��檺css -->

<%@ include file="/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/partOfCampion_COwnerLeft_css.txt"%>
<!-- �H�W�O��D�����ΰ��檺css -->

<style>
div.darkgray {
	background-color: #4e5452;
	display:flex;
	justify-content: space-around;
}

div.lightgray {
	background-color: #eee;
	display:flex;
	justify-content: space-around;
}

div.white {
	background-color: #fff;
	display:flex;
	justify-content: space-around;
}

div.lightgreen {
	background-color: #80c344;
	display:flex;
	justify-content: space-around;
}

div.darkgreen {
	background-color: #4B7F52;
	display:flex;
	justify-content: space-around;
}
p{
	display: inline-block;
	font-size: 2em;
	font-weight: 555;
	margin: 20px 40px;
}
</style>
</head>
<body>

	<%@ include file="/partOfCampion_frontTop_body.txt"%>
	<%@ include file="/partOfCampion_frontFooter_body.txt"%>
	<h1>�H�W�O�e�x�������έ���</h1>
	<hr>
	<br>
	<br>
	<%@ include file="/partOfCampion_backTop_body.txt"%>
	<%@ include file="/partOfCampion_backLeft_body.txt"%>
	<h1>�H�W�O��x�������ΰ���</h1>
	<hr>
	<br>
	<br>
	<%@ include file="/partOfCampion_COwnerTop_body.txt"%>
	<%@ include file="/partOfCampion_COwnerLeft_body.txt"%>
	<h1>�H�W�O��D�������ΰ���</h1>
	<hr>
	<br>
	<br>
	<div class="darkgray">
		<p style="color: #4e5452;">#4e5452</p>
		<p style="color: #eee;">#4e5452</p>
		<p style="color: #fff;">#4e5452</p>
		<p style="color: #80c344;">#4e5452</p>
		<p style="color: #4B7F52;">#4e5452</p>
	</div>
	<div class="lightgray">
		<p style="color: #4e5452;">#eee</p>
		<p style="color: #eee;">#eee</p>
		<p style="color: #fff;">#eee</p>
		<p style="color: #80c344;">#eee</p>
		<p style="color: #4B7F52;">#eee</p>
	</div>
	<div class="white">
		<p style="color: #4e5452;">#fff</p>
		<p style="color: #eee;">#fff</p>
		<p style="color: #fff;">#fff</p>
		<p style="color: #80c344;">#fff</p>
		<p style="color: #4B7F52;">#fff</p>
	</div>
	<div class="lightgreen">
		<p style="color: #4e5452;">#80c344</p>
		<p style="color: #eee;">#80c344</p>
		<p style="color: #fff;">#80c344</p>
		<p style="color: #80c344;">#80c344</p>
		<p style="color: #4B7F52;">#80c344</p>
	</div>
	<div class="darkgreen">
		<p style="color: #4e5452;">#4B7F52</p>
		<p style="color: #eee;">#4B7F52</p>
		<p style="color: #fff;">#4B7F52</p>
		<p style="color: #80c344;">#4B7F52</p>
		<p style="color: #4B7F52;">#4B7F52</p>
	</div>
	<h1>�H�W�O�t��ѦҡC#xxxxxx�O�I���⪺��d�C�����ϥΪ��C��զX</h1>
	<hr>
	<br>
	<br>
	<!-- �H�U�O�e�x����������js -->
	<%@ include file="/partOfCampion_frontTop_js.txt"%>

</body>
</html>