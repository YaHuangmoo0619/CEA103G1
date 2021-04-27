<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.announcement.model.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<% AnnouncementVO announcementVO = (AnnouncementVO)request.getAttribute("announcementVO"); %>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>


<title>公告列表新增 - addAnnouncement.jsp</title>

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
		 <h3>公告列表新增 - addAnnouncementFunction.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/announcement/select_page.jsp"><img src="<%=request.getContextPath()%>/images/logo.png" width="50" height="50" border="0"><br>回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

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
		<td>發文者:</td>
		<td>
			<select size="1" name="emp_no">

				<c:if test="${announcementVO == null || announcementVO.emp_no == 0}">
					<option value="0">--請選擇--
					<c:forEach var="employeeVO" items="${employeeSvc.all}">	
					<c:if test="${employeeVO.emp_no != 90001}">
					<option value="${employeeVO.emp_no}">${employeeVO.name}
					</c:if>
					</c:forEach>
				</c:if>
				
				<c:if test="${announcementVO != null && announcementVO.emp_no != 0}">
					<option value="0">--請選擇--
					<c:forEach var="employeeVO" items="${employeeSvc.all}">
						<c:if test="${announcementVO.emp_no == employeeVO.emp_no}" >
						<option value="${announcementVO.emp_no}" selected>${employeeSvc.getOneEmployee(announcementVO.emp_no).name}
						</c:if>
						
						<c:if test="${announcementVO.emp_no != employeeVO.emp_no && employeeVO.emp_no != 90001}">
						<option value="${employeeVO.emp_no}">${employeeVO.name}
						</c:if>	
					</c:forEach>	
				</c:if>

			</select>
		</td>
	</tr>
	<tr>
		<td>撰寫內文:</td>
		<td>
			<textarea name="an_cont" rows="20" cols="45">
			<c:if test="${announcementVO == null || announcementVO.an_cont.length() == 0}">
--範例--

活動內容：
於活動期間內發布露營器材使用教學文章，可獲得經驗值10點。
認定標準：
1.標題內需有CAMPION活動字樣
2.內容需有露營器材使用教學

活動時間：04/14(三)00:00~05/11(二)23:59

活動範例：
第一週04/14(三)00:00~04/20(二)23:59 期間內發文即達到當週資格。
經驗值發送日：04/22(四)前

第二週04/21(三)00:00~04/27(二)23:59 期間內發文即達到當週資格。
經驗值發送日：04/29(四)前

第三週04/28(三)00:00~05/04(二)23:59 期間內發文即達到當週資格。
經驗值發送日：05/06(四)前

第四週05/05(三)00:00~05/11(二)23:59 期間內發文即達到當週資格。
經驗值發送日：05/13(四)前

發送日期如有變動隨時公告通知
			</c:if>
			<c:if test="${announcementVO != null && announcementVO.an_cont.length() != 0}">
${announcementVO.an_cont.trim()}
			</c:if>
			</textarea>
		</td>
	</tr>
	<tr>
		<td>發文日期:</td>
		<td>
		<c:if test="${announcementVO == null || announcementVO.an_skd_date == null}">
			<input name="an_skd_date" id="announce_date" type="text" placeholder="2014-06-19">
		</c:if>
		<c:if test="${announcementVO != null && announcementVO.an_skd_date != null}">
			<input name="an_skd_date" id="announce_date" type="text" value="${announcementVO.an_skd_date}">
		</c:if>
		</td>
	</tr>
	<tr>
		<td>選擇照片:</td>
		<td>
		<img src="" class="img">
		<c:if test="${announcementVO != null}">
		
		</c:if>
		<br>
		<input type="file" name="an_pic" id="myFile">
		</td>
	</tr>
</table>
<br>

<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>
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
				console.log(result);
				input[input.length-3].previousElementSibling.before(img);
			});
			reader.readAsDataURL(files[i]);
			imgs[0].remove();
		}else{
			alert('請上傳圖檔');
		}
	}
});

<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->
$.datetimepicker.setLocale('zh');
$(function(){
	 $('#announce_date').datetimepicker({
	  format:'Y-m-d',
	  minDate:'-1970/01/01',
	  timepicker:false
	 });

});
</script>
</body>
</html>