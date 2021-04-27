<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.announcement.model.*"%>
<%@ page import="java.util.*"%>

<% 
AnnouncementService announcementSrv = new AnnouncementService();
List<AnnouncementVO> list = announcementSrv.getAll();
Integer lastAn_no = list.get(0).getAn_no();
request.setAttribute("lastAn_no", lastAn_no);
%>

<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<title>Announcement: Home</title>

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
   <tr><td><h3>Announcement: Home</h3><img src="<%=request.getContextPath()%>/images/logo.png" width="50" height="50" border="0"><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Announcement: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty  errorMsgs}">
	<font style="color:red">�Эץ��H�U���~�G</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/announcement/listAllAnnouncement.jsp'>List</a> all Announcements.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/announcement/announcement.do" >
        <b>��J���i�s�� (�p1):</b>
        <input type="text" name="an_no" placeholder="1��${lastAn_no}�����Ʀr">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="announcementSvc" scope="page" class="com.announcement.model.AnnouncementService" />
   
   <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/announcement/announcement.do" >
       <b>��ܤ��i�s��:</b>
       <select size="1" name="an_no">
       			<option value= 0>--�п��--
         <c:forEach var="announcementVO" items="${announcementSvc.all}" > 
          		<option value="${announcementVO.an_no}">${announcementVO.an_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  	</li>
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/announcement/announcement.do" >
       <b>��ܧ�s���:</b>
       <select size="1" name="an_skd_date">
       <% 
			AnnouncementService announcementSvc2 = new AnnouncementService();
			List<AnnouncementVO> lists = announcementSvc2.getAll();
			List<Date> dateList = new ArrayList<Date>();
			Set<Date> dateSet = new HashSet<Date>();
			for(AnnouncementVO announcementVO : lists){
				if(dateSet.add(announcementVO.getAn_skd_date())){
					dateList.add(announcementVO.getAn_skd_date());
				}
			}
			request.setAttribute("an_skd_date", dateList);
		%>
			<option value= 0>--�п��--
         <c:forEach var="an_skd_date" items="${an_skd_date}" > 
	        <option value="${an_skd_date}">${an_skd_date}
         </c:forEach> 
       </select>
       <input type="hidden" name="action" value="getDate_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
</ul>


<h3>���i�޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/announcement/addAnnouncement.jsp'>Add</a> a new Announcement.</li>
</ul>

</body>
</html>