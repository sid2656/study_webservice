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
当前用户：${loginUser.nickname}
<%
List<User> users = (List<User>)request.getAttribute("users");
for(User u:users){
%>
	<%=u.getNickname()%>-------<%=u.getUsername()%>
	<a href="user.do?username=<%=u.getUsername()%>&method=delete">删除</a>
<%
}
%>
</body>
</html>