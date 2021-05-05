<%@ page import="com.mysql.cj.util.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
 <title>Title</title>
</head>
<body>
<br>
<br>
<%
 String info =(String) request.getAttribute("info");
%>
<%
 if (!StringUtils.isNullOrEmpty(info)){
%>
 <h1 style="color: red;text-align: center" ><%=info%></h1>
<%
 }
%>

<div align="center">
 <form action="/register" method="post">
 <table border="2">
 <tr>
 <th>使用者名稱</th>
 <td><input type="text" name="userName"/></td>
 </tr>
 <tr>
 <th>密碼</th>
 <td><input type="password" name="password"/></td>
 </tr>
 <tr>
 <th>年齡</th>
 <td><input type="text" name="age"/></td>
 </tr>
 <tr>
 <td colspan="2" align="center">
  <input type="submit" value="註冊"/>
  <input type="reset" value="清空"/>
 </td>
 </tr>
 </table>
 </form>
</div>
</body>
</html>