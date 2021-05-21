<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="BIG5">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
            list-style: none;
        }
        html, body {
            height: 100%;
        }
        body {
            background: url("<%=request.getContextPath()%>/front-images/LoginCSO.jpg") no-repeat center center / cover;
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
            font-family: '�L�n������', sans-serif;
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
            font-family: '�L�n������', sans-serif;
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
            font-family: '�L�n������', sans-serif;
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
        <form class="form" method="post" action="<%=request.getContextPath()%>/campsite_owner/campsite_owner.do">
            <h2>��D�n�J</h2>
            <div class="group">
                <label for="id">�b��</label>
                <input type="text" name="acc" id="acc">
            </div>
            <div class="group">
                <label for="pwd">�K�X</label>
                <input type="password" name="pwd" id="pwd">
            </div>
            <div class="btn-group">
                <button class="btn">���o���ҫH</button>  
                <input type="hidden" name="action" value="resend">
            </div>
             <%-- ���~��C --%>
			<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
            <br>
<!--             <div> -->
<%--     			<a href='<%=request.getContextPath()%>/front-end/member/forgetAccount.jsp'>�ѰO�b��</a> --%>
<%--     			<a href='<%=request.getContextPath()%>/front-end/member/forgetPassword.jsp'>&emsp;�ѰO�K�X</a> --%>
<%--     			<a href='<%=request.getContextPath()%>/front-end/member/redirectMail.jsp'>&emsp;���H���ҫH</a> --%>
<!--     		</div> -->
        </form>
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	var enable = "<%=session.getAttribute("enable")%>";
	if(!(enable === null)){
		alert(enable);
	}
</script>
</html>