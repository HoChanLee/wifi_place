<%@page import="localhost.wifi_place.BookMark"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
<%
String id = request.getParameter("id");
BookMark db = new BookMark();
db.favDelete(id);
%>
<script>
alert("삭제 되었습니다.");
location.href="bookmark-list.jsp";
</script>
</body>
</html>