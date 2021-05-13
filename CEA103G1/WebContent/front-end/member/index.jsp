<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
    function refresh() {
   
   
     src="index.jsp?id="+Math.random();
    }
</script>
<%@ page contentType="charset=Big5" language="java"
        import ="java.awt.*"
         import ="java.awt.image.BufferedImage"
          import="java.util.*"
         import="javax.imageio.ImageIO"
         pageEncoding="Big5"%>
<%
response.setHeader("Cache-Control","no-cache");
  int width=60,height=20;
  BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

  Graphics g=image.getGraphics();  //設定背景色
  g.setColor(new Color(200,200,200));
  g.fillRect(0,0,width,height);

  Random rnd=new Random();
  int randNum=rnd.nextInt(8999)+1000;
  String randStr=String.valueOf(randNum);

  session.setAttribute("randStr",randStr);

  g.setColor(Color.black);
  g.setFont(new Font("", Font.PLAIN,20));
  g.drawString(randStr,10,17);

    for (int i = 0; i < 100; i++) {
   
   
        int x=rnd.nextInt(width);
        int y=rnd.nextInt(height);
        g.drawOval(x,y,1,1);
    }
    //輸出影象到頁面
    ImageIO.write(image,"JPEG",response.getOutputStream());
    out.clear();
    out=pageContext.pushBody();

%>
</body>
</html>