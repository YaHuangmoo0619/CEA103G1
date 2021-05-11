<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>

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

	//列出每一篇標籤目前共有幾篇文章
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
<title>新增文章 - addArticle.jsp前台</title>



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

#tags_add_list {
	width: 300px;
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

</style>


<style>
.search-container {
  width: 490px;
  display: block;
  margin: 0 auto;
}

input#search-bar {
  margin: 0 auto;
  width: 100%;
  height: 45px;
  padding: 0 20px;
  font-size: 1rem;
  border: 1px solid #D0CFCE;
  outline: none;
}
input#search-bar:focus {
  border: 1px solid #008ABF;
  transition: 0.35s ease;
  color: #008ABF;
}
input#search-bar:focus::-webkit-input-placeholder {
  transition: opacity 0.45s ease;
  opacity: 0;
}
input#search-bar:focus::-moz-placeholder {
  transition: opacity 0.45s ease;
  opacity: 0;
}
input#search-bar:focus:-ms-placeholder {
  transition: opacity 0.45s ease;
  opacity: 0;
}

.search-icon {
  position: relative;
  float: right;
  width: 75px;
  height: 75px;
  top: -62px;
  right: -45px;
}
</style>



</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>文章資料新增 - addArticle.jsp 前台</h3>
			</td>
			<td>
				<h4>
					<a href="/CEA103G1/back-end/article/select_page.jsp"><img
						src="/CEA103G1/images/Campion.png" width="100" height="100"
						border="0"></a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>文章資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="/CEA103G1/article/article.do" name="form1"
		autocomplete>
		<table>
			<jsp:useBean id="bd_clSvc" scope="page"
				class="com.board_class.model.Board_ClassService" />
			<tr>
				<td>發文看板:<font color=red><b>*</b></font></td>
				<td><select size="1" name="bd_cl_no">
						<c:forEach var="board_classVO" items="${bd_clSvc.all}">
							<option value="${board_classVO.bd_cl_no}"
								${(articleVO.bd_cl_no==board_classVO.bd_cl_no)? 'selected':'' }>${board_classVO.bd_name}
						</c:forEach>

				</select></td>
			</tr>
			<tr>
				<td>發文者:</td>
				<td><input type="TEXT" name="mbr_no" size="45"
					value="<%=(articleVO == null) ? "" : articleVO.getMbr_no()%>" /></td>
			</tr>
			<tr>
				<td>文章標題:</td>
				<td><input type="TEXT" name="art_title" size="45"
					value="<%=(articleVO == null) ? "" : articleVO.getArt_title()%>"
					placeholder="標題" /></td>
			</tr>
			<tr>
				<td>文章內容:</td>
				<td><textarea id="art_cont" name="art_cont"><%=(articleVO == null) ? "" : articleVO.getArt_cont()%></textarea></td>
			</tr>


		</table>
		<br>
		<br>
		<div onclick="history.back()">取消</div>
		<div onclick="showModal1()">下一步</div>

		<br> <input type="hidden" name="action" value="insert">



		<!-- 		Modal      -->
		<div class="modal" tabindex="-1" role="dialog" id="test1">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title" style="text-align: center;">加入話題</h3>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div id="tags_add_list">

							<input placeholder="根據你的文章內容搜尋相關話題" id=tags_input name=tags_input value="">
						</div>
						<br>




						標籤:<font color=red><b>*</b></font>
						<div class=tag_can_be_selected id="tag_can_be_selected">
<%-- 							<% --%>
<!--  								int tag_count = 1; -->
<%-- 							%> --%>
							<c:forEach var="tag_list" items="${tag_list}">
								<div class=tag_selected_parent>
									<div class="tag_selected tag_num" id=tag${tag_list.key}>${tag_list.key}</div>
									<div class=tag_num>${tag_list.value}篇文章</div>
								</div>
<!-- 								<div style="display: none"> -->
<%-- 									<div style="display: none"><%=tag_count++%></div> --%>
<!-- 								</div> -->
							</c:forEach>
							
							
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">返回</button>
						<input type="submit" value="送出新增" class="btn btn-primary">
					</div>
				</div>
			</div>
		</div>

	</FORM>



	<script>
		$('#art_cont').summernote(
				{
					placeholder : '請輸入文字',
					tabsize : 2,
					height : 100,
					toolbar : [
					// [groupName, [list of button]]
					[ 'style', [ 'bold', 'italic', 'underline' ] ],
							[ 'fontsize', [ 'fontsize' ] ],
							[ 'color', [ 'color' ] ],
							[ 'para', [ 'paragraph' ] ],
							[ 'insert', [ 'link', 'picture' ] ], ]
				});


		$("body").on("click",".tag_selected_parent",function() { //當標籤被點的時候，要加到預新增的標籤列表中並隱藏被點擊的標籤
			if($(this).children("div:first") >0){ //如果.tag_selected_parent底下有div的話
	            var tag_text1 = $(this).children("div:first").html(); //將div html設為空
				var new_tag = $('<div style="border: solid; border-color:black" class=tag_prepared_to_add display:flex>'
						+ tag_text1 + '</div>');
				$("#tags_add_list").prepend(new_tag);
				$(this).children("div:first").hide();
				$(this).children("div:first").next().hide();
				
	        }else{
	        	console.log("該元素不存在"); //如果不存在則不作為
	        	var x = document.getElementById("tags_input").value;
				var new_tag = $('<div style="border: solid; border-color:black" class=tag_prepared_to_add display:flex>'
						+ x + '</div>');
				console.log("x="+x);
	        	$(this).children("button:first").hide(); //點選button一樣會隱藏該選項，並新增xx標籤至new_tag
	        	$("#tags_add_list").prepend(new_tag);
	        	$(this).children("button:first").next().hide();
	        }
			
							
			});

		$("#tags_add_list").on("click", ".tag_prepared_to_add", function() { //當列表中的標籤被點，會被移除，並把原本隱藏的標籤再度顯示
			var tag_text2 = $(this).html();
			$("div:contains('" + tag_text2 + "')").show();
			$("div:contains('" + tag_text2 + "')").next().show();
			$(this).remove();

		});



		$("input[name*='tags_input']").on("input propertychange", function() { //實時監聽輸入框變化
			console.log("正在輸入中");
			var x = document.getElementById("tags_input").value;
			console.log(x);
// 			var document.getElementById(".tag_selected_parent");//抓已經在標籤列表中的標籤
			
			$.ajax({ //負責傳到articleServlet 回傳
				type : "POST",
				url : "http://localhost:8081/CEA103G1/article/article.do",
				data : {action: "search_tag",tag:x}, //要傳的參數 action、要新增的標籤、「目前已經在待新增的標籤列表中的標籤」
				dataType: "json",
				success : function(data) {
					$("#tag_can_be_selected").html(""); //將標籤選單清空
// 					console.log($.isEmptyObject(data)); //測試用
					if($.isEmptyObject(data)){ //如果資料庫還沒有這個標籤
						$("#tag_can_be_selected").append(
								`<div class=tag_selected_parent>`+`
								<button type="button">新增`+x+`標籤</button>`	
								 +`</div>`
								);
					}
					
// 					var tagString = "";
// 					for(var i = 0 ; i<data.length; i++){
//                         $("#tag_can_be_selected").append(data[i]+"</br>");
//                     }
						
//還要添加判斷式 如果這些資料中沒有與input中輸入的值完全匹配的標籤，就要有一個選項是「新增xxx標籤」
					for(var key in data){
// 						$("#tag_can_be_selected").append(key+":"+data[key]+"</br>");
// 						alert(key);//獲取json物件的key

// 						alert(data[key]);//獲取json物件key對應的value值
						$("#tag_can_be_selected").append(
						`<div class=tag_selected_parent>
							<div class="tag_selected tag_num" id="tag`+key+`">`+key+`</div>
							<div class=tag_num>`+data[key]+`篇文章</div>
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
</script>

</body>
</html>