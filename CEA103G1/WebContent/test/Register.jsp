<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>註冊頁</title>
</head>
<body>
<div style="text-align:center;margin-top:120px">
    <h1 >請註冊</h1>
    <form action="RegisterServlet" method="post">
        <table style="margin-left:40%">
            <caption>使用者註冊</caption>
            <tr>
                <td>ID:</td>
                <td><input name="id" type="text" size="20"></td>
            </tr>
            <tr>
                <td>登入名：</td>
                <td><input name="name" type="text" size="20"></td>
            </tr>
            <tr>
                <td>密碼:</td>
                <td><input name="password" type="password" size="20"></td>
            </tr>
            <tr>
                <td>角色:</td>
                <td><input name="role" type="text" size="20"></td>
            </tr>
        </table>
        <input type="submit" value="註冊">
        <input type="reset" value="重置">
    </form>
    <br>
    <a href=LoginPageServlet?url=WEB-INF/pages/Login.jsp>登入頁</a>
</div>
</body>
</html>