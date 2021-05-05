<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite.model.*"%>
<%@ page import="com.campsite_picture.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<%
	CampVO campVO = (CampVO) request.getAttribute("campVO");
	Camp_PictureService camp_pictureSvc = new Camp_PictureService();
	List<String> camp_piclist = camp_pictureSvc.getCamp_Picture(campVO.getCamp_no());
	pageContext.setAttribute("camp_piclist", camp_piclist);
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>修改營區</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
<style>
img {
	width: 150px;
	height: 100px;
}
table{
	width:60%;
}
table, th, td {
	border: 1px solid black;
	background-color: white;
	text-align: center;
}

button {
	background-color: white; /* Blue background */
	border: none; /* Remove borders */
	color: black; /* White text */
	padding: 12px 16px; /* Some padding */
	font-size: 16px; /* Set a font size */
	cursor: pointer; /* Mouse pointer on hover */
}
</style>
</head>
<body bgcolor='white'>

	<div>
		<h1>修改營區</h1>

		<h3>
			<a
				href="<%=request.getContextPath()%>/back-end/campsite/listAllCamp.jsp">回清單</a>
		</h3>
	</div>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post" name="form1"
		ACTION="<%=request.getContextPath()%>/campsite/insertcamp.do"
		enctype="multipart/form-data"
		οnkeydοwn="if(event.keyCode==13){return false;}">
		<table>
			<tr style="display: none;">
				<td>營主編號:</td>
				<td><input type="TEXT" name="cso_no" size="45"
					value="<%=campVO.getCso_no()%>" /></td>
			</tr>
			<tr>
				<td>營區名稱:</td>
				<td><input type="TEXT" name="camp_name" size="45"
					value="<%=(campVO == null) ? "" : campVO.getCamp_name()%>" /></td>
			</tr>
			<tr>
				<td>營區資訊:</td>
				<td><input type="TEXT" name="campInfo" size="45"
					value="<%=(campVO == null) ? "無" : campVO.getCampInfo()%>" /></td>
			</tr>
			<tr>
				<td>注意事項:</td>
				<td><input type="TEXT" name="note" size="45"
					value="<%=(campVO == null) ? "無" : campVO.getNote()%>" /></td>
			</tr>
			<tr>
				<td>配置圖:</td>
				<td><input type="file" id="config" name="config"><input
					type="hidden" name="image"
					value="<%=(campVO == null) ? "無" : campVO.getConfig()%>">
					<div class="container">
						<img id="showconfig">
					</div></td>
			</tr>
			<tr>
				<td>無線通訊:</td>
				<td><div id="wireless" class="checkbox-group required">
						<input type="checkbox" name="wireless" value="有wifi">wifi

						<input type="checkbox" name="wireless" value="中華電信有訊號">
						中華電信 <input type="checkbox" name="wireless" value="遠傳有訊號">
						遠傳 <input type="checkbox" name="wireless" value="台哥大有訊號">
						台哥大 <input type="checkbox" name="wireless" value="亞太有訊號">
						亞太 <input type="checkbox" name="wireless" value="台灣之星有訊號">
						台灣之星
					</div></td>
			</tr>
			<tr>
				<td>寵物:</td>
				<td id="pet"><input type="radio" name="pet" value="0">不可攜帶寵物
					<input type="radio" name="pet" value="1">可攜帶寵物</td>
			</tr>
			<tr>
				<td>營區設施:</td>
				<td><input type="TEXT" name="facility" size="45"
					value="<%=(campVO == null) ? "無" : campVO.getFacility()%>" /></td>
			</tr>
			<tr>
				<td>營業日:</td>
				<td id=operate_date><input type="radio" name="operate_Date"
					value="0">只有平日 <input type="radio" name="operate_Date"
					value="1">只有假日 <input type="radio" name="operate_Date"
					value="2">平、假日皆營業</td>
			</tr>
			<tr>
				<td>停車方式:</td>
				<td><input type="TEXT" name="park" size="45"
					value="<%=(campVO == null) ? "集中停車" : campVO.getPark()%>" /></td>
			</tr>
			<tr>
				<td>縣市:</td>
				<td><input name="county" type="text"
					value="<%=(campVO == null) ? "桃園市" : campVO.getCounty()%>"></td>
			</tr>
			<tr>
				<td>鄉鎮市區:</td>
				<td><input name="district" type="text"
					value="<%=(campVO == null) ? "中壢區" : campVO.getDistrict()%>"></td>
			</tr>
			<tr>
				<td>地址:</td>
				<td><input name="address" type="text"
					value="<%=(campVO == null) ? "復興路46號" : campVO.getAddress()%>"></td>
			</tr>

		</table>
		<div>
			<c:forEach var="feature_listVO" items="${list}">
				<div>
					<input type="checkbox" name="feature_list"
						value="${feature_listVO.camp_fl_no}">${feature_listVO.camp_fl_name}</div>
			</c:forEach>
		</div>
		<table id="camp_plc">
			<tr id="title">
				<th>營位名稱</th>
				<th>數量</th>
				<th>人數</th>
				<th>人數上限</th>
				<th>平日價格</th>
				<th>假日價格</th>
			</tr>
			<tr>
				<td><input type="text"></td>
				<td><input type="number" pattern="number"></td>
				<td><input type="number" pattern="number"></td>
				<td><input type="number" pattern="number"></td>
				<td><input type="number" pattern="number"></td>
				<td><input type="number" pattern="number"></td>
			</tr>
		</table>
		<input id="plc_amt" type="hidden" name="plc_amt">
		<button id="plc" type="button">
			<ion-icon name="add-circle" size="large"></ion-icon>
		</button>
		<input type="button" id="done" value="確定">
		<hr>
		<input type="file" id="progressbarTWInput" name="photo"
			accept="image/gif, image/jpeg, image/png"multiple/ >
		<div id="preview_progressbarTW_imgs"
			style="width: 100%; height: 300px; overflow: scroll;">
			<p>目前沒有圖片</p>
		</div>

		<hr>
		<input type="hidden" name="action" value="update"> <input
			type="hidden" name="camp_no" value="<%=campVO.getCamp_no()%>">
		<input type="submit" value="送出修改">
	</FORM>
	<!-- 	/CEA103G1/camp/campconfig.do? -->
	<script>
		$("#preview_progressbarTW_imgs").html("");
		var camp_pic = `${camp_piclist}`;
		if(!(camp_pic === "[]")){
			camp_pic = camp_pic.substring(1,camp_pic.length-1);
			camp_pic = camp_pic.split(", ");
			console.log(camp_pic);
			
			for(let i = 0; i < camp_pic.length; i++){
				var img = $("<img width='500' height='500'>").attr('src', camp_pic[i]);
				$("#preview_progressbarTW_imgs").append(img);
			}
		}
		var src = `/CEA103G1/camp/campconfig.do?camp_no=<%=campVO.getCamp_no()%>`;
		$("#showconfig").attr('src', src);
		
		var wireless = `<%=campVO.getWireless()%>`;
		eachWireless = wireless.split("、");
		for(let i = 0; i < eachWireless.length; i++){
			$("#wireless").find(':input').each(function() {
				if($(this).val()===eachWireless[i]){
					$(this).prop("checked",true);
				}
			});
		}
		var pet = `<%=campVO.getPet()%>`;
		$("#pet").find(':input').each(function() {
			if($(this).val()===pet){
				$(this).prop("checked",true);
			}
		});
		var operate_date = `<%=campVO.getOperate_Date()%>`;
		$("#operate_date").find(':input').each(function() {
			if ($(this).val() === operate_date) {
				$(this).prop("checked", true);
			}
		});
		$("#done").click(function() {
			let index = 0;
			$("#title").nextAll().each(function(i, dom) {
				$(dom).find('input').attr("name", "plc" + index);
				index++;
			});
			$("#plc_amt").val(index - 1);
		});
		$("#plc").click(
				function() {
					let index = 0;
					$("#title").next().clone().find('input').val("").end()
							.appendTo("#camp_plc")
				});

		$('#config').on('change', function(e) {
			const file = this.files[0];
			const fr = new FileReader();
			fr.onload = function(e) {
				$('#showconfig').attr('src', e.target.result);
			};

			fr.readAsDataURL(file);
		});

		$("#progressbarTWInput").change(function() {
			readURL(this);
		});

		function readURL(input) {
			if (input.files && input.files.length >= 0) {
				for (var i = 0; i < input.files.length; i++) {
					var reader = new FileReader();
					reader.onload = function(e) {
						var img = $("<img width='500' height='500'>").attr(
								'src', e.target.result);
						$("#preview_progressbarTW_imgs").append(img);
					}
					reader.readAsDataURL(input.files[i]);
				}
			} else {
				var noPictures = $("<p>目前沒有圖片</p>");
				$("#preview_progressbarTW_imgs").append(noPictures);
			}
		}
	</script>
</body>

</html>