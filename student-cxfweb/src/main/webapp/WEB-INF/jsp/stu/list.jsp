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
		<td>学生学号</td>
		<td>学生姓名</td>
		<td>考生号</td>
		<td>性别</td>
		<td>状态</td>
		<td>操作</td>
	</tr>
	<c:forEach items="${stus.datas }" var="stu">
		<tr>
			<td>${stu.xh }</td><td>${stu.name }</td>
			<td>${stu.ksh }</td><td>${stu.sex }</td>
			<td>
				<c:if test="${stu.status eq 0 }">在校</c:if>
				<c:if test="${stu.status eq 1}"><span style="font-weight:bold;color:#f00">离校</span></c:if>
			</td>
			<td>
			<a href="<%=request.getContextPath()%>/stu/rp/${stu.id}">查询学生奖惩</a>
			<a href="<%=request.getContextPath()%>/stu/update/${stu.id}">修改学生信息</a>
			<a href="<%=request.getContextPath()%>/stu/addReward/${stu.id}">设置奖励</a>
			<a href="<%=request.getContextPath()%>/stu/addPunish/${stu.id}">设置处罚</a>
			</td>
		</tr>
	</c:forEach>
	<tr>
	<td colspan="6">
	<jsp:include page="/jsp/pager.jsp">
		<jsp:param value="${stus.totalRecord }" name="totalRecord"/>
		<jsp:param value="stus" name="url"/>
	</jsp:include>
	</td>
	</tr>
</table>
</body>
</html>