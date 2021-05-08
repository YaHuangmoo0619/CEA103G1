<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>



<div id="main">

	<!-- Thread Start -->
	<div class="t" style="margin-bottom: 0px; border-bottom: 0">
		<table cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<th class="h"><strong class="fl w">註冊使用者</strong> &nbsp; <span
					style="color: red; font-weight: bold;">${ message }</span></th>
			</tr>
		</table>
	</div>

	<html:form action="/CEA103G1/member_rank/member_rank.do">
		<html:hidden property="action" value="add" />
		<div class="t t2">
			<table cellspacing="0" cellpadding="0" width="100%"
				style="table-layout: fixed; border-top: 0">

				<tr>
					<td>帳號:</td>
					<td><input type="TEXT" name="acc" size="45" /></td>
				</tr>
				<tr>
					<td>密碼:</td>
					<td><input type="TEXT" name="pwd" size="45" /></td>
				</tr>
				<tr>
					<td>確認密碼:</td>
					<td><input type="TEXT" name="pwd2" size="45" /></td>
				</tr>
				<tr class="tr3">
					<td>姓名:</td>
					<td><input type="TEXT" name="name" size="45" /></td>
				</tr>
				<tr>
					<td>電子郵件:</td>
					<td><input type="TEXT" name="mail" size="45" /></td>
				</tr>
				<tr>
					<td>生日:</td>
					<td><input type="TEXT" name="bday" size="45" /></td>
				</tr>
				<tr>
					<td colspan="2"><html:submit styleClass="btn" value="註冊" /></td>
				</tr>
			</table>
			<input type="hidden" name="action" value="insert"> <input
				type="submit" value="註冊">
			</FORM>
		</div>
	</html:form>