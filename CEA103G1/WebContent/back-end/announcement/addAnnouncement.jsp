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


<title>���i�C��s�W - addAnnouncement.jsp</title>

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
		 <h3>���i�C��s�W - addAnnouncementFunction.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/announcement/select_page.jsp"><img src="<%=request.getContextPath()%>/images/logo.png" width="50" height="50" border="0"><br>�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

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
					<c:forEach var="employeeVO" items="${employeeSvc.all}">	
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