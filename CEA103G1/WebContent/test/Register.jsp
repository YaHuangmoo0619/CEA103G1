<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>���U��</title>
</head>
<body>
<div style="text-align:center;margin-top:120px">
    <h1 >�е��U</h1>
    <form action="RegisterServlet" method="post">
        <table style="margin-left:40%">
            <caption>�ϥΪ̵��U</caption>
            <tr>
                <td>ID:</td>
                <td><input name="id" type="text" size="20"></td>
            </tr>
            <tr>
                <td>�n�J�W�G</td>
                <td><input name="name" type="text" size="20"></td>
            </tr>
            <tr>
                <td>�K�X:</td>
                <td><input name="password" type="password" size="20"></td>
            </tr>
            <tr>
                <td>����:</td>
                <td><input name="role" type="text" size="20"></td>
            </tr>
        </table>
        <input type="submit" value="���U">
        <input type="reset" value="���m">
    </form>
    <br>
    <a href=LoginPageServlet?url=WEB-INF/pages/Login.jsp>�n�J��</a>
</div>
</body>
</html>