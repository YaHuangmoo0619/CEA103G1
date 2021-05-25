<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.campsite_owner.model.*"%>

<jsp:useBean id="campsite_ownerSvc" class="com.campsite_owner.model.Campsite_ownerService"/>

<%
Campsite_ownerVO campsite_ownerVO = (Campsite_ownerVO) request.getAttribute("campsite_ownerVO");
%>

<html>
<head>
<title>營主- listOneCampsite_owner.jsp</title>

<%@ include file="/part-of/partOfCampion_COwnerTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_COwnerLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>

<style>
div.out{
	display:flex;
	margin: 1em 3em;
}
img.inModal{
	width: 100%;
	margin: 5px;
}
div.mid{
	width:50%;
	padding: 10px 20px;
}
div.in{
	margin-bottom: 5px;
}
div.divR{
	display:inline;
	width:30%;	
}
div.divL{
	display:inline;
	width:70%;
}
div.kinds{
	display:inline;
	padding-right:20px;
}
div.colName{
	font-weight:555;
}
img.inDiv:hover{
	cursor:pointer;
}
</style>
</head>
<body>
<div class="out">
	<div class="mid">
		<img class="inModal" src="<%=request.getContextPath() %>/campsite_owner/GetPhoto?cso_no=${campsite_ownerVO.cso_no}">
	</div>
	<div class="mid">
		<div class="in">
			<div class="divR colName">姓名：</div>
			<div class="divL">${campsite_ownerVO.name}</div>
		</div>
		<div class="in">
			<div class="divR colName">帳號：</div>
			<div class="divL">${campsite_ownerVO.acc}</div>
		</div>
		<div class="in">
			<div class="divR colName">身份證正面照：</div>
			<div class="divL"><img class="inDiv" src="<%=request.getContextPath() %>/campsite_owner/GetPhoto?cso_no=${campsite_ownerVO.cso_no}&action=ciYezz5F"></div>
		</div>
		<div class="in">
			<div class="divR colName">身份證反面照：</div>
			<div class="divL"><img class="inDiv" src="<%=request.getContextPath() %>/campsite_owner/GetPhoto?cso_no=${campsite_ownerVO.cso_no}&action=1YYmZMYO"></div>
		</div>
		<div class="in">
			<div class="divR colName">第二證件正面照：</div>
			<div class="divL"><img class="inDiv" src="<%=request.getContextPath() %>/campsite_owner/GetPhoto?cso_no=${campsite_ownerVO.cso_no}&action=3M0vLk3a"></div>
		</div>
		<div class="in">
			<div class="divR colName">營業證明：</div>
			<div class="divL"><img class="inDiv" src="<%=request.getContextPath() %>/campsite_owner/GetPhoto?cso_no=${campsite_ownerVO.cso_no}&action=SiTVUb7c"></div>
		</div>
		<div class="in">
			<div class="divR colName">加入時間：</div>
			<div class="divL"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${campsite_ownerVO.join_time}"/></div>
		</div>
		<div class="in">
			<div class="divR colName">帳號狀態：</div>
			<div class="divL">
			${campsite_ownerVO.stat == 0?'未審核':''}
			${campsite_ownerVO.stat == 1?'已審核':''}
			${campsite_ownerVO.stat == 2?'已停權':''}
			</div>
		</div>
	</div>
</div>
<hr style="margin: 0 auto;">
<div class="out">
	<div class="in">
		<div class="colName">基本資料：</div>
		<br>
		<div class="divR colName">身份證：</div>
		<div class="divL">${campsite_ownerVO.id}</div>&nbsp;&nbsp;
		<div class="divR colName">生日：</div>
		<div class="divL">${campsite_ownerVO.bday}</div>&nbsp;&nbsp;
		<div class="divR colName">性別：</div>
		<div class="divL">${campsite_ownerVO.sex == 1?'女性':'男性'}</div>
		<br>
		<br>
		<div class="divR colName">手機：</div>
		<div class="divL">${campsite_ownerVO.mobile}</div>&nbsp;&nbsp;
		<div class="divR colName">信箱：</div>
		<div class="divL">${campsite_ownerVO.mail}</div>&nbsp;&nbsp;
		<br>
		<br>
		<div class="divR colName">銀行帳戶代號：</div>
		<div class="divL">${campsite_ownerVO.bank_no}</div>&nbsp;&nbsp;
		<div class="divR colName">銀行帳戶帳號：</div>
		<div class="divL">${campsite_ownerVO.bank_acc}</div>&nbsp;&nbsp;
		<br>
		<br>
		<div class="divR colName">地址：</div>
		<div class="divL">${campsite_ownerVO.city}&nbsp;&nbsp;${campsite_ownerVO.dist}&nbsp;&nbsp;${campsite_ownerVO.add}</div>
	</div>
</div>
		
<script>
let countClick = 1;
$('.inDiv').click(function(e){
// 	alert('in');
	if (countClick % 2 == 1) {
		$(this).css("width","100%");
		countClick++;
	}else {
		$(this).css("width","50px");
		countClick++;
	}
});
</script>
</body>
</html>