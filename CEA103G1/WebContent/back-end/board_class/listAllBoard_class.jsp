<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.board_class.model.*"%>

<%
    Board_ClassService board_classSvc = new Board_ClassService();
    List<Board_ClassVO> list = board_classSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�ݪO�޲z</title>
<link rel="stylesheet"	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

<style>
body {
  font-family: "Open Sans", sans-serif;
  line-height: 1.25;
}

table {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: 0;
  padding: 0;
  width: 100%;
  table-layout: fixed;
}

table caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

table tr {
  background-color: #f8f8f8;
  border: 1px solid #ddd;
  padding: .35em;
}

table th,
table td {
  padding: .625em;
  text-align: center;
}

table th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
}

@media screen and (max-width: 600px) {
  table {
    border: 0;
  }

  table caption {
    font-size: 1.3em;
  }
  
  table thead {
    border: none;
    clip: rect(0 0 0 0);
    height: 1px;
    margin: -1px;
    overflow: hidden;
    padding: 0;
    position: absolute;
    width: 1px;
  }
  
  table tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: .625em;
  }
  
  table td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: .8em;
    text-align: right;
  }
  
  table td::before {
    /*
    * aria-label has no advantage, it won't be read inside a table
    content: attr(aria-label);
    */
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
  }
  
  table td:last-child {
    border-bottom: 0;
  }
}

.board_manage{
margin: 20px 20px 20px 20px;
font-size:30px;
}
.add_board{
margin: 20px 20px 20px 20px;
}

</style>

</head>
<body>

<div class="board_manage">�ݪO�޲z</div>



<button type="button" class="btn btn-info add_board">�s�W�ݪO</button>


<table>
	<tr>
		<th>�ݪO�s��</th>
		<th>�ݪO�W��</th>
		<th>�ݪO���A</th>
		<th></th>
		<th></th>
	</tr>
	<c:forEach var="board_classVO" items="${list}">
		
		<tr>
			<td>${board_classVO.bd_cl_no}</td>
			<td>${board_classVO.bd_name}</td>
			<c:if test="${board_classVO.bd_stat==0}"><td>����</td></c:if>
			<c:if test="${board_classVO.bd_stat==1}"><td>���</td></c:if>
			<td>
				 <div style="display:none">${board_classVO.bd_cl_no}</div>
				 <div style="display:none">${board_classVO.bd_name}</div>
			     <input type="button" class=modify_board id=modify_board_id value="�ק�">	     
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/board_class/board_class.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="bd_cl_no"  value="${board_classVO.bd_cl_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>


		<div class="modal fade" id="add_board_modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <div>�s�W�ݪO</div>
      </div>
      <%-- ���~��C --%>
		<c:if test="${not empty errorMsgs}">
		<font style="color:red">�Эץ��H�U���~:</font>
		<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
		</ul>
		</c:if>
      <FORM METHOD="post" ACTION="/CEA103G1/board_class/board_class.do" name="form1">
      <div class="modal-body">
				�ݪO�W��:
				<input type="TEXT" name="bd_name" /><br>		
					�ݪO���A:
				  <input type="radio"  id="display" name="board_stat" value="0">
				  <label for="display">����</label>
				  <input type="radio"  id="display_none"name="board_stat" value="1">
				  <label for="display_none">���</label>

				
		<br> <input type="hidden" name="action" value="insert">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary close_modal_add_board" data-bs-dismiss="modal">����</button>
        <button type="submit" class="btn btn-primary">�s�W</button>
      </div>
      </FORM>
    </div>
  </div>
</div>



		<div class="modal fade" id="board_update" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <div>�ק�ݪO</div>
      </div>
      <%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">�Эץ��H�U���~:</font>
		<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
	</c:if>
      <FORM METHOD="post" ACTION="/CEA103G1/board_class/board_class.do" name="form2">
      <div class="modal-body">
				�ݪO�W��:
				<input type="TEXT" name="bd_name" /><br>		
					�ݪO���A:
				  <input type="radio"  id="display" name="board_stat_change" value="0">
				  <label for="display">����</label>
				  <input type="radio"  id="display_none"name="board_stat_change" value="1">
				  <label for="display_none">���</label>

				
		<br> <input type="hidden" name="action" value="update">
      </div>
      <div class=for_hidden_input></div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary close_modal_modify_board" data-bs-dismiss="modal">����</button>
        <button type="submit" class="btn btn-primary">�ק�</button>
      </div>
      </FORM>
    </div>
  </div>
</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script>
$(".add_board").click(function(){
	$('#add_board_modal').modal('show');
})

$(".modify_board").click(function(){
	var bd_name = $(this).prev().text();
	
 	var bd_cl_no = $(this).prev().prev().text();
	  $(".for_hidden_input").append('<input type="hidden" name="bd_name" value="'+bd_name+'" />');
	  $(".for_hidden_input").append('<input type="hidden" name="bd_cl_no" value="'+bd_cl_no+'" />');
	$('#board_update').modal('show');
})

  $('.close_modal_add_board').click(function(){
	  $('#add_board_modal').modal('hide');
  })
  
    $('.close_modal_modify_board').click(function(){
	  $('#board_update').modal('hide');
  })
</script>
</body>
</html>