<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/bootstrap.jsp"/>
</head>
<body>
<c:import url="../temp/header.jsp"/>
<div class="container">
	<table class="table table-hover">
		<tr>
			<td>NO</td><td>TITLE</td><td>WRITER</td><td>DATE</td><td>HIT</td>
		</tr>
		<c:forEach items="${requestScope.list}" var="ar">
			<tr>
				<td>${ar.num }</td>
				<td><a href="./noticeSelect?num=${ar.num }">${ar.title }</a></td>
				<td>${ar.writer }</td>
				<td>${ar.reg_date }</td>
				<td>${ar.hit }</td>
			</tr>
		</c:forEach>
	</table>
	<div class="container">
		<ul class="pager">
		<c:if test="${pager.curBlock gt 1 }">
		  <li><a href="./noticeList?curPage=${pager.startNum-1}&serch=${pager.search.search}&kind=${pager.search.kind}">Previous</a></li>
		</c:if>
			 <li>
			  	<ul class="pagination">
		<c:forEach begin="${pager.startNum }" end="${pager.lastNum }" var="num">
				  <li><a href="./noticeList?curPage=${num }&serch=${pager.search.search}&kind=${pager.search.kind}">${num }</a></li>
		  </c:forEach>
				</ul>
			 </li>
		<c:if test="${pager.curBlock lt pager.totalBlock }">
		  <li><a href="./noticeList?curPage=${pager.lastNum+1}&serch=${pager.search.search}&kind=${pager.search.kind}">Next</a></li>
		</c:if>
		</ul>
	</div>
	<a href="./noticeWrite"><input type="button" value="Write" class="btn btn-primary"></a>
</div>
</body>
</html>