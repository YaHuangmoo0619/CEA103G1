<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Article_Likes: Home</title>

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
   <tr><td><h3>Article_Likes: Home</h3></td></tr>
</table>


<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article_likes/article_likes.do" >
        <b>輸入文章編號 :</b>
        <input type="text" name="art_no">
        <input type="hidden" name="action" value="getbyart_no">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
    <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article_likes/article_likes.do" >
        <b>輸入會員編號 :</b>
        <input type="text" name="mbr_no">
        <input type="hidden" name="action" value="getbymbr_no">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article_likes/article_likes.do" >
       <b>選擇文章編號:</b>
       <select size="1" name="art_no">
         <c:forEach var="articleVO" items="${articleSvc.all_Front}" > 
          <option value="${articleVO.art_no}">${articleVO.art_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getbyart_no">
       <input type="submit" value="送出">
    </FORM>
  </li>
  

  
</ul>


</body>
</html>