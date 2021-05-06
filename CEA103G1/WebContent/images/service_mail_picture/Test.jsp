<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="BIG5"%>
    <%@ page  import="com.service_mail_picture.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
    #container {
            padding: 10px;
            border: 1px black solid;
            width: 600px;
            margin: 0px auto;
            margin-top: 50px;
        }
        .align{
        	display: inline;
        	vertical-align: text-top;
        }
        #preview, .change{
        	margin: 10px 0px;
        }
        img{
        	width: 160px;
        	margin: 10px;
        }
        .delete{
        	display: none;
        }

	</style>

</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/TestPutPhoto" enctype="multipart/form-data" >
	<input type="file" name="myfile" multiple>
	<input type="submit">
</form>
<jsp:useBean id="service_mail_pictureSvc" class="com.service_mail_picture.model.Service_mail_pictureService"/>
<img src="${service_mail_pictureSvc.getOneService_mail_picture(12).mail_pic}" width=30px>
<img src="<%=request.getContextPath()%>/images/campionLogoCircle.png" width=30px>

<div id='container'>
        <form method="post" action="<%=request.getContextPath()%>/TestPutPhoto" enctype="multipart/form-data">
        
            <label>請上傳圖片檔案：</label>
            <input type="file" id='myFile' name='files' multiple>
            <input type="submit" value="上傳">
        </form>
        <div id='preview'>               
        </div>        
    </div>
    <script>
        let myFile = document.getElementById('myFile');
        let preview = document.getElementById('preview');
	let imgs = document.getElementsByClassName('img');        

        myFile.addEventListener('change', function(e) {
	    while(imgs.length != 0){
		imgs[0].remove();
	    }
        	let files = e.target.files;
            for (let i = 0; i < files.length; i++) {
                if (files[i].type.indexOf('image') > -1) {
                    fileName = files[i].name;
                    //console.log(files[i]);
                    let reader = new FileReader();
                    reader.addEventListener('load', function(e) {
                        let result = e.target.result;
                        //console.log(result);
                        let img = document.createElement('img');
                        img.setAttribute('class', 'img');
                        img.classList.add('align');
                        img.src = result;
                        preview.append(img);
                    });
                    reader.readAsDataURL(files[i]);
                } else {
                    alert('請上傳圖檔');
                }
            }
        });

    </script>
</body>
</html>