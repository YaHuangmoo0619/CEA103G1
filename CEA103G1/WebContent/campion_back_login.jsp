<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style type="text/css">
        @import url("https://fonts.googleeapis.com/css?family=Noto+Sans+TC:100,300,400,500,700,900&display=swap");
        * {
            margin: 0;
            padding: 0;
            list-style: none;
        }
        html, body {
            height: 100%;
        }
        body {
            background: url("<%=request.getContextPath()%>/front-images/topphoto.jpg") no-repeat center center / cover;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .login {
            width: 350px;
            height: 450px;
            background-color: rgba(0, 0, 0, .5);
            border-radius: 10px;
            border: 3px solid #fff;
            box-shadow: 0 0 60px #000;
            backdrop-filter: blur(5px);
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .form {
            width: 250px;
            font-family: '微軟正黑體', sans-serif;
            color: #fff;
        }
        .form h2 {
            margin-bottom: 20px;
            padding-bottom: 20px;
            border-bottom: 1px solid #fff;
            display: flex;
            justify-content: center;
        }
        .form .group {
            margin-bottom: 20px;
        }
        .form label {
            line-height: 2;
            font-family: '微軟正黑體', sans-serif;
        }
        .form input {
            width: 250px;
            border: 1px solid #aaa;
            line-height: 3;
            border-radius: 5px;

        }
        .form .btn-group {
            font-size: 0;
            margin-top: 30px;
        }
        .form .btn {
            font-size: 17px;
            border-radius: 5px;
            border: none;
            background-color: #239ad1;
            width: 250px;
            padding: 10px 0;
            color: #fff;
            font-family: '微軟正黑體', sans-serif;
        }
        .form .btn+.btn {
            margin-left: 20px;
        }
        button:hover {
            background: linear-gradient(#6ab4fe, #6ab4fe);
        }
        a {
   		    text-decoration:none;
		}
		input[type='checkbox']{
            width:12px;
            height:12px;
        }
    </style>
</head>

<body>
    <div class="login">
    ${errorMsgs.errorMsgs[0]}
        <form class="form" method="post" action="<%=request.getContextPath()%>/employee/employee.do">
            <h2>管理員登入</h2>
            ${errorMsgs.notFound[0]}
            <div class="group">
                <label for="acc">帳號</label>
                <input type="text" name="acc" id="acc">
                ${errorMsgs.accError[0]}
            </div>
            <div class="group">
                <label for="pwd">密碼</label>
                <input type="password" name="pwd" id="pwd">
                ${errorMsgs.pwdError[0]}
            </div>
            <div class="btn-group">
                <button class="btn">登入</button>  
                <input type="hidden" name="action" value="login_Employee">
            </div>
        </form>
    </div>
</body>

</html>