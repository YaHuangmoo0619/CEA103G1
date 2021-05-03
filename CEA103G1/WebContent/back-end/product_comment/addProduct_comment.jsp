<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_comment.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<% Product_commentVO product_commentVO = (Product_commentVO)request.getAttribute("product_commentVO"); %>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<title>公告列表新增 - addProduct_comment.jsp</title>

<style>
  table#table-1 {
	background-color: #FFF;
    border: 2px solid black;
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

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
  textarea { 
  	resize: none; 
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>商品評論新增 - addProduct_commentFunction.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/product_comment/select_page.jsp"><img src="<%=request.getContextPath()%>/images/logo.png" width="50" height="50" border="0"><br>回首頁</a></h4>
	</td></tr>
</table>

<h3>評論新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not mbrty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_comment/product_comment.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>發文者:</td>
		<td>
			<select size="1" name="mbr_no">

				<c:if test="${product_commentVO == null || product_commentVO.mbr_no == 0}">
					<option value="0">--請選擇--
					<c:forEach var="memberVO" items="${memberSvc.all}">	
					<c:if test="${memberVO.mbr_no != 10002}">
					<option value="${memberVO.mbr_no}">${memberVO.name}
					</c:if>
					</c:forEach>
				</c:if>
				
				<c:if test="${product_commentVO != null && product_commentVO.mbr_no != 0}">
					<option value="0">--請選擇--
					<c:forEach var="memberVO" items="${memberSvc.all}">
						<c:if test="${product_commentVO.mbr_no == memberVO.mbr_no}" >
						<option value="${product_commentVO.mbr_no}" selected>${memberSvc.getOneMember(product_commentVO.mbr_no).name}
						</c:if>
						
						<c:if test="${product_commentVO.mbr_no != memberVO.mbr_no && memberVO.mbr_no != 10002}">
						<option value="${memberVO.mbr_no}">${memberVO.name}
						</c:if>	
					</c:forEach>	
				</c:if>

			</select>
		</td>
	</tr>
	<tr>
		<td>撰寫內文:</td>
		<td>
			<textarea name="cmnt_cont" rows="20" cols="45">
			<c:if test="${product_commentVO == null || product_commentVO.cmnt_cont.length() == 0}">
123
			</c:if>
			<c:if test="${product_commentVO != null && product_commentVO.cmnt_cont.length() != 0}">
${product_commentVO.cmnt_cont.trim()}
			</c:if>
			</textarea>
		</td>
	</tr>

</table>
<br>
<% 
long millis = System.currentTimeMillis();
java.sql.Timestamp cmnt_time = new java.sql.Timestamp(millis);
pageContext.setAttribute("cmnt_time",cmnt_time);
%>
<input type="hidden" name="cmnt_time" value="${cmnt_time}"/>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>

</body>
</html>