<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite.model.*"%>
<%@ page import="com.feature_list.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<%
	CampVO campVO = (CampVO) request.getAttribute("campVO");
	Feature_ListService feature_listSvc = new Feature_ListService();
	List<Feature_ListVO> list = feature_listSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<%-- <%=campVO == null%> --%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="icon"
	href="<%=request.getContextPath()%>/images/campionLogoIcon.png"
	type="image/png">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<meta charset="BIG5">
<title>Insert title here</title>
<%@ include file="/part-of/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
<script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
<style>
body {
	background-color: #4e5452;
	color: #4e5452;
}

div.left {
	margin-top: 20px;
}

div.right {
	background-color: #fff;
	margin-top: 40px;
	padding: 50px 50px;
	border-radius: 5px;
}

a.content {
	color: #80c344;
}

a.content:hover {
	color: #4B7F52;
}

form {
	margin: 0px auto;
}

span {
	color: #80c344;
}

#camp_plc tr td input {
	width: 80%;
}
</style>
</head>
<body>
	<%@ include file="/part-of/partOfCampion_COwnerTop_body.txt"%>

	<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>

	<div class="container">
		<div class="row">
			<div class="left col-3">
				<%@ include file="/part-of/partOfCampion_COwnerLeft_body.txt"%></div>
			<div class="right col-9">
				<h3>刊登營區</h3>
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
						<tr>
							<td>營主編號:</td>
							<td><input type="TEXT" name="cso_no" size="45"
								value="<%=(campVO == null) ? "70001" : campVO.getCso_no()%>" /></td>
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
							<td><input type="file" id="config" name="config" required><input
								type="hidden" name="image"
								value="<%=(campVO == null) ? "無" : campVO.getConfig()%>">
								<div class="container">
									<img id="showconfig">
								</div></td>
						</tr>
						<tr>
							<td>無線通訊:</td>
							<td><div class="checkbox-group required">
									<input type="checkbox" name="wireless" value="有wifi">wifi

									<input type="checkbox" name="wireless" value="中華電信有訊號 ">
									中華電信 <input type="checkbox" name="wireless" value="遠傳有訊號">
									遠傳 <input type="checkbox" name="wireless" value="台哥大有訊號">
									台哥大 <input type="checkbox" name="wireless" value="亞太有訊號">
									亞太 <input type="checkbox" name="wireless" value="台灣之星有訊號">
									台灣之星
								</div></td>
						</tr>
						<tr>
							<td>寵物:</td>
							<td><input type="radio" name="pet" value="0"
								check="<%=(campVO == null) ? "" : (campVO.getPet() == 0 ? "true" : "")%>"
								required>不可攜帶寵物 <input type="radio" name="pet" value="1"
								check="<%=(campVO == null) ? "" : (campVO.getPet() == 1 ? "true" : "")%>">可攜帶寵物</td>
						</tr>
						<tr>
							<td>營區設施:</td>
							<td><input type="TEXT" name="facility" size="45"
								value="<%=(campVO == null) ? "無" : campVO.getFacility()%>" /></td>
						</tr>
						<tr>
							<td>營業日:</td>
							<td><input type="radio" name="operate_Date" value="0"
								check="<%=(campVO == null) ? "" : (campVO.getOperate_Date() == 0 ? "true" : "")%>"
								required>只有平日 <input type="radio" name="operate_Date"
								value="1"
								check="<%=(campVO == null) ? "" : (campVO.getOperate_Date() == 1 ? "true" : "")%>">只有假日
								<input type="radio" name="operate_Date" value="2"
								check="<%=(campVO == null) ? "" : (campVO.getOperate_Date() == 2 ? "true" : "")%>">平、假日皆營業</td>
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
					<br>
					<div>
						<c:forEach var="feature_listVO" items="${list}">
							<div style="display: inline-block;">
								<input type="checkbox" name="feature_list"
									value="${feature_listVO.camp_fl_no}">${feature_listVO.camp_fl_name}</div>
						</c:forEach>
						<div style="display: inline-block;">
							<input type="checkbox" name="feature_list" value="other">其他:<input
								type="text">
						</div>
					</div>
					<br>
					<button type="button" onclick="showModal1()">新增營位</button>
					<div id="container" disabled="disabled"></div>
						<input id="plc_amt" type="hidden" name="plc_amt">
					<!-- 					<button id="plc" type="button"> -->
					<!-- 						<ion-icon name="add-circle" size="large"></ion-icon> -->
					<!-- 					</button> -->
					<!-- 					<input type="button" id="done" value="確定"> -->
					<hr>
					<input type="file" id="progressbarTWInput" name="photo"
						accept="image/gif, image/jpeg, image/png"multiple/ >
					<div id="preview_progressbarTW_imgs"
						style="width: 100%; height: 300px; overflow: scroll;">
						<p>目前沒有圖片</p>
					</div>

					<hr>
					<input type="hidden" name="action" value="insert"> <input
						type="submit" value="刊登營區">
				</FORM>
			</div>
		</div>
	</div>
	<div class="modal" tabindex="-1" role="dialog" id="test1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" style="text-align: center;">新增營位</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div id="edit">
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
					</div>
					<button id="plc" type="button">
						<ion-icon name="add-circle" size="large"></ion-icon>
					</button>
				</div>
				<div class="modal-footer">
					<button type="button" id="done" value="確定"
						class="btn btn-secondary" data-dismiss="modal">確定</button>
				</div>
			</div>
		</div>
	</div>
	<script>
		$("#done").click(function() {
			let index = 0;
			if($("#title").nextAll().length === 1){
				let flag = true;
				$("#title").nextAll().find('input').each(function() {
					if($(this).val() === ""){
						flag = false;
					}			
				});
				if(flag === false){
					$("#title").nextAll().find('input').val("");
				}else{
					if(!($("#title").nextAll().length === 0)){
						$("#title").nextAll().each(function(i, dom) {
							$(dom).find('input').attr("name", "plc" + index);
							index++;
						});
						$("#plc_amt").val(index - 1);
						$("#container").append($('#edit').children());
						$("#container").find('input').attr("disabled",true);
					}
				}
			}else{
				$("#title").nextAll().find('input').each(function() {
					if($(this).val() === ""){
						$(this).parent().parent().remove();
					}
				});
				$("#title").nextAll().each(function(i, dom) {
					$(dom).find('input').attr("name", "plc" + index);
					index++;
				});
				$("#plc_amt").val(index - 1);
				$("#container").append($('#edit').children());
				$("#container").find('input').attr("disabled",true);
			}
		});
		$("#plc").click(function() {
			let flag = true;
			$("#title").nextAll().find('input').each(function() {
				if($(this).val() === ""){
					flag = false;
				}
			});
			if(flag === false){
				return false;				
			}
			$("#title").next().clone().find('input').val("").end().appendTo("#camp_plc");
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
			$("#preview_progressbarTW_imgs").html(""); // 清除預覽
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
		function showModal1() {
			$("#edit").append($("#container").children());
			$("#edit").find('input').attr("disabled",false);
			$('#test1').modal('show');
		}
	</script>
</body>
</html>