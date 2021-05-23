<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite.model.*"%>
<%@ page import="com.place.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	CampVO campVO = (CampVO) request.getAttribute("campVO");
%>
<jsp:useBean id="placeSvc" scope="page"
	class="com.place.model.PlaceService" />
<jsp:useBean id="csoSvc" scope="page"
	class="com.campsite_owner.model.Campsite_ownerService" />
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<link rel="icon"
	href="<%=request.getContextPath()%>/images/campionLogoIcon.png"
	type="image/png">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>營區資料</title>
<%@ include file="/part-of/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
<script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
<style>
.confirm {
	background-color: #5599FF;
	color: #000088;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}

.confirm:hover {
	background-color: #000088;
	color: #5599FF;
	cursor: pointer;
}

.not {
	background-color: #FF3333;
	color: #880000;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}

.not:hover {
	background-color: #AA0000;
	color: #FF0000;
	cursor: pointer;
}
th{
	width: 100px;
}
th, td {
	text-align: left;
	/* 	border: 1px solid #4e5452; */
	padding: 10px 10px;
	border-bottom:solid 1px;
}

#config {
	width: 150px;
	height: 100px;
}

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

table {
	border-collapse: collapse;
	/*自動斷行*/
	word-wrap: break-word;
	table-layout: fixed;
	text-align: left;
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
				<div style="display: inline-block;" style="width:45%;">
					<h3>營區資料</h3>
					<div style="display:inline-block;"><FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/camp/camp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改" class="confirm"> <input type="hidden"
							name="camp_no" value="${campVO.camp_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM></div>
					<c:if test="${campVO.campsite_Status==0}">
						<div style="display:inline-block;"><FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/camp/camp.do">
							<button class="not">
								<c:out value="不營業" />
							</button>
							<input type="hidden" name=action value="updatestatus"> <input
								type="hidden" name="camp_no"
								value="${campVO.camp_no}"> <input type="hidden"
								name="campsite_status" value="1">
						</FORM></div>
					</c:if>
					<c:if test="${campVO.campsite_Status==1}">
						<div style="display:inline-block;"><FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/camp/camp.do">
							<button class="confirm">
								<c:out value="營業" />
							</button>
							<input type="hidden" name=action value="updatestatus"> <input
								type="hidden" name="camp_no"
								value="${campVO.camp_no}"> <input type="hidden"
								name="campsite_status" value="0">
						</FORM></div>
					</c:if>
					<table>
<!-- 						<tr> -->
<!-- 							<th>營主姓名</th> -->
<%-- 							<td>${csoSvc.getOneCampsite_owner(campVO.cso_no).name}</td> --%>
<!-- 						</tr> -->
						<tr>
							<th>營區名稱</th>
							<td>${campVO.camp_name}</td>
						</tr>
						<tr>
							<th>營區資訊</th>
							<td>${campVO.campInfo}</td>
						</tr>
						<tr>
							<th>注意事項</th>
							<td>${campVO.note}</td>
						</tr>
						<tr>
							<th>配置圖</th>
							<td><img id="config"
								src="<%=request.getContextPath()%>/camp/campconfig.do?camp_no=${campVO.camp_no}"></td>
						</tr>
						<tr>
							<th>海拔</th>
							<td>${campVO.height}</td>
						</tr>
						<tr>
							<th>無線通訊</th>
							<td>${campVO.wireless}</td>
						</tr>
						<tr>
							<th>寵物</th>
							<c:if test="${campVO.pet==0}">
								<td><c:out value="不可攜帶寵物" /></td>
							</c:if>
							<c:if test="${campVO.pet==1}">
								<td><c:out value="可攜帶寵物" /></td>
							</c:if>
						</tr>
						<tr>
							<th>營區設施</th>
							<td>${campVO.facility}</td>
						</tr>
						<tr>
							<th>營業日</th>
							<td>${(campVO.operate_Date >= 1) ? ((campVO.operate_Date ==2) ? "平假日" : "假日") : "平日"}</td>
						</tr>
						<tr>
							<th>停車方式</th>
							<td>${campVO.park}</td>
						</tr>
						<tr>
							<th>地址</th>
							<td>${campVO.address}</td>
						</tr>
						<tr>
							<th>緯經度</th>
							<td>${campVO.latitude},${campVO.longitude}</td>
						</tr>
					</table>
				</div>
				<div style="display: inline-block; margin-left: 50px; width:50%;">
					<h3>營位資料</h3>
					<table>
						<tr>
							<th>營位名稱</th>
							<th>人數</th>
							<th>人數上限</th>
							<th>平日價格</th>
							<th>假日價格</th>
							<th>開放</th>
						</tr>
						<c:forEach var="placeVO"
							items="${placeSvc.getByCamp(campVO.camp_no)}">
							<tr>
								<td>${placeVO.plc_name}</td>
								<td>${placeVO.ppl}</td>
								<td>${placeVO.max_ppl}</td>
								<td>${placeVO.pc_wkdy}</td>
								<td>${placeVO.pc_wknd}</td>
								<c:if test="${placeVO.open_stat==0}">
									<td><input onclick="openstat(this)" type="checkbox" value="${placeVO.plc_no}" name="open_stat"></td>
								</c:if>
								<c:if test="${placeVO.open_stat==1}">
									<td><input onclick="openstat(this)" type="checkbox" value="${placeVO.plc_no}" name="open_stat" checked></td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
					<hr>
					<button type="button" onclick="showModal1()">新增營位</button>
					<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/place/place.do">
					<div id="container"></div>
					<input type="hidden" name=camp_no value="${campVO.camp_no}">
					<input id="plc_amt" type="hidden" name="plc_amt">
					</FORM>
				</div>
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
					<br>
					<button id="plc" type="button" style="border:none;cursor:pointer; background-color:white;">
						<ion-icon name="add-circle" style="border:none;cursor:pointer" size="large"></ion-icon>
					</button>
				</div>
				<div class="modal-footer">
					<button type="button" id="done" value="確定"
						class="btn btn-secondary" data-dismiss="modal">確定</button>
				</div>
			</div>
		</div>
	</div>
<%-- 	<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%> --%>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script>
	function showModal1() {
		$('#plc_amt').nextAll().remove();
		$("#edit").append($("#container").children());
		$("#edit").find('input').attr("readonly",false);
		$('#test1').modal('show');
	}
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
					let html = `<input type="hidden" name="action" value="insert"><input id="send" type="submit" value="刊登營位">`;
					$("#plc_amt").after(html);
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
			let html = `<input type="hidden" name="action" value="insert"><input id="send" type="submit" value="刊登營位">`;
			$("#plc_amt").after(html);
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
	function openstat(e){
		if(e.checked){
			$.ajax({
				type : "GET",
				dataType : "json",
				url : "/CEA103G1/place/place.do?action=updatestat",
				data : {plc_no: e.value, open_stat:1},
				success : function(data) {
					alert(data);				
				}
			});
		}else{
			$.ajax({
				type : "GET",
				dataType : "json",
				url : "/CEA103G1/place/place.do?action=updatestat",
				data : {plc_no: e.value, open_stat:0},
				success : function(data) {
					alert(data);
				}
			});
		}
	}
</script>
</body>
</html>