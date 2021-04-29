<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html lang="zh-tw">
<html>
<head>
<meta charset="BIG5">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<link rel="icon" href="<%=request.getContextPath() %>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>營家Campion</title>
<%@ include file="/partOfCampion_frontTop_css.txt" %>
<%@ include file="/partOfCampion_frontFooter_css.txt" %>
<!-- 以上是前台頁首頁尾的css -->
<%@ include file="/partOfCampion_backTop_css.txt" %>
<%@ include file="/partOfCampion_backLeft_css.txt" %>
<!-- 以上是後台頁首及側欄的css -->
</head>
<body>
<%@ include file="/partOfCampion_frontTop_body.txt" %>
<%@ include file="/partOfCampion_frontFooter_body.txt" %>
<h1>以上是前台的頁首頁尾</h1>
<%@ include file="/partOfCampion_backTop_body.txt" %>
<%@ include file="/partOfCampion_backLeft_body.txt" %>
<h1>以上是後台的頁首及側欄</h1>
<%@ include file="/partOfCampion_frontTop_js.txt" %>
</body>
</html>