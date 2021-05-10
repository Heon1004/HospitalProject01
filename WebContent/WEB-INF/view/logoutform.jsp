<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/style.css">
<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@300&display=swap" rel="stylesheet">
<title>キムくん病院・ホーム</title>
</head>
<body>
<%
    HttpSession userSession = request.getSession();
    userSession.invalidate();
    out.println("<script>location.href = 'index.jsp';</script>");
%>

</body>
</html>