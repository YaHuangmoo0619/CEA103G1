<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member_rank.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    Member_rankService member_rankSvc = new Member_rankService();
    List<Member_rankVO> list = member_rankSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>��ϵ������|��� - listAllMember_rank.jsp</title>

<style>
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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>��ϵ������|��� - listAllMember_rank.jsp</h3>
		 <h4><a href="/CEA103G1/back-end/member_rank/select_page.jsp"><img src="/CEA103G1/images/logo.png" width="50" height="50" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>��ϵ������|�s��</th>
		<th>��ϵ������|�W��</th>
		
	</tr>
	
	<c:forEach var="member_rankVO" items="${list}" >	
		<tr>
			<td>${member_rankVO.rank_no}</td>
			<td>${member_rankVO.rank_name}</td>
			<td>${member_rankVO.pt_rwd_rto}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member_rank/member_rank.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="rank_no"  value="${member_rankVO.rank_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member_rank/member_rank.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="rank_no"  value="${member_rankVO.rank_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>


</body>
</html>