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
<title>�|�� - listOneMember.jsp</title>

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
			<div class="divR colName">�m�W�G</div>
			<div class="divL">${memberVO.name}</div>
		</div>
		<div class="in">
			<div class="divR colName">�b���G</div>
			<div class="divL">${memberVO.acc}</div>
		</div>
		<div class="in">
			<div class="divR colName">�|�����šG</div>
			<div class="divL">${member_rankSvc.getOneMember_rank(memberVO.rank_no).rank_name}</div>
		</div>
		<div class="in">
			<div class="divR colName">�|���I�ơG</div>
			<div class="divL">${memberVO.pt}</div>
		</div>
		<div class="in">
			<div class="divR colName">�|���g��ȡG</div>
			<div class="divL">${memberVO.exp}</div>
		</div>
		<div class="in">
			<div class="divR colName">�[�J�ɶ��G</div>
			<div class="divL">${memberVO.join_time}</div>
		</div>
		<div class="in">
			<div class="divR colName">�b�����A�G</div>
			<div class="divL">
			${memberVO.acc_stat == 0 ?'������':''}
			${memberVO.acc_stat == 1?'�q�L':''}
			${memberVO.acc_stat == 2?'���q�L':''}
			${memberVO.acc_stat == 3?'�w���v':''}
			</div>
		</div>
		<div class="in">
			<div class="divR colName">�|���Ƶ��G</div>
			<div class="divL">${memberVO.rmk}</div>
		</div>
	</div>
</div>
<hr style="margin: 0 auto;">
<div class="out">
	<div class="in">
		<div class="colName">�򥻸�ơG</div>
		<br>
		<div class="divR colName">�����ҡG</div>
		<div class="divL">${memberVO.id}</div>&nbsp;&nbsp;
		<div class="divR colName">�ͤ�G</div>
		<div class="divL">${memberVO.bday}</div>&nbsp;&nbsp;
		<div class="divR colName">�ʧO�G</div>
		<div class="divL">${memberVO.sex == 1?'�k��':'�k��'}</div>
		<br>
		<br>
		<div class="divR colName">����G</div>
		<div class="divL">${memberVO.mobile}</div>&nbsp;&nbsp;
		<div class="divR colName">�H�c�G</div>
		<div class="divL">${memberVO.mail}</div>&nbsp;&nbsp;
		<div class="divR colName">�H�Υd�G</div>
		<div class="divL">${memberVO.card}</div>&nbsp;&nbsp;
		<br>
		<br>
		<div class="divR colName">�a�}�G</div>
		<div class="divL">${memberVO.city}&nbsp;&nbsp;${memberVO.dist}&nbsp;&nbsp;${memberVO.add}</div>
	</div>
</div>
			
</body>
</html>