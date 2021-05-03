<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_comment.model.*"%>
<%@ page import="java.util.*"%>

<% 
Product_commentService product_commentSrv = new Product_commentService();
List<Product_commentVO> list = product_commentSrv.getAll();
Integer lastProd_cmnt_no = list.get(0).getProd_cmnt_no();
request.setAttribute("lastProd_cmnt_no", lastProd_cmnt_no);
%>

<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<title>Product_comment: Home</title>

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
   <tr><td><h3>Product_comment: Home</h3><img src="<%=request.getContextPath()%>/images/logo.png" width="50" height="50" border="0"><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Product_comment: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty  errorMsgs}">
	<font style="color:red">請修正以下錯誤：</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/product_comment/listAllProduct_comment.jsp'>List</a> all Product_comments.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_comment/product_comment.do" >
        <b>輸入評論編號 (如1):</b>
        <input type="text" name="prod_cmnt_no" placeholder="1至${lastProd_cmnt_no}間的數字">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="product_commentSvc" scope="page" class="com.product_comment.model.Product_commentService" />
   
   <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_comment/product_comment.do" >
       <b>選擇評論編號:</b>
       <select size="1" name="prod_cmnt_no">
       			<option value= 0>--請選擇--
         <c:forEach var="product_commentVO" items="${product_commentSvc.all}" > 
          		<option value="${product_commentVO.prod_cmnt_no}">${product_commentVO.prod_cmnt_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  	</li>
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_comment/product_comment.do" >
       <b>選擇更新日期:</b>
       <select size="1" name="cmnt_time">
       <% 
			Product_commentService product_commentSvc2 = new Product_commentService();
			List<Product_commentVO> lists = product_commentSvc2.getAll();
			List<Date> dateList = new ArrayList<Date>();
			Set<Date> dateSet = new HashSet<Date>();
			for(Product_commentVO product_commentVO : lists){
				if(dateSet.add(product_commentVO.getCmnt_time())){
					dateList.add(product_commentVO.getCmnt_time());
				}
			}
			request.setAttribute("cmnt_time", dateList);
		%>
			<option value= 0>--請選擇--
         <c:forEach var="cmnt_time" items="${cmnt_time}" > 
	        <option value="${cmnt_time}">${cmnt_time}
         </c:forEach> 
       </select>
       <input type="hidden" name="action" value="getDate_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>


<h3>商品評論管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/product_comment/addProduct_comment.jsp'>Add</a> a new Product_comment.</li>
</ul>

</body>
</html>