<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>登入頁</title>
</head>
<div style="text-align:center;margin-top:120px">
    <form action="LoginServlet" method="post">
        <table style="margin-left:40%">
            <marquee width="200"scrolldelay="250">使用者登入</marquee>

            <tr>
                <td>登入id：</td>
                <td><input name="id" type="text" size="21"></td>
            </tr>
            <tr>
                <td>密碼:</td>
                <td><input name="password" type="password" size="21"></td>
            </tr>
        </table>
        <input type="submit" value="登入">
        <input type="reset" value="重置">
    </form>
    <br>
    <a href=RegisterPageServlet?url=WEB-INF/pages/Register.jsp>註冊頁</a>
</div>
</body>
</html>