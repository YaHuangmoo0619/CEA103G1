<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Product_report: Home</title>

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
   <tr><td><h3>Product_report: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Product_report: Home</p>

<h3>資料查詢:</h3>
	
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='${pageContext.request.contextPath}/front-end/product_report/listAllProduct_report.jsp'>List</a> all Product_report.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_report/product_report.do" >
        <b>輸入商品回報編號 :</b>
        <input type="text" name="prod_rpt_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="product_reportSvc" scope="page" class="com.product_report.model.Product_reportService" />
   
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_report/product_report.do" >
       <b>選擇商品回報編號:</b>
       <select size="1" name="prod_rpt_no">
         <c:forEach var="product_reportVO" items="${product_reportSvc.all}" > 
          <option value="${product_reportVO.prod_rpt_no}">${product_reportVO.prod_rpt_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product_report/product_report.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="prod_rpt_no">
         <c:forEach var="product_reportVO" items="${product_reportSvc.all}" > 
          <option value="${product_reportVO.prod_rpt_no}">${product_reportVO.mbr_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>商品回報管理</h3>

<ul>
  <li><a href='${pageContext.request.contextPath}/front-end/product_report/addProduct_report.jsp'>Add</a> a new Product_report.</li>
</ul>

</body>
</html>