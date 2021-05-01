<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Member_rank: Home</title>

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
   <tr><td><h3>Member_rank: Home</h3><img src="<%=request.getContextPath()%>/images/logo.png" width="50" height="50" border="0"><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Member_rank: Home</p>

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
  <li><a href='<%=request.getContextPath()%>/back-end/member_rank/listAllMember_rank.jsp'>List</a> all Member_ranks.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member_rank/member_rank.do" >
        <b>輸入廣告編號 :</b>
        <input type="text" name="rank_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="member_rankSvc" scope="page" class="com.member_rank.model.Member_rankService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member_rank/member_rank.do" >
       <b>選擇廣告編號:</b>
       <select size="1" name="rank_no">
         <c:forEach var="member_rankVO" items="${member_rankSvc.all}" > 
          <option value="${member_rankVO.rank_no}">${member_rankVO.rank_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member_rank/member_rank.do" >
       <b>選擇廣告名稱:</b>
       <select size="1" name="rank_no">
         <c:forEach var="member_rankVO" items="${member_rankSvc.all}" > 
          <option value="${member_rankVO.rank_no}">${member_rankVO.rank_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>廣告管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/member_rank/addMember_rank.jsp'>Add</a> a new Member_rank.</li>
</ul>

</body>
</html>