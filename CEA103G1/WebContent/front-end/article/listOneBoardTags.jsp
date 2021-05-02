<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ page import = "redis.clients.jedis.Jedis" %>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>列出某看板可選擇的所有標籤</title>
</head>
<body>
<%
	Jedis jedis = new Jedis("localhost", 6379);
	jedis.auth("123456");
	jedis.select(6); 
	List<String> tag_list = new ArrayList<>();
    for (String str : jedis.smembers("board:6:tags")){
    tag_list.add(str);
    }
    for(String str2 : tag_list){
    	System.out.println(str2);
    }
    jedis.close();
    pageContext.setAttribute("tag_list", tag_list);
%>


<c:forEach var="tag_list" items="${tag_list}">
<div>${tag_list}</div>
</c:forEach>

</body>
</html>