<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>


<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<script src="https://cdn.jsdelivr.net/npm/tw-city-selector@2.1.1/dist/tw-city-selector.min.js"></script>

<html>
<head>
<meta charset="BIG5">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>register</title>

<style>
	.addr-county{background:#4169E1;color:#fff}
.addr-district{background:#008000;color:#fff}
.addr-zip{background:#c00;color:#fff;border:1px solid #666}
  table#table-1 {
	background-color: #CCCCFF;
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
table#table-1 {
	background-color: #CCCCFF;
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
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>���U����</h3>
			</td>
			<td></td>
		</tr>
	</table>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form method="post"
		action="<%=request.getContextPath()%>/member/member.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>�b��</td>
				<td><input type="TEXT" name="acc" id="acc" size="45"
					value="<%= (memberVO == null)? "" : memberVO.getAcc()%>" /></td>
			</tr>
			<tr>
				<td>�K�X</td>
				<td><input type="password" name="pwd" id="pwd" size="45"
					value="<%= (memberVO == null)? "" : memberVO.getPwd()%>" /></td>
			</tr>
			<!--
	<tr>
		<td>�T�{�K�X</td>
		<td><input type="password" name="pwd" id="pwd" size="45" value="<%= (memberVO == null)? "" : memberVO.getPwd()%>"/></td>
	</tr>
	-->
			<tr>
				<td>�����Ҧr��</td>
				<td><input type="TEXT" name="id" id="pwd" size="45"
					value="<%=(memberVO == null) ? "" : memberVO.getId()%>" /></td>
			</tr>
			<tr>
				<td>�m�W</td>
				<td><input type="TEXT" name="name" id="name" size="45"
					value="<%=(memberVO == null) ? "" : memberVO.getName()%>" /></td>
			</tr>
			<tr>
				<td>�ͤ�</td>
				<td><input name="bday" id="bday" type="text"></td>
			</tr>
			<tr>
				<td>�ʧO</td>
				<td onclick="noChecked(this)"><c:if
						test="${memberVO.getSex() == null}">
						<input type="radio" id="state1" name="sex" value="1">
						<label for="state1">�k</label>
						<input type="radio" id="state1" name="sex" value="2">
						<label for="state2">�k</label>
					</c:if> <c:if test="${memberVO.getSex() == 1}">
						<input type="radio" id="state1" name="sex" value="1" checked>
						<label for="state1">�k</label>
						<input type="radio" id="state1" name="sex" value="2">
						<label for="state2">�k</label>
					</c:if> <c:if test="${memberVO.getSex() == 2}">
						<input type="radio" id="state1" name="sex" value="1">
						<label for="state1">�k</label>
						<input type="radio" id="state1" name="sex" value="2" checked>
						<label for="state2">�k</label>
					</c:if></td>
			</tr>
			<tr>
				<td>���</td>
				<td><input type="TEXT" name="mobile" id="mobile" size="45"
					value="<%=(memberVO == null) ? "" : memberVO.getMobile()%>" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="TEXT" name="mail" id="mail" size="45"
					value="<%=(memberVO == null) ? "" : memberVO.getMail()%>" /></td>
			</tr>
			<tr>
				<td>�a�}_����</td>
				<td><select id="city" name="city"></select></td>
			</tr>
			<tr>
				<td>�a�}_�ϰ�</td>
				<td><select id="dist" name="dist"></select></td>
			</tr>
			<tr>
				<td>�a�}</td>
				<td><input type="TEXT" name="add" id="add" size="45"
					 /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="register_Member">
		<input type="submit" value="���U">
	</FORM>

	<script>
		$.datetimepicker.setLocale('zh');
		$(function() {
			$('#announce_date').datetimepicker({
				format : 'Y-m-d',
				minDate : '-1970/01/01',
				timepicker : false
			});

		});
	</script>

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="<%=request.getContextPath()%>/front-end/member/city_dist.js"></script>
<script type="text/javascript">
 window.onload = function () {
		 AddressSeleclList.Initialize('city', 'dist');
   };
</script>


<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->
<% 
  java.sql.Date bday = null;
  try {
	    bday = memberVO.getBday();
   } catch (Exception e) {
	    bday = new java.sql.Date(System.currentTimeMillis());
   }
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
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

<script>
        $.datetimepicker.setLocale('zh');
        $('#bday').datetimepicker({
           theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=bday%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>

</body>
</html>