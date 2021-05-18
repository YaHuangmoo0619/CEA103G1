<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite_owner.model.*"%>

<%
	Campsite_ownerVO campsite_ownerVO = (Campsite_ownerVO) request.getAttribute("campsite_ownerVO");
%>



<html>
<head>
<meta charset="BIG5">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>register</title>

<style>
	.addr-county{background:#4169E1;color:#fff}
.addr-district{background:#008000;color:#fff}
.addr-zip{background:#c00;color:#fff;border:1px solid #666}
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>註冊營主</h3></td><td>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form class="form" method="post" action="<%=request.getContextPath()%>/campsite_owner/campsite_owner.do" enctype="multipart/form-data">
<table>
	<tr>
		<td>大頭照</td>
		<td>
			<input type="file" id="sticker" name="sticker">
			<div class="container">
				<img id="showconfig" style="width:200px;height:100px;">
			</div>
		</td>
	</tr>
	<tr>
		<td>姓名</td>
		<td><input type="TEXT" name="name" id="name" size="45" value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getName()%>" required></td>
	</tr>	
	<tr>
		<td>帳號</td>
		<td><input type="TEXT" name="acc" id="acc" size="45"  value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getAcc()%>" required></td>
	</tr>
	<tr>
		<td>密碼</td>
		<td><input type="password" name="pwd" id="pwd" size="45"/></td>
	</tr>
	<tr>
		<td>確認密碼</td>
		<td><input type="password" name="pwd" id="confirm_pwd" size="45"/></td>
	</tr>
	<tr>
		<td>身分證字號</td>
		<td><input type="TEXT" name="id" id="id" size="45"  value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getId()%>" required></td>
	</tr>
	<tr>
		<td>生日</td>
		<td><input  name="bday" id="bday" type="text" value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getBday()%>" required></td>
	</tr>
	<tr>
		<td>性別</td>
		<td>
			<input  type="radio" name="sex" value="0" <%=(campsite_ownerVO == null) ? "" : (campsite_ownerVO.getSex() == 0 ? "checked" : "")%> required>男
			<input  type="radio" name="sex" value="1" <%=(campsite_ownerVO == null) ? "" : (campsite_ownerVO.getSex() == 1 ? "checked" : "")%>>女
		</td>
	</tr>
	<tr>
		<td>手機</td>
		<td><input type="tel" name="mobile" id="mobile" size="45"value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getMobile()%>" required></td>
	</tr>
	<tr>
		<td>電子信箱</td>
		<td><input type="email" name="mail" id="mail" size="45"value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getMail()%>" required></td>
	</tr>
	<tr>
		<td>地址</td>
		<td>
			<div id="twzipcode"></div>
			<input type="TEXT" name="address" id="address" size="45" value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getAdd()%>" required>
		</td>
	</tr>
	<tr>
		<td>銀行代碼</td>
		<td><input type="TEXT" name="bank_no" id="bank_no" size="45" value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getBank_no()%>" required></td>
	</tr>
	<tr>
		<td>銀行帳戶</td>
		<td><input type="TEXT" name="bank_acc" id="bank_acc" size="45" value="<%=(campsite_ownerVO == null) ? "" : campsite_ownerVO.getBank_acc()%>" required></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出"></FORM>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/twzipcode/old/jquery.twzipcode-1.4.1.min.js"></script>
<script>
$('#twzipcode').twzipcode();
$('#sticker').on('change', function(e) {
	const file = this.files[0];
	const fr = new FileReader();
	fr.onload = function(e) {
		$('#showconfig').attr('src', e.target.result);
	};
	fr.readAsDataURL(file);
});
</script>
</body>
</html>