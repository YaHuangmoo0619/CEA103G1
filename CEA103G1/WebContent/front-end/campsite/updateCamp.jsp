<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite.model.*"%>
<%@ page import="com.place.model.*"%>
<%@ page import="com.feature_list.model.*"%>
<%@ page import="com.campsite_feature.model.*"%>
<%@ page import="com.campsite_picture.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<%
	CampVO campVO = (CampVO) request.getAttribute("campVO");
	Camp_PictureService camp_pictureSvc = new Camp_PictureService();
	List<String> camp_piclist = camp_pictureSvc.getCamp_Picture(campVO.getCamp_no());
	List<Camp_FeatureVO> camp_featurelist = (List)request.getAttribute("camp_featurelist");
	Feature_ListService feature_listSvc = new Feature_ListService();
	List<Feature_ListVO> list = feature_listSvc.getAll();
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("camp_piclist", camp_piclist);
%>
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
<body bgcolor='white'>
	<%@ include file="/part-of/partOfCampion_COwnerTop_body.txt"%>

	<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>

	<div class="container">
		<div class="row">
			<div class="left col-3">
				<%@ include file="/part-of/partOfCampion_COwnerLeft_body.txt"%></div>
			<div class="right col-9">
				<h1>修改營區</h1>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<FORM METHOD="post" name="form1"
					ACTION="<%=request.getContextPath()%>/camp/camp.do"
					enctype="multipart/form-data"
					οnkeydοwn="if(event.keyCode==13){return false;}">
					<table>
						<tr style="display: none;">
							<td>營主編號:</td>
							<td><input type="TEXT" name="cso_no" size="45"
								value="<%=campVO.getCso_no()%>" /></td>
						</tr>
						<tr style="display: none;">
							<td>營區編號:</td>
							<td><input type="TEXT" name="camp_no" size="45"
								value="<%=campVO.getCamp_no()%>" /></td>
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
									<img id="showconfig" style="width:200px;height:100px;">
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
					<br>
					<div>
						<c:forEach var="feature_listVO" items="${list}">
							<c:if test="${empty camp_featurelist}">
								<div style="display: inline-block;"><input type="checkbox" name="feature_list"
												value="${feature_listVO.camp_fl_no}">${feature_listVO.camp_fl_name}</div>
							</c:if>
							<%Boolean flag = false;pageContext.setAttribute("flag",flag);%>
							<c:if test="${not empty camp_featurelist}">
								<c:forEach var="camp_featureVO" items="${camp_featurelist}">
									<c:if test="${camp_featureVO.camp_fl_no==feature_listVO.camp_fl_no}">
										<%flag = true;pageContext.setAttribute("flag",flag);%>
									</c:if>
								</c:forEach>
								<c:if test="${flag == true}">
									<div style="display: inline-block;"><input type="checkbox" name="feature_list"
													value="${feature_listVO.camp_fl_no}" checked>${feature_listVO.camp_fl_name}</div>		
								</c:if>					
								<c:if test="${flag == false}">
									<div style="display: inline-block;"><input type="checkbox" name="feature_list"
													value="${feature_listVO.camp_fl_no}">${feature_listVO.camp_fl_name}</div>		
								</c:if>					
							</c:if>					
						</c:forEach>
						<br>
						<div style="display: inline-block;">
							<input id="otherornot" type="hidden" name="otherornot">
							<input type="checkbox" name="feature_list" id="otherfeature">其他:<input
								type="text" id="other" style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;" disabled="true">
						</div>
					</div>
					<br>
					<button type="button" onclick="showModal1()">編輯營位</button>
					<div id="container">
						<table id="camp_plc">
							<tr id="title">
								<th>營位名稱</th>
								<th>數量</th>
								<th>人數</th>
								<th>人數上限</th>
								<th>平日價格</th>
								<th>假日價格</th>
							</tr>
							<%!int count = 0;%>
							<c:forEach var="placeVO" items="${placelist}">						
								<tr>
									<td><input type="text" name="plc<%=count%>" value="${placeVO.plc_name.substring(0,placeVO.plc_name.indexOf(','))}" readonly="true"></td>
									<td><input type="number" pattern="number" name="plc<%=count%>" value="${placeVO.plc_name.substring(placeVO.plc_name.indexOf(',')+1)}" readonly="true"></td>
									<td><input type="number" pattern="number" name="plc<%=count%>" value="${placeVO.ppl}" readonly="true"></td>
									<td><input type="number" pattern="number" name="plc<%=count%>" value="${placeVO.max_ppl}" readonly="true"></td>
									<td><input type="number" pattern="number" name="plc<%=count%>" value="${placeVO.pc_wkdy}" readonly="true"></td>
									<td><input type="number" pattern="number" name="plc<%=count%>" value="${placeVO.pc_wknd}" readonly="true"></td>
								</tr>
								<%count++;%>
							</c:forEach>
							<%count--;%>
						</table>
					</div>
					<input id="plc_amt" type="hidden" name="plc_amt" value="<%=count%>">
					<hr>
					<input type="file" id="progressbarTWInput" name="photo"
						accept="image/gif, image/jpeg, image/png" multiple>
					<div id="preview_progressbarTW_imgs"
						style="width: 100%; height: 300px; overflow: scroll;">
						<p>目前沒有圖片</p>
					</div>

					<hr>
					<input type="hidden" name="action" value="update"> <input
						type="submit" value="送出修改">
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
		
		$('#otherfeature').change(function(){
			   if($(this).prop('checked')){
				   $('#otherornot').val("yes");
				   $('#other').attr("disabled",false);
			   }else{
				   $('#otherornot').val("no");
				   $('#other').attr("disabled",true);
			   }
			});
			$('#other').change(function(){
				$('#otherfeature').val($(this).val());
			});
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
							$("#container").find('input').attr("readonly",true);
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
					$("#container").find('input').attr("readonly",true);
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
			$("#edit").find('input').attr("readonly",false);
			$('#test1').modal('show');
		}
	</script>
</body>

</html>