<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.member.model.*"%>

<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
<jsp:useBean id="member_rankSvc" scope="page" class="com.member_rank.model.Member_rankService" />

<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<html>
<head>
<title>會員 - listOneMember.jsp</title>

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

</style>
</head>
<body>
<div class="out">
	<div class="mid">
		<img class="inModal" src="<%=request.getContextPath() %>/member/GetPhoto?mbr_no=${memberVO.mbr_no}">
	</div>
	<div class="mid">
		<div class="in">
			<div class="divR colName">姓名：</div>
			<div class="divL">${memberVO.name}</div>
		</div>
		<div class="in">
			<div class="divR colName">帳號：</div>
			<div class="divL">${memberVO.acc}</div>
		</div>
		<div class="in">
			<div class="divR colName">會員等級：</div>
			<div class="divL">${member_rankSvc.getOneMember_rank(memberVO.rank_no).rank_name}</div>
		</div>
		<div class="in">
			<div class="divR colName">會員點數：</div>
			<div class="divL">${memberVO.pt}</div>
		</div>
		<div class="in">
			<div class="divR colName">會員經驗值：</div>
			<div class="divL">${memberVO.exp}</div>
		</div>
		<div class="in">
			<div class="divR colName">加入時間：</div>
			<div class="divL">${memberVO.join_time}</div>
		</div>
		<div class="in">
			<div class="divR colName">帳號狀態：</div>
			<div class="divL">
			${memberVO.acc_stat == 0 ?'未驗證':''}
			${memberVO.acc_stat == 1?'通過':''}
			${memberVO.acc_stat == 2?'未通過':''}
			${memberVO.acc_stat == 3?'已停權':''}
			</div>
		</div>
		<div class="in">
			<div class="divR colName">會員備註：</div>
			<div class="divL">${memberVO.rmk}</div>
		</div>
	</div>
</div>
<hr style="margin: 0 auto;">
<div class="out">
	<div class="in">
		<div class="colName">基本資料：</div>
		<br>
		<div class="divR colName">身份證：</div>
		<div class="divL">${memberVO.id}</div>&nbsp;&nbsp;
		<div class="divR colName">生日：</div>
		<div class="divL">${memberVO.bday}</div>&nbsp;&nbsp;
		<div class="divR colName">性別：</div>
		<div class="divL">${memberVO.sex == 1?'女性':'男性'}</div>
		<br>
		<br>
		<div class="divR colName">手機：</div>
		<div class="divL">${memberVO.mobile}</div>&nbsp;&nbsp;
		<div class="divR colName">信箱：</div>
		<div class="divL">${memberVO.mail}</div>&nbsp;&nbsp;
		<div class="divR colName">信用卡：</div>
		<div class="divL">${memberVO.card}</div>&nbsp;&nbsp;
		<br>
		<br>
		<div class="divR colName">地址：</div>
		<div class="divL">${memberVO.city}&nbsp;&nbsp;${memberVO.dist}&nbsp;&nbsp;${memberVO.add}</div>
	</div>
</div>
			
</body>
</html>