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
				<th class="h"><strong class="fl w">���U�ϥΪ�</strong> &nbsp; <span
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
					<td>�b��:</td>
					<td><input type="TEXT" name="acc" size="45" /></td>
				</tr>
				<tr>
					<td>�K�X:</td>
					<td><input type="TEXT" name="pwd" size="45" /></td>
				</tr>
				<tr>
					<td>�T�{�K�X:</td>
					<td><input type="TEXT" name="pwd2" size="45" /></td>
				</tr>
				<tr class="tr3">
					<td>�m�W:</td>
					<td><input type="TEXT" name="name" size="45" /></td>
				</tr>
				<tr>
					<td>�q�l�l��:</td>
					<td><input type="TEXT" name="mail" size="45" /></td>
				</tr>
				<tr>
					<td>�ͤ�:</td>
					<td><input type="TEXT" name="bday" size="45" /></td>
				</tr>
				<tr>
					<td colspan="2"><html:submit styleClass="btn" value="���U" /></td>
				</tr>
			</table>
			<input type="hidden" name="action" value="insert"> <input
				type="submit" value="���U">
			</FORM>
		</div>
	</html:form>