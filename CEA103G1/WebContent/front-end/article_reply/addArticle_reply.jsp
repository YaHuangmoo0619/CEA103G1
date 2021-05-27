<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article_reply.model.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="java.sql.Timestamp"%>

<%
	int art_no = Integer.parseInt(request.getParameter("art_no")); 
	ArticleService aritcleSvc = new ArticleService();
	ArticleVO articleVO = new ArticleVO();
	articleVO = aritcleSvc.getOneArticle(art_no);
	pageContext.setAttribute("articleVO", articleVO);
%>
	
<%
	int mbr_no = Integer.parseInt(request.getParameter("mbr_no")); 

%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�d��</title>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
    <link rel="apple-touch-icon" type="image/png" href="https://cpwebassets.codepen.io/assets/favicon/apple-touch-icon-5ae1a0698dcc2402e9712f7d01ed509a57814f994c660df9f7a952f3060705ee.png" />
    <meta name="apple-mobile-web-app-title" content="CodePen">
    <link rel="shortcut icon" type="image/x-icon" href="https://cpwebassets.codepen.io/assets/favicon/favicon-aec34940fbc1a6e787974dcd360f2c6b63348d4b1f4e06c77743096d55480f33.ico" />
    <link rel="mask-icon" type="" href="https://cpwebassets.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg" color="#111" />
<style>
.container{
padding:20px 0px 0px 0px;
}

.respondandcancel{
display:inline-block;
float:right;
margin:0px 20px 0px 0px;
width:56px;
height:40px;
}
</style>
</head>
<body bgcolor='white'>
<div class=container>
<h3>�^���峹&nbsp;:&nbsp;${articleVO.art_title}</h3>
<h3>�^�����e</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="/CEA103G1/article_reply/article_reply.do" name="form1">


	
<textarea id="rep_cont" name="rep_cont"></textarea>


<%-- 		<input type="TEXT" name="rep_cont" size="45" value="<%=article_replyVO.getRep_cont()%>" />	 --%>
	



<br>

<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
<input type="hidden" name="art_no" value="<%=art_no%>">
<input type="hidden" name="mbr_no" value="<%=mbr_no%>">
<input type="hidden" name="action" value="insert">

<button type="submit" class="btn btn-default respondandcancel">�^��</button>
<button type="button" class="btn btn-default respondandcancel cancel">����</button>

</FORM>
</div>


<script>
$('#rep_cont').summernote(
		{
			placeholder : '�п�J��r',
			tabsize : 2,
			height : 300,
			maxHeight: 300, //�T�w�A���g���ܴN�i�H�N�Զ}
			toolbar : [
			// [groupName, [list of button]]
			[ 'style', [ 'bold', 'italic', 'underline' ] ],
					[ 'fontsize', [ 'fontsize' ] ],
					[ 'color', [ 'color' ] ],
					[ 'para', [ 'paragraph' ] ],
					[ 'insert', [ 'picture' ] ], ]
		});
		
$(".cancel").click(function(){
	window.history.go(-1);
})		
</script>


</body>
</html>