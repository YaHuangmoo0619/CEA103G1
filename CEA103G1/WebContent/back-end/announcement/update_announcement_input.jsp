<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.announcement.model.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="java.util.*"%>

<% AnnouncementVO announcementVO = (AnnouncementVO)request.getAttribute("announcementVO"); %>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<title>公告列表修改 - update_announcement_input.jsp</title>

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
		 <h3>公告列表修改 - update_announcement_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/announcement/select_page.jsp"><img src="<%=request.getContextPath()%>/images/logo.png" width="50" height="50" border="0"><br>回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<jsp:useBean id="employeeSvc" scope="page" class="com.employee.model.EmployeeService" />
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/announcement/announcement.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>公告編號:<font color=red><b>*</b></font></td>
		<td><%=announcementVO.getAn_no()%></td>
	</tr>
	<tr>
		<td>發文者:</td>
		<td>
			<select size="1" name="emp_no">
				
				<c:forEach var="employeeVO" items="${employeeSvc.all}">
				
				<c:if test="${announcementVO.emp_no == employeeVO.emp_no}" >
				<option value="${announcementVO.emp_no}" selected>${employeeSvc.getOneEmployee(announcementVO.emp_no).name}
				</c:if>
				
				<c:if test="${announcementVO.emp_no != employeeVO.emp_no && employeeVO.emp_no != 90001}">
				<option value="${employeeVO.emp_no}">${employeeVO.name}
				</c:if>	
								
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>撰寫內文:</td>
		<td>
			<textarea name="an_cont" rows="20" cols="45">${announcementVO.an_cont}</textarea>
		</td>
	</tr>
	<tr>
		<td>選擇照片:</td>
		<td>
		<img src="<%=request.getContextPath()%>/announcement/GetPhoto?an_no=${announcementVO.an_no}" style="width:200px" class="img">
		<br>
		<input type="file" name="an_pic" id="myFile">		
		</td>
	</tr>

</table>
<br>
<% 
long millis = System.currentTimeMillis();
java.sql.Date an_skd_date = new java.sql.Date(millis);
pageContext.setAttribute("an_skd_date",an_skd_date);
%>
<input type="hidden" name="an_skd_date" value="${an_skd_date}"/>
<input type="hidden" name="an_no" value="<%=announcementVO.getAn_no()%>">
<input type="hidden" name="action" value="update">
<input type="submit" value="送出修改"></FORM>


<script>
let myFile = document.getElementById('myFile');
let input = document.getElementsByTagName('input');
let imgs = document.getElementsByClassName('img');
myFile.addEventListener('change',function(e){
	let files = e.target.files;
	for(let i = 0; i < files.length; i++){
		if(files[0].type.indexOf('image') > -1){
			fileName = files[0].name;
			let reader = new FileReader();					
			reader.addEventListener('load', function(e){
				let result = e.target.result;			
				let img = document.createElement('img');
				img.setAttribute('class', 'img');
				img.style.width='200px';
				img.src = result;
				input[0].previousElementSibling.before(img);
			});
			reader.readAsDataURL(files[i]);
			imgs[0].remove();
		}else{
			alert('請上傳圖檔');
		}
	}
});
</script>
</body>
</html>