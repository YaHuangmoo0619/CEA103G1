<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'check.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->
</head>
<body>
	<%
request.setCharacterEncoding("utf-8");
String users=request.getParameter("username");
String pass=request.getParameter("pwd");
boolean flag=false;
PreparedStatement sql=null;  
ResultSet rs=null;
Connection conn=null;
%>
	<% 
String driver = "com.mysql.jdbc.Driver";  
String url = "jdbc:mysql://127.0.0.1:3307/login";  
String use = "root";   
String password = "960404";  
Class.forName(driver);  
conn= DriverManager.getConnection(url,use,password);  
sql =conn.prepareStatement("select * from student where username=? and password=?");
sql.setString(1,users);
sql.setString(2,pass);
rs=sql.executeQuery();
if (rs.next()) {  
flag=true;
}
rs.close();
sql.close();
conn.close();
%>
	<!-- 判斷是否是正確的登入使用者 -->
	<% if (flag==true){ %>
	<jsp:forward page="show.jsp" />
	<%} 
else
if (flag==false){
%>
	<jsp:forward page="login_fali.jsp" />
	<%} %>
</body>
</html>
l>
