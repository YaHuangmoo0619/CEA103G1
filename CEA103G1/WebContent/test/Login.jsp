<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>�n�J��</title>
</head>
<div style="text-align:center;margin-top:120px">
    <form action="LoginServlet" method="post">
        <table style="margin-left:40%">
            <marquee width="200"scrolldelay="250">�ϥΪ̵n�J</marquee>

            <tr>
                <td>�n�Jid�G</td>
                <td><input name="id" type="text" size="21"></td>
            </tr>
            <tr>
                <td>�K�X:</td>
                <td><input name="password" type="password" size="21"></td>
            </tr>
        </table>
        <input type="submit" value="�n�J">
        <input type="reset" value="���m">
    </form>
    <br>
    <a href=RegisterPageServlet?url=WEB-INF/pages/Register.jsp>���U��</a>
</div>
</body>
</html>