<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>IBM Article: Home</title>

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
   <tr><td><h3>IBM Article: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Article: Home</p>

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
  <li><a href='<%=request.getContextPath()%>/back-end/article/listAllArticle.jsp'>List</a> all Articles.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" >
        <b>��J�峹�s�� (�p1):</b>
        <input type="text" name="art_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" >
       <b>��ܤ峹�s��:</b>
       <select size="1" name="art_no">
         <c:forEach var="articleVO" items="${articleSvc.all}" > 
          <option value="${articleVO.art_no}">${articleVO.art_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" >
       <b>��ܤ峹���D:</b>
       <select size="1" name="art_no">
         <c:forEach var="articleVO" items="${articleSvc.all}" > 
          <option value="${articleVO.art_no}">${articleVO.art_title}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
  
    <jsp:useBean id="board_classSvc" scope="page" class="com.board_class.model.Board_ClassService" />
    <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" >
       <b>��ܬݪO:</b>
       <select size="1" name="bd_cl_no">
         <c:forEach var="board_classVO" items="${board_classSvc.all}" > 
          <option value="${board_classVO.bd_cl_no}">${board_classVO.bd_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOneArticle_ByBoard_Clss_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
  
</ul>


<h3>�峹�޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/article/addArticle.jsp'>Add</a> a new Article.</li>
</ul>

</body>
</html>