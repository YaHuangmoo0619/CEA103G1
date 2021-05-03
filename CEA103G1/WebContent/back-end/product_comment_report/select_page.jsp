<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_comment_report.model.*"%>
<%@ page import="java.util.*"%>

<% 
Product_comment_reportService product_comment_reportSrv = new Product_comment_reportService();
List<Product_comment_reportVO> list = product_comment_reportSrv.getAll();
Integer lastProd_cmnt_rpt_no = list.get(0).getProd_cmnt_rpt_no();
request.setAttribute("lastProd_cmnt_rpt_no", lastProd_cmnt_rpt_no);
%>

<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<title>Product_comment_report: Home</title>

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
   <tr><td><h3>Product_comment_report: Home</h3><img src="<%=request.getContextPath()%>/images/logo.png" width="50" height="50" border="0"><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Product_comment_report: Home</p>

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
  <li><a href='<%=request.getContextPath()%>/back-end/product_comment_report/listAllProduct_comment_report.jsp'>List</a> all Product_comment_reports.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_comment_report/product_comment_report.do" >
        <b>輸入評論檢舉編號 (如1):</b>
        <input type="text" name="prod_cmnt_prt_no" placeholder="1至${lastProd_cmnt_prt_no}間的數字">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="product_comment_reportSvc" scope="page" class="com.product_comment_report.model.Product_comment_reportService" />
   
   <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_comment_report/product_comment_report.do" >
       <b>選擇評論檢舉編號:</b>
       <select size="1" name="prod_cmnt_rpt_no">
       			<option value= 0>--請選擇--
         <c:forEach var="product_comment_reportVO" items="${product_comment_reportSvc.all}" > 
          		<option value="${product_comment_reportVO.prod_cmnt_rpt_no}">${product_comment_reportVO.prod_cmnt_rpt_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  	</li>
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_comment_report/product_comment_report.do" >
       <b>選擇更新日期:</b>
       <select size="1" name="rpt_time">
       <% 
			Product_comment_reportService product_comment_reportSvc2 = new Product_comment_reportService();
			List<Product_comment_reportVO> lists = product_comment_reportSvc2.getAll();
			List<Date> dateList = new ArrayList<Date>();
			Set<Date> dateSet = new HashSet<Date>();
			for(Product_comment_reportVO product_comment_reportVO : lists){
				if(dateSet.add(product_comment_reportVO.getRpt_time())){
					dateList.add(product_comment_reportVO.getRpt_time());
				}
			}
			request.setAttribute("cmnt_time", dateList);
		%>
			<option value= 0>--請選擇--
         <c:forEach var="rpt_time" items="${rpt_time}" > 
	        <option value="${rpt_time}">${rpt_time}
         </c:forEach> 
       </select>
       <input type="hidden" name="action" value="getDate_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>


<h3>商品評論檢舉管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/product_comment_report/addProduct_comment_report.jsp'>Add</a> a new Product_comment_report.</li>
</ul>

</body>
</html>