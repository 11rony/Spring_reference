<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>미니 프로젝트</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>


<!-- 상단 메뉴 부분 -->
<nav class="navbar navbar-expand-md bg-dark navbar-dark fixed-top shadow-lg">
	<a class="navbar-brand" href="index.html">SoftSoldesk</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
	        data-target="#navMenu">
		<span class="navbar-toggler-icon"></span>        
	</button>
	<div class="collapse navbar-collapse" id="navMenu">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a href="board_main.html" class="nav-link">자유게시판</a>
			</li>
			<li class="nav-item">
				<a href="board_main.html" class="nav-link">유머게시판</a>
			</li>
			<li class="nav-item">
				<a href="board_main.html" class="nav-link">정치게시판</a>
			</li>
			<li class="nav-item">
				<a href="board_main.html" class="nav-link">스포츠게시판</a>
			</li>
		</ul>
		
		<ul class="navbar-nav ml-auto">
			<li class="nav-item">
				<a href="login.html" class="nav-link">로그인</a>
			</li>
			<li class="nav-item">
				<a href="join.html" class="nav-link">회원가입</a>
			</li>
			<li class="nav-item">
				<a href="modify_user.html" class="nav-link">정보수정</a>
			</li>
			<li class="nav-item">
				<a href="index.html" class="nav-link">로그아웃</a>
			</li>
		</ul>
	</div>
</nav>

<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
				<div class="card shadow">
					<div class="card-body">
						<form:form action="${root}board/write_pro " method="post"
							modelAttribute="writeContentBean" enctype="multipart/form-data">
							<div class="form-group">
								<form:label path="content_subject">제목</form:label>
								<form:input path="content_subject" class="form-control" />
								<form:errors path='content_subject' style='color:red' />
							</div>
							<div class="form-group">
								<form:label path="content_text">내용</form:label>
								<form:textarea path="content_text" class="form-control"
									rows="10" style="resize:none" />
								<form:errors path='content_text' style='color:red' />
							</div>
							<div class="form-group">
								<form:label path="upload_file">첨부 이미지</form:label>
								<form:input type='file' path='upload_file' class="form-control" accept="image/*" />
							</div>
						</form:form>
					</div>
				</div>
			</div>
		<div class="col-sm-3"></div>
	</div>
</div>

<div class="container-fluid bg-dark text-white" style="margin-top:50px;padding-top:30px;padding-bottom:30px">
	<div class="container">
		<p>http://www.softSoldesk.co.kr</p>
		<p>게시판 예제</p>
		<p>사업자번호 : 000-111-222</p>
	</div>
</div>

</body>
</html>
