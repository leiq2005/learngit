<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 用户bean对象 -->
<jsp:useBean id="person" class="com.stronglei.web.test.Person"></jsp:useBean>
<!-- JSTL核心标签库导入 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



<title>Insert title here</title>
</head>
<body>




	<h1>JSTL 学习</h1>
	<c:out value="雷区"></c:out>
	<c:catch var="error">
		<c:import url="./doc/linux.txt" charEncoding="UTF-8"></c:import>
	</c:catch>


	<!-- 简单条件判断 -->
	<c:if test="${empty error}">
		<c:out value="异常是空(${str})"></c:out>
	</c:if>
	<!-- 条件判断 -->
	<c:choose>
		<c:when test="${empty error}">
			<c:out value="页面没有报错！"></c:out>
		</c:when>
		<c:otherwise>
			<c:out value="页面报错： ${error}"></c:out>

			<!-- 页面重定向（可以带参数） -->
			<c:redirect url="error.jsp">
				<c:param name="user">stronglei</c:param>
				<c:param name="password">123</c:param>
			</c:redirect>
		</c:otherwise>
	</c:choose>

	<!-- 生成url -->
	<c:url value="http://baidu.com" var="bd" scope="session"></c:url>
	<a href="${bd}">百度</a>




	<!-- 设置用户对象的值 -->
	<c:set value="雷强" target="${person}" property="info"></c:set>
	<c:set value="男" target="${person}" property="sex"></c:set>
	<c:set value="30" target="${person}" property="age"></c:set>

	<br>
	<c:out value="获取 bean age:${person.info}"></c:out>
	<br>
	<c:out value="获取 bean age:${person.sex}"></c:out>
	<br>
	<c:out value="获取 bean age:${person.age}"></c:out>
	<br>


	<form action="error.jsp">
		<input>
		<button>提交</button>
	</form>
</body>
</html>