<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Board_class: Home</title>

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
   <tr><td><h3>Board_Class: Home</h3></td></tr>
</table>

<p>This is the Home page for Board_class: Home</p>

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
  <li><a href='<%=request.getContextPath()%>/back-end/board_class/listAllBoard_class.jsp'>List</a> all Board_class.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/board_class/board_class.do" >
        <b>輸入看板編號 (如1):</b>
        <input type="text" name="bd_cl_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="board_classSvc" scope="page" class="com.board_class.model.Board_ClassService" />
   
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/board_class/board_class.do" >
       <b>選擇看板:</b>
       <select size="1" name="bd_cl_no">
         <c:forEach var="board_classVO" items="${board_classSvc.all}" > 
          <option value="${board_classVO.bd_cl_no}">${board_classVO.bd_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>看板管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/board_class/addBoard_class.jsp'>Add</a> a new Article.</li>
</ul>

</body>
</html>