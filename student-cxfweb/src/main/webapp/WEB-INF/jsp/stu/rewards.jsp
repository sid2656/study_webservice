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
		<td colspan="6">
		<a href="<%=request.getContextPath()%>/stu/stus">学生列表页面</a>
		<a href="<%=request.getContextPath()%>/stu/import">导入学生信息</a>
		<a href="<%=request.getContextPath()%>/stu/export">导出学生信息</a>
		<a href="<%=request.getContextPath()%>/stu/punishs">查询所有处罚学生</a>
		<a href="<%=request.getContextPath()%>/stu/rewards">查询所有奖励学生</a>
		</td>
	</tr>
	<tr>
		<td>学生</td>
		<td>奖励情况</td>
		<td>奖励学年</td>
		<td>奖励学期</td>
		<td>奖励原因</td>
	</tr>
	<c:forEach items="${rps.datas }" var="rp">
		<tr>
			<td>${rp.student.name }</td><td>${rp.situation }</td>
			<td>${rp.year }</td><td>${rp.semester }</td>
			<td>
			${rp.cause }
			</td>
		</tr>
	</c:forEach>
	<tr>
	<td colspan="6">
	<jsp:include page="/jsp/pager.jsp">
		<jsp:param value="${rps.totalRecord }" name="totalRecord"/>
		<jsp:param value="rewards" name="url"/>
	</jsp:include>
	</td>
	</tr>
</table>
</body>
</html>