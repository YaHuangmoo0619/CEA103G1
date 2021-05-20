<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.member.model.*" %>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="redis.clients.jedis.Jedis"%>
<%@ page import="java.util.*"%>
<%
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>

<%
	Jedis jedis = new Jedis("localhost", 6379);
	jedis.auth("123456");
	jedis.select(6);
	// 	List<String> tag_list = new ArrayList<>();
	Map<String, Long> tag_list = new TreeMap<>();

	//�C�X�C�@�g���ҥثe�@���X�g�峹
	//     for (String str : jedis.smembers("board:6:tags")){
	//     System.out.println(jedis.scard("tag:"+str+":posts"));
	//     tag_list.add(str);
	//     }

	for (String str : jedis.smembers("board:6:tags")) {
		//System.out.println(jedis.scard("tag:"+str+":posts"));
		tag_list.put(str, jedis.scard("tag:" + str + ":posts"));
	}

	jedis.close();
	pageContext.setAttribute("tag_list", tag_list);
%>
<% 
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO"); 
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
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
<title>�s�W�峹 - addArticle.jsp�e�x</title>
<%@ include file="/part-of/partOfCampion_frontTop_css.txt"%>



<style>


#tags_add_list {
	width: 500px;
	height: 54;
	padding: 6px 0px 6px 8px;
	border: 1px;
	border-color: black;
	display: inline-flex;
	flex-wrap: wrap;
}

#tags_add_list input {
	min-width: 0px;
}

.tag_prepared_to_add {
	width: 72;
	height: 32;
	display: inline-block;
	border-color: coral;
}

.tag_num {
	display: inline-block;
}

.container{
padding: 20px 50px 20px 50px;
/* background:#ff7d40; */
}

.cancelAndNextParent{
text-align: right;
}
.cancelAndNext{
display:inline-block;
padding:0px 0px 0px 20px;
}

.main_div{
padding: 0px 0px 20px 0px;
}
</style>






</head>
<body bgcolor='white'>
<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>


	

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
<div class="container">
	<h3 class=main_div>�o��峹</h3>
	<FORM METHOD="post" ACTION="/CEA103G1/article/article.do" name="form1"
		autocomplete>
		
			<jsp:useBean id="bd_clSvc" scope="page"
				class="com.board_class.model.Board_ClassService" />
			
				<div class=main_div>
				<select size="1" name="bd_cl_no">
						<c:forEach var="board_classVO" items="${bd_clSvc.all}">
							<option value="${board_classVO.bd_cl_no}"
								${(articleVO.bd_cl_no==board_classVO.bd_cl_no)? 'selected':'' }>${board_classVO.bd_name}
						</c:forEach>
				</select>
				</div>
				
				<div class=main_div>
				<input type="TEXT" name="art_title" size="45"
					value="<%=(articleVO == null) ? "" : articleVO.getArt_title()%>"
					placeholder="�п�J�峹���D" />
				</div>
				
				<div class=main_div>
				<textarea id="art_cont" name="art_cont"><%=(articleVO == null) ? "" : articleVO.getArt_cont()%></textarea>
				</div>
			

		<div class="cancelAndNextParent main_div">
		<div class="cancelAndNext" onclick="history.back()">����</div>
		<div class="cancelAndNext" onclick="showModal1()">�U�@�B</div>
		</div>
		
		<input type="hidden" name="action" value="insert">
		<input type="hidden" name="mbr_no" value="${memberVO.getMbr_no()}" />

		<!-- 		Modal      -->
		<div class="modal" tabindex="-1" role="dialog" id="test1">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title" style="text-align: center;">�[�J���D</h3>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div id="tags_add_list">

							<input placeholder="�ھڧA���峹���e�j�M�������D" id=tags_input name=tags_input value="">
						</div>
						<br>




						����:<font color=red><b>*</b></font>
						<div class=tag_can_be_selected id="tag_can_be_selected">
<%-- 							<% --%>
<!--  								int tag_count = 1; -->
<%-- 							%> --%>
							<c:forEach var="tag_list" items="${tag_list}">
								<div class=tag_selected_parent>
									<div class="tag_selected tag_num" id=tag${tag_list.key}>${tag_list.key}</div>
									<div class=tag_num>${tag_list.value}�g�峹</div>
								</div>
<!-- 								<div style="display: none"> -->
<%-- 									<div style="display: none"><%=tag_count++%></div> --%>
<!-- 								</div> -->
							</c:forEach>
							
							
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">��^</button>
						<input type="submit" value="�e�X�s�W" class="btn btn-primary">
					</div>
				</div>
			</div>
		</div>

	</FORM>

</div>


	<script>
		$('#art_cont').summernote(
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


		$("body").on("click",".tag_selected_parent",function() { //����ҳQ�I���ɭԡA�n�[��w�s�W�����ҦC�������óQ�I��������
// 			if($(this).children("div:first") >0){ //�p�G.tag_selected_parent���U��div����
	            var tag_text1 = $(this).children("div:first").html(); 
				var new_tag = $('<div style="border: solid; border-color:black" class=tag_prepared_to_add display:flex>'
						+ tag_text1 + '</div>'); //�n���w�s�W�����ҦC�������e
				$("#tags_add_list").prepend(new_tag); //��J�w�s�W�����ҦC��
				$(this).children("div:first").hide(); //�쥻������
				$(this).children("div:first").next().hide(); 

				
// 	        }else{
// 	        	console.log("�Ӥ������s�b"); //�p�G���s�b�h���@��
// 	        	var x = document.getElementById("tags_input").value;
// 				var new_tag = $('<div style="border: solid; border-color:black" class=tag_prepared_to_add display:flex>'
// 						+ x + '</div>');
// 				console.log("x="+x);
// 	        	$(this).children("button:first").hide(); //�I��button�@�˷|���øӿﶵ�A�÷s�Wxx���Ҧ�new_tag
// 	        	$("#tags_add_list").prepend(new_tag);
// 	        	$(this).children("button:first").next().hide();
// 	        }
			
							
			});

		$("#tags_add_list").on("click", ".tag_prepared_to_add", function() { //��C�������ҳQ�I�A�|�Q�����A�ç�쥻���ê����ҦA�����
			var tag_text2 = $(this).html();
			$("div:contains('" + tag_text2 + "')").show();
			$("div:contains('" + tag_text2 + "')").next().show();
			$(this).remove();

		});



		$("input[name*='tags_input']").on("input propertychange", function() { //��ɺ�ť��J���ܤ�
			console.log("���b��J��");
			var x = document.getElementById("tags_input").value;
			console.log(x);
// 			var document.getElementById(".tag_selected_parent");//��w�g�b���ҦC��������
			
			$.ajax({ //�t�d�Ǩ�articleServlet �^��
				type : "POST",
				url : "http://localhost:8081/CEA103G1/article/article.do",
				data : {action: "search_tag",tag:x}, //�n�Ǫ��Ѽ� action�B�n�s�W�����ҡB�u�ثe�w�g�b�ݷs�W�����ҦC�������ҡv
				dataType: "json",
				success : function(data) {
					$("#tag_can_be_selected").html(""); //�N���ҿ��M��
// 					console.log($.isEmptyObject(data)); //���ե�
					if($.isEmptyObject(data)){ //�p�G��Ʈw�٨S���o�Ӽ���
						$("#tag_can_be_selected").append(
								`<div class=tag_selected_parent>`+`
								<button type="button">�s�W`+x+`����</button>`	
								 +`</div>`
								);
					}
					
// 					var tagString = "";
// 					for(var i = 0 ; i<data.length; i++){
//                         $("#tag_can_be_selected").append(data[i]+"</br>");
//                     }
						
//�٭n�K�[�P�_�� �p�G�o�Ǹ�Ƥ��S���Pinput����J���ȧ����ǰt�����ҡA�N�n���@�ӿﶵ�O�u�s�Wxxx���ҡv
					for(var key in data){
// 						$("#tag_can_be_selected").append(key+":"+data[key]+"</br>");
// 						alert(key);//���json����key

// 						alert(data[key]);//���json����key������value��
						$("#tag_can_be_selected").append(
						`<div class=tag_selected_parent>
							<div class="tag_selected tag_num" id="tag`+key+`">`+key+`</div>
							<div class=tag_num>`+data[key]+`�g�峹</div>
						 </div>`
						);
						}
						
						
				},
				 
                error:function(xhr, ajaxOptions, thrownError){

                    alert(xhr.status+"\n"+thrownError);
                }
			}); 
		});

		function showModal1() {
			$('#test1').modal('show');
		}
	</script>
	
	
	
	    <script>
        window.console = window.console || function(t) {};
        if (document.location.search.match(/type=embed/gi)) {
    	window.parent.postMessage("resize", "*");
  }
        
        function upload(){
        	$("button[name='Picture]'").click();
        }
</script>

</body>
</html>