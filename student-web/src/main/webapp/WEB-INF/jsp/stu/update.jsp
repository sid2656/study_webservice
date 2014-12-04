<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>修改${stu.name }的处罚情况</h2>
<sf:form method="POST" modelAttribute="stu">
学生学号:<sf:input path="xh"/><br/>
学生考生号:<sf:input path="ksh"/><br/>
学生姓名:<sf:input path="name"/><br/>
学生性别:<sf:radiobutton path="sex" value="男"/>男<sf:radiobutton path="sex" value="女"/>女<br/>
当前状态:<sf:select path="status">
	<sf:option value="0">在校</sf:option>
	<sf:option value="1">离校</sf:option>
</sf:select><br/>
<input type="submit" value="修改学生信息"/>

</sf:form>
</body>
</html>