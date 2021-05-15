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
	PlaceService placeSvc = new PlaceService();
	List<PlaceVO> placelist = placeSvc.getByCamp(campVO.getCamp_no());
	List<String> camp_piclist = camp_pictureSvc.getCamp_Picture(campVO.getCamp_no());
	Feature_ListService feature_listSvc = new Feature_ListService();
	List<Feature_ListVO> list = feature_listSvc.getAll();
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("placelist", placelist);
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
				<h1>�ק����</h1>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">�Эץ��H�U���~:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<FORM METHOD="post" name="form1"
					ACTION="<%=request.getContextPath()%>/campsite/insertcamp.do"
					enctype="multipart/form-data"
					�jnkeyd�jwn="if(event.keyCode==13){return false;}">
					<table>
						<tr style="display: none;">
							<td>��D�s��:</td>
							<td><input type="TEXT" name="cso_no" size="45"
								value="<%=campVO.getCso_no()%>" /></td>
						</tr>
						<tr>
							<td>��ϦW��:</td>
							<td><input type="TEXT" name="camp_name" size="45"
								value="<%=(campVO == null) ? "" : campVO.getCamp_name()%>" /></td>
						</tr>
						<tr>
							<td>��ϸ�T:</td>
							<td><input type="TEXT" name="campInfo" size="45"
								value="<%=(campVO == null) ? "�L" : campVO.getCampInfo()%>" /></td>
						</tr>
						<tr>
							<td>�`�N�ƶ�:</td>
							<td><input type="TEXT" name="note" size="45"
								value="<%=(campVO == null) ? "�L" : campVO.getNote()%>" /></td>
						</tr>
						<tr>
							<td>�t�m��:</td>
							<td><input type="file" id="config" name="config"><input
								type="hidden" name="image"
								value="<%=(campVO == null) ? "�L" : campVO.getConfig()%>">
								<div class="container">
									<img id="showconfig" style="width:200px;height:100px;">
								</div></td>
						</tr>
						<tr>
							<td>�L�u�q�T:</td>
							<td><div id="wireless" class="checkbox-group required">
									<input type="checkbox" name="wireless" value="��wifi">wifi

									<input type="checkbox" name="wireless" value="���عq�H���T��">
									���عq�H <input type="checkbox" name="wireless" value="���Ǧ��T��">
									���� <input type="checkbox" name="wireless" value="�x���j���T��">
									�x���j <input type="checkbox" name="wireless" value="�ȤӦ��T��">
									�Ȥ� <input type="checkbox" name="wireless" value="�x�W���P���T��">
									�x�W���P
								</div></td>
						</tr>
						<tr>
							<td>�d��:</td>
							<td id="pet"><input type="radio" name="pet" value="0">���i��a�d��
								<input type="radio" name="pet" value="1">�i��a�d��</td>
						</tr>
						<tr>
							<td>��ϳ]�I:</td>
							<td><input type="TEXT" name="facility" size="45"
								value="<%=(campVO == null) ? "�L" : campVO.getFacility()%>" /></td>
						</tr>
						<tr>
							<td>��~��:</td>
							<td id=operate_date><input type="radio" name="operate_Date"
								value="0">�u������ <input type="radio" name="operate_Date"
								value="1">�u������ <input type="radio" name="operate_Date"
								value="2">���B�������~</td>
						</tr>
						<tr>
							<td>�����覡:</td>
							<td><input type="TEXT" name="park" size="45"
								value="<%=(campVO == null) ? "��������" : campVO.getPark()%>" /></td>
						</tr>
						<tr>
							<td>����:</td>
							<td><input name="county" type="text"
								value="<%=(campVO == null) ? "��饫" : campVO.getCounty()%>"></td>
						</tr>
						<tr>
							<td>�m����:</td>
							<td><input name="district" type="text"
								value="<%=(campVO == null) ? "���c��" : campVO.getDistrict()%>"></td>
						</tr>
						<tr>
							<td>�a�}:</td>
							<td><input name="address" type="text"
								value="<%=(campVO == null) ? "�_����46��" : campVO.getAddress()%>"></td>
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
							<input type="checkbox" name="feature_list" value="other">��L:<input
								type="text">
						</div>
					</div>
					<br>
					<button type="button" onclick="showModal1()">�s�W���</button>
					<div id="container" disabled="disabled"></div>
					<input id="plc_amt" type="hidden" name="plc_amt">
					<hr>
					<input type="file" id="progressbarTWInput" name="photo"
						accept="image/gif, image/jpeg, image/png"multiple/ >
					<div id="preview_progressbarTW_imgs"
						style="width: 100%; height: 300px; overflow: scroll;">
						<p>�ثe�S���Ϥ�</p>
					</div>

					<hr>
					<input type="hidden" name="action" value="insert"> <input
						type="submit" value="�e�X�ק�">
				</FORM>
			</div>
		</div>
	</div>
	<div class="modal" tabindex="-1" role="dialog" id="test1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" style="text-align: center;">�s�W���</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div id="edit">
						<table id="camp_plc">
							<tr id="title">
								<th>���W��</th>
								<th>�ƶq</th>
								<th>�H��</th>
								<th>�H�ƤW��</th>
								<th>�������</th>
								<th>�������</th>
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
					<button type="button" id="done" value="�T�w"
						class="btn btn-secondary" data-dismiss="modal">�T�w</button>
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
		eachWireless = wireless.split("�B");
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
				var noPictures = $("<p>�ثe�S���Ϥ�</p>");
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