<%@page import="cn.edu.zttc.service.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="user.do" method="post">
	<input type="hidden" name="method" value="add">
	Username:<input type="text" name="username"/><br/>
	password:<input type="text" name="password"/><br/>
	nickname:<input type="text" name="nickname"/><br/>
	<input type="submit">
</form>
</body>
</html>