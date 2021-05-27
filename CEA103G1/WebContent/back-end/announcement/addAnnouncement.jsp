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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
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


<title>�s�W���i - addAnnouncement.jsp</title>
<%@ include file="/part-of/partOfCampion_backTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_backLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
<style>
body{
	background-color: #4e5452;
	color: #4e5452;
}
div.left{
	margin-top: 20px;
}
div.right{
	background-color: #fff;
	margin-top: 40px;
	padding: 50px 50px;
	border-radius: 5px;
}
a.content{
	color: #80c344;
	font-size: 0.6em;
}
a.content:hover {
	color: #4B7F52;
}

table{
	width: 700px;
	margin: 30px auto;
	border: 1px solid #4e5452;
}
th, td{
	text-align: center;
	border: 1px solid #4e5452;
	padding: 10px 15px;
}
td.function{
	text-align: justify;	
}
label.spotlight{
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}
form{
	text-align: center;
}
textarea{
	resize: none;
}
input.confirm{
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
	margin: 0px 10px;
}
input.confirm:hover{
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}
</style>
<style>
#container {
	padding: 10px;
	max-width: 250px;
	margin: 0px auto;
}
.align{
	display: inline;
	vertical-align: text-top;
}
#preview, .change{
	margin: 10px 0px;
	
}
img{
	max-width: 100%;
	margin: 10px;
}
.delete{
	display: none;
}
</style>
</head>
<body>
<%@ include file="/part-of/partOfCampion_backTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
<div class="container">
	<div class="row">
		<div class= "left col-3">
		<%@ include file="/part-of/partOfCampion_backLeft_body.txt"%></div>
		<div class="right col-9">
			<h2>�s�W���i&nbsp;<a class="content" href="<%=request.getContextPath()%>/back-end/announcement/listAllAnnouncement.jsp">�^���i�C��</a></h2>
			<hr>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
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
		<td>�o���:</td>
		<td>
			<select size="1" name="emp_no">

				<c:if test="${announcementVO == null || announcementVO.emp_no == 0}">
					<option value="0">--�п��--
					<c:forEach var="employeeVO" items="${employeeSvc.getFunctionEmp_no(6)}">	
					<c:if test="${employeeVO.emp_no != 90001}">
					<option value="${employeeVO.emp_no}">${employeeVO.name}
					</c:if>
					</c:forEach>
				</c:if>
				
				<c:if test="${announcementVO != null && announcementVO.emp_no != 0}">
					<option value="0">--�п��--
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
		<td>���g����:</td>
		<td>
			<textarea name="an_cont" rows="20" cols="45">
			<c:if test="${announcementVO == null || announcementVO.an_cont.length() == 0}">
--�d��--

���ʤ��e�G
�󬡰ʴ������o���S�羹���ϥαоǤ峹�A�i��o�g���10�I�C
�{�w�зǡG
1.���D���ݦ�CAMPION���ʦr��
2.���e�ݦ��S�羹���ϥαо�

���ʮɶ��G04/14(�T)00:00~05/11(�G)23:59

���ʽd�ҡG
�Ĥ@�g04/14(�T)00:00~04/20(�G)23:59 �������o��Y�F���g���C
�g��ȵo�e��G04/22(�|)�e

�ĤG�g04/21(�T)00:00~04/27(�G)23:59 �������o��Y�F���g���C
�g��ȵo�e��G04/29(�|)�e

�ĤT�g04/28(�T)00:00~05/04(�G)23:59 �������o��Y�F���g���C
�g��ȵo�e��G05/06(�|)�e

�ĥ|�g05/05(�T)00:00~05/11(�G)23:59 �������o��Y�F���g���C
�g��ȵo�e��G05/13(�|)�e

�o�e����p���ܰ��H�ɤ��i�q��
			</c:if>
			<c:if test="${announcementVO != null && announcementVO.an_cont.length() != 0}">
${announcementVO.an_cont.trim()}
			</c:if>
			</textarea>
		</td>
	</tr>
	<tr>
		<td>�o����:</td>
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
		<td>��ܷӤ�:</td>
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
<input type="submit" value="�e�X�s�W">
</FORM>
</div>
</div>
</div>
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
			alert('�ФW�ǹ���');
		}
	}
});

<!-- �ѦҺ���: https://xdsoft.net/jqplugins/datetimepicker/ -->
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