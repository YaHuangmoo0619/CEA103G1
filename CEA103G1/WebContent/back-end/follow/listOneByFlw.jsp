<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.follow.model.*"%>
<%@ page import="java.util.*"%>

<%int flw_mbr_no = Integer.parseInt(request.getParameter("flw_mbr_no")); %>
<%
  FollowService followSvc = new FollowService();

  List<FollowVO> list = followSvc.findbyflw(flw_mbr_no);
  pageContext.setAttribute("list", list);
  
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>listOneByFlw</title>
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
	width: 600px;
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

<table id="table-1">
	<tr><td>
		 <h3>listOneByFlw.jsp</h3>
				 <h4><a href="/CEA103G1/back-end/follow/select_page.jsp"><img src="/CEA103G1/images/Campion.png"
						width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>


<table>
		<tr>
			<th>會員編號</th>
			<th>被追蹤會員編號</th>

		</tr>
		
		
<%@ include file="pageforhome.file"%>
		<c:forEach var="followVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>

                <td>${followVO.flw_mbr_no}</td>	
                <td>${followVO.flwed_mbr_no}</td>	

			</tr>
		</c:forEach>
		
						
	</table>
	<%@ include file="page2.file"%>

</body>
</html>