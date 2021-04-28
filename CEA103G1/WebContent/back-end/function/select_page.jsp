<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.function.model.*"%>
<%@ page import="java.util.*"%>

<% 
FunctionService functionSrv = new FunctionService();
List<FunctionVO> list = functionSrv.getAll();
Integer lastFx_no = list.get(list.size()-1).getFx_no();
request.setAttribute("lastFx_no", lastFx_no);
%>

<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<title>Function: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #FFF;
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
   <tr><td><h3>Function: Home</h3><img src="<%=request.getContextPath()%>/images/logo.png" width="50" height="50" border="0"><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Function: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty  errorMsgs}">
	<font style="color:red">�Эץ��H�U���~�G</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/function/listAllFunction.jsp'>List</a> all Functions.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/function/function.do" >
        <b>��J�v���s�� (�p1):</b>
        <input type="text" name="fx_no" placeholder="1��${lastFx_no}�����Ʀr">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="functionSvc" scope="page" class="com.function.model.FunctionService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/function/function.do" >
       <b>����v���s��:</b>
       <select size="1" name="fx_no">
         <option value= 0>--�п��--
         <c:forEach var="functionVO" items="${functionSvc.all}" > 
          <option value="${functionVO.fx_no}">${functionVO.fx_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/function/function.do" >
       <b>����v���W��:</b>
       <select size="1" name="fx_no">
       	 <option value= 0>--�п��--
         <c:forEach var="functionVO" items="${functionSvc.all}" > 
          <option value="${functionVO.fx_no}">${functionVO.fx_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�v���޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/function/addFunction.jsp'>Add</a> a new Function.</li>
</ul>
</body>
</html>