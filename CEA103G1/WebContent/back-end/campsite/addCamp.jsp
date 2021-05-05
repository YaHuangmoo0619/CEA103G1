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
<link rel="icon"
	href="<%=request.getContextPath()%>/images/campionLogoIcon.png"
	type="image/png">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<meta charset="BIG5">
<title>Insert title here</title>
<%@ include file="/part-of/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
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
#camp_plc tr td input{
	width:80%;
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
				<h3>�Z�n���</h3>
				<%-- ���~��C --%>
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
						<tr>
							<td>��D�s��:</td>
							<td><input type="TEXT" name="cso_no" size="45"
								value="<%=(campVO == null) ? "70001" : campVO.getCso_no()%>" /></td>
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
							<td><input type="file" id="config" name="config" required><input
								type="hidden" name="image"
								value="<%=(campVO == null) ? "�L" : campVO.getConfig()%>">
								<div class="container">
									<img id="showconfig">
								</div></td>
						</tr>
						<tr>
							<td>�L�u�q�T:</td>
							<td><div class="checkbox-group required">
									<input type="checkbox" name="wireless" value="��wifi">wifi

									<input type="checkbox" name="wireless" value="���عq�H���T�� ">
									���عq�H <input type="checkbox" name="wireless" value="���Ǧ��T��">
									���� <input type="checkbox" name="wireless" value="�x���j���T��">
									�x���j <input type="checkbox" name="wireless" value="�ȤӦ��T��">
									�Ȥ� <input type="checkbox" name="wireless" value="�x�W���P���T��">
									�x�W���P
								</div></td>
						</tr>
						<tr>
							<td>�d��:</td>
							<td><input type="radio" name="pet" value="0"
								check="<%=(campVO == null) ? "" : (campVO.getPet() == 0 ? "true" : "")%>"
								required>���i��a�d�� <input type="radio" name="pet" value="1"
								check="<%=(campVO == null) ? "" : (campVO.getPet() == 1 ? "true" : "")%>">�i��a�d��</td>
						</tr>
						<tr>
							<td>��ϳ]�I:</td>
							<td><input type="TEXT" name="facility" size="45"
								value="<%=(campVO == null) ? "�L" : campVO.getFacility()%>" /></td>
						</tr>
						<tr>
							<td>��~��:</td>
							<td><input type="radio" name="operate_Date" value="0"
								check="<%=(campVO == null) ? "" : (campVO.getOperate_Date() == 0 ? "true" : "")%>"
								required>�u������ <input type="radio" name="operate_Date"
								value="1"
								check="<%=(campVO == null) ? "" : (campVO.getOperate_Date() == 1 ? "true" : "")%>">�u������
								<input type="radio" name="operate_Date" value="2"
								check="<%=(campVO == null) ? "" : (campVO.getOperate_Date() == 2 ? "true" : "")%>">���B�������~</td>
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
					<div>
						<c:forEach var="feature_listVO" items="${list}">
							<div>
								<input type="checkbox" name="feature_list"
									value="${feature_listVO.camp_fl_no}">${feature_listVO.camp_fl_name}</div>
						</c:forEach>
					</div>
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
					<input id="plc_amt" type="hidden" name="plc_amt">
					<button id="plc" type="button">
						<ion-icon name="add-circle" size="large"></ion-icon>
					</button>
					<input type="button" id="done" value="�T�w">
					<hr>
					<input type="file" id="progressbarTWInput" name="photo"
						accept="image/gif, image/jpeg, image/png"multiple/ >
					<div id="preview_progressbarTW_imgs"
						style="width: 100%; height: 300px; overflow: scroll;">
						<p>�ثe�S���Ϥ�</p>
					</div>

					<hr>
					<input type="hidden" name="action" value="insert"> <input
						type="submit" value="�Z�n���">
				</FORM>
			</div>
		</div>
	</div>
	<script>
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
			$("#preview_progressbarTW_imgs").html(""); // �M���w��
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
	</script>
</body>
</html>