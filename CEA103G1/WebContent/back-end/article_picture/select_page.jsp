<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Article_Picure: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Article_Picure: Home</h3></td></tr>
</table>


<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/article_picture/listAllArticle_Picuture.jsp'>List</a> all Article_Pictures.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article_picture/article_picture.do" >
        <b>��J�Ӥ��s�� (�p1):</b>
        <input type="text" name="art_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>
  
    <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article_picture/article_picture.do" >
        <b>��J�峹�s�� (�p1):</b>
        <input type="text" name="art_no">
        <input type="hidden" name="action" value="getbyArt_no">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  
  
</ul>


<h3>�Ӥ��޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/article_picture/addArticle_picture.jsp'>Add</a> a new Article_Picture.</li>
</ul>

</body>
</html>