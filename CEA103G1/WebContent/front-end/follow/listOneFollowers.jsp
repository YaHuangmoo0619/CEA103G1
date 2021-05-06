<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.follow.model.*"%>
<%@ page import="com.article.model.*"%>

<%int fans_num = (Integer)request.getAttribute("fans_num"); %>
<%String mbr_no = String.valueOf(request.getAttribute("mbr_no"));%>

<%
  FollowService followSvc = new FollowService();
  List<FollowVO> fans_list = (List<FollowVO>)request.getAttribute("followVO_fans");
  pageContext.setAttribute("fans_list", fans_list); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>粉絲</title>
</head>
<body>

<c:forEach var="fans_list" items="${fans_list}">
<div class=fans><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${fans_list.flw_mbr_no}&action=getProfile" >${fans_list.flw_mbr_no}</a></div>
</c:forEach>
</body>
</html>