<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campsite.model.*"%>
<%@ page import="com.feature_list.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camp/camp.do"
		name="form1" enctype="multipart/form-data" onsubmit="return false;">
	<table id="camp_plc">
		<tr id="title">
			<th>���W��</th>
			<th>�ƶq</th>
			<th>�H��</th>
			<th>�H�ƤW��</th>
			<th>�������</th>
			<th>�������</th>
		</tr>
		<tr>
			<td><input type="text"></td>
			<td><input type="number" pattern="number"></td>
			<td><input type="number" pattern="number"></td>
			<td><input type="number" pattern="number"></td>
			<td><input type="number" pattern="number"></td>
			<td><input type="number" pattern="number"></td>
		</tr>
	</table>
</FORM>
<script>
		$("#camp_plc").keydown(function(e) {
			if (e.which == 13) {				
				$("#title").next().clone().find('input').val("").end().appendTo(this)
			}
		});
		$("#send").click(function() {
			let index = 0;
			$("#title").nextAll().each(function(i, dom) {
				$(dom).find('input').attr("name", "plc"+index);
				index++;
			});
		});
	</script>
</body>
</html>