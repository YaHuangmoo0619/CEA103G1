<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.sql.*, javax.sql.* "%>
<%@ page import="javax.naming.* "%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<%
    response.setContentType("image/jpeg");
	String camp_no = request.getParameter("camp_no");
	Connection con;
	//mysql³s±µ
	Context ctx = new javax.naming.InitialContext();
	DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/David");
	con = ds.getConnection();

	try {
		Statement stmt = con.createStatement();
		String sql = " SELECT CONFIG FROM CAMPION WHERE CAMP_NO = " + camp_no;
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("CONFIG"));
			byte[] buf = new byte[in.available()]; // 4K buffer
			int len;
			OutputStream outs = response.getOutputStream();
			while ((len = in.read(buf)) != -1) {
				outs.write(buf, 0, len);
			}
			in.close();
		} else {
			rs.close();
			response.sendRedirect("./images/error.gif");
		}
	} finally {
		con.close();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>

</body>
</html>