<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.follow.model.*"%>
<%@ page import="com.article.model.*"%>

<%int follows_num = (Integer)request.getAttribute("follows_num"); %>
<%String mbr_no = String.valueOf(request.getAttribute("mbr_no"));%>

<%
  FollowService followSvc = new FollowService();
  List<FollowVO> follows_list = (List<FollowVO>)request.getAttribute("followVO_follows");
  pageContext.setAttribute("follows_list", follows_list); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>追蹤名單</title>
</head>
<body>

<c:forEach var="follows_list" items="${follows_list}">
<div class=follow><a href="<%=request.getContextPath()%>/follow/follow.do?mbr_no=${follows_list.flwed_mbr_no}&action=getProfile">${follows_list.flwed_mbr_no}</a></div>
</c:forEach>
</body>
</html>