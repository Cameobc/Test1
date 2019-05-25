<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/bootstrap.jsp"/>
<script type="text/javascript">
	$(function() {
		var count =0;
		var d=0;
		$('#add').click(function() {
				d++;
			if(count<5){
				$('#fileUpload').append('<input type="file" class="form-control" id="f1" name="f'+d+'"><span class="del">X</span>');
				count++;
			}else{
				alert('더는 추가할 수 없습니다.');
			}
		});
		//***********************
		$('#fileUpload').on('click', '.del', function() {
			$(this).prev().remove();
			$(this).remove();
			count--;
		})
	});
</script>
</head>
<body>
<c:import url="../temp/header.jsp"/>
<div class="container">
	<h1>Notice Write</h1>
	<form action="./noticeWrite" method="post" enctype="multipart/form-data">
	    <div class="form-group">
	      <label for="title">Title:</label>
	      <input type="text" class="form-control" id="title" name="title">
	    </div>
	    <div class="form-group">
	      <label for="writer">Writer:</label>
	      <input type="text" class="form-control" id="writer" name="writer">
	    </div>
	    <div class="form-group">
		  <label for="contents">Contents:</label>
		  <textarea class="form-control" rows="20" id="contents" name="contents"></textarea>
		</div>
		<div class="form-group" id="fileUpload">
	      <label for="file">File:</label>
	    </div>
	    <div class="form-group">
	    	<input type="button" class="btn btn-primary" value="Add" id="add">
	    </div>
		<button class="btn btn-danger">Write</button>
  </form>
</div>
</body>
</html>