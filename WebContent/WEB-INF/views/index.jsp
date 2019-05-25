<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<c:import url="./temp/bootstrap.jsp"/>
</head>
<body>
<c:import url="./temp/header.jsp"/>
<h1>Welcome ${sessionScope.member.id }</h1>
<img alt="thunder" src="./images/성현제_20190304b.png">
</body>
</html>