<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>专升本报名</h2>
<form action="" method="post">
	填写学号:<input type="text" name="xh"/><br/>
	填写姓名:<input type="text" name="name"/><br/>
	报考学校:<input type="text" name="school"/><br/>
	<input type="submit" value="报名"/>
</form>
<h3 style="color:#f00;">${error}</h3>
</body>
</html>