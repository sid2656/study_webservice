<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table align="center" border="1" width="1024">
	<tr>
		<td>
		<a href="<%=request.getContextPath()%>/stu/stus">学生列表页面</a>
		<a href="<%=request.getContextPath()%>/stu/import">导入学生信息</a>
		<a href="<%=request.getContextPath()%>/stu/export">导出学生信息</a>
		<a href="<%=request.getContextPath()%>/stu/punishs">查询所有处罚学生</a>
		<a href="<%=request.getContextPath()%>/stu/rewards">查询所有奖励学生</a>
		</td>
	</tr>
	<tr>
	<td>
	<form action="import" method="post" enctype="multipart/form-data">
	选择导入的文件，必须是xls类型<input type="file" name="stuList"/><input type="submit"/>
	${error}
	</form>
	</td>
	</tr>
</table>
</body>
</html>